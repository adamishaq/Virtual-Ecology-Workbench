package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Type;


public class BinaryPrimitiveNode extends ExprNode {

	private BinaryPrimitive prim;
	private ExprNode lExpr;
	private ExprNode rExpr;

	public BinaryPrimitiveNode(BinaryPrimitive prim, ExprNode lExpr, ExprNode rExpr) {
		this.prim = prim;
		this.lExpr = lExpr;
		this.rExpr = rExpr;
	}
	
	@Override
	public void check() {
		//TODO this might be able to take variety arguments, :/
		lExpr.check();
		rExpr.check();
		AmbientVariableTables varTables = AmbientVariableTables.getTables();
		Type floatType = (Type) varTables.checkTypeTable("$float");
		if (lExpr.getExprType() != floatType) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("First argument does not evaluate to a float",line_number));
			return;
		}
		if (rExpr.getExprType() != floatType) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("Second argument does not evalute to a float",line_number));
			return;
		}
		setExprType(floatType);

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
