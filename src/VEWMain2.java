import java.io.File;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import VEW.Compiler2.ModelCompiler;
import VEW.Controller2.TitlePage;
import VEW.Controller2.VEWController2;

class VEWMain2 {
  private static int     CompileMode    = VEWController2.DontCompile;
  private static String  DefaultModel   = null;
  private static String  ModelDirectory = "modelTree";
  
	static {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}

	}
  
  public static void tryDelete(File f) {
    if (f.exists()) {
      try {
        f.delete();
      } catch (Exception e) {}
    }
  }
  
  public static void clearTemp() {
    if (!new File("CompiledJobs").exists()) new File("CompiledJobs"+File.separator).mkdirs();
    if (!new File("VEWTemp").exists()) new File("VEWTemp"+File.separator).mkdirs();
    tryDelete(new File("VEWTemp"+File.separator+"vew.kmz"));
    File[] f1 = new File("VEWTemp").listFiles();
    for (int i=0; i<f1.length; i++) {
      if (f1[i].isDirectory()) {
        File[] f2 = f1[i].listFiles();
        if (f2.length==0) tryDelete(f1[i]);
      }
    }
  }
  
  public static void main(String[] args)  {
    File f = new File("vew.xml.lock");
    if (f.exists()) { f.delete(); }
    clearTemp();
    ProcessArgs(args);
    if (CompileMode==VEWController2.CompileCode) {
      ModelCompiler.compile(ModelDirectory + File.separator + DefaultModel,"VEWTemp","xxx");
      System.exit(0);
    } else {
      TitlePage tp = new TitlePage();
      tp.setVisible(true);
    }
	}
  
  private static void ProcessArgs(String[] args) {
    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        if (args[i].toLowerCase().startsWith("/usedir:"))
          ModelDirectory = args[i].substring(args[i].indexOf(":") + 1);
        else if (args[i].toLowerCase().startsWith("/usemodel:"))
          DefaultModel = args[i].substring(args[i].indexOf(":") + 1);
        else if (args[i].toLowerCase().startsWith("/compilecode"))
          CompileMode = VEWController2.CompileCode;
        else if (args[i].toLowerCase().startsWith("/compileexec"))
          CompileMode = VEWController2.CompileExec;
        else if (args[i].toLowerCase().startsWith("/compilejar"))
          CompileMode = VEWController2.CompileJar;
        else if (args[i].toLowerCase().startsWith("/help") || args[i].startsWith("/?") || args[i].startsWith("--help"))
          OutputCorrectUsage(args[i], true);
        else if (args[i].toLowerCase().startsWith("/noc"))
          VEWController2.NOC_Mode=true;
        else
          OutputCorrectUsage(args[i], false);
      }
    }

    if(CompileMode != VEWController2.DontCompile) {
      if(DefaultModel == null) {
        System.out.println("The /usemodel flag must be included when using a /compile flag, use /help for more info.");
        System.exit(0);
      } else if(DefaultModel.split("/").length < 3) {
        System.out.println("The /usemodel flag must include a model, project and experiment when using a /compile flag, use /help for more info.");
        System.exit(0);
      }
    }
  }
  
  private static void OutputCorrectUsage(String InvalidCommand, boolean Requested) {
    if(!Requested) {
      System.out.println("Invalid command line argument: " + InvalidCommand);
      System.out.println("Correct usage:");
    } else System.out.println("Command line options for VEW:");
    
    System.out.println("/compilecode            - Don't display the gui, just generate code for the model specified by /usemodel.");
    System.out.println("/compileexec            - Don't display the gui, just compile the model specified by /usemodel to class files.");
    System.out.println("/compilejar             - Don't display the gui, just compile the model specified by /usemodel into a jar file.");
    System.out.println("/help                   - Display this message.");
    System.out.println("/usedir:[modeldir]      - Look for models starting in specified modeldir.");
    System.out.println("/usemodel:[mod/pro/exp] - Auto load (or compile) the model, project and experiment specified (project and experiment optional unless /compile flag is used).");
    System.exit(0);
  }
}
