package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.UIComponents.VariableEditorPanel;


public class AddUnitButtonListener implements ActionListener {

	private VariableEditorPanel parent;
	
	public AddUnitButtonListener(VariableEditorPanel v) {
		parent = v;
	}
	
	public void actionPerformed(ActionEvent event) {
        parent.add_unit();
	}
	
}