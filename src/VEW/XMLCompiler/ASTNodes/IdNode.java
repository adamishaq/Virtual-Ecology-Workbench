package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.*;

public class IdNode extends ExprNode {
	
	private String name;
	private VariableType var;
	
	public IdNode(String name) {
		this.name = name;
	}

	@Override
	public void check() throws SemanticCheckException {
		VariableType v = getCatagory().checkAccessableVariableTable(name);
		if (v == null) {
			throw new SemanticCheckException("Unrecognized variable " + name);
		}
		if ((v instanceof Local || v instanceof VarietyLocal) && !v.isAssignedTo()) {
			throw new SemanticCheckException("Local variable " + name + " has not been assigned to before reading");
		}
		var = (VariableType) v;
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
