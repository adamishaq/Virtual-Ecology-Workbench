package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;

public class VOpNode  extends ExprNode {

	private VOperator vop;
	private ExprNode expression;
	
	public VOpNode(VOperator _vop, ExprNode _expression) {
		this.vop = _vop;
		this.expression = _expression;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		expression.check();
		Type exprType = expression.getExprType();
		if (!(exprType instanceof Variety)) {
			throw new SemanticCheckException("The expression must be a vector");
		}
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		Variety varType = (Variety) exprType;
		if (varType.getElementType() == floatType) {
			throw new SemanticCheckException("The input for VBOp must be a vector of booleans");
		}
		setExprType(floatType);
	}

	@Override
	public String generateXML() {
		// TODO - find out what avg codegens into
		String func = "";
		switch (vop) {
		case AVERAGE : func = "???";
		case PRODUCT : func = "varietymul";
		case SUM 	 : func = "varietysum";
		}
		return "\\" + func + "{" + expression.generateXML() + "}";
	}

}
