package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.UIComponents.VariableEditorPanel;

public class AddVarListener implements ActionListener {

	private VariableEditorPanel parent;
	
	public AddVarListener(VariableEditorPanel par) {
		this.parent = par;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.construct_variable();
	}

}
