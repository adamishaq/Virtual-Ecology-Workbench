package VEW.Compiler2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.RunPanel;
import VEW.Controller2.VEWController2;
import VEW.Scenario2.FileIO;

import com.sun.tools.javac.Main;


public class ModelCompiler {
  public static final String COMPILE_STATUS = new String("VEW 3.1 Compile Status: ");  
  public static void main(String[] args) {
    if (args.length==4) {
      if (args[3].toLowerCase().equals("/noc")) {
        VEWController2.NOC_Mode=true;
      }
    }
    if (args.length>=3) compile(args[0],args[1],args[2]);
    else compile("modelTree/LERM-PS/1/Model.xml","VEWTemp","xxx");
  }
    
  public static void compile(String modelPath,String tempPath,String pid) {  
    long pidNo = -1;
    if (!pid.equals("xxx")) pidNo = Long.parseLong(pid);
    long idNo=pidNo;
    String targetPath = tempPath+File.separator+pid;
    File f = new File(targetPath);
    f.mkdirs();
    String progressFile = "progress.txt";
    XMLFile ModelFile = XMLFile.LoadFile(modelPath);
    FileIO.GenerateDataFromSpec(ModelFile, targetPath,progressFile);
    XMLTag theModel = ModelFile.getTag("model");
    String[] seeds = theModel.getValue("jobs/seeds").split(",");
    XMLTag[] batchTags = theModel.getTag("jobs").getTags("batch");
    String[][] batches = new String[batchTags.length][];
    int[] batchCounters = new int[batchTags.length];
    for (int i=0; i<batchTags.length; i++) batchCounters[i]=0;
    int jobCount=1;
    for (int i=0; i<batchTags.length; i++) { 
      batches[i] = batchTags[i].getAttribute("vals").split(",");
      jobCount*=batches[i].length;
    }
    int totalJob=0;
    for (int i=0; i<seeds.length; i++) {
      int job=0;
      for (int j=0; j<batchTags.length; j++) batchCounters[j]=0;      
      while (job<jobCount) {
        String thisJobPath = targetPath+File.separator+"Job"+job+"Seed"+seeds[i]+"_"+String.valueOf(idNo)+File.separator;
        new File(thisJobPath).mkdirs();
        XMLFile newModel = (XMLFile) ModelFile.clone();
        newModel.setFileName(thisJobPath+"Model.xml");
        if (newModel.getTag("seed")!=null) newModel.getTag("seed").removeFromParent();
        newModel.addTag(new XMLTag("seed",seeds[i]));
        String jobName = theModel.getValue("buildname")+"_Seed"+seeds[i]+"_Job"+String.valueOf(job)+"_"+String.valueOf(idNo);
        long startMillis = Long.parseLong(theModel.getValue("track/start"));
        long endMillis = Long.parseLong(theModel.getValue("track/end"));
        long secStep = Long.parseLong(theModel.getValue("track/secstep"));
        long timeSteps = (endMillis-startMillis)/(secStep*1000);
        RunPanel.getLock("jobs.xml.lock");
        XMLFile xf = XMLFile.LoadFile("jobs.xml");
        XMLTag thisJobTag = xf.getTag("jobfile/jobs").getTagWhere("job","@name",jobName);
        if (thisJobTag!=null) {
          thisJobTag.setAttribute("output",thisJobPath);
          thisJobTag.setAttribute("timesteps",String.valueOf(timeSteps));
          thisJobTag.write();
        }
        
        RunPanel.releaseLock("jobs.xml.lock");
        try {
          PrintWriter P = StringTools.OpenOutputFile(thisJobPath+File.separator+"progress.txt");
          P.write("100\n");
          P.flush();
          P.close();
          P = null;
        } catch (Exception e) {}

        
        for (int j=0; j<batchTags.length; j++) {
          String name = batchTags[j].getAttribute("name");
          String val = batches[j][batchCounters[j]];
          String species = name.substring(0,name.indexOf(":"));
          name = name.substring(name.indexOf(":")+2);
          name = name.substring(0,name.indexOf("(")-1);
          newModel.getTag("species").getTagWhere("species","@name",species).getTagWhere("param","@name",name).setAttribute("b","0.0");
          newModel.getTag("species").getTagWhere("species","@name",species).getTagWhere("param","@name",name).setAttribute("a",val);          
        }
        newModel.write();
        StringTools.copyFile(targetPath+File.separator+"BoundaryConditions.vso",thisJobPath+"BoundaryConditions.vso");
        StringTools.copyFile(targetPath+File.separator+"InitialConditions.vso",thisJobPath+"InitialConditions.vso");
        BlackBox.compile(newModel,thisJobPath);
        PrintWriter PW = StringTools.OpenOutputFile(thisJobPath+File.separator+"compile.log");
        javacCode(thisJobPath,PW);
        PW.flush();
        PW.close();
        createJar(thisJobPath,idNo,theModel);
        RunPanel.getLock("jobs.xml.lock"); // Just block until the file is fully written.
        try {
          PrintWriter P = StringTools.OpenOutputFile(thisJobPath+File.separator+"compiled.txt");
          P.write(thisJobPath+"VEW"+idNo+".jar\n");
          P.flush();
          P.close();
          P = null;
        } catch (Exception e) { e.printStackTrace(PW); }
        RunPanel.releaseLock("jobs.xml.lock");
        int j=0;
        boolean done=false;
        if (job<jobCount-1) {
          while (!done) {
            batchCounters[j]++;
            if (batchCounters[j]<batches[j].length) done=true;
            else {
              batchCounters[j]=0;
              j++;
            }
          }
        }
        job++;
        idNo++;
        totalJob++;
      }
      
    }
    new File(targetPath+File.separator+"BoundaryConditions.vso").delete();
    new File(targetPath+File.separator+"InitialConditions.vso").delete();
    new File(targetPath+File.separator+"Track.vso").delete();
    new File(targetPath+File.separator+"progress.txt").delete();
    new File(targetPath+File.separator+"Model.xml").delete();
  }
  
  
  public static void javacCode(String thePath,PrintWriter PW) {
    final JavaFilter jf = new JavaFilter();
    final String sim[] = new File(thePath + "VEW"+File.separator+"Sim"+File.separator).list(jf);
    final String com[] = new File(thePath + "VEW"+File.separator+"Common"+File.separator).list(jf);
    final String xml[] = new File(thePath + "VEW"+File.separator+"Common"+File.separator+"XML"+File.separator).list(jf);
    final String rnd[] = new File(thePath + "VEW"+File.separator+"Common"+File.separator+"Random"+File.separator).list(jf);
    final String[] Fs2 = new String[sim.length + com.length + xml.length + rnd.length + 2];
    Fs2[0] = "-classpath";
    Fs2[1] = "." + File.pathSeparator + "trove.jar";
    int i=2;
    for (int j=0; j<sim.length; j++) Fs2[i++]=thePath+"VEW"+File.separator+"Sim"+File.separator+sim[j];
    for (int j=0; j<com.length; j++) Fs2[i++]=thePath+"VEW"+File.separator+"Common"+File.separator+com[j];
    for (int j=0; j<xml.length; j++) Fs2[i++]=thePath+"VEW"+File.separator+"Common"+File.separator+"XML"+File.separator+xml[j];
    for (int j=0; j<rnd.length; j++) Fs2[i++]=thePath+"VEW"+File.separator+"Common"+File.separator+"Random"+File.separator+rnd[j];
    final int status = Main.compile(Fs2,PW);
    PW.println(COMPILE_STATUS+status);
  }
  
