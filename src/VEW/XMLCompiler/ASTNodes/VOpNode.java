package VEW.XMLCompiler.ASTNodes;

public class VOpNode  extends ExprNode {

	private VOperator vop;
	private ExprNode expression;
	
	public VOpNode(VOperator _vop, ExprNode _expression) {
		this.vop = _vop;
		this.expression = _expression;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		// TODO - find out what avg codegens into
		String func = "";
		switch (vop) {
		case AVERAGE : func = "???"; break;
		case PRODUCT : func = "varietymul"; break;
		case SUM 	 : func = "varietysum"; break;
		}
		return "\\" + func + "{" + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String func = "???";
		String exp = "???";
		if (expression != null)
			exp = expression.generateLatex();
		switch (vop) {
		case AVERAGE : func = " average "; break;
		case PRODUCT : func = " \\prod "; break;
		case SUM 	 : func = " \\sum "; break;
		}
		return func + "(" + exp + ")";
	}

}
