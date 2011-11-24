package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Catagory;
import VEW.Planktonica2.model.Chemical;
import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.VarietyType;

public class VBOpNode extends BExprNode {
	
	private VBoolOperator vop;
	private BExprNode expression;
	
	public VBOpNode(VBoolOperator _vop, BExprNode _expression) {
		this.vop = _vop;
		this.expression = _expression;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Variety operations cannot be called within chemical equations",
							line_number));
		}
		expression.check(enclosingCategory, enclosingTree);
		Type exprType = expression.getBExprType();
		if (!(exprType instanceof VarietyType)) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The expression must be a vector",line_number));
		}
		VarietyType varType = (VarietyType) exprType;
		Type boolType = (Type) AmbientVariableTables.getTables().checkTypeTable("$boolean");
		if (varType.getElementType() != boolType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The input for VBOp must be a vector of booleans",line_number));
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

}
