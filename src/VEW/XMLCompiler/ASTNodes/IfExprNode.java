package VEW.XMLCompiler.ANTLR;

public class IfExprNode extends ASTree implements ExprNode {

	private BExprNode conditionExpr;
	private ExprNode thenExpr;
	private ExprNode elseExpr;
	
	public IfExprNode(BExprNode conditionExpr, ExprNode thenExpr, ExprNode elseExpr) {
		this.conditionExpr = conditionExpr;
		this.thenExpr = thenExpr;
		this.elseExpr = elseExpr;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		return "\\conditional{" + conditionExpr.generateXML() + "," + thenExpr.generateXML()
		 + "," + elseExpr.generateXML() + "}";
	}
	
	public String generateLatex() {
		return "if\\;(" + conditionExpr.generateLatex() + ")\\;then\\;(" + thenExpr.generateLatex()
		 + ")\\;else\\;(" + elseExpr.generateLatex() + ")";
	}

}
