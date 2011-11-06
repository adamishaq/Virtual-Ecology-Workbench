package VEW.Planktonica2;

import java.util.Observable;
import java.util.Observer;

import VEW.Planktonica2.ControllerStructure.VEWController;

public abstract class Model implements Observer {

	VEWController controller;
	
	/**
	 * 
	 * @param controller the data in the current system
	 */
	public Model (VEWController controller) {
		this.controller = controller;
		controller.addObserver(this);
		
	}
	
	
	/**
	 * A function that activates the checkModel method, causing the model to
	 * automatically compile. 
	 */
	@Override
	public void update(Observable o, Object arg) {
		checkModel();

	}
	
	/**
	 * Checks the validity of the current data in the controller.
	 */
	public abstract void checkModel ();

}
