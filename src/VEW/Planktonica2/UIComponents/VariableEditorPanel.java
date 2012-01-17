package VEW.Planktonica2.UIComponents;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VEW.Planktonica2.Display;
import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.DisplayEventHandlers.AddUnitButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.AddVarListener;
import VEW.Planktonica2.DisplayEventHandlers.ClearUnitsButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.DeleteVarListener;
import VEW.Planktonica2.DisplayEventHandlers.UpdateVarListener;
import VEW.Planktonica2.DisplayEventHandlers.VarTypeListener;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Local;
import VEW.Planktonica2.Model.Parameter;
import VEW.Planktonica2.Model.StateVariable;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.Model.Variety;
import VEW.Planktonica2.Model.VarietyConcentration;
import VEW.Planktonica2.Model.VarietyLocal;
import VEW.Planktonica2.Model.VarietyParameter;
import VEW.Planktonica2.Model.VarietyVariable;

/**
 * <code>JPanel</code> containing all the controls to add/delete/update variables in the model
 */
public class VariableEditorPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1484036714507019850L;
	
	private VarType current_selection = VarType.GROUPVAR;
	private Catagory current_category;
	private VariableType current_variable;
	private VEWController controller;
	private HashMap<String,Integer> food_sets = new HashMap<String,Integer>();
	private ArrayList<Unit> current_units = new ArrayList<Unit>();
	
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
	
	private JLabel units = new JLabel("  Unit name :  ");
	private JTextField unit_name = new JTextField("");
	private JLabel exp = new JLabel("  Exponent :  ");
	private JTextField unit_exp = new JTextField("");
	private JButton add_unit;
	private JButton clear_units;
	private JEditorPane unit_list = new JEditorPane();
	
	private JButton add_var = new JButton("Add");
	private JButton update_var = new JButton("Update");
	private JButton remove_var = new JButton("Remove");
	
	/**
	 * Create a new <code>JPanel</code> which will communicate with <code>controller</code>
	 * @param controller
	 */
	public VariableEditorPanel(VEWController controller) {
		this.controller = controller;
		this.controller.addObserver(this);
		this.initialize();
	}

	public VarType getCurrent_selection() {
		return current_selection;
	}

	/**
	 * Initialise all components and lay them out on the form
	 */
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
		unit_editor.add(unit_name, c);
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 1;
		unit_editor.add(exp,c);
		c.ipadx = 150;
		c.gridx = 2;
		unit_editor.add(unit_exp,c);
		
		// Set up the unit view
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 0;
		c.fill = GridBagConstraints.NONE;
		add_unit = new JButton(new ImageIcon(Display.IconRoot+ "add_unit.png"));
		add_unit.setContentAreaFilled(false);
		add_unit.addActionListener(new AddUnitButtonListener(this));
		unit_editor.add(add_unit,c);
		c.gridx = 1;
		clear_units = new JButton(new ImageIcon(Display.IconRoot+ "delete_unit.png"));
		clear_units.setContentAreaFilled(false);
		clear_units.addActionListener(new ClearUnitsButtonListener(this));
		unit_editor.add(clear_units,c);
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 100;
		c.gridwidth = 2;
		c.gridx = 2;
		unit_list.setEditable(false);
		unit_list.setContentType("text/html");
		unit_list.setPreferredSize(new Dimension(100,50));
		unit_list.setText("");
		unit_editor.add(unit_list,c);
		c.ipadx = 0;
		c.ipady = 0;
		
		// Add the panel to the main layout
		unit_editor.setPreferredSize(variable_info.getPreferredSize());
		main_layout.gridx = 1;
		main_layout.gridy = 0;
		this.add(unit_editor,main_layout);
		
		add_var.addActionListener(new AddVarListener(this));
		update_var.addActionListener(new UpdateVarListener(this));
		remove_var.addActionListener(new DeleteVarListener(this));
		button_panel.add(add_var);
		button_panel.add(update_var);
		button_panel.add(remove_var);
		// Add the panel to the main layout
		main_layout.gridwidth = 2;
		main_layout.ipady = 25;
		main_layout.gridx = 0;
		main_layout.gridy = 1;
		this.add(button_panel,main_layout);
	}
	
	/**
	 * Update the display based on the variable type selected in the <code>JComboBox</code>
	 * @param var_type - The type of variable selected
	 */
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
		variable_info.validate();
	}
	
	/**
	 * Creates a new <code>VariableType</code> object based on the values in the text boxes/combo boxes
	 * @return - A new <code>VariableType</code>
	 */
	public VariableType construct_variable() {
		// Check the variable has all appropriate values set
		if (!validate_variable(false))
			return null;
		// Construct the variable
		ArrayList<Unit> units = new ArrayList<Unit>();
		for (Unit u: current_units) {
			units.add(u);
		}
		if (units.isEmpty())
			units.add(new Unit(0,"dimensionless",1));
		current_units.clear();
		unit_list.setText("");
		String desc = var_desc.getText();
		// All of these things screw with the XML...
		desc = desc.replaceAll("<", "%ltag").replaceAll(">", "%rtag").replaceAll("&", "");
		switch (current_selection) {
		case GROUPVAR :
			StateVariable v = new StateVariable();
			v.setName(var_name.getText());
			v.setDesc(desc);
			v.setHist(Integer.parseInt(h_size.getText()));
			v.setValue(Float.parseFloat(i_val.getText()));
			v.setUnits(units);
			current_category.addToStateVarTable(v);
			this.current_variable = v;
			return v;
		case GROUPPARAM :
			Parameter p = new Parameter();
			p.setName(var_name.getText());
			p.setDesc(desc);
			p.setValue(Float.parseFloat(i_val.getText()));
			p.setUnits(units);
			current_category.addToParamTable(p);
			this.current_variable = p;
			return p;
		case LOCALVAR :
			Local l = new Local();
			l.setName(var_name.getText());
			l.setDesc(desc);
			l.setUnits(units);
			current_category.addToLocalTable(l);
			this.current_variable = l;
			return l;
		case FOODPARAM :
			// Make sure the currently selected category is not a chemical
			if (this.current_category instanceof FunctionalGroup) {
				VarietyParameter vp = new VarietyParameter((FunctionalGroup) this.current_category);
				vp.setName(var_name.getText());
				vp.setDesc(desc);
				vp.setValue(Float.parseFloat(i_val.getText()));
				vp.setUnits(units);
				// Check the food set link actually exists
				VarietyConcentration vc =
					this.current_category.checkVarietyConcTable(link_combo.getSelectedItem().toString());
				if (vc == null)
					return null;
				vp.setLinkConcentration(vc);
				current_category.addToVarietyParamTable(vp);
				this.current_variable = vp;
				return vp;
			}
		case FOODVAR :
			// Make sure the currently selected category is not a chemical
			if (this.current_category instanceof FunctionalGroup) {
				VarietyVariable vv = new VarietyVariable((FunctionalGroup) this.current_category);
				vv.setName(var_name.getText());
				vv.setDesc(desc);
				vv.setHist(Integer.parseInt(h_size.getText()));
				vv.setValue(Float.parseFloat(i_val.getText()));
				vv.setUnits(units);
				// Check the food set link actually exists
				VarietyConcentration vc =
					this.current_category.checkVarietyConcTable(link_combo.getSelectedItem().toString());
				if (vc == null)
					return null;
				vv.setLinkConcentration(vc);
				current_category.addToVarietyStateTable(vv);
				this.current_variable = vv;
				return vv;
			}
			break;
		case FOODSET :
			// Make sure the currently selected category is not a chemical
			if (this.current_category instanceof FunctionalGroup) {
				VarietyConcentration vc = new VarietyConcentration();
				vc.setName(var_name.getText());
				vc.setDesc(desc);
				vc.setUnits(units);
				current_category.addToVarietyConcTable(vc);
				this.current_variable = vc;
				// Add the food set to the available list
				DefaultComboBoxModel model = (DefaultComboBoxModel) link_combo.getModel();
				int index = model.getSize();
				this.food_sets.put(vc.getName(), index);
				model.addElement(vc.getName());
				return vc;
			}
			break;
		case FOODLOCAL :
			// Make sure the currently selected category is not a chemical
			if (this.current_category instanceof FunctionalGroup) {
				VarietyLocal vl = new VarietyLocal((FunctionalGroup) this.current_category);
				vl.setName(var_name.getText());
				vl.setDesc(desc);
				vl.setUnits(units);
				// Check the food set link actually exists
				VarietyConcentration vc =
					this.current_category.checkVarietyConcTable(link_combo.getSelectedItem().toString());
				if (vc == null)
					return null;
				vl.setLinkConcentration(vc);
				current_category.addToVarietyLocalTable(vl);
				this.current_variable = vl;
				return vl;
			}
		}
		return null;
	}

	/**
	 * Adds a variable to the model
	 * @param v - Variable to add
	 */
	public void add_var(VariableType v) {
		controller.addVariable(v);
	}
	
	/**
	 * Updates the information held about a variable in the current model
	 */
	public void update_variable() {
		// Check the variable has all appropriate values set
		if (!validate_variable(true))
			return;
		VariableType v = current_category.checkAllVariableTables(var_name.getText());
		if (v == null)
			v = this.current_variable;
		if (v == null)
			return;
		if (!v.isEditable()) {
			JOptionPane.showMessageDialog(this, "This is a built-in variable and cannot be edited");
			return;
		}
		if (v instanceof VarietyConcentration && current_selection != VarType.FOODSET && food_set_used(v))
			return;
		// Remove the previous version of the variable and add a new one
		if (v != null) {
			v = current_category.removeFromTables(v.getName());
			if (v instanceof VarietyConcentration) {
				int index = this.food_sets.get(v.getName());
				this.food_sets.remove(v.getName());
				DefaultComboBoxModel model = (DefaultComboBoxModel) link_combo.getModel();
				model.removeElementAt(index);
			}
			if (v != null) {
				construct_variable();
				controller.updateVariableDisplay();
				this.update_food_sets(controller.getSelectedCatagory().get_variety_concs());
			}
		}
	}
	
	/**
	 * Checks a valid <code>VariableType</code> could be constructed from the values provided by the user
	 * @param exists - Does the variable already exist in the model
	 */
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
	
	/**
	 * Fills the text boxes/combo boxes with the appropriate data from an existing variable
	 * @param v - Variable to display
	 */
	public void display(VariableType v) {
		this.current_variable = v;
		// Fill in the variable name and description
		var_name.setText(v.getName().replaceAll("\\$", "_"));
		var_name.setCaretPosition(0);
		var_desc.setText(v.getDesc().replaceAll("%ltag", "<").replaceAll("%rtag", ">"));
		var_desc.setCaretPosition(0);
		current_units.clear();
		// Display the units of the variable
		for (Unit u : v.getUnits()) {
			current_units.add(u);
		}
		display_units();
		String hist = "-";
		if (v.getHist() != null)
			hist = String.valueOf(v.getHist());
		String init = "-";
		if (v.getValue() != null)
			init = String.valueOf(v.getValue());
		// Check what type of variable it is
		if (v instanceof StateVariable) {
			type_combo.setSelectedIndex(0);
			h_size.setText(hist);
			i_val.setText(init);
		} else if (v instanceof Parameter) {
			type_combo.setSelectedIndex(1);
			i_val.setText(init);
		} else if (v instanceof Local) {
			type_combo.setSelectedIndex(2);
		} else if (v instanceof VarietyParameter) {
			type_combo.setSelectedIndex(3);
			VarietyParameter vp = (VarietyParameter) v;
			i_val.setText(init);
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
			h_size.setText(hist);
			i_val.setText(init);
			update_link_combo(vv);
		}
		this.unit_name.setText("");
		this.unit_exp.setText("");
		this.validate();
	}

	/**
	 * Displays the units of a variable in <code>HTML</code>
	 */
	private void display_units() {
		String text = "<html>";
		for (Unit u : current_units)
			if (!u.format().equals("-"))
				text += u.format();
		text += "</html>";
		unit_list.setText(text);
	}
	
	/**
	 * Ensures that an existing variable has the correct food set link displayed
	 * @param v
	 */
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
	
	/**
	 * Updates the food set link combo box with the currently available food sets
	 * @param food_array - Array of strings giving names of every food set
	 */
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

	/**
	 * Adds a unit that the user has described to the current variable if possible
	 */
	public void add_unit() {
		if (!validate_unit())
			return;
		String name = unit_name.getText();
		int exponent = Integer.parseInt(unit_exp.getText());
		for (Unit u: current_units) {
			if (u.getName().equals(name)) {
				u.setExponent(u.getExponent() + exponent);
				if (u.getExponent() == 0) {
					current_units.remove(u);
				}
				display_units();
				return;
			}
		}
		current_units.add(new Unit(0,name,exponent));
		display_units();
	}
	
	/**
	 * Check a user defined unit is valid
	 */
	private boolean validate_unit() {
		if (unit_name.getText().length() < 1)
			return false;
		for (int i = 0; i < unit_name.getText().length(); i++) {
			if (!Character.isLetter(unit_name.getText().charAt(i))) {
				JOptionPane.showMessageDialog(this, "Unit names may only contain letters");
				return false;
			}
		}
		try {
			int ex = Integer.parseInt(unit_exp.getText());
			if (ex == 0)
				return false;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Invalid unit exponent");
			return false;
		}
		return true;
	}
	
	public void clear_units() {
		this.current_units.clear();
		display_units();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof VariableType) {
			
			display((VariableType) arg);
		}
		
	}

	/**
	 * Delete the current variable from the model
	 */
	public void delete_variable() {
		if (!validate_variable(true))
			return;
		VariableType v = current_category.checkAllVariableTables(var_name.getText());
		if (v == null)
			return;
		if (!v.isEditable())
			return;
		// Check the user really wants to delete this variable
		int choice = JOptionPane.showOptionDialog(this, "Are you sure you want to delete the variable "
				+ v.getName() + "?",
			"Confirm delete", JOptionPane.YES_NO_OPTION, 1, null, null, 1);
		if (choice == 1)
			return;
		if (v instanceof VarietyConcentration && food_set_used(v))
			return;
		controller.delete_variable(v);
		if (v instanceof VarietyConcentration) {
			this.update_food_sets(controller.getSelectedCatagory().get_variety_concs());
		}
	}

	private boolean food_set_used(VariableType v) {
		String variables = "";
		boolean found = false;
		Catagory c = controller.getSelectedCatagory();
		for (String s : c.get_variety_locals()) {
			VarietyLocal vl = c.checkVarietyLocalTable(s);
			if (vl.getLinkConcentration().equals(v)) {
				variables += vl.getName() + ",";
				found = true;
			}
		}
		for (String s : c.get_variety_params()) {
			VarietyParameter vp = c.checkVarietyParamTable(s);
			if (vp.getLinkConcentration().equals(v)) {
				variables += vp.getName() + ",";
				found = true;
			}
		}
		for (String s : c.get_variety_states()) {
			VarietyVariable vv = c.checkVarietyStateTable(s);
			if (vv.getLinkConcentration().equals(v)) {
				variables += vv.getName() + ",";
				found = true;
			}
		}
		if (found) {
			variables = variables.substring(0,variables.length()-1);
			JOptionPane.showMessageDialog(this, "Cannot remove this food set as it is used by the following" +
					" variables: " + variables);
		}
		return found;
	}
}
