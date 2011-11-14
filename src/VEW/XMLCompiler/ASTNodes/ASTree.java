package VEW.XMLCompiler.ASTNodes;

public abstract class ASTree {
	public abstract void check() throws SemanticCheckException;
	public abstract String generateXML();
}
