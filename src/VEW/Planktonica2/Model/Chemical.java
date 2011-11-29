package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

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
	
	public Chemical(String name, String filePath) {
		super();
		this.file_path = filePath;
		this.name = name;
	}

	@Override
	public BuildFromXML build(XMLTag tag) {
		baseTag = tag;
		// name
		XMLTag nameTag = tag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
			nameTag.removeFromParent();
		}
		
		// value
		XMLTag valueTag = tag.getTag(XMLTagEnum.VALUE.xmlTag());
		if (valueTag != null) {
			this.value = valueTag.getValue();
			valueTag.removeFromParent();
		}		
		
		
		XMLTag [] spectra = tag.getTags(XMLTagEnum.SPECTRUM.xmlTag());
		this.spectrum = new ArrayList<Spectrum> (spectra.length);
		for (XMLTag t : spectra) {
			Spectrum s = new Spectrum ();
			s.build(t);
			this.spectrum.add(s);
			t.removeFromParent();

		}
		
		// pigment
		XMLTag pigmentValue = tag.getTag(XMLTagEnum.PIGMENT.xmlTag());
		if (pigmentValue != null) {
			this.setPigment(Boolean.valueOf(pigmentValue.getValue()));
			pigmentValue.removeFromParent();
		}
		
		// functions
		XMLTag [] tags = tag.getTags(XMLTagEnum.FUNCTION.xmlTag());
		this.functions = new ArrayList<Function> (tags.length);

		for (XMLTag t : tags) {
			Function f = new Function (file_path, this);
			f.build(t);
			functions.add(f);
			t.removeFromParent();
		}

		// parameters
		tags = tag.getTags(XMLTagEnum.PARAMETER.xmlTag());

		for (XMLTag t : tags) {
			Parameter p = new Parameter();
			p.build(t);
			paramTable.put(p.getName(), p);
			t.removeFromParent();
		}

		// local
		tags = tag.getTags(XMLTagEnum.LOCAL.xmlTag());

		for (XMLTag t : tags) {
			Local l = new Local();
			l.build(t);
			localVarTable.put(l.getName(), l);
			t.removeFromParent();
		}

		// variables
		tags = tag.getTags(XMLTagEnum.STATE_VARIABLE.xmlTag());

		for (XMLTag t : tags) {
			StateVariable v = new StateVariable();
			v.build(t);
			stateVarTable.put(v.getName(), v);
			t.removeFromParent();
		}
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		tables.addChemical(name);

		return this;
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		super.buildToXML();
		baseTag.setName("chemical");
		baseTag.addTag("pigment", pigment);
		Iterator<Spectrum> spectrumIter = spectrum.iterator();
		while (spectrumIter.hasNext()) {
			Spectrum spectrum = spectrumIter.next();
			baseTag.addTag(spectrum.buildToXML());
		}
		return baseTag;
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
		for (Spectrum s : spectrum) {
			s.setHasPigment(pigment);
		}
	}

	
	public Collection<Spectrum> getSpectrum() {
		return spectrum;
	}
	
	public Spectrum getSpectrum(String name) {
		
		for (Spectrum s : this.getSpectrum()) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		
		return new NullSpectrum();
		
	}

	public void setSpectrum(Collection<Spectrum> spectrum) {
		this.spectrum = spectrum;
	}

	

}
