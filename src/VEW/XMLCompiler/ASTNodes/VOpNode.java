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
		case AVERAGE : func = "???";
		case PRODUCT : func = "varietymul";
		case SUM 	 : func = "varietysum";
		}
		return "\\" + func + "{" + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String func = "";
		switch (vop) {
		case AVERAGE : func = " average ";
		case PRODUCT : func = " \\prod ";
		case SUM 	 : func = " \\sum ";
		}
		return func + "(" + expression.generateLatex() + ")";
	}

}
