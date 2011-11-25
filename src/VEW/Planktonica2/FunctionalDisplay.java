package VEW.Planktonica2;

import java.awt.Dimension;
import javax.swing.JCheckBox;
import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;
import VEW.Planktonica2.DisplayEventHandlers.ButtonCommandNamesEnum;
import VEW.Planktonica2.DisplayEventHandlers.FGButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.TreeButtonListener;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.StageEditor.StageEditorPanel;
import VEW.UIComponents.VariableEditorPanel;

public class FunctionalDisplay extends Display {

	private static final long serialVersionUID = -6094339463447273188L;
	private EditorPanel editorPanel;
	private VariableEditorPanel variablePanel;
	private StageEditorPanel stageEditor;
	
	private FunctionalGroupController funcController;

	public FunctionalDisplay(FunctionalGroupController controller, Dimension initialSize) {
		super(controller, initialSize);
		
		this.funcController = controller;
		
	}
	
	public void updateVariablePanel(VariableType v) {
		this.variablePanel.display(v);
	}
	
	@Override
	protected String getCategoryName() {
		// Returns 
		return "function";
	}



	@Override
	protected void populateAncilaryFuncPane() {
		
		// editor tab
		this.addTabToAncilary("Editor", editorPanel = new EditorPanel (this.controller));
		// variable tab
		this.addTabToAncilary("Variable", variablePanel = new VariableEditorPanel ());
		// edit stages
		stageEditor = null;
		if (funcController == null) {
			stageEditor = new StageEditorPanel((FunctionalGroupController) this.controller);
		} else {
			stageEditor = new StageEditorPanel(funcController);
		}
		
		this.addTabToAncilary("Edit Stages", stageEditor);
		
	}



	@Override
	protected void populateButtonPane() {
		
		this.defaultPopulateButtonPane();
		
		TreeButtonListener FGListener = new FGButtonListener(this.controller);
		JCheckBox predBox = new JCheckBox ("Mark as top predator");
		predBox.setActionCommand(ButtonCommandNamesEnum.TOP_GUN.toString());
		predBox.addActionListener(FGListener);
		
		upFG.addActionListener(FGListener);
		downFG.addActionListener(FGListener);
		removeInstance.addActionListener(FGListener);
		renameInstance.addActionListener(FGListener);
		addInstance.addActionListener(FGListener);
		
		this.treeButtonPanel.add(predBox);
		
	}


	
	
	





}
