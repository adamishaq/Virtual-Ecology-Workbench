package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;


public class LayerMaker {
  public static void writeLayerJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("");
      PW.println("public abstract class Layer { ");
      PW.println("  int ID;");
      PW.println("}");
      PW.flush();
      PW.close();
    } catch (Exception e) { e.printStackTrace(); }

  }

}

