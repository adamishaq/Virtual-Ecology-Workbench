package VEW.XMLCompiler.ANTLR.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.runtime.RecognitionException;
import org.junit.Before;
import org.junit.Test;

import VEW.XMLCompiler.ANTLR.ANTLRParser;

public abstract class GenericTest {
	
	private final String pathSep = System.getProperty("file.separator");
	private final String pathName = "src" + pathSep + "VEW" + pathSep + "XMLCompiler" + pathSep + "ANTLR" + pathSep +  "test" + pathSep + "test files" + pathSep;
	private final String fileName = "";
	ANTLRParser p;
	
	@Before
	public void setUp() throws IOException {
		try {
			p = new ANTLRParser (pathName + getFileName());
		} catch (FileNotFoundException e) {
			System.err.println("Could not found file at: " + pathName + fileName);
			throw e;
		}
	}
	
	/**
	 * Method containing the name of the file to be run by the Parser.
	 * @return the file name
	 */
	protected abstract String getFileName();

	/**
	 * runs the parser and fails on break.
	 * @throws RecognitionException fails if the parser fails to recognise a token.
	 */
	@Test
	public void testParser () throws RecognitionException {
		
		p.getAntlrAST();
		
		
	}
}
