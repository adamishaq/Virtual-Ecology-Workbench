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
	
	@Override
	public String generateLatex() {
		String latex_name = name;
		if (name.contains("_")) {
			latex_name = name.replaceFirst("_", "_{");
			latex_name += "}";
		}
		return latex_name;
	}
	
	public String getName() {
		return name;
	}

}
