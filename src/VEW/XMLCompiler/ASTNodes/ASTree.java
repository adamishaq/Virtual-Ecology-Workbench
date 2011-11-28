package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;

public abstract class ASTree {

	protected int line_number;
	
	public int getLine() {
		return line_number;
	}
	
	public abstract void check(Catagory enclosingCategory, ConstructedASTree enclosingTree);
	public abstract String generateXML();
	public abstract String generateLatex();
}
