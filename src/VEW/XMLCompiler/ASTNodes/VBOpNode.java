package VEW.XMLCompiler.ANTLR;

public class VBOpNode extends ASTree implements BExprNode {
	
	private VBoolOperator vop;
	private BExprNode expression;
	
	public VBOpNode(VBoolOperator _vop, BExprNode _expression) {
		this.vop = _vop;
		this.expression = _expression;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		String func = "";
		switch (vop) {
		case ALL  : func = "allVariety";
		case NONE : func = "noVariety";
		case SOME : func = "someVariety";
		}
		return "\\" + func + "{" + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String func = "";
		switch (vop) {
		case ALL  : func = " all ";
		case NONE : func = " no ";
		case SOME : func = " some ";
		}
		return func + "(" + expression.generateLatex() + ")";
	}
}