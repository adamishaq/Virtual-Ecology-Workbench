package VEW.Planktonica2.Model;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public class Variety extends VariableType {

	private VarietyConcentration linkConcentration;
	
	
	public Variety (Catagory catagory) {
		super(catagory);
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		setVarType(new VarietyType("float", floatType));
	}

	
	@Override
	public BuildFromXML build (XMLTag tag) {
		varBuild(tag);
		String link = tag.getAttribute(XMLTagEnum.VARIETY_LINK.xmlTag());
		linkConcentration = parentCatagory.checkVarietyConcTable(link);		
		return this;
		
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		varTag.setAttribute("link", linkConcentration.getName());
		return varTag;
	}
	

	public VarietyConcentration getLinkConcentration () {
		return this.linkConcentration;
	}
	
	public void setLinkConcentration(VarietyConcentration linkConcentration) {
		this.linkConcentration = linkConcentration;
	}
}
