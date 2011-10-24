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
		// TODO Auto-generated method stub
		return null;
	}

}
