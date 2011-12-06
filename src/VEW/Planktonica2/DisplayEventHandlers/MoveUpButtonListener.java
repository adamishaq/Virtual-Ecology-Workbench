package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.ControllerStructure.VEWController;

public class MoveUpButtonListener implements ActionListener {

	private VEWController controller;
	
	public MoveUpButtonListener(VEWController controller) {
		super();
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		controller.moveSelectedUp();
		
	}

}
