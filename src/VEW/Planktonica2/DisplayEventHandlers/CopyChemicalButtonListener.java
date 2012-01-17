package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.ChemicalDisplay;

public class CopyChemicalButtonListener implements ActionListener {

	private ChemicalDisplay d;
	
	public CopyChemicalButtonListener(ChemicalDisplay d) {
		super();
		this.d = d;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		d.copy_chemical();		
	}

}
