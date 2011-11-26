package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public class VarietyConcentration extends VariableType {

	public VarietyConcentration(FunctionalGroup funcGroup) {
		super(funcGroup);
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		setVarType(floatType);
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		varTag.setName("varietyconcentration");
		return varTag;
	}

	
	
}
