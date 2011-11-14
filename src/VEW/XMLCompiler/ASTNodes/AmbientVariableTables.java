package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Type;

public class AmbientVariableTables {
	
	private static AmbientVariableTables tables;
	private SymbolTable<Type> typeTable;
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
		typeTable.put("$float", new Type("float"));
		typeTable.put("$foodSet", new Type("foodSet"));
		typeTable.put("$vector", new Type("vector"));
		typeTable.put("$boolean", new Type("boolean"));
		
	}

	private void initialiseSystemVarTable() {
		Type floatType = (Type) typeTable.get("$float"); 
		systemVarTable.put("d_leap", 
				new Variable( "d_leap", floatType, "Extra days due to leap year (1 or 0)"));
		systemVarTable.put("d_year",
				new Variable("d_year", floatType, "Days this year since 1st January"));
		systemVarTable.put("PI",
				new Variable( "PI", floatType, "PI"));
		systemVarTable.put("TimeStep",
				new Variable( "TimeStep", floatType, "Time step size"));
	}

	private void initialiseWaterColumnVarTable() {
		Type floatType = (Type) typeTable.get("$float");
		waterColumnVarTable.put("Turbocline",
				new Variable("Turbocline", floatType, "Turbocline"));
		
	}

	private void initialisePhysicsVarTable() {
		Type floatType = (Type) typeTable.get("$float");
		physicsVarTable.put("Density",
				new Variable("Density", floatType, "Density"));
		physicsVarTable.put("Full Irradiance",
				new Variable("Full Irradiance", floatType, "Full Irradiance"));
		physicsVarTable.put("Salinity",
				new Variable("Salinity", floatType, "Salinity"));
		physicsVarTable.put("Temperature",
				new Variable("Temperature", floatType, "Salinity"));
		physicsVarTable.put("Visible Irradiance",
				new Variable("Visible Irradiance", floatType, "Visible Irradiance"));		
	}
	
	
	public Object checkAllTables(String key) {
		Object obj = checkTypeTable(key);
		if (obj != null)
			return obj;
		obj = checkPhysicsTable(key);
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
	
	public Object checkSystemTable(String key) {
		return systemVarTable.get(key);
	}

	public Object checkWaterColumnTable(String key) {
		return waterColumnVarTable.get(key);
	}

	public Object checkPhysicsTable(String key) {
		return physicsVarTable.get(key);
	}

	public static AmbientVariableTables getTables() {
		if (tables == null) {
			tables = new AmbientVariableTables();
		}
		return tables;
	}
	
	
}
