package VEW.Planktonica2.Model;

import java.util.Collection;

public class VarietyVariable extends Variety {

	public VarietyVariable(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	
	






	public VarietyVariable(String name, String desc, Type type,
			Collection<Unit> units, Float value, Integer hist,
			boolean editable, VarietyConcentration link) {
		super(name, desc, type, units, value, hist, editable, link);
	}








	@Override
	protected String getVariableClassName() {
		return "varietyvariable";
	}

	
}
