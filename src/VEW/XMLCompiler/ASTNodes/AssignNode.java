package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.DisplayOptions;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.Model.VarietyType;

/**
 * The AST node that represents an assignment
 * @author David Coulden
 *
 */
public class AssignNode extends RuleNode {

	private IdNode identifier; //Represents the variable being assigned to
	private ExprNode expr; //The expression being assigned
	private VariableType assignVar; //The variable representation of the assign variable
	
	public AssignNode(IdNode identifier, ExprNode expr, int line) {
		this.line_number = line;
		this.identifier = identifier;
		this.expr = expr;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		if (isWithinConditional()) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Cannot assign to a variable within a conditional",line_number));
		}
		String idName = identifier.getName();
		VariableType var = enclosingCategory.checkAssignableVariableTables(identifier.getName());
		if (var == null) {
			enclosingTree.addSemanticException(
				new SemanticCheckException(idName + " is not an assignable variable",line_number));
			return;
		}
		identifier.set_units(enclosingCategory);
		//Checks child expression
		expr.check(enclosingCategory, enclosingTree);
		//Checks whether units 
		if (UnitChecker.getUnitChecker().CheckUnitCompatability(identifier.getUnits(),
				expr.getUnits()) == 0) {
			String warning = "Units of " + identifier.getName() + " (";
			for (Unit u : identifier.getUnits())
				warning += u.format();
			warning += ") not compatible with units of " +
			"the expression on line " + line_number + " (";
			for (Unit u : expr.getUnits())
				warning += u.format();
			warning += ")";
			enclosingTree.addWarning(warning);
		}
		//Checks that the assignment is correctly typed
		checkTypeCompatibility(var.getVarType(), enclosingTree);
		assignVar = var;
		assignVar.setAssigned(true);
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
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING) {
			float scale = UnitChecker.getUnitChecker().CheckUnitCompatability(identifier.getUnits(),
					expr.getUnits());
			if (scale != 0 && scale != 1)
				return "\\assign{" + identifier.generateXML() + "," +
						"\\mul{" + scale + "," + expr.generateXML() + "}}";
		}
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
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING) {
			float scale = UnitChecker.getUnitChecker().CheckUnitCompatability(identifier.getUnits(),
					expr.getUnits());
			if (scale != 0 && scale != 1)
				return id + " = " + scale + " * (" + ex + ")";
		}
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
