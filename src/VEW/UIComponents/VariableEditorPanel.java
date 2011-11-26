package VEW.UIComponents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.DisplayEventHandlers.AddVarListener;
import VEW.Planktonica2.DisplayEventHandlers.UpdateVarListener;
import VEW.Planktonica2.DisplayEventHandlers.VarTypeListener;
import VEW.Planktonica2.model.Catagory;
import VEW.Planktonica2.model.FunctionalGroup;
import VEW.Planktonica2.model.Local;
import VEW.Planktonica2.model.Parameter;
import VEW.Planktonica2.model.StateVariable;
import VEW.Planktonica2.model.Unit;
import VEW.Planktonica2.model.VariableType;
import VEW.Planktonica2.model.Variety;
import VEW.Planktonica2.model.VarietyConcentration;
import VEW.Planktonica2.model.VarietyLocal;
import VEW.Planktonica2.model.VarietyParameter;
import VEW.Planktonica2.model.VarietyVariable;

public class VariableEditorPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1484036714507019850L;
	
	private VarType current_selection = VarType.GROUPVAR;
	private Catagory current_category;
	private VEWController controller;
	private HashMap<String,Integer> food_sets = new HashMap<String,Integer>();
	
	private GridBagConstraints c = new GridBagConstraints();
	private GridBagConstraints main_layout = new GridBagConstraints();
	private JPanel variable_info = new JPanel(new GridBagLayout());
	private JPanel unit_editor = new JPanel(new GridBagLayout());
	private JPanel button_panel = new JPanel(new GridBagLayout());
	private JLabel type = new JLabel("Type/Scope :");
	private JComboBox  type_combo = new JComboBox();
	private JLabel name = new JLabel("Variable Name :   ");
	private JTextField var_name = new JTextField();
	private JLabel desc = new JLabel("Description :");
	private JTextField var_desc = new JTextField();
	private JLabel history_size = new JLabel("History Size :  ");
	private JTextField h_size = new JTextField("0");
	private JLabel initial_value = new JLabel("Initial Value :  ");
	private JTextField i_val = new JTextField("0");
	private JLabel fs_link = new JLabel("Food-Set Link :");
	private JComboBox  link_combo = new JComboBox();
	
	private JLabel units = new JLabel("  Units :  ");
	private JTextField unit_string = new JTextField("");
	
	private JButton add_var = new JButton("Add");

	private JButton update_var = new JButton("Update");
	
	public VariableEditorPanel(VEWController controller) {
		this.controller = controller;
		this.controller.addObserver(this);
		this.initialize();
	}

	public VarType getCurrent_selection() {
		return current_selection;
	}

	private void initialize() {
		this.setLayout(new GridBagLayout());
		// Label for scope choice
		c.ipady = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		variable_info.add(type, c);
		// Combo box to choose scope
		for (VarType vt : VarType.values()) {
			type_combo.addItem(vt);
	    }
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
		// Add the final attributes based on the value in the combo box
		change_var_type(current_selection);
		// Add the panel to the main panel
		main_layout.fill = GridBagConstraints.HORIZONTAL;
		main_layout.gridx = 0;
		main_layout.gridy = 0;
		this.add(variable_info,main_layout);
		
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		unit_editor.add(units, c);
		c.ipadx = 150;
		c.gridx = 2;
		c.gridy = 0;
		unit_editor.add(unit_string, c);
		c.ipadx = 0;
		// Add the panel to the main layout
		unit_editor.setPreferredSize(variable_info.getPreferredSize());
		main_layout.gridx = 1;
		main_layout.gridy = 0;
		this.add(unit_editor,main_layout);
		
		add_var.addActionListener(new AddVarListener(this));
		update_var.addActionListener(new UpdateVarListener(this));
		button_panel.add(add_var);
		button_panel.add(update_var);
		// Add the panel to the main layout
		main_layout.gridwidth = 2;
		main_layout.ipady = 25;
		main_layout.gridx = 0;
		main_layout.gridy = 1;
		this.add(button_panel,main_layout);
	}
	
	public void change_var_type(VarType var_type) {
		current_selection = var_type;
		boolean i_value = true;
		boolean history = true;
		boolean food_set = true;
		switch (var_type) {
		case GROUPVAR : 
			food_set = false;
			break;
		case GROUPPARAM :
			history = false;
			food_set = false;
			break;
		case LOCALVAR :
			i_value = false;
			history = false;
			food_set = false;
			break;
		case FOODPARAM :
			history = false;
			break;
		case FOODSET :
			i_value = false;
			history = false;
			food_set = false;
			break;
		case FOODLOCAL :
			i_value = false;
			history = false;
			break;
		case FOODVAR :
			break;
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
		variable_info.remove(add_var);
		variable_info.validate();
		int y = 3;
		c.gridwidth = 2;
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
			// Text field for Initial value
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
			c.gridx = 3;
			c.gridy = y;
			variable_info.add(link_combo, c);
			y++;
		}
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = y;
		//variable_info.add(add_var, c);
		variable_info.validate();
	}
	
	
	
	public void construct_variable() {
		// Check the variable has all appropriate values set
		if (!validate_variable(false))
			return;
		// Construct the variable
		switch (current_selection) {
		case GROUPVAR :
			StateVariable v = new StateVariable(this.current_category);
			v.setName(var_name.getText());
			v.setDesc(var_desc.getText());
			v.setHist(Integer.parseInt(h_size.getText()));
			v.setValue(Float.parseFloat(i_val.getText()));
			current_category.addToStateVarTable(v);
			break;
		case GROUPPARAM :
			Parameter p = new Parameter(this.current_category);
			p.setName(var_name.getText());
			p.setDesc(var_desc.getText());
			p.setValue(Float.parseFloat(i_val.getText()));
			current_category.addToParamTable(p);
			break;
		case LOCALVAR :
			Local l = new Local(this.current_category);
			l.setName(var_name.getText());
			l.setDesc(var_desc.getText());
			current_category.addToLocalTable(l);
			break;
		case FOODPARAM :
			// Make sure the currently selected category is not a chemical
			if (this.current_category instanceof FunctionalGroup) {
				VarietyParameter vp = new VarietyParameter((FunctionalGroup) this.current_category);
				vp.setName(var_name.getText());
				vp.setDesc(var_desc.getText());
				vp.setValue(Float.parseFloat(i_val.getText()));
				// Check the food set link actually exists
				VarietyConcentration vc =
					this.current_category.checkVarietyConcTable(link_combo.getSelectedItem().toString());
				if (vc == null)
					return;
				vp.setLinkConcentration(vc);
				current_category.addToVarietyParamTable(vp);
			}
			break;
		case FOODVAR :
			// Make sure the currently selected category is not a chemical
			if (this.current_category instanceof FunctionalGroup) {
				VarietyVariable vv = new VarietyVariable((FunctionalGroup) this.current_category);
				vv.setName(var_name.getText());
				vv.setDesc(var_desc.getText());
				vv.setHist(Integer.parseInt(h_size.getText()));
				vv.setValue(Float.parseFloat(i_val.getText()));
				// Check the food set link actually exists
				VarietyConcentration vc =
					this.current_category.checkVarietyConcTable(link_combo.getSelectedItem().toString());
				if (vc == null)
					return;
				vv.setLinkConcentration(vc);
				current_category.addToVarietyStateTable(vv);
			}
			break;
		case FOODSET :
			// Make sure the currently selected category is not a chemical
			if (this.current_category instanceof FunctionalGroup) {
				VarietyConcentration vc = new VarietyConcentration((FunctionalGroup) this.current_category);
				vc.setName(var_name.getText());
				vc.setDesc(var_desc.getText());
				current_category.addToVarietyConcTable(vc);
			}
			break;
		case FOODLOCAL :
			// Make sure the currently selected category is not a chemical
			if (this.current_category instanceof FunctionalGroup) {
				VarietyLocal vl = new VarietyLocal((FunctionalGroup) this.current_category);
				vl.setName(var_name.getText());
				vl.setDesc(var_desc.getText());
				// Check the food set link actually exists
				VarietyConcentration vc =
					this.current_category.checkVarietyConcTable(link_combo.getSelectedItem().toString());
				if (vc == null)
					return;
				vl.setLinkConcentration(vc);
				current_category.addToVarietyLocalTable(vl);
			}
			break;
		}
		controller.update_category(this.current_category);
	}

	public void update_variable() {
		// Check the variable has all appropriate values set
		if (!validate_variable(true))
			return;
		VariableType v = current_category.checkAllVariableTables(var_name.getText());
		v.setDesc(var_desc.getText());
		ArrayList<Unit> units = new ArrayList<Unit>();
		units.add(new Unit(0,unit_string.getText(),0));
		v.setUnits(units);
		if (v instanceof Variety) {
			Variety var = (Variety) v;
			if (this.current_category instanceof FunctionalGroup) {
				// Check the food set link actually exists
				VarietyConcentration vc =
					this.current_category.checkVarietyConcTable(link_combo.getSelectedItem().toString());
				if (vc == null)
					return;
				if (var instanceof VarietyVariable) {
					var.setHist(Integer.parseInt(h_size.getText()));
					var.setValue(Float.parseFloat(i_val.getText()));
					var.setLinkConcentration(vc);
				} else if (var instanceof VarietyParameter) {
					var.setValue(Float.parseFloat(i_val.getText()));
					var.setLinkConcentration(vc);
				}
			}
		} else {
			if (v instanceof StateVariable) {
				v.setHist(Integer.parseInt(h_size.getText()));
				v.setValue(Float.parseFloat(i_val.getText()));
			} else if (v instanceof Parameter) {
				v.setValue(Float.parseFloat(i_val.getText()));
			}
		}
	}
	
	private boolean validate_variable(boolean exists) {
		// Check there is something to add it to
		if (this.current_category == null)
			return false;
		// Check that the variable has a name
		if (var_name.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Variable must have a name");
			return false;
		}
		// Check it contains no disallowed characters
		char[] chars = var_name.getText().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!(Character.isLetterOrDigit(chars[i]) || chars[i] == '_')) {
				JOptionPane.showMessageDialog(this, "Variable name cannot contain '" 
						+ chars[i] + "'");
				return false;
			}
		}
		// Check the name is unique if it is not an existing variable
		if (!exists && current_category.checkAllVariableTables(var_name.getText()) != null) {
			JOptionPane.showMessageDialog(this, "A variable of that name already exists");
			return false;
		}
		// Check the name is not unique if it is an existing variable
		if (exists && current_category.checkAllVariableTables(var_name.getText()) == null) {
			JOptionPane.showMessageDialog(this, "No variable of that name exists");
			return false;
		}
		// Check that history has a legal value
		switch (current_selection) {
		case GROUPVAR :
		case FOODVAR :
			try {
				int hist = Integer.parseInt(h_size.getText());
				if (hist < 0) {
					JOptionPane.showMessageDialog(this, "History size must be 0 or higher");
					return false;
				}
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(this, "Invalid history size");
				return false;
			}
		}
		// Check that initial value is legal
		switch (current_selection) {
		case GROUPVAR :
		case GROUPPARAM :
		case FOODPARAM :
		case FOODVAR :
			try {
				Float.parseFloat(i_val.getText());
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(this, "Invalid variable initial value");
				return false;
			}
		}
		return true;
	}
	
	public void display(VariableType v) {
		// Fill in the variable name and description
		var_name.setText(v.getName());
		var_name.setCaretPosition(0);
		var_desc.setText(v.getDesc());
		var_desc.setCaretPosition(0);
		String units = "";
		for (Unit u : v.getUnits()) {
			units += u.format();
		}
		unit_string.setText(units);
		// Check what type of variable it is
		if (v instanceof StateVariable) {
			type_combo.setSelectedIndex(0);
			StateVariable sv = (StateVariable) v;
			h_size.setText(String.valueOf(sv.getHist()));
			i_val.setText(String.valueOf(v.getValue()));
		} else if (v instanceof Parameter) {
			type_combo.setSelectedIndex(1);
			Parameter p = (Parameter) v;
			i_val.setText(String.valueOf(p.getValue()));
		} else if (v instanceof Local) {
			type_combo.setSelectedIndex(2);
		} else if (v instanceof VarietyParameter) {
			type_combo.setSelectedIndex(3);
			VarietyParameter vp = (VarietyParameter) v;
			i_val.setText(String.valueOf(vp.getValue()));
			update_link_combo(vp);
		} else if (v instanceof VarietyConcentration) {
			type_combo.setSelectedIndex(4);
		} else if (v instanceof VarietyLocal) {
			type_combo.setSelectedIndex(5);
			VarietyLocal vl = (VarietyLocal) v;
			update_link_combo(vl);
		} else if (v instanceof VarietyVariable) {
			type_combo.setSelectedIndex(6);
			VarietyVariable vv = (VarietyVariable) v;
			h_size.setText(String.valueOf(vv.getHist()));
			i_val.setText(String.valueOf(vv.getValue()));
			update_link_combo(vv);
		}
		this.validate();
	}

	private void update_link_combo(Variety v) {
		if (food_sets.get(v.getLinkConcentration().getName()) != null) {
			Integer index = food_sets.get(v.getLinkConcentration().getName());
			link_combo.setSelectedIndex(index);
		}
	}

	public void update_selected_category(Catagory f) {
		this.current_category = f;
		this.update_food_sets(f.get_variety_concs());
	}
	
	private void update_food_sets(String[] food_array) {
		this.food_sets.clear();
		DefaultComboBoxModel model = (DefaultComboBoxModel) link_combo.getModel();
		model.removeAllElements();
		int index = 0;
		for (int i = 0; i < food_array.length; i++) {
			this.food_sets.put(food_array[i], index);
			model.addElement(food_array[i]);
			index++;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof VariableType) {
			
			display((VariableType) arg);
		}
		
	}
}
