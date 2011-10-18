package VEW.Controller2;

import java.util.GregorianCalendar;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Scenario2.Generator;

public class ModelConverter {
  private static int speciesID;
  
  private static void sortLocals(XMLTag fg) {
    // Get the sub-functions at the top.
    XMLTag[] funcs = fg.getTags("function");
    for (int i=0; i<funcs.length; i++) {
      funcs[i].removeFromParent();
      fg.addTag(funcs[i]);
    }
    
    while (fg.getTag("localvar")!=null) fg.getTag("localvar").setName("local");
    while (fg.getTag("exportvar")!=null) fg.getTag("exportvar").setName("local");
    while (fg.getTag("subfunction")!=null) fg.getTag("subfunction").setName("function");
    funcs = fg.getTags("function");
    for (int i=0; i<funcs.length; i++) {
      XMLTag localvar = funcs[i].getTag("localvar");
      while (localvar!=null) {
        localvar.setName("local");
        localvar.removeFromParent();
        fg.addTag(localvar);
        localvar = funcs[i].getTag("localvar");
      }
      localvar = funcs[i].getTag("varietylocal");
      while (localvar!=null) {
        localvar.removeFromParent();
        fg.addTag(localvar);
        localvar = funcs[i].getTag("varietylocal");
      }
    }
  }
  
  private static void addFG(XMLTag fg, XMLTag model) {
    XMLTag newFG = (XMLTag) fg.clone();
    while (newFG.getTag("species")!=null) newFG.getTag("species").removeFromParent();
    XMLTag[] stages = newFG.getTags("stage");
    for (int i=0; i<stages.length; i++) {
      if (stages[i].getTag("mem")!=null) stages[i].getTag("mem").removeFromParent();
    }
    sortLocals(newFG);
    model.addTag(newFG);
  }
  
  private static void addChem(XMLTag chem, XMLTag model) {
    XMLTag newChem = (XMLTag) chem.clone();
    sortLocals(newChem);
    model.addTag(newChem);
  }
  
  private static void addSpecies(XMLTag fg, XMLTag model) {
    if (model.getTag("species")==null) model.addTag(new XMLTag("species",""));
    XMLTag[] species = fg.getTags("species");
    for (int i=0; i<species.length; i++) {
      XMLTag[] varieties = species[i].getTags("variety");
      for (int j=0; j<varieties.length; j++) {
        XMLTag newSpecies = new XMLTag("species");
        newSpecies.setAttribute("id",String.valueOf(speciesID++));
        newSpecies.setAttribute("fg",fg.getValue("name"));
        newSpecies.setAttribute("name",varieties[j].getValue("name"));
        newSpecies.setAttribute("x",varieties[j].getValue("x"));
        XMLTag[] params = species[i].getTags("param");
        for (int k=0; k<params.length; k++) {
          XMLTag newParam = new XMLTag("param");
          newParam.setAttribute("name",params[k].getValue("name"));
          newParam.setAttribute("a",params[k].getValue("a"));
          newParam.setAttribute("b",params[k].getValue("b"));
          newSpecies.addTag(newParam);
        }
        model.getTag("species").addTag(newSpecies);
      }
    }
  }
  
  private static void addFoodsets(XMLTag fg, XMLTag model) {
    if (model.getTag("foodsets")==null) model.addTag(new XMLTag("foodsets",""));
    XMLTag[] species = fg.getTags("species");
    for (int i=0; i<species.length; i++) {
      XMLTag[] varconc = species[i].getTags("varconc");
      for (int j=0; j<varconc.length; j++) {
        String foodName = varconc[j].getValue("name");
        if (!foodName.equals("Ingestion")) {
          XMLTag[] varieties = species[i].getTags("variety");
          for (int k=0; k<varieties.length; k++) {
            XMLTag foodSet = new XMLTag("foodset");
            foodSet.setAttribute("name",varieties[k].getValue("name")+" : "+foodName);
            XMLTag[] foodVarieties = varconc[j].getTags("variety");
            for (int l=0; l<foodVarieties.length; l++) {
              XMLTag newFood = new XMLTag("food");
              String foodPiece = foodVarieties[l].getAttribute("name");
              String foodSpecies = foodPiece.substring(0,foodPiece.indexOf("$"));
              String foodStage = foodPiece.substring(foodPiece.indexOf("$")+1);
              XMLTag[] newSpecies = model.getTag("species").getTags("species");
              for (int m=0; m<newSpecies.length; m++) {
                if (StringTools.nonSpace(newSpecies[m].getAttribute("name")).equals(foodSpecies)) {
                  foodSpecies = newSpecies[m].getAttribute("name");
                  m=newSpecies.length;
                }
              }
              newFood.setAttribute("species",foodSpecies);
              newFood.setAttribute("stage",foodStage);
              XMLTag[] varparams = foodVarieties[l].getTagsWhere("varparam","@link",foodName);
              for (int m=0; m<varparams.length; m++) {
                XMLTag newParam = new XMLTag("param");
                newParam.setAttribute("name",varparams[m].getValue("name"));
                newParam.setAttribute("a",varparams[m].getValue("a"));
                newParam.setAttribute("b",varparams[m].getValue("b"));
                newFood.addTag(newParam);
              }
              foodSet.addTag(newFood);
            }
            model.getTag("foodsets").addTag(foodSet);
          }
        }
      }
    }
  }
  
