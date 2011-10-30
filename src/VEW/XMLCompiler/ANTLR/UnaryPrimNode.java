package VEW.XMLCompiler.ANTLR;

public class UnaryPrimNode extends ASTree implements ExprNode {

	private UnaryPrimitive primitive;
	private ExprNode argument;
	
	public UnaryPrimNode(UnaryPrimitive primitive, ExprNode argExpr) {
		this.primitive = primitive;
		this.argument = argExpr;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

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

}
