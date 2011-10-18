package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;


public class UpdateFunctionMaker {
  public static void writeUpdateFunctionJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("");
      PW.println("interface UpdateFunction");
      PW.println("{");
      PW.println("  public void update(FunctionalGroup fg);");
      PW.println("}");
      PW.flush();
      PW.close();
    } catch (Exception e) { e.printStackTrace(); }

  }

}

