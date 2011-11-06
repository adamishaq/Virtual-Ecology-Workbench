package VEW.XMLCompiler.ASTNodes;

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
		if (condType instanceof Variety) {
			throw new SemanticCheckException("The condition must evaluate to a boolean");
		}
		rule.check();
	}

	@Override
	public String generateXML() {
		return "\\ifthen{" + conditionExpr.generateXML() + "," + rule.generateXML() + "}";
	}

}
