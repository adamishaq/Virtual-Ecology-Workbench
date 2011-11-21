package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.VarietyType;


public class IfRuleNode extends RuleNode {
	
	private BExprNode conditionExpr;
	private RuleNode rule;
	
	public IfRuleNode(BExprNode conditionExpr, RuleNode rule) {
		this.conditionExpr = conditionExpr;
		this.rule = rule;
	}
	
	@Override
	public void check() {
		conditionExpr.check();
		Type condType = conditionExpr.getBExprType();
		if (condType instanceof VarietyType) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("The condition must evaluate to a boolean",line_number));
		}
		rule.setInsideConditional(true);
		rule.check();
	}

	@Override
	public String generateXML() {
		return "\\ifthen{" + conditionExpr.generateXML() + "," + rule.generateXML() + "}";
	}

	@Override
	public String generateLatex() {
		String condition = "???";
		if (conditionExpr != null)
			condition = conditionExpr.generateLatex();
		String result = "???";
		if (rule != null)
			result = rule.generateLatex();
		return "if\\;(" + condition + ")\\;then\\;(" + result + ")";
	}
	
}
