package VEW.Planktonica2.Model;

import java.util.Collection;

import VEW.Common.XML.XMLTag;

public class StateVariable extends VariableType {

	private String codeName;
	
	public StateVariable() {
		super();
	}
	
	

	public StateVariable(String name, String desc, Type type,
			Collection<Unit> units, Float value, Integer hist, boolean editable) {
		super(name, desc, type, units, value, hist, editable);
	}

	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		if (codeName != null && varTag.getTag(XMLTagEnum.CODE_NAME.xmlTag()) == null) {
			varTag.addTag(XMLTagEnum.CODE_NAME.xmlTag(), codeName);
		}
		return varTag;
	}
	
	public String getCodeName() {
		return this.codeName;
	}
	
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}



	
	@Override
	protected String getVariableClassName() {
		// TODO Auto-generated method stub
		return "variable";
	}
}
