package VEW.XMLCompiler.ASTNodes;

import java.util.HashMap;

@SuppressWarnings("serial")
public class SymbolTable extends HashMap<String, Object>{
	
	private SymbolTable outerTable;
	
	public SymbolTable() {
		outerTable = null;
	}
	
	public SymbolTable(SymbolTable _outerTable) {
		outerTable = _outerTable;
	}

	public Object checkOuterTables(String key) {
		Object val = get(key);
		if (val == null && outerTable != null) {
			val = outerTable.checkOuterTables(key);
		}
		return val;
	}

	public void setOuterTable(SymbolTable _outerTable) {
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
