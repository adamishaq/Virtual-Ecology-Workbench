package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.UIComponents.VariableEditorPanel;


public class ClearUnitsButtonListener implements ActionListener {

	private VariableEditorPanel parent;
	
	public ClearUnitsButtonListener(VariableEditorPanel v) {
		parent = v;
	}
	
	public void actionPerformed(ActionEvent event) {
        parent.clear_units();
	}
	
}