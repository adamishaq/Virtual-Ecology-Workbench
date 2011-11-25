package VEW.Compiler2;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.MiscUtils;
import VEW.Controller2.OutputDialog2;
import VEW.Planktonica2.PigmentGraph.PigmentPanel;

public class BlackBox  {
  private static String theOutputDir = null;
  private static boolean pchangeNeeded = false;
  private static int integrate_no;
  private static int VarietyLabelCount;
  private static int countCreates;
  private static final byte INFIX = 1;
  private static final byte PREFIX = 2;
  private static boolean debugMode = true;
 
  public static boolean compile(XMLTag model, String outputDir) {
    VarietyLabelCount = 0;
    countCreates = 0;
    integrate_no=0;
    ArrayList deathReasons = new ArrayList();
    theOutputDir = outputDir;
    new File(theOutputDir + "/VEW/Sim").mkdirs();
    CompileModel(model.getTag("model"),deathReasons);
    AddKernel(model);
    SysVarsCompiler.makeSysVars(model, deathReasons, theOutputDir);
    return true;
  }

  /** ********************* */
  /* The Proper Compiler! */
  /** ********************* */

  private static void status(String s) {
    System.out.println(s);
  }

  public static void CompileSetVar(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group) {
    if (eq.endsWith("$Pool")) PW.print("_"+eq.substring(0,eq.length()-5)+"_Pool_New = (");
    else if (eq.equals("z")) PW.print("z[0]=(");
    else { 
      XMLTag v = group.getTagWhere("local", "name", eq);
      if (v == null) v = group.getTagWhere("varietylocal", "name", eq);
      if (v == null) v = group.getTagWhere("*", "name", eq);
      if (v != null) {
        String tag = v.getName();
        if (tag.equals("local")) PW.print(v.getValue("name") + " = (");
        else if (tag.equals("variable")) PW.print(v.getValue("name") + "[0]=(");
        else if (tag.equals("varietyvariable")) {
          final String link = v.getAttribute("link");
          PW.println("for (int _"+link+"=0; _"+link+"<params."+link+"_types.length; _"+link+"++)");
          PW.print("      " + eq + "[_" + link + "][0]=(");

        } else if (tag.equals("varietylocal")) {
          final String link = v.getAttribute("link");
          PW.println("for (int _"+link+ "=0; _"+link+"<params."+link+"_types.length; _"+link+"++)");
          PW.print("      " + eq + "[params._speciesOfFG][_" + v.getAttribute("link") + "]");
          PW.print(" = (");
        }

        else System.out.println("NOT PRINTED: " + eq + " type = " + tag);
      } else System.out.println("NOT FOUND3: " + eq + " in " + group.getValue("name") + "." + function.getValue("name") + "." + equation.getValue("name"));
    }
  }

  public static void CompileGetVar(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    if (eq.equals("TimeStep")) PW.print("SysVars.stepInHours");
    else if (eq.equals("d_year")) PW.print("SysVars._gmtDay");
    else if (eq.equals("d_leap")) PW.print("SysVars._gmtLeap");
    else if (eq.equals(OutputDialog2.WC_TURBOCLINE)) PW.print("Kernel.W.MLDepth");
    else if (eq.equals("MLDepth")) PW.print("Kernel.W.MLDepth");
    else if (eq.equals("Max_MLD")) PW.print("Kernel.W.Max_MLD");
    else if (eq.equals("PI")) PW.print("Math.PI");
    
    // Deal with internal pools
    
    else if (eq.endsWith("$Pool")) {
      final String chemName = eq.substring(0,eq.length()-5);
      PW.print("_"+chemName+"_Pool_Old");
      
    } else if (eq.endsWith("$Ingested")) {
      final String chemName = eq.substring(0,eq.length()-9);
      PW.print("_"+chemName+"_Ing");
      
    } else if (eq.equals("z")) {
      if (fgUpdate) PW.print("z[1]");
      else PW.print("z");
    
    // Deal with ambient environment parts.
    
    
    } else if (eq.endsWith("$Conc")) {
      String chemName = eq.substring(0,eq.length()-5);
      PW.print("blayer.solution_chem["+chemName+".ID_"+chemName+"]/blayer.Height");
    
    } else if ((eq.equals(OutputDialog2.FULL_IRRADIANCE)) || (eq.equals("Full_Irrad"))) {
      if (fgUpdate) PW.print("Utils.getFullIrrad(z[1])");
      else PW.print("Utils.getFullIrrad(z)");
      
    } else if ((eq.equals(OutputDialog2.VISIBLE_IRRADIANCE)) || (eq.equals("Vis_Irrad"))) {
      PW.print("_ambientVisIrrad");
    
    } else if (eq.equals(OutputDialog2.SALINITY)) {
      if (fgUpdate) PW.print("Utils.getSalinity(z[1])");
      else PW.print("Utils.getSalinity(z)");
    
    } else if ((eq.equals(OutputDialog2.TEMPERATURE)) || (eq.equals("Temp"))) {
      PW.print("_ambientTemperature");
    
    } else if (eq.equals(OutputDialog2.DENSITY)) {
      if (fgUpdate) PW.print("Utils.getDensity(z[1])");
      else PW.print("Utils.getDensity(z)");
    
    } else {
      XMLTag v = null;
      if (group != null) {
        if (v == null) v = group.getTagWhere("local", "name", eq);
        if (v == null) v = group.getTagWhere("varietylocal", "name", eq);
        if (v == null) v = group.getTagWhere("*", "name", eq);
      }
      
      if ((v == null) && (group.getTag("predator") != null) && (model.getTag("closure") != null) && (group.getValue("predator").equals("true"))) 
        v = model.getTag("closure").getTagWhere("predator", "name", group.getValue("name")).getTagWhere("*", "name", eq);
      if (v != null) {
        final String tag = v.getName();
        final String vname = v.getValue("name");
        final String cname = v.getValue("codename");
        final String vlink = v.getAttribute("link");
        if (((tag.equals("variable")) || (tag.equals("fgvar")))) PW.print(vname+"[1]");
        else if (tag.equals("sizevar")) PW.print(cname);
        else if (tag.equals("parameter")) PW.print("params." + vname);
        else if (tag.equals("local")) PW.print(vname);
        else if (tag.equals("sysvar")) PW.print(cname);
        else if (tag.equals("varietyparameter")) PW.print("params." + vname+"[_"+vlink+"]");
          
        else if (tag.equals("varietyvariable")) {
          PW.print(vname);
          if (v.getValue("name").equals("IngestedCells")) {
            PW.print("[_ingIndex]");
          } else {
            PW.print("[_"+vlink+"][1]");
          }
  
        } else if (tag.equals("varietylocal")) {
          PW.print(vname+"[");
          PW.print("params._speciesOfFG]");
          PW.print("[_"+ vlink+"]");
  
        } else if (tag.equals("varietyconcentration")) {
          if ((vname.equals("Ingestion")) || (vname.equals("IngestedCells")))
            PW.print("IngestedCells[_ingIndex]");
          else {
            PW.print("blayer.SpeciesConcentration_old[");
            PW.print("params."+vname+"_types[_"+vname+"]][");
            PW.print("params."+vname+"_status[_"+vname+"]]/blayer.Height");
          }
        } else PW.print(vname);
      } else {
        System.out.println("NOT FOUND1: " + eq + " in " + group.getValue("name") + "." + function.getValue("name") + "." + equation.getValue("name"));
      }
    }
  }

  public static void CompileVal(String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    String[] leftAndRight = StringTools.nestedSplit(eq, ',');
    String sival = StringTools.spit(leftAndRight[0], "\\sival{");
    String[] sivalParts = StringTools.nestedSplit(sival, ',');
    double l = Double.parseDouble(sivalParts[0]);
    double r = Double.parseDouble(sivalParts[1]);
    PW.print(l);
    if (r != 0) PW.print("*Math.pow(10," + r + ")");
  }

  public static void CompileVarHist(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    String[] leftAndRight = StringTools.nestedSplit(eq, ',');
    PW.print(StringTools.spit(leftAndRight[0], "\\var{") + "[1+(int)(");
    CompileExpression(model, leftAndRight[1], PW, equation, function, group, fgUpdate);
    PW.print(")]");
  }

  public static void CompileIfSwitch(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    String[] threeParts = StringTools.nestedSplit(eq, ',');

    PW.print("(");
    CompileExpression(model, threeParts[0], PW, equation, function, group, fgUpdate);
    PW.print(")?(");

    CompileExpression(model, threeParts[1], PW, equation, function, group, fgUpdate);
    PW.print("):(");
    CompileExpression(model, threeParts[2], PW, equation, function, group, fgUpdate);
    PW.print(")");
  }

  public static void CompileMulti(XMLTag model, String eq, String operator, byte opPos, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    String[] items = StringTools.nestedSplit(eq, ',');
    if (opPos == INFIX) {
      PW.print("(");
      for (int i = 0; i < items.length; i++) {
        PW.print("(");
        CompileExpression(model, items[i], PW, equation, function, group, fgUpdate);
        PW.print(")");
        if (i < items.length - 1) PW.print(operator);
      }
      PW.print(")");
    } else {
      PW.print("(" + operator + "{");
      for (int i = 0; i < items.length; i++) {
        PW.print("(");
        CompileExpression(model, items[i], PW, equation, function, group, fgUpdate);
        PW.print(")");
        if (i < items.length - 1) PW.print(", ");
      }
      PW.print("}))");
    }
  }

  public static void CompileBinary(XMLTag model, String eq, String operator, byte opPos, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    String[] leftAndRight = StringTools.nestedSplit(eq, ',');
    if (opPos == INFIX) {
      PW.print("(");
      CompileExpression(model, leftAndRight[0], PW, equation, function, group, fgUpdate);
      PW.print(") " + operator + " (");
      CompileExpression(model, leftAndRight[1], PW, equation, function, group, fgUpdate);
      PW.print(")");
    } else {
      PW.print(operator + "(");
      CompileExpression(model, leftAndRight[0], PW, equation, function, group, fgUpdate);
      PW.print(", ");
      CompileExpression(model, leftAndRight[1], PW, equation, function, group, fgUpdate);
      PW.print(")");
    }
  }

  public static void CompileUnary(XMLTag model, String eq, String operator, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    String[] leftAndRight = StringTools.nestedSplit(eq, ',');
    PW.print(operator + "(");
    CompileExpression(model, leftAndRight[0], PW, equation, function, group, fgUpdate);
    PW.print(")");
  }

  public static void CompileUnaryFunc(XMLTag model, String eq, String operator, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    String[] leftAndRight = StringTools.nestedSplit(eq, ',');
    PW.print(operator + "(");
    CompileExpression(model, leftAndRight[0], PW, equation, function, group, fgUpdate);
    PW.print(")");
  }

