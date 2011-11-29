package xmlControllerTest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Model;
import VEW.Planktonica2.Model.XMLTagEnum;

public class SetAsTopPredatorTest {

	FunctionalGroupController cont;
	
	@Before
	public void setUp() throws Exception {
		
		XMLFile f = new XMLFile("hello", "lol");
		
		String name = "predator";
		
		XMLTag funcGroupTag = f.addTag(XMLTagEnum.FUNCTIONAL_GROUP.xmlTag());
		
		funcGroupTag.addTag(XMLTagEnum.PREDATOR.xmlTag(), "true");
		
		funcGroupTag.addTag(XMLTagEnum.NAME.xmlTag(), name);
		
		Model m = new Model(f);
		m.buildFromFile();
		
		cont = new FunctionalGroupController(m);
		
		

		for (FunctionalGroup group : m.getFunctionalGroups()) {
			if (group.getName().equals(name)) {
				cont.setSelectedFunctionalGroup(group);
			}
		}

		
	}

	
	@Test
	public void test() {
		
		Assert.assertTrue(cont.isTopPredator());
		Assert.assertTrue(cont.getSelectedFunctionalGroup().checkStateVariableTable(FunctionalGroup.predVarName) != null);
		
		cont.setSelectedAsTopPredator(true);
		
		Assert.assertTrue(cont.isTopPredator());
		Assert.assertTrue(cont.getSelectedFunctionalGroup().checkStateVariableTable(FunctionalGroup.predVarName) != null);
		
		cont.setSelectedAsTopPredator(false);
		
		Assert.assertFalse(cont.isTopPredator());
		Assert.assertFalse(cont.getSelectedFunctionalGroup().checkStateVariableTable(FunctionalGroup.predVarName) != null);
		
		cont.setSelectedAsTopPredator(false);
		
		Assert.assertFalse(cont.isTopPredator());
		Assert.assertFalse(cont.getSelectedFunctionalGroup().checkStateVariableTable(FunctionalGroup.predVarName) != null);
		
		cont.setSelectedAsTopPredator(true);
		
		Assert.assertTrue(cont.isTopPredator());
		Assert.assertTrue(cont.getSelectedFunctionalGroup().checkStateVariableTable(FunctionalGroup.predVarName) != null);
		
		cont.setSelectedAsTopPredator(false);
		
		Assert.assertFalse(cont.isTopPredator());
		Assert.assertFalse(cont.getSelectedFunctionalGroup().checkStateVariableTable(FunctionalGroup.predVarName) != null);
		
		cont.setSelectedAsTopPredator(true);
		
		Assert.assertTrue(cont.isTopPredator());
		Assert.assertTrue(cont.getSelectedFunctionalGroup().checkStateVariableTable(FunctionalGroup.predVarName) != null);
	}

}
