package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Local;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.Model.VarietyLocal;
import VEW.Planktonica2.Model.VarietyType;

public class AssignNode extends RuleNode {

	private IdNode identifier;
	private ExprNode expr;
	private VariableType assignVar;
	
	public AssignNode(IdNode identifier, ExprNode expr) {
		this.identifier = identifier;
		this.expr = expr;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		String idName = identifier.getName();
		VariableType var = enclosingCategory.checkAssignableVariableTables(identifier.getName());
		if (var == null) {
			enclosingTree.addSemanticException(
				new SemanticCheckException(idName + " is not an assignable variable",line_number));
		} else if ((var instanceof VarietyLocal || var instanceof Local) && var.isAssignedTo()) {
			enclosingTree.addSemanticException(
				new SemanticCheckException(idName + " has already been assigned to in a previous rule",line_number));
		} else {
			expr.check(enclosingCategory, enclosingTree);
			checkTypeCompatibility(var.getVarType(), enclosingTree);
			assignVar = var;
			assignVar.setAssigned(true);
		}
	}
	
	private void checkTypeCompatibility(Type varType, ConstructedASTree enclosingTree) {
		Type exprType = expr.getExprType();
		if (exprType instanceof VarietyType && !(varType instanceof VarietyType)) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Cannot assign a variety value to a scalar value",line_number));
		}
	}

	@Override
	public String generateXML() {
		return "\\assign{" + identifier.generateXML() + "," + expr.generateXML() + "}";
	}

	@Override
	public String generateLatex() {
		String id = "???";
		if (identifier != null)
			id = identifier.generateLatex();
		String ex = "???";
		if (expr != null)
			ex = expr.generateLatex();
		return id + " = " + ex;
	}
	
	public VariableType getAssignVar() {
		return assignVar;
	}
}
