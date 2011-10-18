package VEW.Compiler2;

import java.io.PrintWriter;
import java.util.ArrayList;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.OutputDialog2;

public class BLayerCompiler {
  public static void insertChemicalIngestionVars(PrintWriter PW, XMLTag tag, String declaration, ArrayList rememberEntries) {
    XMLTag[] vars = tag.getTagsWhere("var","@chemtype",OutputDialog2.DEF_ING_FROM);
    
    for (int i=0; i<vars.length; i++) {
      boolean found=false;
      int j=0;
      
      while ((!found) && (j<rememberEntries.size())) {
        String chem = rememberEntries.get(j++).toString();
        String specFcomp = rememberEntries.get(j++).toString();
        String stageFcomp= rememberEntries.get(j++).toString();
        String tryAll = rememberEntries.get(j++).toString();
        String specBcomp = rememberEntries.get(j++).toString();
        String stageBcomp= rememberEntries.get(j++).toString();
        if ((chem.equals(vars[i].getAttribute("chem"))) &&
            (specFcomp.equals(vars[i].getAttribute("fromspecies"))) &&
            (stageFcomp.equals(vars[i].getAttribute("fromstage"))) &&
            (tryAll.equals(vars[i].getAttribute("byall"))) &&
            (specBcomp.equals(vars[i].getAttribute("byspecies"))) &&
            (stageBcomp.equals(vars[i].getAttribute("bystage")))) found=true;
      }
      
      if (!found) {
        if ((vars[i].getAttribute("byall")!=null) && (vars[i].getAttribute("byall").equals("true"))) {
          PW.print(declaration+" _"+vars[i].getAttribute("chem")+"_from_");
          PW.print(StringTools.nonSpace(vars[i].getAttribute("fromspecies"))+"_");
          PW.println(StringTools.nonSpace(vars[i].getAttribute("fromstage"))+"_by_all = 0.0;");
          rememberEntries.add(vars[i].getAttribute("chem"));
          rememberEntries.add(vars[i].getAttribute("fromspecies"));
          rememberEntries.add(vars[i].getAttribute("fromstage"));
          rememberEntries.add("all");
          rememberEntries.add(" ");
          rememberEntries.add(" ");
        
        } else {
          PW.print(declaration+" _"+vars[i].getAttribute("chem")+"_from_");
          PW.print(StringTools.nonSpace(vars[i].getAttribute("fromspecies"))+"_");
          PW.print(StringTools.nonSpace(vars[i].getAttribute("fromstage"))+"_by_");
          PW.print(StringTools.nonSpace(vars[i].getAttribute("byspecies"))+"_");
          PW.println(StringTools.nonSpace(vars[i].getAttribute("bystage"))+" = 0.0;");
          rememberEntries.add(vars[i].getAttribute("chem"));
          rememberEntries.add(vars[i].getAttribute("fromspecies"));
          rememberEntries.add(vars[i].getAttribute("fromstage"));
          rememberEntries.add("!");
          rememberEntries.add(vars[i].getAttribute("byspecies"));
          rememberEntries.add(vars[i].getAttribute("bystage"));
        }
      }
    }
  
  }
  
  public static void insertChemicalIngestionVars(PrintWriter PW, XMLTag model, String declaration) {
    ArrayList rememberEntries = new ArrayList();
    insertChemicalIngestionVars(PW, model.getTag("output/fieldchem"), declaration, rememberEntries);
    XMLTag[] audits = model.getTag("output").getTags("audit");
    for (int i=0; i<audits.length; i++) 
      insertChemicalIngestionVars(PW,audits[i],declaration,rememberEntries);
    rememberEntries.clear();
  }
  
