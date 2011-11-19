package VEW.UIComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

public class VariableEditorPanel extends JPanel {

	private Dimension d;
	
	JPanel variable_info = new JPanel(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	JLabel type = new JLabel("Type/Scope :");
	JComboBox  type_combo = new JComboBox();
	JLabel name = new JLabel("Variable Name :   ");
	JTextField var_name = new JTextField();
	JLabel desc = new JLabel("Description :");
	JTextField var_desc = new JTextField();
	JLabel history_size = new JLabel("History Size :  ");
	JTextField h_size = new JTextField();
	JLabel initial_value = new JLabel("Initial Value :  ");
	JTextField i_val = new JTextField();
	JLabel fs_link = new JLabel("Food-Set Link :");
	JComboBox  link_combo = new JComboBox();
	
	public VariableEditorPanel(Dimension dimension) {
		this.setPreferredSize(dimension);
		d = dimension;
		this.initialize();
	}

	private void initialize() {
		variable_info.setPreferredSize(new Dimension(d.width - 25, d.height - 25));
		// Label for scope choice
		c.ipady = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		variable_info.add(type, c);
		// Combo box to choose scope
		type_combo.addItem("Group Variable");
		type_combo.addItem("Group Parameter");
		type_combo.addItem("Local Variable");
		type_combo.addItem("Food-Based Parameter");
		type_combo.addItem("Food Set/Concentration");
		type_combo.addItem("Food-Based Local");
		type_combo.addItem("Food-Based Variable");
		type_combo.addItemListener(new VarTypeListener(this));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 3;
		c.gridy = 0;
		variable_info.add(type_combo, c);
		
		// Label for Variable name
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		variable_info.add(name, c);
		// Text field for Var name
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		variable_info.add(var_name, c);
		
		// Label for Variable description
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		variable_info.add(desc, c);
		// Text field for Var description
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		variable_info.add(var_desc, c);

		change_var_type("Group Variable");
		this.add(variable_info,BorderLayout.WEST);
	}
	
	public void change_var_type(String var_type) {
		boolean i_value = true;
		boolean history = true;
		boolean food_set = true;
		if (var_type.equals("Group Variable")) {
			food_set = false;
		} else if (var_type.equals("Group Parameter")) {
			history = false;
			food_set = false;
		} else if (var_type.equals("Local Variable")) {
			i_value = false;
			history = false;
			food_set = false;
		} else if (var_type.equals("Food-Based Parameter")) {
			history = false;
		} else if (var_type.equals("Food Set/Concentration")) {
			i_value = false;
			history = false;
			food_set = false;
		} else if (var_type.equals("Food-Based Local")) {
			i_value = false;
			history = false;
		} else if (var_type.equals("Food-Based Variable")) {

		}
		history_size.setVisible(false);
		variable_info.remove(history_size);
		variable_info.remove(h_size);
		initial_value.setVisible(false);
		variable_info.remove(initial_value);
		variable_info.remove(i_val);
		fs_link.setVisible(false);
		variable_info.remove(fs_link);
		variable_info.remove(link_combo);
		variable_info.validate();
		int y = 3;
		if (history) {
			// Label for History Size
			history_size.setVisible(true);
			c.gridx = 0;
			c.gridy = y;
			variable_info.add(history_size, c);
			// Text field for History Size
			c.gridx = 3;
			c.gridy = y;
			variable_info.add(h_size, c);
			y++;
		}
		if (i_value) {
			// Label for Initial value
			initial_value.setVisible(true);
			c.gridx = 0;
			c.gridy = y;
			variable_info.add(initial_value, c);
			// Text field for Initial value=
			c.gridx = 3;
			c.gridy = y;
			variable_info.add(i_val, c);
			y++;
		}
		if (food_set) {
			// Label for food-set link
			fs_link.setVisible(true);
			c.gridx = 0;
			c.gridy = y;
			variable_info.add(fs_link, c);
			// Combo box for food links
			link_combo.addItem("Food Set 1");
			link_combo.addItem("Food Set 2");
			c.gridx = 3;
			c.gridy = y;
			variable_info.add(link_combo, c);
		}
		variable_info.validate();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1484036714507019850L;

	static class VarTypeListener implements ItemListener {

		private String current = "";
		private VariableEditorPanel parent;
		
		public VarTypeListener(VariableEditorPanel par) {
			this.parent = par;
		}
		
		@Override
		public void itemStateChanged(ItemEvent i) {
			String selection = i.getItem().toString();
			if (!current.equals(selection)) {
				current = selection;
				parent.change_var_type(selection);
			}
		}
		
	}
	
}
