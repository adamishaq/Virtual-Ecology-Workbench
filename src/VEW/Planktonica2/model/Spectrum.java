package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Iterator;

import VEW.Common.XML.XMLTag;

public class Spectrum implements BuildFromXML, BuildToXML, Iterable<WaveLengthIntensityPair> {
	
	protected String name;

	
	protected ArrayList<WaveLengthIntensityPair> values;
	protected final String GRAPH_VALUES = "\\graphvals";

	@Override
	public BuildFromXML build(XMLTag tag) {
		
		XMLTag nameTag = tag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
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
					
					this.values = new ArrayList<WaveLengthIntensityPair> ();
					int wavelength = 0;
					for (int i = 1; i < valueArray.length; i+=2, wavelength++) {
						Double intensity = Double.valueOf(valueArray[i]);
						this.values.add(new WaveLengthIntensityPair(wavelength, intensity));
					}					
				}
			}
		}
		
		return this;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WaveLengthIntensityPair getSpectrumValue(int index) {
		return values.get(index);
	}

	public void setValue(int index, double intensity) {
		values.set(index, new WaveLengthIntensityPair(index, intensity));
	}

	public int getNumberValues() {
		return values.size();
	}

	@Override
	public XMLTag buildToXML() {
		XMLTag spectrumTag = new XMLTag("spectrum");
		spectrumTag.addTag("name", name);
		XMLTag eqTag = buildGraphValuesXML();
		spectrumTag.addTag(eqTag);
		return spectrumTag;
	}


	private XMLTag buildGraphValuesXML() {
		XMLTag eqTag = new XMLTag("equation");
		eqTag.addTag("name", name);
		eqTag.addTag("eq", buildGraphValuesString());
		return eqTag;
	}


	private String buildGraphValuesString() {
		String graphValsString = GRAPH_VALUES + "{";
		for (int n = 0; n < values.size(); n++) {
			graphValsString += "{" + n + "," + values.get(n) + "}";
			if (n != values.size() - 1) {
				graphValsString += ",";
			}
		}
		return graphValsString + "}";
	}


	@Override
	public Iterator<WaveLengthIntensityPair> iterator() {
		return values.iterator();
	}
	
	
	
}
