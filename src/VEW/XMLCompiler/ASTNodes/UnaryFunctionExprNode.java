package VEW.XMLCompiler.ASTNodes;

public class UnaryFunctionExprNode extends RuleNode {

	private UnaryExprFunction function;
	private ExprNode expArg;

	public UnaryFunctionExprNode(UnaryExprFunction function, ExprNode expArg) {
		this.function = function;
		this.expArg = expArg;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		String func = "";
		switch (function) {
		case DIVIDE : func = "divide"; break;
		}
		return "\\" + func + "{" + expArg.generateXML() + "}";
	}

}
