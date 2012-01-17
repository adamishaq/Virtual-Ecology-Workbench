package VEW.Planktonica2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DisplayOptionsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 163080522470495910L;
	private EditorPanel parent;
	
	JCheckBox maximised = new JCheckBox();
	JLabel layout_preview = new JLabel();
	// The layout preview images
	private ImageIcon vertical = new ImageIcon("Data/Graphics/icons/Preview.png");
	private ImageIcon horizontal = new ImageIcon("Data/Graphics/icons/Preview2.png");
	JRadioButton layout_horizontal = new JRadioButton("Horizontal",
			(!DisplayOptions.getOptions().LAYOUT_VERTICAL));
	JRadioButton layout_vertical = new JRadioButton("Vertical",
			DisplayOptions.getOptions().LAYOUT_VERTICAL);
    
	JCheckBox preview_units = new JCheckBox();
	JCheckBox preview_names = new JCheckBox();
	JCheckBox source_doc = new JCheckBox();
	
	JCheckBox convert_types = new JCheckBox();
	JCheckBox scale_types = new JCheckBox();
	
	public DisplayOptionsDialog(EditorPanel parent) {
		super();
		
		// Set up this dialog
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setTitle("Options");
		this.setMinimumSize(new Dimension(300,200));
		this.setPreferredSize(new Dimension(300,200));
		this.parent = parent;
		
		JTabbedPane option_tabs = new JTabbedPane();
		
		// Create the display panel and add it to the display tab
		JPanel display = new JPanel(new GridBagLayout());
		GridBagConstraints l_c = new GridBagConstraints();
		l_c.fill = GridBagConstraints.HORIZONTAL;
		l_c.gridx = 0;
		l_c.gridy = 0;
		if (DisplayOptions.getOptions().LAYOUT_VERTICAL) {
			layout_preview.setIcon(vertical);
		} else {
			layout_preview.setIcon(horizontal);
		}
		display.add(layout_preview,l_c);
		l_c.gridy = 2;
		layout_horizontal.addChangeListener(new LayoutChangeListener(this));
		display.add(layout_horizontal,l_c);
		l_c.gridx = 1;
		display.add(layout_vertical,l_c);
		// Make the two buttons mutually exclusive
		ButtonGroup group = new ButtonGroup();
	    group.add(layout_horizontal);
	    group.add(layout_vertical);
		option_tabs.add("Display",display);
		
		// Create the latex panel and add it to the latex tab
		JPanel latex = new JPanel(new GridBagLayout());
		l_c.gridx = 0;
		l_c.gridy = 0;
		preview_units.setSelected(DisplayOptions.getOptions().PREVIEW_UNITS);
		preview_units.setText("Show units in previews");
		latex.add(preview_units,l_c);
		l_c.gridy = 1;
		preview_names.setSelected(DisplayOptions.getOptions().PREVIEW_RULE_NAMES);
		preview_names.setText("Show rule names in previews");
		latex.add(preview_names,l_c);
		l_c.gridy = 2;
		source_doc.setSelected(DisplayOptions.getOptions().SOURCE_IN_LATEX);
		source_doc.setText("Show source code in LaTeX documentation");
		latex.add(source_doc,l_c);
		option_tabs.add("LaTeX",latex);
		
		// Create the units panel and add it to the units tab
		JPanel units = new JPanel(new GridBagLayout());
		l_c.gridx = 0;
		l_c.gridy = 0;
		convert_types.setSelected(DisplayOptions.getOptions().ATTEMPT_TYPE_CONVERSION);
		convert_types.setText("Attempt type conversion");
		convert_types.addChangeListener(new UnitChangeListener(this));
		units.add(convert_types,l_c);
		l_c.gridy = 1;
		scale_types.setEnabled(DisplayOptions.getOptions().ATTEMPT_TYPE_CONVERSION);
		scale_types.setSelected(DisplayOptions.getOptions().ATTEMPT_TYPE_CONVERSION
				&& DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING);
		scale_types.setText("Attempt type scaling");
		units.add(scale_types,l_c);
		l_c.gridy = 2;
		JButton edit_equivalences = new JButton("Edit Equivalences");
		edit_equivalences.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UnitEquivalencesDialog d = new UnitEquivalencesDialog();
				d.setAlwaysOnTop(true);
				d.setLocationRelativeTo(null);
				d.setResizable(false);
				d.setVisible(true);
			}
		});
		units.add(edit_equivalences,l_c);
		option_tabs.add("Units",units);
		
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 3;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		this.add(option_tabs,c);
		
		// Add the two button on the bottom of the dialog
		JPanel buttons = new JPanel();
		JButton apply = new JButton("Apply");
		apply.addActionListener(new ApplyListener(this));
		buttons.add(apply);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new CancelListener(this));
		buttons.add(cancel);
		c.weightx = 0;
		c.weighty = 0;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.CENTER;
		this.add(buttons,c);
	}
	
	public void apply() {
		DisplayOptions.getOptions().LAYOUT_VERTICAL = layout_vertical.isSelected();
		DisplayOptions.getOptions().PREVIEW_UNITS = preview_units.isSelected();
		DisplayOptions.getOptions().PREVIEW_RULE_NAMES = preview_names.isSelected();
		DisplayOptions.getOptions().SOURCE_IN_LATEX = source_doc.isSelected();
		DisplayOptions.getOptions().ATTEMPT_TYPE_CONVERSION = convert_types.isSelected();
		DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING 
			= convert_types.isSelected() && scale_types.isSelected();
		parent.apply_options();
		// Update the config file
		DisplayOptions.getOptions().write_config();
	}
	
	public void close() {
		this.dispose();
	}
	
	public class LayoutChangeListener implements ChangeListener {

		private DisplayOptionsDialog parent;
			
			public LayoutChangeListener(DisplayOptionsDialog parent) {
				this.parent = parent;
			}

			@Override
			public void stateChanged(ChangeEvent arg0) {
				parent.update_preview();
			}
			
		}
	
	public void update_preview() {
		if (layout_vertical.isSelected()) {
			layout_preview.setIcon(vertical);
		} else {
			layout_preview.setIcon(horizontal);
		}
	}
	
	public class UnitChangeListener implements ChangeListener {

		private DisplayOptionsDialog parent;
			
			public UnitChangeListener(DisplayOptionsDialog parent) {
				this.parent = parent;
			}

			@Override
			public void stateChanged(ChangeEvent arg0) {
				parent.update_units();
			}
			
		}
	
	public void update_units() {
		if (convert_types.isSelected()){
			scale_types.setEnabled(true);
		} else {
			scale_types.setSelected(false);
			scale_types.setEnabled(false);
		}
	}
	
	public class ApplyListener implements ActionListener {

		private DisplayOptionsDialog parent;
			
			public ApplyListener(DisplayOptionsDialog parent) {
				this.parent = parent;
			}
			
			public void actionPerformed(ActionEvent event) {
				parent.apply();
			}
			
		}
	
	public class CancelListener implements ActionListener {

		private DisplayOptionsDialog parent;
			
			public CancelListener(DisplayOptionsDialog parent) {
				this.parent = parent;
			}
			
			public void actionPerformed(ActionEvent event) {
				parent.close();
			}
			
		}
	
}
