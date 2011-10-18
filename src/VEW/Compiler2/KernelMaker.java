package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;


public class KernelMaker {
  public static void writeKernelJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("");
      PW.println("import java.util.ArrayList;");
      PW.println("import java.io.*;");
      PW.println("");
      PW.println("public class Kernel {");
      PW.println("");
      PW.println("  private static ArrayList speciesObjects = new ArrayList();");
      PW.println("");
      PW.println("  public static WaterCol W;");
      PW.println("  public static long myTime = 0;");
      PW.println("  public static int timeSteps = 0;");
      PW.println("  public static double DTime = 6;");
      PW.println("  public static int historySize = 2;");
      PW.println("  public static PrintWriter P;");
      PW.println("");
      PW.println("  public static FunctionalGroup getSpecies(int i) { return (FunctionalGroup) speciesObjects.get(i); }");
      PW.println("");
      PW.println("  public static void clear() {");
      PW.println("    speciesObjects.clear();");
      PW.println("  }");
      PW.println("");
      PW.println("  public static int findSpecies(String speciesName) {");
      PW.println("    int i=0;");
      PW.println("    boolean found = false;");
      PW.println("    while ((i<speciesObjects.size()) && (!found)) {");
      PW.println("      found = ((FunctionalGroup)speciesObjects.get(i)).name().equals(speciesName);");
      PW.println("      if (!found) i++;");
      PW.println("    }");
      PW.println("    if (!found) return -1;");
      PW.println("    else return i;");
      PW.println("  }");
      PW.println("");
      PW.println("  public static int registerSpecies(FunctionalGroup fg) {");
      PW.println("    int index = findSpecies(fg.name());");
      PW.println("    if (index==-1) {");
      PW.println("      speciesObjects.add(fg);");
      PW.println("      return speciesObjects.size()-1;");
      PW.println("    } else return index;");
      PW.println("  }");
      PW.println("");
      PW.println("  public static int getAllStageIndex(int speciesNo) {");
      PW.println("    return ((FunctionalGroup) speciesObjects.get(speciesNo)).getAllStageIndex();");
      PW.println("  }");
      PW.println("");
      PW.println("  public static int getStageCount(int speciesNo) {");
      PW.println("    return ((FunctionalGroup) speciesObjects.get(speciesNo)).getStageCount();");
      PW.println("  }");
      PW.println("");
      PW.println("");
      PW.println("  public static void print(String s) {");
      PW.println("    if (P==null) { System.out.print(s); } else { P.print(s); P.flush(); }");
      PW.println("  }");
      PW.println("  public static void println(String s) {");
      PW.println("    if (P==null) { System.out.println(s); } else { P.println(s); P.flush(); }");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void println(double d) {");
      PW.println("    if (P==null) { System.out.println(d); } else { P.println(d); P.flush(); }");
      PW.println("  }");
      PW.println("}");
      PW.flush();
      PW.close();
    } catch (Exception e) { e.printStackTrace(); }

  }

}

