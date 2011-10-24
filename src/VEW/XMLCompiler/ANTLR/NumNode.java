package VEW.XMLCompiler.ANTLR;

public class NumNode extends ASTree implements ExprNode {

	private float value;
	
	public NumNode(float value) {
		this.value = value;
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
