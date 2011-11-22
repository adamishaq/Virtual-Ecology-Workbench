package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Local;
import VEW.Planktonica2.model.VariableType;
import VEW.Planktonica2.model.VarietyLocal;

public class IdNode extends ExprNode {
	
	private String name;
	private VariableType var;
	
	public IdNode(String name) {
		this.name = name;
	}

	@Override
	public void check() {
		VariableType v = getCatagory().checkAccessableVariableTable(name);
		if (v == null) {
			CommonTreeWalker.add_exception(new SemanticCheckException("Unrecognized variable " + name,
					line_number));
		}
		if ((v instanceof Local || v instanceof VarietyLocal) && !v.isAssignedTo()) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("Local variable " + name + " has not been assigned to before reading",
					line_number));
		}
		var = (VariableType) v;
		exprType = var.getVarType();
	}

	@Override
	public String generateXML() {
		String var = name.replaceAll("FullIrradiance", "Full Irradiance");
		var = var.replace("VisibleIrradiance", "Visible Irradiance");
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
