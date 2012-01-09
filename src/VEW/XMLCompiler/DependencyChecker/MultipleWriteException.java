package VEW.XMLCompiler.DependencyChecker;

import VEW.Planktonica2.Model.VariableType;
import VEW.XMLCompiler.ASTNodes.RuleNode;

/**
 * More than one write has occured in an ASTree
 * 
 * @author Chris Bates
 *
 */
public class MultipleWriteException extends Exception {

	private static final long serialVersionUID = 2492870850363362822L;
	
	private DependantMetaData<RuleNode> rule1;
	private DependantMetaData<RuleNode> rule2;
	private VariableType affectedVar;
	
	public MultipleWriteException(DependantMetaData<RuleNode> firstWriteRule, DependantMetaData<RuleNode> secondWriteRule, VariableType affectedVar, String message) {
		super(message);
		this.rule1 = firstWriteRule;
		this.rule2 = secondWriteRule;
		this.affectedVar = affectedVar;
	}
	
	public DependantMetaData<RuleNode> getFirstWriteRuleData() {
		return this.rule1;
	}
	
	public DependantMetaData<RuleNode> getSecondWriteRuleData() {
		return this.rule2;
	}
	
	public RuleNode getFirstWriteRule() {
		return this.rule1.getNode();
	}
	
	public RuleNode getSecondWriteRule() {
		return this.rule2.getNode();
	}

	public VariableType getAffectedVariable () {
		return this.affectedVar;
	}

}
