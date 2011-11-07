package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Common.XML.XMLTag;

public class Chemical extends Catagory implements BuildFromXML {

	private String name;
	private String value;
	
	private boolean pigment;
	private Collection<Spectrum> spectrum;
	
	private Collection<Function> functions;
	private Collection<StateVariable> variables;
	private Collection<Parameter> parameters;
	private Collection<Local> locals;
	

	
	public Chemical() {
		super();
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
			Function f = new Function ();
			f.build(t);
			functions.add(f);
		}

		// parameters
		tags = tag.getTags(XMLTagEnum.PARAMETER.xmlTag());
		this.parameters = new ArrayList<Parameter> (tags.length);

		for (XMLTag t : tags) {
			Parameter p = new Parameter(this);
			p.build(t);
			paramTable.put(p.getName(), p);
		}

		// local
		tags = tag.getTags(XMLTagEnum.LOCAL.xmlTag());
		this.locals = new ArrayList<Local> (tags.length);

		for (XMLTag t : tags) {
			Local l = new Local(this);
			l.build(t);
			localVarTable.put(l.getName(), l);
		}

		// variables
		tags = tag.getTags(XMLTagEnum.VARIABLE.xmlTag());
		this.variables = new ArrayList<StateVariable> (tags.length);

		for (XMLTag t : tags) {
			StateVariable v = new StateVariable(this);
			v.build(t);
			stateVarTable.put(v.getName(), v);
		}


		return this;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isPigment() {
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

	public Collection<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(Collection<Function> functions) {
		this.functions = functions;
	}

	public Collection<StateVariable> getVariables() {
		return variables;
	}

	public void setVariables(Collection<StateVariable> variables) {
		this.variables = variables;
	}

	public Collection<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(Collection<Parameter> parameters) {
		this.parameters = parameters;
	}

	public Collection<Local> getLocals() {
		return locals;
	}

	public void setLocals(Collection<Local> locals) {
		this.locals = locals;
	}

	
	
}
