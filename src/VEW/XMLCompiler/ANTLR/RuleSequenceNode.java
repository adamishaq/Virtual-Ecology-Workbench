package VEW.XMLCompiler.ANTLR;

public class RuleSequenceNode extends ASTree {
	
	private String ruleName;
	private RuleNode rule;
	private RuleSequenceNode seq;

	public RuleSequenceNode(RuleNode rNode) {
		this.rule = rNode;
		this.seq = null;
	}
	
	public RuleSequenceNode(String ruleName, RuleNode rNode) {
		this.ruleName = ruleName;
		this.rule = rNode;
	}
	
	public RuleSequenceNode(RuleNode rNode, RuleSequenceNode seqNode) {
		this.rule = rNode;
		this.seq = seqNode;
	}
	
	public RuleSequenceNode(String ruleName, RuleNode rNode, RuleSequenceNode seqNode) {
		this.ruleName = ruleName;
		this.rule = rNode;
		this.seq = seqNode;
	}
	
	public void setRuleSequence(RuleSequenceNode seq) {
		this.seq = seq;
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
