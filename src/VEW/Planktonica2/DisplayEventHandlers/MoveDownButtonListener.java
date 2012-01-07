package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.ControllerStructure.VEWController;

public class MoveDownButtonListener implements ActionListener {

	private VEWController controller;

	public MoveDownButtonListener(VEWController controller) {
		super();
		this.controller = controller;
	}
 
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		this.controller.moveSelectedDown();
		
	}

}
