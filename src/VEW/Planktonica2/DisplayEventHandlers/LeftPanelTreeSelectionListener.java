package VEW.Planktonica2.DisplayEventHandlers;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.model.Catagory;
import VEW.Planktonica2.model.Function;

public class LeftPanelTreeSelectionListener implements TreeSelectionListener {

	
	
	private VEWController controller;

	public LeftPanelTreeSelectionListener(VEWController controller) {
		this.controller = controller;
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent selection) {

		DefaultMutableTreeNode n = (DefaultMutableTreeNode) selection.getPath().getLastPathComponent();
		
		if (n != null && n.getUserObject() != null) {
			if (n.getUserObject() instanceof Catagory) {
				Catagory c = (Catagory) n.getUserObject();
				controller.setSelectedItem(c);
				
			} else if (n.getUserObject() instanceof Function) {
				
				Function f = (Function) n.getUserObject();
				//controller.setSelectedItem(f);
				String file_path = f.getSource_code();
				file_path += f.getParentName();
				file_path += "\\";
				file_path += f.getName();
				file_path += ".bacon";
				controller.load_source(file_path);
			}
		}
		
		

	}

}
