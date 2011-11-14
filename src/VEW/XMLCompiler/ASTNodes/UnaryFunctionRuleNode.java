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
		//May change this into a change node
		//TODO check that the id refers to a stage, and perhaps stage semantic attribute
		//TODO some sort of warning system if multiple non conditional changes appear

	}

	@Override
	public String generateXML() {
		String func = "";
		switch (funcName) {
		case CHANGE : func = "change"; break;
		}
		return "\\" + func + "{" + idArg.generateXML() + "}";
	}
	
	@Override
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