  public static void insertChemicalIngestionUpdate(PrintWriter PW, XMLTag tag, XMLTag model, ArrayList rememberEntries, String prefix) {
    XMLTag[] vars = tag.getTagsWhere("var","@chemtype",OutputDialog2.DEF_ING_FROM);
    for (int i=0; i<vars.length; i++) {
      boolean found=false;
      int j=0;
      
      while ((!found) && (j<rememberEntries.size())) {
        String chem = rememberEntries.get(j++).toString();
        String specFcomp = rememberEntries.get(j++).toString();
        String stageFcomp= rememberEntries.get(j++).toString();
        String tryAll = rememberEntries.get(j++).toString();
        String specBcomp = rememberEntries.get(j++).toString();
        String stageBcomp= rememberEntries.get(j++).toString();
        if ((chem.equals(vars[i].getAttribute("chem"))) &&
            (specFcomp.equals(vars[i].getAttribute("fromspecies"))) &&
            (stageFcomp.equals(vars[i].getAttribute("fromstage"))) &&
            (tryAll.equals(vars[i].getAttribute("byall"))) &&
            (specBcomp.equals(vars[i].getAttribute("byspecies"))) &&
            (stageBcomp.equals(vars[i].getAttribute("bystage")))) found=true;
      }
      
      if (!found) {
        String chem = vars[i].getAttribute("chem");
        String fromSpecies = vars[i].getAttribute("fromspecies");
        String fromStage = vars[i].getAttribute("fromstage");
        XMLTag[] species = model.getTag("species").getTags("species");
        int fromSpecNo=0;
        while (!species[fromSpecNo].getAttribute("name").equals(fromSpecies)) fromSpecNo++;
        final String fgName = StringTools.nonSpace(species[fromSpecNo].getAttribute("fg"));
        XMLTag[] stages = model.getTagWhere("functionalgroup","name",fgName).getTags("stage");
        int fromStageNo=0;
        while (!stages[fromStageNo].getValue("name").equals(fromStage)) fromStageNo++;
        if ((vars[i].getAttribute("byall")!=null) && (vars[i].getAttribute("byall").equals("true"))) {
          PW.println("                if ((type=="+fromSpecNo+") && (stageID=="+fromStageNo+"))");
          PW.print("                  "+prefix+"_");
          PW.print(StringTools.nonSpace(chem)+"_from_");
          PW.print(StringTools.nonSpace(fromSpecies)+"_");
          PW.print(StringTools.nonSpace(fromStage)+"_by_all+=delta*(");
          PW.print("(("+fgName+")prey)._"+chem+"_Ing+");
          PW.print("(("+fgName+")prey)._"+chem+"_Pool_Old);");
          rememberEntries.add(vars[i].getAttribute("chem"));
          rememberEntries.add(vars[i].getAttribute("fromspecies"));
          rememberEntries.add(vars[i].getAttribute("fromstage"));
          rememberEntries.add("all");
          rememberEntries.add(" ");
          rememberEntries.add(" ");
          
        
        } else {
          String bySpecies = vars[i].getAttribute("byspecies");
          String byStage = vars[i].getAttribute("bystage");
          int bySpecNo=0;
          while (!species[bySpecNo].getAttribute("name").equals(bySpecies)) bySpecNo++;
          stages = model.getTagWhere("functionalgroup","name",species[bySpecNo].getAttribute("fg")).getTags("stage");
          int byStageNo=0;
          while (!stages[byStageNo].getValue("name").equals(byStage)) byStageNo++;
          PW.println("                if ((type=="+fromSpecNo+") && (stageID=="+fromStageNo+")");
          PW.println("                 && (rec.consumer.getParams()._type=="+bySpecNo+") && (rec.consumer._CurrentStage=="+byStageNo+"))");
          PW.print("                  "+prefix+"_"+vars[i].getAttribute("chem")+"_from_");
          PW.print(StringTools.nonSpace(vars[i].getAttribute("fromspecies"))+"_");
          PW.print(StringTools.nonSpace(vars[i].getAttribute("fromstage"))+"_by_");
          PW.print(StringTools.nonSpace(vars[i].getAttribute("byspecies"))+"_");
          PW.print(StringTools.nonSpace(vars[i].getAttribute("bystage"))+" += delta*(");
          PW.print("(("+fgName+")prey)._"+chem+"_Ing+");
          PW.print("(("+fgName+")prey)._"+chem+"_Pool_Old);");
          rememberEntries.add(vars[i].getAttribute("chem"));
          rememberEntries.add(vars[i].getAttribute("fromspecies"));
          rememberEntries.add(vars[i].getAttribute("fromstage"));
          rememberEntries.add("!");
          rememberEntries.add(vars[i].getAttribute("byspecies"));
          rememberEntries.add(vars[i].getAttribute("bystage"));
        }
      }
    }
  }
  
