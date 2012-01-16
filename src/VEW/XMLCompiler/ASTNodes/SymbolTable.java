package VEW.XMLCompiler.ASTNodes;

import java.util.HashMap;

/**
 * A hash table extension allowing for tiers of tables and storage of objects with string keys. Created
 * with purpose of matching identifiers to objects.
 * @author David Coulden
 *
 * @param <V> The type of object the symbol table will store
 */
public class SymbolTable <V> extends HashMap<String, V>{
	
	private static final long serialVersionUID = 2439614476143080597L;

	private SymbolTable<?> outerTable;
	
	public SymbolTable() {
		outerTable = null;
	}
	
	public SymbolTable(SymbolTable<?> _outerTable) {
		outerTable = _outerTable;
	}

	/**
	 * Checks the current symbol table for key and if not found checks in its parent, etc, until object is
	 * found or table root is reached
	 * @param key The string used for lookups
	 * @return The object if found
	 */
	public Object checkOuterTables(String key) {
		Object val = get(key);
		if (val == null && outerTable != null) {
			val = outerTable.checkOuterTables(key);
		}
		return val;
	}

	public void setOuterTable(SymbolTable<V> _outerTable) {
		outerTable = _outerTable;
	}
	
	public String toString() {
		String result = "";
		if (outerTable != null) {
			result += outerTable.toString();
		}
		return result += super.toString() + '\n';
	}
}
