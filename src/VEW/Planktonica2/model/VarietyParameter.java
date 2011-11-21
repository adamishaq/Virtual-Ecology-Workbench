package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;

public class VarietyParameter extends Variety {

	public VarietyParameter(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	
	@Override
	public XMLTag buildToXML() {
		XMLTag varTag = super.buildToXML();
		varTag.setName("varietyparameter");
		return varTag;
	}

}
