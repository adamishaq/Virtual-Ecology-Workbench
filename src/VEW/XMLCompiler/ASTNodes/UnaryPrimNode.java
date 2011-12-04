package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VarietyType;

public class UnaryPrimNode extends ExprNode {

	private UnaryPrimitive primitive;
	private ExprNode argument;
	
	public UnaryPrimNode(UnaryPrimitive primitive, ExprNode argExpr, int line) {
		this.primitive = primitive;
		this.argument = argExpr;
		this.line_number = line;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		argument.check(enclosingCategory, enclosingTree);
		Type argType = argument.getExprType();
		setExprType(argType);
		units = argument.getUnits();
		switch (primitive) {
		// This function requires dimensionless arguments
		case EXP 		 :
			if (!UnitChecker.getUnitChecker().isDimensionless(argument.getUnits())) {
				enclosingTree.addWarning("Expression raised to power of expression with units on line " +
						line_number);
			}
		// These functions return dimensionless results
		case ACOS 		 :
		case ASIN 		 : 
		case ATAN 		 : 
		case COS 		 :
		case SIN 		 : 
		case TAN 		 : 
		case RND 		 :
		case LN 		 :
		case LOGTEN 	 :
		case SALINITYAT  :
			units = UnitChecker.dimensionless_collection;
			break;
		// These functions don't alter the arguments units
		case ABS 		 :
		case SQRT 		 :
		case INTEGRATE 	 :
			units = argument.getUnits();
			break;
		// These functions all expect an argument of type meters and return various units
		case DENSITYAT 	 :
			ArrayList<Unit> density = new ArrayList<Unit>();
			density.add(new Unit(0,"kg",1));
			density.add(new Unit(0,"m",-3));
			check_depth_function("densityAt",density,enclosingTree);
			break;
		case FULLIRRADAT :
			ArrayList<Unit> irrad = new ArrayList<Unit>();
			irrad.add(new Unit(0,"W",1));
			irrad.add(new Unit(0,"m",-2));
			check_depth_function("fullIrradAt",irrad,enclosingTree);
			break;
		case TEMPAT 	 :
			ArrayList<Unit> temp = new ArrayList<Unit>();
			temp.add(new Unit(0,"K",1));
			check_depth_function("temperatureAt",temp,enclosingTree);
			break;
		case UVIRRADAT 	 :
			irrad = new ArrayList<Unit>();
			irrad.add(new Unit(0,"W",1));
			irrad.add(new Unit(0,"m",-2));
			check_depth_function("UVIrradAt",irrad,enclosingTree);
			break;
		case VISIRRADAT	 :
			irrad = new ArrayList<Unit>();
			irrad.add(new Unit(0,"W",1));
			irrad.add(new Unit(0,"m",-2));
			check_depth_function("visIrradAt",irrad,enclosingTree);
			break;
		// These functions expect an argument with units Wm-2, and return meters
		case DEPTHFORFI  :
			check_irrad_function("depthForFI",enclosingTree);
			break;
		case DEPTHFORVI  :
			check_irrad_function("depthForVI",enclosingTree);
		}
	}

	private void check_depth_function(String name,ArrayList<Unit> units,ConstructedASTree enclosingTree) {
		ArrayList<Unit> meters = new ArrayList<Unit>();
		meters.add(new Unit(0,"m",1));
		if(!UnitChecker.getUnitChecker().CheckUnitCompatability(this.argument.getUnits(),meters)) {
			enclosingTree.addWarning("Argument of function " + name + " should be of type meters.");
			this.units = UnitChecker.null_collection;
		} else {
			this.units = units;
		}
	}

	private void check_irrad_function(String name,ConstructedASTree enclosingTree) {
		ArrayList<Unit> irrad = new ArrayList<Unit>();
		irrad.add(new Unit(0,"W",1));
		irrad.add(new Unit(0,"m",-2));
		if(!UnitChecker.getUnitChecker().CheckUnitCompatability(this.argument.getUnits(),irrad)) {
			enclosingTree.addWarning("Argument of function " + name + " should be of type Wm^-2.");
			this.units = UnitChecker.null_collection;
		} else {
			ArrayList<Unit> meters = new ArrayList<Unit>();
			meters.add(new Unit(0,"m",1));
			this.units = meters;
		}
	}
	
/*
>>>>>>> 222e1b3e2c4736782da4ca167c65441f455547de
	private Type checkCompatibility(Type argType) {
		if (argType instanceof VarietyType) {
			return argType;
		}
		return argType;
	} */

	@Override
	public String generateXML() {
		String func = "";
		switch (primitive) {
		case ABS 		 : func = "abs"; break;
		case ACOS 		 : func = "acos"; break;
		case ASIN 		 : func = "asin"; break;
		case ATAN 		 : func = "atan"; break;
		case COS 		 : func = "cos"; break;
		case EXP 		 : func = "exp"; break;
		case LN 		 : func = "log"; break;
		case LOGTEN 	 : func = "log10"; break;
		case RND 		 : func = "rnd"; break;
		case SIN 		 : func = "sin"; break;
		case SQRT 		 : func = "sqrt"; break;
		case TAN 		 : func = "tan"; break;
		case DENSITYAT 	 : func = "densityAt"; break;
		case DEPTHFORFI  : func = "depthForFI"; break;
		case DEPTHFORVI  : func = "depthForVI"; break;
		case FULLIRRADAT : func = "fullIrradAt"; break;
		case SALINITYAT  : func = "salinityAt"; break;
		case TEMPAT 	 : func = "temperatureAt"; break;
		case UVIRRADAT 	 : func = "UVIrradAt"; break;
		case INTEGRATE 	 : func = "integrate"; break;
		case VISIRRADAT	 : func = "visIrradAt"; break;
		}
		return "\\" + func + "{" + argument.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String func = "???";
		String arg = "???";
		if (argument != null)
			arg = argument.generateLatex();
		switch (primitive) {
		case ABS 		 : return " | " + arg + " | ";
		case ACOS 		 : func = " \\cos ^ {-1} "; break;
		case ASIN 		 : func = " \\sin ^ {-1} "; break;
		case ATAN 		 : func = " \\tan ^ {-1} "; break;
		case COS 		 : func = " \\cos "; break;
		case EXP 		 : return " e ^ {" + arg + "}";
		case LN 		 : func = " \\ln "; break;
		case LOGTEN 	 : func = " log_{10} "; break;
		case RND 		 : func = " rnd "; break;
		case SIN 		 : func = " \\sin "; break;
		case SQRT 		 : return " \\sqrt{" + arg + "}";
		case TAN 		 : func = " \\tan "; break;
		case DENSITYAT 	 : func = " densityAt "; break;
		case DEPTHFORFI  : func = " depthForFI "; break;
		case DEPTHFORVI  : func = " depthForVI "; break;
		case FULLIRRADAT : func = " fullIrradAt "; break;
		case SALINITYAT  : func = " salinityAt "; break;
		case TEMPAT 	 : func = " temperatureAt "; break;
		case UVIRRADAT 	 : func = " UVIrradAt "; break;
		case INTEGRATE 	 : func = " integrate "; break;
		case VISIRRADAT  : func = " visIrradAt"; break;
		}
		return func + "(" + arg + ")";
	}


}
