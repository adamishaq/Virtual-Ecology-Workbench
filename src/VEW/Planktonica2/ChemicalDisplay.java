package VEW.Planktonica2;

import java.awt.Dimension;

import javax.swing.JCheckBox;

import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.DisplayEventHandlers.ButtonCommandNamesEnum;
import VEW.Planktonica2.DisplayEventHandlers.ChemButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.FGButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.TreeButtonListener;
import VEW.Planktonica2.Model.VariableType;
import VEW.UIComponents.VariableEditorPanel;

public class ChemicalDisplay extends Display {

	private static final long serialVersionUID = 7723416016731656817L;
	private VariableEditorPanel variablePanel;
	private EditorPanel editorPanel;
	
	private ChemicalController chemController;


	protected ChemicalDisplay(ChemicalController controller, Dimension initialSize) {
		super(controller, initialSize);
		
		this.chemController = controller;
	}

	@Override
	protected String getCategoryName() {
		// TODO Auto-generated method stub
		return "chemical";
	}

	public void updateVariablePanel(VariableType v) {
		this.variablePanel.display(v);
	}
	
	@Override
	protected void populateAncilaryFuncPane() {

		// editor tab
		this.addTabToAncilary("Editor", editorPanel = new EditorPanel (this.controller));
		// variable tab
		this.addTabToAncilary("Variable", variablePanel = new VariableEditorPanel ());
		// pigment tab
		this.addTabToAncilary("Pigment Display", new PigmentPanel ());
		
	}

	@Override
	protected void populateButtonPane() {
		
		this.defaultPopulateButtonPane();
		
		TreeButtonListener ChemListener = new ChemButtonListener(this.controller);
		
		upFG.addActionListener(ChemListener);
		downFG.addActionListener(ChemListener);
		removeInstance.addActionListener(ChemListener);
		renameInstance.addActionListener(ChemListener);
		addInstance.addActionListener(ChemListener);
		
	}

	

	

}
