package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Catagory;
import VEW.Planktonica2.model.Chemical;
import VEW.Planktonica2.model.FunctionalGroup;
import VEW.Planktonica2.model.Stage;
import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.VarietyType;


public class BinaryFunctionNode extends RuleNode {

	private BinaryFunction binFunc;
	private IdNode idArg;
	private ExprNode expArg;
	
	public BinaryFunctionNode(BinaryFunction function, IdNode idArg, ExprNode expArg) {
		this.binFunc = function;
		this.idArg = idArg;
		this.expArg = expArg;
	}
	
	@Override
	public void check() {
		//Considering splitting this into three nodes
		Catagory cata = getCatagory();
		if (cata instanceof Chemical) {
			CommonTreeWalker.add_exception(
				new SemanticCheckException("Special functions cannot be called within chemical equations",line_number));
		} else {
			FunctionalGroup group = (FunctionalGroup) cata;
			expArg.check();
			Type expArgType = expArg.getExprType();
			switch (binFunc) {
				case UPTAKE : 
				case RELEASE : {
					//TODO Check id is a chemical
					break;
				}
				case PCHANGE : {
					Stage st = group.checkStageTable(idArg.getName());
					if (st == null) {
						CommonTreeWalker.add_exception(
								new SemanticCheckException(idArg.getName() + " is not a stage",line_number));
					}
				}
			}
			if (expArgType instanceof VarietyType) {
				CommonTreeWalker.add_exception(
						new SemanticCheckException("The expression must evaluate to a scalar value",line_number));
			}
		}
		
	}

	@Override
	public String generateXML() {
		String func = "";
		switch (binFunc) {
		case UPTAKE  : func = "uptake"; break;
		case RELEASE : func = "release"; break;
		case PCHANGE : return "\\pchange{" + idArg.generateXML() + "," + expArg.generateXML() + "}";
		}
		return "\\" + func + "{" + expArg.generateXML() + "," + idArg.generateXML() + "}";
	}

	@Override
	public String generateLatex() {
		String func = "???";
		String id = "???";
		if (idArg != null)
			id = idArg.generateLatex();
		String exp = "???";
		if (expArg != null)
			exp = expArg.generateLatex();
		switch (binFunc) {
		case UPTAKE  : func = "uptake"; break;
		case RELEASE : func = "release"; break;
		case PCHANGE : return "pchange(" + id + "," + exp + ")";
		}
		return func + "(" + id + "," + exp + ")";
	}
	
}
