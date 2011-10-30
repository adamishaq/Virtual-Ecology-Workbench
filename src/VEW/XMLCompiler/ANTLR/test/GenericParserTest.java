package VEW.XMLCompiler.ANTLR.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.runtime.RecognitionException;
import org.junit.Before;
import org.junit.Test;

import VEW.XMLCompiler.ANTLR.ANTLRParser;
import VEW.XMLCompiler.ANTLR.ASTree;
import VEW.XMLCompiler.ANTLR.TreeWalkerException;

public abstract class GenericParserTest {
	
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

	
	
	
}
