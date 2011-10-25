package VEW.XMLCompiler.ANTLR;

public class BinOpNode extends ASTree implements ExprNode {
	
	private String operator;
	private ExprNode leftExpr;
	private ExprNode rightExpr;
	
	public BinOpNode (String operator, ExprNode leftExpr, ExprNode rightExpr) {
		this.operator = operator;
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
