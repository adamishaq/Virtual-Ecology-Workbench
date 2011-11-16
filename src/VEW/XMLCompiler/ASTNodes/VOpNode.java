package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.Chemical;
import VEW.Planktonica2.ControllerStructure.Type;
import VEW.Planktonica2.ControllerStructure.VarietyType;

public class VOpNode  extends ExprNode {

	private VOperator vop;
	private ExprNode expression;
	
	public VOpNode(VOperator _vop, ExprNode _expression) {
		this.vop = _vop;
		this.expression = _expression;
	}
	
	@Override
	public void check() {
		if (getCatagory() instanceof Chemical) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("Variety operations cannot be called within chemical equations",
							line_number));
		}
		expression.check();
		Type exprType = expression.getExprType();
		if (!(exprType instanceof VarietyType)) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("The expression must be a vector",line_number));
		}
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		VarietyType varType = (VarietyType) exprType;
		if (varType.getElementType() == floatType) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("The input for VBOp must be a vector of booleans",line_number));
		}
		setExprType(floatType);
	}

	@Override
	public String generateXML() {
		// TODO - find out what avg codegens into
		String func = "";
		switch (vop) {
		case AVERAGE : func = "???"; break;
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

}
