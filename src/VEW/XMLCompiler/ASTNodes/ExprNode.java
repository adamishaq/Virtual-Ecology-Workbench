package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;

public abstract class ExprNode extends ASTree{
	protected Type exprType;
	
	public void setExprType(Type _exprType) {
		exprType = _exprType;
	}
	
	public Type getExprType() {
		return exprType;
	}
}
