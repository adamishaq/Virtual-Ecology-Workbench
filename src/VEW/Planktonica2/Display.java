package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class Display extends JPanel {

	private static final long serialVersionUID = -3961634639923671255L;
	protected ModelDisplay ModelDisplay;
	protected JList items;
	protected JList functions;
	private static final String IconRoot = "Data"+File.separator+"Graphics"+File.separator+"icons"+File.separator;
	protected JComboBox varList;
	
	private JEditorPane detailsHTML;

	protected JButton editVar;
	protected JButton upFunc;
	protected JButton downFunc;
	protected JButton upFG;
	protected JButton downFG;
	protected JButton addInstance;
	protected JButton removeInstance;
	protected JButton renameInstance;
	protected JButton copyInstance;
	protected JButton addFunction;
	protected JButton removeFunction;
	protected JButton renameFunction;
	protected JButton editFunction;
	protected JButton copyFunction;

	/**
	 * Enum for the different lists
	 * @author Michael (Only)
	 *
	 */
	enum IconTypes {
		Items, Functions
	}

	protected Display() {
		super();
		initialiseButtons();
		initialiseGUI();
	}


	protected abstract String getCategoryName();
	protected String getCurrentItem() {
		return items.getSelectedValue().toString();
	}
	protected String getCurrentFunction() {
		return functions.getSelectedValue().toString();
	}

	private void initialiseGUI() {
		items = new JList(new DefaultListModel());
		functions = new JList(new DefaultListModel());
		final JPanel itemListPanel = new JPanel(new BorderLayout());
		final JPanel funcListPanel = new JPanel(new BorderLayout());
		final JPanel itemButtons = new JPanel(new FlowLayout());
		final JPanel funcButtons = new JPanel(new FlowLayout());

		final JPanel variableListPanel = new JPanel(new BorderLayout());
		final JPanel variableDetails = new JPanel(new BorderLayout());
		
		itemListPanel.add(new JLabel(IconTypes.Items.toString()), BorderLayout.NORTH);
		funcListPanel.add(new JLabel(IconTypes.Functions.toString()), BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(this.items);
		scrollPane.setPreferredSize(new Dimension(120,100));
		itemListPanel.add(scrollPane);
		scrollPane = new JScrollPane(this.functions);
		scrollPane.setPreferredSize(new Dimension(120,100));
		funcListPanel.add(scrollPane);

		addButtons(IconTypes.Items, itemButtons);
		addButtons(IconTypes.Functions, funcButtons);

		itemListPanel.add(itemButtons);
		funcListPanel.add(funcButtons);

		varList = new JComboBox();
		varList.setPreferredSize(new Dimension(200, 20));
	    
		variableListPanel.add(varList);
		variableListPanel.add(editVar);
		
		detailsHTML = new JEditorPane();
		detailsHTML.setContentType("text/html");
		detailsHTML.setText("<html><body>Variables go here!</body></html>");
		detailsHTML.setEditable(false);
		detailsHTML.setPreferredSize(new Dimension(300,100));
		
		variableDetails.add(detailsHTML);
		
		
		/* Add panels to this Dsplay JPanel */
		add(itemListPanel);
		add(funcListPanel);

	}

	/**
	 * Does what it says on the tin
	 * @param type - types Enum
	 * @param panel - JPanel to add buttons to
	 */
	private void addButtons(IconTypes type, JPanel panel) {
		switch (type) {
		case Items:
			panel.add(upFG);
			panel.add(downFG);
			panel.add(addInstance);
			panel.add(renameInstance);
			panel.add(removeInstance);
			panel.add(copyInstance);
			break;
		case Functions:
			panel.add(upFunc);
			panel.add(downFunc);
			panel.add(addFunction);
			panel.add(renameFunction);
			panel.add(editFunction);
			panel.add(removeFunction);
			panel.add(copyFunction);
			break;
		}
	}

	private void initialiseButtons() {
		editVar = new JButton("Edit Var");
		upFunc = new JButton(new ImageIcon(IconRoot+ "up.gif"));
		downFunc = new JButton(new ImageIcon(IconRoot+ "down.gif"));
		upFG = new JButton(new ImageIcon(IconRoot+ "up.gif"));
		downFG = new JButton(new ImageIcon(IconRoot+ "down.gif"));
		addInstance = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
		removeInstance = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
		renameInstance = new JButton(new ImageIcon(IconRoot + "rename.gif"));
		copyInstance = new JButton(new ImageIcon(IconRoot + "copy.gif"));
		addFunction = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
		removeFunction = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
		renameFunction = new JButton(new ImageIcon(IconRoot + "rename.gif"));
		editFunction = new JButton(new ImageIcon(IconRoot + "edit.gif"));
		copyFunction = new JButton(new ImageIcon(IconRoot + "copy.gif"));

		
	}


	protected void setButtonToolTips() {
		upFunc.setToolTipText("Move " + this.getCurrentFunction() + " up?");
		downFunc.setToolTipText("Move " + this.getCurrentFunction() + " down?");
		upFG.setToolTipText("Move " + this.getCurrentItem() + " up?");
		downFG.setToolTipText("Move " + this.getCurrentItem() + " down?");
		addInstance.setToolTipText("Add a new " + this.getCategoryName() + "?");
		removeInstance.setToolTipText("Remove " + this.getCurrentItem() + "?");
		renameInstance.setToolTipText("Rename " + this.getCurrentItem() + "?");
		copyInstance.setToolTipText("Copy " + this.getCurrentItem() + "?");
		addFunction.setToolTipText("Add a new function to " + this.getCurrentItem() + "?");
		removeFunction.setToolTipText("Remove " + this.getCurrentFunction() + "?");
		renameFunction.setToolTipText("Rename " + this.getCurrentFunction() + "?");
		editFunction.setToolTipText("Edit " + this.getCurrentFunction() + "?");
		copyFunction.setToolTipText("Copy " + this.getCurrentFunction() + "?");
	}



}
