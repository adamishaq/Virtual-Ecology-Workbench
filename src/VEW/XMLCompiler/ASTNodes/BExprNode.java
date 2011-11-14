package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.Type;

public abstract class BExprNode extends ASTree{
	protected Type bExprType;
	
	public void setBExprType(Type _bExprType) {
		bExprType = _bExprType;
	}
	
	public Type getBExprType() {
		return bExprType;
	}
}
