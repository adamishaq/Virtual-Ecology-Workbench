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
		// TODO Auto-generated method stub

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

	@Override
	public String generateLatex() {
		String op = "";
		switch (prim) {
		case MAX     : op = " max "; break; 
		case MIN    : op = " min "; break; 
		}
		return op + "(" + lExpr.generateLatex() + "," + rExpr.generateLatex() + ")";
	}

}
