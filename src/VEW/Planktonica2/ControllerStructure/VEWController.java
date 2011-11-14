package VEW.Planktonica2.ControllerStructure;

import java.util.Collection;
import java.util.Observable;
import java.util.prefs.BackingStoreException;

import javax.swing.tree.DefaultMutableTreeNode;

import VEW.Planktonica2.model.Function;
import VEW.Planktonica2.model.Model;
import VEW.Planktonica2.model.StateVariable;


public abstract class VEWController extends Observable {
	
	Model model;
	
	/**
	 * 
	 * @param m the class based model of the current VEW project
	 * @throws BackingStoreException when the XMLFile does not have the required tags in it.
	 */
	public VEWController (Model m) {
		super();
		model = m;
	}

	/**
	 * Get a variable by name.
	 * 
	 * This takes into account the currently selected Item and Function when choosing the Variable data to return.
	 * 
	 * @param selectedVariable
	 * @return a Variable with name == selected Variable
	 */
	public StateVariable getVariable(String selectedVariable) {
		
		if (getSelectedItem() == null || getSelectedFunction () == null) {
			return null;
		}
		
		
		
		// TODO: get variable.
		
		return null;
		
	}

	public void populateFunctionTree(DefaultMutableTreeNode rootNode) {
		
		rootNode.removeAllChildren();
		
		Collection<SelectableItem> selectables = this.getSelectables();
		
		for (SelectableItem s : selectables) {
			rootNode.add(new DefaultMutableTreeNode(s));
		}
		
	}
	
	private String getSelectedFunction() {
		
		return null;
	}
	
	public Function getFunctionAtIndex(int functionNo) {
		return getSelectedItem().getFunctionAtIndex(functionNo);
	}

	public int getNoFunctions() {
		return (getSelectedItem() == null) ? 0 : getSelectedItem().getNoFunctions();
	}
	
	public abstract SelectableItem getSelectedItem();
	
	/**
	 * Sets the currently selected item (FunctionalGroup/Chemical) and
	 * fires an event telling listeners that the selection has changed.
	 * @param i
	 */
	public void setSelectedItem (SelectableItem i) {
		
		if (setInternalSelectedItem(i)) {
			this.setChanged();
			this.notifyObservers(i);
		}
		
	}
	
	/**
	 * Called by setSelectedItem to set the internal reference to the appropriate SelectableItem
	 * @param i the item that has just been selected
	 * @return whether the selection was a success or not
	 */
	protected abstract boolean setInternalSelectedItem(SelectableItem i);
	 
	public abstract Collection<SelectableItem> getSelectables();

	

	

	
}
