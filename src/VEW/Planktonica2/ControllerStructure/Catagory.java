package VEW.Planktonica2.ControllerStructure;

import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;
import VEW.XMLCompiler.ASTNodes.SymbolTable;

public abstract class Catagory {
	
	protected SymbolTable stateVarTable;
	protected SymbolTable paramTable;
	protected SymbolTable localVarTable;
	protected SymbolTable varietyStateTable;
	protected SymbolTable varietyParamTable;
	protected SymbolTable varietyConcTable;
	protected SymbolTable varietyLocalTable;
	
	public Catagory() {
		initialiseTables();
	}

	private void initialiseTables() {
		stateVarTable = new SymbolTable();
		paramTable = new SymbolTable();
		localVarTable = new SymbolTable();
		varietyParamTable = new SymbolTable();
		varietyConcTable = new SymbolTable();
		varietyLocalTable = new SymbolTable();
		varietyStateTable = new SymbolTable();
	}
	
	public VariableType checkAssignableVariableTables(String variableName) {
		StateVariable sVar = checkStateVariableTable(variableName);
		if (sVar != null) return sVar;
		Local localVar = checkLocalVarTable(variableName);
		if (localVar != null) return localVar;
		VarietyLocal varLocal = checkVarietyLocalTable(variableName);
		if (varLocal != null) return varLocal;
		VarietyVariable varState = checkVarietyStateTable(variableName);
		return varState;
	}
	
	public VariableType checkAccessableVariableTable(String variableName) {
		VariableType var = checkAssignableVariableTables(variableName);
		if (var != null) return var;
		VarietyConcentration conc = checkVarietyConcTable(variableName);
		if (conc != null) return conc;
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		var = (VariableType) tables.checkGlobalVariableTables(variableName);
		return var;
	}
	
	
	
	
	public void addToVarietyConcTable(VarietyConcentration conc) {
		varietyConcTable.put(conc.getName(), conc);
	}
	
	public VarietyConcentration checkVarietyConcTable(String conc) {
		return (VarietyConcentration) varietyConcTable.get(conc);
	}
	
	
	
	public void addToStateVarTable(StateVariable var) {
		stateVarTable.put(var.getName(), var);
	}
	
	public StateVariable checkStateVariableTable(String varName) {
		return (StateVariable) stateVarTable.get(varName);
	}
	
	
	
	public void addToParamTable(Parameter param) {
		paramTable.put(param.getName(), param);
	}
	
	public Parameter checkParameterTable(String paramName) {
		return (Parameter) paramTable.get(paramName);
	}
	
	
	
	public void addToLocalTable(Local local) {
		localVarTable.put(local.getName(), local);
	}
	
	public Local checkLocalVarTable(String localName) {
		return (Local) localVarTable.get(localName);
	}
	
	
	
	public void addToVarietyStateTable(VarietyVariable var) {
		varietyStateTable.put(var.getName(), var);
	}
	
	public VarietyVariable checkVarietyStateTable(String varietyName) {
		return (VarietyVariable) varietyStateTable.get(varietyName);
	}
	
	
	
	public void addToVarietyParamTable(VarietyParameter varParam) {
		varietyParamTable.put(varParam.getName(), varParam);
	}
	
	public VarietyParameter checkVarietyParamTable(String paramName) {
		return (VarietyParameter) varietyParamTable.get(paramName);
	}
	
	
	
	public void addToVarietyLocalTable(VarietyLocal varLocal) {
		varietyLocalTable.put(varLocal.getName(), varLocal);
	}
	
	public VarietyLocal checkVarietyLocalTable(String localName) {
		return (VarietyLocal) varietyLocalTable.get(localName);
	}

	
	
	public SymbolTable getStateVarTable() {
		return stateVarTable;
	}

	public void setStateVarTable(SymbolTable stateVarTable) {
		this.stateVarTable = stateVarTable;
	}

	public SymbolTable getParamTable() {
		return paramTable;
	}

	public void setParamTable(SymbolTable paramTable) {
		this.paramTable = paramTable;
	}

	public SymbolTable getLocalVarTable() {
		return localVarTable;
	}

	public void setLocalVarTable(SymbolTable localVarTable) {
		this.localVarTable = localVarTable;
	}

	public SymbolTable getVarietyStateTable() {
		return varietyStateTable;
	}

	public void setVarietyStateTable(SymbolTable varietyStateTable) {
		this.varietyStateTable = varietyStateTable;
	}

	public SymbolTable getVarietyParamTable() {
		return varietyParamTable;
	}

	public void setVarietyParamTable(SymbolTable varietyParamTable) {
		this.varietyParamTable = varietyParamTable;
	}

	public SymbolTable getVarietyConcTable() {
		return varietyConcTable;
	}

	public void setVarietyConcTable(SymbolTable varietyConcTable) {
		this.varietyConcTable = varietyConcTable;
	}

	public SymbolTable getVarietyLocalTable() {
		return varietyLocalTable;
	}

	public void setVarietyLocalTable(SymbolTable varietyLocalTable) {
		this.varietyLocalTable = varietyLocalTable;
	}
}
