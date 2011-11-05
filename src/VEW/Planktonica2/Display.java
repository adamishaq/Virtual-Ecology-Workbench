package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.ListSelectionModel;

public abstract class Display extends JPanel {

	private static final long serialVersionUID = -3961634639923671255L;
	
	protected VEWController controller;
	
	protected JPanel topDisplay;
	protected int numRowsOfTopDisplay = 3;
	protected final String topPanelEmptySlot = "EmptyPanel"; 
	
	protected ModelDisplay modelDisplay;
	protected final JPanel variableSelection = new JPanel (new GridLayout(2, 2));
	
	protected final DefaultListModel itemList = new DefaultListModel();
	protected final DefaultListModel functionList = new DefaultListModel();
	protected JList items;
	protected JList functions;
	
	private static final String IconRoot = "Data"+File.separator+"Graphics"+File.separator+"icons"+File.separator;
	protected final Dimension STANDARD_BUTTON_SIZE = new Dimension(24, 24);
	protected final Dimension ALTERNATE_BUTTON_SIZE = new Dimension(new Dimension(150, 24));
	protected final Dimension STANDARD_GROUP_SIZE = new Dimension(250, 200);
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

	protected Display(VEWController controller, Dimension initialSize) {
		super();
		this.controller = controller;
		initialiseGUI(initialSize);
	}


	protected abstract String getCategoryName();
	
	
	 
	
	protected String getCurrentItem() {
		return items.getSelectedValue().toString();
	}
	protected String getCurrentFunction() {
		return functions.getSelectedValue().toString();
	}

	private void initialiseGUI(Dimension initialSize) {
		
		this.setLayout(new GridLayout(2, 1));
		this.setPreferredSize(initialSize);
		
		GridBagLayout gb = new GridBagLayout();
		topDisplay = new JPanel (gb);
		
		topDisplay.setBackground(Color.red);
		
		
		
		initialiseButtons();
		resetButtons(); 
		
		final JPanel topBoxes = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		populateItemPanel(topBoxes);
		populateFunctionPanel(topBoxes);
		
		GridBagConstraints constraints = new GridBagConstraints ();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.gridwidth = 2;
		constraints.weighty = 1;
		topDisplay.add(topBoxes, constraints);
		
		

		populateVariablePanel();
		
		
		
		this.add(topDisplay);
		
		// TODO: put model display in here.
		this.add(new JPanel ());
		

	}
	
	private void populateItemPanel(JPanel topBoxes) {
		
		final JPanel itemListPanel = new JPanel(new BorderLayout());
		itemListPanel.setPreferredSize(STANDARD_GROUP_SIZE);
		
		itemListPanel.add(new JLabel("Items"), BorderLayout.NORTH);
		
		// set up list of items
		this.items = new JList(itemList);
		items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		final JScrollPane scrollPane = new JScrollPane(this.items);
		scrollPane.setPreferredSize(new Dimension(120,100));
		itemListPanel.add(scrollPane, BorderLayout.CENTER);
		
		final JPanel itemButtons = new JPanel(new FlowLayout());
		addItemButtons(itemButtons);
		itemListPanel.add(itemButtons, BorderLayout.SOUTH);
		
		topBoxes.add(itemListPanel, BorderLayout.CENTER);
	}

	private void populateFunctionPanel(JPanel topBoxes) {
		
		final JPanel funcListPanel = new JPanel(new BorderLayout());
		funcListPanel.setPreferredSize(STANDARD_GROUP_SIZE);
		
		funcListPanel.add(new JLabel("Functions"), BorderLayout.NORTH);

		functions = new JList(functionList);
		functions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		final JScrollPane scrollPane = new JScrollPane(this.functions);
		scrollPane.setPreferredSize(new Dimension(120,100));
		funcListPanel.add(scrollPane, BorderLayout.CENTER);
		
		final JPanel funcButtons = new JPanel(new FlowLayout());
		addFunctionButtons(funcButtons);
		funcListPanel.add(funcButtons, BorderLayout.SOUTH);
		
		topBoxes.add(funcListPanel, BorderLayout.WEST);
	}
	