  public static void addPMRules(XMLTag fg, XMLTag model) {
    if (model.getTag("particlemanagement")==null) model.addTag(new XMLTag("particlemanagement"));
    XMLTag[] specs = fg.getTags("species");
    for (int i=0; i<specs.length; i++) {
      XMLTag[] varieties = specs[i].getTags("variety");
      for (int j=0; j<varieties.length; j++) {
        XMLTag[] rules = varieties[j].getTag("particlemanagement/general").getTags();
        for (int k=0; k<rules.length; k++) {
          XMLTag newRule = new XMLTag("rule");
          if (rules[k].getName().equals("splitrule")) newRule.setAttribute("type",ParticleManager2.RULE_SPLIT);
          else if (rules[k].getName().equals("mergerule")) newRule.setAttribute("type",ParticleManager2.RULE_MERGE);
          newRule.setAttribute("species",varieties[j].getValue("name"));
          newRule.setAttribute("alldepth","true");
          newRule.setAttribute("alltime","true");          
          newRule.setAttribute("stage",rules[k].getAttribute("state"));
          newRule.setAttribute("fg",fg.getValue("name"));
          newRule.setAttribute("turbo","false");          
          newRule.setAttribute("value",rules[k].getValue("value"));
          if (rules[k].getValue("scope").equals("layer")) newRule.setAttribute("scope",ParticleManager2.RULE_LAYER);
          else if (rules[k].getValue("scope").equals("column")) newRule.setAttribute("scope",ParticleManager2.RULE_COLUMN);
          // mixing layer wasn't supported in 3.1
          if (!(rules[k].getName().equals("splitrule")) || (!rules[k].getValue("value").equals("0"))) 
            model.getTag("particlemanagement").addTag(newRule);
        }
      }
    }
  }
  
  public static void addPartInit(XMLTag fg, XMLTag model31) {
    if (model31.getTag("initialplankton")==null) model31.addTag(new XMLTag("initialplankton"));
    XMLTag[] specs = fg.getTags("species");
    for (int i=0; i<specs.length; i++) {
      XMLTag[] varieties = specs[i].getTags("variety");
      for (int j=0; j<varieties.length; j++) {
        XMLTag initialTag = varieties[j].getTag("particlemanagement/initial");
        XMLTag newDist = new XMLTag("distribution");
        newDist.setAttribute("species",varieties[j].getValue("name"));
        newDist.setAttribute("stage",initialTag.getValue("state"));
        newDist.setAttribute("popsize",initialTag.getTagWhere("option","name","Cell Count").getValue("upper"));
        newDist.setAttribute("permetre",initialTag.getTagWhere("option","name","Particles Per Meter").getValue("upper"));
        newDist.setAttribute("date",model31.getValue("track/start"));
        newDist.setAttribute("top",initialTag.getTagWhere("option","name","Distribution depth").getValue("upper"));
        newDist.setAttribute("bottom",initialTag.getTagWhere("option","name","Distribution depth").getValue("lower"));
        
        XMLTag[] vars = initialTag.getTags("option");
        for (int k=0; k<vars.length; k++) {
          String varName = vars[k].getValue("name");
          if ((!varName.endsWith("$Ingested")) && (!varName.equals("Cell Count")) && (!varName.equals("Particles Per Meter")) && (!varName.equals("Distribution depth"))) {
            XMLTag newVar = new XMLTag("var");
            newVar.setAttribute("name",varName);
            if (varName.endsWith("$Pool")) varName = varName.replace('$',' ');
            newVar.setAttribute("desc",varName);
            String lower = vars[k].getValue("lower");
            String upper = vars[k].getValue("upper");
            if (lower.equals(upper)) {
              newVar.setAttribute("val",vars[k].getValue("lower"));
              newVar.setAttribute("rnd","0.0");
            } else {
              newVar.setAttribute("val",vars[k].getValue("lower"));
              newVar.setAttribute("rnd",String.valueOf(Double.parseDouble(upper)-Double.parseDouble(lower)));
            }
            newDist.addTag(newVar);
          }
        }
        
        model31.getTag("initialplankton").addTag(newDist);
      }
    }
  }
  
