package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.Catagory;

public abstract class ASTree {
	protected Catagory enclosingCatagory;

	public Catagory getCatagory() {
		return enclosingCatagory;
	}
	
	public void setCatagory(Catagory catagory) {
		enclosingCatagory = catagory;
	}
	
	public abstract void check() throws SemanticCheckException;
	public abstract String generateXML();
}
