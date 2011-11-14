package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

public class ConstructedASTree {
	
	private ASTree tree;
	private ArrayList<TreeWalkerException> exceptions;
	
	public ConstructedASTree(ASTree _tree, ArrayList<TreeWalkerException> _excep) {
		tree = _tree;
		exceptions = new ArrayList<TreeWalkerException>(_excep);
	}
	
	public ASTree getTree() {
		return tree;
	}
	
	public ArrayList<TreeWalkerException> getExceptions() {
		return exceptions;
	}

}
