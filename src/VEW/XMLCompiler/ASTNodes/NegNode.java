package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;

public class NegNode extends ExprNode {

	private ExprNode negExpr;
	
	public NegNode(ExprNode negExpr) {
		this.negExpr = negExpr;
	}
	
	@Override
	public void check(Catagory enclosingCategory,
			ConstructedASTree enclosingTree) {
		negExpr.check(enclosingCategory, enclosingTree);
		setExprType(checkCompatibility(negExpr.getExprType()));
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

}
