package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VariableType;

public class IdNode extends ExprNode {
	
	private String name;
	private VariableType var;
	
	public IdNode(String name, int line) {
		this.name = name;
		this.line_number = line;
	}

	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		VariableType v = enclosingCategory.checkAccessableVariableTable(name);
		if (v == null) {
			enclosingTree.addSemanticException(new SemanticCheckException("Unrecognized variable " + name,
					line_number));
			units = new ArrayList<Unit>();
			units.add(new Unit(0,"dimensionless",1));
		}/*
		else if ((v instanceof Local || v instanceof VarietyLocal) && !v.isAssignedTo()) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Local variable " + name + " has not been assigned to before reading",
					line_number));
		}*/
		else {
			var = (VariableType) v;
			exprType = var.getVarType();
			units = var.getUnits();
		}
	}

	public void set_units(Catagory enclosingCategory) {
		VariableType v = enclosingCategory.checkAccessableVariableTable(name);
		if (v == null) {
			units = UnitChecker.null_collection;
		} else {
			var = v;
			units = var.getUnits();
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

	public VariableType getVariableType() {
		return this.var;
	}
	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		visitor.visit(this);
		
	}

}
