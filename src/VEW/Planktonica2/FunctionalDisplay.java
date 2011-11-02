package VEW.Planktonica2;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class FunctionalDisplay extends Display {

	private static final long serialVersionUID = -6094339463447273188L;

	public FunctionalDisplay() {
		super();
		
		JPanel agentEditorPanel = new JPanel(new GridLayout());
		JCheckBox predCheckBox = new JCheckBox("Mark as Top Gun");
		JButton stageEditor = new JButton("Edit Stages");
		
		agentEditorPanel.add(predCheckBox);
		agentEditorPanel.add(stageEditor);
		
		add(agentEditorPanel);
	}
	
	@Override
	protected String getCategoryName() {
		// Returns 
		return "function";
	}


}
