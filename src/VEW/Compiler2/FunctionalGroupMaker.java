package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;


public class FunctionalGroupMaker {
  public static void writeFunctionalGroupJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("");
      PW.println("import java.io.*;");
      PW.println("");
      PW.println("import VEW.Sim.BLayer;");
      PW.println("import VEW.Sim.Kernel;");
      PW.println("import VEW.Sim.Logging;");
      PW.println("");
      PW.println("public abstract class FunctionalGroup {");
      PW.println("");
      PW.println("  public int index;");
      PW.println("  public double[] z = new double[3];");
      PW.println("  public double[] c = new double[2];");
      XMLTag[] species = model.getTag("species").getTags("species");
      int speciesCount = model.getTag("species").getTags("species").length;
      int itemCount=0;
      for (int i=0; i<speciesCount; i++) {
        XMLTag fg = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg"));
        itemCount+=fg.getTags("stage").length;
      }
      PW.println("  public static double[] IngestedCells = new double["+itemCount+"];");
      
      PW.println("  public double ingestionLoss;");
      PW.println("  public long id;");
      PW.println("  public int liveTime;   // Stores timestep number that the particle came into existence on...");
      PW.println("  public BLayer blayer;");
      PW.println("  public static long IDCount = 0;");
      PW.println("  public boolean log = true;");
      PW.println("  public abstract String fgName();");
      PW.println("  public abstract String name();");
      PW.println("  public abstract FunctionalGroup getClone();");
      PW.println("  public abstract void mergeWith(FunctionalGroup fg2);");
      PW.println("  public abstract boolean logDescendents();");
      PW.println("  public abstract void writeSnapshot(DataOutputStream snapshot);");
      PW.println("  public abstract FunctionalGroup readSnapshot(DataInputStream snapshot);");
      PW.println("  public abstract int getStageCount();");
      PW.println("  public abstract void updateLiveSim(boolean force);");
      PW.println("  public abstract void split(double _no);");
      PW.println("  public abstract void copyRequests(FunctionalGroup _fg0);");
      PW.println("  public static double _ambientTemperature=0;");
      PW.println("  public static double _ambientVisIrrad=0;");
      PW.println("  public abstract void _writeAudits();");
      PW.println("  public abstract int getAllStageIndex();");
      PW.println("  public static int SORT_BY = 1;");
      PW.println("  public final static int SORT_CELL_COUNT = 1;");
      PW.println("  public int _CurrentStage = 0;");
      PW.println("  public static int _NextStage = -1;");
      PW.println("  public abstract boolean getStageLogStatus();");
      PW.println("  public abstract FGParams getParams();");
      PW.println("");
      PW.println("  public void update() {");
      PW.println("//    System.out.println(Kernel.timeSteps+\",\"+id+\",\"+getParams()._type+\",\"+_CurrentStage);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setLog() {");
      PW.println("    if (Logging.isAuditEnabled())");
      PW.println("      log = Logging.shouldLog(id, getParams()._type);");
      PW.println("    else");
      PW.println("      log = true;");
      PW.println("  }");
      PW.println("");
      PW.println("  public abstract void freeParticle();");
      PW.println("");
      PW.println("  public FunctionalGroup() {");
      PW.println("    z[0] = 0.0;");
      PW.println("    z[1] = 0.0;");
      PW.println("    z[2] = 0.0;");
      PW.println("    c[0] = 0.0;");
      PW.println("    c[1] = 0.0;");
      PW.println("  }");
      PW.println("");
      PW.println("  public void changeState(int NewState, int reason, boolean maintain, int speciesID) {");
      PW.println("    if(_CurrentStage != NewState) {");
      PW.println("      int type = getParams()._type;");
      PW.println("      liveTime = Kernel.timeSteps;");
      PW.println("      if ((log) && (getStageLogStatus()) && (Logging.getLifespan()))");
      PW.println("        Logging.writeChangeToLifespan(type, reason, Kernel.timeSteps, id, c[1], NewState);");
      PW.println("");
      PW.println("      if ((maintain) && (type==speciesID)) Kernel.W.lostParticles[type][_CurrentStage]++;");
      PW.println("      _NextStage = NewState;");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
            PW.println("");
      PW.println("}");
      PW.flush();
      PW.close();
    } catch (Exception e) { e.printStackTrace(); }

  }

}

