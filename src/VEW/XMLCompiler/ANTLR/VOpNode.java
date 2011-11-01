package VEW.XMLCompiler.ANTLR;

public class VOpNode  extends ASTree implements ExprNode {

	private VOperator vop;
	private ExprNode expression;
	
	public VOpNode(String _vop, ExprNode _expression) {
		this.vop = VOperator.valueOf(_vop);
		this.expression = _expression;
	}
	
	@Override
	public void check() {
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
