package VEW.Planktonica2;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.DisplayEventHandlers.CopyChemicalButtonListener;
import VEW.Planktonica2.PigmentGraph.PigmentPanel;
import VEW.Planktonica2.UIComponents.VariableEditorPanel;


public class ChemicalDisplay extends Display {

	private static final long serialVersionUID = 7723416016731656817L;


	protected ChemicalDisplay(ChemicalController controller, Dimension initialSize) {
		super(controller, initialSize);
		JButton copy_chem = new JButton(new ImageIcon(IconRoot+ "copy_chemical.png"));
		copy_chem.setPreferredSize(STANDARD_BUTTON_SIZE);
		copy_chem.addActionListener(new CopyChemicalButtonListener(this));
		copy_chem.setToolTipText("Copy the selected chemical");
		this.treeButtonPanel.add(copy_chem);
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


	public void copy_chemical() {
		((ChemicalController)this.controller).copy_chemical(this);
	}

	

	

	

}
