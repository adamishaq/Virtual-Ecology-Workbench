package VEW.XMLCompiler.ANTLR;

public class IdNode extends ASTree implements ExprNode {
	
	private String name;
	
	public IdNode(String name) {
		this.name = name;
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
