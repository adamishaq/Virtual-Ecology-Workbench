package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.UIComponents.VariableEditorPanel;

public class UpdateVarListener implements ActionListener {

	private VariableEditorPanel parent;
	
	public UpdateVarListener(VariableEditorPanel par) {
		this.parent = par;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.update_variable();
	}

}