  public static void createJar(String thePath,long pid, XMLTag theModel) {
    try {
      final String FS = File.separator;
      File F = new File("CompiledJobs" + File.separator + "VEW"+String.valueOf(pid)+".jar");
      if (F.exists()) F.delete();
      //Manifest Man = new Manifest(new FileInputStream("Data"+FS+"Kernel"+FS+"1.1"+FS+"MANIFEST.MF"));
      Manifest Man = new Manifest();
      Man.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION,"1.0");
      Man.getMainAttributes().put(Attributes.Name.SPECIFICATION_VENDOR,"VEW");
      Man.getMainAttributes().put(Attributes.Name.SEALED,"true");
      Man.getMainAttributes().put(Attributes.Name.MAIN_CLASS,"VEW.Sim.Launcher");
      JarOutputStream JOS = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(F)), Man);
      addFileToJar(JOS, thePath+FS+"InitialConditions.vso","InitialConditions.vso");
      addFileToJar(JOS, thePath+FS+"BoundaryConditions.vso","BoundaryConditions.vso");
      addFileToJar(JOS, thePath+FS+"trove.jar","trove.jar");
      addFileToJar(JOS, thePath+FS+"Model.xml","Model.xml");
      addFileToJar(JOS, thePath+FS+"compile.log","compile.log");
      if (theModel.getTag("jobs/restart").getAttribute("load").equals("true")) {
        String file = theModel.getTag("jobs/restart").getAttribute("file");
        String justFile = file;
        while (justFile.indexOf("/")>=0) justFile=justFile.substring(justFile.indexOf("/")+1);
        while (justFile.indexOf("\\")>=0) justFile=justFile.substring(justFile.indexOf("\\")+1);        
        addFileToJar(JOS,file,justFile);
      }

      String[] Fs = (new File(thePath, "VEW"+FS+"Sim")).list();
      for (int i = 0; i < Fs.length; i++) addFileToJar(JOS, thePath+FS+"VEW"+FS+"Sim"+FS+Fs[i],"VEW/Sim/"+Fs[i]);
      Fs = (new File(thePath, "VEW"+FS+"Common")).list();
      for (int i = 0; i < Fs.length; i++)
        if ((!Fs[i].equals("XML"))&&(!Fs[i].equals("Random"))) addFileToJar(JOS, thePath+FS+"VEW"+FS+"Common"+FS+Fs[i],"VEW/Common/"+Fs[i]);
      Fs = (new File(thePath, "VEW"+FS+"Common"+FS+"XML")).list();
      for (int i = 0; i < Fs.length; i++) addFileToJar(JOS, thePath+FS+"VEW"+FS+"Common"+FS+"XML"+FS+Fs[i],"VEW/Common/XML/" + Fs[i]);
      Fs = (new File(thePath, "VEW"+FS+"Common"+FS+"Random")).list();
      for (int i = 0; i < Fs.length; i++) addFileToJar(JOS, thePath+FS+"VEW"+FS+"Common"+FS+"Random"+FS+Fs[i],"VEW/Common/Random/" + Fs[i]);
      JOS.closeEntry();
      JOS.close();
      File f = new File("CompiledJobs"+File.separator+"VEW"+String.valueOf(pid)+".jar.msg");
      f.createNewFile();
      
      
    }
    catch(Exception y) {}
  }
  
  private static void addFileToJar(JarOutputStream JarStream, String InputPath, String EntryPath) throws IOException, FileNotFoundException {
    int read = -1;
    byte[] buf = new byte[4096];
    BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(InputPath));
    JarStream.putNextEntry(new JarEntry(EntryPath));
    read = BIS.read(buf);
    while(read != -1) { 
      JarStream.write(buf, 0, read);
      read = BIS.read(buf);
    }
    BIS.close();
  }
  
  private static class JavaFilter implements FilenameFilter {
    public boolean accept(File file, String str) {
      return str.toLowerCase().endsWith(".java");
    }
  }
}