	private void populateVariablePanel() {
		
		varList = new JComboBox();
		varList.setPreferredSize(new Dimension(200, 20));
		
		variableSelection.add(varList);
		variableSelection.add(editVar);
		variableSelection.add(new JPanel ());
		variableSelection.add(new JPanel ());
		
		
		
		detailsHTML = new JEditorPane();
		detailsHTML.setContentType("text/html");
		detailsHTML.setText("<html><body>Variables go here!</body></html>");
		detailsHTML.setEditable(false);
		detailsHTML.setPreferredSize(new Dimension(300,100));
		
		final JPanel variableDetails = new JPanel(new BorderLayout());
		variableDetails.add(detailsHTML);
		
		// add variableSelection and variableDetails to topDisplay
		GridBagConstraints constraints = new GridBagConstraints ();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 0;
		
		topDisplay.add(variableSelection, constraints);
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridheight = 2;
		constraints.gridwidth = 1;
		
		
		topDisplay.add(variableDetails, constraints);
		
		
	}
	
	/**
	 * adds the correct buttons onto the itemPanel
	 * 
	 * @param itemPanel
	 */
	
	private void addItemButtons(JPanel itemPanel) {
		itemPanel.add(upFG);
		itemPanel.add(downFG);
		itemPanel.add(addInstance);
		itemPanel.add(renameInstance);
		itemPanel.add(removeInstance);
		itemPanel.add(copyInstance);
	}
	
	
	private void addFunctionButtons(JPanel functionPanel) {
		functionPanel.add(upFunc);
		functionPanel.add(downFunc);
		functionPanel.add(addFunction);
		functionPanel.add(renameFunction);
		functionPanel.add(editFunction);
		functionPanel.add(removeFunction);
		functionPanel.add(copyFunction);
	}
	


	private void initialiseButtons() {
		editVar = new JButton("Edit Var");
		editVar.setPreferredSize(ALTERNATE_BUTTON_SIZE);
		
		upFunc = new JButton(new ImageIcon(IconRoot+ "up.gif"));
		upFunc.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		downFunc = new JButton(new ImageIcon(IconRoot+ "down.gif"));
		downFunc.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		upFG = new JButton(new ImageIcon(IconRoot+ "up.gif"));
		upFG.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		downFG = new JButton(new ImageIcon(IconRoot+ "down.gif"));
		downFG.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		addInstance = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
		addInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		removeInstance = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
		removeInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		renameInstance = new JButton(new ImageIcon(IconRoot + "rename.gif"));
		renameInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		copyInstance = new JButton(new ImageIcon(IconRoot + "copy.gif"));
		copyInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		addFunction = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
		addFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		removeFunction = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
		removeFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		renameFunction = new JButton(new ImageIcon(IconRoot + "rename.gif"));
		renameFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		editFunction = new JButton(new ImageIcon(IconRoot + "edit.gif"));
		editFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		copyFunction = new JButton(new ImageIcon(IconRoot + "copy.gif"));		
		copyFunction.setPreferredSize(STANDARD_BUTTON_SIZE);		
		
	}
	
	
	
	protected void resetButtons() {
		
		addInstance.setEnabled(false);
		
		editVar.setEnabled(false);
		upFunc.setEnabled(false);
		downFunc.setEnabled(false);
		upFG.setEnabled(false);
		downFG.setEnabled(false);
		removeInstance.setEnabled(false);
		renameInstance.setEnabled(false);
		copyInstance.setEnabled(false);
		addFunction.setEnabled(false);
		removeFunction.setEnabled(false);
		renameFunction.setEnabled(false);
		editFunction.setEnabled(false);
		copyFunction.setEnabled(false);
	}


	
	protected void setButtonToolTips() {
		
		addInstance.setToolTipText("Add a new " + this.getCategoryName() + "?");
		
		String currentFunction = getCurrentFunction();
		
		if (currentFunction == null) {
			upFunc.setToolTipText("Move " + currentFunction + " up?");
			downFunc.setToolTipText("Move " + currentFunction + " down?");
			removeFunction.setToolTipText("Remove " + currentFunction + "?");
			renameFunction.setToolTipText("Rename " + currentFunction + "?");
			editFunction.setToolTipText("Edit " + currentFunction + "?");
			copyFunction.setToolTipText("Copy " + currentFunction + "?");
		}
		
		String currentItem = getCurrentItem();
		
		if (this.getCurrentItem() == null) {
			upFG.setToolTipText("Move " + currentItem + " up?");
			downFG.setToolTipText("Move " + currentItem + " down?");
			
			removeInstance.setToolTipText("Remove " + currentItem + "?");
			renameInstance.setToolTipText("Rename " + currentItem + "?");
			copyInstance.setToolTipText("Copy " + currentItem + "?");
			addFunction.setToolTipText("Add a new function to " + currentItem + "?");
		}
		
		
		
	}

	
}
