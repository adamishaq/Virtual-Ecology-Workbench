package VEW.XMLCompiler.ASTNodes;

public class BooleanNotOpNode extends BExprNode {

	private BExprNode expression;

	public BooleanNotOpNode (BExprNode expression) {
		this.expression = expression;
	}
	
	@Override
	public void check() {
		expression.check();
		setBExprType(expression.getBExprType());
	}

	@Override
	public String generateXML() {
		return "\\not{" + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		if (expression != null)
			return " \\sim " + expression.generateLatex();
		else
			return " \\sim ???";
	}
}
