package VEW.Planktonica2;

import java.awt.Dimension;

import VEW.Planktonica2.ControllerStructure.ChemicalController;
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
		
	}

	

	

	

}
