package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Local;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.Model.VarietyLocal;

public class IdNode extends ExprNode {
	
	private String name;
	private VariableType var;
	
	public IdNode(String name) {
		this.name = name;
	}

	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		VariableType v = enclosingCategory.checkAccessableVariableTable(name);
		if (v == null) {
			enclosingTree.addSemanticException(new SemanticCheckException("Unrecognized variable " + name,
					line_number));
		}
		else if ((v instanceof Local || v instanceof VarietyLocal) && !v.isAssignedTo()) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Local variable " + name + " has not been assigned to before reading",
					line_number));
		}
		else {
			var = (VariableType) v;
			exprType = var.getVarType();
		}
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
