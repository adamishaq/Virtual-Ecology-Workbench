package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;
import VEW.XMLCompiler.ASTNodes.SymbolTable;

public class FunctionalGroup extends Catagory {
	

	private boolean invisible;
	private String file_path;
	
	
	private SymbolTable<Stage> stageTable;

	public FunctionalGroup (String file_path) {
		super();
		invisible = true;
		this.file_path = file_path;
		initialiseFuncTables();
	}

	public FunctionalGroup (String name, String file_path) {
		super();
		invisible = true;
		this.name = name;
		this.file_path = file_path;
		initialiseFuncTables();
	}
	
	private void initialiseFuncTables() {
		stageTable = new SymbolTable<Stage>();
		addInitialChemicalStateVariables();
		addInitialStateVariables();
	}


	private void addInitialStateVariables() {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = tables.checkTypeTable("$float");
		Collection<Unit>units = new ArrayList<Unit>();
		units.add(new Unit(0, "m", 1));
		StateVariable z = new StateVariable("z", "Depth", floatType, units, new Float(0), 2, false);
		stateVarTable.put("z", z);
	}


	private void addInitialChemicalStateVariables() {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Collection<String> chemicalNames = tables.retrieveChemicalBaseNames();
		for (String chemName : chemicalNames) {
			addChemicalStateVariables(chemName);
		}
	}


	public void addChemicalStateVariables(String chemName) {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Collection<Unit> units = new ArrayList<Unit>();
		units.add(new Unit(0, "mol", 1));
		Type floatType = tables.checkTypeTable("$float");
		String varName = chemName + "_Ingested";
		String varDescription = chemName + " incoming pool";
		StateVariable chemVar = new StateVariable(varName, varDescription,
													floatType, units, null, null, false);
		stateVarTable.put(varName, chemVar);
		varName = chemName + "_Pool";
		varDescription = chemName + " internal pool";
		chemVar = new StateVariable(varName, varDescription, floatType, units, null, null, false);
		stateVarTable.put(varName, chemVar);
	}
	
	@Override
	public BuildFromXML build(XMLTag xmlTag) {
		baseTag = xmlTag;
		// name
		XMLTag nameTag = xmlTag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
			nameTag.removeFromParent();
		}
		
		// invis
		XMLTag invisValue = xmlTag.getTag(XMLTagEnum.INVISIBLE.xmlTag());
		if (invisValue != null) {
			this.invisible = Boolean.valueOf(invisValue.getValue());
			invisValue.removeFromParent();
		}
		
		// stages
		XMLTag [] tags = xmlTag.getTags(XMLTagEnum.STAGE.xmlTag());
		
		for (XMLTag t : tags) {
			Stage s = new Stage ();
			s.build(t);
			stageTable.put(s.getName(), s);
			t.removeFromParent();
		}
		
		// functions
		tags = xmlTag.getTags(XMLTagEnum.FUNCTION.xmlTag());
		
		for (XMLTag t : tags) {
			Function f = new Function (stageTable.values(),file_path,this);
			f.build(t);
			functions.add(f);
			t.removeFromParent();
		}
		
		// parameters
		tags = xmlTag.getTags(XMLTagEnum.PARAMETER.xmlTag());
		
		for (XMLTag t : tags) {
			Parameter p = new Parameter();
			p.build(t);
			paramTable.put(p.getName(), p);
			t.removeFromParent();
		}
		
		// local
		tags = xmlTag.getTags(XMLTagEnum.LOCAL.xmlTag());
		
		for (XMLTag t : tags) {
			Local l = new Local();
			l.build(t);
			localVarTable.put(l.getName(), l);
			t.removeFromParent();
		}
		
		// variables
		tags = xmlTag.getTags(XMLTagEnum.VARIABLE.xmlTag());
		
		for (XMLTag t : tags) {
			StateVariable v = new StateVariable();
			v.build(t);
			stateVarTable.put(v.getName(), v);
			t.removeFromParent();
		}
		
		// variety concentration
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_CONCENTRATION.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyConcentration vc = new VarietyConcentration();
			vc.build(t);
			varietyConcTable.put(vc.getName(), vc);
			t.removeFromParent();
		}
		
		// variety variable
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_VARIABLE.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyVariable vv = new VarietyVariable(this);
			vv.build(t);
			varietyStateTable.put(vv.getName(), vv);
			t.removeFromParent();
		}
		
		// variety locals
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_LOCAL.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyLocal vl = new VarietyLocal(this);
			vl.build(t);
			varietyLocalTable.put(vl.getName(), vl);
			t.removeFromParent();
		}
		
		// variety parameter
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_PARAM.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyParameter vp = new VarietyParameter(this);
			vp.build(t);
			varietyParamTable.put(vp.getName(), vp);
			t.removeFromParent();
		}
		
		
		return this;
	}
	
	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		super.buildToXML();
		baseTag.setName("functionalgroup");
		Collection<Stage> stages = stageTable.values();
		Iterator<Stage> iter = stages.iterator();
		while (iter.hasNext()) {
			Stage st = iter.next();
			baseTag.addTag(st.buildToXML());
		}
		return baseTag;
	}

	
	
	

	public boolean isInvisible() {
		return invisible;
	}

	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}

	

	
	
	public int getNoStages() {
		return stageTable.size();
	}
	
		
	
	
	public void addToStageTable(Stage stage) {
		stageTable.put(stage.getName(), stage);
	}
	
	public Stage checkStageTable(String stageName) {
		return stageTable.get(stageName);
	}



	public Collection<String> getStageNames() {
		
		return stageTable.keySet();
	}


	
}
