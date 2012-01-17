package VEW.Planktonica2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import VEW.Planktonica2.Model.UnitChecker;

public class UnitEquivalencesDialog extends JDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 733617142938376686L;
	
	UnitPanel first_unit = new UnitPanel();
	UnitPanel second_unit = new UnitPanel();
	private JTextField scale = new JTextField();
	private JTree equivalence_tree;
	
	public UnitEquivalencesDialog() {
		super();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setTitle("Unit Equivalence Editor");
		this.setMinimumSize(new Dimension(600,400));
		this.setPreferredSize(new Dimension(600,400));
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		equivalence_tree = new JTree(root);
		Collection<String> equivs = UnitChecker.getUnitChecker().getEquivalences();
		for (String s : equivs) {
			root.add(new DefaultMutableTreeNode(s));
		}
		equivalence_tree.setVisibleRowCount(6);
		equivalence_tree.expandRow(0);
		equivalence_tree.setRootVisible(false);
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 4;
		c.gridheight = 3;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		JScrollPane scroll_pane = new JScrollPane(equivalence_tree);
		scroll_pane.setPreferredSize(new Dimension(250,150));
		this.add(scroll_pane,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		this.add(first_unit,c);
		
		c.weightx = 0;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 4;
		JLabel scale_label = new JLabel("  Scale:");
		this.add(scale_label,c);
		c.gridx = 2;
		c.ipadx = 75;
		this.add(scale,c);
		
		c.ipadx = 0;
		c.weightx = 1;
		c.gridheight = 3;
		c.gridx = 3;
		c.gridy = 3;
		this.add(second_unit,c);
		
		JPanel buttons = new JPanel();
		JButton apply = new JButton("Add");
		apply.addActionListener(new AddListener(this));
		buttons.add(apply);
		JButton delete = new JButton("Delete");
		delete.addActionListener(new DeleteListener(this));
		buttons.add(delete);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new CancelListener(this));
		buttons.add(cancel);
		c.weightx = 0;
		c.weighty = 0;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.CENTER;
		this.add(buttons,c);
	}
	
	public void add() {
		if (first_unit.getCurrentUnits() != null && !first_unit.getCurrentUnits().isEmpty()
				&& second_unit.getCurrentUnits() != null && !second_unit.getCurrentUnits().isEmpty()) {
			try {
				float scale_factor = 1;
				if (!scale.getText().equals("")) {
					scale_factor = Float.parseFloat(scale.getText());
					if (scale_factor == 0)
						return;
				}
				if (!UnitChecker.getUnitChecker().add_equivalence(first_unit.getCurrentUnits(),
						scale_factor,second_unit.getCurrentUnits())) {
					JOptionPane.showMessageDialog(this, "An equivalence between those two unit types" +
							" already exists");
					return;
				}
				DefaultTreeModel t = (DefaultTreeModel) equivalence_tree.getModel();
				DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
				Collection<String> equivs = UnitChecker.getUnitChecker().getEquivalences();
				for (String s : equivs) {
					root.add(new DefaultMutableTreeNode(s));
				}
				t.setRoot(root);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Invalid scaling factor");
			}
		}
	}
	
	public void delete() {
		int remove = equivalence_tree.getSelectionModel().getLeadSelectionRow();
		if (remove >= 0) {
			UnitChecker.getUnitChecker().remove_base_equivalence(remove);
			DefaultTreeModel t = (DefaultTreeModel) equivalence_tree.getModel();
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
			Collection<String> equivs = UnitChecker.getUnitChecker().getEquivalences();
			for (String s : equivs) {
				root.add(new DefaultMutableTreeNode(s));
			}
			t.setRoot(root);
		}
	}

	public void close() {
		this.dispose();
	}
	
	public class AddListener implements ActionListener {

		private UnitEquivalencesDialog parent;
			
			public AddListener(UnitEquivalencesDialog parent) {
				this.parent = parent;
			}
			
			public void actionPerformed(ActionEvent event) {
				parent.add();
			}
			
		}
	
	public class DeleteListener implements ActionListener {

		private UnitEquivalencesDialog parent;
			
			public DeleteListener(UnitEquivalencesDialog parent) {
				this.parent = parent;
			}
			
			public void actionPerformed(ActionEvent event) {
				parent.delete();
			}
			
		}
	
	public class CancelListener implements ActionListener {

		private UnitEquivalencesDialog parent;
			
			public CancelListener(UnitEquivalencesDialog parent) {
				this.parent = parent;
			}
			
			public void actionPerformed(ActionEvent event) {
				parent.close();
			}
			
		}

}
