package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;

public class Parameter extends VariableType {

	public Parameter() {
		super();
	}

	public Parameter(Parameter v) {
		this.setName(v.getName());
		this.setDesc(v.getDesc());
		this.setVarType(v.getVarType());
		Collection<Unit> units = new ArrayList<Unit>();
		for (Unit u : v.getUnits()) {
			Unit new_u = new Unit(u.getSize(),u.getName(),u.getExponent());
			units.add(new_u);
		}
		this.setUnits(units);
		this.setValue(v.getValue());
	}

	@Override
	protected String getVariableClassName() {
		return "parameter";
	}

	
}
