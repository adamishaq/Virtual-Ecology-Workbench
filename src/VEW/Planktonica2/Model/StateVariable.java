package VEW.Planktonica2.Model;

import VEW.Common.XML.XMLTag;

public class StateVariable extends VariableType {

	private String codeName;
	
	public StateVariable(Catagory catagory) {
		super(catagory);
	}

	
	
	@Override
	public BuildFromXML build(XMLTag tag) {
		super.build(tag);
		XMLTag codeName = tag.getTag(XMLTagEnum.CODE_NAME.xmlTag());
		if (codeName != null) {
			this.codeName = codeName.getValue(); 
		}
		
		return this;
	}



	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		if (codeName != null) {
			varTag.addTag(XMLTagEnum.CODE_NAME.xmlTag(), codeName);
		}
		varTag.setName("variable");
		return varTag;
	}
	
	public String getCodeName() {
		return this.codeName;
	}
	
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
}
