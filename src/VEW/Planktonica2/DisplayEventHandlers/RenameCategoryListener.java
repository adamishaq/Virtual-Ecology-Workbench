package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.Display;

public class RenameCategoryListener implements ActionListener {

	Display parent;
	
	public RenameCategoryListener(Display display) {
		this.parent = display;
	}
	
	public void actionPerformed(ActionEvent event) {
		parent.rename_category();
	}
	
}
