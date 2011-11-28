package VEW.Planktonica2.Model;

import java.util.Collection;

public class GlobalVariable extends VariableType {

	public GlobalVariable() {
		super();
	}

	public GlobalVariable(String name, String desc, Type type,
			Collection<Unit> units, Float value, Integer hist, boolean editable) {
		super(name, desc, type, units, value, hist, editable);
	}



}
