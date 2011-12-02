package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.UIComponents.VariableEditorPanel;


public class AddVarListener implements ActionListener {

	private VariableEditorPanel parent;
	
	public AddVarListener(VariableEditorPanel par) {
		this.parent = par;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		VariableType v = parent.construct_variable();
		if (v != null)
			parent.add_var(v);
	}

}
