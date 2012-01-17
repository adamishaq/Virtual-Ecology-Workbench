package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Common.XML.XMLTag;

public class StateVariable extends VariableType {

	private String codeName;
	
	public StateVariable() {
		super();
	}
	
	

	public StateVariable(String name, String desc, Type type,
			Collection<Unit> units, Float value, Integer hist, boolean editable) {
		super(name, desc, type, units, value, hist, editable);
	}

	public StateVariable(StateVariable v) {
		this.setName(v.getName());
		this.setDesc(v.getDesc());
		this.setVarType(v.getVarType());
		Collection<Unit> units = new ArrayList<Unit>();
		for (Unit u : v.getUnits()) {
			Unit new_u = new Unit(u.getSize(),u.getName(),u.getExponent());
			units.add(new_u);
		}
		this.setUnits(units);
		if (v.getValue() != null)
			this.setValue(v.getValue());
		else
			this.setValue(0);
		if (v.getHist() != null)
			this.setHist(v.getHist());
		else
			this.setHist(0);
	}



	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag varTag = super.buildToXML();
		if (codeName != null && varTag.getTag(XMLTagEnum.CODE_NAME.xmlTag()) == null) {
			varTag.addTag(XMLTagEnum.CODE_NAME.xmlTag(), codeName);
		}
		return varTag;
	}
	
	public String getCodeName() {
		return this.codeName;
	}
	
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}



	
	@Override
	protected String getVariableClassName() {
		return "variable";
	}
}
