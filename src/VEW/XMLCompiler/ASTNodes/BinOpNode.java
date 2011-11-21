package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.VarietyType;



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

	public String generateLatex() {
		String func = "???";
		String left = "???";
		if (lExpr != null)
			left = lExpr.generateLatex();
		String right = "???";
		if (rExpr != null)
			right = rExpr.generateLatex();
		switch (operator) {
		case PLUS     : func  = "+"; break;
		case MINUS    : func  = "-"; break;
		case MULTIPLY : func  = "*"; break;
		case DIVIDE   : return "\\frac {" + left + "} {" + right + "}";
		case POWER    : return left + "^ {" + right + "}";
		}
		return left + func + right;
	}

}
