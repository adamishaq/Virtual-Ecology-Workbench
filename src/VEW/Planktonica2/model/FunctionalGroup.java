package VEW.Planktonica2.Model;

import java.util.Collection;
import java.util.Iterator;

import VEW.Common.XML.XMLTag;
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

	
	private void initialiseFuncTables() {
		stageTable = new SymbolTable<Stage>();
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
			stageTable.put(s.getName(), s);
		}
		
		// functions
		tags = xmlTag.getTags(XMLTagEnum.FUNCTION.xmlTag());
		
		for (XMLTag t : tags) {
			Function f = new Function (stageTable.values(),file_path,this.name);
			f.build(t);
			functions.add(f);
		}
		
		// parameters
		tags = xmlTag.getTags(XMLTagEnum.PARAMETER.xmlTag());
		
		for (XMLTag t : tags) {
			Parameter p = new Parameter(this);
			p.build(t);
			paramTable.put(p.getName(), p);
		}
		
		// local
		tags = xmlTag.getTags(XMLTagEnum.LOCAL.xmlTag());
		
		for (XMLTag t : tags) {
			Local l = new Local(this);
			l.build(t);
			localVarTable.put(l.getName(), l);
		}
		
		// variables
		tags = xmlTag.getTags(XMLTagEnum.VARIABLE.xmlTag());
		
		for (XMLTag t : tags) {
			StateVariable v = new StateVariable(this);
			v.build(t);
			stateVarTable.put(v.getName(), v);
		}
		
		// variety concentration
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_CONCENTRATION.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyConcentration vc = new VarietyConcentration(this);
			vc.build(t);
			varietyConcTable.put(vc.getName(), vc);
		}
		
		// variety variable
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_VARIABLE.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyVariable vv = new VarietyVariable(this);
			vv.build(t);
			varietyStateTable.put(vv.getName(), vv);
		}
		
		// variety locals
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_LOCAL.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyLocal vl = new VarietyLocal(this);
			vl.build(t);
			varietyLocalTable.put(vl.getName(), vl);
		}
		
		// variety parameter
		tags = xmlTag.getTags(XMLTagEnum.VARIETY_PARAM.xmlTag());
		
		for (XMLTag t : tags) {
			VarietyParameter vp = new VarietyParameter(this);
			vp.build(t);
			varietyParamTable.put(vp.getName(), vp);
		}
		
		
		return this;
	}
	
	@Override
	public XMLTag buildToXML() {
		XMLTag tag = super.buildToXML();
		tag.setName("functionalgroup");
		Collection<Stage> stages = stageTable.values();
		Iterator<Stage> iter = stages.iterator();
		while (iter.hasNext()) {
			Stage st = iter.next();
			tag.addTag(st.buildToXML());
		}
		return tag;
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

	/**
	 * 
	 * @param funcName
	 * @return The function corresponding to funName or null
	 */
	public Function getFunctionIndex(String funcName) {
		Iterator<Function> iter = functions.iterator();
		while (iter.hasNext()) {
			Function func = iter.next();
			if (funcName.equals(func.getName())) {
				return func;
			}
		}
		return null;
		
	}

	/**
	 * Moves funcName by the offset in the functions list
	 * @param funcName
	 * @param offset (+ = down/ - = up (the list))
	 */
	public void moveFunctionIndex(String funcName, int offset) {
		Function func = getFunctionIndex(funcName);
		
		if (func != null) {
			int oldIndex = functions.indexOf(func);
			if (oldIndex > 0 && oldIndex < functions.size() - 2) {
				functions.remove(func);
				functions.add(oldIndex + offset, func);
			}
		} else {
			System.err.println("Could not move func");
		}
		
		
	}


	
}
