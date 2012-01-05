package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.VarietyType;

public class VOpNode  extends ExprNode {

	private VOperator vop;
	private ExprNode expression;
	
	public VOpNode(VOperator _vop, ExprNode _expression, int line) {
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
		Type exprType = expression.getExprType();
		if (!(exprType instanceof VarietyType)) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The expression must be a vector",line_number));
		}
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		VarietyType varType = (VarietyType) exprType;
		if (!varType.getElementType().equals(floatType)) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The input for variety operation must be a vector of floats",line_number));
		}
		setExprType(floatType);
		units = expression.getUnits();
	}

	@Override
	public String generateXML() {
		String func = "";
		switch (vop) {
		case AVERAGE : func = "varietyavg"; break;
		case PRODUCT : func = "varietymul"; break;
		case SUM 	 : func = "varietysum"; break;
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
		case AVERAGE : func = " average "; break;
		case PRODUCT : func = " \\prod "; break;
		case SUM 	 : func = " \\sum "; break;
		}
		return func + "(" + exp + ")";
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		expression.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

}