  private static void sortTrack(XMLTag model30, XMLTag model31) {
    XMLTag oldTrack = model30.getTag("scenario/column");
    
    XMLTag newTrack = new XMLTag("track");
    if (oldTrack.getTag("initiallatitude")!=null) 
      newTrack.addTag(new XMLTag("latitude",oldTrack.getValue("initiallatitude")));
    else newTrack.addTag(new XMLTag("latitude",oldTrack.getValue("latitude")));
    if (oldTrack.getTag("initiallongitude")!=null)
      newTrack.addTag(new XMLTag("longitude",oldTrack.getValue("initiallongitude")));
    else newTrack.addTag(new XMLTag("longitude",oldTrack.getValue("longitude")));
    newTrack.addTag(new XMLTag("secstep",oldTrack.getValue("steplength")));
    newTrack.addTag(new XMLTag("map","World"));
    GregorianCalendar anyDate = new GregorianCalendar();
    anyDate.set(GregorianCalendar.SECOND,0);
    anyDate.set(GregorianCalendar.MILLISECOND,0);
    anyDate.set(GregorianCalendar.YEAR,Integer.parseInt(oldTrack.getValue("startyear")));
    anyDate.set(GregorianCalendar.MONTH,Integer.parseInt(oldTrack.getValue("startmonth")));
    anyDate.set(GregorianCalendar.DAY_OF_MONTH,Integer.parseInt(oldTrack.getValue("startday"))+1);
    String[] startTime = oldTrack.getValue("starttime").split(":");
    anyDate.set(GregorianCalendar.HOUR_OF_DAY,Integer.parseInt(startTime[0]));
    anyDate.set(GregorianCalendar.MINUTE,Integer.parseInt(startTime[1]));
    newTrack.addTag(new XMLTag("start",String.valueOf(anyDate.getTimeInMillis())));
    anyDate.add(GregorianCalendar.SECOND, Integer.parseInt(oldTrack.getValue("steplength"))*Integer.parseInt(oldTrack.getValue("duration")));
    newTrack.addTag(new XMLTag("end",String.valueOf(anyDate.getTimeInMillis())));
    if (oldTrack.getValue("integrationmethod").equals("0")) newTrack.addTag(new XMLTag("trackmode",Generator.INT_FWD));
    else if (oldTrack.getValue("integrationmethod").equals("1")) newTrack.addTag(new XMLTag("trackmode",Generator.INT_BWD));
    else if (oldTrack.getValue("integrationmethod").equals("2")) newTrack.addTag(new XMLTag("trackmode",Generator.INT_FIX));
    int i=0;
    String velocityData = oldTrack.getValue("velocitydata");
    while (!Generator.velFiles[i].equals(velocityData)) i++;
    newTrack.addTag(new XMLTag("velocityfield",Generator.velRanges[i]));
    model31.addTag(newTrack);    
  }
  
  private static void sortBoundary(XMLTag model30, XMLTag model31) {
    model31.addTag(new XMLTag("boundaryconditions"));
    model31.getTag("boundaryconditions").addTag(new XMLTag("climatedata",model30.getValue("scenario/boundaryconditions/climatedata")));
    model31.getTag("boundaryconditions").addTag(new XMLTag("heatflux",model30.getValue("scenario/boundaryconditions/heatflux")));    
  }
  
  private static void sortInitCol(XMLTag model30, XMLTag model31) {
    model31.addTag(new XMLTag("initialconditions"));
    model31.getTag("initialconditions").addTag(new XMLTag("turbocline",model30.getValue("scenario/initialconditions/turbocline")));
    XMLTag[] fields = model30.getTag("scenario/initialconditions").getTags("field");
    for (int i=0; i<fields.length; i++) {
      XMLTag newField = new XMLTag("field");
      newField.setAttribute("source",fields[i].getValue("source"));
      newField.setAttribute("name",fields[i].getValue("name"));
      newField.setValue(fields[i].getValue("data"));      
      if (i==0) {
        String data = fields[i].getValue("data");
        String[] bits = data.split(",");
        model31.getTag("initialconditions").setAttribute("size",String.valueOf(bits.length));
      }
      model31.getTag("initialconditions").addTag(newField);
    }
  }
  
