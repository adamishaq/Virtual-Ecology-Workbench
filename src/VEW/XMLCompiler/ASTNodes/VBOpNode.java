package VEW.XMLCompiler.ASTNodes;

public class VBOpNode extends BExprNode {
	
	private VBoolOperator vop;
	private BExprNode expression;
	
	public VBOpNode(VBoolOperator _vop, BExprNode _expression) {
		this.vop = _vop;
		this.expression = _expression;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		String func = "";
		switch (vop) {
		case ALL  : func = "allVariety"; break;
		case NONE : func = "noVariety"; break;
		case SOME : func = "someVariety"; break;
		}
		return "\\" + func + "{" + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String func = "???";
		String exp = "???";
		if (expression != null)
			exp = expression.generateLatex();
		switch (vop) {
		case ALL  : func = " all "; break;
		case NONE : func = " no "; break;
		case SOME : func = " some "; break;
		}
		return func + "(" + exp + ")";
	}
}
