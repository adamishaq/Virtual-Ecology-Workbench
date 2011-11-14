package BACONCompiler.SemanticAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;

import VEW.Planktonica2.ControllerStructure.GlobalVariable;
import VEW.Planktonica2.ControllerStructure.Type;
import VEW.XMLCompiler.ASTNodes.SymbolTable;

public class createVariableTest {

	@Test
	public void makeFloat() {
		SymbolTable topSymTable = new SymbolTable();
		topSymTable.put("$float", new Type("float"));
		Type floatType = (Type) topSymTable.get("$float");
		GlobalVariable var = new GlobalVariable("TestVar", "desc", floatType, null);
		SymbolTable table = new SymbolTable(topSymTable);
		table.put("TestVar", var);
		GlobalVariable retrievedVar = (GlobalVariable) table.get("TestVar");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("float"));
	}
	
	@Test
	public void makeFoodSet() {
		SymbolTable topSymTable = new SymbolTable();
		topSymTable.put("$foodSet", new Type("foodSet"));
		Type floatType = (Type) topSymTable.get("$foodSet");
		GlobalVariable var = new GlobalVariable("TestSet", "desc", floatType, null);
		SymbolTable table = new SymbolTable(topSymTable);
		table.put("TestSet", var);
		GlobalVariable retrievedVar = (GlobalVariable) table.get("TestSet");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("foodSet"));
	}
	
	@Test
	public void makeVector() {
		SymbolTable topSymTable = new SymbolTable();
		topSymTable.put("$vector", new Type("vector"));
		Type floatType = (Type) topSymTable.get("$vector");
		GlobalVariable var = new GlobalVariable("Vector", "desc", floatType, null);
		SymbolTable table = new SymbolTable(topSymTable);
		table.put("Vector", var);
		GlobalVariable retrievedVar = (GlobalVariable) table.get("Vector");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("vector"));
	}

}
