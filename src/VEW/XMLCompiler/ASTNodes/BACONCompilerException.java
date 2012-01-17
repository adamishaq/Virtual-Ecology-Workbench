package VEW.XMLCompiler.ASTNodes;

public class BACONCompilerException extends Exception {

	/**
	 * An exception that represents any problem occuring during compilation
	 */
	private static final long serialVersionUID = 1L;
	protected int line;
	protected String message;

	public BACONCompilerException() {
		super();
	}

	public BACONCompilerException(String message) {
		super(message);
	}

	public BACONCompilerException(Throwable cause) {
		super(cause);
	}

	public int getLine() {
		return line;
	}

	public String getError() {
		return message;
	}

}