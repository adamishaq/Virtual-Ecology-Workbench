package VEW.Planktonica2;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.tree.DefaultMutableTreeNode;

import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.model.Chemical;

public class ChemicalDisplay extends Display {

	private static final long serialVersionUID = 7723416016731656817L;
	private EditorPanel editorPanel;
	private VariablePanel variablePanel;
	private PigmentPanel pigPanel;
	
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
