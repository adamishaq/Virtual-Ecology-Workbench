package VEW.XMLCompiler.ASTNodes;

import java.util.Collection;

import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;

/**
 * A subclass of ASTree that represents any AST node that could be considered an expression.
 * @author David Coulden
 *
 */
public abstract class ExprNode extends ASTree{
	protected Type exprType; //Type the expression evaluates to
	protected Collection<Unit> units; //Units the expression evaluates to
	
	public void setExprType(Type _exprType) {
		exprType = _exprType;
	}
	
	public Type getExprType() {
		return exprType;
	}
	
	public Collection<Unit> getUnits() {
		return units;
	}
}
