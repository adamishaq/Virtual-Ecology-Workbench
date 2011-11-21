package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;

public class Variety extends VariableType {

	private VarietyConcentration linkConcentration;
	
	
	public Variety (Catagory catagory) {
		super(catagory);
	}

	
	@Override
	public BuildFromXML build (XMLTag tag) {
		varBuild(tag);
		String link = tag.getAttribute(XMLTagEnum.VARIETY_LINK.xmlTag());
		linkConcentration = parentCatagory.checkVarietyConcTable(link);		
		return this;
		
	}
	

	public VarietyConcentration getLinkConcentration () {
		return this.linkConcentration;
	}
	
	public void setLinkConcentration(VarietyConcentration linkConcentration) {
		this.linkConcentration = linkConcentration;
	}
}
