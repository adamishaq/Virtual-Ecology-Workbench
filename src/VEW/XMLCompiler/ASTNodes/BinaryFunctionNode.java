package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.GlobalVariable;
import VEW.Planktonica2.Model.Stage;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;


public class BinaryFunctionNode extends RuleNode {

	private BinaryFunction binFunc;
	private IdNode idArg;
	private ExprNode expArg;
	
	public BinaryFunctionNode(BinaryFunction function, IdNode idArg, ExprNode expArg, int line) {
		this.binFunc = function;
		this.idArg = idArg;
		this.expArg = expArg;
		this.line_number = line;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		//Considering splitting this into three nodes
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Special functions cannot be called within chemical equations",line_number));
			return;
		}
		FunctionalGroup group = (FunctionalGroup) enclosingCategory;
		expArg.check(enclosingCategory, enclosingTree);
		Type expArgType = expArg.getExprType();
		switch (binFunc) {
			case UPTAKE : 
			case RELEASE : {
				AmbientVariableTables tables = AmbientVariableTables.getTables();
				GlobalVariable chem = tables.checkChemicalTable(idArg.getName());
				if (chem == null) {
					enclosingTree.addSemanticException(
							new SemanticCheckException(idArg.getName() + " is not a chemical concentration",line_number));
				}
				break;
			}
			case PCHANGE : {
				Stage st = group.checkStageTable(idArg.getName());
				if (st == null) {
					enclosingTree.addSemanticException(
							new SemanticCheckException(idArg.getName() + " is not a stage",line_number));
				}
			}
		}
		if (expArgType instanceof VarietyType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The expression must evaluate to a scalar value",line_number));
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
