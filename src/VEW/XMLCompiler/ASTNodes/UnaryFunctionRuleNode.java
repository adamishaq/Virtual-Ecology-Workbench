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
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		//May change this into a change node
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Special functions cannot be called within chemical equations",
							line_number));
			return;
		}
		FunctionalGroup group = (FunctionalGroup) enclosingCategory;
		Stage stg = group.checkStageTable(idArg.getName());
		if (stg == null) {
			enclosingTree.addSemanticException(
					new SemanticCheckException(idArg.getName() + " is not a known stage",line_number));
		}

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
