package VEW.XMLCompiler.ASTNodes;


import VEW.Planktonica2.DisplayOptions;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VarietyType;


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
		if (rExpr.getExprType() instanceof VarietyType) {
			setExprType(rExpr.getExprType());
		}
		if (UnitChecker.getUnitChecker().CheckUnitCompatability(rExpr.getUnits(), lExpr.getUnits()) == 0) {
			enclosingTree.addWarning("Comparison of two different unit types on line " + line_number);
			this.units = UnitChecker.null_collection;
		} else if (UnitChecker.getUnitChecker().CheckUnitCompatability(rExpr.getUnits(),
				lExpr.getUnits()) != 1) {
			// The units of the LHS will be used
			this.units = lExpr.getUnits();
		} else {
			this.units = UnitChecker.getUnitChecker().add_units(rExpr.getUnits(), lExpr.getUnits());
		}
		setExprType(lExpr.getExprType());
	}

	@Override
	public String generateXML() {
		String op = "";
		switch (prim) {
		case MAX     : op = "max"; break; 
		case MIN    : op = "min"; break; 
		}
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING) {
			float scale = UnitChecker.getUnitChecker().CheckUnitCompatability(lExpr.getUnits(),
					rExpr.getUnits());
			if (scale != 0 && scale != 1)
				return "\\" + op + "{" + lExpr.generateXML() + "," +
						"\\mul{" + scale + "," + rExpr.generateXML() + "}}";
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
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING) {
			float scale = UnitChecker.getUnitChecker().CheckUnitCompatability(lExpr.getUnits(),
					rExpr.getUnits());
			if (scale != 0 && scale != 1)
				return op + "(" + left + ",(" + scale + "*" + right + "))";
		}
		return op + "(" + left + "," + right + ")";
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		lExpr.acceptDependencyCheckVisitor(visitor);
		rExpr.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

}
