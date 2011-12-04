package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;



public class BooleanBinOpNode extends BExprNode {

	private BooleanBinOperator booleanOp;
	private BExprNode rBExpr;
	private BExprNode lBExpr;
	
	public BooleanBinOpNode(BooleanBinOperator booleanOp, BExprNode lBExpr, BExprNode rBExpr, int line) {
		this.booleanOp = booleanOp;
		this.rBExpr = rBExpr;
		this.lBExpr = lBExpr;
		this.line_number = line;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		rBExpr.check(enclosingCategory, enclosingTree);
		lBExpr.check(enclosingCategory, enclosingTree);
		Type rBExprType = rBExpr.getBExprType();
		Type lBExprType = lBExpr.getBExprType();
		try {
			setBExprType(checkTypeCompatible(rBExprType, lBExprType));
		} catch (BACONCompilerException e) {
			enclosingTree.addSemanticException(e);
			setBExprType(lBExprType);
		}
	}
	
	private Type checkTypeCompatible(Type rBExprType, Type lBExprType) throws BACONCompilerException {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type boolType = (Type) tables.checkTypeTable("$boolean");
		if (lBExprType instanceof VarietyType) {
			VarietyType vlType = (VarietyType) lBExprType;
			if (rBExprType instanceof VarietyType) {
				VarietyType vrType = (VarietyType) rBExprType;
				if (!vlType.checkLinkCompatible(vrType)) {
					throw new SemanticCheckException("Boolean expressions evaluate to varieties with different link sets",
														line_number);
				}
				return vrType;
			}
			return lBExprType;
		}
		if (rBExprType instanceof VarietyType) {
			return rBExprType;
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
