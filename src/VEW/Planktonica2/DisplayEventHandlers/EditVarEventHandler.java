package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JList;

import VEW.Planktonica2.ControllerStructure.VEWController;

public class EditVarEventHandler implements MouseListener {

	JList functionList;
	JList instanceList;
	JComboBox variableList;
	VEWController controller;
	
	public EditVarEventHandler (JList instanceList, JList functionList, JComboBox variableList, VEWController controller) {
		this.functionList = functionList;
		this.instanceList = instanceList;
		this.controller = controller;
		this.variableList = variableList;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

		// finds currently selected variable
		// sets detailsHTML up
		// changes saved state
		// opens AddVarPage
		// sets the details from the VariableChooser
		String selectedInstance = (String) instanceList.getSelectedValue();
		String selectedFunction = (String) functionList.getSelectedValue();
		String selectedVariable = (String) variableList.getSelectedItem();
		
		//TODO: keep doing this
		
		// get variable out of controller.
		// set up details and open the variable editor box.
		/*
		XMLTag theFunction;
        theFunction = getCurrentFunction();
        
        varNameString = varList.getSelectedItem().toString();
        XMLTag group = getCurrentInstance();
        XMLTag theVar = theFunction.getTagWhere("*", "name", varNameString);
        XMLTag theVarTemp = null;
        if (theVar == null) theVar = group.getTagWhere("*", "name", varNameString);
        //if (theVar == null) theVar = xmlFile.getTag("model/kernel").getTagWhere("*", "name", varNameString);
        if (theVar == null) theVarTemp = xmlFile.getTag("model/kernel");
        if (theVarTemp != null) theVar = theVarTemp.getTagWhere("*", "name", varNameString);
        if (theVar != null) addVarPage.editThisVar(xmlFile.getTag("model"), theFunction, group, theVar, AddVarPage.ALL);
        theVar = theFunction.getTagWhere("*", "name", varNameString);
        if (theVar == null) theVar = group.getTagWhere("*", "name", varNameString);
        detailsHTML.setText(VariableChooser.HTMLForVarHelper(varNameString,group, theVar, false));
        vc2.unsaved(false);
        parent.pack();
		*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		return;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		return;	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		return;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		return;
	}

	
}
