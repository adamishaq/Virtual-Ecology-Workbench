package VEW.XMLCompiler.ASTNodes;

public class VarHistNode extends ExprNode {

	private IdNode identifier;
	private ExprNode expression;
//	private Variable var;

	public VarHistNode (IdNode identifier, ExprNode expression) {
		this.identifier = identifier;
		this.expression = expression;
		
	}
	
	@Override
	public void check() throws SemanticCheckException {
		identifier.check();
		expression.check();
		if (expression instanceof NumNode) {
			//TODO some checking for variables out of history ranges
		}
		//TODO find out if expressions in varhist is valid
		setExprType(expression.getExprType());

	}

	@Override
	public String generateXML() {
		return "\\varhist{" + identifier.generateXML() + "," + expression.generateXML() + "}";
	}

}
