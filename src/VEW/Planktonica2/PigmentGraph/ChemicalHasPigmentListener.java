package VEW.Planktonica2.PigmentGraph;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import VEW.Planktonica2.ControllerStructure.ChemicalController;

public class ChemicalHasPigmentListener implements ItemListener {

	private ChemicalController controller;
	
	public ChemicalHasPigmentListener(ChemicalController c) {
		super();
		this.controller = c;
		
	}

	@Override
	public void itemStateChanged(ItemEvent event) {

		controller.chemicalHasPigment(event.getStateChange() == ItemEvent.SELECTED);
		
	}

	

}
