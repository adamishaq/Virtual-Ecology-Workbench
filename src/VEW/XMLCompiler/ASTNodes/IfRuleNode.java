package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.Type;
import VEW.Planktonica2.ControllerStructure.VarietyType;

public class IfRuleNode extends RuleNode {
	
	private BExprNode conditionExpr;
	private RuleNode rule;
	
	public IfRuleNode(BExprNode conditionExpr, RuleNode rule) {
		this.conditionExpr = conditionExpr;
		this.rule = rule;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		conditionExpr.check();
		Type condType = conditionExpr.getBExprType();
		if (condType instanceof VarietyType) {
			throw new SemanticCheckException("The condition must evaluate to a boolean");
		}
		rule.setInsideConditional(true);
		rule.check();
	}

	@Override
	public String generateXML() {
		return "\\ifthen{" + conditionExpr.generateXML() + "," + rule.generateXML() + "}";
	}

}
