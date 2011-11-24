package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;

public class StateVariable extends VariableType {

	public StateVariable(Catagory catagory) {
		super(catagory);
	}

	public XMLTag buildToXML() {
		XMLTag varTag = super.buildToXML();
		varTag.setName("variable");
		return varTag;
	}
}
