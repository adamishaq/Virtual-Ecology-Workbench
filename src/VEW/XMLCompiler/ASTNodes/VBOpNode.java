package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;

/**
 * An AST node that represents a boolean variety expression
 * @author David Coulden
 *
 */
public class VBOpNode extends BExprNode {
	
	private VBoolOperator vop; //The type of boolean variety operator
	private BExprNode expression; //The boolean variety expression being operated on
	
	public VBOpNode(VBoolOperator _vop, BExprNode _expression, int line) {
		this.vop = _vop;
		this.expression = _expression;
		this.line_number = line;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Variety operations cannot be called within chemical equations",
							line_number));
			return;
		}
		expression.check(enclosingCategory, enclosingTree);
		Type exprType = expression.getBExprType();
		if (!(exprType instanceof VarietyType)) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The boolean expression must evaluate to a variety",line_number));
			return;
		}
		VarietyType varType = (VarietyType) exprType;
		Type boolType = (Type) AmbientVariableTables.getTables().checkTypeTable("$boolean");
		if (varType.getElementType() != boolType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The input for variety boolean operations must be a boolean variety",line_number));
		}
		setBExprType(boolType);
	}

	@Override
	public String generateXML() {
		String func = "";
		switch (vop) {
		case ALL  : func = "allVariety"; break;
		case NONE : func = "noVariety"; break;
		case SOME : func = "someVariety"; break;
		}
		return "\\" + func + "{" + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String func = "???";
		String exp = "???";
		if (expression != null)
			exp = expression.generateLatex();
		switch (vop) {
		case ALL  : func = " all "; break;
		case NONE : func = " no "; break;
		case SOME : func = " some "; break;
		}
		return func + "(" + exp + ")";
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		expression.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

}
