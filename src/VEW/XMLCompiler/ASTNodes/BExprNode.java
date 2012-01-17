package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Type;

/**
 * A subclass of ASTree that represents any AST node that could be considered a boolean expression.
 * @author David Coulden
 *
 */
public abstract class BExprNode extends ASTree{
	
	protected Type bExprType; //The type the boolean expression evaluates to
	
	public void setBExprType(Type _bExprType) {
		bExprType = _bExprType;
	}
	
	public Type getBExprType() {
		return bExprType;
	}

}
