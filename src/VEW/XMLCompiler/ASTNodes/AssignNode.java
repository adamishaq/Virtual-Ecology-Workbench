package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.Model.VarietyType;

public class AssignNode extends RuleNode {

	private IdNode identifier;
	private ExprNode expr;
	private VariableType assignVar;
	
	public AssignNode(IdNode identifier, ExprNode expr, int line) {
		this.line_number = line;
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
		} /*else if ((var instanceof VarietyLocal || var instanceof Local) && var.isAssignedTo()) {
			enclosingTree.addSemanticException(
				new SemanticCheckException(idName + " has already been assigned to in a previous rule",line_number));
		}*/ else {
			expr.check(enclosingCategory, enclosingTree);
			checkTypeCompatibility(var.getVarType(), enclosingTree);
			assignVar = var;
			assignVar.setAssigned(true);
		}
		identifier.set_units(enclosingCategory);
		if (!UnitChecker.getUnitChecker().CheckUnitCompatability(identifier.getUnits(),
				expr.getUnits())) {
			enclosingTree.addWarning("Units of " + identifier.getName() + " not compatible with units of " +
					"the expression on line " + line_number);
		}
	}
	
	private void checkTypeCompatibility(Type varType, ConstructedASTree enclosingTree) {
		Type exprType = expr.getExprType();
		if (exprType instanceof VarietyType) {
			if (varType instanceof VarietyType) {
				VarietyType vExprType = (VarietyType) exprType;
				VarietyType vVarType = (VarietyType) varType;
				if (!vVarType.checkLinkCompatible(vExprType)) {
					enclosingTree.addSemanticException(
							new SemanticCheckException("Cannot assign as the two varieties have different links", line_number));
				}
				return;
			}
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

	
	/**
	 * Doesnt' check the IdNode identifier.
	 * This can be done by the visitor in the visitor class.
	 */
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		super.acceptDependencyCheckVisitor(visitor);
		
		expr.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}
}
