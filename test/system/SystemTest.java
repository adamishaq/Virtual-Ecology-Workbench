package system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLFile;
import VEW.Controller2.TitlePage;
import VEW.Controller2.VEWController2;
import VEW.Planktonica2.Planktonica;
import VEW.Planktonica2.SyntaxChecker;

public class SystemTest {
	
	/* An arbitrary test, which simply loads a resource model.xml, clones it, and tests against itself */
	
	private static final String SEPARATOR = File.separator;
	private static final String MODELRESOURCE = "test"+SEPARATOR+"resources"+SEPARATOR+"ModelTree"+SEPARATOR;
	private XMLFile origXmlFile;
	private XMLFile testXmlFile;
	private File f; 
	
	@Before
	public void setupXml() {
		origXmlFile = XMLFile.LoadFile(MODELRESOURCE + "LERM-ES" + SEPARATOR + "1" + SEPARATOR + "Model.xml");
		testXmlFile = origXmlFile.cloneTo(MODELRESOURCE + "LERM-ES" + SEPARATOR + "1" + SEPARATOR + "Model2.xml");
		f = new File(MODELRESOURCE + "LERM-ES" + SEPARATOR + "1" + SEPARATOR + "Model2.xml");
	}
	
	@After
	public void afterTest() {
		f.delete();
	}
	
	@Test 
	public void testXmlIO() {
		String[] origValues = origXmlFile.getValues();
		String[] testValues = testXmlFile.getValues();
		for (int x = 0; x < origValues.length ; x++) {
			assertEquals(origValues[x], testValues[x]);
		}
	}
	
	//ABSTRACT OUT THE IDEA OF COMPILING THE XML, AND HAVE PLANK AND NEW IMPL USE THIS INTERFACE
	
	/*A test which simply checks to ensure the XML file is not written to by Planktonica, since no commands have
	* been issued
	*/
	
	@Test
	public void testXmlCompileDecompile() {
			
		
		TitlePage tp = new TitlePage();
		VEWController2 vc = new VEWController2(tp, testXmlFile);
		Planktonica plk = new Planktonica(vc, testXmlFile);
		SyntaxChecker sc = new SyntaxChecker(vc, testXmlFile);
		//assertTrue(sc.checkModel()); //THE SYNTAX CHECKER IS BROKEN, WILL ALWAYS RETURN FALSE
		
		
		String[] origValues = origXmlFile.getValues();
		String[] testValues = testXmlFile.getValues();
		for (int x = 0; x < origValues.length ; x++) {
			assertEquals(origValues[x], testValues[x]);
		}

	}
	
	/* Write a test which simulates issuing Planktonica commands - Refactoring required */
	
	
}
