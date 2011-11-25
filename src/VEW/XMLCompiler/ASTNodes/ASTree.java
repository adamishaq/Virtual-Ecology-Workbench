package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;

public abstract class ASTree {
	protected Catagory enclosingCatagory;

	protected int line_number;
	
	public Catagory getCatagory() {
		return enclosingCatagory;
	}
	
	public void setCatagory(Catagory catagory) {
		enclosingCatagory = catagory;
	}
	
	public int getLine() {
		return line_number;
	}
	
	public abstract void check();
	public abstract String generateXML();
	public abstract String generateLatex();
}
