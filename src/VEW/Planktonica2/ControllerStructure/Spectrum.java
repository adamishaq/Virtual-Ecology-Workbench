package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import VEW.Common.XML.XMLTag;

public class Spectrum implements BuildFromXML {
	
	private String name;
	private ArrayList<Float> equations;
	private final String GRAPH_VALUES = "\\graphvals";  

	@Override
	public BuildFromXML build(XMLTag tag) {
		
		XMLTag nameTag = tag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
		}
		
		
		// comes in the form \graphvals{{0, v1}, {1, v2}, ...}
		XMLTag spectraTag = tag.getTag(XMLTagEnum.GRAPH_VAL.xmlTag());
		if (spectraTag != null) {
			String s = spectraTag.getValue();
			if (s != null) {
				// checks that the string starts with string == GRAPH_VALUES
				if (s.startsWith(GRAPH_VALUES)) {
					// removes surrounding "\graphvals{values}", leaving just values
					String values = s.substring(s.indexOf("{")+1, s.lastIndexOf("}")-1);
					// splits values around "," so of form ["{0, v1}", "{1, v2}", ...]
					String [] valueArray = values.split(",");
					
					// passes into get values
					this.equations = getSpectraValues (valueArray);
					
				}
			}
		}
		
		return this;
	}

	/**
	 * Gets an array of the form ["{0, v1}", "{1, v2}", ...] and returns the values
	 * 
	 * @param valueArray array of form ["{0, v1}", "{1, v2}", ...]
	 * @return an ArrayList<Float> == [v1, v2, v3, ...]
	 */
	private ArrayList<Float> getSpectraValues(String [] valueArray) {
		
		ArrayList<Float> returnArray = new ArrayList<Float>(valueArray.length);
		
		for (String s : valueArray) {
			String values = s.substring(s.indexOf("{")+1, s.lastIndexOf("}")-1);
			String [] array = values.split(",");
			if (array.length >= 2) {
				returnArray.add(Float.valueOf(array[1]));
			}
		}
		
		return returnArray;
		
		
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Float> getEquations() {
		return equations;
	}

	public void setEquations(ArrayList<Float> equations) {
		this.equations = equations;
	}
	
	
	
}
