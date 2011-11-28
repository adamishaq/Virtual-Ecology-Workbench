package VEW.Planktonica2.Model;

import java.util.Collection;

import VEW.Common.XML.XMLTag;

public class VarietyConcentration extends VariableType {

	

	public VarietyConcentration(String name, String desc, Type type,
			Collection<Unit> units, Float value, Integer hist, boolean editable) {
		super(name, desc, type, units, value, hist, editable);
	}

	public VarietyConcentration() {
		super();
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		varTag.setName("varietyconcentration");
		return varTag;
	}

	
	
}
