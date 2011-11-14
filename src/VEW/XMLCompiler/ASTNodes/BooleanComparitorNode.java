package VEW.XMLCompiler.ASTNodes;

public class BooleanComparitorNode extends BExprNode {
	
	private ComparisonOperator comparitor;
	private ExprNode rExpr;
	private ExprNode lExpr;
	
	public BooleanComparitorNode (ComparisonOperator comparitor, ExprNode leftExpr, ExprNode rightExpr) {
		this.comparitor = comparitor;
		this.rExpr = rightExpr;
		this.lExpr = leftExpr;
	}

	@Override
	public void check() throws SemanticCheckException {
		// TODO Auto-generated method stub

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
	
	public String generateLatex() {
		String op = "???";
		String left = "???";
		if (lExpr != null)
			left = lExpr.generateLatex();
		String right = "???";
		if (rExpr != null)
			right = rExpr.generateLatex();
		switch (comparitor) {
		case EQUALS        : op = " = "; break; 
		case NEQUALS       : op = " \\neq" ; break; 
		case GREATERTHAN   : op = " > "; break; 
		case LESSTHAN      : op = " < "; break; 
		case GREATEREQUALS : op = " \\geq "; break;
		case LESSEQUALS    : op = " \\leq "; break; 
		}
		return left + op + right;
	}

}
