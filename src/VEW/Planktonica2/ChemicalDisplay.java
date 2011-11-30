package VEW.Planktonica2;

import java.awt.Dimension;

import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.DisplayEventHandlers.ChemButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.TreeButtonListener;
import VEW.Planktonica2.PigmentGraph.PigmentPanel;
import VEW.Planktonica2.UIComponents.VariableEditorPanel;


public class ChemicalDisplay extends Display {

	private static final long serialVersionUID = 7723416016731656817L;


	protected ChemicalDisplay(ChemicalController controller, Dimension initialSize) {
		super(controller, initialSize);
	}

	
	@Override
	protected String getCategoryName() {
		return "chemical";
	}
	
	@Override
	protected void populateAncilaryFuncPane() {

		// editor tab
		this.addTabToAncilary("Editor", this.editorPanel = new EditorPanel (this.controller));
		
		this.addTabToAncilary("Variable", variablePanel = new VariableEditorPanel (this.controller));
		// pigment tab
		this.addTabToAncilary("Pigment Display", new PigmentPanel ((ChemicalController) this.controller));
		
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
