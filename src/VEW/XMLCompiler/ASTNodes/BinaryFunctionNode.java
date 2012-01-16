package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.GlobalVariable;
import VEW.Planktonica2.Model.Stage;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;

/**
 * A AST node that represents a binary function, where the first argument is a variable and second
 * argument is an expression.
 * @author David Coulden
 *
 */
public class BinaryFunctionNode extends RuleNode {

	private BinaryFunction binFunc; //The type of binary function
	private IdNode idArg; //The variable argument
	private ExprNode expArg; //The expression argument
	
	public BinaryFunctionNode(BinaryFunction function, IdNode idArg, ExprNode expArg, int line) {
		this.binFunc = function;
		this.idArg = idArg;
		this.expArg = expArg;
		this.line_number = line;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Special functions cannot be called within chemical equations",line_number));
			return;
		}
		FunctionalGroup group = (FunctionalGroup) enclosingCategory;
		expArg.check(enclosingCategory, enclosingTree);
		Type expArgType = expArg.getExprType();
		//Different behaviour based on each type of binary function
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
		case PCHANGE : return "\\pchange{\\stage{" + idArg.getName() + "}," + expArg.generateXML() + "}";
		}
		return "\\" + func + "{" + expArg.generateXML() + ",\\var{" + idArg.getName().replace('_', '$') + "}}";
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

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		super.acceptDependencyCheckVisitor(visitor);
		
		idArg.acceptDependencyCheckVisitor(visitor);
		expArg.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}
	
}
