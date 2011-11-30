package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import VEW.Planktonica2.UIComponents.VarType;
import VEW.Planktonica2.UIComponents.VariableEditorPanel;


public class VarTypeListener implements ItemListener {

	private VariableEditorPanel parent;
	
	public VarTypeListener(VariableEditorPanel par) {
		this.parent = par;
	}
	
	@Override
	public void itemStateChanged(ItemEvent i) {
		VarType selection = (VarType) i.getItem();
		if (!parent.getCurrent_selection().equals(selection)) {
			parent.change_var_type(selection);
		}
	}
	
}
