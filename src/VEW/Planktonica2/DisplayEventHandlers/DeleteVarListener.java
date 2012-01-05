package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.UIComponents.VariableEditorPanel;

public class DeleteVarListener implements ActionListener {

	private VariableEditorPanel parent;
	
	public DeleteVarListener(VariableEditorPanel par) {
		this.parent = par;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.delete_variable();
	}

}
