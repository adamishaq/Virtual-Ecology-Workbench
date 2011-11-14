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
		String condition = "???";
		if (conditionExpr != null)
			condition = conditionExpr.generateLatex();
		String result = "???";
		if (rule != null)
			result = rule.generateLatex();
		return "if\\;(" + condition + ")\\;then\\;(" + result + ")";
	}
	
}
