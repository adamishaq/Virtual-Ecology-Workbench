package VEW.XMLCompiler.ASTNodes;

public class Variable {
	
	private String name;
	private Type type;
	private String description;

	
	public Variable(String _name, Type _type) {
		name = _name;
		type = _type;
		description = "";
	}
	
	public Variable(String _name, Type _type, String _description) {
		name = _name;
		type = _type;
		description = _description;
	}
	
	public Type getVarType() {
		return type;
	}
	
	public String getVarName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
