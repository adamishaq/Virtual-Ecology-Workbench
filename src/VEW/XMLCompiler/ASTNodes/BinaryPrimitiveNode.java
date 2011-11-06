package VEW.XMLCompiler.ASTNodes;

public class BinaryPrimitiveNode extends ExprNode {

	private BinaryPrimitive prim;
	private ExprNode lExpr;
	private ExprNode rExpr;

	public BinaryPrimitiveNode(BinaryPrimitive prim, ExprNode lExpr, ExprNode rExpr) {
		this.prim = prim;
		this.lExpr = lExpr;
		this.rExpr = rExpr;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		//TODO this might be able to take variety arguments, :/
		lExpr.check();
		rExpr.check();
		AmbientVariableTables varTables = AmbientVariableTables.getTables();
		Type floatType = (Type) varTables.checkTypeTable("$float");
		if (lExpr.getExprType() != floatType) {
			throw new SemanticCheckException("First argument does not evaluate to a float");
		}
		if (rExpr.getExprType() != floatType) {
			throw new SemanticCheckException("Second argument does not evalute to a float");
		}
		setExprType(floatType);

	}

	@Override
	public String generateXML() {
		String op = "";
		switch (prim) {
		case MAX     : op = "max"; break; 
		case MIN    : op = "min"; break; 
		}
		return "\\" + op + "{" + lExpr.generateXML() + "," + rExpr.generateXML() + "}";
	}

}
