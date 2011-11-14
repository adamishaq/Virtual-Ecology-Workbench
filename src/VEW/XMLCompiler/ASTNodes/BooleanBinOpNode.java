package VEW.XMLCompiler.ASTNodes;

public class BooleanBinOpNode extends BExprNode {

	private BooleanBinOperator booleanOp;
	private BExprNode rBExpr;
	private BExprNode lBExpr;
	
	public BooleanBinOpNode(BooleanBinOperator booleanOp, BExprNode leftBExpr, BExprNode rightBExpr) {
		this.booleanOp = booleanOp;
		this.rBExpr = rightBExpr;
		this.lBExpr = leftBExpr;
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
