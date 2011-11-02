package VEW.XMLCompiler.ASTNodes;

public class BooleanComparitorNode extends BExprNode {
	
	private ComparisonOperator comparitor;
	private ExprNode rightExpr;
	private ExprNode leftExpr;
	
	public BooleanComparitorNode (ComparisonOperator comparitor, ExprNode leftExpr, ExprNode rightExpr) {
		this.comparitor = comparitor;
		this.rightExpr = rightExpr;
		this.leftExpr = leftExpr;
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
		return "\\" + op + "{" + leftExpr.generateXML() + "," + rightExpr.generateXML() + "}";
	}
	
	public String generateLatex() {
		String op = "";
		switch (comparitor) {
		case EQUALS        : op = " = "; break; 
		case NEQUALS       : op = " \\neq" ; break; 
		case GREATERTHAN   : op = " > "; break; 
		case LESSTHAN      : op = " < "; break; 
		case GREATEREQUALS : op = " \\geq "; break;
		case LESSEQUALS    : op = " \\leq "; break; 
		}
		return leftExpr.generateLatex() + op + rightExpr.generateLatex();
	}

}
