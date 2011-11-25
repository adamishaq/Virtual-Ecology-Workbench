package VEW.Planktonica2.ControllerStructure;

import java.util.Collection;
import java.util.Observable;
import java.util.prefs.BackingStoreException;

import javax.swing.tree.DefaultMutableTreeNode;

import VEW.Planktonica2.Display;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.Model;
import VEW.Planktonica2.Model.VariableType;


public abstract class VEWController extends Observable {
	
	protected Model model;
	protected String currentSourcePath;
	protected Display display;
	
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
	public VariableType getVariable(String selectedVariable) {
		
		if (getSelectedItem() == null || getSelectedFunction () == null) {
			return null;
		}
		SelectableItem i = getSelectedItem();
		if (i instanceof Catagory) {
			Catagory c = (Catagory) i;
			return c.checkAccessableVariableTable(selectedVariable);
		}
		return null;
		
	}

	public void populateFunctionTree(DefaultMutableTreeNode rootNode) {
		
		rootNode.removeAllChildren();
		
		Collection<SelectableItem> selectables = this.getSelectables();
		
		for (SelectableItem s : selectables) {
			DefaultMutableTreeNode select = new DefaultMutableTreeNode(s);
			rootNode.add(select);
			for (int i = 0; i < s.getNoFunctions(); i++) {
				select.add(new DefaultMutableTreeNode(s.getFunctionAtIndex(i)));
			}
		}
		
	}
	
	public String getSelectedFunction() {
		return display.get_selected_function();
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
		this.display.update_vars(i);
		
	}
	
	/**
	 * Called by setSelectedItem to set the internal reference to the appropriate SelectableItem
	 * @param i the item that has just been selected
	 * @return whether the selection was a success or not
	 */
	protected abstract boolean setInternalSelectedItem(SelectableItem i);
	 
	public abstract Collection<SelectableItem> getSelectables();

	public void setDisplay(Display disp) {
		this.display = disp;
	}

	public void load_source(String filePath) {
		this.currentSourcePath = filePath;
		this.setChanged();
		this.notifyObservers(new SourcePath(filePath));
	}

	public void updateVariablePanel(VariableType variable) {
		display.updateVariablePanel(variable);
	}

	

	

	
}
