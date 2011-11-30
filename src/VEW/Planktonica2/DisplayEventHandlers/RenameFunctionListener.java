package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.Display;

public class RenameFunctionListener implements ActionListener {

	Display parent;
	
	public RenameFunctionListener(Display display) {
		this.parent = display;
	}
	
	public void actionPerformed(ActionEvent event) {
		parent.rename_function();
	}
	
}
