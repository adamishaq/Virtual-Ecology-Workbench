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
		rule.check();
	}

	@Override
	public String generateXML() {
		return "\\ifthen{" + conditionExpr.generateXML() + "," + rule.generateXML() + "}";
	}

	public String generateLatex() {
		return "if\\;(" + conditionExpr.generateLatex() + ")\\;then\\;(" + rule.generateLatex() + ")";
	}
	
}
