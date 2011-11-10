package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Common.XML.XMLTag;

public class FunctionalGroup implements BuildFromXML {
	
	private String name;
	private boolean invisible;
	
	private ArrayList <Function> functions;
	
	private Collection <Parameter> parameters;
	private Collection <Local> locals;
	private Collection <Variable> variables;
	
	private Collection <VarietyConcentration> varietyConcentrations;
	private Collection <VarietyParameter> varietyParameters;
	private Collection <VarietyVariable> varietyVariables;
	private Collection <VarietyLocal> varietyLocals;
	
	private ArrayList <Stage> stages;

	public FunctionalGroup () {
		invisible = true;
		functions  = new ArrayList<Function> ();
		stages = new ArrayList<Stage> ();

		parameters = new ArrayList<Parameter> ();
		locals = new ArrayList<Local> ();
		variables = new ArrayList<Variable> ();
		
		varietyConcentrations = new ArrayList<VarietyConcentration> ();
		varietyParameters = new ArrayList<VarietyParameter> ();
		varietyVariables = new ArrayList<VarietyVariable> ();
		varietyLocals = new ArrayList<VarietyLocal> ();
		
	}
	
	
	@Override
	public BuildFromXML build(XMLTag xmlTag) {
		
		// name
		XMLTag nameTag = xmlTag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
		}
		
		// invis
		XMLTag invisValue = xmlTag.getTag(XMLTagEnum.INVISIBLE.xmlTag());
		if (invisValue != null) {
			this.invisible = Boolean.valueOf(invisValue.getValue());
		}
		
		// stages
		XMLTag [] tags = xmlTag.getTags(XMLTagEnum.STAGE.xmlTag());
		
		for (XMLTag t : tags) {
			Stage s = new Stage ();
			s.build(t);
			stages.add(s);
		}
		
		// functions
		tags = xmlTag.getTags(XMLTagEnum.FUNCTION.xmlTag());
		
		for (XMLTag t : tags) {
			Function f = new Function (stages);
			f.build(t);
			functions.add(f);
		}
		
		// parameters
		tags = xmlTag.getTags(XMLTagEnum.PARAMETER.xmlTag());
		
		for (XMLTag t : tags) {
			Parameter p = new Parameter();
			p.build(t);
			parameters.add(p);
		}
		
		// local
		tags = xmlTag.getTags(XMLTagEnum.LOCAL.xmlTag());
		
		for (XMLTag t : tags) {
			Local l = new Local();
			l.build(t);
			locals.add(l);
		}
		
		// variables
		tags = xmlTag.getTags(XMLTagEnum.VARIABLE.xmlTag());
		
		for (XMLTag t : tags) {
			Variable v = new Variable();
			v.build(t);
			variables.add(v);
		}
		
		// variety concentration
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_CONCENTRATION.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyConcentration vc = new VarietyConcentration();
			vc.build(t);
			varietyConcentrations.add(vc);
		}
		
		// variety variable
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_VARIABLE.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyVariable vv = new VarietyVariable(this.varietyConcentrations);
			vv.build(t);
			varietyVariables.add(vv);
		}
		
		// variety locals
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_LOCAL.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyLocal vl = new VarietyLocal(this.varietyConcentrations);
			vl.build(t);
			varietyLocals.add(vl);
		}
		
		// variety parameter
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_PARAM.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyParameter vp = new VarietyParameter(this.varietyConcentrations);
			vp.build(t);
			varietyParameters.add(vp);
		}
		
		
		return this;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInvisible() {
		return invisible;
	}

	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}

	public ArrayList<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(ArrayList<Function> functions) {
		this.functions = functions;
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

	public Collection<Variable> getVariables() {
		return variables;
	}

	public void setVariables(Collection<Variable> variables) {
		this.variables = variables;
	}

	public Collection<VarietyConcentration> getVarietyConcentrations() {
		return varietyConcentrations;
	}

	public void setVarietyConcentrations(
			Collection<VarietyConcentration> varietyConcentrations) {
		this.varietyConcentrations = varietyConcentrations;
	}

	public Collection<VarietyParameter> getVarietyParameters() {
		return varietyParameters;
	}

	public void setVarietyParameters(Collection<VarietyParameter> varietyParameters) {
		this.varietyParameters = varietyParameters;
	}

	public Collection<VarietyVariable> getVarietyVariables() {
		return varietyVariables;
	}

	public void setVarietyVariables(Collection<VarietyVariable> varietyVariables) {
		this.varietyVariables = varietyVariables;
	}

	public Collection<VarietyLocal> getVarietyLocals() {
		return varietyLocals;
	}

	public void setVarietyLocals(Collection<VarietyLocal> varietyLocals) {
		this.varietyLocals = varietyLocals;
	}

	public ArrayList<Stage> getStages() {
		return stages;
	}

	public void setStages(ArrayList<Stage> stages) {
		this.stages = stages;
	}



	
	public Function getFunctionAtIndex(int functionNo) {
		return functions.get(functionNo);
	}



	
	public Stage getStageAtIndex(int stageNo) {
		return this.stages.get(stageNo);
	}



	
	public int getNoStages() {
		return stages.size();
	}
	
	public int getNoFunctions() {
		return functions.size();
	}
	
}
