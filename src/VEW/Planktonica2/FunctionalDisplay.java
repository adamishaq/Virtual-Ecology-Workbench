package VEW.Planktonica2;

import java.awt.Dimension;
import javax.swing.JCheckBox;
import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;
import VEW.Planktonica2.StageEditor.StageEditorPanel;
import VEW.UIComponents.VariableEditorPanel;

public class FunctionalDisplay extends Display {

	private static final long serialVersionUID = -6094339463447273188L;
	private StageEditorPanel stageEditor;
	
	private FunctionalGroupController funcController;

	public FunctionalDisplay(FunctionalGroupController controller, Dimension initialSize) {
		super(controller, initialSize);
		
		this.funcController = controller;
		
	}
	
	@Override
	protected String getCategoryName() {
		// Returns 
		return "function";
	}



	@Override
	protected void populateAncilaryFuncPane() {
		
		// editor tab
		this.addTabToAncilary("Editor", new EditorPanel (this.controller));
		// variable tab
		this.addTabToAncilary("Variable", new VariableEditorPanel (this.controller));
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
		
		JCheckBox predBox = new JCheckBox ("Mark as top predator");
		
		this.buttonPane.add(predBox);
		
	}


	
	
	





}
