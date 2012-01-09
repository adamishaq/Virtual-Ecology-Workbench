package VEW.Planktonica2.Model;

import java.util.ArrayList;
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

	public VarietyVariable(VarietyVariable v) {
		super(v.parentCatagory);
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
		this.setHist(v.getHist());
		this.setLinkConcentration(v.getLinkConcentration());
	}


	@Override
	protected String getVariableClassName() {
		return "varietyvariable";
	}

	
}
