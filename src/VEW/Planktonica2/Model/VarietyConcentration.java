package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;


import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public class VarietyConcentration extends VariableType {

	

	public VarietyConcentration(String name, String desc, Type type,
			Collection<Unit> units, Float value, Integer hist, boolean editable) {
		super(name, desc, type, units, value, hist, editable);
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		VarietyType varType = new VarietyType("float", floatType);
		varType.setLink(this);
		setVarType(varType);
	}

	public VarietyConcentration() {
		super();
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		VarietyType varType = new VarietyType("float", floatType);
		varType.setLink(this);
		setVarType(varType);
	}
	

	public VarietyConcentration(VarietyConcentration v) {
		this.setName(v.getName());
		this.setDesc(v.getDesc());
		this.setVarType(v.getVarType());
		Collection<Unit> units = new ArrayList<Unit>();
		for (Unit u : v.getUnits()) {
			Unit new_u = new Unit(u.getSize(),u.getName(),u.getExponent());
			units.add(new_u);
		}
		this.setUnits(units);
	}

	@Override
	protected String getVariableClassName() {
		return "varietyconcentration";
	}

	
	
}