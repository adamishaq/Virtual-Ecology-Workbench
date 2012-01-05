package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.Display;


public class AddFunctionButtonListener implements ActionListener {

	private Display parent;
	
	public AddFunctionButtonListener(Display d) {
		parent = d;
	}
	
	public void actionPerformed(ActionEvent event) {
	    parent.addFunction();
	}
	
}
