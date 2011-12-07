package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.DisplayOptions;
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
		String name = "Rule";
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
			if (rule != null) {
				String ruleString = "\\\\ \\\\ \\;";
				if (DisplayOptions.PREVIEW_RULE_NAMES && this.ruleName != null) {
					ruleString +=  format_name() + "\\;:\\;";
				}
				ruleString += rule.generateLatex();
				return ruleString + seq.generateLatex();
			}
			return "\\\\ \\\\ \\;???" + seq.generateLatex();
		} else {
			if (rule != null) {
				String ruleString = "\\\\ \\\\ \\;";
				if (DisplayOptions.PREVIEW_RULE_NAMES && this.ruleName != null) {
					ruleString +=  format_name() + "\\;:\\;";
				}
				ruleString += rule.generateLatex();
				return ruleString;
			}
			return "\\\\ \\\\ \\;???";
		}
	}

	private String format_name() {
		// Remove all double qoutes and replace spaces with 'LaTeX spaces'
		String name = "";
		for (int i = 0; i < this.ruleName.length(); i++) {
			if (this.ruleName.charAt(i) == ' ')
				name += "\\:";
			else if (!(this.ruleName.charAt(i) == '\"'))
				name += this.ruleName.charAt(i);
		}
		return name;
	}
	

}
