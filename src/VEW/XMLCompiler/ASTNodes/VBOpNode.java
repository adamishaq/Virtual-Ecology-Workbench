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
		case ALL  : func = "allVariety";
		case NONE : func = "noVariety";
		case SOME : func = "someVariety";
		}
		return "\\" + func + "{" + expression.generateXML() + "}";
	}
}
