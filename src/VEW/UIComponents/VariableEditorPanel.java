package VEW.UIComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VariableEditorPanel extends JPanel {

	private Dimension d;
	
	public VariableEditorPanel(Dimension dimension) {
		this.setPreferredSize(dimension);
		d = dimension;
		this.initialize();
	}

	private void initialize() {
		JPanel variable_info = new JPanel(new GridBagLayout());
		variable_info.setPreferredSize(new Dimension(d.width - 25, d.height - 25));
		GridBagConstraints c = new GridBagConstraints();
		
		// Label for scope choice
		c.ipady = 5;
		JLabel type = new JLabel("Type/Scope :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		variable_info.add(type, c);
		// Combo box to choose scope
		JComboBox  type_combo = new JComboBox();
		type_combo.addItem("Group Variable");
		type_combo.addItem("Group Parameter");
		type_combo.addItem("Local Variable");
		type_combo.addItem("Food-Based Parameter");
		type_combo.addItem("Food Set/Concentration");
		type_combo.addItem("Food-Based Local");
		type_combo.addItem("Food-Based Variable");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 3;
		c.gridy = 0;
		variable_info.add(type_combo, c);
		
		// Label for Variable name
		JLabel name = new JLabel("Variable Name :   ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		variable_info.add(name, c);
		// Text field for Var name
		JTextField var_name = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		variable_info.add(var_name, c);
		
		// Label for Variable description
		JLabel desc = new JLabel("Description :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		variable_info.add(desc, c);
		// Text field for Var description
		JTextField var_desc = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		variable_info.add(var_desc, c);
		
		// Label for History Size
		JLabel history_size = new JLabel("History Size :  ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		variable_info.add(history_size, c);
		// Text field for History Size
		JTextField h_size = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		variable_info.add(h_size, c);
		
		// Label for Initial value
		JLabel initial_value = new JLabel(" Initial Value :  ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		variable_info.add(initial_value, c);
		// Text field for Initial value
		JTextField i_val = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		variable_info.add(i_val, c);
		
		
		
		this.add(variable_info,BorderLayout.WEST);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1484036714507019850L;

	
	
}
