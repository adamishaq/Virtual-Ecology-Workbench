/*
 * SystemTools.java
 *
 * Created on July 14, 2004, 1:10 PM
 */

package VEW.Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.lang.StringBuffer;
//import java.util.Properties;


/**
 *
 * @author  Evan
 */
public class SystemTools {
    
    /** Creates a new instance of SystemTools */
    public SystemTools() {
    }        
    
	public static boolean executeInShell(String path, String [] _cmd) {
		return executeInShell(path, _cmd, false);
	}

    public static boolean executeInShell(String path, String [] _cmd, boolean debug) {
    	if (File.separator.equals("/")) {
    		return executeInShellUnix(path, _cmd, debug);
    	} else {
    		return executeInShellDos(path, _cmd, debug);
    	}    	
    }
    
    public static boolean executeInShellUnix(String path, String [] _cmd, boolean debug) {
        String shell = "bash";
        String option = "-c";
        
        String [] cmd = new String[3];
        cmd[0] = shell;
        cmd[1] = option;
        cmd[2] = "cd " + path + "; " + StringTools.join(_cmd, " ");
        
        return execute(cmd, debug);        
    }
    
    public static boolean executeInShellDos(String path, String [] _cmd, boolean debug) {        
        // on my XP system this is the only foolproof way of running a command in a specific 
        // working directory from inside java
        
        String shell = "cmd /C"; //"start /WAIT /MIN cmd /C"; //rundll32 SHELL32.DLL,ShellExec_RunDLL 
        String [] _shell = shell.split(" ");
                
        String [] cmd = new String[_shell.length + 1];
        for (int i=0; i < _shell.length; i++)
            cmd[i] = _shell[i];
                
        cmd[_shell.length] = "\"cd " + path + " && " + StringTools.join(_cmd, " ") + "\"";
        
        return execute(cmd, debug);        
    }
    
    public static boolean execute(String[] cmd) {         
        return execute(cmd, false);
    }
    
    public static boolean execute(String[] cmd, boolean debug) {         
    
      if (debug) {
        String cmdstring = StringTools.join(cmd, " | ");
        System.out.println("execute: " + cmdstring);
      }
        
      try {
          Process p = Runtime.getRuntime().exec(cmd);     
          String p_str;
          BufferedReader p_in = new BufferedReader(new InputStreamReader(p.getInputStream()));
          try {
            while ((p_str = p_in.readLine()) != null) {
                System.out.println(p_str);
            }
          } catch (IOException e) {
                return false;
          }
      } catch (IOException e1) {
	    System.err.println(e1);
	    return false;
      }
      return true;
    }
    
    public static boolean move(String source, String dest) {
        //System.out.println(source + "->" + dest);
        File file = new File(source);
        return file.renameTo(new File(dest));        
    }
    
    public static boolean delete(String filename) {
        //System.out.println(source + "->" + dest);
        File file = new File(filename);
        return file.delete();     
    }
    
    /*public static String changeWorkingDir(String dir) {
        Properties p = new Properties(System.getProperties());
        String old_dir = p.getProperty("user.dir");
        p.setProperty("user.dir", dir);   
        System.setProperties(p);
        
        return old_dir;
    }*/
   
    public static void main(String[] args) {
        boolean shell = false;        
  
        if (args[0].startsWith("sh"))
            shell = true;        
        
        String path = "";
        int i = 1;
        if (shell) {
            path = args[1];
            i = 2;
        }
        
        String [] cmd = new String[args.length - i];
        
        for (int j = 0; j < args.length - i; j++)
            cmd[j] = args[j+i];
            
        if (shell) {
            executeInShellDos(path, cmd, true);
        } else {
            execute(cmd, true);
        }
        
        return;
    }
    
}
