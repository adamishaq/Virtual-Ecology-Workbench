package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.Display;


public class DeleteButtonListener implements ActionListener {

	private Display parent;
	
	public DeleteButtonListener(Display d) {
		parent = d;
	}
	
	public void actionPerformed(ActionEvent event) {
        parent.delete_category();
	}
	
}
