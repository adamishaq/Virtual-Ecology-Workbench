package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Iterator;

import VEW.Common.XML.XMLTag;

public class Spectrum implements BuildFromXML, BuildToXML, Iterable<WaveLengthIntensityPair> {
	
	protected String name;
	protected ArrayList<WaveLengthIntensityPair> values;
	protected final String GRAPH_VALUES = "\\graphvals";
	private XMLTag baseTag;
	private boolean hasPigment;
	

	public Spectrum(Spectrum s) {
		this.name = s.getName();
		ArrayList<WaveLengthIntensityPair> vals = new ArrayList<WaveLengthIntensityPair>();
		for (WaveLengthIntensityPair w : s.values) {
			vals.add(new WaveLengthIntensityPair(w));
		}
		this.values = vals;
		this.baseTag = s.baseTag;
		this.hasPigment = s.hasPigment;
	}

	public Spectrum() {}

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
					
					this.values = new ArrayList<WaveLengthIntensityPair> ();
					int wavelength = 0;
					for (int i = 1; i < valueArray.length; i+=2, wavelength++) {
						Double intensity = Double.valueOf(valueArray[i]);
						this.values.add(new WaveLengthIntensityPair(wavelength, intensity));
					}					
				}
			}
		}
		equationTag.removeFromParent();
		
		return this;
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
		for (int n = 0; n < values.size(); n++) {
			graphValsString += "{" + n + "," + values.get(n).getIntensity() + "}";
			if (n != values.size() - 1) {
				graphValsString += ",";
			}
		}
		return graphValsString + "}";
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

	

	public boolean hasPigment() {
		return hasPigment;
	}

	public void setHasPigment(boolean hasPigment) {
		this.hasPigment = hasPigment;
	}

	@Override
	public Iterator<WaveLengthIntensityPair> iterator() {
		return values.iterator();
	}
	
}
