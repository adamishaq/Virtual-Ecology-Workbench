package VEW.Compiler2;

import java.io.PrintWriter;
import java.util.ArrayList;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.OutputDialog2;

public class AuditTrailCompiler {
  
  public final static int STATE_VARIABLE = 0;
  public final static int AMBIENT_BIO = 1;
  public final static int AMBIENT_CHEM = 2;
  public final static int AMBIENT_DEMOGRAPHIC = 3;  
  public final static int AMBIENT_PHY = 4;
  public final static int LOCAL = 5;
  public final static int VBASED = 6;  
  public final static int DEFINITIONS = 100;
  public final static int LOGGING = 101;  
  
  public static int getDeathReason(String fg, String s, ArrayList deathReasons) {
    int i=0;
    boolean found=false;
    while ((!found) && (i<deathReasons.size())) {
      found=(deathReasons.get(i).toString().equals(fg+":"+s));
      if (!found) i++;
    }
    if (!found) System.out.println("Not found "+fg+":"+s);
    return i;
  }
  
    
  public static void writeAuditInit(XMLTag oo, PrintWriter PW, XMLTag fg, ArrayList deathReasons, XMLTag theModel) {
    PW.println("  public static void _initialiseAuditOutput() {");
    PW.println("    XMLTag _fg;");
    PW.println("    XMLTag o;");
    //String speciesName = StringTools.nonSpace(speciesText);
    XMLTag[] species = theModel.getTag("species").getTags("species");
    boolean found=false;
    for (int i=0; i<species.length; i++) {
      XMLTag speciesTag = theModel.getTag("species").getTags("species")[i];
      String speciesText = speciesTag.getAttribute("name");
      XMLTag theAuditLog = oo.getTagWhere("audit","@species",speciesText);
      XMLTag[] theVars = theAuditLog.getTags("var");
      if (theVars.length>0) {
        found=true;
        i=species.length;
      }
    }
    if (found) PW.println("    XMLTag _var;");
    for (int i=0; i<species.length; i++) {
      XMLTag speciesTag = theModel.getTag("species").getTags("species")[i];
      if (speciesTag.getAttribute("fg").equals(fg.getValue("name"))) {
        String speciesText = speciesTag.getAttribute("name");
        String speciesName = StringTools.nonSpace(speciesText);
          PW.println("    _fg = new XMLTag(\"functionalgroup\");");
          PW.println("    _fg.addTag(new XMLTag(\"data\",\"fg_"+speciesName+".bin\"));");
          PW.println("    Logging.setFgFile("+String.valueOf(i)+",Launcher.JarPath+File.separator+\"fg_"+speciesName+".bin\");");
          PW.println("    Logging.setFgTime("+String.valueOf(i)+",Launcher.JarPath+File.separator+\"fg_"+speciesName+"_time.bin\");");
          if ((oo.getTag("lineage").getAttribute("active").equals("true"))) {
            PW.println("    Logging.setLineage("+String.valueOf(i)+",\"lineage_"+speciesName+".bin\");");
            PW.println("    _fg.addTag(new XMLTag(\"lineage\",\"lineage_"+speciesName+".bin\"));");
          }
          if ((oo.getTag("lifespan").getAttribute("active").equals("true"))) {
            PW.println("    Logging.setLifespan("+String.valueOf(i)+",\"lifespan_"+speciesName+".bin\");");
            PW.println("    _fg.addTag(new XMLTag(\"lifespan\",\"lifespan_"+speciesName+".bin\"));");
          }
          PW.println("    _fg.addTag(new XMLTag(\"name\",\""+speciesText+"\"));");   
          PW.println("    _fg.addTag(new XMLTag(\"time\",\"fg_"+speciesName+"_time.bin\"));");
          PW.println("    _fg.addTag(new XMLTag(\"type\",\""+String.valueOf(i)+"\"));");
          PW.println("    _fg.addTag(new XMLTag(\"zip\",\"true\"));"); 
      
          PW.println("    o = new XMLTag(\"output\");");
          XMLTag ooSpecific = oo.getTagWhere("audit","@species",speciesText);
          PW.println("    o.addTag(new XMLTag(\"after\",String.valueOf(SysVars._firstExecTime)));");
          PW.println("    o.addTag(new XMLTag(\"freq\",\""+ooSpecific.getAttribute("freq")+"\"));");
          PW.println("    o.addTag(new XMLTag(\"to\",\""+ooSpecific.getAttribute("to")+"\"));");
          PW.println("    _fg.addTag(o);");
          handleVariables(DEFINITIONS,oo,PW,i,deathReasons,theModel);
          PW.println("    Logging.dataFormats.addTag(_fg);");
          PW.println("    Logging.dataFormats.write();");
        
      }
    }
    PW.println("  }");
  }
  
