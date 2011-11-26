package VEW.Planktonica2.Model;

import VEW.Common.XML.XMLTag;

public class VarietyVariable extends Variety {

	public VarietyVariable(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		varTag.setName("varietyvariable");
		return varTag;
	}

	
}
