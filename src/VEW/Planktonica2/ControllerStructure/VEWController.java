package VEW.Planktonica2.ControllerStructure;

import java.util.Observable;
import java.util.prefs.BackingStoreException;

import VEW.Planktonica2.ControllerStructure.StateVariable;


public abstract class VEWController extends Observable {
	
	
	
	/**
	 * 
	 * @throws BackingStoreException when the XMLFile does not have the required tags in it.
	 */
	public VEWController () throws BackingStoreException {
	}
	
	protected abstract void readInData() throws BackingStoreException;

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

	private String getSelectedFunction() {
		
		return null;
	}

	private Object getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
