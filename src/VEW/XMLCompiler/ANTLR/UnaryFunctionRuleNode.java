package VEW.XMLCompiler.ANTLR;

public class UnaryFunctionRuleNode extends ASTree implements RuleNode {

	private UnaryRuleFunction funcName;
	private IdNode idArg;
	
	public UnaryFunctionRuleNode (UnaryRuleFunction funcName, IdNode idArg) {
		this.funcName = funcName;
		this.idArg = idArg;
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