  public static void insertChemicalUpdates(PrintWriter PW, XMLTag model) {
    ArrayList rememberEntries = new ArrayList();
    insertChemicalIngestionUpdate(PW, model.getTag("output/col"),model,rememberEntries,"Kernel.W.");
    rememberEntries.clear();
    insertChemicalIngestionUpdate(PW, model.getTag("output/fieldchem"),model, rememberEntries,"");
    
    XMLTag[] audits = model.getTag("output").getTags("audit");
    for (int i=0; i<audits.length; i++) 
      insertChemicalIngestionUpdate(PW, audits[i],model, rememberEntries,"");
    rememberEntries.clear();
  }
  
  public static void writeBLayerJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("");
      PW.println("import java.io.DataInputStream;");
      PW.println("import java.io.DataOutputStream;");
      PW.println("");
      PW.println("import VEW.Sim.FunctionalGroup;");
      PW.println("import VEW.Sim.Kernel;");
      PW.println("");
      PW.println("public class BLayer {");
      PW.println("  public double Depth; // The depth of the top of the layer.");
      PW.println("  public double Height; // The height of the layer.");
      PW.println("");
      insertChemicalIngestionVars(PW,model,"  public double ");
      PW.println("");
      final int chemCount = model.getTags("chemical").length;
      PW.println("  public Chemical[] Chemicals = new Chemical["+chemCount+"];");
      PW.println("  public double[] released = new double["+chemCount+"];");
      PW.println("  public double[] released_track = new double["+chemCount+"];");
      PW.println("");
      final XMLTag[] chems = model.getTags("chemical");
      for (int i=0; i<chems.length; i++) {
        final String chem = StringTools.nonSpace(chems[i].getValue("name"));
        if (BlackBox.chemInUse(model,null,chem,"uptake")) { 
          PW.println("  public double request_"+chem+"=0.0;");
          PW.println("  public double deplete_"+chem+"=0.0;");
        }
      }
      PW.println("");
      final int speciesCount = model.getTag("species").getTags("species").length;
      PW.println("  public double[] particulate_chem = new double["+chemCount+"]; // Total particulate chemical");
      PW.println("  public double[][][] particulate_chem_ss = new double["+chemCount+"]["+speciesCount+"][]; // Total particulate chemical,species,stage");
      PW.println("  public double[][][] particulate_chem_ss_old = new double["+chemCount+"]["+speciesCount+"][]; // Total particulate chemical,species,stage");
      PW.println("  public double[][] SpeciesConcentration = new double["+speciesCount+"][]; // [Species ID][Stage]");
      PW.println("  public double[][] SpeciesConcentration_old = new double["+speciesCount+"][]; // Previous timstep[Species ID][Stage]");
      PW.println("  public int[][] AgentCounts = new int["+speciesCount+"][]; // [Species ID][Stage]");
      
