package  VEW.Compiler2;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.EventPanel;
import VEW.Controller2.MiscUtils;
import VEW.Controller2.OutputDialog2;
import VEW.Controller2.ParticleManager2;
import VEW.Controller2.RunPanel;
import VEW.Controller2.VEWController2;
import VEW.Scenario2.ScenarioPanel2;
import VEW.Scenario2.VerticalDiffusionDialog;

public class SysVarsCompiler {
  
  private static final int LOGGING = 100;
  private static final int DEFINITIONS = 101;
  
  public final static  double[] getDepthRefList() {
    double[] d = new double[520];
    float power=-2;
    d[0] = 0.0;
    for (int i=0; i<20; i++) {
      d[i+1]=Math.pow(10,power);
      power+=0.1f;
    }
    for (int i=1; i<500; i++) d[i+20]=i;
    return d;
  }
  
  private static void CompileEventOp(PrintWriter PW, String itemString, String op, String  val) {
    if (!itemString.equals("_pLayer.TempAdj")) {
      if (op.equals(EventPanel.OP_ADD)) PW.print(itemString+"+"+val); 
      if (op.equals(EventPanel.OP_DIV)) PW.print(itemString+"/"+val);
      if (op.equals(EventPanel.OP_MUL)) PW.print(itemString+"*"+val);
      if (op.equals(EventPanel.OP_SET)) PW.print(val);
      if (op.equals(EventPanel.OP_SUB)) PW.print(itemString+"-"+val);
    }  else {
      if (op.equals(EventPanel.OP_ADD)) PW.print(val);
      if (op.equals(EventPanel.OP_DIV)) PW.print("_pLayer.Temp*((1/"+val+")-1)");
      if (op.equals(EventPanel.OP_MUL)) PW.print("_pLayer.Temp*("+val+"-1)");
      if (op.equals(EventPanel.OP_SET)) PW.print(val+"-_pLayer.Temp");
      if (op.equals(EventPanel.OP_SUB)) PW.print("-"+val);
      
    }
  }
  
  private static void CompileEvents(PrintWriter PW, XMLTag model) {
    GregorianCalendar startSim = new GregorianCalendar();
    GregorianCalendar endSim = new GregorianCalendar();
    MiscUtils.findDateLimits(model,startSim, endSim);
    PW.println("  public static void doEvents() {");
    XMLTag ev = model.getTag("events");
    if (ev!=null) {
      XMLTag[] events = ev.getTags("event");
      boolean doPhysics=false;
      boolean doBio=false;
      for (int i=0; i<events.length; i++) {
        String scope = events[i].getAttribute("scope");
        if (scope.equals(EventPanel.PHYSICS_LAYERS)) doPhysics=true;
        if (scope.equals(EventPanel.BIOCHEM_LAYERS)) doBio=true;
      }
      if (doPhysics ||doBio) {
        PW.println("    int _startLayer=0;");
        PW.println("    int _endLayer=0;");
        if (doPhysics) PW.println("    PLayer _pLayer;");
        if (doBio) PW.println("    BLayer _bLayer;");
        PW.println("");
      }
      String itemString="";        
      for (int i=0; i<events.length; i++) {
        String scope = events[i].getAttribute("scope");
        
        if ((scope.equals(EventPanel.PHYSICS_LAYERS)) || (scope.equals(EventPanel.BIOCHEM_LAYERS))) {
          PW.println("    // "+events[i].getAttribute("name")); 
          PW.println("");
          String op = events[i].getAttribute("op");
          String val = events[i].getAttribute("val");
          if ((events[i].getAttribute("continuous")!=null) && (events[i].getAttribute("continuous").equals("true"))) {
            PW.println("    { // Perform this event continuously");
          }  else {
            long startEventMillis = Long.parseLong(events[i].getAttribute("start"));
            long endEventMillis = Long.parseLong(events[i].getAttribute("end"));
            long stepMillis = Long.parseLong(model.getValue("track/secstep"))*1000;
            double interval = Double.parseDouble(events[i].getAttribute("interval"));
            String intervalUnit = events[i].getAttribute("intervalunit");
            if (intervalUnit.equals(EventPanel.INT_TS)) {
              PW.println("    if (Logging.isTime("+(long)(interval*stepMillis)+"L,"+startEventMillis+"L,"+endEventMillis+"L)) {");
            }  else if (intervalUnit.equals(EventPanel.INT_HOUR)) {
              final long hourMillis = 3600*1000;
              PW.println("    if (Logging.isTime("+(long)(interval*(hourMillis/stepMillis))+"L,"+startEventMillis+"L,"+endEventMillis+"L)) {");
            }  else if (intervalUnit.equals(EventPanel.INT_DAY)) {
              final long dayMillis = 24*3600*1000;
              PW.println("    if (Logging.isTime("+(long)(interval*(dayMillis/stepMillis))+"L,"+startEventMillis+"L,"+endEventMillis+"L)) {");
            }  else if (intervalUnit.equals(EventPanel.INT_WEEK)) {
              final long weekMillis = 24*3600*1000;
              PW.println("    if (Logging.isTime("+(long)(interval*(weekMillis/stepMillis))+"L,"+startEventMillis+"L,"+endEventMillis+"L)) {");
            }  else if (intervalUnit.equals(EventPanel.INT_YEAR)) {
              PW.println("    if (Logging.isYearlyInterval("+(int)interval+","+startEventMillis+"L,"+endEventMillis+"L)) {");
            }
          }
          if (scope.equals(EventPanel.PHYSICS_LAYERS)) {
            PW.println("      _startLayer = Utils.findPLayerID("+events[i].getAttribute("top")+");");
            if (StringTools.parseBoolean(events[i].getAttribute("mld"))) 
              PW.println("      final double _mld = Kernel.W.MLDepth;");
            String bottomDepthS = "Utils.findPLayerID("+events[i].getAttribute("bottom")+")";
            if (StringTools.parseBoolean(events[i].getAttribute("mld"))) bottomDepthS = "Utils.findPLayerID(_mld)"; 
            PW.println("      _endLayer = "+bottomDepthS+";");
            PW.println("      for (int _l = _startLayer; _l<=_endLayer; _l++) {");
            PW.println("        _pLayer = (PLayer) Kernel.W.P_LayerList.get(_l);");
            String item=events[i].getAttribute("item");
            if (item.equals("Density")) itemString="_pLayer.Density";
            else if (item.equals("Full Irradiance")) itemString="_pLayer.Full_Irrad";          
            else if (item.equals("Salinity")) itemString="_pLayer.Salinity";
            else if (item.equals("Temperature")) itemString="_pLayer.TempAdj";
            else if (item.equals("Visible Irradiance")) itemString="_pLayer.Vis_Irrad";
            else itemString="ERROR";
            PW.print("        "+itemString+" = ");
            if (events[i].getAttribute("interpolate")!=null) {
              if (events[i].getAttribute("interpolate").equals("true")) {  
                final double topDepth = Double.parseDouble(events[i].getAttribute("top"));
                final double bottomDepth = Double.parseDouble(events[i].getAttribute("bottom"));
                final double bottomVal = Double.parseDouble(events[i].getAttribute("bottomval"));
                final double topVal = Double.parseDouble(events[i].getAttribute("val"));
                val = topVal + " + ("+(bottomVal-topVal)+"*";
                val += "((_pLayer.Depth-"+topDepth+")/";
                if (StringTools.parseBoolean(events[i].getAttribute("mld"))) val += "(_mld-"+topDepth+")));";
                else val += (bottomDepth-topDepth)+"))";
              }
            }
            CompileEventOp(PW,itemString,op,val);
            PW.println(";");
            PW.println("      }");           
                        
          }  else {
            PW.println("      _startLayer = "+events[i].getAttribute("top")+";");
            if (StringTools.parseBoolean(events[i].getAttribute("mld"))) 
              PW.println("      final double _mld = Kernel.W.MLDepth;");
            String bottomDepthS = events[i].getAttribute("bottom");
            if (StringTools.parseBoolean(events[i].getAttribute("mld"))) bottomDepthS = "(int) Math.floor(_mld)"; 
            PW.println("      _endLayer = "+bottomDepthS+";");
            PW.println("      for (int _l = _startLayer; _l<=_endLayer; _l++) {");
            itemString = events[i].getAttribute("item");
            final String chemName = itemString.substring(0,itemString.length()-14); // Remove  "  concentration"            
            itemString = "Kernel.W.B_Layer[_l].solution_chem["+chemName+".ID_"+chemName+"]";
            PW.print("        "+itemString+"=(");
            itemString = itemString+".get()";
            if (events[i].getAttribute("interpolate")!=null) {
              if (events[i].getAttribute("interpolate").equals("true")) {
                final double topDepth = Double.parseDouble(events[i].getAttribute("top"));
                final double bottomDepth = Double.parseDouble(events[i].getAttribute("bottom"));
                final double bottomVal = Double.parseDouble(events[i].getAttribute("bottomval"));
                final double topVal = Double.parseDouble(events[i].getAttribute("val"));
                
                val = topVal +" + ("+(bottomVal-topVal)+"*";
                val += "((_l-"+topDepth+")/";
                if (StringTools.parseBoolean(events[i].getAttribute("mld"))) 
                  val += "(_mld-"+topDepth+")))";
                else val += (bottomDepth-topDepth)+"))";
              }
            }

            CompileEventOp(PW,itemString,op,val);
            PW.println(");");
            PW.println("      }");
          }
          
          PW.println("    }");
        }  
      }
    }
    PW.println("  }");
  }
  
  public static  int convertTime(String s) {
    String[] TimeBits = s.split(":");
    int t = 2 * Integer.parseInt(TimeBits[0]);
    if (TimeBits[1].equals("30")) t++;
    return t;
  }
 
  public static void handleStateChanges(PrintWriter  out, String  eq,  String lookFor,  XMLTag funcTag,  XMLTag[] states) {
    int startIndex = eq.indexOf("\\" + lookFor + "{");
    String decompose = StringTools.spit(eq.substring(startIndex),  "\\" + lookFor + "{");
    String stagename = StringTools.spit(decompose.substring(decompose.indexOf("\\stage{")),  "\\stage{");
    int k = 0;
    while ((k  <  states.length) && (!states[k].getValue("name").equals(stagename)))
      k++;
    XMLTag[] calls = funcTag.getTags("calledin");
    for (int l = 0; l  <  calls.length; l++) {
      String callName = calls[l].getValue();
      int m = 0;
      while ((m  <  states.length) && (!states[m].getValue("name").equals(callName)))
        m++;
      out.println("     AddStateLink(fgTag, " + m + ", " + k + ", \"" + lookFor + "\");");
    }
  }
  
