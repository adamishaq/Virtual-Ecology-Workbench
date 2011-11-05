package VEW.Planktonica2;

import java.util.Observable;
public abstract class VEWController extends Observable {
	
	public VEWController () {
		readInData();
	}
	
	protected abstract void readInData();

	/**
	 * Get a variable by name.
	 * 
	 * This takes into account the currently selected Item and Function when choosing the Variable data to return.
	 * 
	 * @param selectedVariable
	 * @return a Variable with name == selected Variable
	 */
	public Variable getVariable(String selectedVariable) {
		
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