      PW.println("  public double[][][] SpeciesStateDemography = new double["+speciesCount+"][][]; // [Species ID][Stage][Reason]");
      PW.println("");
      PW.println("  public double[] solution_chem = new double["+chemCount+"]; // Total solution chemical");
      PW.println("");
      PW.println("  public double[][] ingest_request = new double["+speciesCount+"][]; // Ingestion requests (species,stage)");
      PW.println("  public double[][] ingest_deplete = new double["+speciesCount+"][]; // Depletion proportion for ingestion");
      PW.println("  public double[][] ingested_prop = new double["+speciesCount+"][]; // Actual proportion of available stock ingested");
      PW.println("");
      PW.println("  public void resetIngestionVars() {");
      insertChemicalIngestionVars(PW,model,"    ");
      PW.println("  }");
      PW.println("");
      PW.println("  public BLayer(int LayerDepth, int LayerHeight) {");
      PW.println("    Chemicals = SysVars.getNewChemicalArray(this);");
      PW.println("");
      PW.println("    // Initialise properties of the layer.");
      PW.println("    Depth = LayerDepth;");
      PW.println("    Height = LayerHeight;");
      PW.println("");
      PW.println("    // Initialise the concentrations.");
      PW.println("");
      PW.println("    for (int speciesNo=0; speciesNo<"+speciesCount+"; speciesNo++) {");
      PW.println("      FunctionalGroup fg = Kernel.getSpecies(speciesNo);");
      PW.println("      final int stageCount = fg.getStageCount();");
      PW.println("      SpeciesConcentration[speciesNo] = new double[stageCount];");
      PW.println("      SpeciesConcentration_old[speciesNo] = new double[stageCount];");      
      PW.println("      AgentCounts[speciesNo] = new int[stageCount];");
      PW.println("      ingest_deplete[speciesNo] = new double[stageCount];");
      PW.println("      ingested_prop[speciesNo] = new double[stageCount];");
      PW.println("      ingest_request[speciesNo] = new double[stageCount];");
      PW.println("      for (int chemNo=0; chemNo<"+chemCount+"; chemNo++) {");
      PW.println("        particulate_chem_ss[chemNo][speciesNo]=new double[stageCount];");
      PW.println("        particulate_chem_ss_old[chemNo][speciesNo]=new double[stageCount];");      
      PW.println("      }");
      PW.println("      SpeciesStateDemography[speciesNo] = new double[fg.getStageCount() +1][SysVars.countDeathReasons()];");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
      PW.println("  public void ChemicalDepletion() {");
      for (int i=0; i<chems.length; i++) {
        final String chem=StringTools.nonSpace(chems[i].getValue("name"));
        if (BlackBox.chemInUse(model, null, chem, "uptake")) {
          PW.println("    if (request_"+chem+">0) {");
          PW.println("      if (request_"+chem+">solution_chem["+chem+".ID_"+chem+"]) {");
          PW.println("        deplete_"+chem+" = solution_chem["+chem+".ID_"+chem+"]/request_"+chem+";");
          PW.println("        solution_chem["+chem+".ID_"+chem+"]=0.0;");
          PW.println("      } else {");
          PW.println("        deplete_"+chem+" = 1.0;");
          PW.println("        solution_chem["+chem+".ID_"+chem+"]=Math.max(0,solution_chem["+chem+".ID_"+chem+"]-request_"+chem+");");
          PW.println("      }");
          PW.println("      request_"+chem+"=0;");
          PW.println("    }");
        }
        PW.println("    solution_chem["+chem+".ID_"+chem+"]+=released["+chem+".ID_"+chem+"];");
        PW.println("    released["+chem+".ID_"+chem+"]=0.0;");
      }
      PW.println("    ");
      PW.println("  }");
      
