package VEW.Planktonica2.Model;

import java.util.Collection;

import VEW.Common.XML.XMLTag;

public class StateVariable extends VariableType {

	public StateVariable(Catagory catagory) {
		super(catagory);
	}
	
	public StateVariable(String name, String desc, Type type, Collection<Unit> units) {
		super(name, desc, type, units);
	}

	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		varTag.setName("variable");
		return varTag;
	}
}
