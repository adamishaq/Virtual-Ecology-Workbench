package VEW.Planktonica2.model;

import java.util.ArrayList;
import VEW.Common.XML.XMLTag;

public class Spectrum implements BuildFromXML, BuildToXML {
	
	private String name;
	private ArrayList<Float> equations;
	private final String GRAPH_VALUES = "\\graphvals";
	private XMLTag baseTag;

	@Override
	public BuildFromXML build(XMLTag tag) {
		baseTag = tag;
		XMLTag nameTag = tag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
			nameTag.removeFromParent();
		}
		
		
		// comes in the form \graphvals{{0, v1}, {1, v2}, ...}
		XMLTag equationTag = tag.getTag(XMLTagEnum.EQUATION.xmlTag());
		XMLTag spectraTag = equationTag.getTag(XMLTagEnum.GRAPH_VAL.xmlTag());
		if (spectraTag != null) {
			String s = spectraTag.getValue();
			if (s != null) {
				// checks that the string starts with string == GRAPH_VALUES
				if (s.startsWith(GRAPH_VALUES)) {
					// removes surrounding "\graphvals{values}", leaving just values
					String values = s.substring(s.indexOf("{")+1, s.lastIndexOf("}"));
					
					values = values.replaceAll("(\\{)", "");
					
					values = values.replaceAll("(\\})", "");					
					
					// splits values around "," so of form ["0", "v1", "1", "v2", ...]
					String [] valueArray = values.split(",");
					
					this.equations = new ArrayList<Float> ();
					for (int i = 1; i < valueArray.length; i+=2) {
						equations.add(Float.valueOf(valueArray[i]));
					}					
				}
			}
		}
		equationTag.removeFromParent();
		
		return this;
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


	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		if (baseTag == null) {
			baseTag = new XMLTag("spectrum");
		}
		baseTag.addTag("name", name);
		XMLTag eqTag = buildGraphValuesXML();
		baseTag.addTag(eqTag);
		return baseTag;
	}


	private XMLTag buildGraphValuesXML() {
		XMLTag eqTag = new XMLTag("equation");
		eqTag.addTag("name", name);
		eqTag.addTag("eq", buildGraphValuesString());
		return eqTag;
	}


	private String buildGraphValuesString() {
		String graphValsString = GRAPH_VALUES + "{";
		for (int n = 0; n < equations.size(); n++) {
			graphValsString += "{" + n + "," + equations.get(n) + "}";
			if (n != equations.size() - 1) {
				graphValsString += ",";
			}
		}
		return graphValsString + "}";
	}
	
	
	
}
