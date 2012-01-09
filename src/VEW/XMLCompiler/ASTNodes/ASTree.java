package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;

public abstract class ASTree {

	protected int line_number;
	
	public int getLine() {
		return line_number;
	}
	
	/**
	 * Performs a symantic check on this ASTree.
	 * 
	 * @param enclosingCategory the parent catagory in which this function (ASTree) is supposed to be contained
	 * @param enclosingTree the top level node that represents a constructed tree.
	 */
	public abstract void check(Catagory enclosingCategory, ConstructedASTree enclosingTree);
	public abstract String generateXML();
	public abstract String generateLatex();
	
	public abstract void acceptDependencyCheckVisitor(ASTreeVisitor visitor);
}
