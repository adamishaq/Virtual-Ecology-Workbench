package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;


public class BinaryPrimitiveNode extends ExprNode {

	private BinaryPrimitive prim;
	private ExprNode lExpr;
	private ExprNode rExpr;

	public BinaryPrimitiveNode(BinaryPrimitive prim, ExprNode lExpr, ExprNode rExpr, int line) {
		this.prim = prim;
		this.lExpr = lExpr;
		this.rExpr = rExpr;
		this.line_number = line;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		//TODO this might be able to take variety arguments, :/
		lExpr.check(enclosingCategory, enclosingTree);
		rExpr.check(enclosingCategory, enclosingTree);
		AmbientVariableTables varTables = AmbientVariableTables.getTables();
		Type floatType = (Type) varTables.checkTypeTable("$float");
		if (lExpr.getExprType() != floatType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("First argument does not evaluate to a float",line_number));
			return;
		}
		if (rExpr.getExprType() != floatType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Second argument does not evalute to a float",line_number));
			return;
		}
		setExprType(floatType);
		if (!UnitChecker.getUnitChecker().CheckUnitCompatability(rExpr.getUnits(), lExpr.getUnits())) {
			enclosingTree.addWarning("Comparison of two different unit types on line " + line_number);
			units = new ArrayList<Unit>();
			units.add(new Unit(0,"null",1));
		} else {
			this.units = rExpr.getUnits();
		}
	}

	@Override
	public String generateXML() {
		String op = "";
		switch (prim) {
		case MAX     : op = "max"; break; 
		case MIN    : op = "min"; break; 
		}
		return "\\" + op + "{" + lExpr.generateXML() + "," + rExpr.generateXML() + "}";
	}


	@Override
	public String generateLatex() {
		String op = "???";
		String left = "???";
		if (lExpr != null)
			left = lExpr.generateLatex();
		String right = "???";
		if (rExpr != null)
			right = rExpr.generateLatex();
		switch (prim) {
		case MAX     : op = " max "; break; 
		case MIN    : op = " min "; break; 
		}
		return op + "(" + left + "," + right + ")";
	}

}
