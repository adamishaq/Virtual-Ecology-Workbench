package VEW.Planktonica2;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

public class VariablePanel extends JPanel {

	private static final long serialVersionUID = -285575176231783774L;

	protected JButton editVar;
	protected final Dimension ALTERNATE_BUTTON_SIZE = new Dimension(new Dimension(150, 24));
	
	private JEditorPane detailsHTML;
	
	public VariablePanel () {
		super();
		initialiseGUI();
		
	}

	
	
	private void initialiseGUI() {
		
		this.setLayout(new GridLayout (2, 1));
		
		editVar = new JButton("Edit Var");
		editVar.setPreferredSize(ALTERNATE_BUTTON_SIZE);
		
		// TODO: do edit var stuff
		
		
		detailsHTML = new JEditorPane();
		detailsHTML.setContentType("text/html");
		detailsHTML.setText("<html><body>Variables go here!</body></html>");
		detailsHTML.setEditable(false);
		detailsHTML.setPreferredSize(new Dimension(300,100));
		
		this.add(editVar);
		this.add(detailsHTML);
		
	}
	
	
}
