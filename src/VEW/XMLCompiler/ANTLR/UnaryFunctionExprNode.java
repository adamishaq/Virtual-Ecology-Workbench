package VEW.XMLCompiler.ANTLR;

public class UnaryFunctionExprNode extends ASTree implements RuleNode {

	private UnaryExprFunction function;
	private ExprNode expArg;

	public UnaryFunctionExprNode(UnaryExprFunction function, ExprNode expArg) {
		this.function = function;
		this.expArg = expArg;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
