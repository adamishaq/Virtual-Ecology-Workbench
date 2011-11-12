package VEW.Planktonica2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import VEW.Planktonica2.ControllerStructure.Chemical;
import VEW.Planktonica2.ControllerStructure.FunctionalGroup;
import VEW.Planktonica2.ControllerStructure.VEWController;

public class FunctionalDisplay extends Display {

	private static final long serialVersionUID = -6094339463447273188L;
	private EditorPanel editorPanel;
	private VariablePanel variablePanel;
	private StageEditorPanel stageEditor;
	

	public FunctionalDisplay(VEWController controller, Dimension initialSize) {
		super(controller, initialSize);
		
	}
	
	
	
	@Override
	protected String getCategoryName() {
		// Returns 
		return "function";
	}



	@Override
	protected void populateAncilaryFuncPane() {
		
		// editor tab
		this.addTabToAncilary("Editor", editorPanel = new EditorPanel ());
		// variable tab
		this.addTabToAncilary("Variable", variablePanel = new VariablePanel ());
		// edit stages
		this.addTabToAncilary("Edit Stages", stageEditor = new StageEditorPanel (this.controller));
		
	}



	@Override
	protected void populateButtonPane() {
		
		this.defaultPopulateButtonPane();
		
		JCheckBox predBox = new JCheckBox ("Mark as top predator");
		
		this.buttonPane.add(predBox);
		
	}



	
	@Override
	protected void fillFunctionTree() {
		
		this.rootNode.removeAllChildren();
		
		Collection<FunctionalGroup> functionalGroups = controller.getFunctionalGroups();
		
		for (FunctionalGroup g : functionalGroups) {
			MutableTreeNode t = new DefaultMutableTreeNode (g);
			this.rootNode.add(t);
		}
		
		this.tree.expandRow(0);
		this.tree.setRootVisible(false);
		
	}
	
	
	





}
