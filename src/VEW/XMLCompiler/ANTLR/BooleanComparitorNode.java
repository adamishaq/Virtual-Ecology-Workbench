package VEW.XMLCompiler.ANTLR;

public class BooleanComparitorNode extends ASTree implements BExprNode {
	
	private ComparisonOperator comparitor;
	private ExprNode rightExpr;
	private ExprNode leftExpr;
	
	public BooleanComparitorNode (String comparitor, ExprNode rightExpr, ExprNode leftExpr) {
		this.comparitor = ComparisonOperator.valueOf(comparitor);
		this.rightExpr = rightExpr;
		this.leftExpr = leftExpr;
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
