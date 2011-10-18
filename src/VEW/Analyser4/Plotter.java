package VEW.Analyser4;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.GZIPInputStream;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.ProgressMonitor;

import org.dom4j.Document;
import org.dom4j.Node;

import VEW.Common.StringTools;

public class Plotter {

  public static double[] physicalDepths = new double[521];
  public static final int WC_PLOT = 1;
  public static final int FD_PLOT = 2;
  public static final int FG_PLOT = 3;
  public static final int XY_PLOT = 4;
  public static final int CT_PLOT = 5;
  public static final int CO_BACK = 6;
  public static final int BA_PLOT = 7;
  public static final int DE_PROF = 8;
  public static final int CO_JPG_BACK = 9;
  public static final int CT_JPG_PLOT = 10;
  public static int plotFreq = 1;
  private ContourPlotter CP;
  public Analyser4 a4 = null;
  private static String version = "";
  public static final String V1_1 = "1.1";
  PlotThread thBack;
  PlotThread th1;
  PlotThread th2;
  PlotThread th3;
  PlotThread th4;
  PlotThread th5;
  
  protected static boolean cancelPlot = false;
  
  /* File handling helpers */

  public Plotter(Analyser4 _a4) {
    a4 = _a4;
    CP = new ContourPlotter();
  }
  
  public void setFreq(int i) { plotFreq = i; }
  
  static {
    float power = -2;
    physicalDepths[0] = 0;
    for (int i = 1; i < 21; i++) {
      physicalDepths[i] = Math.pow(10, power);
      power += 0.1;
    }
    for (int i = 21; i < 521; i++)
      physicalDepths[i] = i - 20;
  }

  public void ReallySkip(long Bytes, DataInputStream TheStream) throws Exception {
    long BytesToGo = Bytes;
    while (BytesToGo > Integer.MAX_VALUE)
      BytesToGo -= TheStream.skipBytes(Integer.MAX_VALUE);
    if (BytesToGo > 0) TheStream.skipBytes((int) BytesToGo);
  }

  public double readReal(DataInputStream dis, boolean useFloat) throws Exception {
    double d;
    if (useFloat) d = dis.readFloat();
    else d = dis.readDouble();
    return d;
  }

  public int readInt(DataInputStream dis) throws Exception {
    return dis.readInt();
 }

  public long readLong(DataInputStream dis) throws Exception {
    return dis.readLong();
 }

  
  /* Main plot top-level */

  public void plotOnlyBackground(String dataPath, GraphPanel gp, BackgroundChooser bc, long t1, long t2, double sl, Document format, JDialog parent, PrintWriter PW) {
    if (bc.useScale()) {
      if (!bc.useContours()) {
        thBack = new PlotThread(dataPath, gp, bc, t1, t2, format, PW, CO_BACK);
        thBack.start();
        bc.setData(true);
      } else {
        thBack = new PlotThread(dataPath, gp, bc, t1, t2, format, bc.getNoContours(), bc.getFillContours(), bc.getDrawContours(), CT_PLOT);
        thBack.start();
        bc.setData(true);        
      }
    }
    else if (bc.usePlain()) {
      gp.setBackgroundChooser(bc);
      gp.plotPlainBackground();
    }
  }
  
  public void exportOnlyBackground(String dataPath, GraphPanel gp, BackgroundChooser bc, long t1, long t2, double sl, Document format, JDialog parent, PrintWriter PW,
      int oldPW, int oldPH, LineChooser2 lc,String fn,double[][] oldScreen) {
    if (bc.useScale()) {
      if (!bc.useContours()) {
        thBack = new PlotThread(dataPath, gp, bc, t1, t2, format, PW, CO_JPG_BACK,
                                oldPW,oldPH,lc,fn,oldScreen);
        thBack.start();
        bc.setData(true);
      } else {
        thBack = new PlotThread(dataPath, gp, bc, t1, t2, format, bc.getNoContours(), 
            bc.getFillContours(), bc.getDrawContours(), CT_JPG_PLOT,
            oldPW,oldPH,lc,fn,oldScreen);
        thBack.start();
        bc.setData(true);        
      }
    }
    else if (bc.usePlain()) {
      gp.setBackgroundChooser(bc);
      gp.plotPlainBackground();
      gp.realExportJPG(lc,oldPW,oldPH,fn,oldScreen);
    }
  }

  
  
  public void plot(String dataPath, GraphPanel gp, BackgroundChooser bc, LineChooser2 lc, long t1, long t2, Document formatFile, JFrame parent, PrintWriter PW) {
    if (formatFile.valueOf("/dataformat/version").equals("")) version = "1.0"; 
    else version = formatFile.valueOf("/dataformat/version");
    cancelPlot = false;
    gp.clearAll();
    gp.setContours(bc.useContours());
    
    
    if (bc.useScale()) {
      if (!bc.useContours()) {
        thBack = new PlotThread(dataPath, gp, bc, t1, t2, formatFile, PW, CO_BACK);
        thBack.start();
        bc.setData(true);
      } else {
        thBack = new PlotThread(dataPath, gp, bc, t1, t2, formatFile, bc.getNoContours(), bc.getFillContours(), bc.getDrawContours(), CT_PLOT);
        thBack.start();
        bc.setData(true);        
      }
    }
    else if (bc.usePlain()) {
      gp.setBackgroundChooser(bc);
      gp.plotPlainBackground();
    }
    
    
    if (!cancelPlot) {
      th1 = new PlotThread(dataPath, gp, lc, t1, t2, formatFile, WC_PLOT);
      th1.start();
    }
    
    if (!cancelPlot) {
      th2 = new PlotThread(dataPath, gp, lc, t1, t2, formatFile, FD_PLOT);
      th2.start();
      
    }
    
    if (!cancelPlot) {
      th3 = new PlotThread(dataPath, gp, lc, t1, t2, formatFile, FG_PLOT);
      th3.start();
    }
    
    if (!cancelPlot) {
      th4 = new PlotThread(dataPath, gp, lc, t1, t2, formatFile, XY_PLOT);
      th4.start();
    }
    
    if (!cancelPlot) {
      th5 = new PlotThread(dataPath, gp, lc, formatFile, DE_PROF);
      th5.start();
    }
    
  }
  
  public void checkFinished(GraphPanel gp, LineChooser2 lc) {
    int countAliveThreads = 0;
    if ((th1!=null) && (!th1.isDone())) countAliveThreads++;
    if ((th2!=null) && (!th2.isDone())) countAliveThreads++;
    if ((th3!=null) && (!th3.isDone())) countAliveThreads++;
    if ((th4!=null) && (!th4.isDone())) countAliveThreads++;
    if ((th5!=null) && (!th5.isDone())) countAliveThreads++;
    if ((thBack!=null) && (!thBack.isDone())) countAliveThreads++;
    if (countAliveThreads==0) {
      a4.refreshAxes();
      gp.replotGraph(lc,a4.startOfPlot,a4.endOfPlot);
      gp.repaint();
      a4.plotButtons(true);
    }
  }
  
