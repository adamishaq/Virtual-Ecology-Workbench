package VEW.XMLCompiler.ASTNodes;

/**
 * An exception representing a problem when parsing the ANTLR common tree, generally indicates a parse
 * error being detected
 * @author David Coulden
 *
 */
public class TreeWalkerException extends BACONCompilerException {

	private int char_pos;
	public TreeWalkerException(String msg) {
		super(msg);
	}

	public TreeWalkerException(String msg, int _line, int _char_pos) {
		line = _line;
		char_pos = _char_pos;
		message = msg;
	}

	public int getLine() {
		return line;
	} 

	public int getChar_pos() {
		return char_pos;
	}

	public String getError() {
		return message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
