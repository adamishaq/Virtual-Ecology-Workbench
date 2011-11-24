package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

public class ConstructedASTree {
	
	private ASTree tree;
	private ArrayList<Exception> exceptions;
	
	public ConstructedASTree(ASTree _tree, ArrayList<Exception> _excep) {
		tree = _tree;
		exceptions = new ArrayList<Exception>(_excep);
	}
	
	public ASTree getTree() {
		return tree;
	}
	
	public ArrayList<Exception> getExceptions() {
		return exceptions;
	}

}
