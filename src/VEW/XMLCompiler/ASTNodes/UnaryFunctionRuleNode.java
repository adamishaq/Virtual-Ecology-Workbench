package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.*;

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
		Catagory cata = getCatagory();
		if (cata instanceof Chemical) {
			throw new SemanticCheckException("Special functions cannot be called within chemical equations");
		}
		FunctionalGroup group = (FunctionalGroup) cata;
		Stage stg = group.checkStageTable(idArg.getName());
		if (stg == null) {
			throw new SemanticCheckException(idArg.getName() + " is not a known stage");
		}
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

}
