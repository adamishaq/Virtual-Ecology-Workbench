package VEW.Planktonica2.Model;

import java.util.Collection;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public abstract class Variety extends VariableType {
	protected Catagory parentCatagory;
	private VarietyConcentration linkConcentration;
	
	
	public Variety (Catagory catagory) {
		parentCatagory = catagory;
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		setVarType(new VarietyType("float", floatType));
	}

	
	
	public Variety(String name, String desc, Type type, Collection<Unit> units,
			Float value, Integer hist, boolean editable, VarietyConcentration link) {
		super(name, desc, type, units, value, hist, editable);
		linkConcentration = link;
	}



	@Override
	public BuildFromXML build (XMLTag tag) {
		super.build(tag);
		String link = tag.getAttribute(XMLTagEnum.VARIETY_LINK.xmlTag());
		linkConcentration = parentCatagory.checkVarietyConcTable(link);
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		VarietyType varType = new VarietyType("float", floatType);
		varType.setLink(linkConcentration);
		setVarType(varType);
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
		VarietyType varType = (VarietyType) getVarType();
		varType.setLink(linkConcentration);
		this.linkConcentration = linkConcentration;
	}
}
