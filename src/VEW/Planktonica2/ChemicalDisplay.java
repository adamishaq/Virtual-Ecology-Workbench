package VEW.Planktonica2;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JTree.DynamicUtilTreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import VEW.Planktonica2.ControllerStructure.Chemical;
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

	
	@Override
	protected void fillFunctionTree() {

		this.rootNode.removeAllChildren();
		
		Collection<Chemical> chemicals = controller.getChemicals();

		for (Chemical c : chemicals) {
			DefaultMutableTreeNode t = new DefaultMutableTreeNode(c);
			this.rootNode.add(t);
		}
		
		this.tree.expandRow(0);
		this.tree.setRootVisible(false);
	}

	

}