  public static void makeSysVars(XMLTag  theModel,  ArrayList  deathReasons,  String theOutputDir) {
    PrintWriter  out = StringTools.OpenOutputFile(theOutputDir + "/VEW/Sim/SysVars.java");
    
    out.println("package VEW.Sim;");
    out.println("import VEW.Common.XML.*;");
    out.println("import VEW.Common.*;");
    out.println("import java.util.GregorianCalendar;");
    out.println("import java.util.TimeZone;");
    out.println("import java.io.File;");
    
    out.println("");
    out.println("public class SysVars {");
    out.println("  public       static long startTime = "+theModel.getValue("track/start")+"L;");
    out.println("  public final static long endTime = " +theModel.getValue("track/end")+"L;");
    out.println("  public final static long stepInMillis = " +(Long.parseLong(theModel.getValue("track/secstep"))*1000)+"L;");   
    out.println("  public final static long stepInSeconds = " +theModel.getValue("track/secstep")+"L;");
    out.println("  public final static double stepInHours = " + String.valueOf(Double.parseDouble(theModel.getValue("track/secstep"))/3600.0)+";");    
    out.println("  public final static double _initialLongitude = " + theModel.getValue("track/longitude") + ";");
    out.println("  public final static double _initialLatitude = " + theModel.getValue("track/latitude") + ";");
    out.println("  public final static int _layers = " + theModel.getTag("initialconditions").getAttribute("size")+ ";");
    out.println("  public static long _seed = " +theModel.getValue("seed")+";");
    out.println("  public static boolean _seedForced = false;");
    out.println("  public final static double c_m = 1.0;");
    out.println("  public final static double I_d0 = 1.0;");
    out.println("  public final static double J_ws = 1.0;");
    out.println("  public final static double m_m3 = 1.0;");
    out.println("  public final static double P_c = 1.0;");
    out.println("  public final static double s_h = 0.0;");
    out.println("  public final static double u_mg = 1000.0;");
    out.println("  public final static double[] _pLayerDepths = getDepthRefList();");
    out.println("  public static double _gmtDay = 0.0;");
    out.println("  public static double _gmtLeap = 0.0;");
    out.println("  private static final TimeZone GMTTimeZone = TimeZone.getTimeZone(\"GMT\");");
    out.println("  public static final GregorianCalendar _gc = new GregorianCalendar(GMTTimeZone);");
    out.print("  public static boolean _skipBoundary = ");
    if (theModel.getTag("jobs/restart").getAttribute("skipboundary")==null) out.println("false;");
    else out.println(theModel.getTag("jobs/restart").getAttribute("skipboundary")+";");
    out.print("   public static long _firstExecTime = ");
    if ((theModel.getTag("jobs/restart").getAttribute("snaptime")==null) ||  
       (theModel.getTag("jobs/restart").getAttribute("load")==null) ||
       (theModel.getTag("jobs/restart").getAttribute("load").equals("false"))) out.println("startTime;");
    else out.println(theModel.getTag("jobs/restart").getAttribute("snaptime")+"L;");
    out.println("");
    out.println("  public static int getDayOfYear(long step) {");
    out.println("    _gc.setTimeInMillis(step);");
    out.println("    return _gc.get(GregorianCalendar.DAY_OF_YEAR);");
    out.println("  }");
    out.println("");
    out.println("  public static int getLeapFlag(long step) {");
    out.println("    _gc.setTimeInMillis(step);");
    out.println("    return (_gc.isLeapYear(_gc.get(GregorianCalendar.YEAR)))?1:0;");
    out.println("  }");
    out.println("");
    out.println("  public static double getHourOfDay(long step) {");
    out.println("    _gc.setTimeInMillis(step);");
    out.println("    double hour = _gc.get(GregorianCalendar.HOUR_OF_DAY);");
    out.println("    if (_gc.get(GregorianCalendar.MINUTE)==30) hour+=0.5;");
    out.println("    return hour;");
    out.println("  }");
    
    
    for (int i=0; i<deathReasons.size(); i++) {
      out.println("  public final static int _DR_"+StringTools.nonSpace(deathReasons.get(i).toString())+" = "+i+";");
    }
    out.print("  public final static int[] stageCounts = new int[] {");
    final XMLTag[] speciesTags = theModel.getTag("species").getTags("species");
    for (int i=0; i<speciesTags.length; i++) {
      XMLTag fg = theModel.getTagWhere("functionalgroup","name",speciesTags[i].getAttribute("fg"));
      out.print(String.valueOf(fg.getTags("stage").length));
      if (i<speciesTags.length-1) out.print(",");
    }
    out.println("};");
    
    out.println("  public static boolean getStageLog(int species,  int stage) { return _stageLogs[species][stage]; }");
    out.print("  public final static boolean[][] _stageLogs = new  boolean[][] {");
    
    boolean[][] _stageLogs = new boolean[theModel.getTag("species").getTags("species").length][];
    
    for (int i=0; i<_stageLogs.length; i++) {
      XMLTag species = theModel.getTag("species").getTags("species")[i];
      XMLTag fg = theModel.getTagWhere("functionalgroup","name",species.getAttribute("fg"));
      XMLTag audit = theModel.getTag("output").getTagWhere("audit","@species",species.getAttribute("name"));     
      XMLTag[] stages = fg.getTags("stage");
      out.print("{");
      for (int j=0; j<stages.length; j++) {
        String stage = stages[j].getValue("name");
        if (audit==null) out.print("false");
        else {
          boolean  found = audit.getAttribute("stage").equals(stage); 
          found = (found || (audit.getAttribute("stage").startsWith(stage+",")));
          found = (found || (audit.getAttribute("stage").indexOf(","+stage+",")>=0));
          found = (found ||  audit.getAttribute("stage").endsWith(","+stage));
          out.print(found);
        }
        if (j<stages.length-1) out.print(",");
      }
      out.print("}");
      if (i<_stageLogs.length-1) out.print(",");
    }
    out.println("};");
    
    // ingestioncodes[species][preyspecies][preystage] 23/4/07
    out.print("  public final static int[][][] ingestionBy = new int[][][] {");
    for (int i=0; i<speciesTags.length; i++) {
      out.print("{");
      for (int j=0; j<speciesTags.length; j++) {
        out.print("{");
        final XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",speciesTags[j].getAttribute("fg"));
        final XMLTag[] stages = fgTag.getTags("stage");
        for (int k=0; k<stages.length; k++) {
          out.print("_DR_"+StringTools.nonSpace(speciesTags[i].getAttribute("name")));
          out.print("_"+StringTools.nonSpace(OutputDialog2.AGENT_BEING_INGESTED_BY));
          out.print(StringTools.nonSpace(speciesTags[j].getAttribute("name"))+"_");
          out.print(StringTools.nonSpace(stages[k].getValue("name")));
          if (k<stages.length-1) out.print(",");
        }
        out.print("}");
        if (j<speciesTags.length-1) out.print(",");
        out.println("");
        out.print("          ");
         
      }
      out.print("}");
      if (i<speciesTags.length-1) out.print(",");
      out.println("");
      out.print("      ");
    }
    out.println("};");
    out.println("");
    out.print("  public final static int[] ingestionAll = new int[] {");
    for (int i=0; i<speciesTags.length; i++) {
      out.print("_DR_"+StringTools.nonSpace(speciesTags[i].getAttribute("name")));
      out.print("_"+StringTools.nonSpace(OutputDialog2.AGENT_BEING_INGESTED_ALL));
      if (i<speciesTags.length-1) out.print(",");
    }
    out.println("};");
    out.println("");
    out.println("  public static int getIngIDFor(int type, int stage) {");
    out.println("    if (type==0) return stage;");
    out.println("    else  return stage+stageCounts[type-1];");
    out.println("  }");
        
    out.println("  public static int deadPeriod = 0;");
    out.println("");

    out.println("  public final static double[] getDepthRefList() {");
    out.println("    double[] d = new double[520];");
    out.println("    float power=-20;");
    out.println("    d[0] = 0.0;");
    out.println("    for (int i=0; i<20; i++) {");
    out.println("      d[i+1]=Math.pow(10,power);");
    out.println("      power+=0.1f;");
    out.println("    }");
    out.println("    for (int i=1; i<500; i++) d[i+20]=i;");
    out.println("    return d;");
    out.println("  }");
    out.println("");
    
    XMLTag[] Chems = theModel.getTags("chemical");
    out.println("  public static Chemical[] getNewChemicalArray(BLayer b) {");
    out.println("    Chemical[] Chems = new Chemical[" + Chems.length + "];");
    int chemNo;
    for (chemNo = 0; chemNo  <  Chems.length; chemNo++)
      out.println("     Chems[" + chemNo + "] = new " + StringTools.nonSpace(Chems[chemNo].getValue("name")) + "(b);");

    out.println("    return Chems;");
    out.println("  }");
    out.println();

    out.println("  public static void init() {");

    for (chemNo = 0; chemNo  <  Chems.length; chemNo++)
      out.println("    new " + StringTools.nonSpace(Chems[chemNo].getValue("name")) + "(null);");

    if (theModel.getTag("output/lineage")!=null) {
      if (theModel.getTag("output/lineage").getAttribute("active").equals("true")) {
        final long fromDate = Long.parseLong(theModel.getTag("output/lineage").getAttribute("from"));
        final long toDate = Long.parseLong(theModel.getTag("output/lineage").getAttribute("to"));
        out.println("    Logging.setLineage(true,"+fromDate+"L,"+toDate+"L);");
      }  
    }
    if (theModel.getTag("output/lifespan")!=null) {
      if (theModel.getTag("output/lifespan").getAttribute("active").equals("true")) {
        final long fromDate = Long.parseLong(theModel.getTag("output/lifespan").getAttribute("from"));
        final long toDate = Long.parseLong(theModel.getTag("output/lifespan").getAttribute("to"));
        out.println("    Logging.setLifespan(true,"+fromDate+"L,"+toDate+"L);");
      }  
    }
    
    out.println("    Logging.init("+String.valueOf(theModel.getTag("species").getTags("species").length) + ");");
    out.println("    Logging.setFloat(true);");
    out.println("    Logging.setZip(true);");    
    for (int i=0; i<speciesTags.length; i++) {
      String fg = StringTools.nonSpace(speciesTags[i].getAttribute("fg"));
      String species = StringTools.nonSpace(speciesTags[i].getAttribute("name"));
      out.println("    "+fg+" _"+species+" = new "+fg+"();");
      out.println("    _"+species+".params.set"+species+"Params();");
      out.println("    Kernel.registerSpecies(_"+species+");");
    }
    out.println("  }");
    out.println("");
    CompileEvents(out, theModel);

    out.println("  public static void AddDeathReason(XMLTag  addTo,String id, String reason) {");
    out.println("    XMLTag  t = new XMLTag(\"death\");");
    out.println("    t.setAttribute(\"id\",id);");
    out.println("    t.setAttribute(\"reason\",reason);");
    out.println("    addTo.addTag(t);");
    out.println("  }");
    out.println("");
    out.println("  public static void AddStateID(XMLTag addTo,String id, String state) {");
    out.println("    XMLTag t = new XMLTag(\"state\");");
    out.println("    t.setAttribute(\"id\",id);");
    out.println("    t.setAttribute(\"name\",state);");
    out.println("    addTo.addTag(t);");
    out.println("  }");

    out.println("  public static void AddStateLink(XMLTag addTo, int from, int to, String type) {");
    out.println("    XMLTag t = new XMLTag(\"transition\");");
    out.println("    t.setAttribute(\"from\",String.valueOf(from));");
    out.println("    t.setAttribute(\"to\",String.valueOf(to));");
    out.println("    t.setAttribute(\"type\",type);");
    out.println("    addTo.addTag(t);");
    out.println("  }");

    out.println("");
    out.println("  public static void InitDeathReasons(XMLTag t) {");
    for (int i=0; i<deathReasons.size(); i++)
      out.println("    AddDeathReason(t,\"" + i + "\",\"" + StringTools.nonSpace(((String) deathReasons.get(i))) + "\");");
    out.println("  }");
    out.println("");
    out.println("  public static int countDeathReasons() { return " + deathReasons.size() + "; }");
    out.println("");
    XMLTag[] fgs=theModel.getTags("functionalgroup");
    out.println("  public static void InitStates(XMLTag t) {");
    out.println("    XMLTag fgTag;");
    for (int fgNo=0; fgNo<fgs.length; fgNo++) {
      out.println("    fgTag = new XMLTag(\"functionalgroup\");");
      out.println("    fgTag.addTag(\"name\",\"" + fgs[fgNo].getValue("name") + "\");");
      XMLTag stateTag = new  XMLTag("functionalgroup");
      stateTag.addTag("name",  fgs[fgNo].getValue("name"));
      XMLTag[] states = fgs[fgNo].getTags("stage");
      for (int stNo=0; stNo<states.length; stNo++)
        out.println("    AddStateID(fgTag,\"" + stNo + "\",\"" + states[stNo].getValue("name") + "\");");

      /* Find  state  changes */
      XMLTag[] funcs = fgs[fgNo].getTags("function");
      for (int i=0; i<funcs.length; i++) {
        XMLTag[] eqs = funcs[i].getTags("equation");
        for (int j=0; j<eqs.length; j++) {
          String eq = eqs[j].getValue("eq");
          if (eq.indexOf("\\change") >= 0) handleStateChanges(out, eq, "change", funcs[i], states);
          if (eq.indexOf("\\pchange") >= 0) handleStateChanges(out,  eq,  "pchange", funcs[i], states);
          if (eq.indexOf("\\create") >= 0) handleStateChanges(out, eq, "create", funcs[i], states);
        }
      }
      out.println("    t.addTag(fgTag);");
    }
    out.println("  }");
    out.println("");
    
    out.println("  public static void CallInits() {");
    for (int i=0; i<fgs.length; i++) {
      out.println("    "+StringTools.nonSpace(fgs[i].getValue("name"))+".initialisePlankton();");
    }
    out.println("  }");
    out.println("");

    out.println("  public static void handleDistributions() {");
    for (int i=0; i<fgs.length; i++) {
      out.println("    "+StringTools.nonSpace(fgs[i].getValue("name"))+".handleDistributions();");
    }
    out.println("  }");
    out.println("");
    compileChemicalConservation(out, theModel);
    if (VEWController2.NOC_Mode) VerticalDiffusionDialog.compile(out,theModel.getTag("VerticalDiffusion"));
    writeFieldLogging(out, theModel);
    writePM(out,theModel);
    writeCheckpointBits(out, theModel);
    writeWCEvents(out, theModel);
    writeERA40Handler(out, theModel);
    out.println("}");
    out.flush();
    out.close();
    
  }
    /* Chemical  Conservation */

