package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;



public class BooleanBinOpNode extends BExprNode {

	private BooleanBinOperator booleanOp;
	private BExprNode rBExpr;
	private BExprNode lBExpr;
	
	public BooleanBinOpNode(BooleanBinOperator booleanOp, BExprNode lBExpr, BExprNode rBExpr) {
		this.booleanOp = booleanOp;
		this.rBExpr = rBExpr;
		this.lBExpr = lBExpr;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		rBExpr.check(enclosingCategory, enclosingTree);
		lBExpr.check(enclosingCategory, enclosingTree);
		Type rBExprType = rBExpr.getBExprType();
		Type lBExprType = lBExpr.getBExprType();
		setBExprType(checkTypeCompatible(rBExprType, lBExprType));
	}
	
	private Type checkTypeCompatible(Type rBExprType, Type lBExprType) {
		AmbientVariableTables varTables = AmbientVariableTables.getTables();
		Type boolType = (Type) varTables.checkTypeTable("$boolean");
		if (rBExprType instanceof VarietyType || lBExprType instanceof VarietyType) {
			return new VarietyType("boolean", boolType);
		}
		return boolType;
		
	}

	@Override
	public String generateXML() {
		String op = "";
		switch (booleanOp) {
		case AND : op = "and"; break; 
		case OR  : op = "or"; break;
		}
		return "\\" + op + "{" + lBExpr.generateXML() + "," + rBExpr.generateXML() + "}";
	}

	
	public String generateLatex() {
		String op = "???";
		String left = "???";
		if (lBExpr != null)
			left = lBExpr.generateLatex();
		String right = "???";
		if (rBExpr != null)
			right = rBExpr.generateLatex();
		switch (booleanOp) {
		case AND : op = " \\cap "; break; 
		case OR  : op = " \\cup "; break;
		}
		return  left + "\\;" + op + "\\;" + right;
	}

}