  public static void CompileExpression(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    if (StringTools.chomp(eq, "\\minus{")) CompileUnary(model, StringTools.spit(eq, "\\minus{"), "-", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\abs{")) CompileUnary(model, StringTools.spit(eq, "\\abs{"), "Math.abs", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\rnd{")) CompileUnary(model, StringTools.spit(eq, "\\rnd{"), "Utils.rnd", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\depthForVI{")) CompileUnary(model, StringTools.spit(eq, "\\depthForVI{"), "Utils.getDepthForVI", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\depthForFI{")) CompileUnary(model, StringTools.spit(eq, "\\depthForFI{"), "Utils.getDepthForFI", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\temperatureAt{")) CompileUnaryFunc(model, StringTools.spit(eq, "\\temperatureAt{"), "Utils.getTemperature", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\salinityAt{")) CompileUnaryFunc(model, StringTools.spit(eq, "\\salinityAt{"), "Utils.getSalinity", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\densityAt{")) CompileUnaryFunc(model, StringTools.spit(eq, "\\densityAt{"), "Utils.getDensity", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\fullIrradAt{")) CompileUnaryFunc(model, StringTools.spit(eq, "\\fullIrradAt{"), "Utils.getFullIrrad", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\visIrradAt{")) CompileUnaryFunc(model, StringTools.spit(eq, "\\visIrradAt{"), "Utils.getVisIrrad", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\UVIrradAt{")) CompileUnaryFunc(model, StringTools.spit(eq, "\\UVIrradAt{"), "Utils.getUVIrrad", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\log{")) CompileUnary(model, StringTools.spit(eq, "\\log{"), "Math.log", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\text{")) PW.print(StringTools.spit(eq, "\\text{"));
    else if (StringTools.chomp(eq, "\\log10{")) CompileUnary(model,StringTools.spit(eq, "\\log10{"), "Utils.log10", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\asin{")) CompileUnary(model,StringTools.spit(eq, "\\asin{"), "Math.asin", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\acos{")) CompileUnary(model,StringTools.spit(eq, "\\acos{"), "Math.acos", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\atan{")) CompileUnary(model,StringTools.spit(eq, "\\atan{"), "Math.atan", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\sin{")) CompileUnary(model, StringTools.spit(eq, "\\sin{"), "Math.sin", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\cos{")) CompileUnary(model, StringTools.spit(eq, "\\cos{"), "Math.cos", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\tan{")) CompileUnary(model, StringTools.spit(eq, "\\tan{"), "Math.tan", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\sqrt{")) CompileUnary(model, StringTools.spit(eq, "\\sqrt{"), "Math.sqrt", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\exp{")) CompileUnary(model, StringTools.spit(eq, "\\exp{"), "Math.exp", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\not{")) CompileUnary(model, StringTools.spit(eq, "\\not{"), "!", PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\pow{")) CompileBinary(model,StringTools.spit(eq, "\\pow{"), "Math.pow", PREFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\div{")) CompileBinary(model,StringTools.spit(eq, "\\div{"), "/", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\sub{")) CompileBinary(model,StringTools.spit(eq, "\\sub{"), "-", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\greater{")) CompileBinary(model,StringTools.spit(eq, "\\greater{"), ">", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\greaterequal{")) CompileBinary(model,StringTools.spit(eq, "\\greaterequal{"), ">=", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\equal{")) CompileBinary(model,StringTools.spit(eq, "\\equal{"), "==", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\lessequal{")) CompileBinary(model,StringTools.spit(eq, "\\lessequal{"), "<=", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\less{")) CompileBinary(model,StringTools.spit(eq, "\\less{"), "<", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\neq{")) CompileBinary(model,StringTools.spit(eq, "\\neq{"), "!=", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\conditional{")) CompileIfSwitch(model,StringTools.spit(eq, "\\conditional{"), PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\min{")) CompileMulti(model,StringTools.spit(eq, "\\min{"), "Utils.min(new double[] ", PREFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\max{")) CompileMulti(model,StringTools.spit(eq, "\\max{"), "Utils.max(new double[] ", PREFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\mul{")) CompileMulti(model,StringTools.spit(eq, "\\mul{"), "*", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\add{")) CompileMulti(model,StringTools.spit(eq, "\\add{"), "+", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\and{")) CompileMulti(model,StringTools.spit(eq, "\\and{"), "&&", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\or{")) CompileMulti(model,StringTools.spit(eq, "\\or{"), "||", INFIX, PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\var{")) CompileGetVar(model,StringTools.spit(eq, "\\var{"), PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\val{")) CompileVal(StringTools.spit(eq, "\\val{"), PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\varhist{")) CompileVarHist(model,StringTools.spit(eq, "\\varhist{"), PW, equation, function, group, fgUpdate);
  }

  public static void CompileGraphVals(String eq, PrintWriter PW) {
    eq = StringTools.spit(eq, "\\graphvals{");
    PW.print("    ");
    while (eq.length() > 2) {
      eq = StringTools.RHS(eq, StringTools.getUnNested(eq, '{'));
      String lhs = StringTools.LHS(eq, StringTools.getUnNested(eq, ','));
      String rhs = StringTools.RHS(eq, StringTools.getUnNested(eq, ','));
      PW.print("if (freq<=" + lhs + ") return ");
      lhs = StringTools.LHS(rhs, StringTools.getUnNested(rhs, '}'));
      PW.println(lhs + ";");
      PW.print("    else ");
      eq = StringTools.RHS(rhs, StringTools.getUnNested(rhs, '}'));
    }
    PW.println(" return 0;");
  }

  public static String FindLinkVariable(String tempexp, XMLTag function, XMLTag group) {
    String linkName = "";
    boolean foundVar = false;
    while ((!foundVar) && (tempexp.indexOf("\\var{")>-1)) {
      int vi = tempexp.indexOf("\\var{");
      String tryvar = StringTools.spit(tempexp.substring(vi), "\\var{");
      XMLTag t = function.getTagWhere("*", "name", tryvar);
      if (t == null) t = group.getTagWhere("*", "name", tryvar);
      if (t != null) {
        if (t.getAttribute("link") != null) {
          linkName = t.getAttribute("link");
          if (!linkName.equals("Ingestion")) foundVar = true;
          
        } else if (t.getName().equals("varietyconcentration")) {
          linkName = t.getValue("name");
          if (!t.getValue("name").equals("Ingestion")) foundVar = true;
        }
      }
      tempexp = tempexp.substring(vi + 1);
    }
    return linkName;
  }

  public static String CheckForVarietyFunctions(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    while ((eq.indexOf("someVariety") >= 0) || (eq.indexOf("allVariety") >= 0) || (eq.indexOf("noVariety") >= 0) || (eq.indexOf("varietymul") >= 0)
        || (eq.indexOf("varietysum") >= 0)) {
      String s = "someVariety";
      int i = eq.indexOf(s);
      if (i == -1) {
        s = "allVariety";
        i = eq.indexOf(s);
      }
      if (i == -1) {
        s = "noVariety";
        i = eq.indexOf(s);
      }
      if (i == -1) {
        s = "varietymul";
        i = eq.indexOf(s);
      }
      if (i == -1) {
        s = "varietysum";
        i = eq.indexOf(s);
      }

      String exp = StringTools.spit(eq.substring(i - 1), "\\" + s + "{");
      String linkName = FindLinkVariable(exp, function, group);

      if (!s.startsWith("variety")) {
        PW.println("int _" + linkName + " = 0;");
        PW.print("    boolean _varResult" + VarietyLabelCount + " = ");
        if (s.equals("allVariety")) {
          PW.println("true;");
          PW.print("    while ((_varResult" + VarietyLabelCount + ") && (_" + linkName + "<");
          if (linkName.equals("Ingestion")) PW.println("IngestedCells.length)) {");
          else PW.println("params." + linkName + "_types.length)) {");
        } else {
          PW.println("false;");
          PW.print("    while (!(_varResult" + VarietyLabelCount + ") && (_" + linkName + "<");
          if (linkName.equals("Ingestion")) PW.println("IngestedCells.length)) {");
          PW.println("params." + linkName + "_types.length)) {");

        }

        PW.print("      _varResult" + VarietyLabelCount + " = ");
        CompileExpression(model,exp, PW, equation, function, group, fgUpdate);
        PW.println(";");
        PW.println("      _" + linkName + "++;");

      } else {
        PW.print("double _varComp" + VarietyLabelCount + " = ");
        if (s.equals("varietymul")) PW.println("1.0;");
        if (s.equals("varietysum")) PW.println("0.0;");
        if (linkName.equals("Ingestion")) {
          PW.println("    for (int _ingIndex=0; _ingIndex< IngestedCells.length; _ingIndex++) {");
        } else {
          PW.print("    for (int _" + linkName + "=0; _" + linkName + "<");
          PW.println("params." + linkName + "_types.length; _" + linkName + "++) {");
          if (eq.indexOf("{IngestedCells}")>=0) PW.println("      int _ingIndex = SysVars.getIngIDFor(params."+linkName+"_types[_"+linkName+"],params."+linkName+"_status[_"+linkName+"]);");         
        }
        PW.print("      _varComp" + VarietyLabelCount + " ");
        if (s.equals("varietymul")) PW.print("*= ");
        if (s.equals("varietysum")) PW.print("+= ");
        CompileExpression(model, exp, PW, equation, function, group, fgUpdate);
        PW.println(";");
      }

      PW.println("    }");
      if (s.equals("noVariety")) PW.println("    _varResult" + VarietyLabelCount + " = !_varResult" + VarietyLabelCount + ";");
      PW.print("    ");
      if (!s.startsWith("variety")) eq = eq.substring(0, i - 1) + "\\" + "text{_varResult" + VarietyLabelCount + "}"
          + eq.substring(2 + s.length() + i + exp.length());
      else eq = eq.substring(0, i - 1) + "\\" + "text{_varComp" + VarietyLabelCount + "}" + eq.substring(2 + s.length() + i + exp.length());
      VarietyLabelCount++;
    }
    return eq;
  }

  public static void debugVariableDump(XMLTag model, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate, String vars) {
    ArrayList v = new ArrayList();
    int i = 0;
    PW.println("      System.out.println(\"NaN Error, "+group.getValue("name")+":"+function.getValue("name")+":"+equation.getValue("name")+", ID=\"+id+\", stage=\"+_stageNames[_CurrentStage]+\" Timestep=\"+Kernel.timeSteps);");
    while (vars.indexOf("\\var{", i) >= 0) {
      String tryVar = StringTools.spit(vars.substring(vars.indexOf("\\var", i)), "\\var{");
      boolean found=false;
      for (int j=0; j<v.size(); j++) {
        if (v.get(j).toString().equals(tryVar)) {
          found=true;
          j=v.size();
        }
      }
      if (!found) {
        v.add(tryVar);
        PW.print("      System.out.println(\"    " + tryVar + "=\"+");
        CompileGetVar(model,tryVar, PW, equation, function, group, fgUpdate);
        PW.println(");");
      }
      i = vars.indexOf("\\var{", i) + 2;
      
    }
    v.clear();
  }
  
  public static void CompileAssign(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    eq = CheckForVarietyFunctions(model, eq, PW, equation, function, group, fgUpdate);
    String[] leftAndRight = StringTools.nestedSplit(eq, ',');
    
    String theVar = StringTools.spit(leftAndRight[0], "\\var{");
    CompileSetVar(model, theVar, PW, equation, function, group);
    CompileExpression(model,leftAndRight[1], PW, equation, function, group, fgUpdate);
    PW.println(");");
    if (debugMode) {
      if (theVar.indexOf("$Pool") >= 0) {
        String chem = theVar.substring(0, theVar.indexOf("$Pool"));
        PW.println("    if (Double.isNaN(_"+chem+"_Pool_New)) {");
        debugVariableDump(model,PW,equation,function,group,fgUpdate,leftAndRight[1]);
        PW.println("    }");
      }
    }
  }

  public static boolean containsVarietyParam(String eq) {
    return ((eq.indexOf("\\varietyparameter{") >= 0) || (eq.indexOf("$Star") >= 0));
  }

  public static void CompileInteg(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, String op, String placeholder, boolean fgUpdate) {
    final String I = "_intz_"+String.valueOf(integrate_no);
    String p_length = "";
    if (placeholder.equals("")) PW.println("double "+I+" = 0.0;");
    else {
      p_length="params."+placeholder+"_types.length";
      PW.println("double[] "+I+" = new double["+p_length+"];");
    }
    
    PW.println("    { // Scope for integration");
    PW.println("      final BLayer _orig_blayer = blayer;");
    PW.println("      final double _z1 = (z[1]>z[2])?z[2]:z[1];");
    PW.println("      final double _z2 = (z[1]>z[2])?z[1]:z[2];");
    PW.println("      final double _diff = (_z2==_z1)?1:(_z2-_z1);");
    PW.println("      BLayer _b1 = Utils.findBLayer(_z1);");
    PW.println("      BLayer _b2 = Utils.findBLayer(_z2);");
    PW.println("      if (_b1.Depth<Kernel.W.MLDepth) _b1=Kernel.W.mixedLayer;");
    PW.println("      if (_b2.Depth<Kernel.W.MLDepth) _b2=Kernel.W.mixedLayer;");    
    PW.println("      if (_b1==_b2) {");
    
    if (placeholder.equals("")) {
      PW.print("        "+I+" = ");
      CompileExpression(model, eq, PW, equation, function, group, fgUpdate);
    } else {
      PW.println("        for (int _"+placeholder+"=0; _"+placeholder+"<params."+placeholder+"_types.length; _"+placeholder+"++)");
      PW.print("          "+I+"[_"+placeholder+"]=_diff*(");
      CompileExpression(model, eq, PW, equation, function, group, fgUpdate);
      PW.println(");");
    }
    PW.println("      } else {");
    PW.println("        final double _topProp = (_b1==Kernel.W.mixedLayer)?(1+(int)Kernel.W.MLDepth-_z1):((_b1.Depth+_b1.Height)-_z1);");
    PW.println("        final double _bottomProp = (_z2-_b2.Depth);");
    PW.println("        final double _midProp = _b2.Height;");
    PW.println("        for (int _layer=(int)_b1.Depth; _layer<=_b2.Depth; _layer++) {");
    PW.println("          blayer = Kernel.W.B_Layer[_layer];");
    PW.println("          double _prop = (blayer==_b1)?_topProp:(blayer==_b2)?_bottomProp:_midProp;");
    PW.println("          if (blayer.Depth<Kernel.W.MLDepth) {");
    PW.println("            blayer=Kernel.W.mixedLayer; // Overwrite the layer being treated");
    PW.println("            _layer=(int)Kernel.W.MLDepth; // Treat MLD as one layer");
    PW.println("            _prop=(Math.min(_z2,(1.0+(int)Kernel.W.MLDepth))-_z1); // Prop of MLD time");
    PW.println("          }"); 
    PW.println("          if (_prop>0) {");
    if (placeholder.equals("")) {
      PW.print("            "+I+" += _prop*(");
      CompileExpression(model, eq, PW, equation, function, group, fgUpdate);
      PW.println(");");

    } else {
      PW.println("            for (int _"+placeholder+"=0; _"+placeholder+"<params."+placeholder+"_types.length; _"+placeholder+"++) {");
      PW.print("              "+I+"[_"+placeholder+"] += _prop*(");
      CompileExpression(model, eq, PW, equation, function, group, fgUpdate);
      PW.println(");");
      PW.println("            }");
      PW.println("          }");
    }
    PW.println("          blayer = _orig_blayer;");
    PW.println("        }");
    PW.println("      }");
    PW.println("    }");
    PW.print("    ");
  }

  public static int AddReasonForChange(String reason, ArrayList deathReasons) {
    deathReasons.add(reason);
    return deathReasons.size() - 1;
  }
  
  public static void getDeathReasonsFor(String species, XMLTag fgTag, ArrayList deathReasons) {
    final XMLTag[] funcs = fgTag.getTags("function");
    for (int i=0; i<funcs.length; i++) {
      XMLTag[] eqs = funcs[i].getTags("equation");
      for (int j=0; j<eqs.length; j++) {
        String eq = eqs[j].getValue("eq");
        if ((eq.indexOf("\\change{")>=0) || (eq.indexOf("\\pchange{")>=0)) {
          AddReasonForChange(species+":"+eqs[j].getValue("name"),deathReasons);
        }
      }
    }
  }
  
  public static void writeDeathReason(XMLTag model, PrintWriter PW, String fgName, String s) {
    XMLTag[] validSpecies = model.getTag("species").getTagsWhere("species","@fg",fgName);
    for (int i=0; i<validSpecies.length; i++) {
      int speciesNo=0;
      
      final String species = validSpecies[i].getAttribute("name");
      while ((speciesNo<model.getTag("species").getTags("species").length) && 
          (!model.getTag("species").getTags("species")[speciesNo].getAttribute("name").equals(species)))
            speciesNo++;
   
      PW.print("(getParams()._type=="+speciesNo+")?");
      PW.print("SysVars._DR_"+StringTools.nonSpace(validSpecies[i].getAttribute("name")));
      PW.print("_"+StringTools.nonSpace(s)+":");
    }
    PW.print("-1");
  }
    
  
  public static void getDeathReasons(XMLTag theModel, ArrayList deathReasons) {
    final XMLTag[] species = theModel.getTag("species").getTags("species");
    for (int i=0; i<species.length; i++) {
      final String speciesName = StringTools.nonSpace(species[i].getAttribute("name"));
      final XMLTag fg = theModel.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")); 
      getDeathReasonsFor(speciesName,fg,deathReasons);
      AddReasonForChange(speciesName+":Zero Sub-Population",deathReasons);
      AddReasonForChange(speciesName+":"+OutputDialog2.AGENT_BEING_INGESTED_ALL,deathReasons);
      final XMLTag[] speciesTags = theModel.getTag("species").getTags("species");
      for (int j=0; j<speciesTags.length; j++) {
        final XMLTag predSpecies = speciesTags[j];
        final XMLTag predFG = theModel.getTagWhere("functionalgroup","name",predSpecies.getAttribute("fg"));
        final XMLTag[] stages = predFG.getTags("stage");
        for (int k=0; k<stages.length; k++) {
          final String predStage = stages[k].getValue("name");
          final String predSpec = predSpecies.getAttribute("name");
          AddReasonForChange(speciesName+":"+OutputDialog2.AGENT_BEING_INGESTED_BY+predSpec+":"+predStage,deathReasons);
        }
      }
            
    }
  }
    
  public static int getIDForSpecies(XMLTag model, String species) {
    int speciesNo=0;
    while ((speciesNo<model.getTag("species").getTags("species").length) && 
        (!model.getTag("species").getTags("species")[speciesNo].getAttribute("name").equals(species)))
          speciesNo++;
    return speciesNo; 
  }    
  
  public static int getIDForStage(XMLTag group, String stage) {
    XMLTag[] stages = group.getTags("stage");
    int i=0;
    while (i<stages.length) {
      if (stages[i].getValue("name").equals(stage)) return i;
      i++;
    }
    return -1;
  }
  
  public static int getIDForChemical(String chem, XMLTag model) {
    XMLTag[] chems = model.getTags("chemical");
    int i=0;
    while (i<chems.length) {
      if (chems[i].getValue("name").equals(chem)) return i;
      i++;
    }
    return -1;
  }

  public static void writeSingleLayerFoodIngestion(PrintWriter PW, String speciesName, XMLTag model, String foodset, String[] paramlist, String spaces, XMLTag eq, XMLTag function, XMLTag group) {
    XMLTag[] foods = model.getTag("foodsets").getTagWhere("foodset","@name",speciesName+" : "+foodset).getTags("food");
    for (int i=0; i<foods.length; i++) {
      PW.println(spaces+"_"+foodset+"="+i+";");
      final int preySpeciesNo = getIDForSpecies(model,foods[i].getAttribute("species"));
      final XMLTag preyFg = model.getTagWhere("functionalgroup","name",model.getTag("species").getTags("species")[preySpeciesNo].getAttribute("fg"));
      final int preyStageNo = getIDForStage(preyFg,foods[i].getAttribute("stage"));
      PW.print(spaces+"if (_b1.SpeciesConcentration_old["+preySpeciesNo+"]["+preyStageNo+"]/_b1.Height>");
      CompileGetVar(model,StringTools.spit(paramlist[1],"\\var{"),PW,eq,function,group,false);
      PW.println(") {");
      PW.print(spaces+"  final double _amount = (");
      CompileGetVar(model,StringTools.spit(paramlist[2],"\\var{"),PW,eq,function,group,false);
      PW.println(")*(3600.0*SysVars.stepInHours);");
      PW.print(spaces+"  _requested_");
      PW.print(StringTools.nonSpace(foods[i].getAttribute("species"))+"_");
      PW.println(StringTools.nonSpace(foods[i].getAttribute("stage"))+" +=_amount;");
      PW.println(spaces+"}");
    }
  }
  
  public static void writeMultiLayerFoodIngestion(PrintWriter PW, String speciesName, XMLTag model, String foodset, String[] paramlist, String spaces, XMLTag eq, XMLTag function, XMLTag group) {
    XMLTag[] foods = model.getTag("foodsets").getTagWhere("foodset","@name",speciesName+" : "+foodset).getTags("food");
    for (int i=0; i<foods.length; i++) {
      PW.println(spaces+"_"+foodset+"="+i+";");
      final int preySpeciesNo = getIDForSpecies(model,foods[i].getAttribute("species"));
      final XMLTag preyFg = model.getTagWhere("functionalgroup","name",model.getTag("species").getTags("species")[preySpeciesNo].getAttribute("fg"));
      final int preyStageNo = getIDForStage(preyFg,foods[i].getAttribute("stage"));
      PW.print(spaces+"if (_b.SpeciesConcentration_old["+preySpeciesNo+"]["+preyStageNo+"]/_b.Height>");
      CompileGetVar(model,StringTools.spit(paramlist[1],"\\var{"),PW,eq,function,group,false);
      PW.println(") {");
      PW.print(spaces+"  final double _amount = (_prop*");
      CompileGetVar(model,StringTools.spit(paramlist[2],"\\var{"),PW,eq,function,group,false);
      PW.println(")*(3600.0*SysVars.stepInHours);");
      PW.print(spaces+"  _requested_");
      PW.print(StringTools.nonSpace(foods[i].getAttribute("species"))+"_");
      PW.println(StringTools.nonSpace(foods[i].getAttribute("stage"))+" +=_amount;");
      PW.println(spaces+"}");
    }
  }

  public static void CompileStat(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, ArrayList deathReasons, boolean fgUpdate) {
   // Pre-compute any integration functions that are needed. 
    while (eq.indexOf("\\integrate{")>0) {
      final int pos = eq.indexOf("\\integrate{");
      final String eqPart = eq.substring(pos);
      final String intPart = StringTools.spit(eqPart,"\\integrate{");
      final String afterPart = eq.substring(pos+intPart.length()+12);
      final String placeholder = FindLinkVariable(intPart,function,group);
      String index="";
      if (!placeholder.equals("")) index="[_"+placeholder+"]";
      eq = eq.substring(0,pos)+"\\text{_intz_"+String.valueOf(integrate_no)+index+"}"+afterPart;
      CompileInteg(model,intPart, PW, equation, function, group, "",placeholder, fgUpdate);
      integrate_no++;
    }
    if (StringTools.chomp(eq, "\\while{")) CompileWhile(model,StringTools.spit(eq, "\\while{"), PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\ifthen{")) CompileIfThen(model,StringTools.spit(eq, "\\ifthen{"), PW, equation, function, group, deathReasons, fgUpdate);
    else if (StringTools.chomp(eq, "\\assign{")) CompileAssign(model,StringTools.spit(eq, "\\assign{"), PW, equation, function, group, fgUpdate);
    else if (StringTools.chomp(eq, "\\change{")) {
      String StageName = StringTools.spit(StringTools.spit(eq, "\\change{"), "\\stage{");
      boolean maintainBoolean = false;
      int speciesID = 0;
      XMLTag pm = model.getTag("particlemanagement");
      if (pm!=null) {
        XMLTag[] pms = pm.getTagsWhere("rule","@type","Maintain");
        for (int i=0; i<pms.length; i++) {
          String ruleName = pms[i].getAttribute("rule");
          String functionName = pms[i].getAttribute("function");
          boolean ok = true;
          if (!group.getValue("name").equals(pms[i].getAttribute("fg"))) ok = false;
          if (!functionName.equals(function.getValue("name"))) ok  = false;
          if (!ruleName.equals(equation.getValue("name"))) ok = false;
          if (ok) maintainBoolean = true;
          speciesID = getIDForSpecies(model,pms[i].getAttribute("species"));
        }
      }
      PW.print("changeState(_STAGE_" + StageName + ",");
      writeDeathReason(model,PW,group.getValue("name"),equation.getValue("name"));
      PW.println(", "+String.valueOf(maintainBoolean)+", "+String.valueOf(speciesID)+");");

    } else if (StringTools.chomp(eq, "\\pchange")) {
      eq = StringTools.spit(eq, "\\pchange{");
      String StageName = StringTools.spit(StringTools.LHS(eq, StringTools.getUnNested(eq, ',')), "\\stage{");
      PW.println("{");
      PW.print("      _pchanges[_STAGE_"+StageName+"]=");
      CompileExpression(model,StringTools.RHS(eq, StringTools.getUnNested(eq, ',')), PW, equation, function, group, fgUpdate);
      
      PW.println(";");
      PW.print("      _reasons[_STAGE_"+StageName+"]=");
      writeDeathReason(model,PW,group.getValue("name"),equation.getValue("name"));
      PW.println(";");
      PW.println("    }");

     
    } else if (StringTools.chomp(eq, "\\divide{")) {
      eq = StringTools.spit(eq, "\\divide{");
      PW.println("{");
      PW.print("      c[0]+=(c[1]*(");
      CompileExpression(model,eq, PW, equation, function, group, fgUpdate);
      PW.println("-1));");
      PW.println("      if ((log) && (SysVars.getStageLog(params._type,_CurrentStage)) && (Logging.getLifespan()))");
      PW.println("        Logging.writeDivideToLifespan(params._type, 0, Kernel.myTime, id, c[1], c[0]);");
      PW.println("    }");

    } else if (StringTools.chomp(eq, "\\create{")) {

      eq = StringTools.spit(eq, "\\create{");
      String StageName = StringTools.spit(StringTools.LHS(eq, StringTools.getUnNested(eq, ',')), "\\stage{");
      eq = StringTools.RHS(eq, StringTools.getUnNested(eq, ','));
      PW.println("{");
      final String fgName = StringTools.nonSpace(group.getValue("name"));
      PW.println("      " + fgName + " X" + countCreates + " = (" + fgName + ") getBlankParticle();");
      PW.println("      X" + countCreates + ".z[0]=z[0];");
      PW.println("      X" + countCreates + ".z[1]=z[1];");
      PW.println("      X" + countCreates + ".z[2]=z[2];");
      PW.println("      X" + countCreates + "._CurrentStage = _STAGE_" + StageName + ";");

      String[] paramlist = StringTools.nestedSplit(eq, ',');
      PW.print("      X" + countCreates + ".c[0]=(c[1]*");
      CompileExpression(model,paramlist[0], PW, equation, function, group, fgUpdate);
      PW.println(");");
      PW.println("      X"+countCreates+".c[1]=X"+countCreates+".c[0];");

      for (int i = 1; i < paramlist.length; i++) {
        eq = paramlist[i];
        eq = StringTools.spit(eq, "\\set{");
        String[] leftAndRight = StringTools.nestedSplit(eq, ',');
        String varName = StringTools.spit(leftAndRight[0],"\\var{");
        final String X = "X"+countCreates+".";
        if (varName.endsWith("$Pool")) {
          PW.print("      "+X+"_"+varName.substring(0,varName.length()-5)+"_Pool_Old = (");
          CompileExpression(model,leftAndRight[1], PW, equation, function, group, fgUpdate);  
          PW.println(");");
        } else if (varName.equals("z")) {
            PW.print("      "+X+"z[0]=(");
            CompileExpression(model,leftAndRight[1], PW, equation, function, group, fgUpdate);
            PW.println(");");
            PW.println("      "+X+"z[1]="+X+"z[0];");
            PW.println("      "+X+"z[2]="+X+"z[0];");
        } else { 
          final XMLTag v = group.getTagWhere("*", "name", varName);
          if (v != null) {
            final String tag = v.getName();
            if (tag.equals("variable")) {
              PW.print("      "+X+v.getValue("name") + "[0]=(");
              CompileExpression(model,leftAndRight[1], PW, equation, function, group, fgUpdate);
              PW.println(");");
              PW.println("      "+X+v.getValue("name") + "[1]="+X+v.getValue("name")+"[0];");
              
            } else if (tag.equals("varietyvariable")) {
              final String link = v.getAttribute("link");
              PW.println("for (int _"+link+"=0; _"+link+"<params."+link+"_types.length; _"+link+"++)");
              PW.print("      "+X+varName+"[_"+link+"][0]=(");
              CompileExpression(model,leftAndRight[1], PW, equation, function, group, fgUpdate);
              PW.println(");");
              PW.print("      "+X+varName+"[_"+link+"][1]="+X+varName+"[_"+link+"][0];");
            }
          } 
        }
      }

      PW.println("      Kernel.W.addQueuedAgent(X" + countCreates + ");");
      PW.println("      if ((log) && (SysVars.getStageLog(X"+countCreates+".getParams()._type,_CurrentStage))) {");
      PW.println("        if (Logging.getLineage()) Logging.writeCreateToLineage(X" + countCreates + ".params._type, Kernel.myTime, id, X" + countCreates + ".id,_CurrentStage,X" + countCreates + "._CurrentStage);");
      PW.println("        if (Logging.getLifespan()) Logging.writeCreateToLifespan(X" + countCreates + ".params._type, 0, Kernel.myTime, id, X" + countCreates + ".id, X" + countCreates + ".c[1], _STAGE_" + StageName + ");");
      PW.println("      }");
      PW.println("    }");
      countCreates++;
    } else if (StringTools.chomp(eq, "\\ingest{")) {
      eq = StringTools.spit(eq, "\\ingest{");
      final String[] paramlist = StringTools.nestedSplit(eq, ',');
      final String foodset = StringTools.spit(paramlist[0], "\\var{");
      XMLTag[] species = model.getTag("species").getTags("species");
      int fgCount=0;
      int firstSpecies=-1;
      XMLTag pred_species = null;
      for (int i=0; i<species.length; i++) { 
        if (species[i].getAttribute("fg").equals(group.getValue("name"))) {
          if (firstSpecies==-1) firstSpecies=i;
          fgCount++;
          pred_species = species[i];
        }
      }
      
      
      
      PW.println("{ // Scope for ingest call");
      PW.println("      final double _z1 = (z[1]>z[2])?z[2]:z[1];");
      PW.println("      final double _z2 = (z[1]>z[2])?z[1]:z[2];");
      PW.println("      final double _zdiff = _z2-_z1;");
      PW.println("      BLayer _b1 = Utils.findBLayer(_z1);");
      PW.println("      BLayer _b2 = Utils.findBLayer(_z2);");
      PW.println("      int _"+foodset+";");
      PW.println("      if (_b1.Depth<Kernel.W.MLDepth) _b1=Kernel.W.mixedLayer;");
      PW.println("      if (_b2.Depth<Kernel.W.MLDepth) _b2=Kernel.W.mixedLayer;");      
      PW.println("      if (_b1==_b2) {");
      
      if (fgCount==1) {
        final String speciesName = pred_species.getAttribute("name");
        writeSingleLayerFoodIngestion(PW,speciesName,model,foodset,paramlist,"        ", equation, function, group);
      } else {
        for (int speciesOfFG=0; speciesOfFG<fgCount; speciesOfFG++) {
          PW.print("        ");
          if (speciesOfFG>0) PW.print("} else ");
          PW.println("if (params._type=="+(firstSpecies+speciesOfFG)+") {");
          final String speciesName = model.getTag("species").getTags("species")[firstSpecies+speciesOfFG].getAttribute("name");
          writeSingleLayerFoodIngestion(PW,speciesName,model,foodset,paramlist,"          ", equation, function, group);
        }
        PW.println("        }");
      }
      PW.println("      } else {");
      PW.println("        final double _topProp = (_b1==Kernel.W.mixedLayer)?(1+(int)Kernel.W.MLDepth-_z1)/_zdiff:((_b1.Depth+_b1.Height)-_z1)/_zdiff;");
      PW.println("        final double _bottomProp = (_z2-_b2.Depth)/_zdiff;");
      PW.println("        final double _midProp = _b2.Height/_zdiff;");
      PW.println("        for (int _layer=(int)_b1.Depth; _layer<=_b2.Depth; _layer++) {");
      PW.println("          BLayer _b = Kernel.W.B_Layer[_layer];");
      PW.println("          double _prop = (_b==_b1)?_topProp:(_b==_b2)?_bottomProp:_midProp;");
      PW.println("          if (_b.Depth<Kernel.W.MLDepth) {");
      PW.println("            _b=Kernel.W.mixedLayer; // Overwrite the layer being treated");
      PW.println("            _layer=(int)Kernel.W.MLDepth; // Treat MLD as one layer");
      PW.println("            _prop=(Math.min(_z2,(1.0+(int)Kernel.W.MLDepth))-_z1)/_zdiff; // Prop of MLD time");
      PW.println("          }"); 
      PW.println("          if (_prop>0) {");
      if (fgCount==1) {
        final String speciesName = pred_species.getAttribute("name");
        writeMultiLayerFoodIngestion(PW,speciesName,model,foodset,paramlist,"            ", equation, function, group);
      } else {
        for (int speciesOfFG=0; speciesOfFG<fgCount; speciesOfFG++) {
          PW.print("            ");
          if (speciesOfFG>0) PW.print("} else ");
          PW.println("if (params._type=="+(firstSpecies+speciesOfFG)+") {");
          final String speciesName = model.getTag("species").getTags("species")[firstSpecies+speciesOfFG].getAttribute("name");
          writeMultiLayerFoodIngestion(PW,speciesName,model,foodset,paramlist,"              ", equation, function, group);
        }
        PW.println("            }");
      }
      PW.println("           }");            
      PW.println("        }");
      PW.println("      }");
      PW.println("    } // End ingest call");
      
    } else if (StringTools.chomp(eq, "\\uptake{")) {
      eq = StringTools.spit(eq, "\\uptake{");
      final String[] leftAndRight = StringTools.nestedSplit(eq, ',');
      leftAndRight[1] = StringTools.spit(leftAndRight[1], "\\var{");
      final String chem = leftAndRight[1].substring(0,leftAndRight[1].indexOf("$"));
      PW.print("_request_"+chem+" += ");
      CompileExpression(model,leftAndRight[0], PW, equation, function, group, fgUpdate);
      PW.println(";");
    } else if ((StringTools.chomp(eq, "\\remineralise{")) || (StringTools.chomp(eq,"\\release{"))) {
      if (StringTools.chomp(eq,"\\remineralise")) eq = StringTools.spit(eq, "\\remineralise{");
      else eq = StringTools.spit(eq,"\\release{");
      String[] paramlist = StringTools.nestedSplit(eq, ',');
      String chemName = StringTools.spit(paramlist[1], "\\var{");
      chemName = chemName.substring(0, chemName.indexOf("$"));
      if (debugMode) {
        PW.print("if (Double.isNaN(");
        CompileExpression(model,paramlist[0], PW, equation, function, group, fgUpdate);
        PW.println(")){");
        PW.println("        System.out.println(\"Warning: TS=\"+Kernel.timeSteps+\" Release  NaN of "+chemName+" by \"+_stageNames[_CurrentStage]+\" \"+name()+\", id=\"+id+\", in "+function.getValue("name")+" : "+equation.getValue("name")+"\");");
        debugVariableDump(model,PW,equation,function,group,fgUpdate,paramlist[0]);
       
        PW.println("    } else {");

        PW.print("      final double _amount = ");
        CompileExpression(model,paramlist[0], PW, equation, function, group, fgUpdate);
        PW.println(";");
        PW.println("      if (_amount<0) {");
        PW.println("        System.out.println(\"Warning: TS=\"+Kernel.timeSteps+\" Release  \"+_amount+\" of "+chemName+" by \"+_stageNames[_CurrentStage]+\" \"+name()+\", id=\"+id+\", in "+function.getValue("name")+" : "+equation.getValue("name")+"\");");
        debugVariableDump(model,PW,equation,function,group,fgUpdate,paramlist[0]);
        PW.println("      } else _release_"+chemName+"+=_amount;");
        PW.println("    }");
      } else {
        PW.print("    _release_"+chemName+"+=");
        CompileExpression(model,paramlist[0], PW, equation, function, group, fgUpdate);
        PW.println(";");
      }
    }
  }

  public static void CompileIfThen(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, ArrayList deathReasons, boolean fgUpdate) {
    String leftAndRight[] = StringTools.nestedSplit(eq, ',');
    PW.print("if (");
    CompileExpression(model,leftAndRight[0], PW, equation, function, group, fgUpdate);
    PW.print(") ");
    CompileStat(model,leftAndRight[1], PW, equation, function, group, deathReasons, fgUpdate);
  }

  public static void CompileWhile(XMLTag model, String eq, PrintWriter PW, XMLTag equation, XMLTag function, XMLTag group, boolean fgUpdate) {
    String leftAndRight[] = StringTools.nestedSplit(eq, ',');
    PW.print("    while (");
    CompileExpression(model,leftAndRight[0], PW, equation, function, group, fgUpdate);
    PW.println(") {");
    for (int i = 1; i < leftAndRight.length; i++) {
      CompileAssign(model,StringTools.spit(leftAndRight[i], "\\assign{"), PW, equation, function, group, fgUpdate);
    }
    PW.println("    }");
  }

   private static void CompileFunction(XMLTag model, XMLTag f, PrintWriter PW, XMLTag group, ArrayList deathReasons, boolean fgUpdate) {
    status("       Function: " + f.getValue("name"));
    PW.println("  public void " + StringTools.nonSpace(f.getValue("name")) + "() {");

    XMLTag[] eqs = f.getTags("equation");
    for (int i = 0; i < eqs.length; i++) {
      PW.print("    ");
      CompileStat(model,eqs[i].getValue("eq"), PW, eqs[i], f, group, deathReasons, fgUpdate);
    }
    PW.println("  }");
    PW.println("");
  }

  /** *************************************************************** */
  /* Main class compiling functions */
  /** *************************************************************** */

  private static void CompileStateVariables(PrintWriter PW, XMLTag fg) {
    PW.println("  // State Variables");
    PW.println("");
    XMLTag[] vs = fg.getTags("variable");
    for (int i = 0; i < vs.length; i++) {
      XMLTag v = vs[i];
      int hist = Integer.parseInt(v.getValue("hist"));
      PW.println("  double[] " + v.getValue("name") + " = new double["+(hist+1)+"];"); 
    }
    vs = fg.getTags("varietyvariable");
    for (int i=0; i<vs.length; i++) {
      if (!vs[i].getValue("name").equals("IngestedCells")) {
        XMLTag v = vs[i];
        PW.println("  public double[][] " + v.getValue("name") + "; // " + v.getValue("desc")+" [food-type][history]");
      }
    }

    PW.println("");
  }

  private static void CompileStage(XMLTag fg, String FunctionalGroupName, String StageName, PrintWriter SPW, XMLTag model) {
    XMLTag[] funcs = fg.getTags("function");
    boolean found=false;
    for (int i=0; i<funcs.length; i++) {
      XMLTag[] calls = funcs[i].getTags("calledin");
      for (int j=0; j<calls.length; j++) {
        if (calls[j].getValue().equals(StageName)) {
          found=true;
          j=calls.length;
          i=funcs.length;
        }
      }
    }
    
    if (found) {
      SPW.println("  public void update_"+StringTools.nonSpace(StageName)+"() {");
      if ((fg.getTag("predator")==null) || (fg.getValue("predator").equals("false"))) {
        SPW.println("    if (c[1]<=1E-10) {");
        SPW.println("      if (c[1]<0) System.out.println(\"WEIRD CELL COUNT id = \"+id+\", c = \"+c[1]+\" Time=\"+Kernel.myTime+\", type=\"+params._type+\" Stage=\"+_CurrentStage);");
        SPW.println("      if ((log) && (SysVars.getStageLog(params._type,_CurrentStage))) {");
        SPW.println("        if (Logging.getLineage()) Logging.writeRemovalToLineage(params._type,Kernel.myTime,id,_CurrentStage);");
        SPW.println("        if (Logging.getLifespan()) Logging.writeRemovalToLifespan(params._type,-1,Kernel.myTime,id,c[1]);");
        SPW.println("      }");
        SPW.println("      c[0]=0;");
        SPW.println("      Kernel.W.queueRemoveAgent(this);");
        SPW.println("      Kernel.W.AgentCounts[params._type][_CurrentStage]++;");
        SPW.println("      blayer.AgentCounts[params._type][_CurrentStage]++;");
        SPW.print("    } else ");
      } else SPW.print("    ");     // Top Predator Miss out cell count checking");
      SPW.println("if (z[0]>=500) {");
      SPW.println("      SysVars.conserveDropout(this);");
      SPW.println("      Kernel.W.queueRemoveAgent(this);");
      SPW.println("      Kernel.W.AgentCounts[params._type][_CurrentStage]++;");
      SPW.println("      blayer.AgentCounts[params._type][_CurrentStage]++;");
      SPW.println("    } else {");
      SPW.println("      genericStartUpdate();");
      for (int i=0; i<funcs.length; i++) {
        XMLTag[] calls = funcs[i].getTags("calledin");
        found=false;
        for (int j=0; j<calls.length; j++) {
          if (calls[j].getValue().equals(StageName)) {
            SPW.println("      "+StringTools.nonSpace(funcs[i].getValue("name"))+"();");
            found=true;
            j=calls.length;
          }
        }
      }
      SPW.println("      genericEndUpdate();");
      SPW.println("    }");
      SPW.println("  }");
    } else SPW.println("  public void update_"+StringTools.nonSpace(StageName)+"() {}");
    SPW.println("");
  }

  private static void CompileLocalDefs(XMLTag model, PrintWriter FGPW, XMLTag fg) {
    int speciesInFG = model.getTag("species").getTagsWhere("species","@fg",fg.getValue("name")).length;
    
    XMLTag[] v = fg.getTags("local");
    for (int j = 0; j < v.length; j++) {
      FGPW.print("  public static double " + v[j].getValue("name"));
      if (v[j].getTag("val") != null) FGPW.print(" = " + v[j].getValue("val"));
    else FGPW.print(" = 0.0");
      FGPW.println("; // " + v[j].getValue("desc"));
    }
    v = fg.getTags("varietylocal");
    for (int j = 0; j < v.length; j++) {
      FGPW.println("  public static double[][] " + v[j].getValue("name") + "; // [species-in-this-fg][food-type]");
    }
    
    FGPW.println("  public static void initVarietyLocals() {");
    v = fg.getTags("varietylocal");
    for (int j = 0; j < v.length; j++)
      FGPW.println("    " + v[j].getValue("name") + " = new double["+speciesInFG+"][];");
    
    XMLTag[] speciesTags = model.getTag("species").getTags("species");
    int countSpeciesInFG = 0;
    for (int i=0; i<speciesTags.length; i++) {
      if (speciesTags[i].getAttribute("fg").equals(fg.getValue("name"))) {
        String speciesName = speciesTags[i].getAttribute("name");
        XMLTag[] foodsets = model.getTag("foodsets").getTags("foodset");
        for (int j=0; j<foodsets.length; j++) {
          String setName = foodsets[j].getAttribute("name");
          String foodSpecies = setName.substring(0,setName.indexOf(":")-1);
          if (foodSpecies.equals(speciesName)) {
            int foodCount = foodsets[j].getTags("food").length;
            v = fg.getTags("varietylocal");
            for (int l = 0; l < v.length; l++)
              FGPW.println("    " + v[l].getValue("name") + "["+countSpeciesInFG+"] = new double["+foodCount+"];");
            
          }
        }
        countSpeciesInFG++;
      }
    }
    FGPW.println("  }");

  }
  
  public static boolean chemInUse(XMLTag model, XMLTag fg, String chem, String type) {
    boolean found=false;
    final XMLTag[] fgs = model.getTags("functionalgroup");
    for (int j=0; j<fgs.length; j++) {
      if ((fg==null) || (fgs[j]==fg)) {
        final XMLTag[] funcs = fgs[j].getTags("function");
        for (int k=0; k<funcs.length; k++) {
          final XMLTag[] eqs = funcs[k].getTags("equation");
          for (int m=0; m<eqs.length; m++) {
            String eq = eqs[m].getValue("eq");
            while (eq.indexOf("\\"+type+"{")>=0) {
              eq = eq.substring(eq.indexOf("\\"+type));
              final String uptake = StringTools.spit(eq,"\\"+type+"{");
              final String[] leftAndRight = StringTools.nestedSplit(uptake, ',');
              String chemName = StringTools.spit(leftAndRight[1],"\\var{");
              chemName=chemName.substring(0,chemName.length()-5);
              chemName=StringTools.nonSpace(chemName);
              if (chemName.equals(chem)) {
                found=true;
                m=eqs.length;
                k=funcs.length;
                j=fgs.length;
                eq="";
              } else eq = eq.substring(eq.indexOf("\\"+type+"{")+5);
            }
          }
        }
      }
    }
    return found;
  }
  
  
  public static boolean[] getChemPoolUsage(XMLTag fg, XMLTag model) {
    XMLTag[] chems = model.getTags("chemical");
    boolean[] chemUse = new boolean[chems.length];
    for (int i=0; i<chemUse.length; i++) {
      if (chems[i].getValue("pigment").equals("true")) chemUse[i]=true;
      else chemUse[i]=false;
    }
    XMLTag[] funcs = fg.getTags("function");
    for (int i=0; i<funcs.length; i++) {
      XMLTag[] eqs = funcs[i].getTags("equation");
      for (int j=0; j<eqs.length; j++) {
        String eq = eqs[j].getValue("eq");
        for (int k=0; k<chems.length; k++) {
          if (eq.indexOf(chems[k].getValue("name")+"$Pool")>=0) chemUse[k]=true;
        }
      }
    }
    return chemUse;
  }

  public static boolean[] getChemIngUsage(XMLTag fg, XMLTag model) {
    XMLTag[] chems = model.getTags("chemical");
    boolean[] chemUse = new boolean[chems.length];
    XMLTag[] funcs = fg.getTags("function");
    for (int i=0; i<funcs.length; i++) {
      XMLTag[] eqs = funcs[i].getTags("equation");
      for (int j=0; j<eqs.length; j++) {
        String eq = eqs[j].getValue("eq");
        for (int k=0; k<chems.length; k++) {
          if (eq.indexOf(chems[k].getValue("name")+"$Ingested")>=0) chemUse[k]=true;
        }
      }
    }
    return chemUse;
  }

  public static boolean[][] getPreyList(XMLTag fg, XMLTag model) {
    boolean[][] ingNeeded = new boolean[model.getTag("species").getTags("species").length][];
    for (int i=0; i<ingNeeded.length; i++) {
      final String fgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
      ingNeeded[i] = new boolean[model.getTagWhere("functionalgroup","name",fgName).getTags("stage").length];
      for (int j=0; j<ingNeeded[i].length; j++) ingNeeded[i][j]=false;
    }
    XMLTag[] functions = fg.getTags("function");
    for (int i=0; i<functions.length; i++) {
      XMLTag[] eqs = functions[i].getTags("equation");
      for (int j=0; j<eqs.length; j++) {
        String eq = eqs[j].getValue("eq");
        while (eq.indexOf("\\ingest{")>=0) {
          eq = eq.substring(eq.indexOf("\\ingest{")+13);
          String foodSet = eq.substring(0,eq.indexOf("}"));
          XMLTag[] speciesTags = model.getTag("species").getTagsWhere("species","@fg",fg.getValue("name"));
          for (int k=0; k<speciesTags.length; k++) {
            String setName = speciesTags[k].getAttribute("name")+" : "+foodSet;
            XMLTag[] theFoods = model.getTag("foodsets").getTagWhere("foodset","@name",setName).getTags("food");
            for (int m=0; m<theFoods.length; m++) {
              int speciesID = getIDForSpecies(model,theFoods[m].getAttribute("species"));
              String fgName = model.getTag("species").getTags("species")[speciesID].getAttribute("fg"); 
              XMLTag foodFG = model.getTagWhere("functionalgroup","name",fgName);
              int stageID = getIDForStage(foodFG,theFoods[m].getAttribute("stage"));
              ingNeeded[speciesID][stageID]=true;
            }
          }
        }
      }
    }
    return ingNeeded;
  }
  
  /**
   * @param model
   * @param fg
   * @param deathReasons
   */
  private static void CompileFunctionalGroup(XMLTag model, XMLTag fg, ArrayList deathReasons) {
    final String fgName = StringTools.nonSpace(fg.getValue("name"));
    final XMLTag[] chems = model.getTags("chemical");
    final boolean[] chemUse = getChemPoolUsage(fg,model);
    final boolean[] ingUse = getChemIngUsage(fg,model);
    status("  Functional Group: " + fgName);
    PrintWriter FGPW = StringTools.OpenOutputFile(theOutputDir + "/VEW/Sim/" + fgName + ".java");
    FGPW.println("package VEW.Sim;");
    FGPW.println("import java.io.*;");
    FGPW.println("import VEW.Common.XML.*;");
    FGPW.println("public class " + fgName + " extends FunctionalGroup {");
    FGPW.println("  // Stage handling.");
    XMLTag[] Stages = fg.getTags("stage");
    FGPW.println("  public FGParams getParams() { return params; }");
    
    for (int i = 0; i < Stages.length; i++) {
      FGPW.println("  protected final static int _STAGE_" + Stages[i].getValue("name") + " = " + i + ";");
    }
    // Initialise the parameters
    
    compileSpeciesParamDefinitions(model,FGPW, fg);

    
    // Add the generic stuff.
    FGPW.println("");/* Write variables for chemical pools in use */
    FGPW.println("  // Generic Handling Functions");
    FGPW.println("");
    FGPW.println("  public String name() { return params._name; }");
    FGPW.println("  public String fgName() { return _fgName; }");
    FGPW.println("  public int getStageCount() { return "+Stages.length+"; }");
    FGPW.println("  public int getAllStageIndex() { return "+Stages.length+"; }");
    FGPW.print("  public static final String[] _stageNames = {");
    for (int i = 0; i < fg.getTags("stage").length; i++) {
      FGPW.print("\"" + fg.getTags("stage")[i].getValue("name") + "\"");
      if (i < fg.getTags("stage").length - 1) FGPW.print(",");
    }
    FGPW.println("};");
    FGPW.println("  public boolean getStageLogStatus() { return SysVars.getStageLog(params._type,_CurrentStage); }");
    FGPW.print("  public boolean logDescendents() {");
    XMLTag auditTag = model.getTag("model/output/audittrails");
    if (auditTag==null) FGPW.println(" return false; }");
    else {
      FGPW.println("");
      XMLTag[] auditTags = auditTag.getTags("functionalgroup");
      for (int i=0; i<auditTags.length; i++) {
        String speciesName = auditTags[i].getValue("name");
        int typeCheck = getIDForSpecies(model,speciesName);
        if (i>0) FGPW.print("    else if ");
        else FGPW.print("    if ");
        FGPW.println("(type=="+typeCheck+") return "+auditTags[i].getValue("usedescendents")+";");
      }
      FGPW.println("    else return false;");
      FGPW.println("  }");
    }
    
    FGPW.println("  public static final String _fgName = new String(\"" + fgName + "\");");
    XMLTag[] funcs = fg.getTags("function");
    pchangeNeeded=false;
    for (int i=0; i<funcs.length; i++) {
      XMLTag[] equations = funcs[i].getTags("equation");
      for (int j=0; j<equations.length; j++) {
        if (equations[j].getValue("eq").indexOf("\\pchange{")>0) {
          j=equations.length;
          i=funcs.length;
          pchangeNeeded=true;
          
        }
      }
    }
    if (pchangeNeeded) {
      FGPW.println("  protected static double[] _pchanges = new double["+Stages.length+"]; // For each stage, proportion of agent to pchange into new stage");
      FGPW.println("  protected static int[] _reasons = new int["+Stages.length+"]; // The demographic reason for stage change");
    }
    FGPW.println("  static {");
    XMLTag[] specs = model.getTag("species").getTags("species");
    for (int i=0; i<specs.length; i++)
      if (StringTools.nonSpace(specs[i].getAttribute("fg")).equals(fgName)) FGPW.println("    _"+StringTools.nonSpace(specs[i].getAttribute("name"))+"Params.set"+StringTools.nonSpace(specs[i].getAttribute("name"))+"Params();");
    FGPW.println("    initVarietyLocals();");  
    FGPW.println("  }");

    FGPW.println("  public _" + fgName + "Params params;");
    FGPW.println("");
    VarietyLabelCount = 0;
    
    /* Write variables for particle ingestion */
    
    boolean[][] ingNeeded = getPreyList(fg,model);
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          FGPW.print("  public static double _ingested_");
          FGPW.print(StringTools.nonSpace(specs[i].getAttribute("name"))+"_");
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          FGPW.println(StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"))+" = 0.0;");
          FGPW.print("  public double _requested_");
          FGPW.print(StringTools.nonSpace(specs[i].getAttribute("name"))+"_");
          FGPW.println(StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"))+" = 0.0;");
        }
      }
    }
    
    /* Write variables for chemical pools in use */
    boolean[] chemPoolsInUse = getChemPoolUsage(fg,model);
    for (int i=0; i<chemPoolsInUse.length; i++) {
      if (chemPoolsInUse[i]) {
        FGPW.println("  public double _"+model.getTags("chemical")[i].getValue("name")+"_Pool_Old = 0.0;");
        FGPW.println("  public static double _"+model.getTags("chemical")[i].getValue("name")+"_Pool_New = 0.0;");
      }
    }
    
    /* Write variables for chemical ingestion pools that are in use */
    
    for (int i=0; i<ingUse.length; i++) {
      if (ingUse[i]) {
        FGPW.println("  public static double _"+model.getTags("chemical")[i].getValue("name")+"_Ing = 0.0;");
      }
    }
    
    /* Write variables for chemical uptake */
    
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("  public double _request_"+chem+"=0.0;");
      if (chemInUse(model,fg,chem,"release"))
        FGPW.println("  public static double _release_"+chem+"=0.0;");
    }
    
    
    CompileStateVariables(FGPW, fg);
    CompileLocalDefs(model,FGPW, fg);
    FGPW.println("");
    if ((fg.getTag("predator") != null) && (fg.getValue("predator").equals("true"))) compileFGClosureFunctions(model,FGPW,fg); 
    for (int i = 0; i < funcs.length; i++) CompileFunction(model,funcs[i], FGPW, fg, deathReasons, true);
    FGPW.println("  // Generic Functions");
    FGPW.println("");
    FGPW.println("  public void declarePresence() {");
    for (int j=0; j<chemPoolsInUse.length; j++) {
      if (chemPoolsInUse[j]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[j].getValue("name"));
        FGPW.println("    Kernel.W.particulate_chem["+j+"]+=(_"+chemName+"_Pool_Old*c[1]);");
        FGPW.println("    Kernel.W.particulate_chem_ss["+j+"][params._type][_CurrentStage]+=(_"+chemName+"_Pool_Old*c[1]);");
      }
    }    
    FGPW.println("    Kernel.W.SpeciesTotal[params._type][_CurrentStage]+=c[1];");
    FGPW.println("  // Do any uptake / release / ingest activity");
    FGPW.println("    final double _z1 = (z[1]>z[2])?z[2]:z[1];");
    FGPW.println("    final double _z2 = (z[1]>z[2])?z[1]:z[2];");
    FGPW.println("    final double _zdiff = _z2-_z1;");
    FGPW.println("    BLayer _b1 = Utils.findBLayer(_z1);");
    FGPW.println("    BLayer _b2 = Utils.findBLayer(_z2);");
    FGPW.println("    if (_b1.Depth<Kernel.W.MLDepth) _b1=Kernel.W.mixedLayer;");
    FGPW.println("    if (_b2.Depth<Kernel.W.MLDepth) _b2=Kernel.W.mixedLayer;");
    FGPW.println("    if (_b1==_b2) {");
    
    
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("      if ("+var+">0) _b1.ingest_request["+i+"]["+j+"]+="+var+";");
          
        }
      }
    }

    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("      if (_request_"+chem+">0) _b1.request_"+chem+"+=_request_"+chem+";");
    }
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"release")) {
        FGPW.println("      if (_release_"+chem+">0) {");
        FGPW.println("        _b1.released["+chem+".ID_"+chem+"]+=_release_"+chem+";");
        FGPW.println("        _b1.released_track["+chem+".ID_"+chem+"]+=_release_"+chem+";");
        FGPW.println("      }");
      }
      if (chemPoolsInUse[i]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[i].getValue("name"));
        FGPW.println("      _b1.particulate_chem["+i+"]+=_"+chemName+"_Pool_Old*c[1];");
        FGPW.println("      _b1.particulate_chem_ss["+i+"][params._type][_CurrentStage]+=_"+chemName+"_Pool_Old*c[1];");
      }
    }
    FGPW.println("      _b1.SpeciesConcentration[params._type][_CurrentStage]+=c[1];");
    FGPW.println("    } else {");
    FGPW.println("      final double _topProp = (_b1==Kernel.W.mixedLayer)?(1+(int)Kernel.W.MLDepth-_z1)/_zdiff:((_b1.Depth+_b1.Height)-_z1)/_zdiff;");
    FGPW.println("      final double _bottomProp = (_z2-_b2.Depth)/_zdiff;");
    FGPW.println("      final double _midProp = _b2.Height/_zdiff;");
    FGPW.println("      for (int _layer=(int)_b1.Depth; _layer<=_b2.Depth; _layer++) {");
    FGPW.println("        BLayer _b = Kernel.W.B_Layer[_layer];");
    FGPW.println("        double _prop = (_b==_b1)?_topProp:(_b==_b2)?_bottomProp:_midProp;");
    FGPW.println("        if (_b.Depth<Kernel.W.MLDepth) {");
    FGPW.println("          _b=Kernel.W.mixedLayer; // Overwrite the layer being treated");
    FGPW.println("          _layer=(int)Kernel.W.MLDepth; // Treat MLD as one layer");
    FGPW.println("          _prop=(Math.min(_z2,(1.0+(int)Kernel.W.MLDepth))-_z1)/_zdiff; // Prop of MLD time");
    FGPW.println("        }"); 
    FGPW.println("        if (_prop>0) {");
    FGPW.println("          final double _propC=_prop*c[1];");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("          if (_request_"+chem+">0) _b.request_"+chem+"+=_request_"+chem+"*_prop;");
    }

    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("          if ("+var+">0) _b.ingest_request["+i+"]["+j+"]+="+var+"*_prop;");
          
        }
      }
    }

    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"release")) {
        FGPW.println("          if (_release_"+chem+">0) {");
        FGPW.println("            _b.released["+chem+".ID_"+chem+"]+=_release_"+chem+"*_prop;");
        FGPW.println("            _b.released_track["+chem+".ID_"+chem+"]+=_release_"+chem+"*_prop;");
        FGPW.println("          }");
      }
      
      if (chemPoolsInUse[i]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[i].getValue("name"));
        FGPW.println("          _b.particulate_chem["+chemName+".ID_"+chemName+"]+=_"+chemName+"_Pool_Old*_propC;");
        FGPW.println("          _b.particulate_chem_ss["+chemName+".ID_"+chemName+"][params._type][_CurrentStage]+=_"+chemName+"_Pool_Old*_propC;");
      }
    }
    FGPW.println("          _b.SpeciesConcentration[params._type][_CurrentStage]+=_propC;");
    FGPW.println("        }");
    FGPW.println("      }");
    FGPW.println("    }");
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public void genericStartUpdate() {");
    FGPW.println("    _NextStage=_CurrentStage;");
    FGPW.println("    final double _z1 = (z[1]>z[2])?z[2]:z[1];");
    FGPW.println("    final double _z2 = (z[1]>z[2])?z[1]:z[2];");
    FGPW.println("    final double _zdiff = _z2-_z1;");
    FGPW.println("    BLayer _b1 = Utils.findBLayer(_z1);");
    FGPW.println("    BLayer _b2 = Utils.findBLayer(_z2);");
    for (int i=0; i<ingUse.length; i++) {
      if (ingUse[i]) {
        FGPW.println("    _"+model.getTags("chemical")[i].getValue("name")+"_Ing = 0.0;");
      }
    }
    FGPW.println("");
    FGPW.println("    // Update sub-population-size if it was ingested by predators");
    FGPW.println("");
    FGPW.println("    if (_b1.Depth<Kernel.W.MLDepth_old) _b1=Kernel.W.mixedLayer;");
    FGPW.println("    if (_b2.Depth<Kernel.W.MLDepth_old) _b2=Kernel.W.mixedLayer;");    
    FGPW.println("    if (_b1==_b2) {");
    FGPW.println("      if (_b1.ingested_prop[params._type][_CurrentStage]>0)");
    FGPW.println("        c[1]-=(c[1]*_b1.ingested_prop[params._type][_CurrentStage]);");
    FGPW.println("      _b1 = Utils.findBLayer(_z1);");
    FGPW.println("      _b2 = Utils.findBLayer(_z2);");    
    FGPW.println("    } else {");
    FGPW.println("      final double _origC = c[1];");
    FGPW.println("      final double _topProp = (_b1==Kernel.W.mixedLayer)?(1+(int)Kernel.W.MLDepth_old-_z1)/_zdiff:((_b1.Depth+_b1.Height)-_z1)/_zdiff;");
    FGPW.println("      final double _bottomProp = (_z2-_b2.Depth)/_zdiff;");
    FGPW.println("      final double _midProp = _b2.Height/_zdiff;");
    FGPW.println("      for (int _layer=(int)_b1.Depth; _layer<=_b2.Depth; _layer++) {");
    FGPW.println("        BLayer _b = Kernel.W.B_Layer[_layer];");
    FGPW.println("        double _prop = (_b==_b1)?_topProp:(_b==_b2)?_bottomProp:_midProp;");
    FGPW.println("        if (_b.Depth<Kernel.W.MLDepth_old) {");
    FGPW.println("          _b=Kernel.W.mixedLayer; // Overwrite the layer being treated");
    FGPW.println("          _layer=(int)Kernel.W.MLDepth_old; // Treat MLD as one layer");
    FGPW.println("          _prop=(Math.min(_z2,(1.0+(int)Kernel.W.MLDepth_old))-_z1)/_zdiff; // Prop of MLD time");
    FGPW.println("        }"); 
    FGPW.println("        if ((_prop>0) && (_b.ingested_prop[params._type][_CurrentStage]>0))");
    FGPW.println("          c[1]-=(_origC*_prop*_b.ingested_prop[params._type][_CurrentStage]);");
    FGPW.println("      }");
    FGPW.println("    }");
    FGPW.println("");
    FGPW.println("    // Now update ingestion, having sorted out cell counts");
    FGPW.println("    if (_b1.Depth<Kernel.W.MLDepth_old) _b1=Kernel.W.mixedLayer;");
    FGPW.println("    if (_b2.Depth<Kernel.W.MLDepth_old) _b2=Kernel.W.mixedLayer;");    
    FGPW.println("    if (_b1==_b2) {");
    
    for (int j=0; j<chems.length; j++) {
      final String chemName = StringTools.nonSpace(chems[j].getValue("name"));
      if (chemInUse(model,fg,chemName,"uptake"))
        FGPW.println("      if ((_request_"+chemName+">0) && (_b1.deplete_"+chemName+"<1)) _request_"+chemName+"*=_b1.deplete_"+chemName+";");
    }
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          String foodSpecies = model.getTag("species").getTags("species")[i].getAttribute("name");
          String foodStage = foodFg.getTags("stage")[j].getValue("name");
          final int foodSpeciesID = getIDForSpecies(model,foodSpecies);
          final int foodStageID = getIDForStage(foodFg,foodStage);
          foodSpecies = StringTools.nonSpace(foodSpecies);
          foodStage = StringTools.nonSpace(foodStage);
          FGPW.println("      if (_b1.ingest_deplete["+i+"]["+j+"]<1) _requested_"+foodSpecies+"_"+foodStage+" *= _b1.ingest_deplete["+i+"]["+j+"];");
          FGPW.println("      if (_requested_"+foodSpecies+"_"+foodStage+">0) {");
          FGPW.println("        _ingested_"+foodSpecies+"_"+foodStage+"+=_requested_"+foodSpecies+"_"+foodStage+";");
          FGPW.println("        if (_b1.SpeciesConcentration_old["+foodSpeciesID+"]["+foodStageID+"]>0) {");
          for (int k=0; k<ingUse.length; k++) {
            if (ingUse[k]) {
              FGPW.print("            _"+model.getTags("chemical")[k].getValue("name")+"_Ing+=");
              FGPW.print("((_requested_"+foodSpecies+"_"+foodStage);
              FGPW.print("/_b1.SpeciesConcentration_old["+foodSpeciesID+"]["+foodStageID+"])");
              FGPW.println("*_b1.particulate_chem_ss_old["+k+"]["+foodSpeciesID+"]["+foodStageID+"])/c[1];");
            }
          }
          FGPW.println("        }");
          FGPW.println("      }");
        }
      }
    }
    FGPW.println("      _b1 = Utils.findBLayer(_z1);");
    FGPW.println("    } else {");
    FGPW.println("      final double _topProp = (_b1==Kernel.W.mixedLayer)?(1+(int)Kernel.W.MLDepth_old-_z1)/_zdiff:((_b1.Depth+_b1.Height)-_z1)/_zdiff;");
    FGPW.println("      final double _bottomProp = (_z2-_b2.Depth)/_zdiff;");
    FGPW.println("      final double _midProp = _b2.Height/_zdiff;");
    
    for (int j=0; j<chems.length; j++) {
      final String chemName = StringTools.nonSpace(chems[j].getValue("name"));
      if (chemInUse(model,fg,chemName,"uptake"))
        FGPW.println("      final double _orig_up_"+chemName+" = _request_"+chemName+";");
    }          
    FGPW.println("      for (int _layer=(int)_b1.Depth; _layer<=_b2.Depth; _layer++) {");
    FGPW.println("        BLayer _b = Kernel.W.B_Layer[_layer];");
    FGPW.println("        double _prop = (_b==_b1)?_topProp:(_b==_b2)?_bottomProp:_midProp;");
    FGPW.println("        if (_b.Depth<Kernel.W.MLDepth_old) {");
    FGPW.println("          _b=Kernel.W.mixedLayer; // Overwrite the layer being treated");
    FGPW.println("          _layer=(int)Kernel.W.MLDepth_old; // Treat MLD as one layer");
    FGPW.println("          _prop=(Math.min(_z2,(1.0+(int)Kernel.W.MLDepth_old))-_z1)/_zdiff; // Prop of MLD time");
    FGPW.println("        }"); 
    FGPW.println("        if (_prop>0) {");
    for (int j=0; j<chems.length; j++) {
      final String chemName = StringTools.nonSpace(chems[j].getValue("name"));
      if (chemInUse(model,fg,chemName,"uptake"))
        FGPW.println("          if ((_request_"+chemName+">0) && (_b.deplete_"+chemName+"<1)) _request_"+chemName+"-=(_orig_up_"+chemName+"*_prop*(1-_b.deplete_"+chemName+"));");
    }
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          String foodSpecies = model.getTag("species").getTags("species")[i].getAttribute("name");
          String foodStage = foodFg.getTags("stage")[j].getValue("name");
          final int foodSpeciesID = getIDForSpecies(model,foodSpecies);
          final int foodStageID = getIDForStage(foodFg,foodStage);
          foodSpecies = StringTools.nonSpace(foodSpecies);
          foodStage = StringTools.nonSpace(foodStage);
          FGPW.println("          double _layer_req_"+foodSpecies+"_"+foodStage+" = _prop*_requested_"+foodSpecies+"_"+foodStage+";");
          FGPW.println("          if (_b.ingest_deplete["+i+"]["+j+"]<1) _layer_req_"+foodSpecies+"_"+foodStage+"*= _b.ingest_deplete["+i+"]["+j+"];");
          FGPW.println("          if (_layer_req_"+foodSpecies+"_"+foodStage+">0) {");
          FGPW.println("            _ingested_"+foodSpecies+"_"+foodStage+"+=_layer_req_"+foodSpecies+"_"+foodStage+";");
          FGPW.println("            if (_b.SpeciesConcentration_old["+foodSpeciesID+"]["+foodStageID+"]>0) {");
          for (int k=0; k<ingUse.length; k++) {
            if (ingUse[k]) {
              FGPW.print("              _"+model.getTags("chemical")[k].getValue("name")+"_Ing+=");
              FGPW.print("((_layer_req_"+foodSpecies+"_"+foodStage);
              FGPW.print("/_b.SpeciesConcentration_old["+foodSpeciesID+"]["+foodStageID+"])");
              FGPW.println("*_b.particulate_chem_ss_old["+k+"]["+foodSpeciesID+"]["+foodStageID+"])/c[1];");
            }
          }
          FGPW.println("          }");
          FGPW.println("        }");
        }
      }
    }
    FGPW.println("        }");
    FGPW.println("      }");
    FGPW.println("    }");
    FGPW.println("");
    for (int i=0; i<chems.length; i++) {
      final String chName = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chName,"uptake")) {
        FGPW.println("    _"+chName+"_Ing+=Math.max(0,_request_"+chName+")/c[1];");
        FGPW.println("    _request_"+chName+"=0.0;");
      }
      if (chemInUse(model,fg,chName,"release")) {
        FGPW.println("    _release_"+chName+"=0.0;");
      }
    }
    
    final boolean[][] foodInUse = getPreyList(fg,model);
    int k=0;
    for (int i=0; i<foodInUse.length; i++) {
      for (int j=0; j<foodInUse[i].length; j++) {
        if (foodInUse[i][j]) {
          final XMLTag speciesTag = model.getTag("species").getTags("species")[i];
          final String species = StringTools.nonSpace(speciesTag.getAttribute("name"));
          final XMLTag preyFG = model.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg"));
          final String stage = StringTools.nonSpace(preyFG.getTags("stage")[j].getValue("name"));
          FGPW.println("    IngestedCells["+k+"] = _ingested_"+species+"_"+stage+"/c[1];");
          FGPW.println("    _ingested_"+species+"_"+stage+"=0.0;");
          FGPW.println("    _requested_"+species+"_"+stage+"=0.0;");
        } else FGPW.println("    IngestedCells["+k+"] = 0.0;");
        k++;
      }
    }
    FGPW.println("    z[2] = z[1];");
    FGPW.println("    z[1] = z[0];");
    FGPW.println("    c[0] = c[1]; // Changes in PM have been done on c[1]...");
    FGPW.println("    blayer = Kernel.W.B_Layer[(int)z[0]];");
    if ((fg.getTag("predator") != null) && (model.getTag("closure") != null) && (fg.getValue("predator").equals("true"))) compileFGClosureUpdate(FGPW,Stages,fg,model);

       FGPW.println("    _ambientTemperature = Utils.getTemperature(z[1]);");
    FGPW.println("    _ambientVisIrrad = Utils.getVisIrrad(z[1]);");
    FGPW.println("    // Prepare variables (set defaults in case they're not changed");
    XMLTag[] vs=fg.getTags("variable");
    for (int i=0; i<vs.length; i++)
      FGPW.println("    "+vs[i].getValue("name")+"[0]="+vs[i].getValue("name")+"[1];");

    vs=fg.getTags("varietyvariable");
    for (int i=0; i<vs.length; i++) {
      if ((vs[i].getValue("name").indexOf("$")==-1) && (!vs[i].getValue("name").equals("IngestedCells")))
        FGPW.println("    for (int _shv=0; _shv<" + vs[i].getValue("name") + ".length; _shv++) "+vs[i].getValue("name") + "[_shv][0]="+vs[i].getValue("name") + "[_shv][1];");
    }

    vs=fg.getTags("varietylocal");
    for (int i=0; i<vs.length; i++) {
      FGPW.println("    for (int _i=0; _i<"+vs[i].getValue("name")+".length; _i++)");
      FGPW.println("      for (int _j=0; _j<"+vs[i].getValue("name")+"[_i].length; _j++)");
      FGPW.println("        "+vs[i].getValue("name") + "[_i][_j]=0.0;");
    }
    XMLTag[] w=fg.getTags("local");
    for (int i=0; i<w.length; i++) FGPW.println("    "+w[i].getValue("name")+" = 0.0;");
    
    for (int i=0; i<chemPoolsInUse.length; i++) {
      if (chemPoolsInUse[i]) {
        FGPW.println("    _"+model.getTags("chemical")[i].getValue("name")+"_Pool_New = _"+model.getTags("chemical")[i].getValue("name")+"_Pool_Old;");
      }
    }

    FGPW.println("  }");
    FGPW.println("");

    FGPW.println("  public void genericEndUpdate() {");
    FGPW.println("    c[1] = c[0]; // Apply any cell division changes, and update pools...");
    for (int i=0; i<chemUse.length; i++) {
      if (chemUse[i]) FGPW.println("    _"+chems[i].getValue("name")+"_Pool_Old = _"+chems[i].getValue("name")+"_Pool_New;");
    }
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"release")) FGPW.println("    _release_"+chem+"*=c[1];");
    }
    
    if (pchangeNeeded) {
      FGPW.println("    int _totalChanges=0;");
      FGPW.println("    for (int _i=0; _i<_pchanges.length; _i++) if (_pchanges[_i]>0) _totalChanges++;");
      FGPW.println("    for (int _i=0; _i<_pchanges.length; _i++) {");
      FGPW.println("      if (_pchanges[_i]>0) {");
      FGPW.println("        final double _newPlankton = c[1]*(_pchanges[_i]/_totalChanges);");
      FGPW.println("        c[0]-=_newPlankton;");
      FGPW.println("        _pchanges[_i]=0;");
      FGPW.println("        "+fgName+" X"+countCreates+" = ("+fgName+") getClone();");
      FGPW.println("        X"+countCreates+".c[0]=_newPlankton;");
      FGPW.println("        X"+countCreates+".c[1]=X"+countCreates+".c[0];");
      FGPW.println("        X"+countCreates+"._CurrentStage=_i;");
      for (int i=0; i<chems.length; i++) {
        final String chem = StringTools.nonSpace(chems[i].getValue("name"));
        if (chemInUse(model,fg,chem,"uptake")) FGPW.println("    X"+countCreates+"._request_"+chem+"*=_newPlankton*_request_"+chem+";");
      }
      for (int i=0; i<ingNeeded.length; i++) {
        for (int j=0; j<ingNeeded[i].length; j++) {
          if (ingNeeded[i][j]) {
            final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
            final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
            final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
            FGPW.println("        X"+countCreates+"."+var+"="+var+"*_newPlankton;");
          }
        }
      }
      vs = fg.getTags("variable");
      for (int i=0; i<vs.length; i++) {
        int hist = Integer.parseInt(vs[i].getValue("hist"));
        for (int j=hist; j>0; j--) {
          FGPW.println("        X"+countCreates+"."+vs[i].getValue("name")+"["+j+"]=X"+countCreates+"."+vs[i].getValue("name")+"["+(j-1)+"];");
        }
      }
      vs = fg.getTags("varietyvariable");
      for (int i = 0; i < vs.length; i++) {
        if ((vs[i].getValue("name").indexOf("$") == -1) && (!vs[i].getValue("name").equals("IngestedCells"))) {
          int hist=1;
          if (vs[i].getTag("hist")!=null) hist = Integer.parseInt(vs[i].getValue("hist"));
          if (hist<1) hist=1;
          for (int j=hist; j>0; j--) 
            FGPW.println("        for (int _shv=0; _shv<" + vs[i].getValue("name") + ".length; _shv++) X"+countCreates+"."+vs[i].getValue("name") + "[_shv]["+j+"]=X"+countCreates+"."+vs[i].getValue("name") + "[_shv]["+(j-1)+"];");
        }        
      }
      
      FGPW.println("        X"+countCreates+".declarePresence();");
      FGPW.println("        Kernel.W.addQueuedAgent(X"+countCreates+");");
      FGPW.println("        blayer.SpeciesStateDemography[params._type][_CurrentStage][_reasons[_i]]+=_newPlankton;");      
      FGPW.println("        Kernel.W.SpeciesStateDemographyCol[params._type][_CurrentStage][_reasons[_i]]+=_newPlankton;");      
      FGPW.println("        if (((X" + countCreates + ".log) && (SysVars.getStageLog(X" + countCreates + ".getParams()._type,X"+countCreates+"._CurrentStage)) || ((log) && (SysVars.getStageLog(params._type, _CurrentStage))))) {");
      FGPW.println("          if (Logging.getLineage()) Logging.writePChangeChildToLineage(X" + countCreates + ".getParams()._type, Kernel.myTime, X" + countCreates + ".id, id,_CurrentStage,X" + countCreates + "._CurrentStage);");
      FGPW.println("          if (Logging.getLifespan()) Logging.writePChangeChildToLifespan(X" + countCreates + ".getParams()._type, Kernel.myTime, X" + countCreates + ".id, id, X" + countCreates + ".c[1], _i);");
      FGPW.println("        }");
      FGPW.println("      }");
      FGPW.println("    }");
      FGPW.println("    c[1]=c[0]; // Update sub-population size after pchanges");
      countCreates++;
    }
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("    "+var+"*=c[1];");
        }
      }
    }

    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) FGPW.println("    _request_"+chem+"*=c[1];");
    }
    FGPW.println("    Kernel.W.AgentCounts[params._type][_CurrentStage]++;");
    FGPW.println("    blayer.AgentCounts[params._type][_CurrentStage]++;");
    FGPW.println("    if (_NextStage!=_CurrentStage) {");
    FGPW.println("      Kernel.W.queueChangeAgent(this);");
    FGPW.println("      _CurrentStage=_NextStage;");
    FGPW.println("    }");
    FGPW.println("    declarePresence();");
    FGPW.println("    if ((LiveSim.inUse()) && (id==LiveSim.logID)) updateLiveSim(false);");
    FGPW.println("    _writeAudits();");
    FGPW.println("");

    vs = fg.getTags("variable");
    for (int i=0; i<vs.length; i++) {
      int hist = Integer.parseInt(vs[i].getValue("hist"));
      for (int j=hist; j>0; j--) {
        FGPW.println("    "+vs[i].getValue("name")+"["+j+"]="+vs[i].getValue("name")+"["+(j-1)+"];");
      }
    }
    vs = fg.getTags("varietyvariable");
    for (int i = 0; i < vs.length; i++) {
      if ((vs[i].getValue("name").indexOf("$") == -1) && (!vs[i].getValue("name").equals("IngestedCells"))) {
        int hist=1;
        if (vs[i].getTag("hist")!=null) hist = Integer.parseInt(vs[i].getValue("hist"));
        if (hist<1) hist=1;
        for (int j=hist; j>0; j--) 
          FGPW.println("    for (int _shv=0; _shv<" + vs[i].getValue("name") + ".length; _shv++) "+vs[i].getValue("name") + "[_shv]["+j+"]="+vs[i].getValue("name") + "[_shv]["+(j-1)+"];");
      }        
    }

    FGPW.println("  }");
    FGPW.println("");
    
    for (int i = 0; i < Stages.length; i++)
      CompileStage(fg,fgName, Stages[i].getValue("name"), FGPW,model);

    FGPW.println("  public void update() {");
    XMLTag[] stages = fg.getTags("stage");
    for (int i=0; i<stages.length; i++) {
      FGPW.print("    ");
      if (i>0) FGPW.print("else ");
      FGPW.println("if (_CurrentStage=="+i+") update_"+StringTools.nonSpace(stages[i].getValue("name"))+"();");
    }    
    FGPW.println("  }");
    LiveSimCompiler.writeLiveSimFG(model, fg, FGPW);
    compileFGParameters(model,fg);
    compileInitialisation(model,FGPW, fg);
    compileMemoryManagement(FGPW, fg, model);
    AuditTrailCompiler.writeAuditInit(model.getTag("output"), FGPW, fg, deathReasons, model);
    AuditTrailCompiler.writeAuditVariables(model.getTag("output"), FGPW, fg, deathReasons, model);
    CompileFGSnapshotSupport(model,FGPW, fg);
    FGPW.println("}");
    FGPW.flush();
    FGPW.close();
  }

  private static void compileFGClosureFunctions(XMLTag model, PrintWriter FGPW, XMLTag fg) {
    XMLTag sizeVar = fg.getTagWhere("variable", "name", "S_t");
    if (sizeVar != null) sizeVar.setName("sizevar");
    else sizeVar = fg.getTagWhere("sizevar", "name", "S_t");
    XMLTag closureTag = model.getTag("closure");
    XMLTag predatorTag = null;
    if (closureTag != null) predatorTag = closureTag.getTagWhere("predator", "name", fg.getValue("name"));
    XMLTag depthTag = null;
    XMLTag sizeTag = null;
    XMLTag concTag = null;
    if (predatorTag != null) {
      depthTag = predatorTag.getTagWhere("function", "name", "VerticalDistribution");
      sizeTag = predatorTag.getTagWhere("function", "name", "Size");
      concTag = predatorTag.getTagWhere("function", "name", "TotalConcentration");
    }
    FGPW.println("  public double getPredSize() {");
    XMLTag sizeEqTag = null;
    String eq = "";
    if (sizeTag != null) {
      sizeEqTag = sizeTag.getTag("equation");
      eq = sizeEqTag.getValue("eq");
    }
    eq = StringTools.spit(eq, "\\assign{");
    eq = StringTools.RHS(eq, StringTools.getUnNested(eq, ','));
    FGPW.print("    return ");
    if (sizeEqTag != null) CompileExpression(model,eq, FGPW, sizeEqTag, sizeTag, fg, true);
    else FGPW.print("0.0;");
    FGPW.println(";");
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public double getTotalConcentration() {");
    XMLTag concEqTag = null;
    if (concTag != null) concEqTag = concTag.getTag("equation");
    eq = "";
    if (concEqTag != null) {
      eq = concEqTag.getValue("eq");
      eq = StringTools.spit(eq, "\\assign{");
      eq = StringTools.RHS(eq, StringTools.getUnNested(eq, ','));
    }
    FGPW.print("    return ");
    if (concEqTag != null) CompileExpression(model,eq, FGPW, concEqTag, concTag, fg, true);
    else FGPW.print("0.0;");
    FGPW.println(";");
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public double getVerticalDistribution() {");
    XMLTag depthEqTag = depthTag.getTag("equation");
    eq = depthEqTag.getValue("eq");
    eq = StringTools.spit(eq, "\\assign{");
    eq = StringTools.RHS(eq, StringTools.getUnNested(eq, ','));
    FGPW.print("    return ");
    CompileExpression(model,eq, FGPW, depthEqTag, depthTag, fg, true);
    FGPW.println(";");
    FGPW.println("  }");
    FGPW.println("");
  }
  
  private static void compileFGClosureUpdate(PrintWriter FGPW, XMLTag[] Stages, XMLTag fg, XMLTag model) {
    int occ = 0;
    ArrayList occs = new ArrayList();
    for (int i = 0; i < Stages.length; i++) {
      if ((Stages[i].getAttribute("closure") != null) && (Stages[i].getAttribute("closure").equals("true"))) {
        occ++;
        occs.add(new Integer(i));
      }
    }
    if (occ > 0) {
      String spaces = "    ";
      if (occ < Stages.length) {
        spaces = "      ";
        FGPW.print("    if ");
        if (occ == 1) FGPW.println("(_CurrentStage==" + occs.get(0).toString() + ") {");
        else if (occ > 1) {
          FGPW.print("(");
          for (int i = 0; i < occ; i++) {
            FGPW.print("(_CurrentStage==" + occs.get(i).toString() + ")");
            if (i < occ - 1) FGPW.print(" || ");
          }
          FGPW.println(") {");
        }
      }
      FGPW.println(spaces + "double _cratio = c[1];");
      FGPW.println(spaces + "c[0]=(getTotalConcentration()*getVerticalDistribution());");
      FGPW.println(spaces + "if (c[0]>0) {");
      FGPW.println(spaces + "  _cratio /= c[0];");
      XMLTag[] chems = model.getTags("chemical");
      final boolean[] chemsInUse = getChemPoolUsage(fg,model);
      final boolean[] chemIngUse = getChemIngUsage(fg,model);
      for (int i=0; i<chems.length; i++) {
        final String chemName = StringTools.nonSpace(chems[i].getValue("name"));
        if (chemsInUse[i]) FGPW.println(spaces + "    _"+chemName+"_Pool_Old = _"+chemName+"_Pool_Old*_cratio;");
        if (chemIngUse[i]) FGPW.println(spaces + "    _"+chemName+"_Ing = _"+chemName+"_Ing*_cratio;");
      }
      FGPW.println(spaces + "}");
      if (occ < Stages.length) FGPW.println("    }");
    }
  }
  
  private static void compileFGParameters(XMLTag model, XMLTag fg) {
    String fgName = fg.getValue("name");
    PrintWriter FGPW = StringTools.OpenOutputFile(theOutputDir + "/VEW/Sim/_" + StringTools.nonSpace(fgName)+ "Params.java");
    FGPW.println("package VEW.Sim;");
    FGPW.println("  public class _"+StringTools.nonSpace(fgName)+"Params extends FGParams {");
    
    XMLTag[] params = fg.getTags("parameter");
    for (int i=0; i<params.length; i++) 
      FGPW.println("    double "+StringTools.nonSpace(params[i].getValue("name"))+" = 0.0;");
    XMLTag[] foodsets = fg.getTags("varietyconcentration");
    for (int i=0; i<foodsets.length; i++) {
      if (!foodsets[i].getValue("name").toUpperCase().equals("INGESTION")) {
        FGPW.println("    int[] "+StringTools.nonSpace(foodsets[i].getValue("name"))+"_types;");
        FGPW.println("    int[] "+StringTools.nonSpace(foodsets[i].getValue("name"))+"_status;");
      }
    }
    XMLTag[] foodparams = fg.getTags("varietyparameter");
    for (int i=0; i<foodparams.length; i++)
      FGPW.println("    double[] "+StringTools.nonSpace(foodparams[i].getValue("name"))+";");
    
    /* In case there are predator parameters too */
    
    if ((fg.getTag("predator") != null) && (model.getTag("closure") != null) && (fg.getValue("predator").equals("true"))) {
      XMLTag[] vs = model.getTag("closure").getTagWhere("predator", "name", fg.getValue("name")).getTags("parameter");
      for (int i = 0; i < vs.length; i++)
        FGPW.println("    public double " + vs[i].getValue("name") + "; // " + vs[i].getValue("desc"));
    }
    
    
    
    FGPW.println("");
    FGPW.println("    public _"+StringTools.nonSpace(fgName)+"Params() {}");
    FGPW.println("");
    XMLTag speciesParent = model.getTag("species");
    XMLTag[] speciesTags = speciesParent.getTagsWhere("species","@fg",fgName);
    int speciesOfFg = 0;
    for (int i=0; i<speciesTags.length; i++) {
      String speciesName = speciesTags[i].getAttribute("name");
      FGPW.println("    public void set"+StringTools.nonSpace(speciesName)+"Params() {");
      FGPW.println("      _name = new String(\""+speciesName+"\");");
      int speciesNo = getIDForSpecies(model,speciesName);
      FGPW.println("      _type = "+speciesNo+";");
      FGPW.println("      _speciesOfFG = "+(speciesOfFg++)+";");
      double xValue = Double.parseDouble(speciesTags[i].getAttribute("x"));
      XMLTag[] speciesParams = speciesTags[i].getTags("param");
      for (int j=0; j<speciesParams.length; j++) {
        double aValue = Double.parseDouble(speciesParams[j].getAttribute("a"));
        double bValue = Double.parseDouble(speciesParams[j].getAttribute("b"));
        double theValue = aValue*Math.pow(xValue,bValue);
        FGPW.println("      "+speciesParams[j].getAttribute("name")+" = "+String.valueOf(theValue)+";");
      }
      
      if ((fg.getTag("predator") != null) && (model.getTag("closure") != null) && (fg.getValue("predator").equals("true"))) {
        XMLTag[] vs = model.getTag("closure").getTagWhere("predator", "name", fg.getValue("name")).getTags("parameter");
        for (int j = 0; j < vs.length; j++) FGPW.println("      " + StringTools.nonSpace(vs[j].getValue("name")) + " = "+vs[j].getValue("value")+";");
      }

      
      XMLTag[] foodSets = model.getTag("foodsets").getTags("foodset");
      for (int j=0; j<foodSets.length; j++) {
        String foodSetName = foodSets[j].getAttribute("name");
        String predatorSpeciesName = foodSetName.substring(0,foodSetName.indexOf(":")-1);
        foodSetName = foodSetName.substring(foodSetName.indexOf(":")+2);
        if (predatorSpeciesName.equals(speciesName)) {
          String types = new String("      "+foodSetName+"_types = new int[] {");
          String stages = new String("      "+foodSetName+"_status = new int[] {");
          XMLTag[] foods = foodSets[j].getTags("food");
          for (int k=0; k<foods.length; k++) {
            String foodSpecies = foods[k].getAttribute("species");
            XMLTag foodSpeciesTag = speciesParent.getTagWhere("species","@name",foodSpecies);
            speciesNo = getIDForSpecies(model,foodSpecies);
            String foodFG = foodSpeciesTag.getAttribute("fg");
            String foodStage = foods[k].getAttribute("stage");
            types = types + String.valueOf(speciesNo);
            stages = stages + foodFG+"._STAGE_"+foodStage;
            if (k<foods.length-1) {
              types = types + ",";
              stages = stages + ",";
            }
          }
          types = types +"};";
          stages = stages + "};";
          FGPW.println(types);
          FGPW.println(stages);

          if (foods.length>0) {
            XMLTag[] paramTags0 = foods[0].getTags("param");
            int paramCount = paramTags0.length;
            String[] paramNames = new String[paramCount];
            for (int pn=0; pn<paramCount; pn++) paramNames[pn]=paramTags0[pn].getAttribute("name");
            for (int k=0; k<paramCount; k++) {
              FGPW.print("      "+paramNames[k]+" = new double[] {");
              for (int l=0; l<foods.length; l++) {
                XMLTag theParam = foods[l].getTagWhere("param","@name",paramNames[k]);
                double aValue = Double.parseDouble(theParam.getAttribute("a"));
                double bValue = Double.parseDouble(theParam.getAttribute("b"));
                double val = aValue*Math.pow(xValue,bValue);
                FGPW.print(String.valueOf(val));
                if (l<foods.length-1) FGPW.print(",");
              }
              FGPW.println("};");
            }
          }
        }
      }
        
      FGPW.println("    }");
      FGPW.println("");
    }
    FGPW.println("  }");
    FGPW.flush();
    FGPW.close();
  }
  
  private static void compileSpeciesParamDefinitions(XMLTag model, PrintWriter FGPW, XMLTag fg) {
    XMLTag speciesTag = model.getTag("species");
    if (speciesTag!=null) {
      XMLTag[] species = speciesTag.getTags("species");
      for (int i=0; i<species.length; i++) {
        String fgName = StringTools.nonSpace(fg.getValue("name"));
        if (StringTools.nonSpace(species[i].getAttribute("fg")).equals(fgName)) {
          String speciesName = StringTools.nonSpace(species[i].getAttribute("name"));
          FGPW.println("  public static _"+fgName+"Params _"+speciesName+"Params = new _"+fgName+"Params();");//.set"+speciesName+"Params();");
        }
      }
    }
  }
  
  private static void compileInitialisation(XMLTag model, PrintWriter FGPW, XMLTag fg) {
    String fgName = fg.getValue("name");
    FGPW.println("  /* Initialisation Routines */");
    FGPW.println("");
    FGPW.println("  public "+StringTools.nonSpace(fgName)+"() {");
    FGPW.println("    super();");
    FGPW.println("    params = new _"+StringTools.nonSpace(fgName)+"Params();");
    FGPW.println("  }");
   
    FGPW.println("");
    FGPW.println("  public void initialiseSpeciesVars() {");
    XMLTag[] varvars = fg.getTags("varietyvariable");
    for (int i = 0; i < varvars.length; i++) {
      if (!varvars[i].getValue("name").equals("IngestedCells")) {
        int hist=1;
        if (varvars[i].getTag("hist")!=null) hist = Integer.parseInt(varvars[i].getValue("hist"));
        if (hist<1) hist=1;
        FGPW.println("    " + varvars[i].getValue("name") + " = new double[params." + varvars[i].getAttribute("link") + "_types.length]["+(hist+1)+"];");
      }
    }
     
   FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public static void initialisePlankton() {");
    FGPW.println("    System.out.print(\"Initialising \"+spareParts.length+\" " + fgName + "\");");
    FGPW.println("    for (int i=0; i<spareParts.length; i++) spareParts[i] = new " + StringTools.nonSpace(fgName) + "();");
    FGPW.println("    System.out.println(\" Done\");");
    FGPW.println("    _initialiseAuditOutput();");
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public static void handleDistributions() {");
    
    XMLTag initialTag = model.getTag("initialplankton");
    if (initialTag!=null) {
      XMLTag[] distributions = initialTag.getTags("distribution");
      for (int i=0; i<distributions.length; i++) {
        XMLTag distribution = distributions[i];
        String species = distribution.getAttribute("species");
        String fgDistName = model.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
        if (StringTools.nonSpace(fgDistName).equals(StringTools.nonSpace(fgName))) {
          double permetre = Double.parseDouble(distributions[i].getAttribute("permetre"));
          double top = Double.parseDouble(distributions[i].getAttribute("top"));
          double bottom = Double.parseDouble(distributions[i].getAttribute("bottom"));
          GregorianCalendar startSim = new GregorianCalendar();
          GregorianCalendar endSim = new GregorianCalendar();
          MiscUtils.findDateLimits(model,startSim,endSim);
          
          String stage = distributions[i].getAttribute("stage");
          if (permetre>0) {
            
            FGPW.println("    if (Kernel.myTime=="+distributions[i].getAttribute("date")+"L) {");
            FGPW.println("      for (double _depth="+top+"; _depth<"+bottom+"; _depth+="+(1.0/permetre)+") {");
            FGPW.println("        "+StringTools.nonSpace(fgName)+" _x = "+StringTools.nonSpace(fgName)+".getParticle();");
            FGPW.println("        _x.params = _"+StringTools.nonSpace(species)+"Params;");
            FGPW.println("        _x.initialiseSpeciesVars();");            
            FGPW.println("        _x.z[0]=_depth;");
            FGPW.println("        _x.z[1]=_depth;");
            FGPW.println("        _x.z[2]=_depth;");
            FGPW.println("        _x.c[0]=("+distributions[i].getAttribute("popsize")+");");
            FGPW.println("        _x.c[1]=_x.c[0];");
            FGPW.println("        _x._CurrentStage = _STAGE_"+stage+";");
            XMLTag[] vars = distribution.getTags("var");
            boolean[] chemsUsed = getChemPoolUsage(fg,model);
            for (int j=0; j<vars.length; j++) {
              XMLTag var = vars[j];
              String varName = var.getAttribute("name");
              if (varName.endsWith("$Pool")) {
                String chem = varName.substring(0,varName.indexOf("$"));
                final int chemNo = getIDForChemical(chem,model);
                if (chemsUsed[chemNo]) {
                  chem=StringTools.nonSpace(chem);
                  FGPW.print("        _x._"+chem+"_Pool_Old = ");
                  FGPW.print(var.getAttribute("val"));
                  double rnd = Double.parseDouble(var.getAttribute("rnd"));
                  if (rnd!=0) FGPW.print("+Utils.rnd("+rnd+")");
                  FGPW.println(";");
                }
                
              } else if (varName.indexOf("$")>=0) { // Variety based
                String rawVar = varName.substring(0,varName.indexOf("$"));
                varName = varName.substring(varName.indexOf("$")+1);
                String varSpecies =varName.substring(0,varName.indexOf(":"));
                String varStage = varName.substring(varName.indexOf(":")+1);
                XMLTag varTag = fg.getTagWhere("varietyvariable","name",rawVar);
                String link = varTag.getAttribute("link");
                XMLTag[] foods = model.getTag("foodsets").getTagWhere("foodset","@name",species+" : "+link).getTags("food");
                int k=0;
                boolean done = false;
                while ((!done) && (k<foods.length)) {
                  done=((foods[k].getAttribute("species").equals(varSpecies)) && (foods[k].getAttribute("stage").equals(varStage)));
                  if (!done) k++;
                }
                FGPW.print("        _x."+rawVar+"["+k+"][0]=(");
                FGPW.print(var.getAttribute("val"));
                double rnd = Double.parseDouble(var.getAttribute("rnd"));
                if (rnd!=0) FGPW.print("+Utils.rnd("+rnd+")");
                FGPW.println(");");
                FGPW.println("        _x."+rawVar+"["+k+"][1]=_x."+rawVar+"["+k+"][0];");
              } else {
                FGPW.print("        _x."+varName+"[1]=(");
                FGPW.print(var.getAttribute("val"));
                double rnd = Double.parseDouble(var.getAttribute("rnd"));
                if (rnd!=0) FGPW.print("+Utils.rnd("+rnd+")");
                FGPW.println(");");
                FGPW.println("        _x."+varName+"[0]=_x."+varName+"[1];");
                
              }
        

            }
            FGPW.println("        Kernel.W.addAgent(_x);");
            FGPW.println("        Kernel.W.SpeciesTotal[_x.getParams()._type][_x._CurrentStage]+=_x.c[1];");
            FGPW.println("        _x.blayer.SpeciesConcentration_old[_x.getParams()._type][_x._CurrentStage]+=_x.c[1];");
            FGPW.println("        _x.blayer.SpeciesConcentration[_x.getParams()._type][_x._CurrentStage]+=_x.c[1];");
            for (int j=0; j<vars.length; j++) {
              XMLTag var = vars[j];
              String varName = var.getAttribute("name");
              if (varName.endsWith("$Pool")) {
                String chem = varName.substring(0,varName.indexOf("$"));
                final int chemNo = getIDForChemical(chem,model);
                if (chemsUsed[chemNo]) {
                  chem=StringTools.nonSpace(chem);
                  FGPW.println("        _x.blayer.particulate_chem["+chem+".ID_"+chem+"]+=_x._"+chem+"_Pool_Old*_x.c[1];");
                  FGPW.println("        _x.blayer.particulate_chem_ss["+chem+".ID_"+chem+"][_x.params._type][_x._CurrentStage]+=_x._"+chem+"_Pool_Old*_x.c[1];");
                }
              }
            }
            FGPW.println("      }");
            FGPW.println("    }");
            if (i<distributions.length-1) FGPW.println("");
          }
        }
      }
    }
    FGPW.println("  }");
    FGPW.println("");
  }
  
  private static void CompileFGSnapshotSupport(XMLTag model, PrintWriter FGPW, XMLTag fg) {
    boolean[][] ingNeeded = getPreyList(fg,model);
    XMLTag[] specs = model.getTag("species").getTags("species");

    String fgName = fg.getValue("name");
    FGPW.println("  public void writeSnapshot(DataOutputStream snapshot) {");
    FGPW.println("    try {");
    FGPW.println("      // don't write type - done elsewhere");
    FGPW.println("      snapshot.writeLong(id);");
    FGPW.println("      snapshot.writeInt(params._type);");
    FGPW.println("      snapshot.writeInt(_CurrentStage);");
    FGPW.println("      snapshot.writeDouble(z[0]);");
    FGPW.println("      snapshot.writeDouble(z[1]);");
    FGPW.println("      snapshot.writeDouble(z[2]);");
    FGPW.println("      snapshot.writeDouble(c[0]);");
    FGPW.println("      snapshot.writeDouble(c[1]);");
    XMLTag[] chems = model.getTags("chemical");
    boolean[] poolsInUse = getChemPoolUsage(fg,model);
    for (int i=0; i<chems.length; i++) {
      String chemName = StringTools.nonSpace(chems[i].getValue("name"));
      if (poolsInUse[i]) FGPW.println("      snapshot.writeDouble(_"+chemName+"_Pool_Old);");
    }
    
    XMLTag[] vars = fg.getTags("variable");
    FGPW.println("      snapshot.writeInt("+vars.length+");");
    for (int i=0; i<vars.length; i++) {
      int hist = Integer.parseInt(vars[i].getValue("hist"));
      for (int j=0; j<=hist; j++) {
        FGPW.println("      snapshot.writeDouble("+vars[i].getValue("name") + "["+j+"]);");
      }
    }

    XMLTag[] varvars = fg.getTags("varietyvariable");
    FGPW.println("      snapshot.writeInt("+varvars.length+");");
    for (int i = 0; i < varvars.length; i++) {
      if (!varvars[i].getValue("name").equals("IngestedCells")) {
        FGPW.println("      snapshot.writeInt(params."+varvars[i].getAttribute("link")+"_types.length);");
        FGPW.println("      for (int i=0; i<params." + varvars[i].getAttribute("link") + "_types.length; i++) {");
        int hist=1;
        if (varvars[i].getTag("hist")!=null) hist=Integer.parseInt(varvars[i].getValue("hist"));
        if (hist<1) hist=1;
        for (int j=0; j<=hist; j++)
          FGPW.println("        snapshot.writeDouble("+ varvars[i].getValue("name") + "[i]["+j+"]);");
        FGPW.println("        }");
      }
    }
    
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("      snapshot.writeDouble(_request_"+chem+");");
    }
    
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          FGPW.println("      snapshot.writeDouble(_requested_"+
            StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+
            StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"))+");");
        }
      }
    }

    FGPW.println("    } catch (Exception e) { e.printStackTrace(); }");
    FGPW.println("  }");
    FGPW.println("  public FunctionalGroup readSnapshot(DataInputStream snapshot) {");
    FGPW.println("    try {");
    FGPW.println("      " + StringTools.nonSpace(fgName) + " X = " + StringTools.nonSpace(fgName) + ".getParticle();");
    FGPW.println("      X.id = snapshot.readLong();");
    FGPW.println("      int specid = snapshot.readInt();");
    XMLTag[] speciesTags = model.getTag("species").getTags("species");
    for (int i=0; i<speciesTags.length; i++) 
      if (StringTools.nonSpace(speciesTags[i].getAttribute("fg")).equals(StringTools.nonSpace(fgName))) 
        FGPW.println("      if (specid=="+i+") X.params = _"+StringTools.nonSpace(speciesTags[i].getAttribute("name"))+"Params;");
    FGPW.println("      X.initialiseSpeciesVars();");
    FGPW.println("      X._CurrentStage = snapshot.readInt();");
    FGPW.println("      X.z[0]=snapshot.readDouble();");
    FGPW.println("      X.z[1]=snapshot.readDouble();");
    FGPW.println("      X.z[2]=snapshot.readDouble();");
    FGPW.println("      if (X.z[0]>=Kernel.W.B_Layer.length) X.blayer=Kernel.W.B_Layer[Kernel.W.B_Layer.length-1];");
    FGPW.println("      else X.blayer=Kernel.W.B_Layer[(int)Math.floor(X.z[0])];");
    FGPW.println("      X.c[0]=snapshot.readDouble();");
    FGPW.println("      X.c[1]=snapshot.readDouble();");
    for (int i=0; i<chems.length; i++) {
      String chemName = StringTools.nonSpace(chems[i].getValue("name"));
      if (poolsInUse[i]) FGPW.println("      X._"+chemName+"_Pool_Old = snapshot.readDouble();");
    }
    FGPW.println("      snapshot.readInt(); // number of vars");
    for (int i=0; i<vars.length; i++) {
      final int hist = Integer.parseInt(vars[i].getValue("hist"));
      for (int j=0; j<=hist; j++) {
        FGPW.println("      X."+vars[i].getValue("name") + "["+j+"]=snapshot.readDouble();");
      }
    }
    FGPW.println("      snapshot.readInt(); // number of v-vars");
    for (int i = 0; i < varvars.length; i++) {
      if (!varvars[i].getValue("name").equals("IngestedCells")) {      
        FGPW.println("      snapshot.readInt(); // Number of entries in this v-var");
        FGPW.println("      for (int i=0; i<X.params." + varvars[i].getAttribute("link") + "_types.length; i++) {");
        int hist=1;
        if (varvars[i].getTag("hist")!=null) hist=Integer.parseInt(varvars[i].getValue("hist"));
        if (hist<1) hist=1;
        for (int j=0; j<=hist; j++)
          FGPW.println("        "+varvars[i].getValue("name")+"[i]["+j+"]=snapshot.readDouble();");
        FGPW.println("    }");
      }
    }
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("      X._request_"+chem+"=snapshot.readDouble();");
    }
    
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          FGPW.println("      X._requested_"+
            StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+
            StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"))+"=snapshot.readDouble();");
        }
      }
    }


    FGPW.println("      return X;");
    FGPW.println("    } catch (Exception e) { e.printStackTrace(); }");
    FGPW.println("    return null;");
    FGPW.println("  }");

  }
  
  private static void compileMemoryManagement(PrintWriter FGPW, XMLTag fg, XMLTag model) {
    String fgName = fg.getValue("name");
    XMLTag[] vars = fg.getTags("variable");
    FGPW.println("  /* MEMORY MANAGEMENT FUNCTIONS */");
    FGPW.println("");
    FGPW.println("  private static " + StringTools.nonSpace(fgName) + "[] spareParts = new " + StringTools.nonSpace(fgName) + "[10];");
    FGPW.println("  private static int sparePartCount = 9;");

    FGPW.println("  public static void getMoreParticles() {");
    FGPW.println("    spareParts = new " + StringTools.nonSpace(fgName) + "[spareParts.length+100];");
    FGPW.println("    sparePartCount+=100;");
    FGPW.println("    for (int i=0; i<=sparePartCount; i++) spareParts[i] = new " + StringTools.nonSpace(fgName) + "();");
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public void freeParticle() {");
    FGPW.println("    ingestionLoss=0;");
    FGPW.println("    liveTime=0;");
    FGPW.println("    spareParts[++sparePartCount] = this;");
    FGPW.println("    liveTime=-1;");
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public void mergeWith(FunctionalGroup __fg2) {");
    FGPW.println("    " + StringTools.nonSpace(fgName) + " _fg2 = (" + StringTools.nonSpace(fgName) + ") __fg2;");
    FGPW.println("    double _OurCells = c[1];");
    FGPW.println("    double _TheirCells = _fg2.c[1];");
    FGPW.println("    if ((_OurCells==0) && (_TheirCells==0)) {");
    FGPW.println("      _OurCells=1;");
    FGPW.println("      _TheirCells=1;");
    FGPW.println("    }");
    FGPW.println("    final double _totalCells = _OurCells + _TheirCells;");
    final XMLTag[] chems = model.getTags("chemical");
    final boolean[] chemsInUse = getChemPoolUsage(fg,model);

    for (int i=0; i<chems.length; i++) {
      final String chemName = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemsInUse[i])
        FGPW.println("    if (_"+chemName+"_Pool_Old!=_fg2._"+chemName+"_Pool_Old) _"+chemName+"_Pool_Old = ((_OurCells*_"+chemName+"_Pool_Old)+(_TheirCells*_fg2._"+chemName+"_Pool_Old))/(_totalCells);");
    }
    
    for (int i=0; i<vars.length; i++) {
      final String v = vars[i].getValue("name");
      final int hist = Integer.parseInt(vars[i].getValue("hist"));
      for (int j=0; j<=hist; j++)
        FGPW.println("    "+v+"["+j+"]=(("+v+"["+j+"]*c[0])+(_fg2."+v+"["+j+"]*_fg2.c[0]))/(c[0]+_fg2.c[0]);");
    }
    XMLTag[] varvars = fg.getTags("varietyvariable");
    for (int i=0; i<varvars.length; i++) {
      String link = varvars[i].getAttribute("link");
      String name = varvars[i].getValue("name");
      if (!name.equals("IngestedCells")) {
        FGPW.println("    for (int _"+link+"=0; _"+link+"<params."+link+"_types.length; _"+link+"++) {");
        int hist=1;
        if (varvars[i].getTag("hist")!=null) hist=Integer.parseInt(varvars[i].getValue("hist"));
        if (hist<1) hist=1;
        for (int j=0; j<=hist; j++)
          FGPW.println("        "+name+"[_"+link+"]["+j+"]=(("+name+"[_"+link+"]["+j+"]*_OurCells)+(_fg2."+name+"[_"+link+"]["+j+"]*_TheirCells))/_totalCells;");
        FGPW.println("    }");
      } 
    }
    final boolean[][] ingNeeded = getPreyList(fg,model);
    XMLTag[] specs = model.getTag("species").getTags("species");

    
    FGPW.println("    // Undo previous uptake/ingest requests for each agent...");
    FGPW.println("    { // Create a scope for dealing with the \"parent\" agent");
    FGPW.println("      final double _z1 = (z[1]>z[2])?z[2]:z[1];");
    FGPW.println("      final double _z2 = (z[1]>z[2])?z[1]:z[2];");
    FGPW.println("      final double _zdiff = _z2-_z1;");
    FGPW.println("      BLayer _b1 = Utils.findBLayer(_z1);");
    FGPW.println("      BLayer _b2 = Utils.findBLayer(_z2);");
    FGPW.println("      if (_b1.Depth<Kernel.W.MLDepth) _b1=Kernel.W.mixedLayer;");
    FGPW.println("      if (_b2.Depth<Kernel.W.MLDepth) _b2=Kernel.W.mixedLayer;");    
    FGPW.println("      if (_b1==_b2) {");
    
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("        if ("+var+">0) _b1.ingest_request["+i+"]["+j+"]=Math.max(0,_b1.ingest_request["+i+"]["+j+"]-"+var+");");
        }
      }
    }

    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("        if (_request_"+chem+">0) _b1.request_"+chem+"=Math.max(0,_b1.request_"+chem+"-_request_"+chem+");");
    }
    for (int j=0; j<chemsInUse.length; j++) {
      if (chemsInUse[j]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[j].getValue("name"));
        final String cid = chemName+".ID_"+chemName;
        FGPW.println("        final double _"+chemName+"_c = _"+chemName+"_Pool_Old*c[1];");
        FGPW.println("        _b1.particulate_chem["+cid+"]=Math.max(0,_b1.particulate_chem["+cid+"]-_"+chemName+"_c);");
        FGPW.println("        _b1.particulate_chem_ss["+cid+"][params._type][_CurrentStage]=Math.max(0,_b1.particulate_chem_ss["+cid+"][params._type][_CurrentStage]-_"+chemName+"_c);");
      }
    }

    
    FGPW.println("      } else {");
    FGPW.println("        final double _topProp = (_b1==Kernel.W.mixedLayer)?(1+(int)Kernel.W.MLDepth-_z1)/_zdiff:((_b1.Depth+_b1.Height)-_z1)/_zdiff;");
    FGPW.println("        final double _bottomProp = (_z2-_b2.Depth)/_zdiff;");
    FGPW.println("        final double _midProp = _b2.Height/_zdiff;");
    FGPW.println("        for (int _layer=(int)_b1.Depth; _layer<=_b2.Depth; _layer++) {");
    FGPW.println("          BLayer _b = Kernel.W.B_Layer[_layer];");
    FGPW.println("          double _prop = (_b==_b1)?_topProp:(_b==_b2)?_bottomProp:_midProp;");
    FGPW.println("          if (_b.Depth<Kernel.W.MLDepth) {");
    FGPW.println("            _b=Kernel.W.mixedLayer; // Overwrite the layer being treated");
    FGPW.println("            _layer=(int)Kernel.W.MLDepth; // Treat MLD as one layer");
    FGPW.println("            _prop=(Math.min(_z2,(1.0+(int)Kernel.W.MLDepth))-_z1)/_zdiff; // Prop of MLD time");
    FGPW.println("          }"); 
    FGPW.println("          if (_prop>0) {");
    FGPW.println("            final double _propC = _prop*c[1];");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("            if (_request_"+chem+">0) _b.request_"+chem+"=Math.max(0,_b.request_"+chem+"-(_request_"+chem+"*_prop));");
    }

    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("            if ("+var+">0) _b.ingest_request["+i+"]["+j+"]=Math.max(0,_b.ingest_request["+i+"]["+j+"]-("+var+"*_prop));");
        }
      }
    }
    for (int j=0; j<chemsInUse.length; j++) {
      if (chemsInUse[j]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[j].getValue("name"));
        final String cid = chemName+".ID_"+chemName;
        FGPW.println("            final double _"+chemName+"_c = _"+chemName+"_Pool_Old*_propC;");
        FGPW.println("            _b.particulate_chem["+cid+"]=Math.max(0,_b.particulate_chem["+cid+"]-_"+chemName+"_c);");
        FGPW.println("            _b.particulate_chem_ss["+cid+"][params._type][_CurrentStage]=Math.max(0,_b.particulate_chem_ss["+cid+"][params._type][_CurrentStage]-_"+chemName+"_c);");
      }
    }
    FGPW.println("          }");
    FGPW.println("        }");
    FGPW.println("      }");
    FGPW.println("    }");
    FGPW.println("    { // Another scope for dealing with the \"child\" agent");
    FGPW.println("      final double _z1 = (_fg2.z[1]>_fg2.z[2])?_fg2.z[2]:_fg2.z[1];");
    FGPW.println("      final double _z2 = (_fg2.z[1]>_fg2.z[2])?_fg2.z[1]:_fg2.z[2];");
    FGPW.println("      final double _zdiff = _z2-_z1;");
    FGPW.println("      BLayer _b1 = Utils.findBLayer(_z1);");
    FGPW.println("      BLayer _b2 = Utils.findBLayer(_z2);");
    FGPW.println("      if (_b1.Depth<Kernel.W.MLDepth) _b1=Kernel.W.mixedLayer;");
    FGPW.println("      if (_b2.Depth<Kernel.W.MLDepth) _b2=Kernel.W.mixedLayer;");    
    FGPW.println("      if (_b1==_b2) {");

    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_fg2._requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("        if ("+var+">0) _b1.ingest_request["+i+"]["+j+"]=Math.max(0,_b1.ingest_request["+i+"]["+j+"]-"+var+");");
        }
      }
    }

    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("        if (_fg2._request_"+chem+">0) _b1.request_"+chem+"=Math.max(0,_b1.request_"+chem+"-_fg2._request_"+chem+");");
    }
    for (int j=0; j<chemsInUse.length; j++) {
      if (chemsInUse[j]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[j].getValue("name"));
        final String cid = chemName+".ID_"+chemName;
        FGPW.println("        final double _"+chemName+"_c = _fg2._"+chemName+"_Pool_Old*_fg2.c[1];");
        FGPW.println("        _b1.particulate_chem["+cid+"]=Math.max(0,_b1.particulate_chem["+cid+"]-_"+chemName+"_c);");
        FGPW.println("        _b1.particulate_chem_ss["+cid+"][params._type][_CurrentStage]=Math.max(0,_b1.particulate_chem_ss["+cid+"][params._type][_CurrentStage]-_"+chemName+"_c);");
      }
    }

    FGPW.println("      } else {");
    FGPW.println("        final double _topProp = (_b1==Kernel.W.mixedLayer)?(1+(int)Kernel.W.MLDepth-_z1)/_zdiff:((_b1.Depth+_b1.Height)-_z1)/_zdiff;");
    FGPW.println("        final double _bottomProp = (_z2-_b2.Depth)/_zdiff;");
    FGPW.println("        final double _midProp = _b2.Height/_zdiff;");
    FGPW.println("        for (int _layer=(int)_b1.Depth; _layer<=_b2.Depth; _layer++) {");
    FGPW.println("          BLayer _b = Kernel.W.B_Layer[_layer];");
    FGPW.println("          double _prop = (_b==_b1)?_topProp:(_b==_b2)?_bottomProp:_midProp;");
    FGPW.println("          if (_b.Depth<Kernel.W.MLDepth) {");
    FGPW.println("            _b=Kernel.W.mixedLayer; // Overwrite the layer being treated");
    FGPW.println("            _layer=(int)Kernel.W.MLDepth; // Treat MLD as one layer");
    FGPW.println("            _prop=(Math.min(_z2,(1.0+(int)Kernel.W.MLDepth))-_z1)/_zdiff; // Prop of MLD time");
    FGPW.println("          }"); 
    FGPW.println("          if (_prop>0) {");
    FGPW.println("            final double _propC = _prop*_fg2.c[1];");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("            if (_fg2._request_"+chem+">0) _b.request_"+chem+"=Math.max(0,_b.request_"+chem+"-(_fg2._request_"+chem+"*_prop));");
    }

    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_fg2._requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("            if ("+var+">0) _b.ingest_request["+i+"]["+j+"]=Math.max(0,_b.ingest_request["+i+"]["+j+"]-("+var+"*_prop));");
          
        }
      }
    }
    for (int j=0; j<chemsInUse.length; j++) {
      if (chemsInUse[j]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[j].getValue("name"));
        final String cid = chemName+".ID_"+chemName;
        FGPW.println("            final double _"+chemName+"_c=_fg2._"+chemName+"_Pool_Old*_propC;");
        FGPW.println("            _b.particulate_chem["+cid+"]=Math.max(0,_b.particulate_chem["+cid+"]-_"+chemName+"_c);");
        FGPW.println("            _b.particulate_chem_ss["+cid+"][params._type][_CurrentStage]=Math.max(0,_b.particulate_chem_ss["+cid+"][params._type][_CurrentStage]-_"+chemName+"_c);");
      }
    }

    FGPW.println("          }");
    FGPW.println("        }");
    FGPW.println("      }");
    FGPW.println("    }");
    FGPW.println("    // And now make uptake/ingest request for merged agent...");
    FGPW.println("    z[0]=((z[0]*_OurCells)+(_fg2.z[0]*_TheirCells))/_totalCells;");
    FGPW.println("    z[1]=((z[1]*_OurCells)+(_fg2.z[1]*_TheirCells))/_totalCells;");
    FGPW.println("    z[2]=((z[2]*_OurCells)+(_fg2.z[2]*_TheirCells))/_totalCells;");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("    _request_"+chem+"+=_fg2._request_"+chem+"; // _request is already *c");
    }
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("    "+var+"+=_fg2."+var+"; // _request is already *c");
        }
      }
    }
    
    FGPW.println("    c[0]+=_fg2.c[0];");
    FGPW.println("    c[1]=c[0];");
    FGPW.println("    { // Last scope for dealing with the new agent");
    FGPW.println("      final double _z1 = (z[1]>z[2])?z[2]:z[1];");
    FGPW.println("      final double _z2 = (z[1]>z[2])?z[1]:z[2];");
    FGPW.println("      final double _zdiff = _z2-_z1;");
    FGPW.println("      BLayer _b1 = Utils.findBLayer(_z1);");
    FGPW.println("      BLayer _b2 = Utils.findBLayer(_z2);");
    FGPW.println("      if (_b1.Depth<Kernel.W.MLDepth) _b1=Kernel.W.mixedLayer;");
    FGPW.println("      if (_b2.Depth<Kernel.W.MLDepth) _b2=Kernel.W.mixedLayer;");    
    FGPW.println("      if (_b1==_b2) {");

    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("        if ("+var+">0) _b1.ingest_request["+i+"]["+j+"]+="+var+";");
        }
      }
    }

    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("        if (_request_"+chem+">0) _b1.request_"+chem+"+=_request_"+chem+";");
    }
    for (int j=0; j<chemsInUse.length; j++) {
      if (chemsInUse[j]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[j].getValue("name"));
        FGPW.println("        _b1.particulate_chem["+chemName+".ID_"+chemName+"]+=_"+chemName+"_Pool_Old*c[1];");
        FGPW.println("        _b1.particulate_chem_ss["+chemName+".ID_"+chemName+"][params._type][_CurrentStage]+=_"+chemName+"_Pool_Old*c[1];");
      }
    }
    FGPW.println("      } else {");
    FGPW.println("        final double _topProp = (_b1==Kernel.W.mixedLayer)?(1+(int)Kernel.W.MLDepth-_z1)/_zdiff:((_b1.Depth+_b1.Height)-_z1)/_zdiff;");
    FGPW.println("        final double _bottomProp = (_z2-_b2.Depth)/_zdiff;");
    FGPW.println("        final double _midProp = _b2.Height/_zdiff;");
    FGPW.println("        for (int _layer=(int)_b1.Depth; _layer<=_b2.Depth; _layer++) {");
    FGPW.println("          BLayer _b = Kernel.W.B_Layer[_layer];");
    FGPW.println("          double _prop = (_b==_b1)?_topProp:(_b==_b2)?_bottomProp:_midProp;");
    FGPW.println("          if (_b.Depth<Kernel.W.MLDepth) {");
    FGPW.println("            _b=Kernel.W.mixedLayer; // Overwrite the layer being treated");
    FGPW.println("            _layer=(int)Kernel.W.MLDepth; // Treat MLD as one layer");
    FGPW.println("            _prop=(Math.min(_z2,(1.0+(int)Kernel.W.MLDepth))-_z1)/_zdiff; // Prop of MLD time");
    FGPW.println("          }"); 
    FGPW.println("          if (_prop>0) {");
    FGPW.println("            final double _propC = _prop*c[1];");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("            if (_request_"+chem+">0) _b.request_"+chem+"+=_request_"+chem+"*_prop;");
    }

    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("            if ("+var+">0) _b.ingest_request["+i+"]["+j+"]+="+var+"*_prop;");
          
        }
      }
    }
    for (int j=0; j<chemsInUse.length; j++) {
      if (chemsInUse[j]) {
        final String chemName = StringTools.nonSpace(model.getTags("chemical")[j].getValue("name"));
        FGPW.println("            final double _"+chemName+"_c = _"+chemName+"_Pool_Old*_propC;");
        FGPW.println("            _b.particulate_chem["+chemName+".ID_"+chemName+"]+=_"+chemName+"_c;");
        FGPW.println("            _b.particulate_chem_ss["+chemName+".ID_"+chemName+"][params._type][_CurrentStage]+=_"+chemName+"_c;");
      }
    }

    FGPW.println("          }");
    FGPW.println("        }");
    FGPW.println("      }");
    FGPW.println("    }");
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public FunctionalGroup getClone() {");
    FGPW.println("    " + StringTools.nonSpace(fgName) + " X = " + StringTools.nonSpace(fgName) + ".getParticle();");
    FGPW.println("    X.blayer = blayer;");
    FGPW.println("    X.params = params;");
    FGPW.println("    X.initialiseSpeciesVars();");                
    for (int i=0; i<chems.length; i++) {
      final String chemName = StringTools.nonSpace(chems[i].getValue("name"));
      final String poolName = "_"+chemName+"_Pool_";
      if (chemsInUse[i]) {
        FGPW.println("    X."+poolName+"Old = "+poolName+"Old;");
      }
    }
    
    FGPW.println("    X.z[0]=z[0];");
    FGPW.println("    X.z[1]=z[1];");
    FGPW.println("    X.z[2]=z[2];");
    FGPW.println("    X.c[0]=c[0];");
    FGPW.println("    X.c[1]=c[1];");
    FGPW.println("    X._CurrentStage = _CurrentStage;");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("    X._request_"+chem+"=0;");
    }
    
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("    X."+var+"="+"0;");
        }
      }
    }


    for (int i=0; i<vars.length; i++) {
      final int hist = Integer.parseInt(vars[i].getValue("hist"));
      final String v = vars[i].getValue("name");
      for (int j=0; j<=hist; j++)
        FGPW.println("    X."+v+"["+j+"]="+v+"["+j+"];");
    }
    for (int i=0; i<varvars.length; i++) {
      String name=varvars[i].getValue("name");
      if (!name.equals("IngestedCells")) {
        String link = varvars[i].getAttribute("link");
        FGPW.println("    for (int _"+link+"=0; _"+link+"<params."+link+"_types.length; _"+link+"++) {");
        
        int hist=1;
        if (varvars[i].getTag("hist")!=null) hist=Integer.parseInt(varvars[i].getValue("hist"));
        if (hist<1) hist=1;
        for (int j=0; j<=hist; j++)
          FGPW.println("      X."+name+"[_"+link+"]["+j+"]="+name+"[_"+link+"]["+j+"];");
        FGPW.println("    }");
      }
    }

    FGPW.println("    return X;");
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public FunctionalGroup getBlankParticle() {");
    FGPW.println("    " + StringTools.nonSpace(fgName) + " X = " + StringTools.nonSpace(fgName) + ".getParticle();");
    FGPW.println("    X.blayer = blayer;");
    FGPW.println("    X.z[0]=0;");
    FGPW.println("    X.z[1]=0;");
    FGPW.println("    X.z[2]=0;");
    FGPW.println("    X.c[0]=0;");
    FGPW.println("    X.c[1]=0;");
    FGPW.println("    X.params = params;");
    FGPW.println("    X.initialiseSpeciesVars();");                
    for (int i=0; i<chems.length; i++) {
      final String chemName = StringTools.nonSpace(chems[i].getValue("name"));
      final String poolName = "_"+chemName+"_Pool_";
      if (chemsInUse[i]) {
        FGPW.println("    X."+poolName+"Old = 0;");
      }
    }
    FGPW.println("    X._CurrentStage = _CurrentStage;");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("    X._request_"+chem+"=0;");
    }
    
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("    X."+var+"="+"0;");
        }
      }
    }


    for (int i=0; i<vars.length; i++) {
      final int hist = Integer.parseInt(vars[i].getValue("hist"));
      final String v = vars[i].getValue("name");
      for (int j=0; j<=hist; j++)
        FGPW.println("    X."+v+"["+j+"]=0;");
    }
    for (int i=0; i<varvars.length; i++) {
      String name=varvars[i].getValue("name");
      if (!name.equals("IngestedCells")) {
        String link = varvars[i].getAttribute("link");
        FGPW.println("    for (int _"+link+"=0; _"+link+"<params."+link+"_types.length; _"+link+"++) {");
        
        int hist=1;
        if (varvars[i].getTag("hist")!=null) hist=Integer.parseInt(varvars[i].getValue("hist"));
        if (hist<1) hist=1;
        for (int j=0; j<=hist; j++)
          FGPW.println("      X."+name+"[_"+link+"]["+j+"]=0;");
        FGPW.println("    }");
      }
    }

    FGPW.println("    return X;");
    FGPW.println("  }");
    FGPW.println("");

    
    
    FGPW.println("  public void split(double _no) {");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("    _request_"+chem+"/=_no;");
    }
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("    "+var+"/=_no;");
        }
      }
    }
    FGPW.println("  }");
    FGPW.println("");
    FGPW.println("  public void copyRequests(FunctionalGroup _fg0) {");
    FGPW.println("    "+StringTools.nonSpace(fgName)+" _fg = ("+StringTools.nonSpace(fgName)+") _fg0;");
    for (int i=0; i<chems.length; i++) {
      final String chem = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemInUse(model,fg,chem,"uptake")) 
        FGPW.println("    _request_"+chem+"=_fg._request_"+chem+";");
    }
    for (int i=0; i<ingNeeded.length; i++) {
      for (int j=0; j<ingNeeded[i].length; j++) {
        if (ingNeeded[i][j]) {
          final String foodFgName = model.getTag("species").getTags("species")[i].getAttribute("fg");
          final XMLTag foodFg = model.getTagWhere("functionalgroup","name",foodFgName);
          final String var = "_requested_"+StringTools.nonSpace(specs[i].getAttribute("name"))+"_"+StringTools.nonSpace(foodFg.getTags("stage")[j].getValue("name"));
          FGPW.println("    "+var+"=_fg."+var+";");
        }
      }
    }
    FGPW.println("  }");
    FGPW.println("");
    
    FGPW.println("  public static " + StringTools.nonSpace(fgName) + " getParticle() {");
    FGPW.println("    if (sparePartCount==-1) getMoreParticles();");
    FGPW.println("    " + StringTools.nonSpace(fgName) + " x = spareParts[sparePartCount];");
    FGPW.println("    spareParts[sparePartCount--] = null;");
    FGPW.println("    x.id = IDCount++;");
    FGPW.println("    x.setLog();");
    FGPW.println("    x.liveTime=Kernel.timeSteps;");
    FGPW.println("    return x;");
    FGPW.println("  }");
    FGPW.println("");

  }
  
  private static void CompileChemical(XMLTag model, XMLTag ch, int chIndex) {
    boolean pigment = false;
    if (ch.getValue("pigment").equals("true")) pigment = true;
    String chName = ch.getValue("name");
    status("   Chemical: " + chName);
    PrintWriter PW = StringTools.OpenOutputFile(theOutputDir + "/VEW/Sim/" + chName + ".java");
    PW.println("package VEW.Sim;");
    PW.println("import java.io.*;");
    PW.println("");
    PW.println("public class " + chName + " extends Chemical {");
    PW.println("  public static final String ChemName = \"" + chName + "\";");
    PW.println("  public boolean isPigment() { return " + String.valueOf(pigment) + "; }");
    PW.println("  public static final int ID_" + chName + "="+chIndex+";");
    PW.println("  // State Variables");
    PW.println("");
    XMLTag[] v = ch.getTags("variable");
    for (int i=0; i<v.length; i++) {
      int hist=1;
      if (v[i].getTag("hist")!=null) hist = Integer.parseInt(v[i].getValue("hist"));
      if (hist<1) hist=1;
      PW.println("  double[] " + v[i].getValue("name") + " = new double["+(hist+1)+"]; // " + v[i].getValue("desc"));
    }
    PW.println("");
    CompileLocalDefs(model,PW, ch);
    XMLTag[] funcs = ch.getTags("function");

    if (pigment) {
      CompilePigment(ch, PW);
    } else {
      PW.println("  public static double chi(double freq) { return 0.0; }");
      PW.println("  public static double e(double freq) { return 0.0; }");
    }

    PW.println();
    for (int i = 0; i < funcs.length; i++)
      CompileFunction(model,funcs[i], PW, ch, null, false);

    PW.println("  public void update() {");
    PW.println("    _ambientTemperature = Utils.getTemperature(z);");
    PW.println("    _ambientVisIrrad = Utils.getVisIrrad(z);");
    for (int i = 0; i < funcs.length; i++)
      PW.println("    " + StringTools.nonSpace(funcs[i].getValue("name")) + "();");

    XMLTag[] vs = ch.getTags("variable");
    for (int i=0; i<vs.length; i++) {
      final String var = vs[i].getValue("name");
      int hist=1;
      if (vs[i].getTag("hist")!=null) hist=Integer.parseInt(vs[i].getValue("hist"));
      for (int j=hist; j>0; j--) { 
        if (vs[i].getValue("name").indexOf("$") == -1) {
          PW.println("    "+var+"["+j+"]="+var+"["+(j-1)+"];");
        }
      }
    }

    PW.println("  }");
    PW.println("  public " + chName + "(BLayer _z) {");
    PW.println("    super(_z);");
    PW.println("    ID = ID_" + chName + ";");
    PW.println("    type = ID_" + chName + ";");

    PW.println("  }");
    PW.println("  public String name() { return ChemName; }");
    PW.println("");
    PW.println("  public void writeSnapshot(DataOutputStream snapshot) {");
    PW.println("    try {");
    PW.println("      snapshot.writeInt(type);");
    PW.println("      snapshot.writeInt("+vs.length+");");
    for (int i=0; i<vs.length; i++) {
      final String var = vs[i].getValue("name");
      int hist=1;
      if (vs[i].getTag("hist")!=null) hist=Integer.parseInt(vs[i].getValue("hist"));
      if (hist<1) hist=1;
      for (int j=0; j<=hist; j++) { 
        if (vs[i].getValue("name").indexOf("$") == -1) {
          PW.println("    snapshot.writeDouble("+var+"["+j+"]);");
          
        }
      }
    }
    PW.println("    } catch (Exception e) { e.printStackTrace(); }");
    PW.println("  }");
    PW.println("");
    PW.println("  public void readSnapshot(DataInputStream snapshot) {");
    PW.println("    try {");
    PW.println("      type = snapshot.readInt();");
    PW.println("      snapshot.readInt(); // Number of variables");
    for (int i=0; i<vs.length; i++) {
      final String var = vs[i].getValue("name");
      int hist=1;
      if (vs[i].getTag("hist")!=null) hist=Integer.parseInt(vs[i].getValue("hist"));
      for (int j=0; j<=hist; j++) { 
        if (vs[i].getValue("name").indexOf("$") == -1) {
          PW.println("    "+var+"["+j+"]=snapshot.readDouble();");
        }
      }
    }
    PW.println("    } catch (Exception e) { e.printStackTrace(); }");
    PW.println("  }");
    PW.println("}");
    PW.flush();
    PW.close();
  }

  private static void CompilePigment(XMLTag pi, PrintWriter PW) {
    XMLTag chiTag = pi.getTagWhere("spectrum", "name", PigmentPanel.Pigment_CHI);
    XMLTag eTag = pi.getTagWhere("spectrum", "name", PigmentPanel.Pigment_e);
    PW.println("  public static double chi(double freq) {");
    CompileGraphVals(chiTag.getValue("equation/eq"), PW);
    PW.println("  }");
    PW.println("  public static double e(double freq) {");
    CompileGraphVals(eTag.getValue("equation/eq"), PW);
    PW.println("  }");
  }

  private static void CompileModel(XMLTag model, ArrayList deathReasons) {
    status("Model:" + model.getValue("name"));
    getDeathReasons(model, deathReasons);
    XMLTag[] fgs = model.getTags("functionalgroup");
    clearVEWTempDir();
    for (int i = 0; i < fgs.length; i++) CompileFunctionalGroup(model,fgs[i], deathReasons);
    XMLTag[] chs = model.getTags("chemical");
    for (int i = 0; i < chs.length; i++) CompileChemical(model,chs[i],i);
  }

  
  private static void clearVEWTempDir() {
    File f = new File(theOutputDir+"/VEW/Sim/");
    File[] files = f.listFiles();
    for (int i=0; i<files.length; i++) {
      files[i].delete();
    }
  }
  
  private static void AddKernel(XMLTag model) {
    final String f = File.separator;
    final String commonDir = "VEW"+f+"Common";
    final String javaDir = theOutputDir+f+"VEW"+f+"Sim"+f;
    try {
      BLayerCompiler.writeBLayerJava(javaDir+"BLayer.java",model);
      ChemicalMaker.writeChemicalJava(javaDir+"Chemical.java",model);
      FGParamsMaker.writeFGParamsJava(javaDir+"FGParams.java",model);
      FunctionalGroupMaker.writeFunctionalGroupJava(javaDir+"FunctionalGroup.java",model);
      KernelMaker.writeKernelJava(javaDir+"Kernel.java",model);
      LauncherMaker.writeLauncherJava(javaDir+"Launcher.java",model);
      LiveSimCompiler.writeLiveSimJava(javaDir+"LiveSim.java",model);      
      LoggingMaker.writeLoggingJava(javaDir+"Logging.java",model);
      ParticleManagerMaker.writeParticleManagerJava(javaDir+"ParticleManager.java",model);
      PLayerMaker.writePLayerJava(javaDir+"PLayer.java",model);
      SimMaker.writeSimJava(javaDir+"Sim.java",model);
      StringTools.copyFile("."+f, theOutputDir, "trove.jar");
      UtilsMaker.writeUtilsJava(javaDir+"Utils.java",model);
      WColCompiler.writeWColJava(javaDir+"WaterCol.java",model);
      new File(theOutputDir + f+"VEW"+f+"Common"+f+"XML").mkdirs();
      StringTools.copyFile(commonDir + f+"XML", theOutputDir + f+"VEW"+f+"Common"+f+"XML", "XMLFile.java");
      StringTools.copyFile(commonDir + f+"XML", theOutputDir + f+"VEW"+f+"Common"+f+"XML", "XMLTag.java");
      StringTools.copyFile(commonDir + f+"XML", theOutputDir + f+"VEW"+f+"Common"+f+"XML", "XMLTagEvent.java");
      StringTools.copyFile(commonDir + f+"XML", theOutputDir + f+"VEW"+f+"Common"+f+"XML", "XMLTagListener.java");
      StringTools.copyFile(commonDir + f+"XML", theOutputDir + f+"VEW"+f+"Common"+f+"XML", "XMLPath.java");
      new File(theOutputDir + f+"VEW"+f+"Common"+f+"Random").mkdirs();
      StringTools.copyFile(commonDir + f+"Random", theOutputDir + f+"VEW"+f+"Common"+f+"Random", "RandomElement.java");
      StringTools.copyFile(commonDir + f+"Random", theOutputDir + f+"VEW"+f+"Common"+f+"Random", "RanMT.java");
      StringTools.copyFile(commonDir + f+"Random", theOutputDir + f+"VEW"+f+"Common"+f+"Random", "RandomSeedable.java");

      StringTools.copyFile(commonDir, theOutputDir + f+"VEW"+f+"Common", "JComboCheckBox.java");
      StringTools.copyFile(commonDir, theOutputDir + f+"VEW"+f+"Common", "BoundaryDataHeader.java");
      StringTools.copyFile(commonDir, theOutputDir + f+"VEW"+f+"Common", "BoundaryDataStep.java");
      StringTools.copyFile(commonDir, theOutputDir + f+"VEW"+f+"Common", "InitialDataLayer.java");
      StringTools.copyFile(commonDir, theOutputDir + f+"VEW"+f+"Common", "InitialDataObject.java");

    } catch (Exception e) { e.printStackTrace(); }
  }
}
