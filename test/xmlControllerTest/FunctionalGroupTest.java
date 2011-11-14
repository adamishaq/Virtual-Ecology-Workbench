package xmlControllerTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.model.FunctionalGroup;
import VEW.Planktonica2.model.XMLTagEnum;

public class FunctionalGroupTest {

	private XMLTag functionalGroup;

	@Before
	public void setUp() throws Exception {
		
		functionalGroup = new XMLTag(XMLTagEnum.FUNCTIONAL_GROUP.xmlTag());
		
		XMLTag name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "chemical");
		XMLTag value = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		
		
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
		
		
		// variety concentration stuff
		String concentrationName = "vc";
		XMLTag vc = new XMLTag(XMLTagEnum.VARIETY_CONCENTRATION.xmlTag());
		XMLTag vcVal = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		XMLTag vcHist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "1");
		XMLTag vcName = new XMLTag(XMLTagEnum.NAME.xmlTag(), concentrationName);
		XMLTag vcDesc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a variety concentration");
		XMLTag vcUnit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,c,1");
		
		XMLTag p1 = new XMLTag(XMLTagEnum.VARIETY_PARAM.xmlTag());
		XMLTag p1Val = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		XMLTag p1Hist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "1");
		XMLTag p1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "p1");
		XMLTag p1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a parameter");
		XMLTag p1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,m,1");
		
		XMLTag l1 = new XMLTag(XMLTagEnum.VARIETY_LOCAL.xmlTag());
		XMLTag l1Val = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "1");
		XMLTag l1Hist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "2");
		XMLTag l1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "l1");
		XMLTag l1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a local");
		XMLTag l1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,0,0");
		
		XMLTag v1 = new XMLTag(XMLTagEnum.VARIETY_VARIABLE.xmlTag());
		XMLTag v1Val = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "2");
		XMLTag v1Hist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "3");
		XMLTag v1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "v1");
		XMLTag v1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a variable");
		XMLTag v1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "-3,g,1");
		
		
		vc.addTag(vcVal);
		vc.addTag(vcHist);
		vc.addTag(vcName);
		vc.addTag(vcDesc);
		vc.addTag(vcUnit);
		
		p1.addTag(p1Name);
		p1.addTag(p1Val);
		p1.addTag(p1Hist);
		p1.addTag(p1Desc);
		p1.addTag(p1Unit);
		p1.setAttribute(XMLTagEnum.VARIETY_LINK.xmlTag(), concentrationName);
		
		l1.addTag(l1Name);
		l1.addTag(l1Val);
		l1.addTag(l1Hist);
		l1.addTag(l1Desc);
		l1.addTag(l1Unit);
		l1.setAttribute(XMLTagEnum.VARIETY_LINK.xmlTag(), concentrationName);
		
		v1.addTag(v1Name);
		v1.addTag(v1Val);
		v1.addTag(v1Hist);
		v1.addTag(v1Desc);
		v1.addTag(v1Unit);
		v1.setAttribute(XMLTagEnum.VARIETY_LINK.xmlTag(), concentrationName);
		
		// add all to chem
		functionalGroup.addTag(name);
		functionalGroup.addTag(value);
		functionalGroup.addTag(variable);
		functionalGroup.addTag(variable2);
		functionalGroup.addTag(variable3);
		functionalGroup.addTag(param);
		functionalGroup.addTag(param2);
		functionalGroup.addTag(local1);
		functionalGroup.addTag(local2);
		functionalGroup.addTag(vc);
		functionalGroup.addTag(p1);
		functionalGroup.addTag(l1);
		functionalGroup.addTag(v1);
		functionalGroup.addTag(function);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		FunctionalGroup f = new FunctionalGroup();
		f.build(functionalGroup);
		
		assertNotNull(f.getName());
		assertNotNull(f.checkLocalVarTable("local1"));
		assertNotNull(f.checkParameterTable("param1"));
		assertNotNull(f.checkStateVariableTable("variable1"));
		
	
		assertNotNull(f.checkLocalVarTable("local2"));
		assertNotNull(f.checkParameterTable("param2"));
		assertNotNull(f.checkStateVariableTable("variable2"));
		
		assertNotNull(f.checkStateVariableTable("variable3"));
		
		assertNotNull(f.checkVarietyConcTable("vc"));
		assertNotNull(f.checkVarietyLocalTable("l1"));
		assertNotNull(f.checkVarietyParamTable("p1"));
		assertNotNull(f.checkVarietyStateTable("v1"));
		
		assertNotNull(f.getFunctions());
		assertTrue(f.getFunctions().size() == 1);
		
	}

}
