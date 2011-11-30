package VEW.Planktonica2.PigmentGraph;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import VEW.Planktonica2.ControllerStructure.ChemicalController;

public class ChemicalHasPigmentListener implements ItemListener {

	private ChemicalController controller;
	private PigmentPanel pigmentPanel;
	
	public ChemicalHasPigmentListener(ChemicalController c, PigmentPanel pigmentPanel) {
		super();
		this.controller = c;
		this.pigmentPanel = pigmentPanel;
		
	}

	@Override
	public void itemStateChanged(ItemEvent event) {

		controller.chemicalHasPigment(event.getStateChange() == ItemEvent.SELECTED);
		this.pigmentPanel.repaint();
		this.pigmentPanel.redrawGraph();		
	}

	

}
