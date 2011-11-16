package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Planktonica2.ControllerStructure.GlobalVariable;
import VEW.Planktonica2.ControllerStructure.Type;
import VEW.Planktonica2.ControllerStructure.Unit;

public class AmbientVariableTables {
	
	private static AmbientVariableTables tables;
	private SymbolTable typeTable;
	private SymbolTable physicsVarTable;
	private SymbolTable waterColumnVarTable;
	//private SymbolTable chemicalTable;
	private SymbolTable systemVarTable;
	
	private AmbientVariableTables() {
		initialiseTypeTable();
		initialisePhysicsVarTable();
		initialiseWaterColumnVarTable();
		initialiseSystemVarTable();
	}

	private void initialiseTypeTable() {
		typeTable = new SymbolTable();
		typeTable.put("$float", new Type("float"));
		typeTable.put("$foodSet", new Type("foodSet"));
		typeTable.put("$vector", new Type("vector"));
		typeTable.put("$boolean", new Type("boolean"));		
	}

	private void initialiseSystemVarTable() {
		systemVarTable = new SymbolTable();
		Type floatType = (Type) typeTable.get("$float"); 
		systemVarTable = new SymbolTable();
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
		waterColumnVarTable = new SymbolTable();
		Type floatType = (Type) typeTable.get("$float");
		waterColumnVarTable = new SymbolTable();
		Collection<Unit> units = new ArrayList<Unit>();
		units.add(new Unit(0, "m", 1));
		waterColumnVarTable.put("Turbocline",
				new GlobalVariable("Turbocline", "Turbocline", floatType, units));
	}

	private void initialisePhysicsVarTable() {
		physicsVarTable = new SymbolTable();
		Type floatType = (Type) typeTable.get("$float");
		physicsVarTable = new SymbolTable();
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
		return (Type) typeTable.get(key);
	}
	
	public GlobalVariable checkSystemTable(String key) {
		return (GlobalVariable) systemVarTable.get(key);
	}

	public GlobalVariable checkWaterColumnTable(String key) {
		return (GlobalVariable) waterColumnVarTable.get(key);
	}

	public GlobalVariable checkPhysicsTable(String key) {
		return (GlobalVariable) physicsVarTable.get(key);
	}

	public static AmbientVariableTables getTables() {
		if (tables == null) {
			tables = new AmbientVariableTables();
		}
		return tables;
	}
	
	public String[] getAllVariableNames() {
		Object[] sys_vars = systemVarTable.keySet().toArray();
		Object[] water_vars = waterColumnVarTable.keySet().toArray();
		Object[] phys_vars = physicsVarTable.keySet().toArray();
		String [] all_vars = new String[(sys_vars.length 
				+ water_vars.length + phys_vars.length)];
		int pos = 0;
		for (int i = 0; i < sys_vars.length; i++) {
			all_vars[pos] = (String) sys_vars[i];
			pos++;
		}
		for (int i = 0; i < water_vars.length; i++) {
			all_vars[pos] = (String) water_vars[i];
			pos++;
		}
		for (int i = 0; i < phys_vars.length; i++) {
			all_vars[pos] = (String) phys_vars[i];
			pos++;
		}
		return all_vars;
	}
	
}
