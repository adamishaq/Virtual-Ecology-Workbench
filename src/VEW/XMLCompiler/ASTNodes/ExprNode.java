package VEW.XMLCompiler.ASTNodes;

import java.util.Collection;

import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;

public abstract class ExprNode extends ASTree{
	protected Type exprType;
	protected Collection<Unit> units;
	
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
