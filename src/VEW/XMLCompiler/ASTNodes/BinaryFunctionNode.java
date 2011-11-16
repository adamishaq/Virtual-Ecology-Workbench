package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.*;

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
	public void check() throws SemanticCheckException {
		//Considering splitting this into three nodes
		Catagory cata = getCatagory();
		if (cata instanceof Chemical) {
			throw new SemanticCheckException("Special functions cannot be called within chemical equations");
		}
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
					throw new SemanticCheckException(idArg.getName() + " is not a stage");
				}
			}
		}
		if (expArgType instanceof VarietyType) {
			throw new SemanticCheckException("The expression must evaluate to a scalar value");
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
		return func + "(" + exp + "," + id + ")";
	}
	
}
