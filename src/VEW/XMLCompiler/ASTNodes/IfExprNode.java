package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;


public class IfExprNode extends ExprNode {

	private BExprNode conditionExpr;
	private ExprNode thenExpr;
	private ExprNode elseExpr;
	
	public IfExprNode(BExprNode conditionExpr, ExprNode thenExpr, ExprNode elseExpr) {
		this.conditionExpr = conditionExpr;
		this.thenExpr = thenExpr;
		this.elseExpr = elseExpr;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		conditionExpr.check(enclosingCategory, enclosingTree);
		Type condType = conditionExpr.getBExprType();
		if (condType instanceof VarietyType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The condition must evaluate to a boolean",line_number));
		}
		thenExpr.check(enclosingCategory, enclosingTree);
		elseExpr.check(enclosingCategory, enclosingTree);

	}

	@Override
	public String generateXML() {
		return "\\conditional{" + conditionExpr.generateXML() + "," + thenExpr.generateXML()
		 + "," + elseExpr.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String cond = "???";
		if (conditionExpr != null)
			cond = conditionExpr.generateLatex();
		String then = "???";
		if (thenExpr != null)
			then = thenExpr.generateLatex();
		String elseexp = "???";
		if (elseExpr != null)
			elseexp = elseExpr.generateLatex();
		return "if\\;(" + cond + ")\\;then\\;(" + then
		 + ")\\;else\\;(" + elseexp + ")";
	}

}
