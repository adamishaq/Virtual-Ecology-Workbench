package VEW.Planktonica2.DisplayEventHandlers;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import VEW.Planktonica2.ControllerStructure.Chemical;
import VEW.Planktonica2.ControllerStructure.FunctionalGroup;
import VEW.Planktonica2.ControllerStructure.VEWController;

public class LeftPanelTreeSelectionListener implements TreeSelectionListener {

	
	
	private VEWController controller;

	public LeftPanelTreeSelectionListener(VEWController controller) {
		this.controller = controller;
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent selection) {

		DefaultMutableTreeNode n = (DefaultMutableTreeNode) selection.getPath().getLastPathComponent();
		
		if (n.getUserObject() instanceof Chemical) {
			
			controller.setSelectedChemical((Chemical) n.getUserObject());
			
		} else if (n.getUserObject() instanceof FunctionalGroup) {
			
			controller.setSelectedFunctionalGroup((FunctionalGroup) n.getUserObject());
			
		}
		
		

	}

}
