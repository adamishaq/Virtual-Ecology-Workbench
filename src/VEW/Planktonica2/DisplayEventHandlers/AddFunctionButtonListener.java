package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import VEW.Planktonica2.Display;


public class AddFunctionButtonListener implements ActionListener {

	private Display parent;
	
	public AddFunctionButtonListener(Display d) {
		parent = d;
	}
	
	public void actionPerformed(ActionEvent event) {
		String name = JOptionPane.showInputDialog(parent,
	        	"Choose a name for the new function",
	            "Name Function", 1);
	    if (name != null) {
	    	parent.addFunction(name);
	    }
	}
	
}
