package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;

/**
 * An AST node representing an negation expression
 * @author David Coulden
 *
 */
public class NegNode extends ExprNode {

	private ExprNode negExpr; //Expression being negated
	
	public NegNode(ExprNode negExpr, int line) {
		this.negExpr = negExpr;
		this.line_number = line;
	}
	
	@Override
	public void check(Catagory enclosingCategory,
			ConstructedASTree enclosingTree) {
		negExpr.check(enclosingCategory, enclosingTree);
		setExprType(checkCompatibility(negExpr.getExprType()));
		this.units = negExpr.getUnits();
	}

	private Type checkCompatibility(Type argType) {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		if (argType instanceof VarietyType) {
			return new VarietyType("float", floatType);
		}
		return floatType;
	}

	@Override
	public String generateXML() {
		return "\\minus{" + negExpr.generateXML() + "}";
	}

	@Override
	public String generateLatex() {
		return "-(" + negExpr.generateLatex() + ")";
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		negExpr.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

}
