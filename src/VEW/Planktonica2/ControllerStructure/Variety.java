package VEW.Planktonica2.ControllerStructure;

import java.util.Collection;

import VEW.Common.XML.XMLTag;

public class Variety extends VariableType {

	private VarietyConcentration linkConcentration;
	private Collection<VarietyConcentration> allConcentrations;
	
	public Variety (Collection<VarietyConcentration> varietyConcentrations) {
		
		this.allConcentrations = varietyConcentrations;
		
	}
	
	@Override
	public BuildFromXML build (XMLTag tag) {
		
		this.varBuild(tag);
		
		String link = tag.getAttribute(XMLTagEnum.VARIETY_LINK.xmlTag());
		
		this.linkConcentration = getConcentrationFromList(link);
				
		return this;
		
	}
	
	private VarietyConcentration getConcentrationFromList (String name) {
		
		if (this.allConcentrations == null || name == null)
			return null;
		
		for (VarietyConcentration s : this.allConcentrations) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		
		return null;
		
	}


	public VarietyConcentration getLinkConcentration () {
		return this.linkConcentration;
	}
}
