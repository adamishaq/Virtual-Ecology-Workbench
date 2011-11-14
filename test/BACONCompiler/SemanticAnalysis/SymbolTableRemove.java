package BACONCompiler.SemanticAnalysis;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import VEW.XMLCompiler.ASTNodes.SymbolTable;

public class SymbolTableRemove {

	@Test
	public void test() {
		SymbolTable symTab = new SymbolTable();
		symTab.put("Test", new Integer(10));
		Integer m = (Integer) symTab.remove("Test");
		assertTrue(m != null);
		Integer n = (Integer) symTab.get("Test");
		assertTrue(n == null);
	}

}
