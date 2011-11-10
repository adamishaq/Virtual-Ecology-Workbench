package VEW.Planktonica2;

import java.awt.Dimension;

import VEW.Planktonica2.ControllerStructure.VEWController;

public class ChemicalDisplay extends Display {

	private static final long serialVersionUID = 7723416016731656817L;
	private EditorPanel editorPanel;
	private VariablePanel variablePanel;
	private PigmentPanel pigPanel;


	protected ChemicalDisplay(VEWController controller, Dimension initialSize) {
		super(controller, initialSize);
	}

	@Override
	protected String getCategoryName() {
		// TODO Auto-generated method stub
		return "chemical";
	}

	
	
	@Override
	protected void populateAncilaryFuncPane() {

		// editor tab
		this.addTabToAncilary("Editor", editorPanel = new EditorPanel ());
		// variable tab
		this.addTabToAncilary("Variable", variablePanel = new VariablePanel ());
		// pigment tab
		this.addTabToAncilary("Pigment Display", pigPanel = new PigmentPanel ());
		
	}

	@Override
	protected void populateButtonPane() {
		
		this.defaultPopulateButtonPane();
		
	}

	

}
