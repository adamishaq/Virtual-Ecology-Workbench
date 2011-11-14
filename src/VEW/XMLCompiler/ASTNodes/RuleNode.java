package VEW.XMLCompiler.ASTNodes;

public abstract class RuleNode extends ASTree {
	private boolean insideConditional = false;
	
	public boolean isWithinConditional(){
		return insideConditional;
	}
	
	public void setInsideConditional(boolean inside) {
		insideConditional = inside;
	}
}
