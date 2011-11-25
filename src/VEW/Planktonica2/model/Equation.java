package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;

public class Equation implements BuildFromXML, BuildToXML {

	private String name;
	private String equation;
	
	@Override
	public BuildFromXML build(XMLTag t) {
		
		XMLTag tag = t.getTag(XMLTagEnum.NAME.xmlTag());
		if (tag != null) {
			this.name = tag.getValue();
		}
		
		tag = t.getTag(XMLTagEnum.EQ.xmlTag());
		
		if (tag != null) {
			this.equation = tag.getValue();
		}
		return this;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}




	@Override
	public XMLTag buildToXML() {
		XMLTag eqTag = new XMLTag("equation");
		eqTag.addTag("name", name);
		//TODO compile AST to XML
		return null;
	}

	
	

}
