package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.Display;

public class RenameButtonListener implements ActionListener {

	Display parent;
	
	public RenameButtonListener(Display display) {
		this.parent = display;
	}
	
	public void actionPerformed(ActionEvent event) {
		parent.rename();
	}
	
}
