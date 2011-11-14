package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;

public class VBOpNode extends BExprNode {
	
	private VBoolOperator vop;
	private BExprNode expression;
	
	public VBOpNode(VBoolOperator _vop, BExprNode _expression) {
		this.vop = _vop;
		this.expression = _expression;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		expression.check();
		Type exprType = expression.getBExprType();
		if (!(exprType instanceof Variety)) {
			throw new SemanticCheckException("The expression must be a vector");
		}
		Variety varType = (Variety) exprType;
		Type boolType = (Type) AmbientVariableTables.getTables().checkTypeTable("$boolean");
		if (varType.getElementType() != boolType) {
			throw new SemanticCheckException("The input for VBOp must be a vector of booleans");
		}
		setBExprType(boolType);
		
	}

	@Override
	public String generateXML() {
		String func = "";
		switch (vop) {
		case ALL  : func = "allVariety";
		case NONE : func = "noVariety";
		case SOME : func = "someVariety";
		}
		return "\\" + func + "{" + expression.generateXML() + "}";
	}
}
