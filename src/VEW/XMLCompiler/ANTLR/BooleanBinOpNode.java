package VEW.XMLCompiler.ANTLR;

public class BooleanBinOpNode extends ASTree implements BExprNode {

	private BooleanBinOperator booleanOp;
	private BExprNode rightBExpr;
	private BExprNode leftBExpr;
	
	public BooleanBinOpNode(String booleanOp, BExprNode rightBExpr, BExprNode leftBExpr) {
		this.booleanOp = BooleanBinOperator.valueOf(booleanOp);
		this.rightBExpr = rightBExpr;
		this.leftBExpr = leftBExpr;
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
