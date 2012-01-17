package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;


public class Local extends VariableType {
	public Local() {
		super();
	}
	
	public Local(Local v) {
		this.setName(v.getName());
		this.setDesc(v.getDesc());
		this.setVarType(v.getVarType());
		Collection<Unit> units = new ArrayList<Unit>();
		for (Unit u : v.getUnits()) {
			Unit new_u = new Unit(u.getSize(),u.getName(),u.getExponent());
			units.add(new_u);
		}
		this.setUnits(units);
	}
	
	@Override
	protected String getVariableClassName() {
		return "local";
	}
}
