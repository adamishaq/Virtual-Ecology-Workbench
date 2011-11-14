package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.GlobalVariable;
import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.Unit;

import java.util.ArrayList;
import java.util.Collection;

public class AmbientVariableTables {
	
	private static AmbientVariableTables tables;
	private SymbolTable<Type> typeTable;
	private SymbolTable<GlobalVariable> physicsVarTable;
	private SymbolTable<GlobalVariable> waterColumnVarTable;
	//private SymbolTable chemicalTable;
	private SymbolTable<GlobalVariable> systemVarTable;
	
	private AmbientVariableTables() {
		initialiseTypeTable();
		initialisePhysicsVarTable();
		initialiseWaterColumnVarTable();
		initialiseSystemVarTable();
	}

	private void initialiseTypeTable() {
		typeTable = new SymbolTable<Type>();
		typeTable.put("$float", new Type("float"));
		typeTable.put("$foodSet", new Type("foodSet"));
		typeTable.put("$vector", new Type("vector"));
		typeTable.put("$boolean", new Type("boolean"));
		
	}

	private void initialiseSystemVarTable() {
		Type floatType = (Type) typeTable.get("$float"); 
		systemVarTable = new SymbolTable<GlobalVariable>();
		Collection<Unit> units = new ArrayList<Unit>();
		units.add(new Unit(0, "d", 1));
		systemVarTable.put("d_leap", 
				new GlobalVariable( "d_leap", "Extra days due to leap year (1 or 0)", floatType, units));
		units = new ArrayList<Unit>();
		units.add(new Unit(0, "d", 1));
		systemVarTable.put("d_year",
				new GlobalVariable("d_year", "Days this year since 1st January", floatType, units));
		units = new ArrayList<Unit>();
		units.add(new Unit(0, "0", 0));
		systemVarTable.put("PI",
				new GlobalVariable( "PI", "PI", floatType, units));
		units = new ArrayList<Unit>();
		units.add(new Unit(0, "h", 1));
		systemVarTable.put("TimeStep",
				new GlobalVariable( "TimeStep", "Time step size", floatType, units));
	}

	private void initialiseWaterColumnVarTable() {
		Type floatType = (Type) typeTable.get("$float");
		waterColumnVarTable = new SymbolTable<GlobalVariable>();
		Collection<Unit> units = new ArrayList<Unit>();
		units.add(new Unit(0, "m", 1));
		waterColumnVarTable.put("Turbocline",
				new GlobalVariable("Turbocline", "Turbocline", floatType, units));
		
	}

	private void initialisePhysicsVarTable() {
		Type floatType = (Type) typeTable.get("$float");
		physicsVarTable = new SymbolTable<GlobalVariable>();
		Collection<Unit> units = new ArrayList<Unit>();
		units.add(new Unit(0, "kg", -3));
		units.add(new Unit(0, "m", -3));
		physicsVarTable.put("Density",
				new GlobalVariable("Density", "Density", floatType, units));
		units = new ArrayList<Unit>();
		units.add(new Unit(0, "W", 1));
		units.add(new Unit(0, "m", -2));
		physicsVarTable.put("Full Irradiance",
				new GlobalVariable("Full Irradiance", "Full Irradiance", floatType, units));
		units = new ArrayList<Unit>();
		units.add(new Unit(0, "0", 0));
		physicsVarTable.put("Salinity",
				new GlobalVariable("Salinity", "Salinity", floatType, units));
		units = new ArrayList<Unit>();
		units.add(new Unit(0, "C", 1));
		physicsVarTable.put("Temperature",
				new GlobalVariable("Temperature", "Temperature", floatType, units));
		units = new ArrayList<Unit>();
		units.add(new Unit(0, "W", 1));
		units.add(new Unit(0, "m", -2));
		physicsVarTable.put("Visible Irradiance",
				new GlobalVariable("Visible Irradiance", "Visible Irradiance", floatType, units));		
	}
	
	
	public Object checkAllTables(String key) {
		Object obj = checkTypeTable(key);
		if (obj != null)
			return obj;
		obj = checkGlobalVariableTables(key);
		return obj;
	}
	
	public GlobalVariable checkGlobalVariableTables(String key) {
		GlobalVariable obj = checkPhysicsTable(key);
		if (obj != null)
			return obj;
		obj = checkWaterColumnTable(key);
		if (obj != null)
			return obj;
		obj = checkSystemTable(key);
		return obj;
	}
	
	public Type checkTypeTable(String key) {
		return typeTable.get(key);
	}
	
	public GlobalVariable checkSystemTable(String key) {
		return systemVarTable.get(key);
	}

	public GlobalVariable checkWaterColumnTable(String key) {
		return waterColumnVarTable.get(key);
	}

	public GlobalVariable checkPhysicsTable(String key) {
		return physicsVarTable.get(key);
	}

	public static AmbientVariableTables getTables() {
		if (tables == null) {
			tables = new AmbientVariableTables();
		}
		return tables;
	}
	
	
}
