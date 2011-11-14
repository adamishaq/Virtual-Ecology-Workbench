package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;

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
	public void check() throws SemanticCheckException {
		rBExpr.check();
		lBExpr.check();
		Type rBExprType = rBExpr.getBExprType();
		Type lBExprType = lBExpr.getBExprType();
		setBExprType(checkTypeCompatible(rBExprType, lBExprType));

	}
	
	private Type checkTypeCompatible(Type rBExprType, Type lBExprType) {
		AmbientVariableTables varTables = AmbientVariableTables.getTables();
		Type boolType = (Type) varTables.checkTypeTable("$boolean");
		if (rBExprType instanceof Variety || lBExprType instanceof Variety) {
			return new Variety("boolean", boolType);
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

}
