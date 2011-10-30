package VEW.XMLCompiler.ANTLR.test;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

import VEW.XMLCompiler.ANTLR.TreeWalkerException;

/**
 * Only passes the test when the file parses correctly.
 * 
 * @author Chris
 *
 */
public abstract class FilePassesParserTest extends GenericParserTest {

	/**
	 * Runs the parser and fails on break.
	 * 
	 * 
	 * @throws RecognitionException fails if the parser fails to recognise a token.
	 * @throws TreeWalkerException 
	 */
	@Test
	public void testParserSuccess() throws RecognitionException, TreeWalkerException {
		
		p.getAST();
		
	}
	
}