  private static void sortOutput(XMLTag model30, XMLTag model31) {
    XMLTag newOutput = new XMLTag("output");
    model31.addTag(newOutput);
    newOutput.setAttribute("zip","true");
    newOutput.setAttribute("float","true");
    XMLTag oldOutput = model30.getTag("output");
    if (oldOutput!=null) {
      if (oldOutput.getTag("column")!=null) processCol(oldOutput.getTag("column"),model31);
      if (oldOutput.getTag("fieldbio")!=null) {
        processFieldBio(oldOutput.getTag("fieldbio"),model31);
        processFieldDemo(oldOutput.getTag("fieldbio"),model31);
      }
  
      if (oldOutput.getTag("fieldchem")!=null) processFieldChem(oldOutput.getTag("fieldchem"),model31);  
      if (oldOutput.getTag("fieldphy")!=null) processFieldPhy(oldOutput.getTag("fieldphy"),model31);
      XMLTag[] audits = oldOutput.getTags("audit");
      for (int i=0; i<audits.length; i++) processAudit(audits[i],model31);
      if (oldOutput.getTag("demography")!=null) processDemography(oldOutput.getTag("demography"),model31);
    }
  }
  
  private static void handleDates(XMLTag model31, XMLTag oldLog, XMLTag newLog) {
    final long secStep = Long.parseLong(model31.getValue("track/secstep"));
    final long startSim = Long.parseLong(model31.getValue("track/start"));
    final long colFrom = startSim+(1000*secStep*Long.parseLong(oldLog.getAttribute("from")));
    final long colTo = startSim+(1000*secStep*Long.parseLong(oldLog.getAttribute("to")));
    newLog.setAttribute("from",String.valueOf(colFrom));
    newLog.setAttribute("to",String.valueOf(colTo));
    newLog.setAttribute("active","true");
    newLog.setAttribute("freq",oldLog.getAttribute("freq"));
    String unit = oldLog.getAttribute("unit");
    if (unit.equals("0")) newLog.setAttribute("unit",OutputDialog2.UNIT_STEP);
    else if (unit.equals("1")) newLog.setAttribute("unit",OutputDialog2.UNIT_HOUR);
    else if (unit.equals("2")) newLog.setAttribute("unit",OutputDialog2.UNIT_DAY);
    else if (unit.equals("3")) newLog.setAttribute("unit",OutputDialog2.UNIT_WEEK);
  }
  
  private static int[] getCodes(String v) {
    int[] code = new int[3];
    code[0]=-1;
    code[1]=-1;
    code[2]=-1;
    v = v.substring(v.indexOf("+")+1);
    if (v.indexOf("-")==-1) code[0] = Integer.parseInt(v);
    else {
      code[0]= Integer.parseInt(v.substring(0,v.indexOf("-")));
      v = v.substring(v.indexOf("-")+1);
    }
    if (v.indexOf("=")==-1) code[1] = Integer.parseInt(v);
    else {
      code[1]= Integer.parseInt(v.substring(0,v.indexOf("=")));
      v = v.substring(v.indexOf("=")+1);
    }
    if (v.length()>0) code[2] = Integer.parseInt(v);        
    return code;
  }
  
  private static void addBioEntry(int[] code, XMLTag model31, XMLTag newVar, String scope) {
    newVar.setAttribute("scope",scope);
    String speciesName = model31.getTag("species").getTags("species")[code[1]].getAttribute("name");
    String fgName = model31.getTag("species").getTags("species")[code[1]].getAttribute("fg");
    String stageName = model31.getTagWhere("functionalgroup","name",fgName).getTags("stage")[code[2]].getValue("name");
    newVar.setAttribute("species",speciesName);
    newVar.setAttribute("stage",stageName);
    newVar.setAttribute("name","Concentration of individuals : "+speciesName+" : "+stageName);
  }
    
  
  private static void addChemEntry(int[] code, XMLTag model31, XMLTag newVar, String scope) {
    String chemicalName = model31.getTags("chemical")[code[1]].getValue("name");
    String chemType = (code[2]==0)?"conc":"bioconc";
    newVar.setAttribute("scope",scope);
    newVar.setAttribute("chem",chemicalName);
    newVar.setAttribute("chemtype",chemType);
    newVar.setAttribute("name",chemicalName+" concentration in "+((code[2]==0)?"Solution":"Plankton"));
  }
  
