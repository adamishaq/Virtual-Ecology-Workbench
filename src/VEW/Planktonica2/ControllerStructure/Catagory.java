package VEW.Planktonica2.ControllerStructure;

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
	
	public StateVariable checkParameterTable(String paramName) {
		return (StateVariable) paramTable.get(paramName);
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
}