      PW.println("  public void handleIngestion() {");
      PW.println("    final double max_proportion = 0.999;");
      PW.println("    for (int species=0; species<"+speciesCount+"; species++) {");
      PW.println("      final FunctionalGroup fg = Kernel.getSpecies(species);");
      PW.println("      final int stageCount = fg.getStageCount();");
      PW.println("      for (int stage=0; stage<stageCount; stage++) {");
      PW.println("        if (ingest_request[species][stage]>0) {");
      PW.println("          final double available = SpeciesConcentration_old[species][stage]*max_proportion;");
      PW.println("          if (ingest_request[species][stage]>available) {");
      PW.println("            ingest_deplete[species][stage]=available/ingest_request[species][stage];");
      PW.println("            ingested_prop[species][stage]=max_proportion;");
      PW.println("          } else {");
      PW.println("            ingest_deplete[species][stage]=1.0;");
      PW.println("            ingested_prop[species][stage]=ingest_request[species][stage]/SpeciesConcentration_old[species][stage];");
      PW.println("          }");
      PW.println("          ingest_request[species][stage]=0;");
      PW.println("        } else ingested_prop[species][stage]=0.0;");
      PW.println("      }");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
      PW.println("  public void updateLayer() {");
      PW.println("    handleIngestion();");
      PW.println("    ChemicalDepletion();");
      PW.println("  }");
      PW.println("");
      PW.println("  public void updateChemistry() {");
      PW.println("    for (int Counter = 0; Counter < Chemicals.length; Counter++)");
      PW.println("      Chemicals[Counter].update();");
      PW.println("  }");
      PW.println("");
      PW.println("  public void updateChemistry(double[] ChemicalTotals) {");
      PW.println("    for (int chem = 0; chem < Chemicals.length; chem++) {");
      PW.println("      Chemicals[chem].update();");
      PW.println("      ChemicalTotals[chem] += solution_chem[chem];");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setChemistry(double[] ChemicalTotals) {");
      PW.println("    for (int chemNo=0; chemNo<"+chems.length+"; chemNo++)");
      PW.println("      solution_chem[chemNo]=ChemicalTotals[chemNo];");
      PW.println("  }");
      PW.println("");
      PW.println(" public void resetStats() {");
      PW.println("   for (int i=0; i<"+chemCount+"; i++) particulate_chem[i]=0;");
      PW.println("   for (int speciesNo=0; speciesNo<"+speciesCount+"; speciesNo++) {");
      PW.println("      for (int stage=0; stage<SpeciesConcentration[speciesNo].length; stage++) {");
      PW.println("        SpeciesConcentration_old[speciesNo][stage]=SpeciesConcentration[speciesNo][stage];");
      PW.println("        SpeciesConcentration[speciesNo][stage]=0;");
      PW.println("        for (int i=0; i<"+chemCount+"; i++) {");
      PW.println("          particulate_chem_ss_old[i][speciesNo][stage]=particulate_chem_ss[i][speciesNo][stage];");
      PW.println("          particulate_chem_ss[i][speciesNo][stage]=0;");
      PW.println("        }");
      PW.println("        for (int reason=0; reason<SpeciesStateDemography[speciesNo][stage].length; reason++) {");
      PW.println("          SpeciesStateDemography[speciesNo][stage][reason]=0;");
      PW.println("          AgentCounts[speciesNo][stage]=0;");
      PW.println("        }");
      PW.println("      }");
      PW.println("    }");
      PW.println("    resetIngestionVars();");
      PW.println("  }");
      PW.println("");
      PW.println("  public void writeChemicalsToSnapshot(DataOutputStream snapshot) {");
      PW.println("    try {");
      PW.println("      for (int chem = 0; chem < Chemicals.length; chem++)");
      PW.println("        Chemicals[chem].writeSnapshot(snapshot);");
      PW.println("    } catch (Exception e) { e.printStackTrace(); }");
      PW.println("  }");
      PW.println("");
      PW.println("  public void readChemicalsFromSnapshot(DataInputStream snapshot) {");
      PW.println("    try {");
      PW.println("      Chemicals = SysVars.getNewChemicalArray(this);");
      PW.println("      for (int chem = 0; chem < Chemicals.length; chem++) {");
      PW.println("        Chemicals[chem].readSnapshot(snapshot);");
      PW.println("      }");
      PW.println("    } catch (Exception e) { e.printStackTrace(); }");
      PW.println("  }");
      PW.println("");
      PW.println("  public void writeStatsToSnapshot(DataOutputStream snapshot) {");
      PW.println("    try {");
      PW.println("      for (int i=0; i<released.length; i++) snapshot.writeDouble(released[i]);");
      for (int i=0; i<chems.length; i++) {
        final String chem = StringTools.nonSpace(chems[i].getValue("name"));
        if (BlackBox.chemInUse(model,null,chem,"uptake")) { 
          PW.println("      snapshot.writeDouble(request_"+chem+");");
          PW.println("      snapshot.writeDouble(deplete_"+chem+");");
        }
      }
      PW.println("      for (int i=0; i<released_track.length; i++) snapshot.writeDouble(released_track[i]);");
      PW.println("      for (int i=0; i<solution_chem.length; i++) snapshot.writeDouble(solution_chem[i]);");
      PW.println("      for (int i=0; i<particulate_chem.length; i++) snapshot.writeDouble(particulate_chem[i]);");
      PW.println("      for (int i=0; i<particulate_chem_ss.length; i++)");
      PW.println("        for (int j=0; j<particulate_chem_ss[i].length; j++)");
      PW.println("          for (int k=0; k<particulate_chem_ss[i][j].length; k++)");
      PW.println("            snapshot.writeDouble(particulate_chem_ss[i][j][k]);");
      PW.println("      for (int i=0; i<particulate_chem_ss_old.length; i++)");
      PW.println("        for (int j=0; j<particulate_chem_ss_old[i].length; j++)");
      PW.println("          for (int k=0; k<particulate_chem_ss_old[i][j].length; k++)");
      PW.println("            snapshot.writeDouble(particulate_chem_ss_old[i][j][k]);");
      PW.println("      for (int i=0; i<SpeciesConcentration.length; i++)");
      PW.println("        for (int j=0; j<SpeciesConcentration[i].length; j++)");
      PW.println("          snapshot.writeDouble(SpeciesConcentration[i][j]);");
      PW.println("      for (int i=0; i<SpeciesConcentration_old.length; i++)");
      PW.println("        for (int j=0; j<SpeciesConcentration_old[i].length; j++)");
      PW.println("          snapshot.writeDouble(SpeciesConcentration_old[i][j]);");
      PW.println("      for (int i=0; i<AgentCounts.length; i++)");
      PW.println("        for (int j=0; j<AgentCounts[i].length; j++)");
      PW.println("          snapshot.writeInt(AgentCounts[i][j]);");
      PW.println("      for (int i=0; i<SpeciesStateDemography.length; i++)");
      PW.println("        for (int j=0; j<SpeciesStateDemography[i].length; j++)");
      PW.println("          for (int k=0; k<SpeciesStateDemography[i][j].length; k++)");
      PW.println("            snapshot.writeDouble(SpeciesStateDemography[i][j][k]);");
      PW.println("      for (int i=0; i<ingest_request.length; i++)");
      PW.println("        for (int j=0; j<ingest_request[i].length; j++)");
      PW.println("          snapshot.writeDouble(ingest_request[i][j]);");
      PW.println("      for (int i=0; i<ingest_deplete.length; i++)");
      PW.println("        for (int j=0; j<ingest_deplete[i].length; j++)");
      PW.println("          snapshot.writeDouble(ingest_deplete[i][j]);");
      PW.println("      for (int i=0; i<ingested_prop.length; i++)");
      PW.println("        for (int j=0; j<ingested_prop[i].length; j++)");
      PW.println("          snapshot.writeDouble(ingested_prop[i][j]);");
      PW.println("    } catch (Exception e) {e.printStackTrace(); }");
      PW.println("  }");
      PW.println("");
      PW.println("  public void readStatsFromSnapshot(DataInputStream snapshot) {");
      PW.println("    try {");
      PW.println("      for (int i=0; i<released.length; i++) released[i] = snapshot.readDouble();");
      for (int i=0; i<chems.length; i++) {
        final String chem = StringTools.nonSpace(chems[i].getValue("name"));
        if (BlackBox.chemInUse(model,null,chem,"uptake")) { 
          PW.println("      request_"+chem+"=snapshot.readDouble();");
          PW.println("      deplete_"+chem+"=snapshot.readDouble();");
        }
      }
      PW.println("      for (int i=0; i<released_track.length; i++) released_track[i]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<solution_chem.length; i++) solution_chem[i]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<particulate_chem.length; i++) particulate_chem[i]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<particulate_chem_ss.length; i++)");
      PW.println("        for (int j=0; j<particulate_chem_ss[i].length; j++)");
      PW.println("          for (int k=0; k<particulate_chem_ss[i][j].length; k++)");
      PW.println("            particulate_chem_ss[i][j][k]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<particulate_chem_ss_old.length; i++)");
      PW.println("        for (int j=0; j<particulate_chem_ss_old[i].length; j++)");
      PW.println("          for (int k=0; k<particulate_chem_ss_old[i][j].length; k++)");
      PW.println("            particulate_chem_ss_old[i][j][k]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<SpeciesConcentration.length; i++)");
      PW.println("        for (int j=0; j<SpeciesConcentration[i].length; j++)");
      PW.println("          SpeciesConcentration[i][j]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<SpeciesConcentration_old.length; i++)");
      PW.println("        for (int j=0; j<SpeciesConcentration_old[i].length; j++)");
      PW.println("          SpeciesConcentration_old[i][j]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<AgentCounts.length; i++)");
      PW.println("        for (int j=0; j<AgentCounts[i].length; j++)");
      PW.println("          AgentCounts[i][j]=snapshot.readInt();");
      PW.println("      for (int i=0; i<SpeciesStateDemography.length; i++)");
      PW.println("        for (int j=0; j<SpeciesStateDemography[i].length; j++)");
      PW.println("          for (int k=0; k<SpeciesStateDemography[i][j].length; k++)");
      PW.println("            SpeciesStateDemography[i][j][k]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<ingest_request.length; i++)");
      PW.println("        for (int j=0; j<ingest_request[i].length; j++)");
      PW.println("          ingest_request[i][j]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<ingest_deplete.length; i++)");
      PW.println("        for (int j=0; j<ingest_deplete[i].length; j++)");
      PW.println("          ingest_deplete[i][j]=snapshot.readDouble();");
      PW.println("      for (int i=0; i<ingested_prop.length; i++)");
      PW.println("        for (int j=0; j<ingested_prop[i].length; j++)");
      PW.println("          ingested_prop[i][j]=snapshot.readDouble();");
      PW.println("    } catch (Exception e) {e.printStackTrace(); }");
      PW.println("  }");
      PW.println("");
      PW.println("}");
      PW.flush();
      PW.close();
    } catch (Exception e) { e.printStackTrace(); }
  }
}
