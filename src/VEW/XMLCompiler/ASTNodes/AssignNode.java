package VEW.XMLCompiler.ASTNodes;

public class AssignNode extends RuleNode {

	private IdNode identifier;
	private ExprNode expr;
	private Variable assignVar;
	
	public AssignNode(IdNode identifier, ExprNode expr) {
		this.identifier = identifier;
		this.expr = expr;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		String idName = identifier.getName();
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Object obj = tables.checkAllTables(idName);
		if (obj != null) {
			throw new SemanticCheckException(idName + " cannot be assigned to");
		}
		//TODO check and retrieve variable from table
		expr.check();
		if (expr.getExprType() != assignVar.getVarType()) {
			//TODO check if its ok
			throw new SemanticCheckException("Assign mismatch, my be ok actually");
		}
		
	}

	@Override
	public String generateXML() {
		return "\\assign{" + identifier.generateXML() + "," + expr.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String id = "???";
		if (identifier != null)
			id = identifier.generateLatex();
		String ex = "???";
		if (expr != null)
			ex = expr.generateLatex();
		return id + " = " + ex;
	}

}
