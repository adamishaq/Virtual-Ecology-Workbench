package xmlControllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.model.Spectrum;

public class SpectrumTest {

	XMLTag tag;
	String testName = "Test";
	
	@Before
	public void setUp() throws Exception {
		
		XMLTag spectrumTag = new XMLTag ("spectrum");
		
		XMLTag name = new XMLTag("name", testName);
		
		XMLTag equation = new XMLTag("equation");
		
		XMLTag equationName = new XMLTag("name", "eq1");
		
		XMLTag values = new XMLTag("eq", "\\graphvals{{0,1},{1,1},{2,0.677},{3,0.702},{4,0.702},{5,0.703},{6,0.695},{7,0.673},{8,0.65},{9,0.618},{10,0.628},{11,0.65},{12,0.672},{13,0.687},{14,0.62},{15,1},{16,1},{17,1},{18,1},{19,1},{20,1},{21,1},{22,1},{23,1},{24,1}}");

		equation.addTag(equationName);
		equation.addTag(values);
		
		spectrumTag.addTag(name);
		spectrumTag.addTag(equation);
		
		tag = spectrumTag;
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	
		Spectrum s = new Spectrum ();
		
		s.build(tag);
		
		assertNotNull(s.getName());
		
		assertEquals(s.getName(), testName);
		
		assertNotNull(s.getEquations());
		
		assertEquals(s.getEquations().size(), 25);
		
		System.out.println(s.getEquations().toString());
		
	}

}
