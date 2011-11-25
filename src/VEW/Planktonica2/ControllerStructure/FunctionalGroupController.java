package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Model;
import VEW.Planktonica2.Model.Stage;

public class FunctionalGroupController extends VEWController {

	private Collection <FunctionalGroup> functionalGroups;
	
	private FunctionalGroup currentFG;
	
	
	
	public FunctionalGroupController(Model m) {
		super(m);
		
		functionalGroups = this.model.getFunctionalGroups();
		
	}

	@Override
	public SelectableItem getSelectedItem() {
		return this.currentFG;
	}
	
	@Override
	protected boolean setInternalSelectedItem(SelectableItem i) {
		if (i instanceof FunctionalGroup) {
			this.currentFG = (FunctionalGroup) i;
			return true;
		}
		
		return false;
		
	}
	
	public Stage getStage(String stageName) {
		return getSelectedFunctionalGroup().checkStageTable(stageName);
	}
	
	public FunctionalGroup getSelectedFunctionalGroup () {
		return this.currentFG;
	}
	
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

	public int getNoStages() {
		FunctionalGroup g = this.getSelectedFunctionalGroup();
		if (g == null) {
			return 0;
		} else {
			return g.getNoStages();
		}
	}
	
	public Collection<FunctionalGroup> getFunctionalGroups() {
		return this.functionalGroups;
	}

	@Override
	public Collection<SelectableItem> getSelectables() {
		Collection<SelectableItem> c = new ArrayList<SelectableItem> (functionalGroups.size());
		for (FunctionalGroup f : functionalGroups) {
			c.add(f);
		}
		return c;
	}

	public void moveFunctionIndex(String funcName, int index) {
		currentFG.moveFunctionIndex(funcName, index);
	}

	
	
	
}
