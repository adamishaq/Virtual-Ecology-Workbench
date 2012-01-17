package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

import VEW.Planktonica2.DisplayOptions;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.GlobalVariable;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VariableType;

/**
 * An AST node that represents an identifier
 * @author David Coulden
 *
 */
public class IdNode extends ExprNode {
	
	private String name; //Name of the identifier
	private VariableType var; //Variable structure if the identifier is a variable
	
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
		}
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
		String var = "";
		if (this.var instanceof GlobalVariable) {
			GlobalVariable gVar = (GlobalVariable) this.var;
			if (gVar.getWriteBackName() != null) {
				var = gVar.getWriteBackName();
			}
			else {
				var = this.var.getName();
			}
		}
		else {
			var = this.var.getName();
		}
		//Is required to solve backwards compatibility problems
		var = var.replaceAll("FullIrradiance", "Full Irradiance");
		var = var.replace("VisibleIrradiance", "Visible Irradiance");
		return "\\var{" + var + "}";
	}
	
	@Override
	public String generateLatex() {
		String latex_name = name;
		if (name.contains("_")) {
			latex_name = name.replaceFirst("_", "_{");
			latex_name += "}";
		}
		if (DisplayOptions.getOptions().PREVIEW_UNITS && this.getUnits() != null 
				&& !this.getUnits().isEmpty()) {
			String units = "";
			for (Unit u : this.getUnits()) {
				units += u.latex_format();
			}
			if (units.length() > 0)
				latex_name = "[" + latex_name + "/" + units + "]";
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
