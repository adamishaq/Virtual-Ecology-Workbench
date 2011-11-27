package VEW.Planktonica2.Model;

import java.util.Collection;

import VEW.Common.XML.XMLTag;

public class VarietyConcentration extends VariableType {

	public VarietyConcentration(String name, String desc, Type type,
			Collection<Unit> units) {
		super(name, desc, type, units);
	}

	public VarietyConcentration(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		varTag.setName("varietyconcentration");
		return varTag;
	}

	
	
}
