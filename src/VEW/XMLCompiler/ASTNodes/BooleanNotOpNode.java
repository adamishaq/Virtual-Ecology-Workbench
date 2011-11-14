package VEW.XMLCompiler.ASTNodes;

public class BooleanNotOpNode extends BExprNode {

	private BExprNode expression;

	public BooleanNotOpNode (BExprNode expression) {
		this.expression = expression;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		expression.check();
		setBExprType(expression.getBExprType());
	}

	@Override
	public String generateXML() {
		return "\\not{" + expression.generateXML() + "}";
	}

}
