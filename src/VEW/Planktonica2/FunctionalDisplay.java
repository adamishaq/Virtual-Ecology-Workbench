package VEW.Planktonica2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import VEW.Planktonica2.ControllerStructure.VEWController;

public class FunctionalDisplay extends Display {

	private static final long serialVersionUID = -6094339463447273188L;
	

	public FunctionalDisplay(VEWController controller, Dimension initialSize) {
		super(controller, initialSize);
		
		//JPanel agentGrid = new JPanel (new GridLayout(1, 2));
		
		JPanel agentEditorPanel = new JPanel(new GridLayout(1, 2));
		JCheckBox predCheckBox = new JCheckBox("Mark as Top Predator");
		JButton stageEditor = new JButton("Edit Stages");
		stageEditor.setPreferredSize(ALTERNATE_BUTTON_SIZE);
		
		agentEditorPanel.add(predCheckBox);
		agentEditorPanel.add(stageEditor);
		
		//agentGrid.add(agentEditorPanel);
		//agentGrid.add(new JPanel (new BorderLayout ()));
		
		GridBagConstraints constraints = new GridBagConstraints ();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		
		this.topDisplay.add(agentEditorPanel, constraints);
	}
	
	
	
	@Override
	protected String getCategoryName() {
		// Returns 
		return "function";
	}





}
