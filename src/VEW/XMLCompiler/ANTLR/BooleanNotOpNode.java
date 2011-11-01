package VEW.XMLCompiler.ANTLR;

public class BooleanNotOpNode extends ASTree implements BExprNode {

	private BExprNode expression;

	public BooleanNotOpNode (BExprNode expression) {
		this.expression = expression;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		return "\\not{" + expression.generateXML() + "}";
	}
	
	public String generateLatex() {
		return " \\sim " + expression.generateLatex();
	}

}