  public static void compileChemicalConservation(PrintWriter out,  XMLTag theModel) {
    out.println("  public static void conserveChemicals(WaterCol _W) {");
    XMLTag conserveTag = theModel.getTag("chemrecycle");
    XMLTag[] chems = conserveTag.getTags("chemical");
    boolean useCC = StringTools.parseBoolean(conserveTag.getValue("useCC"));
    if (useCC) {
      boolean autoDetectTime = StringTools.parseBoolean(conserveTag.getValue("detecttime"));
      boolean yearlyUpdate = StringTools.parseBoolean(conserveTag.getValue("yearlyupdate"));
      boolean autoDetectDepth = StringTools.parseBoolean(conserveTag.getValue("detectdepth"));
      boolean deepestEver = StringTools.parseBoolean(conserveTag.getValue("deepever"));
      boolean deepestYearly = StringTools.parseBoolean(conserveTag.getValue("deepyear"));
      boolean fixedDepth = StringTools.parseBoolean(conserveTag.getValue("fixdepth"));
      if (conserveTag.getValue("resetbelow")==null) conserveTag.addTag(new XMLTag("resetbelow","false"));
      boolean  resetBelow = StringTools.parseBoolean(conserveTag.getValue("resetbelow"));
      int depthToUse = Integer.parseInt(conserveTag.getValue("depthtouse"));
      long timeStart = Long.parseLong(conserveTag.getValue("start"));

      out.println("    if (Kernel.myTime>="+timeStart+"L) {");
      String SP = "";
      if (autoDetectTime) {
        out.println("      if ((deadPeriod==0) && (_W.offSeason)) {");
        out.println("        deadPeriod = " + (int) (7920 / (Integer.parseInt(theModel.getValue("track/secstep")) / 3600.0)) + ";");
        SP = "  ";
      }
      if (yearlyUpdate) {
        out.println("      if (Logging.isYearlyInterval(1, "+timeStart+"L, endTime)) {");
        SP = "  ";
      }

      if ((autoDetectDepth) && (deepestEver)) out.println(SP + "      for (int _layer=(int)(Math.floor(_W.deepestTurboclineEver)+1.0); _layer<_W.B_Layer.length; _layer++) {");
      else if ((autoDetectDepth) && (deepestYearly)) out.println(SP + "      for (int _layer=(int)(Math.floor(_W.deepestTurboclineThisYear)+1.0); _layer<_W.B_Layer.length; _layer++) {");
      else if (fixedDepth) out.println(SP + "       for (int _layer=" + depthToUse + "; _layer<_W.B_Layer.length; _layer++) {");

      for (int j=0; j< chems.length; j++) {
        String ch = chems[j].getValue();
        out.println(SP+"        if (_W.B_Layer[_layer].released_track["+ch+".ID_"+ch+"]>_W.B_Layer[_layer].solution_chem["+ch+".ID_"+ch+"]) _W.B_Layer[_layer].released_track["+ch+".ID_"+ch+"] = _W.B_Layer[_layer].solution_chem["+ch+".ID_"+ch+"];");
        out.println(SP+"        _W.B_Layer[0].solution_chem["+ch+".ID_"+ch+"]+=_W.B_Layer[_layer].released_track["+ch+".ID_"+ch+"];");
        out.println(SP+"        _W.B_Layer[_layer].solution_chem["+ch+".ID_"+ch+"]-=_W.B_Layer[_layer].released_track["+ch+".ID_"+ch+"];");          
        out.println(SP+"        _W.B_Layer[_layer].released_track["+ch+".ID_"+ch+"]=0.0;");
      }
      out.println(SP + "      }");

      if (resetBelow) {  
        if ((autoDetectDepth) && (deepestEver)) out.println(SP + "      for (int _layer=0; _layer<(int)(Math.floor(_W.deepestTurboclineEver)); _layer++) {");
        else if ((autoDetectDepth) && (deepestYearly)) out.println(SP + "       for (int _layer=0; _layer<(int)(Math.floor(_W.deepestTurboclineThisYear)); _layer++) {");
        else if (fixedDepth) out.println(SP + "       for (int _layer=0; _layer<" + depthToUse + "-1; _layer++) {");
        for (int j=0; j< chems.length; j++) {
          String ch = chems[j].getValue();
          out.println(SP+"        _W.B_Layer[_layer].released_track["+ch+".ID_"+ch+"]=0.0;");
        }
        out.println(SP+"      }");
      }

      if ((autoDetectTime) || (yearlyUpdate)) {
        if ((autoDetectDepth) && (deepestYearly)) out.println("        _W.deepestTurboclineThisYear=0.0;");
        if (yearlyUpdate) out.println("      }");
        else out.println("      }  else if (deadPeriod>0) deadPeriod--;");
        
      }
      out.println("    }");
    }
    out.println("  }");
    out.println("");
    out.println("  public static void conserveDropout(FunctionalGroup  _fg) {");
    if (useCC) {
      final XMLTag[] species = theModel.getTag("species").getTags("species");
      out.print("    ");
      for (int j=0; j<species.length; j++) {
        out.println("if (_fg.getParams()._type=="+j+") {");
        final XMLTag fg = theModel.getTagWhere("functionalgroup","name",species[j].getAttribute("fg"));
        out.println("      final "+StringTools.nonSpace(fg.getValue("name"))+" fg = ("+StringTools.nonSpace(fg.getValue("name"))+") _fg;");
        for (int i=0; i<chems.length; i++) {
          final String c = StringTools.nonSpace(chems[i].getValue());
          final int chemID = BlackBox.getIDForChemical(c,theModel);
          boolean[] poolInUse = BlackBox.getChemPoolUsage(fg,theModel);
          if (poolInUse[chemID])  {
            out.println("      { final double val = fg.c[1]*fg._"+c+"_Pool_Old;");
            out.println("      fg.blayer.solution_chem["+c+".ID_"+c+"]+=val;");
            out.println("      fg.blayer.released_track["+c+".ID_"+c+"]+=val; }");
          }
        }
        
        out.print("    }");
        if (j<species.length-1) out.print(" else ");
      }
      out.println("");
    }
    out.println("  }");
  }
  
     
  public static void writeFieldLogging(PrintWriter PW, XMLTag  theModel) {
    final boolean  logColumn = theModel.getTag("output").getTag("col").getAttribute("active").equals("true"); 
    final boolean  logBio = theModel.getTag("output").getTag("fieldbio").getAttribute("active").equals("true");
    final boolean  logPhy = theModel.getTag("output").getTag("fieldphy").getAttribute("active").equals("true");
    final boolean  logDem = theModel.getTag("output").getTag("fielddemo").getAttribute("active").equals("true");
    final boolean  logChem = theModel.getTag("output").getTag("fieldchem").getAttribute("active").equals("true");   
    
    PW.println("  public static void initColData() {");
    if (logColumn) writeColumnLog(DEFINITIONS, PW, theModel);
    PW.println("  }");
    PW.println("");
    PW.println("  public static void logColData() {");
    if (logColumn) writeColumnLog(LOGGING, PW, theModel);    
    PW.println("  }");
    PW.println("");
    PW.println("  public static void initBioFieldData() {");
    if (logBio) writeBLayerBio(DEFINITIONS,  PW,  theModel);
    PW.println("  }");
    PW.println("");
    PW.println("  public static void logBioFieldData() {");
    if (logBio) writeBLayerBio(LOGGING,  PW,  theModel);   
    PW.println("  }");
    PW.println("");
    PW.println("  public static void initDemFieldData() {");
    if (logDem) writeBLayerDemo(DEFINITIONS, PW, theModel);
    PW.println("  }");
    PW.println("");
    PW.println("  public static void logDemFieldData() {");
    if (logDem) writeBLayerDemo(LOGGING, PW, theModel);    
    PW.println("  }");
    PW.println("");
    PW.println("  public static void initChemFieldData() {");
    if (logChem) writeBLayerChem(DEFINITIONS,  PW,  theModel);
    PW.println("  }");
    PW.println("");
    PW.println("  public static void logChemFieldData() {");
    if (logChem) writeBLayerChem(LOGGING,  PW,  theModel);   
    PW.println("  }");
    PW.println("");
    PW.println("  public static void initPhyFieldData() {");
    if (logPhy) writeLayerPhy(DEFINITIONS, PW, theModel);
    PW.println("  }");
    PW.println("");
    PW.println("  public static void logPhyFieldData() {");
    if (logPhy) writeLayerPhy(LOGGING, PW, theModel);    
    PW.println("  }");
    PW.println("");
  }
   
