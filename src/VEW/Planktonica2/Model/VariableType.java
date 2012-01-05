package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;
import VEW.XMLCompiler.DependencyChecker.HasDependency;


public abstract class VariableType implements BuildFromXML, BuildToXML, HasDependency {

	private String name;
	private String desc;
	private Float value;
	private Type type;
	private Integer hist;
	private Collection<Unit> units;
	private boolean assignedTo;
	private boolean readFrom;
	private boolean editable;
	private boolean xmlInclude;
	protected XMLTag tag;
	
	public VariableType() {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		initialiseVariable("", "", floatType, null, null, null, true);
		editable = true;
	}
	
	public VariableType(String name, String desc, Type type, Collection<Unit> units, Float value,
								Integer hist, boolean editable) {
		initialiseVariable(name, desc, type, units, value, hist, editable);
	}
	
	private void initialiseVariable(String name, String desc, Type type, Collection<Unit> units, Float value,
										Integer hist, boolean editable) {
		this.assignedTo = false;
		this.readFrom = false;
		this.name = name;
		this.desc = desc;
		this.type = type;
		this.units = units;
		this.editable = editable;
		this.value = value;
		this.hist = hist;
		xmlInclude = true;
	}
	
	
	@Override
	public BuildFromXML build (XMLTag tag) {
		
		this.tag = tag;
		
		XMLTag nameTag = tag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
			nameTag.removeFromParent();
		}
		
		XMLTag descTag = tag.getTag(XMLTagEnum.DESCRIPTION.xmlTag());
		if (descTag != null) {
			this.desc = descTag.getValue();
			descTag.removeFromParent();
		}
		
		XMLTag valueTag = tag.getTag(XMLTagEnum.VALUE.xmlTag());
		if (valueTag != null) {
			String v = valueTag.getValue();
			
			if (v != null) {
				this.value = Float.valueOf(v);
			}
			valueTag.removeFromParent();
		}
		
		
		XMLTag histTag = tag.getTag(XMLTagEnum.HIST.xmlTag());
		if (histTag != null) {
			String v = histTag.getValue();
			
			if (v != null) {
				this.hist = Integer.valueOf(v);
			}
			histTag.removeFromParent();
		}
		
		buildUnits(tag);
		
		return this;
	}
	
	protected void buildUnits (XMLTag tag) {
		
		
		
		units = new ArrayList<Unit> ();
		XMLTag units = tag.getTag(XMLTagEnum.UNIT.xmlTag());
		if (units != null) {
			String unitString = units.getValue();
			constructUnits(unitString);
			units.removeFromParent();
		}
	}

	private void constructUnits(String unitString) {
		if (unitString != null) {
			String [] individualUnits = unitString.split("(,)");
			// units are always in multiples of 3. checks this
			if (individualUnits.length % 3 == 0) {
				
				String [] unit = new String [3];
				int offset = 0;
				for (int i = 0; i < individualUnits.length ; i++) {
					
					unit[offset] = individualUnits[i];
					
					if (offset >= 2) {
						/*
						 * Checks for null on broken models, because some units seem to have
						 * a null size value and possibly a null exponent value.
						 */
						if (unit[0].equals("null")) {
							unit[0] = "0";
						} else if (unit[2].equals("null")) {
							unit[2] = "0";
						}
						Unit u = new Unit (Integer.valueOf(unit[0]), unit[1], Integer.valueOf(unit[2]));
						this.units.add(u);
						offset = 0;
						unit = new String [3];
						
					} else {
						offset++;
					}
					
				}				
			}
		}
	}

	public XMLTag buildToXML() throws XMLWriteBackException {
		XMLTag newTag = new XMLTag("placeholder");
		if (tag != null)
			newTag.addTags(newTag.getTags());
		newTag.addTag(new XMLTag("name", name));
		if (desc != null)
			newTag.addTag(new XMLTag("desc", desc));
		if (value != null)
			newTag.addTag(new XMLTag("value", Float.toString(value)));
		if (hist != null)
			newTag.addTag(new XMLTag("hist", Integer.toString(hist)));
		buildUnitXML(newTag);
		newTag.setName(this.getVariableClassName());
		return newTag;
	}
	
	/**
	 * Returns the name in the XML associated with the type of variable.
	 * e.g. Local returns "local", Parameter returns "parameter"
	 * @return the name corresponding to the type of the variable
	 */
	protected abstract String getVariableClassName();

	private void buildUnitXML(XMLTag tag) {
		String unitString = "";
		Iterator<Unit> unitIter = units.iterator();
		while(unitIter.hasNext()) {
			Unit u = unitIter.next();
			unitString += u.toString() + ",";
		}
		unitString = unitString.substring(0, unitString.length()-1);
		tag.addTag(new XMLTag("unit", unitString));
	}

	

	/*
	 * Getters and setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Integer getHist() {
		return hist;
	}

	public void setHist(int hist) {
		this.hist = hist;
	}

	public Collection<Unit> getUnits() {
		return units;
	}

	public void setUnits(Collection<Unit> units) {
		this.units = units;
	}
	
	public Type getVarType() {
		return type;
	}
	
	public void setVarType(Type type) {
		this.type = type;
	}
	
	public boolean isAssignedTo() {
		return assignedTo;
	}
	
	public void setAssigned(boolean assigned) {
		assignedTo = assigned;
	}
	
	public boolean isReadFrom() {
		return readFrom;
	}
	
	public void setRead(boolean read) {
		readFrom = read;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public String get_formatted_units() {
		String units = "";
		for (Unit u : this.getUnits()) {
			if (u.getName().equals("dimensionless") || u.getName().equals("null") || u.getExponent() == 0)
				return "dimensionless";
			String exp = "";
			if (u.getExponent() != 1)
				exp = "^{" + u.getExponent() + "}";
			units += u.getName() + exp;
		}
		return units;
	}
	
	public boolean includedInXML() {
		return xmlInclude;
	}
	
	public void setIncludeInXML(boolean include) {
		xmlInclude = include;
	}
	
	
}
