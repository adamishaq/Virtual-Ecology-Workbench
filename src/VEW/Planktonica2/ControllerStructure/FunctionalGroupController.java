package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Model;
import VEW.Planktonica2.Model.Stage;

public class FunctionalGroupController extends VEWController {

	
	private FunctionalGroup currentFG;
	
	
	
	public FunctionalGroupController(Model m) {
		super(m);
		
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
	
		if (getFunctionalGroups().contains(f)) {
			this.currentFG = f;
			return;
		}
		
		FunctionalGroup curr = matchFGOnName(f);
		if (curr != null) {
			this.currentFG = curr;
		}
		
	}
	
	public void setSelectedAsTopPredator(boolean isTopPredator) {
		FunctionalGroup f = this.getSelectedFunctionalGroup();
		if (f != null) {
			f.setTopPredator(isTopPredator);
			this.setChanged();
			this.notifyObservers(new NewVariableEvent());
		}
	}

	private FunctionalGroup matchFGOnName(FunctionalGroup toMatch) {
		for (FunctionalGroup f : getFunctionalGroups()) {
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
		return this.model.getFunctionalGroups();
	}

	@Override
	public Collection<SelectableItem> getSelectables() {
		Collection<SelectableItem> c = new ArrayList<SelectableItem> (getFunctionalGroups().size());
		for (FunctionalGroup f : getFunctionalGroups()) {
			c.add(f);
		}
		return c;
	}
	
	public boolean addStage(String stageName) {
		if (currentFG.checkStageTable(stageName) == null) {
			currentFG.addToStageTable(new Stage(stageName));
			this.setChanged();
			this.notifyObservers(currentFG);
			return true;
		}
		return false;
	}
	

	public boolean stageIsCalledIn(String stageName, int functionIndex) {
		
		Stage selected = getStage(stageName);
		Function f = getFunctionAtIndex(functionIndex);
		
		// called in holds a referrence to the origional stage
		return f.isCalledIn(selected);
	}

	public void setStageIsCalledIn(String stageName, int functionIndex, boolean isCalledIn) {
		Stage selected = getStage(stageName);
		Function f = getFunctionAtIndex(functionIndex);
		
		if (isCalledIn) {
			// add to list of CalledIn
			f.addToCalledIn(selected);
			
		} else {
			// remove from list
			f.removeFromCalledIn(selected);
		}
	}

	public void addCategoryToModel(String name) {
		FunctionalGroup new_fg = new FunctionalGroup(name,model.getFilePath());
		model.addFunctionalGroup(new_fg);
		this.setChanged();
		this.notifyObservers(new NewCategoryEvent(new_fg));
	}
	
	public boolean isTopPredator() {
		FunctionalGroup g = getSelectedFunctionalGroup();
		return (g == null) ? false : g.isTopPredator();
		
	}
	
	
}
