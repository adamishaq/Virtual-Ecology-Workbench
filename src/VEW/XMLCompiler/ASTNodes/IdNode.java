package VEW.XMLCompiler.ASTNodes;

public class IdNode extends ExprNode {
	
	private String name;
	private Variable var;
	
	public IdNode(String name) {
		this.name = name;
	}

	@Override
	public void check() throws SemanticCheckException {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Object obj = tables.checkAllTables(name);
		//TODO check other tables, like state vars etc
		if (obj == null) {
			throw new SemanticCheckException("Unrecognized variable " + name);
		}
		if (!(obj instanceof Variable)) {
			throw new SemanticCheckException(name + " is not a variable");
		}
		var = (Variable) obj;
		exprType = var.getVarType();
	}

	@Override
	public String generateXML() {
		return "\\var{" + name + "}";
	}
	
	public String getName() {
		return name;
	}

}
