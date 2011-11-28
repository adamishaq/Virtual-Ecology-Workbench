package VEW.Planktonica2.StageEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;

public class AddStageButtonListener implements ActionListener {
	
	private FunctionalGroupController controller;
	
	public AddStageButtonListener(FunctionalGroupController controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Get string data from something
		String stageName = "test";
		boolean success = controller.addStage(stageName);
		if (!success) {
			//TODO Error message
		}
		

	}

}
