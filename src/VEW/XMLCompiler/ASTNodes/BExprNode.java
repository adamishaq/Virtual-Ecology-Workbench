package VEW.XMLCompiler.ASTNodes;

public abstract class BExprNode extends ASTree{
	
	protected Type bExprType;
	
	public void setBExprType(Type _bExprType) {
		bExprType = _bExprType;
	}
	
	public Type getBExprType() {
		return bExprType;
	}

}
