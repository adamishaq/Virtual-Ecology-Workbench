package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Unit;


public class NumNode extends ExprNode {

	private float value;
	
	public NumNode(float value, int line) {
		this.value = value;
		this.line_number = line;
		units = new ArrayList<Unit>();
		units.add(new Unit(0,"dimensionless",1));
	}
	
	public float getValue() {
		return this.value;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		setExprType(tables.checkTypeTable("$float"));
	}

	@Override
	public String generateXML() {
		return "\\val{\\sival{" + value + ",0},\\unit{0,0,0}}";
	}

	@Override
	public String generateLatex() {
		return " " + value + " ";
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		visitor.visit(this);
	}

}
