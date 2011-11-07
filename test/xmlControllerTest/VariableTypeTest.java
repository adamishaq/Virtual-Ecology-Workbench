package xmlControllerTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.ControllerStructure.Equation;
import VEW.Planktonica2.ControllerStructure.Local;
import VEW.Planktonica2.ControllerStructure.Parameter;
import VEW.Planktonica2.ControllerStructure.Unit;
import VEW.Planktonica2.ControllerStructure.Variable;
import VEW.Planktonica2.ControllerStructure.XMLTagEnum;

public class VariableTypeTest {

	private XMLTag p1;
	private XMLTag l1;
	private XMLTag v1;
	
	@Before
	public void setUp() throws Exception {
		
		p1 = new XMLTag(XMLTagEnum.PARAMETER.xmlTag());
		XMLTag p1Val = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		XMLTag p1Hist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "1");
		XMLTag p1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "p1");
		XMLTag p1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a parameter");
		XMLTag p1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,m,1");
		
		l1 = new XMLTag(XMLTagEnum.LOCAL.xmlTag());
		XMLTag l1Val = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "1");
		XMLTag l1Hist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "2");
		XMLTag l1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "l1");
		XMLTag l1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a local");
		XMLTag l1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,0,0");
		
		v1 = new XMLTag(XMLTagEnum.VARIABLE.xmlTag());
		XMLTag v1Val = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "2");
		XMLTag v1Hist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "3");
		XMLTag v1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "v1");
		XMLTag v1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a variable");
		XMLTag v1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "-3,g,1");
		
		p1.addTag(p1Name);
		p1.addTag(p1Val);
		p1.addTag(p1Hist);
		p1.addTag(p1Desc);
		p1.addTag(p1Unit);
		
		l1.addTag(l1Name);
		l1.addTag(l1Val);
		l1.addTag(l1Hist);
		l1.addTag(l1Desc);
		l1.addTag(l1Unit);
		
		v1.addTag(v1Name);
		v1.addTag(v1Val);
		v1.addTag(v1Hist);
		v1.addTag(v1Desc);
		v1.addTag(v1Unit);
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		Parameter p = new Parameter();
		p.build(p1);
		
		Local l = new Local();
		l.build(l1);
		
		Variable v = new Variable();
		v.build(v1);
		
		
		assertNotNull(p.getName());
		assertNotNull(p.getDesc());
		assertNotNull(p.getValue());
		assertNotNull(p.getHist());
		assertNotNull(p.getUnits());
		
		assertNotNull(l.getName());
		assertEquals(l.getName(), "l1");
		
		assertNotNull(v.getValue());
		assertEquals(v.getValue(), 2);
		
		// checks l units
		assertNotNull(l.getUnits());
		
		Unit [] units = new Unit [1];
		
		units = l.getUnits().toArray(units);
		
		assertEquals(units.length, 1);
		
		assertNotNull(units[0]);
		
		assertEquals(units[0].getExponent(), 0);
		
		assertEquals(units[0].getSize(), 0);
		
		assertEquals(units[0].getName(), "0");
		
		// checks v units
		units = new Unit [1];
		
		units = v.getUnits().toArray(units);
		
		assertEquals(units.length, 1);
		
		assertNotNull(units[0]);
		
		assertEquals(units[0].getExponent(), 1);
		
		assertEquals(units[0].getSize(), -3);
		
		assertEquals(units[0].getName(), "g");
		
		
		
		
		
	}

}
