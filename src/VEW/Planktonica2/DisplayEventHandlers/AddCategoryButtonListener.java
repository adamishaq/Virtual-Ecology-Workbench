package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import VEW.Planktonica2.Display;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;


public class AddCategoryButtonListener implements ActionListener {

	private Display parent;
	
	public AddCategoryButtonListener(Display d) {
		parent = d;
	}
	
	public void actionPerformed(ActionEvent event) {
        parent.addCategory();
	}
	
}
