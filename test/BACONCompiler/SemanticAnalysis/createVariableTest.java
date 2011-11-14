package BACONCompiler.SemanticAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;

import VEW.Planktonica2.model.Type;
import VEW.XMLCompiler.ASTNodes.SymbolTable;
import VEW.XMLCompiler.ASTNodes.Variable;

public class createVariableTest {

	@Test
	public void makeFloat() {
		SymbolTable<Type> topSymTable = new SymbolTable<Type>();
		topSymTable.put("$float", new Type("float"));
		Type floatType = topSymTable.get("$float");
		Variable var = new Variable("TestVar", floatType);
		SymbolTable<Variable> table = new SymbolTable<Variable>(topSymTable);
		table.put("TestVar", var);
		Variable retrievedVar = (Variable) table.get("TestVar");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("float"));
	}
	
	@Test
	public void makeFoodSet() {
		SymbolTable<Type> topSymTable = new SymbolTable<Type>();
		topSymTable.put("$foodSet", new Type("foodSet"));
		Type floatType = (Type) topSymTable.get("$foodSet");
		Variable var = new Variable("TestSet", floatType);
		SymbolTable<Variable> table = new SymbolTable<Variable>(topSymTable);
		table.put("TestSet", var);
		Variable retrievedVar = (Variable) table.get("TestSet");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("foodSet"));
	}
	
	@Test
	public void makeVector() {
		SymbolTable<Type> topSymTable = new SymbolTable<Type>();
		topSymTable.put("$vector", new Type("vector"));
		Type floatType = (Type) topSymTable.get("$vector");
		Variable var = new Variable("Vector", floatType);
		SymbolTable<Variable> table = new SymbolTable<Variable>(topSymTable);
		table.put("Vector", var);
		Variable retrievedVar = (Variable) table.get("Vector");
		Type type = retrievedVar.getVarType();
		assertTrue(type.getName().equals("vector"));
	}

}
