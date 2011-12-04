package VEW.Planktonica2.StageEditor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class SelectStageDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 163080522470495910L;
	private StageEditorPanel parent;
	private boolean delete; // Is the selected stage to be deleted?
	private JTree stage_tree;
	
	public SelectStageDialog(StageEditorPanel parent,boolean delete) {
		super();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setTitle("Select a stage to " + (delete ? "delete" : "rename"));
		this.setMinimumSize(new Dimension(300,200));
		this.setPreferredSize(new Dimension(300,200));
		this.parent = parent;
		this.delete = delete;
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		stage_tree = new JTree(root);
		Collection<String> stages = parent.get_stages();
		for (String s : stages) {
			root.add(new DefaultMutableTreeNode(s));
		}
		stage_tree.setVisibleRowCount(6);
		stage_tree.expandRow(0);
		stage_tree.setRootVisible(false);
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 3;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		JScrollPane scroll_pane = new JScrollPane(stage_tree);
		scroll_pane.setPreferredSize(new Dimension(250,150));
		this.add(scroll_pane,c);
		JButton select = new JButton((delete ? "Delete" : "Rename"));
		select.addActionListener(new SelectDialogButtonListener(this,delete));
		select.setPreferredSize(new Dimension(50,50));
		c.weightx = 0;
		c.weighty = 0;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.CENTER;
		this.add(select,c);
	}

	public void delete_selected() {
		String select = stage_tree.getSelectionPath().getLastPathComponent().toString();
		if (select == null)
			return;
		if (select.equals("Default")) {
			JOptionPane.showMessageDialog(this, "Cannot delete the Default stage");
			return;
		}
		int choice = JOptionPane.showOptionDialog(this, "Are you sure you want to delete this stage?",
				"Confirm delete", JOptionPane.YES_NO_OPTION, 1, null, null, 1);
		if (choice == 1)
			return;
		parent.remove_stage(select);
		this.dispose();
	}

	public void rename_selected() {
		String select = stage_tree.getSelectionPath().getLastPathComponent().toString();
		if (select == null)
			return;
		if (select.equals("Default")) {
			JOptionPane.showMessageDialog(this, "Cannot rename the Default stage");
			return;
		}
		String name = JOptionPane.showInputDialog(this,
	        	"Choose a new name for the stage",
	            "Rename stage", 1);
	    if (name == null)
	    	return;
	    Collection<String> stages = parent.get_stages();
		for (String s : stages) {
			if (s.equals(name)) {
				JOptionPane.showMessageDialog(this, "There is already a stage with that name");
				return;
			}
		}
		parent.stage_rename(select,name);
		this.dispose();
	}

}