  public static int writeVarEntry(PrintWriter PW, String name, String type, int varID) {
    PW.println("    _var = new XMLTag(\"var\");");
    PW.println("    _var.addTag(new XMLTag(\"id\",\""+(varID++)+"\"));");
    PW.println("    _var.addTag(new XMLTag(\"name\",\""+StringTools.nonSpace(name)+"\"));");
    PW.println("    _var.addTag(new XMLTag(\"desc\",\""+name+"\"));");    
    PW.println("    _var.addTag(new XMLTag(\"type\",\""+type+"\"));");
    PW.println("    _fg.addTag(_var);");
    return varID;
  }    
  
  public static XMLTag getFGForSpeciesNo(int i, XMLTag model) {
    return model.getTagWhere("functionalgroup","name",getSpeciesNo(i,model).getAttribute("fg"));
  }
  
  public static XMLTag getSpeciesNo(int i, XMLTag model) {
    return model.getTag("species").getTagWhere("species","@id",String.valueOf(i));
  }
  
  public static XMLTag getSpecies(String species, XMLTag model) {
    return model.getTag("species").getTagWhere("species","@name",species);
  }
  
  public static XMLTag getFGForSpecies(String species, XMLTag model) {
    return model.getTagWhere("functionalgroup","name",getSpecies(species,model).getAttribute("fg"));
  }
  
