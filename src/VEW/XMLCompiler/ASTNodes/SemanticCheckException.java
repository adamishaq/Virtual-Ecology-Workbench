package VEW.XMLCompiler.ASTNodes;

public class SemanticCheckException extends Exception {

	private int line;
	//private int char_pos;
	private String message;
	
	/**
	 * 
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

	public int getLine() {
		return line;
	} 

	public String getError() {
		return message;
	}
	
}
