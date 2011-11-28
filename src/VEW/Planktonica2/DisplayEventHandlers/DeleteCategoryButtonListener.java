package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.Display;


public class DeleteCategoryButtonListener implements ActionListener {

	private Display parent;
	
	public DeleteCategoryButtonListener(Display d) {
		parent = d;
	}
	
	public void actionPerformed(ActionEvent event) {
        parent.deleteCategory();
	}
	
}
