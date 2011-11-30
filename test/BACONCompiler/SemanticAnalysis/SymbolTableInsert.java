package BACONCompiler.SemanticAnalysis;
import static org.junit.Assert.*;
import VEW.XMLCompiler.ASTNodes.SymbolTable;

import org.junit.Test;


public class SymbolTableInsert {

	@Test
	public void addItemToSymbolTable() {
		SymbolTable<Integer> table = new SymbolTable<Integer>();
		table.put("Key", new Integer(5));
		Integer n = (Integer) table.get("Key");
		int m = n.intValue();
		assertTrue(m == 5);
	}

}