  private static void addDemoEntry(int[] code, XMLTag model31, XMLTag newVar, String varName, String scope) {
    newVar.setAttribute("scope",scope);
    String speciesName = model31.getTag("species").getTags("species")[code[1]].getAttribute("name");
    String fgName = model31.getTag("species").getTags("species")[code[1]].getAttribute("fg");
    String stageName = model31.getTagWhere("functionalgroup","name",fgName).getTags("stage")[code[2]].getValue("name");
    newVar.setAttribute("species",speciesName);
    newVar.setAttribute("stage",stageName);
    if (varName.equals("Number of agents")) {
      newVar.setAttribute("name",OutputDialog2.AGENT_NO_AGENTS+" : "+speciesName+" : "+stageName);
    } if (varName.equals("Being Ingested")) {
      newVar.setAttribute("name",OutputDialog2.AGENT_BEING_INGESTED_ALL+" : "+speciesName+" : "+stageName);
    } else newVar.setAttribute("name",varName+" : "+speciesName+" : "+stageName); 
  }
  
  private static void processCol(XMLTag oldColumn, XMLTag model31) {
    XMLTag newCol = new XMLTag("col");      
    model31.getTag("output").addTag(newCol);
    handleDates(model31,oldColumn,newCol);
    XMLTag[] colVars = oldColumn.getTags("var");
    for (int i=0; i<colVars.length; i++) {
      XMLTag newVar = new XMLTag("var");
      newVar.setAttribute("structure",OutputDialog2.STRUCT_WATER_COLUMN);
      int code[] = getCodes(colVars[i].getAttribute("name"));
      String varName = colVars[i].getAttribute("name");
      varName = varName.substring(0,varName.indexOf("+"));
      if (code[0]==0) { 
        addBioEntry(code,model31,newVar,OutputDialog2.SCOPE_WC_BIOLOGICAL);
      } else if (code[0]==1) { 
        addChemEntry(code,model31,newVar,OutputDialog2.SCOPE_WC_CHEMICAL);
      } else if (code[0]==2) { 
        addDemoEntry(code,model31,newVar,varName,OutputDialog2.SCOPE_WC_DEMOGRAPHIC);
      } else if (code[0]==3) {  
        newVar.setAttribute("scope",OutputDialog2.SCOPE_WC_PHYSICAL);
        newVar.setAttribute("name","Turbocline");
      } 
      newCol.addTag(newVar);
    }
  }
  
  private static void processFieldBio(XMLTag oldFB, XMLTag model31) {
    XMLTag newFB = new XMLTag("fieldbio");      
    model31.getTag("output").addTag(newFB);
    handleDates(model31,oldFB,newFB);
    newFB.setAttribute("top",oldFB.getAttribute("top"));
    newFB.setAttribute("bottom",oldFB.getAttribute("bottom"));
    XMLTag[] vars = oldFB.getTags("var");
    for (int i=0; i<vars.length; i++) {
      int code[] = getCodes(vars[i].getAttribute("name"));
      if (code[0]==0) {
        XMLTag newVar = new XMLTag("var");
        newVar.setAttribute("structure",OutputDialog2.STRUCT_FIELD_DATA);
        addBioEntry(code,model31,newVar,OutputDialog2.SCOPE_FD_BIOLOGICAL);
        newFB.addTag(newVar);
      }
    }
  }
  
