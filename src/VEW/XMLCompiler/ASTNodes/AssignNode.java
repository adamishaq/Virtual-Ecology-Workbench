package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Local;
import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.VariableType;
import VEW.Planktonica2.model.VarietyLocal;
import VEW.Planktonica2.model.VarietyType;

public class AssignNode extends RuleNode {

	private IdNode identifier;
	private ExprNode expr;
	private VariableType assignVar;
	
	public AssignNode(IdNode identifier, ExprNode expr) {
		this.identifier = identifier;
		this.expr = expr;
	}
	
	@Override
	public void check() {
		String idName = identifier.getName();
		VariableType var = getCatagory().checkAssignableVariableTables(identifier.getName());
		if (var == null) {
			CommonTreeWalker.add_exception(
				new SemanticCheckException(idName + " is not an assignable variable",line_number));
		} else if ((var instanceof VarietyLocal || var instanceof Local) && var.isAssignedTo()) {
			CommonTreeWalker.add_exception(
				new SemanticCheckException(idName + " has already been assigned to in a previous rule",line_number));
		} else {
			expr.check();
			checkTypeCompatibility(var.getVarType());
			assignVar = var;
			assignVar.setAssigned(true);
		}
	}
	
	private void checkTypeCompatibility(Type varType) {
		Type exprType = expr.getExprType();
		if (varType instanceof VarietyType && !(exprType instanceof VarietyType)) {
			CommonTreeWalker.add_exception(
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
