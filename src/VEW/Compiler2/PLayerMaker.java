package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;


public class PLayerMaker {
  public static void writePLayerJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("import java.io.*;");
      PW.println("import java.util.ArrayList;");
      PW.println("");
      PW.println("public class PLayer {");
      PW.println("");
      PW.println("  double AvgDens;");
      PW.println("  double AvgIrrad;");
      PW.println("  double AvgSaln;");
      PW.println("  double AvgTemp;");
      PW.println("  double Density;");
      PW.println("  double Depth;");
      PW.println("  double Full_Irrad;");
      PW.println("  double[] irrad = new double[25];");
      PW.println("  double Salinity;");
      PW.println("  double Temp;");
      PW.println("  double TempAdj;");
      PW.println("  public double Vis_Irrad;");
      PW.println("  static int Count = 0;");
      PW.println("");
      PW.println("  public PLayer() {}");
      PW.println("");
      PW.println("  public PLayer(double visIrrad, double fullIrrad, double density, double salinity, double temp, double depth) {");
      PW.println("    Vis_Irrad = visIrrad;");
      PW.println("    Full_Irrad = fullIrrad;");
      PW.println("    Density = density;");
      PW.println("    Salinity = salinity;");
      PW.println("    Temp = temp;");
      PW.println("    TempAdj = 0;");
      PW.println("    Depth = depth;");
      PW.println("    AvgTemp = 0;");
      PW.println("    AvgIrrad = 0;");
      PW.println("    AvgDens = 0;");
      PW.println("    AvgSaln = 0;");
      PW.println("  }");
      PW.println("");
      PW.println("");
      PW.println("  public static void addLayerFromSnapshot(ArrayList list, DataInputStream snapshot) {");
      PW.println("    try {");
      PW.println("      PLayer P = new PLayer();");
      PW.println("      P.AvgDens = snapshot.readDouble();");
      PW.println("      P.AvgIrrad = snapshot.readDouble();");
      PW.println("      P.AvgSaln = snapshot.readDouble();");
      PW.println("      P.AvgTemp = snapshot.readDouble();");
      PW.println("      P.Density = snapshot.readDouble();");
      PW.println("      P.Depth = snapshot.readDouble();");
      PW.println("      P.Full_Irrad = snapshot.readDouble();");
      PW.println("      P.Salinity = snapshot.readDouble();");
      PW.println("      P.Temp = snapshot.readDouble();");
      PW.println("      P.TempAdj=snapshot.readDouble();");
      PW.println("      P.Vis_Irrad = snapshot.readDouble();");
      PW.println("      list.add(P);");
      PW.println("    } catch (Exception e) { e.printStackTrace(); }");
      PW.println("  }");
      PW.println("");
      PW.println("  public void writeSnapshot(DataOutputStream snapshot) {");
      PW.println("    try {");
      PW.println("      snapshot.writeDouble(AvgDens);");
      PW.println("      snapshot.writeDouble(AvgIrrad);");
      PW.println("      snapshot.writeDouble(AvgSaln);");
      PW.println("      snapshot.writeDouble(AvgTemp);");
      PW.println("      snapshot.writeDouble(Density);");
      PW.println("      snapshot.writeDouble(Depth);");
      PW.println("      snapshot.writeDouble(Full_Irrad);");
      PW.println("      snapshot.writeDouble(Salinity);");
      PW.println("      snapshot.writeDouble(Temp);");
      PW.println("      snapshot.writeDouble(TempAdj);");
      PW.println("      snapshot.writeDouble(Vis_Irrad);");
      PW.println("    } catch (Exception e) { e.printStackTrace(); }");
      PW.println("  }");
      PW.println("}");
      PW.flush();
      PW.close();      
    } catch (Exception e) { e.printStackTrace(); }

  }

}

