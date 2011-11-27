package BACONCompiler.SemanticAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;

import VEW.XMLCompiler.ASTNodes.SymbolTable;

public class OuterSymbolTableTest {

	@Test
	public void testOuterConstruct() {
		SymbolTable<Object> outerTable = new SymbolTable<Object>();
		SymbolTable<Object> innerTable = new SymbolTable<Object>(outerTable);
		outerTable.put("Test", new Integer(5));
		Object obj = innerTable.get("Test");
		assertTrue(obj == null);
		obj = innerTable.checkOuterTables("Test");
		assertTrue(obj != null);
		Integer m = (Integer) obj;
		assertTrue(m.intValue() == 5);
	}
	
	@Test
	public void testOuterNoConstruct() {
		SymbolTable<Integer> outerTable = new SymbolTable<Integer>();
		SymbolTable<Integer> innerTable = new SymbolTable<Integer>();
		innerTable.setOuterTable(outerTable);
		outerTable.put("Test", new Integer(5));
		Object obj = innerTable.get("Test");
		assertTrue(obj == null);
		obj = innerTable.checkOuterTables("Test");
		assertTrue(obj != null);
		Integer m = (Integer) obj;
		assertTrue(m.intValue() == 5);
	}
	
	@Test
	public void testItemInInner() {
		SymbolTable<?> outerTable = new SymbolTable<Object>();
		SymbolTable<Integer> innerTable = new SymbolTable<Integer>(outerTable);
		innerTable.put("Test", new Integer(5));
		Object obj = outerTable.get("Test");
		assertTrue(obj == null);
	}
	

}
