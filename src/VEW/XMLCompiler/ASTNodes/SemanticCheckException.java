package VEW.XMLCompiler.ASTNodes;

public class SemanticCheckException extends BACONCompilerException {

	/**
	 * An exception thrown when an error occurs within semantic checking
	 */
	private static final long serialVersionUID = 3973387782580284268L;

	public SemanticCheckException() {
		super();
	}
	
	public SemanticCheckException(String msg) {
		message = msg;
	}
	
	public SemanticCheckException(String msg, int _line) {
		line = _line;
		message = msg;
	}
	
}
