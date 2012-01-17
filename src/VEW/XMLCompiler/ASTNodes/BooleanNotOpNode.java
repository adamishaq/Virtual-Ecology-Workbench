package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;

/**
 * An AST node representing a boolean not expression
 * @author David Coulden
 *
 */
public class BooleanNotOpNode extends BExprNode {

	private BExprNode expression; //The boolean expression being negated

	public BooleanNotOpNode (BExprNode expression) {
		this.expression = expression;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		expression.check(enclosingCategory, enclosingTree);
		setBExprType(expression.getBExprType());
	}

	@Override
	public String generateXML() {
		return "\\not{" + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		if (expression != null)
			return " \\sim " + expression.generateLatex();
		else
			return " \\sim ???";
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		expression.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

	
}