  public static void handleVariables(int mode, XMLTag oo, PrintWriter PW, int speciesNo, ArrayList deathReasons, XMLTag theModel) {
    int varID = 4;
    XMLTag speciesTag = theModel.getTag("species").getTags("species")[speciesNo];
    String speciesText = speciesTag.getAttribute("name");
    //String speciesName = StringTools.nonSpace(speciesText);
    int auditSpeciesNo = speciesNo;
    XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg"));
    XMLTag theAuditLog = oo.getTagWhere("audit","@species",speciesText);
    
    XMLTag[] theVars = theAuditLog.getTags("var");
    if (theVars.length>0) {
      if (mode==DEFINITIONS) {
        PW.println("    _var = new XMLTag(\"var\");");
        PW.println("    _var.addTag(new XMLTag(\"id\",\"1\"));");
        PW.println("    _var.addTag(new XMLTag(\"name\",\"TimeStep\"));");
        PW.println("    _var.addTag(new XMLTag(\"type\",\"long\"));");
        PW.println("    _fg.addTag(_var);");
        PW.println("    _var = new XMLTag(\"var\");");
        PW.println("    _var.addTag(new XMLTag(\"id\",\"2\"));");
        PW.println("    _var.addTag(new XMLTag(\"name\",\"id\"));");
        PW.println("    _var.addTag(new XMLTag(\"type\",\"long\"));");
        PW.println("    _fg.addTag(_var);");
        PW.println("    _var = new XMLTag(\"var\");");
        PW.println("    _var.addTag(new XMLTag(\"id\",\"3\"));");
        PW.println("    _var.addTag(new XMLTag(\"name\",\"Stage\"));");
        PW.println("    _var.addTag(new XMLTag(\"desc\",\"State\"));");        
        PW.println("    _var.addTag(new XMLTag(\"type\",\"int\"));");
        PW.println("    _fg.addTag(_var);");
      } else {
        long auditFreq = SysVarsCompiler.getFreqTS(theModel,theAuditLog);
        PW.println("    if ((log) && (SysVars._stageLogs[params._type][_CurrentStage]) && (Logging.isTime("+auditFreq+"L,"+theAuditLog.getAttribute("from")+"L,"+theAuditLog.getAttribute("to")+"L))) {");
        PW.println("      Logging.writeFGLong("+String.valueOf(auditSpeciesNo)+",Kernel.myTime);");
        PW.println("      Logging.writeFGLong("+String.valueOf(auditSpeciesNo)+",id);");
        PW.println("      Logging.writeFGInt("+String.valueOf(auditSpeciesNo)+",_CurrentStage);");
      }
      for (int i=0; i<theVars.length; i++) {
        XMLTag var = theVars[i];
        String varName = var.getAttribute("name");
        String scope = var.getAttribute("scope");
        
        if (scope.equals(OutputDialog2.SCOPE_AT_AGENT)) {
          String realVar = varName.substring(0,varName.indexOf("("));
          if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+","+realVar+"[1]);");
          else varID=writeVarEntry(PW, realVar,"real", varID);
                    
        } else if (scope.equals(OutputDialog2.SCOPE_AT_INCHEMICAL)) {
          String chemicalName = var.getAttribute("chem");
          String poolOrGain = var.getAttribute("chemtype");
   
          if (poolOrGain.equals("pool")) {
            if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",_"+chemicalName+"_Pool_Old);");
            else varID=writeVarEntry(PW,varName,"real",varID);
          } else if (poolOrGain.equals("gain")) {
            if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",_"+chemicalName+"_Ing);");
            else varID=writeVarEntry(PW,varName,"real",varID);
          }
        
        } else if (scope.equals(OutputDialog2.SCOPE_AT_BIOLOGICAL)) { // Always a concentration
          final String species = var.getAttribute("species");
          final String stage = var.getAttribute("stage");
          final XMLTag[] speciesTags = theModel.getTag("species").getTags("species");
          int targetSpeciesNo=0;
          while (!speciesTags[targetSpeciesNo].getAttribute("name").equals(species)) targetSpeciesNo++;
          final XMLTag[] stages = theModel.getTagWhere("functionalgroup","name",speciesTags[targetSpeciesNo].getAttribute("fg")).getTags("stage");
          int targetStageNo=0;
          while (!stages[targetStageNo].getValue("name").equals(stage)) targetStageNo++;
          if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",Utils.getStar(blayer,"+targetSpeciesNo+", "+targetStageNo+"));");
          else varID=writeVarEntry(PW, varName,"real",varID);

        } else if (scope.equals(OutputDialog2.SCOPE_AT_CHEMICAL)) {
          final String chemicalName = var.getAttribute("chem");
          final String chemType = var.getAttribute("chemtype");
          final XMLTag[] chems = theModel.getTags("chemical");
          int targetChem=0;
          while (!chems[targetChem].getValue("name").equals(chemicalName)) targetChem++;

          if (chemType.equals(OutputDialog2.DEF_SOLN)) {
            if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",blayer.solution_chem["+targetChem+"]);");
            else varID=writeVarEntry(PW,varName,"real",varID);
          } else if (chemType.equals(OutputDialog2.DEF_BIOCONC)) {
            if ((var.getAttribute("species")!=null) && (var.getAttribute("stage")!=null)) {
              if (mode==LOGGING) {
                final String chemName = var.getAttribute("chem");
                final String species = var.getAttribute("species");
                final String stage = var.getAttribute("stage");
                final String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
                int specNo=0;
                while (!theModel.getTag("species").getTags()[specNo].getAttribute("name").equals(species)) specNo++;
                int stageNo=0;
                while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[stageNo].getValue("name").equals(stage)) stageNo++;
                PW.println("        Logging.writeFGReal("+auditSpeciesNo+",blayer.sumFGPool_"+chemName+"("+specNo+","+stageNo+"));");
              } else varID=writeVarEntry(PW,varName,"real",varID); 
            } else {
              final String chemName = var.getAttribute("chem");
              if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",blayer.sumFGPool_"+chemName+"());");
              else varID=writeVarEntry(PW,varName,"real",varID);
            }
          } else if (chemType.equals(OutputDialog2.DEF_ING_BY)) { 
            if (mode==LOGGING) {
              if ((var.getAttribute("stage")==null) && (var.getAttribute("species")==null)) {
                PW.println("        Logging.writeFGReal("+auditSpeciesNo+",blayer.sumFGIng_"+chemicalName+"();");
              } else {
                String byStage = var.getAttribute("stage");
                String bySpecies = var.getAttribute("species");
                int bySpecNo=0;
                while (!theModel.getTag("species").getTags()[bySpecNo].getAttribute("name").equals(bySpecies)) bySpecNo++;
                String fg = theModel.getTag("species").getTagWhere("species","@name",bySpecies).getAttribute("fg");
                int fromStageNo=0;
                while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[fromStageNo].getValue("name").equals(byStage)) fromStageNo++;
              }
            } else varID=writeVarEntry(PW,varName,"real",varID);
            
          } else if (chemType.equals(OutputDialog2.DEF_ING_FROM)) {
            if (mode==LOGGING) {
              final String fromSpecies = StringTools.nonSpace(var.getAttribute("fromspecies"));
              final String fromStage = StringTools.nonSpace(var.getAttribute("fromstage"));
              if ((var.getAttribute("byall")!=null) && (var.getAttribute("byall").equals("true"))) {
                PW.print("        Logging.writeFGReal("+auditSpeciesNo+",blayer._"+chemicalName+"_from_");
                PW.println(fromSpecies+"_"+fromStage+"_by_all);");
              
              } else {
                final String bySpecies = StringTools.nonSpace(var.getAttribute("byspecies"));
                final String byStage = StringTools.nonSpace(var.getAttribute("bystage"));
                PW.print("        Logging.writeFGReal("+auditSpeciesNo+",blayer._"+chemicalName+"_from_");
                PW.println(fromSpecies+"_"+fromStage+"_by_"+bySpecies+"_"+byStage+");");
              }
              
            } else varID=writeVarEntry(PW,varName,"real",varID);
              
          }
        } else if (scope.equals(OutputDialog2.SCOPE_AT_DEMOGRAPHIC)) {
          String species = var.getAttribute("species");
          String stage = var.getAttribute("stage");
          XMLTag[] speciesTags = theModel.getTag("species").getTags("species");
          int targetSpeciesNo=0;
          while (!speciesTags[targetSpeciesNo].getAttribute("name").equals(species)) targetSpeciesNo++;
          XMLTag[] stages = theModel.getTagWhere("functionalgroup","name",speciesTags[targetSpeciesNo].getAttribute("fg")).getTags("stage");
          int targetStageNo=0;
          while (!stages[targetStageNo].getValue("name").equals(stage)) targetStageNo++;

          if (varName.startsWith(OutputDialog2.AGENT_NO_AGENTS+" : ")) {
            if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",blayer.AgentCounts["+targetSpeciesNo+"]["+targetStageNo+"]);");
            else varID=writeVarEntry(PW, varName,"real",varID);
          
          } else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_ALL+" : ")) {
            if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",blayer.SpeciesStateDemography["+targetSpeciesNo+"]["+targetStageNo+"][SysVars.ingestionAll["+targetSpeciesNo+"]]);");
            else varID=writeVarEntry(PW, varName,"real",varID);
          
          } else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_BY)) {
            if (mode==LOGGING) {
              String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
              int specNo=0;
              while (!theModel.getTag("species").getTags()[specNo].getAttribute("name").equals(species)) specNo++;
              int stageNo=0;
              while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[stageNo].getValue("name").equals(stage)) stageNo++;
              String predator = varName.substring(OutputDialog2.AGENT_BEING_INGESTED_BY.length()+3);
              final String predatorSpecies = predator.substring(0,predator.indexOf(":")-1);
              predator = predator.substring(predator.indexOf(":")+2);
              final String predatorStage = predator.substring(0,predator.indexOf(":")-1);
              predator = predator.substring(predator.indexOf(":")+2);
              final String preySpecies = predator.substring(0,predator.indexOf(":")-1);
              PW.print("      Logging.writeFGReal("+auditSpeciesNo+",blayer.SpeciesStateDemography["+specNo+"]["+stageNo+"][");
              PW.print("SysVars._DR_"+StringTools.nonSpace(preySpecies)+"_");
              PW.print(StringTools.nonSpace(OutputDialog2.AGENT_BEING_INGESTED_BY));
              PW.print(StringTools.nonSpace(predatorSpecies)+"_");
              PW.print(StringTools.nonSpace(predatorStage)+"]);");
            } else varID=writeVarEntry(PW, varName,"real",varID);

          } else {
            String reason = varName.substring(0,varName.indexOf(":")-1).trim();
            String fgName = theModel.getTag("species").getTagWhere("species","@name",var.getAttribute("species")).getAttribute("fg");
            int reasonNo = getDeathReason(fgName,reason,deathReasons);
            if (reasonNo==-1) System.out.println("Error getting death reasons");
            else {
             if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",blayer.SpeciesStateDemography["+targetSpeciesNo+"]["+targetStageNo+"]["+reasonNo+"]);");
             else varID=writeVarEntry(PW, varName,"real",varID);
            }
          }
          
        } else if (scope.equals(OutputDialog2.SCOPE_AT_PHYSICAL)) {
          if (mode==LOGGING) {
            if (varName.equals(OutputDialog2.DENSITY)) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",Utils.getDensity(z[1]));");
            else if (varName.equals(OutputDialog2.FULL_IRRADIANCE)) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",Utils.getFullIrrad(z[1]));");
            else if (varName.equals(OutputDialog2.SALINITY)) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",Utils.getSalinity(z[1]));");
            else if (varName.equals(OutputDialog2.TEMPERATURE)) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",Utils.getPureTemperature(z[1]));");
            else if (varName.equals(OutputDialog2.TEMPADJ)) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",Utils.getTemperature(z[1]));");            
            else if (varName.equals(OutputDialog2.VISIBLE_IRRADIANCE)) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",Utils.getVisIrrad(z[1]));");
          } else varID=writeVarEntry(PW, varName, "real",varID);
           
        } else if (scope.equals(OutputDialog2.SCOPE_AT_LOCAL)) {
          if (varName.indexOf("(")>=0) varName = varName.substring(0,varName.indexOf("(")-1).trim();          
          XMLTag findVar = fgTag.getTagWhere("local","name",varName);
          if (findVar!=null) {
            if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+","+findVar.getValue("name")+");");
            else varID=writeVarEntry(PW,findVar.getValue("name"),"real",varID);
          }
        
        } else if (scope.equals(OutputDialog2.SCOPE_AT_VARIETYBASED))  {
          if (varName.indexOf("(")>=0) varName = varName.substring(0,varName.indexOf("(")-1).trim();          
          String species = var.getAttribute("species");
          String stage = var.getAttribute("stage");
          XMLTag[] speciesTags = theModel.getTag("species").getTags("species");
          int targetSpeciesNo=0;
          while (!speciesTags[targetSpeciesNo].getAttribute("name").equals(species)) targetSpeciesNo++;
          XMLTag[] stages = theModel.getTagWhere("functionalgroup","name",speciesTags[targetSpeciesNo].getAttribute("fg")).getTags("stage");
          int targetStageNo=0;
          while (!stages[targetStageNo].getValue("name").equals(stage)) targetStageNo++;

          String link = "";
          boolean variableFlag=false;
          String realVar = "";
          if (varName.equals("IngestedCells")) {
            int ingestionID=0;
            for (int j=0; j<targetSpeciesNo-1; j++) ingestionID+=getFGForSpeciesNo(j,theModel).getTags("stage").length;
            ingestionID+=targetStageNo;
            if (mode==LOGGING) PW.println("      Logging.writeFGReal("+auditSpeciesNo+",IngestedCells["+ingestionID+"]);");
            else varID=writeVarEntry(PW,"Ingested "+stage+" "+species,"real",varID);
          } else {
            String linkString = StringTools.nonSpace(species+"$"+stage);
            if (fgTag.getTagWhere("varietyvariable","name",varName)!=null) {
              link = fgTag.getTagWhere("varietyvariable","name",varName).getAttribute("link");
              realVar = fgTag.getTagWhere("varietyvariable","name",varName).getValue("name");
              variableFlag=true;
            } else {
              XMLTag findVar = fgTag.getTagWhere("varietylocal","name",varName);
              if (findVar!=null) {
                link = findVar.getAttribute("link");
                realVar = findVar.getValue("name");
                String foodSetName = speciesText+" : "+link;
                XMLTag[] foods = theModel.getTag("foodsets").getTagWhere("foodset","@name",foodSetName).getTags("food");
                int j=0;
                boolean found = false;
                while ((j<foods.length) && (!found)) {
                  String potentialFood = StringTools.nonSpace(foods[j].getAttribute("species")+"$"+foods[j].getAttribute("stage"));
                  if (potentialFood.equals(linkString)) found=true;
                  else j++;
                }
            
                if (mode==LOGGING) {
                  PW.print("      Logging.writeFGReal("+auditSpeciesNo+","+realVar+"[params._speciesOfFG]["+j+"]");
                  if (variableFlag) PW.println(".get());");
                  else PW.println(");");
                }
                else varID=writeVarEntry(PW,realVar+" for "+stage+" "+species,"real",varID);
              }
            }
          } 
        }
      }
      if (mode==LOGGING) PW.println("    }");
    }    
  }  
  
  public static void writeAuditVariables(XMLTag oo, PrintWriter PW, XMLTag fg, ArrayList deathReasons, XMLTag theModel) {
    boolean found=false;
    XMLTag[] species = theModel.getTag("species").getTags("species");
    for (int i=0; i<species.length; i++) {
      if (species[i].getAttribute("fg").equals(fg.getValue("name"))) {
        if (oo.getTagWhere("audit","@species",species[i].getAttribute("name")).getAttribute("active").equals("true")) {
          found=true;
          i=species.length;
        }
      }
    }
    if (found) {
      PW.println("  public void _writeAudits() {");
      PW.println("    XMLTag _var;");
      for (int i=0; i<species.length; i++) {
        if (species[i].getAttribute("fg").equals(fg.getValue("name"))) {
          if (oo.getTagWhere("audit","@species",species[i].getAttribute("name")).getAttribute("active").equals("true")) {
            PW.println("    if (params._type=="+i+") {"); 
            handleVariables(LOGGING,oo,PW,i,deathReasons,theModel);
            PW.println("    } ");
          }
        }
      }
      PW.println("  }");

    } else {
      PW.println("  public void _writeAudits() {}");
    }
    PW.println("");
  }
}