  private static void processFieldDemo(XMLTag oldFB, XMLTag model31) {
    XMLTag newFD = new XMLTag("fielddemo");      
    model31.getTag("output").addTag(newFD);
    handleDates(model31,oldFB,newFD);
    newFD.setAttribute("top",oldFB.getAttribute("top"));
    newFD.setAttribute("bottom",oldFB.getAttribute("bottom"));
    XMLTag[] vars = oldFB.getTags("var");
    for (int i=0; i<vars.length; i++) {
      String varName = vars[i].getAttribute("name");
      varName = varName.substring(0,varName.indexOf("+"));
      int code[] = getCodes(vars[i].getAttribute("name"));
      if (code[0]==2) {
        XMLTag newVar = new XMLTag("var");
        newVar.setAttribute("structure",OutputDialog2.STRUCT_FIELD_DATA);
        addDemoEntry(code,model31,newVar,varName,OutputDialog2.SCOPE_FD_DEMOGRAPHIC);
        newFD.addTag(newVar);
      }
    }
  }

  
  private static void processFieldChem(XMLTag oldFC, XMLTag model31) {
    XMLTag newFC = new XMLTag("fieldchem");      
    model31.getTag("output").addTag(newFC);
    handleDates(model31,oldFC,newFC);
    newFC.setAttribute("top",oldFC.getAttribute("top"));
    newFC.setAttribute("bottom",oldFC.getAttribute("bottom"));
    XMLTag[] vars = oldFC.getTags("var");
    for (int i=0; i<vars.length; i++) {
      XMLTag newVar = new XMLTag("var");
      newVar.setAttribute("structure",OutputDialog2.STRUCT_FIELD_DATA);
      int code[] = getCodes(vars[i].getAttribute("name"));
      addChemEntry(code,model31,newVar,OutputDialog2.SCOPE_FD_CHEMICAL);
      newFC.addTag(newVar);
    }
  }
  
  private static void processFieldPhy(XMLTag oldFP, XMLTag model31) {
    XMLTag newFP = new XMLTag("fieldphy");      
    model31.getTag("output").addTag(newFP);
    handleDates(model31,oldFP,newFP);
    int topIndex = Integer.parseInt(oldFP.getAttribute("top"));
    int bottomIndex = Integer.parseInt(oldFP.getAttribute("bottom"));
    String[] phyDepths = MiscUtils.getPhysicalDepths(500);
    newFP.setAttribute("top",phyDepths[topIndex]);
    newFP.setAttribute("bottom",phyDepths[bottomIndex]);
    XMLTag[] vars = oldFP.getTags("var");
    for (int i=0; i<vars.length; i++) {
      XMLTag newVar = new XMLTag("var");
      newVar.setAttribute("structure",OutputDialog2.STRUCT_FIELD_DATA);
      newVar.setAttribute("scope",OutputDialog2.SCOPE_FD_PHYSICAL);
      String varName = vars[i].getAttribute("name");
      varName = varName.substring(0,varName.indexOf("+"));
      newVar.setAttribute("name",varName);
      newFP.addTag(newVar);
    }
  }
  
  private static void processAudit(XMLTag oldA, XMLTag model31) {
    XMLTag newA = new XMLTag("audit");
    model31.getTag("output").addTag(newA);
    String speciesAudit = oldA.getAttribute("name");
    newA.setAttribute("species",speciesAudit);
    handleDates(model31,oldA,newA);
    XMLTag speciesTag = model31.getTag("species").getTagWhere("species","@name",speciesAudit);
    XMLTag[] stageTags = model31.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg")).getTags("stage");
    String stageString = "";
    for (int i=0; i<stageTags.length; i++) {
      if ((stageTags[i].getAttribute("log")!=null) && (stageTags[i].getAttribute("log").equals("true"))) {
        stageString+=stageTags[i].getValue("name")+",";
        stageTags[i].removeAttribute("log");
      }
    }
    if (stageString.length()>0) stageString=stageString.substring(0,stageString.length()-1);
    newA.setAttribute("stage",stageString);
    XMLTag[] vars = oldA.getTags("var");
    for (int i=0; i<vars.length; i++) {
      XMLTag newVar = new XMLTag("var");
      newVar.setAttribute("structure",OutputDialog2.STRUCT_AUDIT_TRAIL+" for "+speciesAudit);
      String varName = vars[i].getAttribute("name");
      int[] code = getCodes(varName);
      varName = varName.substring(0,varName.indexOf("+"));
      if (code[0]==0) addAuditStateVar(varName,speciesTag.getAttribute("fg"),newVar,model31);
      else if (code[0]==1) addBioEntry(code,model31,newVar,OutputDialog2.SCOPE_AT_BIOLOGICAL);
      else if (code[0]==2) addChemEntry(code,model31,newVar,OutputDialog2.SCOPE_AT_CHEMICAL);
      else if (code[0]==3) addDemoEntry(code,model31,newVar,varName,OutputDialog2.SCOPE_AT_DEMOGRAPHIC);
      else if (code[0]==4) addAuditAmbPhy(varName,newVar);
      else if (code[0]==5) addAuditLocal(varName,speciesTag.getAttribute("fg"),newVar,model31);
      else if (code[0]==6) addAuditSpecies(code, speciesTag.getAttribute("fg"), model31, newVar, varName);
      newA.addTag(newVar);
    }
  }
  
