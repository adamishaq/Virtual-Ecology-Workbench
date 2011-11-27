package VEW.Planktonica2.Model;

import java.util.Collection;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public class VarietyConcentration extends VariableType {

	public VarietyConcentration(String name, String desc, Type type,
			Collection<Unit> units) {
		super(name, desc, type, units);
	}

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
