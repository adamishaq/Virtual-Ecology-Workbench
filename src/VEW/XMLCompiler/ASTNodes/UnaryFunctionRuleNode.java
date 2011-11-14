package VEW.XMLCompiler.ASTNodes;

public class UnaryFunctionRuleNode extends RuleNode {

	private UnaryRuleFunction funcName;
	private IdNode idArg;
	
	public UnaryFunctionRuleNode (UnaryRuleFunction funcName, IdNode idArg) {
		this.funcName = funcName;
		this.idArg = idArg;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		String func = "";
		switch (funcName) {
		case CHANGE : func = "change"; break;
		}
		return "\\" + func + "{" + idArg.generateXML() + "}";
	}
	
	public String generateLatex() {
		String func = "???";
		switch (funcName) {
		case CHANGE : func = " change "; break;
		}
		if (idArg != null)
			return func + "(" + idArg.generateLatex() + ")";
		return func + "(???)";
	}

}