  public void plotSingleVar(String dataPath, GraphPanel gp, LineChooser2 lc, long t1, long t2, Document format) {

    int totalLines = lc.getLineCount();
    int totalLinesToPlot = 0;
    ArrayList filesToRead = new ArrayList();
    ArrayList lineDefs = new ArrayList();    
    for (int i=0; i<totalLines; i++) {
      LineDefinition ld = lc.getLineDef(i);
      if ((((ld.type==LineDefinition.TIME_SERIES) || (ld.type == LineDefinition.COMPOUND_PLOT)))
          && (ld.layer==LineDefinition.SINGLE)) {
        
        boolean found = false;
        int j=0;
        while ((!found) && (j<filesToRead.size())) {
          found = (filesToRead.get(j).toString().equals(ld.file));
          if (!found) j++;
        }
       
        if (!found) {
          filesToRead.add(new String(ld.file));
          j = filesToRead.size()-1;
          lineDefs.add(new ArrayList());
        }
        
        ArrayList lineDefsForThisFile = (ArrayList) lineDefs.get(j);
        lineDefsForThisFile.add(ld);
        totalLinesToPlot++;
      }
    }
    
    if (totalLinesToPlot > 0) {
      ProgressMonitor pm = new ProgressMonitor(a4,"Reading...0%","",0,1000);
      pm.setProgress(0);
      
      for (int fileNo=0; fileNo<filesToRead.size(); fileNo++) {
        ArrayList defsForThisFile = (ArrayList) lineDefs.get(fileNo);
        LineDefinition firstLine = (LineDefinition) defsForThisFile.get(0);
        Node dataFormat = format.selectSingleNode("/dataformat/environment[data='"+ firstLine.file + "']");
        int numberOfVarsInFile = dataFormat.selectNodes("var").size();
        long dataStart=0;
        if (dataFormat.valueOf("output/after")==null) {
          dataStart = Long.parseLong(a4.modelFile.valueOf("track/start"));
        } else dataStart = Long.parseLong(dataFormat.valueOf("output/after"));
        int dataFreq = Integer.parseInt(dataFormat.valueOf("output/freq"));
        if (plotFreq< dataFreq) plotFreq = dataFreq;
        final long intervalMillis = a4.timeStepMillis*plotFreq;
        boolean useFloat = (firstLine.useFloat);
        int numSize = useFloat?4:8;
        long skipBetweenTimesteps = ((plotFreq / dataFreq) - 1) * numSize * numberOfVarsInFile;
        
        if (!version.equals(V1_1)) dataStart = a4.startSimMillis+(a4.timeStepMillis*dataStart);
        final long startTime = Math.max(t1,dataStart);
        final int dataLength = (int) ((t2-t1)/intervalMillis);
        long skipStep = numSize * numberOfVarsInFile;
        double[] varSet = new double[numberOfVarsInFile];
        for (int i=0; i<defsForThisFile.size(); i++) {
          LineDefinition d = (LineDefinition) defsForThisFile.get(i);
          d.dataY=new double[dataLength];
          for (int j=0; j<d.dataY.length; j++) d.dataY[j]=Double.NEGATIVE_INFINITY;
        }
          
        DataInputStream theFile = null;  
        try {
          if (firstLine.zip) 
            theFile = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(dataPath + File.separator + firstLine.file))));
          else
            theFile = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath + File.separator + firstLine.file)));
        
          pm.setNote("Skipping unwanted data in "+dataPath + File.separator + firstLine.file);

          for (long i=dataStart; i<t1; i+=a4.timeStepMillis) {
            ReallySkip(skipStep, theFile);
            if (pm.isCanceled()) {
              pm.close();
              cancelPlot=true;
              return;
            }
            final int prog1000 = 1000*(int)((i-dataStart)/((1.0f*t1)-dataStart));
            pm.setProgress(prog1000);
            pm.setNote("Skipping..."+String.valueOf((prog1000/10.0))+" %");
          }
          pm.close();
          pm = new ProgressMonitor(a4,"Reading Data...","",0,1000);
          pm.setProgress(0);
          pm.setNote("Reading data to plot...");
          
          for (long i=startTime; i<t2; i+=intervalMillis) {
            if (pm.isCanceled()) {
              pm.close();
              cancelPlot=true;
              return;
            }
            for (int j = 0; j < numberOfVarsInFile; j++)
              varSet[j] = readReal(theFile, useFloat);
            for (int j = 0; j <defsForThisFile.size(); j++) {
              LineDefinition thisDef = (LineDefinition) defsForThisFile.get(j);
              double theVal = 0;
              if (thisDef.compoundVars1.length>0) theVal = varSet[thisDef.compoundVars1[0]];
              for (int k=1; k<thisDef.compoundVars1.length; k++) {
                if (thisDef.operator==LineDefinition.OP_ADD) 
                  theVal += varSet[thisDef.compoundVars1[k]];
                else if (thisDef.operator==LineDefinition.OP_MUL)
                  theVal *= varSet[thisDef.compoundVars1[k]];
              }
              if (((i-t1)/intervalMillis)<thisDef.dataY.length) thisDef.dataY[(int)((i-t1)/intervalMillis)]=theVal;
            }
            ReallySkip(skipBetweenTimesteps, theFile);
            final int prog1000 = (int)(1000*((i-t1)/(1.0f*(t2-t1))));
            pm.setProgress(prog1000);
            pm.setNote("Reading "+String.valueOf(prog1000/10.0)+" %");
          }
          theFile.close();
        } catch (Exception e) {
          if (!(e instanceof EOFException))
            e.printStackTrace();
        }
      }
      gp.plotLines(null,lc);
      pm.close();
    }
    a4.refreshAxes();
    gp.replotGraph(lc,a4.startOfPlot,a4.endOfPlot);
    gp.repaint();

  }

  public void plotXY(String dataPath, GraphPanel gp, LineChooser2 lc, long t1, long t2, Document format) {
    int totalLines = lc.getLineCount();
    int totalToPlot = 0;
    ArrayList filesToRead = new ArrayList();
    ArrayList lineDefs = new ArrayList();    
    for (int i=0; i<totalLines; i++) {
      LineDefinition ld = lc.getLineDef(i);
      if (ld.type==LineDefinition.XY_PLOT) {
        boolean found = false;
        int j=0;
        while ((!found) && (j<filesToRead.size())) {
          found = (filesToRead.get(j).toString().equals(ld.file));
          if (!found) j++;
        }
        if (!found) {
          filesToRead.add(new String(ld.file));
          j = filesToRead.size()-1;
          lineDefs.add(new ArrayList());
        }
        ArrayList lineDefsForThisFile = (ArrayList) lineDefs.get(j);
        lineDefsForThisFile.add(ld);
        totalToPlot++;
      }
    }
    if (totalToPlot > 0) {
      int totalPoints = (int) ((t2-t1) / (a4.timeStepHours*3600*1000));
      for (int fileNo=0; fileNo<filesToRead.size(); fileNo++) {
        ArrayList defsForThisFile = (ArrayList) lineDefs.get(fileNo);
        LineDefinition firstLine = (LineDefinition) defsForThisFile.get(0);
        Node dataFormat = null;
        if (firstLine.layer==LineDefinition.TIME_SERIES) {
          dataFormat = format.selectSingleNode("/dataformat/environment[data='"+ firstLine.file + "']");
          plotXYTimeSeries(dataPath,gp,lc,t1,t2,dataFormat, defsForThisFile, totalPoints, firstLine);
        } if (firstLine.layer==LineDefinition.FUNCTIONALGROUP) {
         
          plotXYFG(dataPath,gp,lc,t1,t2,format,defsForThisFile,totalPoints);
        }
      }
    }
    gp.plotLines(null,lc);
    a4.refreshAxes();
    gp.replotGraph(lc,a4.startOfPlot,a4.endOfPlot);
    gp.repaint();
  }

  
  public void plotXYTimeSeries(String dataPath, GraphPanel gp, LineChooser2 lc, long t1, long t2, 
      Node dataFormat, ArrayList defsForThisFile, int totalPoints, LineDefinition firstLine) {
  
    ProgressMonitor pm = new ProgressMonitor(a4,"Reading XY data","",0,1000);
    int numberOfVarsInFile = dataFormat.selectNodes("var").size();
    long dataStart = Long.parseLong(dataFormat.valueOf("output/after"));
    int dataFreq = Integer.parseInt(dataFormat.valueOf("output/freq"));
    if (!version.equals(V1_1)) dataStart = a4.startSimMillis+(a4.timeStepMillis*dataStart);
    boolean useFloat = (firstLine.useFloat);
    int numSize = useFloat?4:8;
   
    for (int i=0; i<defsForThisFile.size(); i++) {
      LineDefinition d = (LineDefinition) defsForThisFile.get(i);
      d.dataX=new double[totalPoints];
      d.dataY=new double[totalPoints];
      for (int j=0; j<totalPoints; j++) {
        d.dataX[j]=Double.NEGATIVE_INFINITY;
        d.dataY[j]=Double.NEGATIVE_INFINITY;
      }
    }
    long skipStep = numSize * numberOfVarsInFile;
    if (plotFreq< dataFreq) plotFreq = dataFreq;
    long skipBetweenTimesteps = ((plotFreq / dataFreq) - 1) * numSize * numberOfVarsInFile;
    long intervalMillis = a4.timeStepMillis*plotFreq;
        
    double[] varSet = new double[numberOfVarsInFile];
    DataInputStream theFile = null;  
    try {
      if (firstLine.zip) 
        theFile = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(dataPath + File.separator + firstLine.file))));
      else
        theFile = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath + File.separator + firstLine.file)));
      
      pm.setProgress(0);
      for (long i=dataStart; i<t1; i+=a4.timeStepMillis) {
        ReallySkip(skipStep, theFile);
        if (pm.isCanceled()) {
          pm.close();
          cancelPlot=true;
          return;
        }
        int prog1000=(int)(1000*((i-dataStart)/(t1-dataStart)));
        pm.setProgress(prog1000);
        pm.setNote("Skipping..."+(prog1000/10.0)+" %");
      }
      pm.close();
      pm = new ProgressMonitor(a4, "Reading XY data","",0,1000);
      int pointno=0;
      final long firstStep = Math.max(t1,dataStart);
      for (long i = firstStep; i < t2; i += intervalMillis) {
        if (pm.isCanceled()) {
          pm.close();
          cancelPlot=true;
          return;
        }
        int prog1000 = (int)(1000*((i-firstStep)/((1.0f*t2)-firstStep)));
        pm.setProgress(prog1000);
        pm.setNote("Reading... "+(prog1000/10.0)+" %");
        for (int j = 0; j < numberOfVarsInFile; j++)
          varSet[j] = readReal(theFile, useFloat);
        for (int j = 0; j <defsForThisFile.size(); j++) {
          LineDefinition thisDef = (LineDefinition) defsForThisFile.get(j);
          double theVal1 = 0;
          double theVal2 = 0;
          theVal1 = varSet[thisDef.compoundVars1[0]];
          for (int k=1; k<thisDef.compoundVars1.length; k++) {
            if (thisDef.operator==LineDefinition.OP_ADD) 
              theVal1 += varSet[thisDef.compoundVars1[k]];
            else if (thisDef.operator==LineDefinition.OP_MUL)
              theVal1 *= varSet[thisDef.compoundVars1[k]];
          }
          theVal2 = varSet[thisDef.compoundVars2[0]];
          for (int k=1; k<thisDef.compoundVars2.length; k++) {
            if (thisDef.operator==LineDefinition.OP_ADD) 
              theVal2 += varSet[thisDef.compoundVars2[k]];
            else if (thisDef.operator==LineDefinition.OP_MUL)
              theVal2 *= varSet[thisDef.compoundVars2[k]];
          }
                
          thisDef.dataX[pointno] = theVal1;
          thisDef.dataY[pointno] = theVal2;              
        }
        ReallySkip(skipBetweenTimesteps, theFile);
        pointno++;
      }
      theFile.close();
    } catch (Exception e) {
      if  (!(e instanceof EOFException)) e.printStackTrace();
    }
    pm.close();
    a4.refreshAxes();
    gp.replotGraph(lc,a4.startOfPlot,a4.endOfPlot);
    gp.repaint();
  }

  public void skipFGUntil(DataInputStream theFile, File TimeFile, long t1, long t2, boolean useFloats, String timeFileName, int bytesPerEntry, byte[] types) {
    ProgressMonitor pm = new ProgressMonitor(a4,"Skipping...","",0,1000);
    try {
      long time = 0;
      long remembertime = 0;
      int numSize=8;
      if (useFloats) numSize=4;
      File timeFile = new File(timeFileName);
      if ((!timeFile.exists()) || (!timeFile.isFile())) TimeFile=null; 
      if ((TimeFile==null) || (timeFileName.equals(""))) {
          timeLoop: while (time < t1 - 1) {
          if (pm.isCanceled()) {
            pm.close();
            cancelPlot=true;
            break timeLoop;
          }
          if (types[0]==0) time = (long) (2*readReal(theFile, useFloats));
          else time = readLong(theFile);
          if (!version.equals(V1_1)) time = (a4.startSimMillis+(time*a4.timeStepMillis));          
          if (time>remembertime) {
            remembertime=time;
            int prog1000 = (int)(1000*(time/(t1*1.0f)));
            pm.setProgress(prog1000);
            pm.setNote("Skipping..."+(prog1000/10.0)+" %");
          }
          if (types[0]==0) ReallySkip(bytesPerEntry - numSize, theFile);
          else ReallySkip(bytesPerEntry - 8, theFile);
        }
      } else {
        DataInputStream theTime = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(timeFileName))));
        pm.setNote("Looking up quick-index...");
        final long timeStepNumber = ((t1-a4.startSimMillis)/a4.timeStepMillis);
        ReallySkip((timeStepNumber-1)*8,theTime);
        long timeToReach = theTime.readLong();
        final float total = timeToReach;
        long skipped = 0;
        while (timeToReach>0) {
          if (pm.isCanceled()) {
            pm.close();
            cancelPlot=true;
            return;
          }          
          long skipAmount = 10485760;
          if (skipAmount>timeToReach) skipAmount = timeToReach;
          skipped+=skipAmount;
          timeToReach-=skipAmount;
          ReallySkip(skipAmount,theFile);
          int prog1000 = (int)(1000*(skipped/(1.0f*total)));
          pm.setProgress(prog1000);
          pm.setNote("Skipping..."+(prog1000/10.0)+" %");
        }
      }
    } catch (Exception e) { e.printStackTrace(); }
    pm.close();
  }
  
  
  public void plotXYFG(String dataPath, GraphPanel gp, LineChooser2 lc, long t1, long t2, Document dataFormat, 
      ArrayList defsForThisFile, int totalPoints) {
    boolean oldVersion = false;
    int totalLines = lc.getLineCount();
    int totalToPlot = 0;
    ArrayList filesToRead = new ArrayList();
    ArrayList lineDefs = new ArrayList(); 
    long timestepMillis = (long) (a4.timeStepHours*3600*1000);
    long intervalMillis = timestepMillis*plotFreq;

    for (int i=0; i<totalLines; i++) {
      LineDefinition ld = lc.getLineDef(i);
      if ((ld.type==LineDefinition.XY_PLOT) && (ld.layer == LineDefinition.FUNCTIONALGROUP)) {
        
        boolean found = false;
        int j=0;
        while ((!found) && (j<filesToRead.size())) {
          found = (filesToRead.get(j).toString().equals(ld.file));
          if (!found) j++;
        }
       
        if (!found) {
          filesToRead.add(new String(ld.file));
          j = filesToRead.size()-1;
          lineDefs.add(new ArrayList());
        }
        
        ArrayList lineDefsForThisFile = (ArrayList) lineDefs.get(j);
        lineDefsForThisFile.add(ld);
        totalToPlot++;
      }
    }
    if (totalToPlot>0) {
      mainLoop: for (int fileNo=0; fileNo<filesToRead.size(); fileNo++) {
        ArrayList defsForFile = (ArrayList) lineDefs.get(fileNo);
        LineDefinition firstLine = (LineDefinition) defsForFile.get(0);
        for (int i=0; i<defsForFile.size(); i++) {
          LineDefinition d = (LineDefinition) defsForFile.get(i);
          d.dataY = new double[1+(int)((t2-t1)/intervalMillis)];
          d.dataX = new double[1+(int)((t2-t1)/intervalMillis)];
          for (int j=0; j<d.dataY.length; j++) {
            d.dataY[j]=Double.NEGATIVE_INFINITY;
            d.dataX[j]=Double.NEGATIVE_INFINITY;
          }
        }
        boolean useFloat = (firstLine.useFloat);
        int numSize = useFloat?4:8;
       
        DataInputStream theFile = null;
        double[] valsForThisTimeStepX = new double[totalToPlot];
        double[] valsForThisTimeStepY = new double[totalToPlot];        
        Node format = dataFormat.selectSingleNode("/dataformat/functionalgroup[data='"+ firstLine.file + "']");
        long dataStart = Long.parseLong(dataFormat.valueOf("output/after"));
        
        if (!version.equals(V1_1)) dataStart = a4.startSimMillis+(timestepMillis*dataStart);
        String timeFileName = dataPath + File.separator + format.valueOf("time");
        String indexFile = dataPath + File.separator + format.valueOf("index");
        File IndexFile = new File(indexFile);
        if (!IndexFile.exists()) indexFile = null;
        File TimeFile = new File(timeFileName);
        if (!TimeFile.exists()) TimeFile = null;
        try {
          if (firstLine.zip)
            theFile = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(dataPath+File.separator+firstLine.file))));
          else
            theFile = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath+File.separator+firstLine.file)));
          List varTags = format.selectNodes("var");
          int varsPerEntry = varTags.size();
          
          int bytesPerEntry = 0;
          byte[] types = new byte[varsPerEntry];
          for (ListIterator i = varTags.listIterator(); i.hasNext();) {
            Node var = (Node) i.next();
            String type = var.valueOf("type");
            if (type.length() == 0) {
              types[i.previousIndex()]=0;
              oldVersion=true;
            } else if (type.equals("real")) {
              bytesPerEntry+=numSize;
              types[i.previousIndex()]=0;
            }  else if (type.equals("int")) {
              bytesPerEntry+=4;
              types[i.previousIndex()]=1;
            } else if (type.equals("long")) {
              bytesPerEntry+=8;
              types[i.previousIndex()]=2;
            }
          }
          double[] doubleBuffer = new double[varsPerEntry];
          int[] intBuffer = new int[varsPerEntry];
          long[] longBuffer = new long[varsPerEntry];
          
          
          // SKIP!!!!
          skipFGUntil(theFile,TimeFile,t1,t2, useFloat, timeFileName, bytesPerEntry, types);
          long time = t1;
          long remembertime = time;
          ProgressMonitor pm = new ProgressMonitor(a4,"Reading FG data","",0,1000);
          pm.setNote("Reading...");
          while (time < t2) { // for all useful timesteps
            if (pm.isCanceled()) {
              pm.close();
              cancelPlot=true;
              break mainLoop;
            }
            if (time>remembertime) {
              remembertime=time;
              int prog1000 = (int) (1000*((time-t1)/((1.0f*t2)-t1)));
              pm.setProgress(prog1000);
              pm.setNote("Reading..."+(prog1000/10.0)+" %");
            }
            for (int i = 0; i < varsPerEntry; i++) {
              if (types[i]==0) doubleBuffer[i] = readReal(theFile, useFloat);
              else if (types[i]==1) intBuffer[i] = readInt(theFile);
              else if (types[i]==2) longBuffer[i] = readLong(theFile);
            }
            if (oldVersion) {
              longBuffer[0] = (long) (doubleBuffer[0] * 2); // Timestep
              longBuffer[1] = (long) (doubleBuffer[1]); // Id
              intBuffer[2] = (int) (doubleBuffer[2]); // stage
            } else {
              for (int i=2; i<varsPerEntry; i++) {
                if (types[i]==1) doubleBuffer[i]=intBuffer[i];
                else if (types[i]==2) doubleBuffer[i]=longBuffer[i];
              }
            }
            if (!version.equals(V1_1)) longBuffer[0] = a4.startSimMillis+(longBuffer[0]*a4.timeStepMillis);
            
            
            for (int j = 0; j <defsForThisFile.size(); j++) {
              LineDefinition thisDef = (LineDefinition) defsForThisFile.get(j);
              if (longBuffer[1]==thisDef.thisId) {
                double theVal1 = 0;
                double theVal2 = 0;
                theVal1 = doubleBuffer[thisDef.compoundVars1[0]+2];
                for (int k=1; k<thisDef.compoundVars1.length; k++) {
                  if (thisDef.operator==LineDefinition.OP_ADD) 
                    theVal1 += doubleBuffer[thisDef.compoundVars1[k]+2];
                  else if (thisDef.operator==LineDefinition.OP_MUL)
                    theVal1 *= doubleBuffer[thisDef.compoundVars1[k]+2];
                }
                theVal2 = doubleBuffer[thisDef.compoundVars2[0]+2];
                for (int k=1; k<thisDef.compoundVars2.length; k++) {
                  if (thisDef.operator==LineDefinition.OP_ADD) 
                    theVal2 += doubleBuffer[thisDef.compoundVars2[k]+2];
                  else if (thisDef.operator==LineDefinition.OP_MUL)
                    theVal2 *= doubleBuffer[thisDef.compoundVars2[k]+2];
                }
                valsForThisTimeStepX[j] = theVal1;
                valsForThisTimeStepY[j] = theVal2;
              }
            }
            if (longBuffer[0] > time) {
              time = longBuffer[0];
              if (!version.equals(V1_1)) time = (long) (a4.startSimMillis+(time*a4.timeStepHours*3600*1000));
              if (time>t2) time=t2;
              int dataNo = (int) ((time-t1)/intervalMillis);
              for (int lineNo=0; lineNo<defsForFile.size(); lineNo++) {
                ((LineDefinition) defsForFile.get(lineNo)).dataY[dataNo] = valsForThisTimeStepY[lineNo];
                ((LineDefinition) defsForFile.get(lineNo)).dataX[dataNo] = valsForThisTimeStepX[lineNo];
              }
            }
          } 
          theFile.close();
          pm.close();
        } catch (Exception e) { if (!(e instanceof EOFException)) e.printStackTrace(); }
      }
      a4.refreshAxes();
      gp.replotGraph(lc,a4.startOfPlot,a4.endOfPlot);
      gp.repaint();
    }
  }    
  

  public void plotDepthProfile(String dataPath, GraphPanel gp, LineChooser2 lc, Document dataFormat) {
    ProgressMonitor pm= null;
    int totalLines = lc.getLineCount();
    int totalToPlot = 0;
    long[] timeTargets;
    ArrayList filesToRead = new ArrayList();
    ArrayList lineDefs = new ArrayList();    

    for (int i=0; i<totalLines; i++) {
      LineDefinition ld = lc.getLineDef(i);
      if (ld.type==LineDefinition.DEPTH_PROFILE) {
        
        boolean found = false;
        int j=0;
        while ((!found) && (j<filesToRead.size())) {
          found = (filesToRead.get(j).toString().equals(ld.file));
          if (!found) j++;
        }
       
        if (!found) {
          filesToRead.add(new String(ld.file));
          j = filesToRead.size()-1;
          lineDefs.add(new ArrayList());
        }
        
        ArrayList lineDefsForThisFile = (ArrayList) lineDefs.get(j);
        lineDefsForThisFile.add(ld);
        totalToPlot++;
      }
    }
    
    if (totalToPlot>0) {
      mainLoop: for (int fileNo=0; fileNo<filesToRead.size(); fileNo++) {
        ArrayList defsForThisFile = (ArrayList) lineDefs.get(fileNo);
        LineDefinition firstLine = (LineDefinition) defsForThisFile.get(0);
        Node format = dataFormat.selectSingleNode("/dataformat/field[data='"+ firstLine.file + "']");
        boolean useFloat = (firstLine.useFloat);
        int numSize = useFloat?4:8;
        long dataStart = 0;
        if (format.valueOf("dimensions/dim[1]/@start").equals("null")) {
          dataStart = Long.parseLong(a4.modelFile.valueOf("track/start"));
        } else dataStart= Long.parseLong(format.valueOf("dimensions/dim[1]/@start"));
        
        if (!version.equals(V1_1)) dataStart = a4.startSimMillis+(a4.timeStepMillis*dataStart);
        int dataFreq = Integer.parseInt(format.valueOf("dimensions/dim[1]/@step"));
        if (plotFreq<dataFreq) plotFreq = dataFreq;
            
        int dataTopDepth = 0;
        int dataBottomDepth = 0;
        if (!version.equals(V1_1)) {
          dataTopDepth = Integer.parseInt(format.valueOf("dimensions/dim[2]/@start"));
          dataBottomDepth = Integer.parseInt(format.valueOf("dimensions/dim[2]/@end"));
        } else {
          final double dtop = Double.parseDouble(format.valueOf("dimensions/dim[2]/@start"));
          final double dbottom = Double.parseDouble(format.valueOf("dimensions/dim[2]/@end"));          
          dataTopDepth = (int) Math.floor(dtop);
          dataBottomDepth = (int) Math.floor(dbottom);          
          if (format.valueOf("name").equals("Physical Environment")) {
            while (physicalDepths[dataTopDepth]<dtop) dataTopDepth++;
            while (physicalDepths[dataBottomDepth]<dbottom) dataBottomDepth++;
          }
        }
        
        int layersInData = 1+(dataBottomDepth - dataTopDepth);
        int varsInData = format.selectNodes("var").size();
        long skipPerTimeStep = layersInData * varsInData * numSize;
        
        timeTargets = new long[defsForThisFile.size()];
        long lastTime = -1;
        long firstTime = -1;
        int firstIndex = -1;
        
        for (int i=0; i<timeTargets.length; i++) {
          GregorianCalendar gc = ((LineDefinition) defsForThisFile.get(i)).timestep;
          long timeMillis = gc.getTimeInMillis();
          timeTargets[i] = timeMillis;
          if (i==0) {
            lastTime=timeTargets[0];
            firstTime=timeTargets[0];
            firstIndex=0;
          } 
          else {
            if (timeTargets[i]>lastTime) lastTime=timeTargets[i];
            if (timeTargets[i]<firstTime) {
              firstTime=timeTargets[i];
              firstIndex=i;

            }
          }
        }

        String fileName = dataPath + File.separator + firstLine.file;
        pm = new ProgressMonitor(a4,"Reading depth profile","",0,1000);
        pm.setProgress(0);
          
        DataInputStream theFile = null;
        try {
          if (firstLine.zip) 
            theFile = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(fileName))));
          else
            theFile = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
          long i=dataStart;
          while (i<=lastTime) {
            if (firstTime>i) {
              ReallySkip(skipPerTimeStep, theFile);
              i+=a4.timeStepMillis;
              int prog = (int)(1000.0*((i-dataStart)/((1.0f*lastTime)-dataStart)));
              pm.setProgress(prog);
              pm.setNote("Skipping data: "+(prog/10.0)+" %");
              if (pm.isCanceled()) {
                pm.close();
                cancelPlot=true;
                break mainLoop;
              }
            } else {
              pm.close();
              double[][] data = new double[varsInData][layersInData];
              if (!version.equals(V1_1)) {
                for (int j=0; j<varsInData; j++)
                  for (int k=0; k<layersInData; k++) 
                    data[j][k]=readReal(theFile,useFloat);
              } else {
                for (int k=0; k<layersInData; k++) 
                  for (int j=0; j<varsInData; j++)
                    data[j][k]=readReal(theFile,useFloat);
              }
              
              while (firstTime==i) {
                // Deal with the data
                LineDefinition ld = (LineDefinition) defsForThisFile.get(firstIndex);
                ld.dataY = new double[layersInData];
                for (int j=0; j<layersInData; j++) {
                  double value=data[ld.compoundVars1[0]][j];
                  for (int k=1; k<ld.compoundVars1.length; k++) {
                    if (ld.operator==LineDefinition.OP_ADD) value += data[ld.compoundVars1[k]][j];
                    else if (ld.operator==LineDefinition.OP_MUL) value *= data[ld.compoundVars1[k]][j];
                  }
                  ld.dataY[j+dataTopDepth]=value;
                }
                
                timeTargets[firstIndex]=-1;
                firstIndex=-1;
                firstTime=-1;
                
                for (int j=0; j<timeTargets.length; j++) {
                  if ((firstTime==-1) || (timeTargets[j]<firstTime)) {
                    firstTime = timeTargets[j];
                    firstIndex=j;
                  }
                }
                if (firstTime==-1) i=lastTime+1;
              }
            }
          }
          theFile.close();
        } catch (Exception e) { if (!(e instanceof EOFException)) e.printStackTrace(); }
      }
    }
    if (pm!=null) pm.close();
    gp.plotLines(null,lc);
    a4.refreshAxes();      
    gp.replotGraph(lc,a4.startOfPlot,a4.endOfPlot);
    gp.repaint();
  }


  public void plotFieldAtDepth(String dataPath, GraphPanel gp, LineChooser2 lc, long t1, long t2, Document dataFormat) {
    ProgressMonitor pm = null;
    int totalLines = lc.getLineCount();
    int totalToPlot = 0;
    ArrayList filesToRead = new ArrayList();
    ArrayList lineDefs = new ArrayList();    

    for (int i=0; i<totalLines; i++) {
      LineDefinition ld = lc.getLineDef(i);
      if (((ld.type==LineDefinition.TIME_SERIES) || (ld.type==LineDefinition.COMPOUND_PLOT)) && 
          ((ld.layer == LineDefinition.BIOLOGICAL) || (ld.layer == LineDefinition.PHYSICAL))) {
        
        boolean found = false;
        int j=0;
        while ((!found) && (j<filesToRead.size())) {
          found = (filesToRead.get(j).toString().equals(ld.file));
          if (!found) j++;
        }
       
        if (!found) {
          filesToRead.add(new String(ld.file));
          j = filesToRead.size()-1;
          lineDefs.add(new ArrayList());
        }
        
        ArrayList lineDefsForThisFile = (ArrayList) lineDefs.get(j);
        lineDefsForThisFile.add(ld);
        totalToPlot++;
      }
    }
    
    if (totalToPlot>0) {
      mainLoop: for (int fileNo=0; fileNo<filesToRead.size(); fileNo++) {
        int x = 0;
        ArrayList defsForThisFile = (ArrayList) lineDefs.get(fileNo);
        LineDefinition firstLine = (LineDefinition) defsForThisFile.get(0);
        boolean useFloat = (firstLine.useFloat);
        int numSize = useFloat?4:8;
        Node format = dataFormat.selectSingleNode("/dataformat/field[data='"+ firstLine.file + "']");
        int dataFreq = Integer.parseInt(format.valueOf("dimensions/dim[1]/@step"));
        if (plotFreq<dataFreq) plotFreq = dataFreq;
        final int dataSize = (int) ((t2-t1)/(plotFreq*a4.timeStepMillis));
        for (int i=0; i<defsForThisFile.size(); i++) {
          LineDefinition d = (LineDefinition) defsForThisFile.get(i);
          d.dataY = new double[dataSize];
          for (int j=0; j<d.dataY.length; j++)
            d.dataY[j]=Double.NEGATIVE_INFINITY;
        }
            
        String fileName = dataPath + File.separator + firstLine.file;
        pm = new ProgressMonitor(a4,"Reading field data","",0,1000);
        pm.setProgress(0);
        long dataStart = 0;
        if (format.valueOf("dimensions/dim[1]/@start").equals("null")) {
          dataStart = Long.parseLong(a4.modelFile.valueOf("track/start"));
        } else dataStart = Long.parseLong(format.valueOf("dimensions/dim[1]/@start"));
        if (!version.equals(V1_1)) dataStart = a4.startSimMillis+(a4.timeStepMillis*dataStart);
        int dataTopDepth = 0;
        int dataBottomDepth = 0;
        if (!version.equals(V1_1)) {
          dataTopDepth = Integer.parseInt(format.valueOf("dimensions/dim[2]/@start"));
          dataBottomDepth = Integer.parseInt(format.valueOf("dimensions/dim[2]/@end"));
        } else {
          final double dtop = Double.parseDouble(format.valueOf("dimensions/dim[2]/@start"));
          final double dbottom = Double.parseDouble(format.valueOf("dimensions/dim[2]/@end"));          
          dataTopDepth = (int) Math.floor(dtop);
          dataBottomDepth = (int) Math.floor(dbottom);          
          if (format.valueOf("name").equals("Physical Environment")) {
            while (physicalDepths[dataTopDepth]<dtop) dataTopDepth++;
            while (physicalDepths[dataBottomDepth]<dbottom) dataBottomDepth++;
          }
        }
        int layersInData = 1+(dataBottomDepth - dataTopDepth);
        int varsInData = format.selectNodes("var").size();
        long skipPerTimeStep = layersInData * varsInData * numSize;
        long skipBetweenTimeSteps = ((plotFreq / dataFreq) - 1) * layersInData * varsInData * numSize;
        double[][] dataForTimestep = new double[varsInData][layersInData];
  
        DataInputStream theFile = null;
        try {
          if (firstLine.zip) 
            theFile = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(fileName))));
          else
            theFile = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            
          for (long i=dataStart; i<t1; i+=a4.timeStepMillis) {
            ReallySkip(skipPerTimeStep, theFile);
            if (pm.isCanceled()) {
              pm.close();
              cancelPlot=true;
              break mainLoop;
            }
            pm.setProgress((int) (1000*((i-dataStart)/((1.0f*t1)-dataStart))));
          }
          pm.close();
          pm = new ProgressMonitor(a4,"Reading Field Data","",0,1000);
          final long startData = Math.max(t1,dataStart);
          for (long i = startData; i < t2; i += a4.timeStepMillis) {
            if (pm.isCanceled()) {
              pm.close();
              cancelPlot=true;
              break mainLoop;
            }
            pm.setProgress((int)(1000*((i-startData)/((1.0f*t2)-startData))));
            if (version.equals(V1_1)) {
              for (int k=0; k<layersInData; k++)
                for (int j=0; j<varsInData; j++) 
                  dataForTimestep[j][k]=readReal(theFile,useFloat);

            } else {
              for (int j=0; j<varsInData; j++) 
                for (int k=0; k<layersInData; k++)
                  dataForTimestep[j][k]=readReal(theFile,useFloat);
            }
            
            for (int j=0; j<defsForThisFile.size(); j++) {
              LineDefinition ld = (LineDefinition) defsForThisFile.get(j);
              double d_total = 0;
              if (ld.CPdepthOp==LineDefinition.OP_MUL) d_total=1;
              for (int k=0; k<ld.depthIndices.length; k++) {
                int layer = ld.depthIndices[k];
                double d_layer = 0;
                if (ld.operator==LineDefinition.OP_MUL) d_layer = 1;
                for (int m=0; m<ld.compoundVars1.length; m++) {
                  if (ld.operator == LineDefinition.OP_MUL) d_layer *= dataForTimestep[ld.compoundVars1[m]][layer];
                  else d_layer += dataForTimestep[ld.compoundVars1[m]][layer];
                }
                if (ld.CPdepthOp==LineDefinition.OP_MUL) d_total *= d_layer;
                else d_total += d_layer;
              }
              ld.dataY[x] = d_total;
            }
            if (skipBetweenTimeSteps > 0) ReallySkip(skipBetweenTimeSteps, theFile);
            x++;
          } 
          theFile.close();
        } catch (Exception e) { if (!(e instanceof EOFException)) e.printStackTrace(); }
      }
      if (pm!=null) pm.close();    
      gp.plotLines(null,lc);
      a4.refreshAxes();      
      gp.replotGraph(lc,a4.startOfPlot,a4.endOfPlot);
      gp.repaint();
    }
  }
  
  public void plotFGLines(String dataPath, GraphPanel gp, LineChooser2 lc, long t1, long t2, Document dataFormat) {
    ProgressMonitor pm = null;
    boolean oldVersion = false;    
    int totalLines = lc.getLineCount();
    int totalToPlot = 0;
    ArrayList filesToRead = new ArrayList();
    ArrayList lineDefs = new ArrayList();    

    for (int i=0; i<totalLines; i++) {
      LineDefinition ld = lc.getLineDef(i);
      if (((ld.type==LineDefinition.TIME_SERIES) && (ld.layer == LineDefinition.FUNCTIONALGROUP)) ||
          ((ld.type==LineDefinition.COMPOUND_PLOT) && (ld.layer == LineDefinition.FUNCTIONALGROUP))) {
        
        boolean found = false;
        int j=0;
        while ((!found) && (j<filesToRead.size())) {
          found = (filesToRead.get(j).toString().equals(ld.file));
          if (!found) j++;
        }
       
        if (!found) {
          filesToRead.add(new String(ld.file));
          j = filesToRead.size()-1;
          lineDefs.add(new ArrayList());
        }
        
        ArrayList lineDefsForThisFile = (ArrayList) lineDefs.get(j);
        lineDefsForThisFile.add(ld);
        totalToPlot++;
      }
    }
    if (totalToPlot>0) {
      mainLoop: for (int fileNo=0; fileNo<filesToRead.size(); fileNo++) {
        ArrayList defsForFile = (ArrayList) lineDefs.get(fileNo);
        LineDefinition firstLine = (LineDefinition) defsForFile.get(0);
        int dataSize = (int) ((t2-t1)/(a4.timeStepMillis*plotFreq));
        for (int i=0; i<defsForFile.size(); i++) {
          LineDefinition d = (LineDefinition) defsForFile.get(i);
          d.dataY = new double[dataSize+1];
          for (int j=0; j<d.dataY.length; j++) d.dataY[j]=Double.NEGATIVE_INFINITY;
        }
        boolean useFloat = (firstLine.useFloat);
        int numSize = useFloat?4:8;
       
        DataInputStream theFile = null;
     
        double[] valsForThisTimeStep = new double[totalToPlot];
        Node format = dataFormat.selectSingleNode("/dataformat/functionalgroup[data='" + firstLine.file + "']");
        String timeFileName = dataPath + File.separator + format.valueOf("time");
        String indexFile = dataPath + File.separator + format.valueOf("index");
        
        File IndexFile = new File(indexFile);
        if (!IndexFile.exists()) indexFile = null;
        File TimeFile = new File(timeFileName);
        if (!TimeFile.exists()) TimeFile = null;
        try {
          if (firstLine.zip)
            theFile = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(dataPath+File.separator+firstLine.file))));
          else
            theFile = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath+File.separator+firstLine.file)));
          
          List varTags = format.selectNodes("var");
          int varsPerEntry = varTags.size();
          int bytesPerEntry = 0;
          byte[] types = new byte[varsPerEntry];
          
          for (ListIterator i = varTags.listIterator(); i.hasNext();) {
            Node var = (Node) i.next();
            String type = var.valueOf("type");
            if (type.length() == 0) {
              types[i.previousIndex()]=0;
              oldVersion=true;
            } else if (type.equals("real")) {
              bytesPerEntry+=numSize;
              types[i.previousIndex()]=0;
            }  else if (type.equals("int")) {
              bytesPerEntry+=4;
              types[i.previousIndex()]=1;
            } else if (type.equals("long")) {
              bytesPerEntry+=8;
              types[i.previousIndex()]=2;
            }
          }
          double[] doubleBuffer = new double[varsPerEntry];
          int[] intBuffer = new int[varsPerEntry];
          long[] longBuffer = new long[varsPerEntry];
          pm = new ProgressMonitor(a4,"Reading FG Data","",0,1000);
          
          // SKIP!!!!
          skipFGUntil(theFile,TimeFile,t1,t2, useFloat, timeFileName, bytesPerEntry, types);
          long time = (t1-1);
          long remembertime = time;
          while (time <= t2) { // for all useful timesteps
            if (pm.isCanceled()) {
              pm.close();
              cancelPlot=true;
              break mainLoop;
            }
            if (time!=remembertime) {
              remembertime=time;
              final int prog1000 = (int)(1000*((time-t1)/((1.0f*t2)-t1)));
              pm.setProgress(prog1000);
              pm.setNote("Reading..."+(prog1000/10.0)+" %");
            }
            
            for (int i = 0; i < varsPerEntry; i++) {
              if (types[i]==0) doubleBuffer[i] = readReal(theFile, useFloat);
              else if (types[i]==1) {
                intBuffer[i] = readInt(theFile);
                doubleBuffer[i] = intBuffer[i];
              } else if (types[i]==2) {
                longBuffer[i] = readLong(theFile);
                doubleBuffer[i] = longBuffer[i];
              }
              
            }
            if (oldVersion) {
              longBuffer[0] = (long) (doubleBuffer[0] * 2); // Timestep
              longBuffer[1] = (long) (doubleBuffer[1]); // Id
              intBuffer[2] = (int) (doubleBuffer[2]); // stage
            }
            if (!version.equals(V1_1)) longBuffer[0] = a4.startSimMillis+(a4.timeStepMillis*longBuffer[0]);
            if (longBuffer[0] > time) {
              time = longBuffer[0];
              if (time>t2) time=1+t2;
              if (((time)>=t1) && ((time)<=t2)) {
                int dataStep = (int) ((time-t1)/(a4.timeStepMillis*plotFreq));
                for (int lineNo=0; lineNo<defsForFile.size(); lineNo++) {
                  ((LineDefinition) defsForFile.get(lineNo)).dataY[dataStep] = valsForThisTimeStep[lineNo];
                }
                for (int i=0; i<valsForThisTimeStep.length; i++) valsForThisTimeStep[i]=Double.NEGATIVE_INFINITY;
              }
            }

            for (int lineNo=0; lineNo<defsForFile.size(); lineNo++) {
              LineDefinition ld = (LineDefinition) defsForFile.get(lineNo);
              if (ld.thisId==longBuffer[1]) {
                double theVal = 0;
                theVal = doubleBuffer[2+ld.compoundVars1[0]];
                for (int k=1; k<ld.compoundVars1.length; k++) {
                  if (ld.operator==LineDefinition.OP_ADD) 
                    theVal += doubleBuffer[2+ld.compoundVars1[k]];
                  else if (ld.operator==LineDefinition.OP_MUL)
                    theVal *= doubleBuffer[2+ld.compoundVars1[k]];
                }                
                valsForThisTimeStep[lineNo] = theVal;
              }
            }
            // Reached a new timestep:
            
          } 
         
          theFile.close();
        } catch (Exception e) { 
          if (!(e instanceof EOFException)) e.printStackTrace();
        }
      }
      pm.close();
      gp.plotLines(null,lc);
      a4.refreshAxes();
      gp.replotGraph(lc,a4.startOfPlot,a4.endOfPlot);
      gp.repaint();
    }
  }
    
  public void plotColourBackground(String dataPath, GraphPanel gp, BackgroundChooser bc, long t1, long t2, Document dataFormat,
                                  PrintWriter PW) {
    
    double min = Double.MAX_VALUE;
    double max = Double.MIN_VALUE;
    int pixWidth = gp.getGraphWidth();
    int pixHeight = gp.getGraphHeight();
    int topDepth = bc.getTopDepthIndex();
    int bottomDepth = bc.getBottomDepthIndex();
    int layerType = bc.getLayerType();
    String fieldName = bc.getFieldName();
    int[] graphOption = bc.getGraphOptionInts();
    ProgressMonitor pm = null;
    /* Locate data formats entry, and establish whether it's a physics graph */

    Node format = dataFormat.selectSingleNode("/dataformat/field[name='"+ fieldName + "']");
    String fileName = dataPath + File.separator + format.valueOf("data");
    
    /* Now calculate pre-requisites for data-file trawling */

    boolean floatFormat;
    if (dataFormat.valueOf("/dataformat/format").equals("")) floatFormat=true;
    else floatFormat = dataFormat.valueOf("/dataformat/format").equals("float");
    long numSize = floatFormat?4:8;
    
    long dataStart = 0;
    if (format.valueOf("dimensions/dim[1]/@start").equals("null"))
      dataStart = Long.parseLong(a4.modelFile.valueOf("track/start"));
    else dataStart = Long.parseLong(format.valueOf("dimensions/dim[1]/@start"));
    
    if (!version.equals(V1_1)) dataStart = a4.startSimMillis+(dataStart*a4.timeStepMillis);
    int dataFreq = Integer.parseInt(format.valueOf("dimensions/dim[1]/@step"));
    List vars = format.selectNodes("var");
    int numVars = vars.size();
    
    int depthEnd;
    int depthStart;
    if (!version.equals(V1_1)) {
      depthStart = Integer.parseInt(format.valueOf("dimensions/dim[2]/@start"));
      depthEnd = Integer.parseInt(format.valueOf("dimensions/dim[2]/@end"));
    } else {
      final double dtop = Double.parseDouble(format.valueOf("dimensions/dim[2]/@start"));
      final double dbottom = Double.parseDouble(format.valueOf("dimensions/dim[2]/@end"));          
      depthStart = (int) Math.floor(dtop);
      depthEnd = (int) Math.floor(dbottom);          
      if (format.valueOf("name").equals("Physical Environment")) {
        while (physicalDepths[depthStart]<dtop) depthStart++;
        while (physicalDepths[depthEnd]<dbottom) depthEnd++;
      }
    }
    int numLayers = 1+(depthEnd-depthStart);
    int totalNumsPerStep = numLayers*numVars;
    long bytesPerTimeStep = totalNumsPerStep * numSize;
    if (plotFreq<dataFreq) plotFreq = dataFreq;
    long intervalMillis = plotFreq*a4.timeStepMillis;
    long bytesBetweenTimeStepsToSkip = ((plotFreq/ dataFreq) - 1) * totalNumsPerStep * numSize;
    double[][] dataForOneTimestep = new double[vars.size()][numLayers];

    DataInputStream theFile = null;
    try {
      if (format.valueOf("zip").equals("true"))
        theFile = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(fileName))));
      else
        theFile = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
      pm = new ProgressMonitor(a4,"Reading Background Data","",0,1000);
      for (long i=dataStart; i<t1; i+=a4.timeStepMillis) {
        ReallySkip(bytesPerTimeStep, theFile);
        if (pm.isCanceled()) {
          pm.close();
          cancelPlot=true;
          theFile.close();
          return;
        }
        
        final int prog1000=(int)(1000*((i-dataStart)/((1.0f*t1)-dataStart)));
        pm.setProgress(prog1000);
        pm.setNote("Skipping..."+(prog1000/10.0)+" %");
      }
      int firstY, secondY;
      pm.close();
      pm = new ProgressMonitor(a4,"Reading Background Data","",0,1000);
      pm.setNote("Reading...");
      long startI = Math.max(t1,dataStart);
      if (dataStart>t1) {
        int difference = (int) (dataStart-t1);
        int skipTimeSteps = (plotFreq-(difference%plotFreq));
        startI=startI+skipTimeSteps;
        ReallySkip(bytesPerTimeStep*skipTimeSteps, theFile);
        
      }
      mainLoop: for (long i = startI; i <= t2; i += intervalMillis) {
        if (pm.isCanceled()) {
          pm.close();
          cancelPlot=true;
          break mainLoop;
        }
        final int prog1000=(int) (1000f*((i-startI)/((1.0f*t2)-startI)));
        pm.setProgress(prog1000);
        pm.setNote("Reading..."+(prog1000/10.0)+" %");
        

        if (!version.equals(V1_1)) {
          for (int j=0; j<numVars; j++) 
            for (int k=0; k<numLayers; k++) 
              dataForOneTimestep[j][k] = readReal(theFile, floatFormat);
        
        } else {          
          for (int k=0; k<numLayers; k++) 
            for (int j=0; j<numVars; j++) 
              dataForOneTimestep[j][k] = readReal(theFile, floatFormat);
        }
     
       
        for (int j = topDepth; j <= bottomDepth; j++) {
          if (layerType == BackgroundChooser.PHYSICS_LAYER) {
            firstY = (int) Math.floor((((physicalDepths[j] - physicalDepths[topDepth]) / (physicalDepths[bottomDepth] - physicalDepths[topDepth])) * pixHeight));
            secondY = (int) Math.floor((((physicalDepths[j + 1] - physicalDepths[topDepth]) / (physicalDepths[bottomDepth] - physicalDepths[topDepth])) * pixHeight));
          } else {
            firstY = (int) Math.floor((((double) (j - topDepth) / (bottomDepth - topDepth))) * pixHeight);
            secondY = (int) Math.floor((((double) ((j + 1) - topDepth) / (bottomDepth - topDepth))) * pixHeight);
          }
          if (secondY > pixHeight)
            secondY = pixHeight;
          
          int xPos = (int) Math.floor(pixWidth * ((double) (i - t1) / (double) (t2 - t1)));
          if (xPos >= pixWidth)
            xPos = pixWidth - 1;
                    
          double theValue = 0;
          if (bc.getOp()==BackgroundChooser.OP_MUL) theValue = 1;
          for (int k=0; k<graphOption.length; k++) {
            if (!bc.getLog()) {
              if (bc.getOp()==BackgroundChooser.OP_ADD) theValue+=dataForOneTimestep[graphOption[k]][j];
              else theValue*=dataForOneTimestep[k][j];
            } else {
              if (bc.getOp()==BackgroundChooser.OP_ADD) theValue+=StringTools.log10(dataForOneTimestep[graphOption[k]][j]);
              else theValue*=StringTools.log10(dataForOneTimestep[k][j]);
            }
          }
            
            
          
          for (int k = firstY; k < secondY; k++) {
            gp.setValue(xPos, k, theValue);
            if (theValue>max) max=theValue;
            if (theValue<min) min=theValue;
          }
        }
        int firstX = (int) Math.floor(pixWidth * ((double) (i - t1) / (double) (t2 - t1)));
        int secondX = (int) Math.floor(pixWidth * (((double) (i - t1) + intervalMillis) / (t2 - t1)));
        if (secondX > pixWidth)
          secondX = pixWidth;
        if (secondX > firstX) {
          for (int j = firstX + 1; j < secondX; j++)
            for (int k = 0; k < pixHeight; k++)
              gp.setValue(j, k, gp.getValue(firstX, k));
        }
        if (i <t2)
          ReallySkip(bytesBetweenTimeStepsToSkip, theFile);
      }
      theFile.close();
      pm.close();
    } catch (Exception e) {
      if (!(e instanceof EOFException))
        e.printStackTrace();
    }
    if (bc.getAuto()) bc.setMinMax(min,max);
    if (pm!=null) pm.close();
    gp.setBackgroundChooser(bc);
    a4.refreshAxes();
    gp.replotGraph(a4.lc2,a4.startOfPlot,a4.endOfPlot);
    gp.repaint();
  }
  
  public void plotContour(String dataPath, GraphPanel gp, BackgroundChooser bc, long t1, long t2, Document dataFormat,
      int noContours, boolean fillContours, boolean drawContours) {

    double min = Double.POSITIVE_INFINITY;
    double max = Double.NEGATIVE_INFINITY;
    int topDepth = bc.getTopDepthIndex();
    int bottomDepth = bc.getBottomDepthIndex();
    int layerType = bc.getLayerType();
    String fieldName = bc.getFieldName();
    int[] graphOptions = bc.getGraphOptionInts();
    boolean logMe = bc.getLog();
    double data[][] = null;
    int xPos=0;
    ProgressMonitor pm = new ProgressMonitor(a4,"Reading Background Data","",0,1000);
    
    /* Locate data formats entry, and establish whether it's a physics graph */

    Node format = dataFormat.selectSingleNode("/dataformat/field[name='" + fieldName + "']");
    String fileName = dataPath + File.separator + format.valueOf("data");
    
    /* Now calculate pre-requisites for data-file trawling */

    boolean floatFormat;
    if (dataFormat.valueOf("/dataformat/format").equals("")) floatFormat=true;
    else floatFormat = dataFormat.valueOf("/dataformat/format").equals("float");
    long numSize = floatFormat?4:8;
    
    long dataStart = 0;
    if (format.valueOf("dimensions/dim[1]/@start").equals("null")) 
      dataStart = Long.parseLong(a4.modelFile.valueOf("track/start"));
    else dataStart = Long.parseLong(format.valueOf("dimensions/dim[1]/@start"));
    if (!version.equals(V1_1)) dataStart = a4.startSimMillis+(a4.timeStepMillis*dataStart);    
    int dataFreq = Integer.parseInt(format.valueOf("dimensions/dim[1]/@step"));
    long intervalMillis = a4.timeStepMillis*plotFreq;
    int numVars = format.selectNodes("var").size();
    int depthStart,depthEnd;
    
    if (!version.equals(V1_1)) {
      depthStart = Integer.parseInt(format.valueOf("dimensions/dim[2]/@start"));
      depthEnd = Integer.parseInt(format.valueOf("dimensions/dim[2]/@end"));
    } else {
      final double dtop = Double.parseDouble(format.valueOf("dimensions/dim[2]/@start"));
      final double dbottom = Double.parseDouble(format.valueOf("dimensions/dim[2]/@end"));          
      depthStart = (int) Math.floor(dtop);
      depthEnd = (int) Math.floor(dbottom);          
      if (format.valueOf("name").equals("Physical Environment")) {
        while (physicalDepths[depthStart]<dtop) depthStart++;
        while (physicalDepths[depthEnd]<dbottom) depthEnd++;
      }
    }
    
    int numLayers = 1+(depthEnd-depthStart);
    int totalNumsPerStep = numVars*numLayers;
    
    long bytesPerTimeStep = totalNumsPerStep * numSize;
    
    if (plotFreq<dataFreq) plotFreq = dataFreq;
    long bytesBetweenTimeStepsToSkip = ((plotFreq/ dataFreq) - 1) * totalNumsPerStep * numSize;
    double[][] dataForOneTimestep = new double[numVars][numLayers];

    DataInputStream theFile = null;
    try {
      if (format.valueOf("zip").equals("true"))
        theFile = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(fileName))));
      else
        theFile = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
      for (long i=dataStart; i<t1; i+=a4.timeStepMillis) {
        ReallySkip(bytesPerTimeStep, theFile);
        if (pm.isCanceled()) {
          pm.close();
          cancelPlot=true;
          theFile.close();
          return;
        }
        final int prog1000 =(int)(1000*((i-dataStart)/((1.0f*t1)-dataStart))); 
        pm.setProgress(prog1000);
        pm.setNote("Reading..."+(prog1000/10.0)+" %");
      }
      pm.close();
      pm = new ProgressMonitor(a4,"Reading Background Data","",0,1000);
      pm.setNote("Memory Allocation...");
      xPos = 0;
      data = new double[(int)((t2-t1)/intervalMillis)+1][1+(bottomDepth-topDepth)];
      
      long startI = Math.max(t1,dataStart);
      //if (dataStart>t1) {
