package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Catagory;
import VEW.Planktonica2.model.Chemical;
import VEW.Planktonica2.model.FunctionalGroup;
import VEW.Planktonica2.model.Stage;
import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.VarietyType;



public class CreateNode extends RuleNode {

	private IdNode identifier;
	private ExprNode expression;
	private AssignListNode assignList;

	public CreateNode (IdNode identifier, ExprNode expression) {
		this.identifier = identifier;
		this.expression = expression;
		this.assignList = null;
	}
	
	public CreateNode (IdNode identifier, ExprNode expression, AssignListNode assignList) {
		this.identifier = identifier;
		this.expression = expression;
		this.assignList = assignList;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		Catagory cata = getCatagory();
		if (cata instanceof Chemical) {
			throw new SemanticCheckException("Special functions cannot be called within chemical equations");
		}
		FunctionalGroup group = (FunctionalGroup) cata;
		Stage stage = group.checkStageTable(identifier.getName());
		if (stage == null) {
			throw new SemanticCheckException(identifier.getName() + " is not a valid stage");
		}
		expression.check();
		Type numExprType = expression.getExprType();
		if (numExprType instanceof VarietyType) {
			throw new SemanticCheckException("The expression for number of offspring must evaluate to a scalar");
		}
		//TODO assign list checking may need to be more complex, not sure yet
		assignList.check();

	}

	@Override
	public String generateXML() {
		if (assignList != null) {
			return "\\create{" + identifier.generateXML() + "," 
			 + expression.generateXML() + "," + assignList.generateXML() + "}";
		} else {
			return "\\create{" + identifier.generateXML() + "," 
			 + expression.generateXML() + "}";
		}
	}
	
	@Override
	public String generateLatex() {
		String id = "???";
		if (identifier != null)
			id = identifier.generateLatex();
		String exp = "???";
		if (expression != null)
			exp = expression.generateLatex();
		if (assignList != null) {
			return "create(" + id + "," 
			 + exp + ")\\;with\\;[" + assignList.generateLatex() + "]";
		} else {
			return "create(" + id + "," 
			 + exp + ")";
		}
	}

}
