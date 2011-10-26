package VEW.XMLCompiler.ANTLR;

public class RuleSequenceNode extends ASTree {
	
	private RuleNode rule;
	private RuleSequenceNode seq;

	public RuleSequenceNode(RuleNode rNode, RuleSequenceNode seqNode) {
		rule = rNode;
		seq = seqNode;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		if (seq != null) {
			return "<equation><name>LOLRULE</name><eq>" + rule.generateXML() 
				+ "</eq></equation>" + seq.generateXML();
		} else {
			return "<equation><name>LOLRULE</name><eq>" + rule.generateXML() 
				+ "</eq></equation>";
		}
	}

}
