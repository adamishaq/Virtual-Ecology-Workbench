package VEW.XMLCompiler.ASTNodes;

import VEW.XMLCompiler.DependencyChecker.HasDependency;

public abstract class RuleNode extends ASTree implements  HasDependency {

	private boolean insideConditional = false;
	
	public boolean isWithinConditional(){
		return insideConditional;
	}
	
	public void setInsideConditional(boolean inside) {
		insideConditional = inside;
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		visitor.visit(this);
		
	}
	
	
}
