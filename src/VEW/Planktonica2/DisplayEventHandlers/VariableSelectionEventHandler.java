package VEW.Planktonica2.DisplayEventHandlers;

import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import VEW.Planktonica2.ControllerStructure.VEWController;

public class VariableSelectionEventHandler implements TreeSelectionListener {

	private VEWController controller;
	
	public VariableSelectionEventHandler (VEWController controller) {
		this.controller = controller;
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		JTree t = (JTree) arg0.getSource();
		if (t.getSelectionPath() != null && t.getSelectionPath().getPathCount() > 2) {
			// It's a variable, not a heading
			String s = t.getSelectionPath().getLastPathComponent().toString();
			if (controller.getVariable(s) != null)
				controller.updateVariablePanel(controller.getVariable(s));
		}
	}
	
	

}
