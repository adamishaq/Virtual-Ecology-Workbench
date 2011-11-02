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
		return "\\var{" + name + "}";
	}
	
	public String generateLatex() {
		String latex_name = name.replaceFirst("_", "_{");
		latex_name += "}";
		return latex_name;
	}

}
