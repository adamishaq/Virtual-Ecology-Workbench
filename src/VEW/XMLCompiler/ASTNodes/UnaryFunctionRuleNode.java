package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Stage;

public class UnaryFunctionRuleNode extends RuleNode {

	private UnaryRuleFunction funcName;
	private IdNode idArg;
	
	public UnaryFunctionRuleNode (UnaryRuleFunction funcName, IdNode idArg) {
		this.funcName = funcName;
		this.idArg = idArg;
	}
	
	@Override
	public void check() {
		//May change this into a change node
		Catagory cata = getCatagory();
		if (cata instanceof Chemical) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("Special functions cannot be called within chemical equations",
							line_number));
		}
		FunctionalGroup group = (FunctionalGroup) cata;
		Stage stg = group.checkStageTable(idArg.getName());
		if (stg == null) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException(idArg.getName() + " is not a known stage",line_number));
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
