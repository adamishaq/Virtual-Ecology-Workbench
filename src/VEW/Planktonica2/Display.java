package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import VEW.Planktonica2.ControllerStructure.SelectableItem;
import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.DisplayEventHandlers.FGButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.LeftPanelTreeSelectionListener;
import VEW.Planktonica2.DisplayEventHandlers.VariableSelectionEventHandler;
import VEW.Planktonica2.DisplayEventHandlers.ButtonCommandNamesEnum;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.VariableType;

public abstract class Display extends JSplitPane {

	private static final long serialVersionUID = -3961634639923671255L;
	
	protected VEWController controller;
	
	protected JPanel topDisplay;
	protected int numRowsOfTopDisplay = 3;
	protected final String topPanelEmptySlot = "EmptyPanel"; 
	
	protected final JPanel variableSelection = new JPanel (new GridLayout(2, 2));
	
	private static final String IconRoot = "Data"+File.separator+"Graphics"+File.separator+"icons"+File.separator;
	protected final Dimension STANDARD_BUTTON_SIZE = new Dimension(24, 24);
	protected final Dimension ALTERNATE_BUTTON_SIZE = new Dimension(new Dimension(150, 24));
	protected final Dimension STANDARD_GROUP_SIZE = new Dimension(250, 200);
	
	
	

	
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

	protected JList list;
	
	protected DefaultMutableTreeNode rootNode;
	private DefaultMutableTreeNode varRootNode;
	private JTabbedPane ancilaryFuncPane;

	//final protected JPanel buttonPane = new JPanel ();
	protected JPanel treeButtonPanel = new JPanel(new FlowLayout());

	protected JTree tree;
	protected JTree var_tree;
	
	protected Display(VEWController controller, Dimension initialSize) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		this.controller = controller;
		this.controller.setDisplay(this);
		initialiseGUI(initialSize);
		
