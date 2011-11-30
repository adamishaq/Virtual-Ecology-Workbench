package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;

public class FunctionalGroupIsPredatorListener implements ItemListener {

	FunctionalGroupController controller;
	
	public FunctionalGroupIsPredatorListener(FunctionalGroupController funcController) {
		this.controller = funcController;
	}
	
	@Override
	public void itemStateChanged(ItemEvent event) {
		
		controller.setSelectedAsTopPredator(event.getStateChange() == ItemEvent.SELECTED);
				
	}
}
