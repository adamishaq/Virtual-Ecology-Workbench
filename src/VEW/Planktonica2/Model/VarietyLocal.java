package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;

public class VarietyLocal extends Variety {

	public VarietyLocal(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	

	public VarietyLocal(VarietyLocal v) {
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
		this.setLinkConcentration(v.getLinkConcentration());
	}


	@Override
	protected String getVariableClassName() {
		return "varietylocal";
	}

	
}
