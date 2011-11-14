package VEW.Planktonica2.model;

import java.util.ArrayList;

import VEW.Planktonica2.ControllerStructure.SelectableItem;
import VEW.XMLCompiler.ASTNodes.SymbolTable;

public abstract class Catagory implements SelectableItem, BuildFromXML {
	
	protected String name;
	
	protected ArrayList <Function> functions;
	
	protected SymbolTable<StateVariable> stateVarTable;
	protected SymbolTable<Parameter> paramTable;
	protected SymbolTable<Local> localVarTable;
	protected SymbolTable<VarietyVariable> varietyStateTable;
	protected SymbolTable<VarietyParameter> varietyParamTable;
	protected SymbolTable<VarietyConcentration> varietyConcTable;
	protected SymbolTable<VarietyLocal> varietyLocalTable;
	
	public Catagory() {
		
		functions = new ArrayList<Function>();
		
		initialiseTables();
	}

	private void initialiseTables() {
		stateVarTable = new SymbolTable<StateVariable>();
		paramTable = new SymbolTable<Parameter>();
		localVarTable = new SymbolTable<Local>();
		varietyStateTable = new SymbolTable<VarietyVariable>();
		varietyParamTable = new SymbolTable<VarietyParameter>();
		varietyConcTable = new SymbolTable<VarietyConcentration>();
		varietyLocalTable = new SymbolTable<VarietyLocal>();
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addToVarietyConcTable(VarietyConcentration conc) {
		varietyConcTable.put(conc.getName(), conc);
	}
	
	public VarietyConcentration checkVarietyConcTable(String conc) {
		return varietyConcTable.get(conc);
	}
	
	
	
	public void addToStateVarTable(StateVariable var) {
		stateVarTable.put(var.getName(), var);
	}
	
	public StateVariable checkStateVariableTable(String varName) {
		return stateVarTable.get(varName);
	}
	
	
	
	public void addToParamTable(Parameter param) {
		paramTable.put(param.getName(), param);
	}
	
	public Parameter checkParameterTable(String paramName) {
		return paramTable.get(paramName);
	}
	
	
	
	public void addToLocalTable(Local local) {
		localVarTable.put(local.getName(), local);
	}
	
	public Local checkLocalVarTable(String localName) {
		return localVarTable.get(localName);
	}
	
	
	
	public void addToVarietyStateTable(VarietyVariable var) {
		varietyStateTable.put(var.getName(), var);
	}
	
	public VarietyVariable checkVarietyStateTable(String varietyName) {
		return varietyStateTable.get(varietyName);
	}
	
	
	
	public void addToVarietyParamTable(VarietyParameter varParam) {
		varietyParamTable.put(varParam.getName(), varParam);
	}
	
	public VarietyParameter checkVarietyParamTable(String paramName) {
		return varietyParamTable.get(paramName);
	}
	
	
	
	public void addToVarietyLocalTable(VarietyLocal varLocal) {
		varietyLocalTable.put(varLocal.getName(), varLocal);
	}
	
	public VarietyLocal checkVarietyLocalTable(String localName) {
		return varietyLocalTable.get(localName);
	}

	public void setFunctions(ArrayList<Function> functions) {
		this.functions = functions;
	}
	public ArrayList<Function> getFunctions() {
		return functions;
	}
	@Override
	public int getNoFunctions() {
		return functions.size();
	}
	@Override
	public Function getFunctionAtIndex(int functionNo) {
		return this.functions.get(functionNo);
	}
	
	
	@Override
	public String toString() {
		return this.getName();
	}
}
