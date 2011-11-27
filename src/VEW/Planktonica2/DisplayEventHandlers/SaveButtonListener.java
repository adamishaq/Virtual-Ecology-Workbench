package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.EditorPanel;

public class SaveButtonListener implements ActionListener {

private EditorPanel parent;
	
	public SaveButtonListener(EditorPanel edit) {
		parent = edit;
	}
	
	public void actionPerformed(ActionEvent event) {
		parent.save();
	}
	
}
