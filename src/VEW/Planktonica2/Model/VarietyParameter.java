package VEW.Planktonica2.Model;

import VEW.Common.XML.XMLTag;

public class VarietyParameter extends Variety {

	public VarietyParameter(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		varTag.setName("varietyparameter");
		return varTag;
	}

}
