package VEW.Planktonica2;

import java.awt.Dimension;
import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.PigmentGraph.PigmentPanel;
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
	
	@Override
	protected void populateAncilaryFuncPane() {

		// editor tab
		this.addTabToAncilary("Editor", editorPanel = new EditorPanel (this.controller));
		// variable tab
		this.addTabToAncilary("Variable", variablePanel = new VariableEditorPanel (this.controller));
		// pigment tab
		this.addTabToAncilary("Pigment Display", new PigmentPanel (chemController));
		
	}

	@Override
	protected void populateButtonPane() {
		
		this.defaultPopulateButtonPane();
		
	}

	

	

	

}
