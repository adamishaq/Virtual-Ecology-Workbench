package VEW.Planktonica2.Model;

import java.util.Collection;

public class VarietyConcentration extends VariableType {

	

	public VarietyConcentration(String name, String desc, Type type,
			Collection<Unit> units, Float value, Integer hist, boolean editable) {
		super(name, desc, type, units, value, hist, editable);
	}

	public VarietyConcentration() {
		super();
	}
	

	@Override
	protected String getVariableClassName() {
		return "varietyconcentration";
	}

	
	
}
