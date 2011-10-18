package VEW.Common.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import VEW.Common.StringTools;

public class PrepareForCompiler {
  public static void main(String[] args) throws Exception {
    if (args.length!=4) System.out.println("Usage: java PrepareForCompiler in.java out.java classname funcname");
    else {
      PrintWriter PW = new PrintWriter(new FileOutputStream(new File(args[1])));
      BufferedReader br = new BufferedReader(new FileReader(args[0]));
      
      
      PW.println("package VEW.Compiler2;\n");
      PW.println("import java.io.File;\n");
      PW.println("import java.io.PrintWriter;\n");
      PW.println("import VEW.Common.XML.XMLTag;\n\n");
      PW.println("public class "+args[2]+" {");
      PW.println("  public static void "+args[3]+"(String fileName, XMLTag model) {\n");
      PW.println("    try {\n");
      PW.println("      PrintWriter PW = new PrintWriter(new File(fileName));\n");
      
      int i=0;
      String s;
      String quote = "\"";
      String backslashquote = "\\\"";
      while (br.ready()) {
        s = br.readLine();
        s = StringTools.replace(s,quote,backslashquote);
        PW.println("      PW.println(\""+s+"\");");
        i++;
      }
      PW.println("    } catch (Exception e) { e.printStackTrace(); }\n");
      PW.println("  }\n");
      PW.println("}\n");
      PW.flush();
      PW.close();
      System.out.println(i+" lines written");
    }
  }
}
