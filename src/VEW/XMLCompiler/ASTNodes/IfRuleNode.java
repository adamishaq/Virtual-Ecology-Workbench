package VEW.XMLCompiler.ANTLR;

public class IfRuleNode extends ASTree implements RuleNode {
	
	private BExprNode conditionExpr;
	private RuleNode rule;
	
	public IfRuleNode(BExprNode conditionExpr, RuleNode rule) {
		this.conditionExpr = conditionExpr;
		this.rule = rule;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		return "\\ifthen{" + conditionExpr.generateXML() + "," + rule.generateXML() + "}";
	}

	public String generateLatex() {
		return "if\\;(" + conditionExpr.generateLatex() + ")\\;then\\;(" + rule.generateLatex() + ")";
	}
	
}