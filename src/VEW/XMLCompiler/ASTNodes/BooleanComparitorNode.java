package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;

public class BooleanComparitorNode extends BExprNode {
	
	private ComparisonOperator comparitor;
	private ExprNode rExpr;
	private ExprNode lExpr;
	
	public BooleanComparitorNode (ComparisonOperator comparitor, ExprNode lExpr, ExprNode rExpr) {
		this.comparitor = comparitor;
		this.rExpr = rExpr;
		this.lExpr = lExpr;
	}

	@Override
	public void check() throws SemanticCheckException {
		rExpr.check();
		lExpr.check();
		Type rExprType = rExpr.getExprType();
		Type lExprType = lExpr.getExprType();
		setBExprType(checkTypeCompatible(rExprType, lExprType));

	}

	private Type checkTypeCompatible(Type rExprType, Type lExprType) {
		//TODO some sort of tracking of origins of food sets
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type boolType = (Type) tables.checkTypeTable("$boolean");
		if (rExprType instanceof Variety || lExprType instanceof Variety) {
			return new Variety("boolean", boolType);
		}
		return boolType;
	}

	@Override
	public String generateXML() {
		String op = "";
		switch (comparitor) {
		case EQUALS        : op = "equal"; break; 
		case NEQUALS       : op = "neq"; break; 
		case GREATERTHAN   : op = "greater"; break; 
		case LESSTHAN      : op = "less"; break; 
		case GREATEREQUALS : op = "greaterequal"; break;
		case LESSEQUALS    : op = "lessequal"; break; 
		}
		return "\\" + op + "{" + lExpr.generateXML() + "," + rExpr.generateXML() + "}";
	}

}
