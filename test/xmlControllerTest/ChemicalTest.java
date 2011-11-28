package xmlControllerTest;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.XMLTagEnum;

public class ChemicalTest {

	XMLTag chem;
	
	@Before
	public void setUp() throws Exception {
		
		chem = new XMLTag(XMLTagEnum.CHEMICAL.xmlTag());
		
		XMLTag name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "chemical");
		XMLTag value = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");

		XMLTag pigment = new XMLTag(XMLTagEnum.PIGMENT.xmlTag(), "true");
		
		
		// spec 1
		XMLTag spectrumTag1 = new XMLTag (XMLTagEnum.SPECTRUM.xmlTag());

		XMLTag specName1 = new XMLTag(XMLTagEnum.NAME.xmlTag(), "spectrum1");

		XMLTag equation1 = new XMLTag(XMLTagEnum.EQUATION.xmlTag());

		XMLTag equationName1 = new XMLTag(XMLTagEnum.NAME.xmlTag(), "eq1");

		XMLTag values1 = new XMLTag(XMLTagEnum.GRAPH_VAL.xmlTag(), "\\graphvals{{0,1},{1,1},{2,0.677},{3,0.702},{4,0.702},{5,0.703},{6,0.695},{7,0.673},{8,0.65},{9,0.618},{10,0.628},{11,0.65},{12,0.672},{13,0.687},{14,0.62},{15,1},{16,1},{17,1},{18,1},{19,1},{20,1},{21,1},{22,1},{23,1},{24,1}}");

		equation1.addTag(equationName1);
		equation1.addTag(values1);

		spectrumTag1.addTag(specName1);
		spectrumTag1.addTag(equation1);

		// spec 2
		XMLTag spectrumTag2 = new XMLTag (XMLTagEnum.SPECTRUM.xmlTag());

		XMLTag specName2 = new XMLTag(XMLTagEnum.NAME.xmlTag(), "spectrum2");

		XMLTag equation2 = new XMLTag(XMLTagEnum.EQUATION.xmlTag());

		XMLTag equationName2 = new XMLTag(XMLTagEnum.NAME.xmlTag(), "eq1");

		XMLTag values2 = new XMLTag(XMLTagEnum.GRAPH_VAL.xmlTag(), "\\graphvals{{0,1},{1,1},{2,0.677},{3,0.702},{4,0.702},{5,0.703},{6,0.695},{7,0.673},{8,0.65},{9,0.618},{10,0.628},{11,0.65},{12,0.672},{13,0.687},{14,0.62},{15,1},{16,1},{17,1},{18,1},{19,1},{20,1},{21,1},{22,1},{23,1},{24,1}}");

		equation2.addTag(equationName2);
		equation2.addTag(values2);

		spectrumTag2.addTag(specName2);
		spectrumTag2.addTag(equation2);
		
		// variables
		XMLTag variable = new XMLTag(XMLTagEnum.VARIABLE.xmlTag());
		XMLTag variableName =  new XMLTag (XMLTagEnum.NAME.xmlTag(), "variable1");
		XMLTag variableUnit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,2,2");
		XMLTag variableDesc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "Herrow");
		variable.addTag(variableName);
		variable.addTag(variableUnit);
		variable.addTag(variableDesc);
		
		
		XMLTag variable2 = new XMLTag(XMLTagEnum.VARIABLE.xmlTag());
		XMLTag variable2Name =  new XMLTag (XMLTagEnum.NAME.xmlTag(), "variable2");
		XMLTag variable2Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,2,2");
		XMLTag variable2Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "Herrow");
		variable2.addTag(variable2Name);
		variable2.addTag(variable2Unit);
		variable2.addTag(variable2Desc);
		
		
		XMLTag variable3 = new XMLTag(XMLTagEnum.VARIABLE.xmlTag());
		XMLTag variable3Name =  new XMLTag (XMLTagEnum.NAME.xmlTag(), "variable3");
		XMLTag variable3Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,2,2");
		XMLTag variable3Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "Herrow");
		variable3.addTag(variable3Name);
		variable3.addTag(variable3Unit);
		variable3.addTag(variable3Desc);
		
		// Parameters
		// desc name unit value
		XMLTag param = new XMLTag(XMLTagEnum.PARAMETER.xmlTag());
		XMLTag paramName = new XMLTag(XMLTagEnum.NAME.xmlTag(), "param1");
		XMLTag paramDesc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "some shit");
		XMLTag paramUnit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,1,0");
		XMLTag paramValue = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		param.addTag(paramName);
		param.addTag(paramValue);
		param.addTag(paramUnit);
		param.addTag(paramDesc);
		
		XMLTag param2 = new XMLTag(XMLTagEnum.PARAMETER.xmlTag());
		XMLTag param2Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "param2");
		XMLTag param2Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "some shit");
		XMLTag param2Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,1,0");
		XMLTag param2Value = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		param2.addTag(param2Name);
		param2.addTag(param2Desc);
		param2.addTag(param2Unit);
		param2.addTag(param2Value);
		
		// locals
		XMLTag local1 = new XMLTag(XMLTagEnum.LOCAL.xmlTag());
		XMLTag local1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "local1");
		XMLTag local1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "some shit");
		XMLTag local1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,1,0");
		XMLTag local1Value = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		local1.addTag(local1Name);
		local1.addTag(local1Value);
		local1.addTag(local1Unit);
		local1.addTag(local1Desc);
		
		XMLTag local2 = new XMLTag(XMLTagEnum.LOCAL.xmlTag());
		XMLTag local2Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "local2");
		XMLTag local2Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "some shit");
		XMLTag local2Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,1,0");
		XMLTag local2Value = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		local2.addTag(local2Name);
		local2.addTag(local2Desc);
		local2.addTag(local2Unit);
		local2.addTag(local2Value);
		
		
		// functions
		XMLTag function = new XMLTag(XMLTagEnum.FUNCTION.xmlTag());
		XMLTag functionName = new XMLTag(XMLTagEnum.NAME.xmlTag(), "function1");
		XMLTag functionEquation1 = new XMLTag(XMLTagEnum.EQUATION.xmlTag());
		XMLTag funcEq1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "thisTestTakesAges");
		XMLTag funcEq1 = new XMLTag (XMLTagEnum.EQ.xmlTag(), "\\assign{local1, local2}");
		functionEquation1.addTag(funcEq1Name);
		functionEquation1.addTag(funcEq1);
		
		XMLTag funcEquation2 = new XMLTag(XMLTagEnum.EQUATION.xmlTag());
		XMLTag funcEq2Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "thisTestTakesEvenMoreAges");
		XMLTag funcEq2 = new XMLTag (XMLTagEnum.EQ.xmlTag(), "1 + 2");
		funcEquation2.addTag(funcEq2Name);
		funcEquation2.addTag(funcEq2);
		
		function.addTag(functionName);
		function.addTag(functionEquation1);
		function.addTag(funcEquation2);
		
		// add all to chem
		chem.addTag(name);
		chem.addTag(value);
		chem.addTag(pigment);
		chem.addTag(spectrumTag1);
		chem.addTag(spectrumTag2);
		chem.addTag(variable);
		chem.addTag(variable2);
		chem.addTag(variable3);
		chem.addTag(param);
		chem.addTag(param2);
		chem.addTag(local1);
		chem.addTag(local2);
		chem.addTag(function);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		Chemical c = new Chemical("");
		c.build(chem);
		
		assertNotNull(c.getName());
		assertNotNull(c.getValue());
		assertNotNull(c.hasPigment());
		assertNotNull(c.getSpectrum());
		assertNotNull(c.getFunctions());
		
		
	}

}
