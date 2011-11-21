package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;

public class Parameter extends VariableType {

	public Parameter(Catagory catagory) {
		super(catagory);
	}
	
	public XMLTag buildToXML() {
		XMLTag varTag = super.buildToXML();
		varTag.setName("parameter");
		return varTag;
	}

	
}
