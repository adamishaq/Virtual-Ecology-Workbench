package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;


public class DiffFuncMaker {
  public static void writeDiffFuncJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("");
      PW.println("public abstract class DiffFunc {");
      PW.println("  public DiffFunc() { }");
      PW.println("  public double f(FunctionalGroup _fg) { return f(_fg,_fg.blayer,0,_fg.blayer.Depth); }");
      PW.println("  public abstract double f(FunctionalGroup _fg, BLayer _blayer, int _vcount, double _z);");
      PW.println("}");
      PW.flush();
      PW.close();
    } catch (Exception e) { e.printStackTrace(); }

  }

}

