package VEW.Planktonica2.Model;

import java.util.Collection;

public class GlobalVariable extends VariableType {

	private String writeBackName;
	
	public GlobalVariable() {
		super();
		writeBackName = null;
	}

	public GlobalVariable(String name, String desc, Type type,
			Collection<Unit> units, Float value, Integer hist, boolean editable) {
		super(name, desc, type, units, value, hist, editable);
		writeBackName = null;
	}

	
	@Override
	protected String getVariableClassName() {
		return "";
	}

	public String getWriteBackName() {
		return writeBackName;
	}

	public void setWriteBackName(String writeBackName) {
		this.writeBackName = writeBackName;
	}



}