  public static  long getFreqTS(XMLTag  theModel,XMLTag  tag) {
    long freq = Integer.parseInt(tag.getAttribute("freq"));
    String unit = tag.getAttribute("unit");
    long milliStep = Integer.parseInt(theModel.getValue("track/secstep"))*1000;
    if (unit.equals("Step")) freq*=milliStep;
    else if (unit.equals("Hour")) freq*=milliStep*3600;
    else if (unit.equals("Day")) freq*=milliStep*3600*24;
    else if (unit.equals("Week")) freq*=milliStep*3600*24*7;
    return freq;
  }
  
  public static void writeBLayerChem(int mode, PrintWriter PW, XMLTag  theModel) {
    int varID=0;
    XMLTag chemOO = theModel.getTag("output").getTag("fieldchem");
    if (chemOO!=null)  {
      long chemFreq = getFreqTS(theModel,chemOO);
      long chemFrom = Long.parseLong(chemOO.getAttribute("from"));
      long chemTo = Long.parseLong(chemOO.getAttribute("to"));     
      
      if (mode==LOGGING) PW.println("    if (Logging.isTime("+chemFreq+"L,"+chemFrom+"L,"+chemTo+"L)) {");
      XMLTag[] vars = chemOO.getTags("var");
      if ((vars.length>0) && (mode==DEFINITIONS)) {
        PW.println("    Logging.setChemFile(Launcher.JarPath+File.separator+\"chData.bin\");");        
        defHeaderBC(PW,chemOO,"Chemical Environment","chData.bin","biological",theModel);
      }
      int top = (int) Double.parseDouble(chemOO.getAttribute("top"));
      int bottom = (int) Double.parseDouble(chemOO.getAttribute("bottom"));        

      if (vars.length>0) {
        if (mode==LOGGING) PW.println("      for (int _depth="+top+"; _depth<="+bottom+"; _depth++) {");

        for (int varNo=0; varNo<vars.length; varNo++) {
          XMLTag var = vars[varNo];
          String chemType = var.getAttribute("chemtype");
          String chemName = StringTools.nonSpace(var.getAttribute("chem"));
          if (mode==LOGGING) {
            if (chemType.equals(OutputDialog2.DEF_SOLN)) PW.println("        Logging.writeChemReal(Kernel.W.B_Layer[_depth].solution_chem["+chemName+".ID_"+chemName+"]);");
            else if (chemType.equals(OutputDialog2.DEF_BIOCONC)) {
              String species = var.getAttribute("species");
              String stage = var.getAttribute("stage");
              if ((species==null) || (stage==null)) {
                PW.println("        Logging.writeChemReal(Kernel.W.B_Layer[_depth].particulate_chem["+chemName+".ID_"+chemName+"]);");
              }  else {
                String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
                int specNo=0;
                while (!theModel.getTag("species").getTags()[specNo].getAttribute("name").equals(species)) specNo++;
                int stageNo=0;
                while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[stageNo].getValue("name").equals(stage)) stageNo++;
                PW.println("        Logging.writeChemReal(Kernel.W.B_Layer[_depth].particulate_chem_ss["+chemName+".ID_"+chemName+"]["+specNo+"]["+stageNo+"]);");
              }
            }  else if (chemType.equals(OutputDialog2.DEF_ING_BY)) {
              String varName = var.getAttribute("name");
              String ingBy = varName.substring(chemName.length()+OutputDialog2.CHEM_ING.length());
              String bySpecies = ingBy.substring(0,ingBy.indexOf(":")-1);
              ingBy = ingBy.substring(ingBy.indexOf(":")+2);
              String byStage = "";
              if ((var.getAttribute("stage")==null) && (var.getAttribute("species")==null)) {
                PW.println("        Logging.writeChemReal(Kernel.W.B_Layer[_depth].sumFGIng_"+chemName+"());");
              }  else {
                int bySpecNo=0;
                int byStageNo=0;
                byStage = var.getAttribute("stage");
                bySpecies = var.getAttribute("species");
                while (!theModel.getTag("species").getTags()[bySpecNo].getAttribute("name").equals(bySpecies)) bySpecNo++;
                String fg = theModel.getTag("species").getTagWhere("species","@name",bySpecies).getAttribute("fg");                
                while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[byStageNo].getValue("name").equals(byStage)) byStageNo++;
                PW.println("        Logging.writeChemReal(Kernel.W.B_Layer[_depth].sumFGIng_"+chemName+"("+bySpecNo+","+byStageNo+"));");
              }   
            }  else if (chemType.equals(OutputDialog2.DEF_ING_FROM)) {
              final String fromSpecies = StringTools.nonSpace(var.getAttribute("fromspecies"));
              final String fromStage = StringTools.nonSpace(var.getAttribute("fromstage"));
              if ((var.getAttribute("byall")!=null) && (var.getAttribute("byall").equals("true"))) {
                PW.print("        Logging.writeChemReal(Kernel.W.B_Layer[_depth]._"+chemName+"_from_");
                PW.println(fromSpecies+"_"+fromStage+"_by_all);");
              
              }  else {
                final String bySpecies = StringTools.nonSpace(var.getAttribute("byspecies"));
                final String byStage = StringTools.nonSpace(var.getAttribute("bystage"));
                PW.print("        Logging.writeChemReal(Kernel.W.B_Layer[_depth]._"+chemName+"_from_");
                PW.println(fromSpecies+"_"+fromStage+"_by_"+bySpecies+"_"+byStage+");");
              }
            }
          }  else { 
            PW.println("    var = new XMLTag(\"var\");");
            PW.println("    var.addTag(new XMLTag(\"id\",\""+(varID++)+"\"));");
            String theDesc = chemName;
            String theName = chemName;
            if (chemType.equals(OutputDialog2.DEF_SOLN)) {
              theDesc+=OutputDialog2.SOLUTION;
              theName+="$Conc";
            }  else if (chemType.equals(OutputDialog2.DEF_BIOCONC)) {
              String stage = var.getAttribute("stage");
              String species = var.getAttribute("species");
              if ((stage==null) && (species==null)) {
                theDesc+=" in all plankton";
                theName+="$BioConc:all";
              }  else { 
                theDesc+=" in "+species+" : "+stage;
                theName+="$BioConc:"+StringTools.nonSpace(species)+":"+StringTools.nonSpace(stage);
              }
            }  else if (chemType.equals(OutputDialog2.DEF_ING_BY)) {
              final String stage = var.getAttribute("stage");
              final String species = var.getAttribute("species");
              if ((stage==null) && (species==null)) {
                theDesc+=" ingested by all plankton";
                theName+="$Ing:all";
              }  else { 
                theDesc+=" ingested  by "+species+" : "+stage;
                theName+="$Ing:"+StringTools.nonSpace(species)+":"+StringTools.nonSpace(stage);
              }
            }  else if (chemType.equals(OutputDialog2.DEF_ING_FROM)) {
              final String stage = var.getAttribute("fromstage");
              final String species = var.getAttribute("fromspecies");
              theDesc= chemName+" ingested from "+species+" : "+stage+" by ";
              theName = chemName+"$Ingested_from_"+StringTools.nonSpace(species)+":"+StringTools.nonSpace(stage)+"_by_";
              if ((var.getAttribute("byall")!=null) && (var.getAttribute("byall").equals("true"))) {
                theDesc+="all";
                theName+="all";
              }  else {
                final String bystage = var.getAttribute("bystage");
                final String byspecies = var.getAttribute("byspecies");
                theDesc+=byspecies+" : "+bystage;
                theName+=byspecies+":"+bystage;
              }
            }
            PW.println("    var.addTag(new XMLTag(\"name\",\""+theName+"\"));");
            PW.println("    var.addTag(new XMLTag(\"desc\",\""+theDesc+"\"));");
            PW.println("    fieldTag.addTag(var);");
          }
        }
        if (mode==LOGGING) PW.println("       }");
      }
      if (mode==LOGGING) PW.println("     }");
    }
  }
  
  public static void defHeaderBC(PrintWriter PW, XMLTag  ft,  String env,  String data, String  layers,  XMLTag theModel) {
    PW.println("    XMLTag fieldTag = new XMLTag(\"field\");");
    PW.println("    fieldTag.addTag(\"name\",\""+env+"\");");
    PW.println("    fieldTag.addTag(\"data\",\""+data+"\");");
    PW.println("    fieldTag.addTag(\"layers\",\""+layers+"\");");
    PW.println("    fieldTag.addTag(\"zip\",\"true\");");        
    PW.println("    XMLTag dims = new  XMLTag(\"dimensions\");");
    PW.println("    XMLTag dim1 = new  XMLTag(\"dim\");");
    PW.println("    dim1.setAttribute(\"index\",\"0\");");
    PW.println("    final long startLog = Math.max(SysVars._firstExecTime,"+ft.getAttribute("from")+"L);");
    PW.println("    dim1.setAttribute(\"start\",String.valueOf(startLog));");
    PW.println("    dim1.setAttribute(\"until\",\""+ft.getAttribute("to")+"\");");       
    PW.println("    dim1.setAttribute(\"step\",\""+ft.getAttribute("freq")+"\");");
    PW.println("    dim1.setAttribute(\"label\",\"Timesteps\");");
    PW.println("    dim1.setAttribute(\"units\",\"0,timestep,1\");");
    PW.println("    dims.addTag(dim1);");
    PW.println("    XMLTag dim2 = new  XMLTag(\"dim\");");
    PW.println("    dim2.setAttribute(\"index\",\"1\");");
    PW.println("    dim2.setAttribute(\"start\",\""+ft.getAttribute("top")+"\");");
    PW.println("    dim2.setAttribute(\"end\",\""+ft.getAttribute("bottom")+"\");");       
    PW.println("    dim2.setAttribute(\"step\",\"1\");");
    PW.println("    dim2.setAttribute(\"layer\",\""+layers+"\");");
    PW.println("    dim2.setAttribute(\"label\",\"Depth\");");
    PW.println("    dim2.setAttribute(\"units\",\"0,m,1\");");       
    PW.println("    dims.addTag(dim2);");
    PW.println("    fieldTag.addTag(dims);");
    PW.println("    Logging.dataFormats.addTag(fieldTag);");
    PW.println("    XMLTag var;");
  }
  
  public static void writeBLayerBio(int mode, PrintWriter PW, XMLTag theModel) {
    int varID = 0;
    XMLTag bioOO = theModel.getTag("output").getTag("fieldbio");
    if (bioOO!=null) {
      long bioFreq = getFreqTS(theModel,bioOO);
      long bioFrom = Long.parseLong(bioOO.getAttribute("from"));
      long bioTo = Long.parseLong(bioOO.getAttribute("to"));     
      if (mode==LOGGING) PW.println("     if (Logging.isTime("+bioFreq+"L,"+bioFrom+"L,"+bioTo+"L)) {");
      int top = (int) Double.parseDouble(bioOO.getAttribute("top"));
      int bottom = (int) Double.parseDouble(bioOO.getAttribute("bottom"));
      XMLTag[] vars = bioOO.getTags("var");
      if ((vars.length>0) && (mode==DEFINITIONS)) {
        PW.println("    Logging.setBioFile(Launcher.JarPath+File.separator+\"biData.bin\");");       
        defHeaderBC(PW,bioOO,"Biological Environment","biData.bin","biological",theModel);
      }
      if (vars.length>0) {
       if (mode==LOGGING) PW.println("      for (int _depth="+top+"; _depth<="+bottom+"; _depth++) {");
        
        for (int varNo=0; varNo<vars.length; varNo++) {
          XMLTag var = vars[varNo];
          String species = var.getAttribute("species");
          String stage = var.getAttribute("stage");
          String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
          int specNo=0;
          while (!theModel.getTag("species").getTags()[specNo].getAttribute("name").equals(species)) specNo++;
          int stageNo=0;
          while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[stageNo].getValue("name").equals(stage)) stageNo++;
        
          if (mode==LOGGING) {
            PW.println("        Logging.writeBioReal(Kernel.W.B_Layer[_depth].SpeciesConcentration["+specNo+"]["+stageNo+"]);");
          }  else {
            PW.println("    var = new  XMLTag(\"var\");");
            PW.println("    var.addTag(\"id\",\""+(varID++)+"\");");
            PW.println("    var.addTag(\"name\",\""+species+"$Star:"+stage+"\");");
            PW.println("    var.addTag(\"desc\",\""+var.getAttribute("name")+"\");");
            PW.println("    fieldTag.addTag(var);");
          }
        }
        if (mode==LOGGING) PW.println("       }");
      }
      if (mode==LOGGING) PW.println("     }");
    }
  }
  
  public static void writeBLayerDemo(int mode, PrintWriter PW, XMLTag  theModel) {
    int varID = 0;
    XMLTag demOO = theModel.getTag("output").getTag("fielddemo");
    if (demOO!=null) {
      long demFreq = getFreqTS(theModel,demOO);
      long demFrom = Long.parseLong(demOO.getAttribute("from"));
      long demTo = Long.parseLong(demOO.getAttribute("to"));     
      if (mode==LOGGING) PW.println("     if (Logging.isTime("+demFreq+"L,"+demFrom+"L,"+demTo+"L)) {");
      XMLTag[] vars = demOO.getTags("var");
      if ((vars.length>0) && (mode==DEFINITIONS)) {
        PW.println("    Logging.setDemFile(Launcher.JarPath+File.separator+\"demData.bin\");");        
        defHeaderBC(PW,demOO,"Demographic Information","demData.bin","biological",theModel);
      }
      int top = (int) Double.parseDouble(demOO.getAttribute("top"));
      int bottom = (int) Double.parseDouble(demOO.getAttribute("bottom"));

      if (vars.length>0) {
        if (mode==LOGGING) PW.println("       for (int _depth="+top+"; _depth<="+bottom+"; _depth++) {");
        for (int varNo=0; varNo<vars.length; varNo++) {
          XMLTag var = vars[varNo];
          String varName = var.getAttribute("name");
          String species = var.getAttribute("species");
          String stage = var.getAttribute("stage");
          String deathReason = varName.substring(0,varName.indexOf(":")-1);
          String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
          int specNo=0;
          while (!theModel.getTag("species").getTags()[specNo].getAttribute("name").equals(species)) specNo++;
          int stageNo=0;
          while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[stageNo].getValue("name").equals(stage)) stageNo++;
          if (mode==LOGGING) {
            if (varName.startsWith(OutputDialog2.AGENT_NO_AGENTS+" : ")) {
              PW.println("        Logging.writeDemReal(Utils.getLayerAgents(Kernel.W.B_Layer[_depth],"+specNo+","+stageNo+"));");
            }  else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_ALL+" : ")) {
              PW.println("        Logging.writeDemReal(Kernel.W.B_Layer[_depth].SpeciesStateDemography["+specNo+"]["+stageNo+"][ingestionAll["+specNo+"]]);");
              
            }  else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_BY+" : ")) {
              String predator = varName.substring(OutputDialog2.AGENT_BEING_INGESTED_BY.length()+3);
              final String predatorSpecies = predator.substring(0,predator.indexOf(":")-1);
              int predID=0;
              while (!theModel.getTag("species").getTags("species")[predID].getAttribute("name").equals(predatorSpecies)) predID++;
              String predFG = theModel.getTag("species").getTags("species")[predID].getAttribute("fg");
              predator = predator.substring(predator.indexOf(":")+2);
              final String predatorStage = predator.substring(0,predator.indexOf(":")-1);
              int predStage=0;
              while (!theModel.getTagWhere("functionalgroup","name",predFG).getTags("stage")[predStage].getValue("name").equals(predatorStage)) predStage++;
              predator = predator.substring(predator.indexOf(":")+2);
              final String preySpecies = predator.substring(0,predator.indexOf(":")-1);
              int preyID=0;
              while (!theModel.getTag("species").getTags("species")[preyID].getAttribute("name").equals(preySpecies)) preyID++;
              
              PW.print("        Logging.writeDemReal(Kernel.W.B_Layer[_depth].SpeciesStateDemography["+specNo+"]["+stageNo+"][");
              PW.println("ingestionBy["+preyID+"]["+predID+"]["+predStage+"]]);");
            }  else {
              PW.println("        Logging.writeDemReal(Kernel.W.B_Layer[_depth].SpeciesStateDemography["+specNo+"]["+stageNo+"][_DR_"+StringTools.nonSpace(var.getAttribute("species")+"_"+deathReason)+"]);");
            }
          }  else {
            PW.println("    var = new  XMLTag(\"var\");");
            PW.println("    var.addTag(\"id\",\""+(varID++)+"\");");
            if (varName.startsWith(OutputDialog2.AGENT_NO_AGENTS+" : ")) {
              PW.println("    var.addTag(\"name\",\""+species+"$Count:"+stage+"\");");
              PW.println("    var.addTag(\"desc\",\""+stage+"  "+species+"  agents\");");
            }  else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_ALL+" : ")) {
              PW.println("    var.addTag(\"name\",\""+species+"$Ingest:"+stage+"\");");
              PW.println("    var.addTag(\"desc\",\"Ingested "+species+" "+stage+"\");");
            }  else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_BY+" : ")) {
              PW.println("    var.addTag(\"name\",\""+species+"$Ingest:"+stage+"\");");
              String predator = varName.substring(OutputDialog2.AGENT_BEING_INGESTED_BY.length()+3);
              final String predatorSpecies = predator.substring(0,predator.indexOf(":")-1);
              predator = predator.substring(predator.indexOf(":")+2);
              final String predatorStage = predator.substring(0,predator.indexOf(":")-1);
              predator = predator.substring(predator.indexOf(":")+2);
              final String preySpecies = predator.substring(0,predator.indexOf(":")-1);
              final String preyStage = predator.substring(predator.indexOf(":")+2);
              PW.println("    var.addTag(\"desc\",\"Ingested by "+predatorStage+" "+predatorSpecies+" : "+preyStage+" "+preySpecies+"\");");
            
            }  else {
            
              PW.println("    var.addTag(\"name\",\""+StringTools.nonSpace(varName)+"\");");
              PW.println("    var.addTag(\"desc\",\""+varName+" "+stage+" "+species+"\");");
            }
            
            PW.println("    fieldTag.addTag(var);");
          }
        }
        if (mode==LOGGING) PW.println("       }");
      }
      if (mode==LOGGING) PW.println("     }");
    }
  }

  
  public static void writeLayerPhy(int mode, PrintWriter PW, XMLTag  theModel) {
    int varID = 0;
    XMLTag phyOO = theModel.getTag("output").getTag("fieldphy");
    if (phyOO!=null) {
      long phyFreq = getFreqTS(theModel,phyOO);
      long phyFrom = Long.parseLong(phyOO.getAttribute("from"));
      long phyTo = Long.parseLong(phyOO.getAttribute("to"));
      double phyTop = Double.parseDouble(phyOO.getAttribute("top"));
      double phyBottom = Double.parseDouble(phyOO.getAttribute("bottom"));
      int phyTopIndex = (int) phyTop;
      int phyBottomIndex = (int) phyBottom;      
      double[] pDepth = getDepthRefList();
      while (pDepth[phyTopIndex]<phyTop) phyTopIndex++;
      while (pDepth[phyBottomIndex]<phyBottom) phyBottomIndex++;     
      if (mode==LOGGING) PW.println("     if (Logging.isTime("+phyFreq+"L,"+phyFrom+"L,"+phyTo+"L)) {");
      XMLTag[] vars = phyOO.getTags("var");
      if ((vars.length>0) && (mode==DEFINITIONS)) {
        PW.println("    Logging.setPhyFile(Launcher.JarPath+File.separator+\"phData.bin\");");       
        defHeaderBC(PW,phyOO,"Physical Environment","phData.bin","physics",theModel);
      }
      if (vars.length>0) {
        if (mode==LOGGING) {
          PW.println("      for (int _depth="+phyTopIndex+"; _depth<="+phyBottomIndex+"; _depth++) {");
          PW.println("        PLayer _theLayer = Utils.findPLayer(_pLayerDepths[_depth]);");
        }           
          
        for (int varNo=0; varNo<vars.length; varNo++) {
          XMLTag var = vars[varNo];
          String varName = var.getAttribute("name");
        
          if (mode==LOGGING) {
            if (varName.equals(OutputDialog2.VISIBLE_IRRADIANCE)) PW.println("        Logging.writePhyReal(_theLayer.Vis_Irrad);");
            else if (varName.equals(OutputDialog2.FULL_IRRADIANCE)) PW.println("        Logging.writePhyReal(_theLayer.Full_Irrad);");
            else if (varName.equals(OutputDialog2.TEMPERATURE)) PW.println("        Logging.writePhyReal(_theLayer.Temp);");
            else if (varName.equals(OutputDialog2.TEMPADJ)) PW.println("        Logging.writePhyReal(_theLayer.Temp+_theLayer.TempAdj);");           
            else if (varName.equals(OutputDialog2.SALINITY)) PW.println("         Logging.writePhyReal(_theLayer.Salinity);");
            else if (varName.equals(OutputDialog2.DENSITY)) PW.println("        Logging.writePhyReal(_theLayer.Density);");
          }  else { 
            PW.println("    var = new  XMLTag(\"var\");");
            PW.println("    var.addTag(\"id\",\""+(varID++)+"\");");
            PW.println("    var.addTag(\"name\",\""+varName+"\");");
            PW.println("    var.addTag(\"desc\",\""+varName+"\");");         
            PW.println("    fieldTag.addTag(var);");
          }
        }
        if (mode==LOGGING) PW.println("       }");
      }
      if (mode==LOGGING) PW.println("     }");
    }
  }
  
  public static void writeColumnLog(int mode, PrintWriter PW, XMLTag theModel) {
    int varID = 0;
    XMLTag colOO = theModel.getTag("output/col");
    if (colOO!=null) {
      long colFreq = getFreqTS(theModel,colOO);
      long colFrom = Long.parseLong(colOO.getAttribute("from"));
      long colTo = Long.parseLong(colOO.getAttribute("to"));     

      
      if (mode==LOGGING) PW.println("     if (Logging.isTime("+colFreq+"L,"+colFrom+"L,"+colTo+"L)) {");
      XMLTag[] vars = colOO.getTags("var");
      if ((vars.length>0) && (mode==DEFINITIONS)) {
        PW.println("    XMLTag colTag = new XMLTag(\"environment\");");
        PW.println("    colTag.addTag(new XMLTag(\"name\",\"Water Column\"));");
        PW.println("    colTag.addTag(new XMLTag(\"data\",\"wcData.bin\"));");
        PW.println("    Logging.setWcFile(Launcher.JarPath+File.separator+\"wcData.bin\");");
        PW.println("    colTag.addTag(new XMLTag(\"zip\",\"true\"));");
        PW.println("    colTag.addTag(new XMLTag(\"layers\",\"single\"));");
        PW.println("    XMLTag outTag = new XMLTag(\"output\");");
        PW.println("    final long startLog = Math.max(SysVars._firstExecTime,"+colOO.getAttribute("from")+"L);");
        PW.println("    outTag.addTag(new XMLTag(\"after\",String.valueOf(startLog)));"); 
        PW.println("    outTag.addTag(new XMLTag(\"until\",\""+colOO.getAttribute("to")+"\"));");
        PW.println("    outTag.addTag(new XMLTag(\"freq\",\""+colOO.getAttribute("freq")+"\"));");
        PW.println("    colTag.addTag(outTag);");
        PW.println("    Logging.dataFormats.addTag(colTag);");
        PW.println("    XMLTag var;");
      }
      for (int i=0; i<vars.length; i++) {
        XMLTag var = vars[i];
    
    
        String varName = var.getAttribute("name");
        String scope = var.getAttribute("scope");
        if (scope.equals(OutputDialog2.SCOPE_WC_BIOLOGICAL)) { //  Biological - always  concentration  of individuals
          String species = var.getAttribute("species");
          String stage = var.getAttribute("stage");
          String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
          XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",fg);
          int specNo=0;
          while (!theModel.getTag("species").getTags()[specNo].getAttribute("name").equals(species)) specNo++;
          int stageNo=0;
          while (!fgTag.getTags("stage")[stageNo].getValue("name").equals(stage)) stageNo++;

          if (mode==LOGGING) {
            PW.println("      Logging.writeWCReal(Kernel.W.SpeciesTotal["+specNo+"]["+stageNo+"]);");
          }  else {
            PW.println("    var = new XMLTag(\"var\");");
            PW.println("    var.addTag(new XMLTag(\"id\",\""+(varID++)+"\"));");
            PW.println("    var.addTag(new XMLTag(\"name\",\""+varName+"\"));");
            PW.println("    var.addTag(new XMLTag(\"desc\",\""+varName+"\"));");
            PW.println("    colTag.addTag(var);");
          }
        
        }  else if (scope.equals(OutputDialog2.SCOPE_WC_CHEMICAL)) {  // Chemical (sol/part)
          String chemical = var.getAttribute("chem");
          String chemType = var.getAttribute("chemtype");
          if (mode==LOGGING) {
            if (chemType.equals(OutputDialog2.DEF_SOLN)) PW.println("      Logging.writeWCReal(Kernel.W.solution_chem["+chemical+".ID_"+chemical+"]);");
            else if (chemType.equals(OutputDialog2.DEF_BIOCONC)) {
              if ((var.getAttribute("stage")!=null) && (var.getAttribute("species")!=null)) {
                String stage = var.getAttribute("stage");
                String species = var.getAttribute("species");
                String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
                int specNo=0;
                while (!theModel.getTag("species").getTags()[specNo].getAttribute("name").equals(species)) specNo++;
                int stageNo=0;
                while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[stageNo].getValue("name").equals(stage)) stageNo++;
                PW.println("      Logging.writeWCReal(Kernel.W.particulate_chem_ss["+chemical+".ID_"+chemical+"]["+specNo+"]["+stageNo+"]);");
              }  else {
                PW.println("      Logging.writeWCReal(Kernel.W.particulate_chem["+chemical+".ID_"+chemical+"]);");
              }
            }  else if (chemType.equals(OutputDialog2.DEF_ING_BY)) {
               if ((var.getAttribute("stage")==null) && (var.getAttribute("species")==null)) {
               PW.println("       Logging.writeWCReal(Kernel.W.chemicalIngAll["+chemical+".ID_"+chemical+"]);");
              }  else {
                String byStage = var.getAttribute("stage");
                String bySpecies = var.getAttribute("species");
                int bySpecNo=0;
                while (!theModel.getTag("species").getTags()[bySpecNo].getAttribute("name").equals(bySpecies)) bySpecNo++;
                String fg = theModel.getTag("species").getTagWhere("species","@name",bySpecies).getAttribute("fg");                
                int byStageNo=0;
                while (!theModel.getTagWhere("functionalgroup","name",fg).getTags("stage")[byStageNo].getValue("name").equals(byStage)) byStageNo++;
                PW.println("      Logging.writeWCReal(Kernel.W.chemicalIngTotal["+chemical+".ID_"+chemical+"]["+bySpecNo+"]["+byStageNo+"]);");
              }   
            }  else if (chemType.equals(OutputDialog2.DEF_ING_FROM)) {
              final String fromSpecies = StringTools.nonSpace(var.getAttribute("fromspecies"));
              final String fromStage = StringTools.nonSpace(var.getAttribute("fromstage"));
              if ((var.getAttribute("byall")!=null) && (var.getAttribute("byall").equals("true"))) {
                PW.print("      Logging.writeWCReal(Kernel.W._"+chemical+"_from_");
                PW.println(fromSpecies+"_"+fromStage+"_by_all);");
              
              }  else {
                final String bySpecies = StringTools.nonSpace(var.getAttribute("byspecies"));
                final String byStage = StringTools.nonSpace(var.getAttribute("bystage"));
                PW.print("      Logging.writeWCReal(Kernel.W._"+chemical+"_from_");
                PW.println(fromSpecies+"_"+fromStage+"_by_"+bySpecies+"_"+byStage+");");
              }
            }
            
          }  else { 
            PW.println("    var = new XMLTag(\"var\");");
            PW.println("    var.addTag(new XMLTag(\"id\",\""+(varID++)+"\"));");
            String theDesc = chemical;
            String theName = chemical;
            if (chemType.equals(OutputDialog2.DEF_SOLN)) {
              theDesc+=OutputDialog2.SOLUTION;
              theName+="$TotalConc";
            }  else if (chemType.equals(OutputDialog2.DEF_BIOCONC)) {
              String stage = var.getAttribute("stage");
              String species = var.getAttribute("species");
              if ((stage==null) && (species==null)) {
                theDesc+=" in all plankton";
                theName+="$TotalBioConc:all";
              }  else { 
                theDesc+=" in "+species+" : "+stage;
                theName+="$TotalBioConc:"+StringTools.nonSpace(species)+":"+StringTools.nonSpace(stage);
              }
            }  else if (chemType.equals(OutputDialog2.DEF_ING_BY)) {
              String stage = var.getAttribute("stage");
              String species = var.getAttribute("species");
              if ((stage==null) && (species==null)) {
                theDesc+=" ingested by all plankton";
                theName+="$TotalIng:all";
              }  else { 
                theDesc+=" ingested by "+species+" : "+stage;
                theName+="$TotalIng:"+StringTools.nonSpace(species)+":"+StringTools.nonSpace(stage);
              }
            }  else if (chemType.equals(OutputDialog2.DEF_ING_FROM)) {
              final String stage = var.getAttribute("fromstage");
              final String species = var.getAttribute("fromspecies");
              theDesc= chemical+" ingested from "+species+" : "+stage+" by ";
              theName = chemical+"$Ingested_from_"+StringTools.nonSpace(species)+":"+StringTools.nonSpace(stage)+"_by_";
              if ((var.getAttribute("byall")!=null) && (var.getAttribute("byall").equals("true"))) {
                theDesc+="all";
                theName+="all";
              }  else {
                final String bystage = var.getAttribute("bystage");
                final String byspecies = var.getAttribute("byspecies");
                theDesc+=byspecies+" : "+bystage;
                theName+=byspecies+":"+bystage;
              }
            }
            PW.println("    var.addTag(new XMLTag(\"name\",\""+theName+"\"));");
            PW.println("    var.addTag(new XMLTag(\"desc\",\""+theDesc+"\"));");
            PW.println("    colTag.addTag(var);");
          }
        
        }  else if (scope.equals(OutputDialog2.SCOPE_WC_DEMOGRAPHIC)) { //  Demographic  -  agent  count, ingestion and death reasons.
          String species = var.getAttribute("species");
          String stage = var.getAttribute("stage");
          String deathReason = varName.substring(0,varName.indexOf(":")-1);
          
          String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
          XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",fg);
          int specNo=0;
          while (!theModel.getTag("species").getTags()[specNo].getAttribute("name").equals(species)) specNo++;
          int stageNo=0;
          while (!fgTag.getTags("stage")[stageNo].getValue("name").equals(stage)) stageNo++;

          if (mode==LOGGING) {
            if (varName.startsWith(OutputDialog2.AGENT_NO_AGENTS+" : ")) {
              PW.println("      Logging.writeWCReal(Kernel.W.AgentCounts["+specNo+"]["+stageNo+"]);");
            }  else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_ALL+" : ")) {
              PW.println("      Logging.writeWCReal(Kernel.W.SpeciesStateDemographyCol["+specNo+"]["+stageNo+"][ingestionAll["+specNo+"]]);");
            }  else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_BY+" : ")) {
              String predator = varName.substring(OutputDialog2.AGENT_BEING_INGESTED_BY.length()+3);
              final String predatorSpecies = predator.substring(0,predator.indexOf(":")-1);
              predator = predator.substring(predator.indexOf(":")+2);
              final String predatorStage = predator.substring(0,predator.indexOf(":")-1);
              predator = predator.substring(predator.indexOf(":")+2);
              final String preySpecies = predator.substring(0,predator.indexOf(":")-1);
              PW.print("      Logging.writeWCReal(Kernel.W.SpeciesStateDemographyCol["+specNo+"]["+stageNo+"][");
              PW.print("_DR_"+StringTools.nonSpace(preySpecies)+"_");
              PW.print(StringTools.nonSpace(OutputDialog2.AGENT_BEING_INGESTED_BY));
              PW.print(StringTools.nonSpace(predatorSpecies)+"_");
              PW.println(StringTools.nonSpace(predatorStage)+"]);");
            
            }  else {
              PW.println("      Logging.writeWCReal(Kernel.W.SpeciesStateDemographyCol["+specNo+"]["+stageNo+"][_DR_"+StringTools.nonSpace(var.getAttribute("species")+"_"+deathReason)+"]);");
            }
          }  else {
            PW.println("    var = new XMLTag(\"var\");");
            PW.println("    var.addTag(new XMLTag(\"id\",\""+(varID++)+"\"));");
            if (varName.equals("Concentration of individuals")) {
              PW.println("    var.addTag(new XMLTag(\"name\",\""+species+"$TotalStar:"+stage+"\"));");
              PW.println("    var.addTag(new XMLTag(\"desc\",\"Total Concentration of "+stage+" "+species+"\"));");
            }  else if (varName.startsWith(OutputDialog2.AGENT_NO_AGENTS+" : ")) {
              PW.println("    var.addTag(new XMLTag(\"name\",\""+species+"$TotalCount:"+stage+"\"));");
              PW.println("    var.addTag(new XMLTag(\"desc\",\""+stage+" "+species+" total agents\"));");
            }  else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_ALL+" : ")) {
              PW.println("    var.addTag(new XMLTag(\"name\",\""+species+"$TotalIngest:"+stage+"\"));");
              PW.println("    var.addTag(new XMLTag(\"desc\",\"Total Ingested "+stage+" "+species+"\"));");
            }  else if (varName.startsWith(OutputDialog2.AGENT_BEING_INGESTED_BY+" : ")) {
              PW.println("    var.addTag(new XMLTag(\"name\",\""+species+"$TotalIngest:"+stage+"\"));");
              String predator = varName.substring(OutputDialog2.AGENT_BEING_INGESTED_BY.length()+3);
              final String predatorSpecies = predator.substring(0,predator.indexOf(":")-1);
              predator = predator.substring(predator.indexOf(":")+2);
              final String predatorStage = predator.substring(0,predator.indexOf(":")-1);
              predator = predator.substring(predator.indexOf(":")+2);
              final String preySpecies = predator.substring(0,predator.indexOf(":")-1);
              final String preyStage = predator.substring(predator.indexOf(":")+2);
              PW.println("    var.addTag(new XMLTag(\"desc\",\"Ingested by "+predatorStage+" "+predatorSpecies+" : "+preyStage+" "+preySpecies+"\"));");
            } else {
              PW.println("    var.addTag(new XMLTag(\"name\",\""+StringTools.nonSpace(varName)+"\"));");
              PW.println("    var.addTag(new XMLTag(\"desc\",\""+varName+" "+stage+" "+species+" total\"));");
            }
              
            PW.println("    colTag.addTag(var);");
          }
        
        }  else if (scope.equals(OutputDialog2.SCOPE_WC_PHYSICAL)) {  // Physical
          if (mode==LOGGING) {
            if (varName.equals(OutputDialog2.WC_TURBOCLINE)) {
              PW.println("      Logging.writeWCReal(Kernel.W.MLDepth);");
            }  else if (varName.equals(OutputDialog2.WC_LATITUDE)) {
              PW.println("      Logging.writeWCReal(Kernel.W.Latitude);");
            }  else if (varName.equals(OutputDialog2.WC_LONGITUDE)) {
              PW.println("      Logging.writeWCReal(Kernel.W.Longitude);");
            }  else if (varName.equals(OutputDialog2.WC_HEAT)) {
              PW.println("      Logging.writeWCReal(Kernel.W.getHeatContent());");
            }  else if (varName.equals(OutputDialog2.WC_WIND_SPEED)) {
              PW.println("      Logging.writeWCReal(Kernel.W.WSpeed);");
            }  else if (varName.equals(OutputDialog2.WC_CLOUD_COVER)) {
              PW.println("      Logging.writeWCReal(Kernel.W.Cloud_Cover);");
            }  else if (varName.equals(OutputDialog2.WC_HEAT_LOSS)) {
              PW.println("      Logging.writeWCReal(Kernel.W.Cooling);");
            }  else if (varName.equals(OutputDialog2.WC_EKMAN)) {
              PW.println("      Logging.writeWCReal(Kernel.W.Ekman);");
            }  else if (varName.equals(OutputDialog2.WC_ZENITH_HEIGHT)) {
              PW.println("      Logging.writeWCReal(Kernel.W.Z_Height);");
            }  else if (varName.equals(OutputDialog2.WC_SUN_LIGHT)) {
              PW.println("      Logging.writeWCReal(Kernel.W.SunLight);");
            }  
          }  else {
            PW.println("    var = new XMLTag(\"var\");");
            PW.println("    var.addTag(new XMLTag(\"id\",\""+(varID++)+"\"));");
            PW.println("    var.addTag(new XMLTag(\"name\",\""+varName+"\"));");
            PW.println("    var.addTag(new XMLTag(\"desc\",\""+varName+"\"));");
            PW.println("    colTag.addTag(var);");
          }
        }
      }
      if (mode==LOGGING) PW.println("     }");
    }
  }
  
  public static void writePM(PrintWriter PW, XMLTag  model) {
    PW.println("  public static final int LAYER = 1;");
    PW.println("  public static final int COLUMN = 2;");
    PW.println("  public static final int MIXING = 3;");
    PW.println("  public static final int SPLIT = 1;");
    PW.println("  public static final int MERGE = 2;");
    PW.println("  public static final int TURBO = -1;");
    PW.println("  public static final int YEARLY = -1;");
    PW.println("  public static final int ALL_TIME = -1;");    
    PW.println("");
    PW.print("  public static final long[][] PMRules = new long[][] {");
    XMLTag pm = model.getTag("particlemanagement");
    if (pm==null) PW.println("{}};");
    else {
      XMLTag[] pms = pm.getTags("rule");
      if (pms.length==0) PW.println("{}};");
      else {
        PW.println("");
        boolean merge;
        boolean notFirstRule=false;
        for (int i=0; i<pms.length; i++) {
          if (!pms[i].getAttribute("type").equals(ParticleManager2.RULE_MAINTAIN)) {
            if (notFirstRule) PW.println(",");
            PW.print("    {");
            if (pms[i].getAttribute("scope").equals(ParticleManager2.RULE_LAYER)) PW.print("LAYER,"); 
            else if (pms[i].getAttribute("scope").equals(ParticleManager2.RULE_COLUMN)) PW.print("COLUMN,");
            else if (pms[i].getAttribute("scope").equals(ParticleManager2.RULE_MIXING)) PW.print("MIXING,");
            
            merge=(pms[i].getAttribute("type").equals(ParticleManager2.RULE_MERGE));
            if (merge) PW.print("MERGE,"); else  PW.print("SPLIT,");
            int species = BlackBox.getIDForSpecies(model,pms[i].getAttribute("species"));
            PW.print(species+",");
            PW.print(StringTools.nonSpace(pms[i].getAttribute("fg"))+"._STAGE_"+StringTools.nonSpace(pms[i].getAttribute("stage"))+",");
            PW.print(pms[i].getAttribute("value")+",");
            if (!pms[i].getAttribute("scope").equals(ParticleManager2.RULE_LAYER)) 
              PW.print("0,0,");
            else {
              if (pms[i].getAttribute("alldepth").equals("true")) PW.print("0,SysVars._layers-1,");
              else {
                if (pms[i].getAttribute("fromturbo").equals("true")) PW.print("TURBO,");
                else PW.print(pms[i].getAttribute("top")+",");
                if (pms[i].getAttribute("toturbo").equals("true")) PW.print("TURBO,");
                else PW.print(pms[i].getAttribute("bottom")+",");
              }
            }
            if (pms[i].getAttribute("alltime").equals("true")) PW.print("ALL_TIME,0,0");
            else {
              PW.print(pms[i].getAttribute("start")+","+pms[i].getAttribute("end")+",");
              if ((pms[i].getAttribute("yearly")!=null) && (pms[i].getAttribute("yearly").equals("true")))
                PW.print("YEARLY");
              else PW.print("0");
            }
            PW.print("}");
            notFirstRule=true;
          }
        }
        PW.println("};");
      }
    }
  }
  
  public static void writeCheckpointBits(PrintWriter PW, XMLTag  model) {
    PW.println("  public static void initCheckpoints(boolean forceRead,  boolean  forceWrite) {");
    XMLTag cp = model.getTag("jobs/restart");
    if (cp!=null) {
      String cpw = cp.getAttribute("save");
      if ((cpw!=null) && (cpw.equals("true"))) {
        String unit = cp.getAttribute("intervalunit");
        long first = Long.parseLong(cp.getAttribute("first"));
        long interval = Long.parseLong(cp.getAttribute("interval"));
        if (unit.equals(RunPanel.HOURS)) interval = interval * 3600*1000;
        else if (unit.equals(RunPanel.DAYS)) interval = interval * 24*3600*1000;
        else if (unit.equals(RunPanel.WEEKS)) interval = interval * 7*24*3600*1000;
        else if (unit.equals(RunPanel.YEARS)) interval = interval * 365*24*3600*1000;
        
        PW.println("    if (!forceWrite) Logging.setSnap("+first+"L, "+interval+"L);");
      }
      String cpl = cp.getAttribute("load");
      if ((cpl!=null) && (cpl.equals("true"))) {
        boolean  resetSeed = false;
        if (cp.getAttribute("rnd")!=null) resetSeed = cp.getAttribute("rnd").equals("new");
        StringBuffer file = new  StringBuffer(cp.getAttribute("file"));
        for (int i=0; i<file.length(); i++) {
          if (file.charAt(i)=='\\') file.setCharAt(i,'/');
        }
        PW.println("    if (!forceRead) Logging.setSnap(\""+file+"\","+resetSeed+");");
      }
    }
    PW.println("  }");

  }
  
  public static void writeWCEvents(PrintWriter PW, XMLTag  model) {
    GregorianCalendar  startSim = new GregorianCalendar();
    GregorianCalendar  endSim = new GregorianCalendar();
    MiscUtils.findDateLimits(model,startSim, endSim);
    final int stepLengthSeconds = Integer.parseInt(model.getValue("track/secstep"));
    final long stepLengthMillis = stepLengthSeconds*1000;
    PW.println("  public static void WCEvents() {");
    XMLTag eventTag = model.getTag("events");
    if (eventTag!=null) {
      XMLTag events[] = eventTag.getTags("event");
      for (int i=0; i<events.length; i++) {
        String name="";
        if (events[i].getAttribute("scope").equals(EventPanel.WATER_COLUMN)) {
          if (events[i].getAttribute("item").equals("Wind  Speed")) name="Kernel.W.WSpeed";
          else if (events[i].getAttribute("item").equals("Total  Cloud  Cover")) name="Kernel.W.Cloud_Cover";
          else if (events[i].getAttribute("item").equals("Heat Flux")) name="Kernel.W.Cooling";
          else if (events[i].getAttribute("item").equals("Surface  Sensible Heat  Flux")) name="Kernel.W.Cooling";
          else if (events[i].getAttribute("item").equals("Surface  Latent Heat  Flux")) name="Kernel.W.Cooling";
          else if (events[i].getAttribute("item").equals("Surface  Thermal  Radiation")) name="Kernel.W.Cooling";
          else if (events[i].getAttribute("item").equals("Air  Temperature")) name="Kernel.W.Air_Temp";
          else if (events[i].getAttribute("item").equals("Relative Humidity")) name = "Kernel.W.Rel_Humidity";
          else if (events[i].getAttribute("item").equals("Ekman")) name = "Kernel.W.Ekman";
          String op = events[i].getAttribute("op");
          String val = events[i].getAttribute("val");
          if ((events[i].getAttribute("continuous")!=null) && (events[i].getAttribute("continuous").equals("true"))) {
            PW.print("    ");
          }  else {
            long startEventMillis = Long.parseLong(events[i].getAttribute("start"));
            long endEventMillis = Long.parseLong(events[i].getAttribute("end"));
            double interval = Double.parseDouble(events[i].getAttribute("interval"));
            String intervalUnit = events[i].getAttribute("intervalunit");
            if (intervalUnit.equals(EventPanel.INT_TS)) interval *= stepLengthMillis;
            else if (intervalUnit.equals(EventPanel.INT_SEC)) interval *= 1000L;
            else if (intervalUnit.equals(EventPanel.INT_HOUR)) interval *= 3600000L;
            else if (intervalUnit.equals(EventPanel.INT_DAY)) interval *= 86400000L;
            else if (intervalUnit.equals(EventPanel.INT_WEEK)) interval *= 604800000L;
            if (intervalUnit.equals(EventPanel.INT_YEAR)) {
              PW.print("    if (Logging.isYearlyInterval("+(int)interval+","+startEventMillis+"L,"+endEventMillis+"L)) ");
            }  else {
              PW.print("    if (Logging.isTime("+interval+"L,"+startEventMillis+"L,"+endEventMillis+"L)) ");
            }
          }
          PW.print(name+"=");
          CompileEventOp(PW,name,op,val);
          PW.println(";");
        }
      }
    }
    PW.println("  }");
  }
    
  
  public static void writeERA40Handler(PrintWriter PW, XMLTag  model) {
    PW.println("  public static void handleERA40() {");
    boolean  era40 = false;
    boolean  calc = false;
    if (model.getTag("boundaryconditions/climatedata")!=null)
      era40 = ((model.getValue("boundaryconditions/climatedata").equals(ScenarioPanel2.ERA40_SYNOPTIC))
           || (model.getValue("boundaryconditions/climatedata").equals(ScenarioPanel2.ERA40_MEAN_YEAR))
           || (model.getValue("boundaryconditions/climatedata").equals(ScenarioPanel2.ERA40_MONTHLY_AVG))
           || (model.getValue("boundaryconditions/climatedata").equals(ScenarioPanel2.ERA40_SINGLE)));

    if (model.getTag("boundaryconditions/heatflux")!=null)
      calc = model.getValue("boundaryconditions/heatflux").equals(ScenarioPanel2.ERA40_CALCULATE_FLUX);
    if ((era40) && (calc)) {
      PW.println("    // Calculate heat  flux using emergent  temperature");
      PW.println("");
      PW.println("    // 1.  Surface  latent Heat  Flux;");
      PW.println("");
      PW.println("    final double C_E = 1.2E-3;                                     // Bulk  transfer co-efficient  for  water  vapour");
      PW.println("    final double rho = 1.2225;                                     // density of  air, (kg m-3)");
      PW.println("    final double T_0 = ((PLayer) Kernel.W.P_LayerList.get(0)).Temp; // Temperature of  surface  water (celcius)");
//      PW.println("    final double L_v = 2.5E6 - (2.3*T_0);                           // Heat  of vaporisation.");
      PW.println("    final double q_A = Kernel.W.Rel_Humidity.get();                 // Relative  humidity from  ERA40  data");
      PW.println("    final double q_S = 6.112*Math.exp((17.67*T_0)/(T_0+243.5));     // Saturation-specific humidity  at sea surface temp");
      PW.println("    final double U_vec = Kernel.W.WSpeed.get();                     // From  ERA40  data");
      PW.println("");
//      PW.println("    final double slhf = -(C_E * rho * L_v * U_vec * (0.98 * (q_S - q_A)));");
      PW.println("    final double slhf = -(C_E * rho * U_vec * (q_S - q_A));");     
      PW.println("");
      PW.println("    // 2.  Surface  sensible heat  flux;");
      PW.println("");
      PW.println("    final double C_H = 1.2E-3;                 // Bulk  transfer coefficient for heat");
      PW.println("    final double C_p = 1846;                   // Specific  heat at  constant pressure  for  water  vapour. (Dry air = 1005)");
      PW.println("    final double T_A = Kernel.W.Air_Temp.get(); // Air temperature (K) from  ERA40");
      PW.println("");
      PW.println("    final double sshf = - (C_H * rho * C_p * U_vec * ((T_0 + 273.16)-T_A));");
      PW.println("");
      PW.println("    // 3.  Longwave radiation (Surface  Thermal  Radiation);");
      PW.println("");
      PW.println("    final double epsilon = 0.96;                     // Emisivity of  surface");
      PW.println("    final double sigma = 5.6704E-8;                   // Stefan-Boltzmann  constant,  W  m-2  K-4");
      PW.println("    final double Ch_d = Kernel.W.Cloud_Cover.get();   // Cloud-cover (0..1) from ERA40");
      PW.println("    final double rho_A = 1013;                       // Water-vapour  pressure (millibar)");
      PW.println("");
      PW.println("    final double lwr_1 = epsilon * sigma * Math.pow(T_A,4) * (0.254  - (0.00495 * rho_A)) * (1  -  Ch_d);");
      PW.println("    final double lwr_2 = 4 * epsilon * sigma * Math.pow(T_A,3) * ((T_0 + 273.16)-T_A);");
      PW.println("    final double lwr = lwr_1 + lwr_2;");
      PW.println("");
    }
    PW.println("  }");
    PW.println("");
        
    PW.println("  public static void readBoundaryStep(BoundaryDataStep bds) {");

    if (era40) {
      PW.println("    Kernel.W.WSpeed=bds.BoundaryData[0]; //  ERA40  ws magnitude");
      PW.println("    Kernel.W.Cloud_Cover=bds.BoundaryData[1]; // ERA40 tcc");

      if (!calc) {
        PW.println("    Kernel.W.Cooling=-((bds.BoundaryData[2]+bds.BoundaryData[3]+bds.BoundaryData[4])/86400.0); //  ERA40  SSHF+SLHF+STR  acc  24hr");
        PW.println("    Kernel.W.Z_Height=bds.BoundaryData[5]; //  Astronomy  -  Z_Height");
        PW.println("    Kernel.W.SunLight=bds.BoundaryData[7]; //  Astronomy  -  Irradiance");
        PW.println("    Kernel.W.Longitude=bds.BoundaryData[8];");
        PW.println("    Kernel.W.Latitude=bds.BoundaryData[9];");
      }  else {
        PW.println("    Kernel.W.Rel_Humidity=bds.BoundaryData[2]; //  ERA40  relative humidity  -  r");
        PW.println("    Kernel.W.Air_Temp=bds.BoundaryData[3];     //  ERA40  air  temp - 2t");
        PW.println("    Kernel.W.Z_Height=bds.BoundaryData[4];     //  Astronomy  -  Z_Height");
        PW.println("    Kernel.W.SunLight=bds.BoundaryData[6];     //  Astronomy  -  Irradiance");
        PW.println("    Kernel.W.Longitude=bds.BoundaryData[7];");
        PW.println("    Kernel.W.Latitude=bds.BoundaryData[8];");
        
        PW.println("    handleERA40();");
      }
    }  else {
      PW.println("    Kernel.W.WSpeed=bds.BoundaryData[0];      //  Bunker wind  speed");
      PW.println("    Kernel.W.Cloud_Cover=bds.BoundaryData[1]; // Bunker  cloud  cover");
      PW.println("    Kernel.W.Cooling=bds.BoundaryData[2];     // Bunker  oceanic  heat loss");
      PW.println("    Kernel.W.Ekman=bds.BoundaryData[3];        //  Bunker - Ekman");
      PW.println("    Kernel.W.Z_Height=bds.BoundaryData[4];    //  Z_Height");
      PW.println("    Kernel.W.SunLight=bds.BoundaryData[6];    //  Irradiance");
      PW.println("    Kernel.W.Longitude=bds.BoundaryData[7];");
      PW.println("    Kernel.W.Latitude=bds.BoundaryData[8];");

    }
    PW.println("    WCEvents();");
    PW.println("  }");
    PW.println("");
  }
}
  
