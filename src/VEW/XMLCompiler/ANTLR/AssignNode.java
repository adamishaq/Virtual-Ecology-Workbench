package VEW.XMLCompiler.ANTLR;

public class AssignNode extends ASTree implements RuleNode {

	private IdNode identifier;
	private ExprNode expr;
	
	public AssignNode(IdNode identifier, ExprNode expr) {
		this.identifier = identifier;
		this.expr = expr;
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
