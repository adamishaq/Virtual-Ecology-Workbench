package VEW.XMLCompiler.ANTLR;

public class UnaryPrimNode extends ASTree implements ExprNode {

	private UnaryPrimitive primitive;
	private ExprNode argument;
	
	public UnaryPrimNode(String primName, ExprNode argExpr) {
		this.primitive = UnaryPrimitive.valueOf(primName);
		this.argument = argExpr;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
