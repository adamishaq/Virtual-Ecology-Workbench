package VEW.Planktonica2;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

import VEW.UIComponents.VariableEditorPanel;

public class VariablePanel extends JPanel {

	private static final long serialVersionUID = -285575176231783774L;
	private VariableEditorPanel var_edit;
	
	public VariablePanel () {
		super();
		initialiseGUI();
		
	}

	
	
	private void initialiseGUI() {
		
		this.setLayout(new GridLayout (1, 2));
		var_edit = new VariableEditorPanel();
		this.add(var_edit);


		
	}
	
	
}