  private static void addAuditStateVar(String v, String fg, XMLTag newA, XMLTag model31) {
    boolean done=false;
    if ((v.endsWith("pool")) || (v.endsWith("ingested last timestep")) || (v.endsWith("ingested last time step"))) {
      String chem = "";
      if (v.endsWith("pool")) chem = v.substring(0,v.length()-5);
      else if (v.endsWith("ingested last timestep")) chem = v.substring(0,v.length()-23);
      else chem = v.substring(0,v.length()-24);
      if (model31.getTagWhere("chemical","name",chem)!=null) {
        done=true;
        newA.setAttribute("scope",OutputDialog2.SCOPE_AT_INCHEMICAL);
        newA.setAttribute("chem",chem);
        if (v.endsWith("pool")) {
          newA.setAttribute("chemtype","pool");
          newA.setAttribute("name",chem+" pool");
        } else {
          newA.setAttribute("chemtype","gain");
          newA.setAttribute("name",chem+" gained (ingest/uptake)");
        }
      }
    } else if ((v.equals("Individuals on agent")) || (v.equals("Inidividuals on agent"))) {
      done=true;
      newA.setAttribute("scope",OutputDialog2.SCOPE_AT_AGENT);
      newA.setAttribute("name","c ("+OutputDialog2.AGENT_NO_AGENTS+")");
    }
    
    if (!done) {
      XMLTag theFG = model31.getTagWhere("functionalgroup","name",fg);
      XMLTag theVar = theFG.getTagWhere("variable","desc",v);
      String theDesc = "";
      String theName = "";
      if (theVar!=null) {
        theDesc = theVar.getValue("desc");
        theName = theVar.getValue("name");
      }  else {
        if (v.equals("Depth")) {
          theName="z";
          theDesc="Depth";
        }
      }
      newA.setAttribute("scope",OutputDialog2.SCOPE_AT_AGENT);
      newA.setAttribute("name",theName+" ("+theDesc+")");
    }
  }
  
  private static void addAuditAmbPhy(String v, XMLTag newA) {
    newA.setAttribute("scope",OutputDialog2.SCOPE_AT_PHYSICAL);
    newA.setAttribute("name",v);
  }
  
  private static void addAuditLocal(String v, String fg, XMLTag newA, XMLTag model31) {
    XMLTag theFG = model31.getTagWhere("functionalgroup","name",fg);
    XMLTag theVar = theFG.getTagWhere("local","desc",v);
    if (theVar!=null) {
      newA.setAttribute("scope",OutputDialog2.SCOPE_AT_LOCAL);  
      String tryDesc = theVar.getValue("name");
      if (theVar.getTag("desc")!=null) tryDesc=theVar.getValue("desc");
      if (tryDesc==null) tryDesc=theVar.getValue("name");
      newA.setAttribute("name",theVar.getValue("name")+" ("+tryDesc+")");
    }
  }
  
  private static void addAuditSpecies(int[] code, String fgAudit, XMLTag model31, XMLTag newVar, String varName) {
    newVar.setAttribute("scope",OutputDialog2.SCOPE_AT_VARIETYBASED);
    final String speciesName = model31.getTag("species").getTags("species")[code[1]].getAttribute("name");
    final String fgName = model31.getTag("species").getTags("species")[code[1]].getAttribute("fg");
    final String stageName = model31.getTagWhere("functionalgroup","name",fgName).getTags("stage")[code[2]].getValue("name");
    final XMLTag auditFG = model31.getTagWhere("functionalgroup","name",fgAudit);
    XMLTag theVar = auditFG.getTagWhere("varietylocal","desc",varName);
    if (theVar==null) theVar = auditFG.getTagWhere("varietyvariable","desc",varName);
    newVar.setAttribute("species",speciesName);
    newVar.setAttribute("stage",stageName);
    newVar.setAttribute("name",theVar.getValue("name")+" ("+theVar.getValue("desc")+") : "+speciesName+" : "+stageName); 
  }

  private static void processDemography(String type, XMLTag demog, XMLTag model31) {
    XMLTag newDemog = new XMLTag(type);
    model31.getTag("output").addTag(newDemog);
    if ((demog.getValue(type)!=null) && (demog.getValue(type).equals("true"))) newDemog.setAttribute("active","true");
    else newDemog.setAttribute("active","false");
    final long secStep = Long.parseLong(model31.getValue("track/secstep"));
    final long startMilli = Long.parseLong(model31.getValue("track/start"));
    final long fromStep = startMilli+(secStep*Long.parseLong(demog.getAttribute("from")));
    final long toStep = startMilli+(secStep*Long.parseLong(demog.getAttribute("to")));
    newDemog.setAttribute("to",String.valueOf(toStep));
    newDemog.setAttribute("from",String.valueOf(fromStep));
    
  }
  
