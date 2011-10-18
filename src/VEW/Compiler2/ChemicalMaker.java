package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class ChemicalMaker {

  public static void writeChemicalJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("");
      PW.println("import java.io.*;");
      PW.println("public abstract class Chemical {");
      PW.println("  BLayer blayer; // Depth Note");
      PW.println("  double z; // Depth for physical properties.");
      PW.println("  public int ID = -1; // The ID that will be assigned by the Kernel;");
      PW.println("  int type;");
      PW.println("  public abstract boolean isPigment();");
      PW.println("  public static double _ambientTemperature=0;");
      PW.println("  public static double _ambientVisIrrad=0;");
      PW.println("  public Chemical() { this(null); }");
      PW.println("  public Chemical(BLayer layer) { ");
      PW.println("    if (layer!=null) {");
      PW.println("      blayer = layer;");
      PW.println("      z=layer.Depth;");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
      PW.println("  public abstract String name();");
      PW.println("  public abstract void update();");
      PW.println("  public abstract void writeSnapshot(DataOutputStream snapshot);");
      PW.println("  public abstract void readSnapshot(DataInputStream snapshot);");
      PW.println("  ");
      PW.println("}");
      PW.flush();
      PW.close();
    } catch (Exception e) { e.printStackTrace(); }
  }
   
}
