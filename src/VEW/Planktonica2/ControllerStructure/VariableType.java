package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Common.XML.XMLTag;


public abstract class VariableType implements BuildFromXML {

	protected Catagory parentCatagory;
	private String name;
	private String desc;
	private int value;
	private int hist;
	private Collection<Unit> units;
	
	public VariableType(Catagory parentCatagory) {
		this.parentCatagory = parentCatagory;
	}
	
	@Override
	public BuildFromXML build (XMLTag tag) {
		
		varBuild(tag);
		
		return this;
	}
	
	protected void varBuild (XMLTag tag) {
		
		XMLTag nameTag = tag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
		}
		
		XMLTag descTag = tag.getTag(XMLTagEnum.DESCRIPTION.xmlTag());
		if (descTag != null) {
			this.desc = descTag.getValue();
		}
		
		XMLTag valueTag = tag.getTag(XMLTagEnum.VALUE.xmlTag());
		if (valueTag != null) {
			String v = valueTag.getValue();
			
			if (v != null) {
				this.value = Integer.valueOf(v);
			}
		}
		
		
		XMLTag histTag = tag.getTag(XMLTagEnum.HIST.xmlTag());
		if (histTag != null) {
			String v = histTag.getValue();
			
			if (v != null) {
				this.hist = Integer.valueOf(v);
			}
		}
		
		units = new ArrayList<Unit> ();
		XMLTag units = tag.getTag(XMLTagEnum.UNIT.xmlTag());
		if (units != null) {
			String unitString = units.getValue();
			if (unitString != null) {
				String [] individualUnits = unitString.split("(,)");
				// units are always in multiples of 3. checks this
				if (individualUnits.length % 3 == 0) {
					
					String [] unit = new String [3];
					int offset = 0;
					for (int i = 0; i < individualUnits.length ; i++) {
						
						unit[offset] = individualUnits[i];
						
						if (offset >= 2) {
							Unit u = new Unit (Integer.valueOf(unit[0]), unit[1], Integer.valueOf(unit[2]));
							this.units.add(u);
							offset = 0;
							unit = new String [3];
						}
						
						offset++;
					}				
				}
			}
		}
	}

	
	
	
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getHist() {
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
	
	
	
}
