package VEW.XMLCompiler.ASTNodes;

public class VarHistNode extends ExprNode {

	private IdNode identifier;
	private ExprNode expression;

	public VarHistNode (IdNode identifier, ExprNode expression) {
		this.identifier = identifier;
		this.expression = expression;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		return "\\varhist{" + identifier.generateXML() + "," + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		return "varhist( " + identifier.generateLatex() + " , " + expression.generateLatex() + " )";
	}

}
