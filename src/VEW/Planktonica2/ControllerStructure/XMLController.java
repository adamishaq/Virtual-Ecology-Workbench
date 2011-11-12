package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.prefs.BackingStoreException;

import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class XMLController extends VEWController {

	private XMLFile xmlFile;
	private Collection <FunctionalGroup> functionalGroups;
	private Collection <Chemical> chemicals;
	
	private FunctionalGroup currentFG; 
	private Chemical currentChemical;
	
	public XMLController(XMLFile xmlFile) throws BackingStoreException {
		super();
		this.xmlFile = xmlFile;
		
		functionalGroups = new ArrayList<FunctionalGroup> ();
		chemicals = new ArrayList<Chemical> ();
		
		readInData();
		
	}
	
	@Override
	protected void readInData() throws BackingStoreException {
		
		try {
			XMLTag [] tags = xmlFile.getTags(XMLTagEnum.FUNCTIONAL_GROUP.xmlTag());

			for(XMLTag t : tags) {
				FunctionalGroup f = new FunctionalGroup ();
				f.build(t);
				functionalGroups.add(f);
			}

			tags = xmlFile.getTags(XMLTagEnum.CHEMICAL.xmlTag());

			for(XMLTag t : tags) {
				Chemical c = new Chemical ();
				c.build(t);
				chemicals.add(c);
			}
		} catch (NullPointerException n) {
			throw new BackingStoreException (n); 
		} catch (NumberFormatException f) {
			throw new BackingStoreException (f);
		}
		
	}

	@Override
	public Stage getStageAtIndex (int stageNo) {
		return this.getSelectedFunctionalGroup().getStageAtIndex(stageNo);
	}
	
	@Override
	public Function getFunctionAtIndex (int functionNo) {
		return this.getSelectedFunctionalGroup().getFunctionAtIndex(functionNo);
	}
	
	@Override
	public FunctionalGroup getSelectedFunctionalGroup () {
		return this.currentFG;
	}
	
	@Override
	public void setSelectedFunctionalGroup (FunctionalGroup f) {
		
		if (functionalGroups.contains(f)) {
			this.currentFG = f;
			return;
		}
		
		FunctionalGroup curr = matchFGOnName(f);
		if (curr != null) {
			this.currentFG = curr;
		}
		
	}

	private FunctionalGroup matchFGOnName(FunctionalGroup toMatch) {
		for (FunctionalGroup f : functionalGroups) {
			if (f.getName().equals(toMatch.getName())) {
				return f;
			}
		}
		return null;
	}

	
	@Override
	public int getNoStages() {
		FunctionalGroup g = this.getSelectedFunctionalGroup();
		if (g == null) {
			return 0;
		} else {
			return g.getNoStages();
		}
	}
	

	@Override
	public int getNoFunctions() {
		FunctionalGroup g = this.getSelectedFunctionalGroup();
		if (g == null) {
			return 0;
		} else {
			return g.getNoFunctions();
		}
	}

	
	@Override
	public Collection<FunctionalGroup> getFunctionalGroups() {
		return this.functionalGroups;
	}

	@Override
	public Collection<Chemical> getChemicals() {
		return this.chemicals;
	}

	@Override
	public Chemical getSelectedChemical() {
		return this.currentChemical;
	}

	@Override
	public void setSelectedChemical(Chemical c) {
		this.currentChemical = c;		
	}
	
	
	
}
