package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.VarietyType;

public class UnaryPrimNode extends ExprNode {

	private UnaryPrimitive primitive;
	private ExprNode argument;
	
	public UnaryPrimNode(UnaryPrimitive primitive, ExprNode argExpr) {
		this.primitive = primitive;
		this.argument = argExpr;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		argument.check();
		Type argType = argument.getExprType();
		setExprType(checkCompatibility(argType));

	}

	private Type checkCompatibility(Type argType) {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		if (argType instanceof VarietyType) {
			return new VarietyType("float", floatType);
		}
		return floatType;
	}

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
		}
		return func + "(" + arg + ")";
	}


}
