package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;


public class Local extends VariableType {
	public Local(Catagory catagory) {
		super(catagory);
	}
	
	public XMLTag buildToXML() {
		XMLTag varTag = super.buildToXML();
		varTag.setName("local");
		return varTag;
	}
}
