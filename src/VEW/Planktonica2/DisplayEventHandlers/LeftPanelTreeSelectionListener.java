package VEW.Planktonica2.DisplayEventHandlers;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import VEW.Planktonica2.ControllerStructure.SelectableItem;
import VEW.Planktonica2.ControllerStructure.VEWController;

public class LeftPanelTreeSelectionListener implements TreeSelectionListener {

	
	
	private VEWController controller;

	public LeftPanelTreeSelectionListener(VEWController controller) {
		this.controller = controller;
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent selection) {

		DefaultMutableTreeNode n = (DefaultMutableTreeNode) selection.getPath().getLastPathComponent();
		
		if (n != null && n.getUserObject() != null) {
			controller.setSelectedItem((SelectableItem) n.getUserObject());
		}
		
		

	}

}
