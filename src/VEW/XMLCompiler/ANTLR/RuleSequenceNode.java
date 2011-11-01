package VEW.XMLCompiler.ANTLR;

public class RuleSequenceNode extends ASTree {
	
	private String ruleName;
	private RuleNode rule;
	private RuleSequenceNode seq;

	public RuleSequenceNode(RuleNode rNode) {
		this.rule = rNode;
		this.seq = null;
		this.ruleName = null;
	}
	
	public RuleSequenceNode(String ruleName, RuleNode rNode) {
		this.ruleName = ruleName;
		this.rule = rNode;
	}
	
	public RuleSequenceNode(RuleNode rNode, RuleSequenceNode seqNode) {
		this.rule = rNode;
		this.seq = seqNode;
		this.ruleName = null;
	}
	
	public RuleSequenceNode(String ruleName, RuleNode rNode, RuleSequenceNode seqNode) {
		this.ruleName = ruleName;
		this.rule = rNode;
		this.seq = seqNode;
	}
	
	public void setRuleSequence(RuleSequenceNode seq) {
		this.seq = seq;
	}
	
	public RuleSequenceNode(RuleNode rNode) {
		rule = rNode;
		seq = null;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		String name = "LOLNAME";
		if (ruleName != null) {
			name = ruleName;
		}
		if (seq != null) {
			return "<equation><name>" + name + "</name><eq>" + rule.generateXML() 
				+ "</eq></equation>" + seq.generateXML();
		} else {
			return "<equation><name>" + name + "</name><eq>" + rule.generateXML() 
				+ "</eq></equation>";
		}
	}
	public String generateLatex() {
		if (seq != null) {
			return rule.generateLatex() 
				+ "\\\\ \\\\ " + seq.generateLatex();
		} else {
			return rule.generateLatex();
		}
	}
	

}