		fillGUI();
	}




	protected abstract String getCategoryName();
	
	protected abstract void populateAncilaryFuncPane ();
	
	protected abstract void populateButtonPane ();
	 
	public abstract void updateVariablePanel(VariableType v);
	
	protected void defaultPopulateButtonPane () {
		
		initialiseButtons();
		
		//setButtonToolTips();
		
		//this.addItemButtons(this.buttonPane);
		//this.addFunctionButtons(this.buttonPane);
		
	}
	

	
	private void initialiseGUI(Dimension initialSize) {
		
		this.setPreferredSize(initialSize);
		
		
		this.populateButtonPane();
		
		JPanel leftPanel = new JPanel (new BorderLayout ());
		leftPanel.setMinimumSize(new Dimension (170, this.getHeight()));
		
		constructLeftPanel(leftPanel);
		
		
		
		
		JPanel rightPanel = new JPanel (new BorderLayout ());
		rightPanel.setMinimumSize(new Dimension (300, this.getHeight()));
		
		constructRightPanel(rightPanel);
		
		
		this.setDividerLocation(170);
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		
		this.populateAncilaryFuncPane();
		
		
		//this.ancilaryFuncPane.setEnabled(false);
		
	}
	
	


	private void constructLeftPanel(JPanel leftPanel) {
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.1);
		
		
		// sets up the functional group/chemical tree
		this.rootNode = new DefaultMutableTreeNode ("root"); 
		
		tree = new JTree(this.rootNode);
		
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		tree.addTreeSelectionListener(new LeftPanelTreeSelectionListener (this.controller));
		
		JScrollPane treeViewPane = new JScrollPane(tree);
		
		// set up variable list
		this.varRootNode = new DefaultMutableTreeNode ("Variables"); 
		
		var_tree = new JTree(this.varRootNode);
		this.var_tree.setRootVisible(false);
		var_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		var_tree.addTreeSelectionListener(new VariableSelectionEventHandler (this.controller));
		
		JScrollPane varViewPane = new JScrollPane(var_tree);
		
		
		JSplitPane groupPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		groupPane.setDividerLocation(groupPane.getHeight()-60);
		groupPane.setDividerSize(0);
		groupPane.setResizeWeight(1);
		
		this.addItemButtons(treeButtonPanel);
		groupPane.setBottomComponent(treeButtonPanel);
		groupPane.setTopComponent(treeViewPane);
		
		// add two panels
		splitPane.setTopComponent(groupPane);
		splitPane.setBottomComponent(varViewPane);
		
		Dimension minSize = new Dimension (splitPane.getWidth(), 150);
		
		treeViewPane.setMinimumSize(minSize);
		varViewPane.setMinimumSize(minSize);
		
		splitPane.setDividerLocation(splitPane.getHeight() - 250);
		
		leftPanel.add(splitPane, BorderLayout.CENTER);
		
		
	}

	private void constructRightPanel(JPanel rightPanel) {
		
		
		JPanel layout = new JPanel (new GridBagLayout ());
		//BoxLayout box = new BoxLayout (layout, BoxLayout.Y_AXIS);
		//layout.setLayout(box);
		
		//buttonPane.setLayout(new FlowLayout());
		
		//buttonPane.setPreferredSize(new Dimension (layout.getWidth(), 100));
		//buttonPane.setMaximumSize(new Dimension(layout.getWidth(), 200));
		
		
		GridBagConstraints g = new GridBagConstraints ();
		g.anchor = GridBagConstraints.NORTH;
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.NONE;
		g.weightx = 1;
		g.weighty = 0;
		
		//layout.add(buttonPane, g);
		
		
		this.ancilaryFuncPane = new JTabbedPane ();
		
		
		g.anchor = GridBagConstraints.NORTH;
		g.gridx = 0;
		g.gridy = 1;
		g.fill = GridBagConstraints.BOTH;
		g.weightx = 1;
		g.weighty = 1;
		
		layout.add(ancilaryFuncPane, g);
		
		rightPanel.add(layout, BorderLayout.CENTER);
		
	}
	
	
	protected void addTabToAncilary (String name, JPanel p) {
		this.ancilaryFuncPane.add(name, p);
	}
	
	
	private void addItemButtons(JPanel itemPanel) {
		itemPanel.add(upFG);
		itemPanel.add(downFG);
		itemPanel.add(addInstance);
		itemPanel.add(renameInstance);
		itemPanel.add(removeInstance);
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
		
		
		upFG = new JButton(new ImageIcon(IconRoot+ "up.gif"));
		upFG.setPreferredSize(STANDARD_BUTTON_SIZE);
		upFG.setActionCommand(ButtonCommandNamesEnum.MOVE_UP.toString());
		
		downFG = new JButton(new ImageIcon(IconRoot+ "down.gif"));
		downFG.setPreferredSize(STANDARD_BUTTON_SIZE);
		downFG.setActionCommand(ButtonCommandNamesEnum.MOVE_DOWN.toString());
		
		addInstance = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
		addInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		addInstance.setActionCommand(ButtonCommandNamesEnum.ADD_INSTANCE.toString());
		
		removeInstance = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
		removeInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		removeInstance.setActionCommand(ButtonCommandNamesEnum.REMOVE_INSTANCE.toString());
		
		renameInstance = new JButton(new ImageIcon(IconRoot + "rename.gif"));
		renameInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		renameInstance.setActionCommand(ButtonCommandNamesEnum.RENAME_INSTANCE.toString());
		
		/*
		upFunc = new JButton(new ImageIcon(IconRoot+ "up.gif"));
		upFunc.setPreferredSize(STANDARD_BUTTON_SIZE);
		upFunc.setActionCommand(ButtonCommandNamesEnum.UPFUNC.toString());
		upFunc.addActionListener(funcButtonListener);
		
		downFunc = new JButton(new ImageIcon(IconRoot+ "down.gif"));
		downFunc.setPreferredSize(STANDARD_BUTTON_SIZE);
		downFunc.setActionCommand(ButtonCommandNamesEnum.DOWNFUNC.toString());
		downFunc.addActionListener(funcButtonListener);
		
		copyInstance = new JButton(new ImageIcon(IconRoot + "copy.gif"));
		copyInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		copyInstance.setActionCommand(ButtonCommandNamesEnum.COPY_INSTANCE.toString());
		copyInstance.addActionListener(fgButtonListener);
		
		addFunction = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
		addFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		addFunction.setActionCommand(ButtonCommandNamesEnum.ADD_FUNC.toString());
		addFunction.addActionListener(funcButtonListener);
		
		removeFunction = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
		removeFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		removeFunction.setActionCommand(ButtonCommandNamesEnum.REMOVE_FUNC.toString());
		removeFunction.addActionListener(funcButtonListener);
		
		renameFunction = new JButton(new ImageIcon(IconRoot + "rename.gif"));
		renameFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		renameFunction.setActionCommand(ButtonCommandNamesEnum.RENAME_FUNC.toString());
		renameFunction.addActionListener(funcButtonListener);
		
		editFunction = new JButton(new ImageIcon(IconRoot + "edit.gif"));
		editFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		editFunction.setActionCommand(ButtonCommandNamesEnum.EDIT_FUNC.toString());
		editFunction.addActionListener(funcButtonListener);
		
		copyFunction = new JButton(new ImageIcon(IconRoot + "copy.gif"));		
		copyFunction.setPreferredSize(STANDARD_BUTTON_SIZE);		
		copyFunction.setActionCommand(ButtonCommandNamesEnum.COPY_FUNC.toString());
		copyFunction.addActionListener(funcButtonListener);*/
	}
	
	protected void setButtonToolTips() {
		
		// TODO: set tool tips
		
		addInstance.setToolTipText("Add a new " + this.getCategoryName() + "?");
		
		String currentFunction = ""; // = getCurrentFunction();
		
		if (currentFunction != null) {
			upFunc.setToolTipText("Move " + currentFunction + " up?");
			downFunc.setToolTipText("Move " + currentFunction + " down?");
			removeFunction.setToolTipText("Remove " + currentFunction + "?");
			renameFunction.setToolTipText("Rename " + currentFunction + "?");
			editFunction.setToolTipText("Edit " + currentFunction + "?");
			copyFunction.setToolTipText("Copy " + currentFunction + "?");
		}
		
		String currentItem = ""; // = getCurrentItem();
		
		if (currentItem != null) {
			upFG.setToolTipText("Move " + currentItem + " up?");
			downFG.setToolTipText("Move " + currentItem + " down?");
			
			removeInstance.setToolTipText("Remove " + currentItem + "?");
			renameInstance.setToolTipText("Rename " + currentItem + "?");
			copyInstance.setToolTipText("Copy " + currentItem + "?");
			addFunction.setToolTipText("Add a new function to " + currentItem + "?");
		}
		
		
		
	}
	
	
	

	private void fillGUI() {
		// TODO Auto-generated method stub
		fillFunctionTree();
	}
	
	protected void fillFunctionTree() {
		
		controller.populateFunctionTree(rootNode);
		
		this.tree.expandRow(0);
		this.tree.setRootVisible(false);
		
	}


	
	protected void resetButtons() {
		
		addInstance.setEnabled(false);
		
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


	public void update_vars(SelectableItem i) {
		
		varRootNode.removeAllChildren();
		if (i instanceof Catagory) {
			Catagory c = (Catagory) i;
			// State Variables
			DefaultMutableTreeNode heading = new DefaultMutableTreeNode("State Variables");
			String[] vars = c.get_state_vars();
			add_table_vars(heading, vars);
			// Local Variables
			heading = new DefaultMutableTreeNode("Local Variables");
			vars = c.get_local_vars();
			add_table_vars(heading, vars);
			// Parameters
			heading = new DefaultMutableTreeNode("Parameters");
			vars = c.get_params();
			add_table_vars(heading, vars);
			// FoodSets
			heading = new DefaultMutableTreeNode("Food Sets/Concentrations");
			vars = c.get_variety_concs();
			add_table_vars(heading, vars);
			// Variety State Variables
			heading = new DefaultMutableTreeNode("Variety State Variables");
			vars = c.get_variety_states();
			add_table_vars(heading, vars);
			// Variety Local Variables
			heading = new DefaultMutableTreeNode("Variety Local Variables");
			vars = c.get_variety_locals();
			add_table_vars(heading, vars);
			// Variety Parameters
			heading = new DefaultMutableTreeNode("Variety Parameters");
			vars = c.get_variety_params();
			add_table_vars(heading, vars);
		}
		this.var_tree.expandRow(0);
		this.var_tree.setRootVisible(false);
		DefaultTreeModel t = (DefaultTreeModel) this.var_tree.getModel();
		t.setRoot(varRootNode);
		this.var_tree.validate();
	}




	private void add_table_vars(DefaultMutableTreeNode heading, String[] vars) {
		for (int j = 0; j < vars.length; j++) {
			heading.add(new DefaultMutableTreeNode(vars[j]));
		}
		if (vars.length > 0)
			varRootNode.add(heading);
	}

	public String get_selected_function() {
		return tree.getSelectionPath().getLastPathComponent().toString();
	}
}
