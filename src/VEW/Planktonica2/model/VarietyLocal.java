package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;

public class VarietyLocal extends Variety {

	public VarietyLocal(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	
	@Override
	public XMLTag buildToXML() {
		XMLTag varTag = super.buildToXML();
		varTag.setName("varietylocal");
		return varTag;
	}

	
}
