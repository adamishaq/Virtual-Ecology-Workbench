package VEW.XMLCompiler.ANTLR;

public class BinOpNode extends ASTree implements ExprNode {
	
	private MathematicalOperator operator;
	private ExprNode leftExpr;
	private ExprNode rightExpr;
	
	public BinOpNode (String operator, ExprNode leftExpr, ExprNode rightExpr) {
		this.operator = MathematicalOperator.valueOf(operator);
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub

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
		return "\\" + op + "{" + leftExpr.generateXML() + "," + rightExpr.generateXML() + "}";
	}

}