//        int difference = (int) (dataStart-t1);
        //long skipTimeSteps = (plotFreq-(difference%plotFreq));
        //startI=startI+skipTimeSteps;
        //ReallySkip(bytesPerTimeStep*skipTimeSteps, theFile);
      //}
      pm.close();
      pm = new ProgressMonitor(a4,"Reading Background Data","",0,1000);
      
      if (startI>t1) xPos = (int) (1+((startI-t1)/intervalMillis));
      mainLoop: for (long i = startI; i <= t2; i += intervalMillis) {
        if (pm.isCanceled()) {
          pm.close();
          cancelPlot=true;
          break mainLoop;
        }
        final int pm1000 = (int)(1000*((i-startI)/((1.0f*t2)-startI))); 
        pm.setProgress(pm1000);
        pm.setNote("Reading... "+(pm1000/10)+" %");
        if (!version.equals(V1_1)) {        
          for (int j = 0; j < numVars; j++)
            for (int k=0; k < numLayers; k++) { 
              dataForOneTimestep[j][k] = readReal(theFile, floatFormat);
              if (logMe) dataForOneTimestep[j][k] = StringTools.log10(dataForOneTimestep[j][k]);
            }
        } else {
          for (int k=0; k < numLayers; k++)
            for (int j = 0; j < numVars; j++) {
              dataForOneTimestep[j][k] = readReal(theFile, floatFormat);
              if (logMe) dataForOneTimestep[j][k] = StringTools.log10(dataForOneTimestep[j][k]);
            }
        }
        


        for (int j = topDepth; j <= bottomDepth; j++) {
          double theValue=0;
          if (bc.getOp()==BackgroundChooser.OP_MUL) theValue=1;
          for (int k=0; k<graphOptions.length; k++) {
            if (bc.getOp()==BackgroundChooser.OP_MUL) theValue*=dataForOneTimestep[graphOptions[k]][j];
            else if (bc.getOp()==BackgroundChooser.OP_ADD) theValue+=dataForOneTimestep[graphOptions[k]][j];
          }
          data[xPos][j]=theValue;
          if (theValue>max) max=theValue;
          if (theValue<min) min=theValue;
        }
        xPos++;
        
        if (i < t2) ReallySkip(bytesBetweenTimeStepsToSkip, theFile);
      }
      theFile.close();
    } catch (Exception e) {
      if (!(e instanceof EOFException))
        e.printStackTrace();
    }
    double[] depths;
    if (layerType==BackgroundChooser.PHYSICS_LAYER) depths = physicalDepths;
    else {
      depths = new double[500];
      for (int i=0; i<500; i++) depths[i]=i;
    }
    if (bc.getAuto()) bc.setMinMax(min,max);
    else { min = bc.getMin();  max = bc.getMax(); }
    pm.close();
    CP.plotContours(a4,data, min, max, noContours, t1, t2, topDepth, bottomDepth, fillContours, drawContours, gp, depths, bc.getInvert(), this);
    gp.setBackgroundChooser(bc);
    a4.refreshAxes();
    gp.replotGraph(a4.lc2,a4.startOfPlot,a4.endOfPlot);
    gp.repaint();
  }
  
  
  class PlotThread extends Thread {
    String _dataPath;
    GraphPanel _gp;
    long _t1;
    long _t2;
    LineChooser2 _lc;
    Document _formatFile;
    int _job;
    int _noContours;
    boolean _drawContours;
    boolean _fillContours;
    PrintWriter _PW;
    BackgroundChooser _BC;
    int _oldPW;
    int _oldPH;
    double[][] _oldScreen;
    String _fn;
    boolean done = false;
    
    public boolean isDone() { return done; }
    
    public PlotThread(String dataPath, GraphPanel gp, LineChooser2 lc, long t1, long t2, 
        Document formatFile, int job) {
      _dataPath=dataPath;
      _gp=gp;
      _lc=lc;
      _t1=t1;
      _t2=t2;
      _formatFile=formatFile;
      _job=job;
    }

    public PlotThread(String dataPath, GraphPanel gp, BackgroundChooser BC, long t1, long t2, 
        Document formatFile, int noContours, boolean fillContours, boolean drawContours, int job) {
      this(dataPath,gp,null,t1,t2,formatFile,job);
      _noContours = noContours;
      _fillContours = fillContours;
      _drawContours = drawContours;
      _BC = BC;
    }
    
    public PlotThread(String dataPath, GraphPanel gp, BackgroundChooser BC, long t1, long t2, 
        Document formatFile, int noContours, boolean fillContours, boolean drawContours, int job,
        int oldPW, int oldPH, LineChooser2 lc, String fn, double[][] oldScreen) {
      this(dataPath,gp,BC,t1,t2,formatFile,noContours,fillContours,drawContours,job);
      _oldPW=oldPW;
      _oldPH=oldPH;
      _lc=lc;
      _fn=fn;
      _oldScreen=oldScreen;
    }


    public PlotThread(String dataPath, GraphPanel gp, BackgroundChooser BC, long t1, long t2, 
        Document formatFile, PrintWriter PW, int job) {
      this(dataPath,gp,null,t1,t2,formatFile,job);
      _PW=PW;
      _BC=BC;
    }
    
    public PlotThread(String dataPath, GraphPanel gp, LineChooser2 lc, Document formatFile, int job) {
      _dataPath=dataPath;
      _gp=gp;
      _lc=lc;
      _formatFile = formatFile;
      _job=job;
    }
    
    public PlotThread(String dataPath, GraphPanel gp, BackgroundChooser bc, long t1, long t2, 
        Document format, PrintWriter PW, int job, int oldPW, int oldPH, LineChooser2 lc,
        String fn, double[][] oldScreen) {
      this(dataPath,gp,bc,t1,t2,format,PW,job);
      _oldPW=oldPW;
      _oldPH=oldPH;
      _lc=lc;
      _fn=fn;
      _oldScreen=oldScreen;
    }

    
    
    public void run() {
      done=false;
      if (_job==WC_PLOT) {
        plotSingleVar(_dataPath, _gp, _lc, _t1, _t2, _formatFile);
      } else if (_job==FD_PLOT) {
        plotFieldAtDepth(_dataPath,_gp,_lc,_t1,_t2,_formatFile);
      } else if (_job==FG_PLOT) {
        plotFGLines(_dataPath,_gp,_lc,_t1,_t2,_formatFile);
      } else if (_job==XY_PLOT) {
        plotXY(_dataPath, _gp, _lc, _t1, _t2, _formatFile);
      } else if (_job==CT_PLOT) {
        plotContour(_dataPath, _gp, _BC, _t1, _t2, _formatFile, _noContours, _fillContours, _drawContours);
      } else if (_job==DE_PROF) {
        plotDepthProfile(_dataPath, _gp, _lc, _formatFile);
      } else if (_job==CO_BACK) {
        plotColourBackground(_dataPath, _gp, _BC, _t1, _t2, _formatFile, _PW );
      } else if (_job==CO_JPG_BACK) {
        plotColourBackground(_dataPath, _gp, _BC, _t1, _t2, _formatFile, _PW );
        _gp.realExportJPG(_lc,_oldPW,_oldPH,_fn,_oldScreen);
      } else if (_job==CT_JPG_PLOT) {
        plotContour(_dataPath, _gp, _BC, _t1, _t2, _formatFile, _noContours, _fillContours, _drawContours);
        _gp.realExportJPG(_lc,_oldPW,_oldPH,_fn,_oldScreen);
      }
      done=true;
      checkFinished(_gp, _lc);     
    }
  }
}