package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.Type;
import VEW.Planktonica2.ControllerStructure.VarietyType;

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
	public void check() throws SemanticCheckException {
		conditionExpr.check();
		Type condType = conditionExpr.getBExprType();
		if (condType instanceof VarietyType) {
			throw new SemanticCheckException("The condition must evaluate to a boolean");
		}
		thenExpr.check();
		elseExpr.check();

	}

	@Override
	public String generateXML() {
		return "\\conditional{" + conditionExpr.generateXML() + "," + thenExpr.generateXML()
		 + "," + elseExpr.generateXML() + "}";
	}

}
