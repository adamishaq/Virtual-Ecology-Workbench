package xmlControllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.model.Unit;
import VEW.Planktonica2.model.VarietyConcentration;
import VEW.Planktonica2.model.VarietyLocal;
import VEW.Planktonica2.model.VarietyParameter;
import VEW.Planktonica2.model.VarietyVariable;
import VEW.Planktonica2.model.XMLTagEnum;

public class VarietyTypeTest {

	private XMLTag p1;
	private XMLTag l1;
	private XMLTag v1;
	private XMLTag vc;
	
	private final String concentrationName = "vc";
	
	@Before
	public void setUp() throws Exception {

		vc = new XMLTag(XMLTagEnum.VARIETY_CONCENTRATION.xmlTag());
		XMLTag vcVal = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		XMLTag vcHist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "1");
		XMLTag vcName = new XMLTag(XMLTagEnum.NAME.xmlTag(), concentrationName);
		XMLTag vcDesc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a variety concentration");
		XMLTag vcUnit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,c,1");
		
		p1 = new XMLTag(XMLTagEnum.VARIETY_PARAM.xmlTag());
		XMLTag p1Val = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "0");
		XMLTag p1Hist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "1");
		XMLTag p1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "p1");
		XMLTag p1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a parameter");
		XMLTag p1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,m,1");
		
		l1 = new XMLTag(XMLTagEnum.VARIETY_LOCAL.xmlTag());
		XMLTag l1Val = new XMLTag(XMLTagEnum.VALUE.xmlTag(), "1");
		XMLTag l1Hist = new XMLTag(XMLTagEnum.HIST.xmlTag(), "2");
		XMLTag l1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "l1");
		XMLTag l1Desc = new XMLTag(XMLTagEnum.DESCRIPTION.xmlTag(), "a local");
		XMLTag l1Unit = new XMLTag(XMLTagEnum.UNIT.xmlTag(), "0,0,0");
		
		v1 = new XMLTag(XMLTagEnum.VARIETY_VARIABLE.xmlTag());
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
		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void test() {
		
		VarietyConcentration conc = new VarietyConcentration();
		conc.build(vc);
		
		Collection<VarietyConcentration> concentration = new ArrayList<VarietyConcentration> ();
		concentration.add(conc);
		
		VarietyParameter p = new VarietyParameter(concentration);
		p.build(p1);
		
		VarietyLocal l = new VarietyLocal(concentration);
		l.build(l1);
		
		VarietyVariable v = new VarietyVariable(concentration);
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
		
		assertEquals(l.getLinkConcentration().getName(), concentrationName);
		assertEquals(v.getLinkConcentration().getName(), concentrationName);
		assertEquals(p.getLinkConcentration().getName(), concentrationName);
		
		
	}

}
