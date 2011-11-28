package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.SymbolTable;

public class FunctionalGroup extends Catagory {
	

	private final String predVarName = "S_t";
	
	private boolean invisible;
	private String file_path;
	private boolean predator;
	
	
	private SymbolTable<Stage> stageTable;

	public FunctionalGroup (String file_path) {
		super();
		invisible = true;
		this.file_path = file_path;
		initialiseFuncTables();
	}

	
	private void initialiseFuncTables() {
		stageTable = new SymbolTable<Stage>();
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
			Parameter p = new Parameter(this);
			p.build(t);
			paramTable.put(p.getName(), p);
			t.removeFromParent();
		}
		
		// local
		tags = xmlTag.getTags(XMLTagEnum.LOCAL.xmlTag());
		
		for (XMLTag t : tags) {
			Local l = new Local(this);
			l.build(t);
			localVarTable.put(l.getName(), l);
			t.removeFromParent();
		}
		
		// variables
		tags = xmlTag.getTags(XMLTagEnum.VARIABLE.xmlTag());
		
		for (XMLTag t : tags) {
			StateVariable v = new StateVariable(this);
			v.build(t);
			stateVarTable.put(v.getName(), v);
			t.removeFromParent();
		}
		
		// variety concentration
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_CONCENTRATION.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyConcentration vc = new VarietyConcentration(this);
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
		
		// predator
		tags = xmlTag.getTags(XMLTagEnum.PREDATOR.xmlTag());
		this.predator = tags != null && tags.length > 0 && tags[0].getValue().equals("true");
		
		
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
		
		if (predator) {
			baseTag.addTag("predator", "true");
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


	
	public void setTopPredator(boolean b) {
		this.predator = b;
	
		if (b) {
			this.addPredatorSizeVariable();
		} else {
			this.removePredatorSizeVariable();
		}
		
	}
	
	private void removePredatorSizeVariable() {
		
		this.stateVarTable.remove(predVarName);
		
	}


	private void addPredatorSizeVariable() {
		StateVariable sizePred = new StateVariable(this);
		
		sizePred.setCodeName("SysVars.getPredSize()");
		sizePred.setName(this.predVarName);
		sizePred.setDesc("Size of predator");
		sizePred.setValue(3);
		sizePred.setHist(1);
		ArrayList<Unit> units = new ArrayList<Unit> ();
		units.add(new Unit (-3, "m", 1));
		sizePred.setUnits(units);
		
		this.addToStateVarTable(sizePred);
	}


	
}
