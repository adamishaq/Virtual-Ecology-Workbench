package VEW.XMLCompiler.ANTLR;

public class CreateNode extends ASTree implements RuleNode {

	private IdNode identifier;
	private ExprNode expression;
	private AssignListNode assignList;

	public CreateNode (IdNode identifier, ExprNode expression) {
		this.identifier = identifier;
		this.expression = expression;
		this.assignList = null;
	}
	
	public CreateNode (IdNode identifier, ExprNode expression, AssignListNode assignList) {
		this.identifier = identifier;
		this.expression = expression;
		this.assignList = assignList;
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
