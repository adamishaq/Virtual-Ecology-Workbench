package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.ControllerStructure.Variable;

public class VariableSelectionEventHandler implements ItemListener {

	private JComboBox variableList;
	private VEWController controller;
	
	public VariableSelectionEventHandler (JComboBox variableList, VEWController controller) {
		this.variableList = variableList;
		this.controller = controller;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		String selectedVariable = (String) variableList.getSelectedItem();
		Variable var = controller.getVariable(selectedVariable);
		// TODO: update details on screen without using Details.HTML.
	}

}
