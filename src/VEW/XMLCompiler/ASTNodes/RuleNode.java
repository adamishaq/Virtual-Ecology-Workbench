package VEW.XMLCompiler.ASTNodes;

import VEW.XMLCompiler.DependencyChecker.HasDependency;
/**
 * A subclass of ASTree that represents any AST node that could be considered a single statement.
 * @author David Coulden
 *
 */
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
