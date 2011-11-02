package BACONCompiler.SemanticAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;

import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;
import VEW.XMLCompiler.ASTNodes.SymbolTable;
import VEW.XMLCompiler.ASTNodes.Type;
import VEW.XMLCompiler.ASTNodes.Variable;

public class createVariableTest {

	@Test
	public void makeFloat() {
		SymbolTable topSymTable = new SymbolTable();
		Type floatType = (Type) topSymTable.get("$float");
		Variable var = new Variable("TestVar", floatType);
		SymbolTable table = new SymbolTable(topSymTable);
		table.put("TestVar", var);
		Variable retrievedVar = (Variable) table.get("TestVar");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("float"));
	}
	
	@Test
	public void makeFoodSet() {
		SymbolTable topSymTable = new SymbolTable();
		Type floatType = (Type) topSymTable.get("$foodSet");
		Variable var = new Variable("TestSet", floatType);
		SymbolTable table = new SymbolTable(topSymTable);
		table.put("TestSet", var);
		Variable retrievedVar = (Variable) table.get("TestSet");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("foodSet"));
	}
	
	@Test
	public void makeVector() {
		SymbolTable topSymTable = new SymbolTable();
		Type floatType = (Type) topSymTable.get("$vector");
		Variable var = new Variable("Vector", floatType);
		SymbolTable table = new SymbolTable(topSymTable);
		table.put("Vector", var);
		Variable retrievedVar = (Variable) table.get("Vector");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("vector"));
	}

}
