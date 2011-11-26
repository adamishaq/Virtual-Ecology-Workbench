package VEW.Planktonica2.Model;

import VEW.Common.XML.XMLTag;

public class VarietyLocal extends Variety {

	public VarietyLocal(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		varTag.setName("varietylocal");
		return varTag;
	}

	
}