  private static void processDemography(XMLTag demog, XMLTag model31) {
    processDemography("lineage",demog,model31);
    processDemography("lifespan",demog,model31);
  }
  
  private static void sortChemRecycle(XMLTag model30, XMLTag model31) {
    if (model31.getTag("chemrecycle")==null) model31.addTag(new XMLTag("chemrecycle"));
    XMLTag cr = model31.getTag("chemrecycle");
    cr.addTag(new XMLTag("useCC",model30.getValue("scenario/conserve/useCC")));
    cr.addTag(new XMLTag("detecttime",model30.getValue("scenario/conserve/detecttime")));
    cr.addTag(new XMLTag("yearlyupdate",model30.getValue("scenario/conserve/yearlyupdate")));
    cr.addTag(new XMLTag("detectdepth",model30.getValue("scenario/conserve/useCC")));
    cr.addTag(new XMLTag("deepever",model30.getValue("scenario/conserve/deepever")));
    cr.addTag(new XMLTag("deepyear",model30.getValue("scenario/conserve/deepyear")));
    cr.addTag(new XMLTag("fixdepth",model30.getValue("scenario/conserve/fixdepth")));
    cr.addTag(new XMLTag("continuous",model30.getValue("scenario/conserve/continuous")));
    cr.addTag(new XMLTag("depthtouse",model30.getValue("scenario/conserve/depthtouse")));
    final long step = Long.parseLong(model30.getValue("scenario/conserve/startstep"));
    final long secstep = Long.parseLong(model31.getValue("track/secstep"));
    final long startmillis = Long.parseLong(model31.getValue("track/start"));
    cr.addTag(new XMLTag("start",String.valueOf(startmillis+(step*secstep*1000L))));
    XMLTag[] chemicals = model30.getTag("scenario/conserve").getTags("chemical");
    for (int i=0; i<chemicals.length; i++)
      cr.addTag(new XMLTag("chemical",model30.getTags("chemical")[Integer.parseInt(chemicals[i].getValue())].getValue("name")));
  }
    
  private static void sortClosure(XMLTag model30, XMLTag model31) {
    XMLTag closure = model30.getTag("scenario/closure");
    if (closure!=null) model31.addTag((XMLTag) closure.clone());
  }
  
  private static void sortEvents(XMLTag model30, XMLTag model31) {
    model31.addTag(new XMLTag("events",""));
  }
  
  private static void sortKernel(XMLTag model30, XMLTag model31) {
    if (model31.getTag("kernel")!=null) model31.getTag("kernel").removeFromParent();
  }
  
  public static void convert(XMLFile model30, XMLFile model31) {
    speciesID=0;
    model31.addTag(new XMLTag("version","3.1"));
    XMLTag[] fgs = model30.getTags("functionalgroup");
    for (int i=0; i<fgs.length; i++) addFG(fgs[i],model31);
    XMLTag[] chems = model30.getTags("chemical");
    for (int i=0; i<chems.length; i++) addChem(chems[i],model31);
    for (int i=0; i<fgs.length; i++) addSpecies(fgs[i],model31);
    for (int i=0; i<fgs.length; i++) addFoodsets(fgs[i],model31);
    sortTrack(model30,model31);
    for (int i=0; i<fgs.length; i++) addPMRules(fgs[i],model31);
    for (int i=0; i<fgs.length; i++) addPartInit(fgs[i],model31);
    sortBoundary(model30,model31);
    sortInitCol(model30,model31);
    sortChemRecycle(model30,model31);
    sortClosure(model30,model31);
    sortEvents(model30,model31);
    sortOutput(model30,model31);
    sortKernel(model30,model31);
    model31.write();
  }
  
  public static void main(String args[]) {
    XMLFile model30 = XMLFile.LoadFile("c:\\wes\\work\\vew\\modelTree\\lerm-es\\Builds\\1\\model.xml");
    XMLFile model31 = new XMLFile("c:\\wes\\work\\VEW\\modelTree\\lerm-es\\Builds\\1\\model31.xml","model");
    convert(model30,model31);
    System.out.println("Done!");
  }
    
}
