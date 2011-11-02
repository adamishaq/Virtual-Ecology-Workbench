package VEW.XMLCompiler.ASTNodes;

public class BooleanBinOpNode extends BExprNode {

	private BooleanBinOperator booleanOp;
	private BExprNode rightBExpr;
	private BExprNode leftBExpr;
	
	public BooleanBinOpNode(BooleanBinOperator booleanOp, BExprNode leftBExpr, BExprNode rightBExpr) {
		this.booleanOp = booleanOp;
		this.rightBExpr = rightBExpr;
		this.leftBExpr = leftBExpr;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		String op = "";
		switch (booleanOp) {
		case AND : op = "and"; break; 
		case OR  : op = "or"; break;
		}
		return "\\" + op + "{" + leftBExpr.generateXML() + "," + rightBExpr.generateXML() + "}";
	}
	
	public String generateLatex() {
		String op = "";
		switch (booleanOp) {
		case AND : op = " \\cap "; break; 
		case OR  : op = " \\cup "; break;
		}
		return leftBExpr.generateLatex() + "\\;" + op + "\\;" + rightBExpr.generateLatex();
	}

}
