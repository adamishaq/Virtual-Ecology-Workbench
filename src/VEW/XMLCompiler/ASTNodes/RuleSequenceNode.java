package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;

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
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		rule.check(enclosingCategory, enclosingTree);
		if (seq != null) {
			seq.check(enclosingCategory, enclosingTree);
		}
	}

	@Override
	public String generateXML() {
		String name = "LOLNAME";
		if (ruleName != null) {
			name = ruleName;
		}
		if (seq != null) {
			return name + ":" + rule.generateXML() 
				+ ";" + seq.generateXML();
		} else {
			return name + ":" + rule.generateXML();
		}
	}
	
	public String generateLatex() {
		if (seq != null) {
			if (rule != null)
				return "\\\\ \\\\ \\;" + rule.generateLatex() 
				+ seq.generateLatex();
			else
				return "\\\\ \\\\ \\;???" 
				+ seq.generateLatex();
			
		} else {
			if (rule != null)
				return "\\\\ \\\\ \\;" + rule.generateLatex();
			else
				return "\\\\ \\\\ \\;???";
		}
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		rule.acceptDependencyCheckVisitor(visitor);
		if(seq != null) {
			seq.acceptDependencyCheckVisitor(visitor);
		}
		visitor.visit(this);
		
	}
	
}
