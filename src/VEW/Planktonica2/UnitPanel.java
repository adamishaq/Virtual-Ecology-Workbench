package VEW.Planktonica2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VEW.Planktonica2.Model.Unit;

public class UnitPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003965164144786250L;
	private JLabel units = new JLabel("Unit name :  ");
	private JTextField unit_name = new JTextField("");
	private JLabel exp = new JLabel("Exponent :  ");
	private JTextField unit_exp = new JTextField("");
	private JEditorPane unit_list = new JEditorPane();
	
	private Collection<Unit> current_units = new ArrayList<Unit>();
	
	public UnitPanel() {
		super();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setPreferredSize(new Dimension(200,100));
		// Set up the unit view
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(units, c);
		c.ipadx = 75;
		c.gridx = 2;
		this.add(unit_name, c);
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 1;
		this.add(exp,c);
		c.ipadx = 75;
		c.gridx = 2;
		this.add(unit_exp,c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 0;
		c.fill = GridBagConstraints.NONE;
		JButton add_unit = new JButton(new ImageIcon(Display.IconRoot+ "add_unit.png"));
		add_unit.setContentAreaFilled(false);
		add_unit.addActionListener(new PanelAddUnitListener(this));
		this.add(add_unit,c);
		c.gridx = 1;
		JButton clear_units = new JButton(new ImageIcon(Display.IconRoot+ "delete_unit.png"));
		clear_units.setContentAreaFilled(false);
		clear_units.addActionListener(new PanelDeleteUnitListener(this));
		this.add(clear_units,c);
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 75;
		c.gridwidth = 2;
		c.gridx = 2;
		unit_list.setEditable(false);
		unit_list.setContentType("text/html");
		unit_list.setPreferredSize(new Dimension(75,50));
		unit_list.setText("");
		this.add(unit_list,c);
	}
	
	public Collection<Unit> getCurrentUnits() {
		return this.current_units;
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
		this.current_units = new ArrayList<Unit>();
		display_units();
	}
	
	public class PanelAddUnitListener implements ActionListener {

		private UnitPanel parent;
		
		public PanelAddUnitListener(UnitPanel u) {
			parent = u;
		}
		
		public void actionPerformed(ActionEvent event) {
	        parent.add_unit();
		}
		
	}
	
	public class PanelDeleteUnitListener implements ActionListener {

		private UnitPanel parent;
		
		public PanelDeleteUnitListener(UnitPanel u) {
			parent = u;
		}
		
		public void actionPerformed(ActionEvent event) {
	        parent.clear_units();
		}
		
	}
}