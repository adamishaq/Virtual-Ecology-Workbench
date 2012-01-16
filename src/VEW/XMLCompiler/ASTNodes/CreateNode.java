package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Stage;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;


/**
 * An AST node that represents a create statement
 * @author David Coulden
 *
 */
public class CreateNode extends RuleNode {

	private IdNode identifier; //The create stage identifier
	private ExprNode expression; //The create expression
	private AssignListNode assignList; //The create assign list
	private Stage creationStage; //The representation of the create stage

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
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Create cannot be called within chemical equations",
							line_number));
			return;
		}
		FunctionalGroup group = (FunctionalGroup) enclosingCategory;
		Stage stage = group.checkStageTable(identifier.getName());
		if (stage == null) {
			enclosingTree.addSemanticException(
					new SemanticCheckException(identifier.getName() + " is not a valid stage",line_number));
		}
		creationStage = stage;
		expression.check(enclosingCategory, enclosingTree);
		Type numExprType = expression.getExprType();
		if (numExprType instanceof VarietyType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The expression for number of offspring must evaluate to a scalar",
							line_number));
		}
		
		if (assignList != null) {
			assignList.check(enclosingCategory, enclosingTree);
		}

	}

	@Override
	public String generateXML() {
		if (assignList != null) {
			return "\\create{\\stage{" + creationStage.getName() + "}," 
			 + expression.generateXML() + "," + assignList.generateXML() + "}";
		} else {
			return "\\create{\\stage{" + creationStage.getName() + "}," 
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

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		super.acceptDependencyCheckVisitor(visitor);
		
		
		expression.acceptDependencyCheckVisitor(visitor);
		assignList.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

}
