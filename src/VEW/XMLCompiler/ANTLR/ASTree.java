package VEW.XMLCompiler.ANTLR;

import org.antlr.runtime.tree.CommonTree;

public abstract class ASTree extends CommonTree {
	public abstract void check();
	public abstract String generateXML();
	public abstract String generateLatex();
}
