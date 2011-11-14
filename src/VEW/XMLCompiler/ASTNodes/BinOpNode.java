package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.Type;
import VEW.Planktonica2.ControllerStructure.VarietyType;

public class BinOpNode extends ExprNode {
	
	private MathematicalOperator operator;
	private ExprNode lExpr;
	private ExprNode rExpr;
	
	public BinOpNode (MathematicalOperator operator, ExprNode lExpr, ExprNode rExpr) {
		this.operator = operator;
		this.lExpr = lExpr;
		this.rExpr = rExpr;
	}

	@Override
	public void check() throws SemanticCheckException {
		lExpr.check();
		rExpr.check();
		Type lType = lExpr.getExprType();
		Type rType = rExpr.getExprType();
		setExprType(checkCompatibility(lType, rType));

	}

	private Type checkCompatibility(Type lType, Type rType) {
		//TODO Some sort of tracking of the origins of food based sets
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		if (lType instanceof VarietyType || rType instanceof VarietyType) {
			return new VarietyType("float", floatType);
		}
		return floatType;
		
	}

	@Override
	public String generateXML() {
		String op = "";
		switch (operator) {
		case PLUS     : op = "add"; break; 
		case MINUS    : op = "sub"; break; 
		case MULTIPLY : op = "mul"; break; 
		case DIVIDE   : op = "div"; break; 
		case POWER    : op = "pow"; break; 
		}
		return "\\" + op + "{" + lExpr.generateXML() + "," + rExpr.generateXML() + "}";
	}

}
