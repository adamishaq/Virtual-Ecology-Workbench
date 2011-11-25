package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import VEW.Common.XML.XMLTag;

public class Chemical extends Catagory {

	private String value;
	
	private boolean pigment;
	private Collection<Spectrum> spectrum;
	
	private ArrayList<Function> functions;
	
	private String file_path;
	
	
	public Chemical(String _file_path) {
		super();
		this.file_path = _file_path;
	}
	
	@Override
	public BuildFromXML build(XMLTag tag) {
		
		// name
		XMLTag nameTag = tag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
		}
		
		// value
		XMLTag valueTag = tag.getTag(XMLTagEnum.VALUE.xmlTag());
		if (valueTag != null) {
			this.value = valueTag.getValue();
		}
		
		// pigment
		XMLTag pigmentValue = tag.getTag(XMLTagEnum.PIGMENT.xmlTag());
		if (pigmentValue != null) {
			this.pigment = Boolean.valueOf(pigmentValue.getValue());
		}
		
		
		
		XMLTag [] spectra = tag.getTags(XMLTagEnum.SPECTRUM.xmlTag());
		this.spectrum = new ArrayList<Spectrum> (spectra.length);
		for (XMLTag t : spectra) {
			Spectrum s = new Spectrum ();
			s.build(t);
			this.spectrum.add(s);

		}
		
		
		
		// functions
		XMLTag [] tags = tag.getTags(XMLTagEnum.FUNCTION.xmlTag());
		this.functions = new ArrayList<Function> (tags.length);

		for (XMLTag t : tags) {
			Function f = new Function (file_path, this.name);
			f.build(t);
			functions.add(f);
		}

		// parameters
		tags = tag.getTags(XMLTagEnum.PARAMETER.xmlTag());

		for (XMLTag t : tags) {
			Parameter p = new Parameter(this);
			p.build(t);
			paramTable.put(p.getName(), p);
		}

		// local
		tags = tag.getTags(XMLTagEnum.LOCAL.xmlTag());

		for (XMLTag t : tags) {
			Local l = new Local(this);
			l.build(t);
			localVarTable.put(l.getName(), l);
		}

		// variables
		tags = tag.getTags(XMLTagEnum.VARIABLE.xmlTag());

		for (XMLTag t : tags) {
			StateVariable v = new StateVariable(this);
			v.build(t);
			stateVarTable.put(v.getName(), v);
		}


		return this;
	}
	
	@Override
	public XMLTag buildToXML() {
		XMLTag chemicalTag = super.buildToXML();
		chemicalTag.setName("chemical");
		chemicalTag.addTag("pigment", pigment);
		Iterator<Spectrum> spectrumIter = spectrum.iterator();
		while (spectrumIter.hasNext()) {
			Spectrum spectrum = spectrumIter.next();
			chemicalTag.addTag(spectrum.buildToXML());
		}
		return chemicalTag;
	}

		
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean hasPigment() {
		return pigment;
	}

	public void setPigment(boolean pigment) {
		this.pigment = pigment;
	}

	public Collection<Spectrum> getSpectrum() {
		return spectrum;
	}

	public void setSpectrum(Collection<Spectrum> spectrum) {
		this.spectrum = spectrum;
	}

}
