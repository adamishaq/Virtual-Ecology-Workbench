package VEW.Planktonica2.ControllerStructure;

import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public class VarietyConcentration extends VariableType {

	public VarietyConcentration(FunctionalGroup funcGroup) {
		super(funcGroup);
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		setVarType(floatType);
	}

	
	
}
