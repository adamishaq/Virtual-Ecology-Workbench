package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

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

import VEW.Planktonica2.ControllerStructure.NewCategoryEvent;
import VEW.Planktonica2.ControllerStructure.SelectableItem;
import VEW.Planktonica2.ControllerStructure.SourcePath;
import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.DisplayEventHandlers.AddCategoryButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.AddFunctionButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.CheckButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.CompileButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.LeftPanelTreeSelectionListener;
import VEW.Planktonica2.DisplayEventHandlers.SaveButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.VariableSelectionEventHandler;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.UIComponents.VariableEditorPanel;

public abstract class Display extends JSplitPane implements Observer {

	private static final long serialVersionUID = -3961634639923671255L;
	
	protected VEWController controller;
	
	protected VariableEditorPanel variablePanel;
	protected EditorPanel editorPanel;
	
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
	//protected JButton editFunction;
	//protected JButton copyFunction;
	
	// Compiler buttons
	protected JButton compileButton;
	protected JButton checkButton;
	protected JButton previewButton;
	protected JButton saveButton;

	protected JList list;
	
	protected DefaultMutableTreeNode rootNode;
	private DefaultMutableTreeNode varRootNode;
	private JTabbedPane ancilaryFuncPane;

	final protected JPanel buttonPane = new JPanel ();

	protected JTree tree;
	protected JTree var_tree;
	
	protected Display(VEWController controller, Dimension initialSize) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		this.controller = controller;
		this.controller.addObserver(this);
		initialiseGUI(initialSize);
		
		fillGUI();
	}

	public void update(Observable o, Object arg) {
		
		if (arg instanceof SelectableItem) {
			this.update_vars((SelectableItem)arg);
		}
		if (arg instanceof Catagory) {
			Catagory f = (Catagory) controller.getSelectedItem();
			this.variablePanel.update_selected_category(f);
			//this.variablePanel.clear();
			this.editorPanel.clear();
		} else if (arg instanceof SourcePath) {
			this.ancilaryFuncPane.setSelectedIndex(0);
		} else if (arg instanceof VariableType) {
			this.variablePanel.display((VariableType)arg);
			this.ancilaryFuncPane.setSelectedIndex(1);
		} else if (arg instanceof NewCategoryEvent) {
			update_functions(((NewCategoryEvent)arg).getNew_category());
		}
		
	}

	protected abstract String getCategoryName();
	
	protected abstract void populateAncilaryFuncPane ();
	
	protected abstract void populateButtonPane ();
	
	protected void defaultPopulateButtonPane () {
		
		initialiseButtons();
		
		setButtonToolTips();
		
		this.addItemButtons(this.buttonPane);
		this.addFunctionButtons(this.buttonPane);
		
	}
	

	
	private void initialiseGUI(Dimension initialSize) {
		
		this.setPreferredSize(initialSize);
		
		
		
		JPanel leftPanel = new JPanel (new BorderLayout ());
		leftPanel.setMinimumSize(new Dimension (150, this.getHeight()));
		
		constructLeftPanel(leftPanel);
		
		
		
		
		JPanel rightPanel = new JPanel (new BorderLayout ());
		rightPanel.setMinimumSize(new Dimension (300, this.getHeight()));
		
		constructRightPanel(rightPanel);
		
		
		this.setDividerLocation(150);
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		
		this.populateAncilaryFuncPane();
		this.populateButtonPane();
		
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
		
		JScrollPane treeVeiwPane = new JScrollPane(tree);
		
		// set up variable list
		this.varRootNode = new DefaultMutableTreeNode ("Variables"); 
		
		var_tree = new JTree(this.varRootNode);
		this.var_tree.setRootVisible(false);
		var_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		var_tree.addTreeSelectionListener(new VariableSelectionEventHandler (this.controller));
		
		JScrollPane varVeiwPane = new JScrollPane(var_tree);
		
		// add two panels
		splitPane.setTopComponent(treeVeiwPane);
		splitPane.setBottomComponent(varVeiwPane);
		
		Dimension minSize = new Dimension (splitPane.getWidth(), 150);
		
		treeVeiwPane.setMinimumSize(minSize);
		varVeiwPane.setMinimumSize(minSize);
		
		splitPane.setDividerLocation(splitPane.getHeight() - 150);
		
		leftPanel.add(splitPane, BorderLayout.CENTER);
		
		
	}

	private void constructRightPanel(JPanel rightPanel) {
		
		
		JPanel layout = new JPanel (new GridBagLayout ());
		//BoxLayout box = new BoxLayout (layout, BoxLayout.Y_AXIS);
		//layout.setLayout(box);
		
		buttonPane.setLayout(new FlowLayout());
		
		//buttonPane.setPreferredSize(new Dimension (layout.getWidth(), 100));
		//buttonPane.setMaximumSize(new Dimension(layout.getWidth(), 200));
		
		
		GridBagConstraints g = new GridBagConstraints ();
		g.anchor = GridBagConstraints.NORTH;
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.NONE;
		g.weightx = 1;
		g.weighty = 0;
		
		layout.add(buttonPane, g);
		
		
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
		//itemPanel.add(copyInstance);
		itemPanel.add(compileButton);
		itemPanel.add(checkButton);
		itemPanel.add(saveButton);
	}
	
	
	private void addFunctionButtons(JPanel functionPanel) {
		functionPanel.add(upFunc);
		functionPanel.add(downFunc);
		functionPanel.add(addFunction);
		functionPanel.add(renameFunction);
		//functionPanel.add(editFunction);
		functionPanel.add(removeFunction);
		//functionPanel.add(copyFunction);
	}
	
	private void initialiseButtons() {
			
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
		addInstance.addActionListener(new AddCategoryButtonListener(this));
		
		removeInstance = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
		removeInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		renameInstance = new JButton(new ImageIcon(IconRoot + "rename.gif"));
		renameInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		copyInstance = new JButton(new ImageIcon(IconRoot + "copy.gif"));
		copyInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		addFunction = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
		addFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		addFunction.addActionListener(new AddFunctionButtonListener(this));
		
		removeFunction = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
		removeFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		renameFunction = new JButton(new ImageIcon(IconRoot + "rename.gif"));
		renameFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		/*
		editFunction = new JButton(new ImageIcon(IconRoot + "edit.gif"));
		editFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		copyFunction = new JButton(new ImageIcon(IconRoot + "copy.gif"));		
		copyFunction.setPreferredSize(STANDARD_BUTTON_SIZE);	
		*/
		compileButton = new JButton(new ImageIcon(IconRoot + "compile.gif"));		
		compileButton.setPreferredSize(STANDARD_BUTTON_SIZE);
		compileButton.addActionListener(new CompileButtonListener(this.editorPanel));
		
		checkButton = new JButton(new ImageIcon(IconRoot + "compile.gif"));		
		checkButton.setPreferredSize(STANDARD_BUTTON_SIZE);
		checkButton.addActionListener(new CheckButtonListener(this.editorPanel));
		
		saveButton = new JButton(new ImageIcon(IconRoot + "compile.gif"));
		saveButton.setPreferredSize(STANDARD_BUTTON_SIZE);
		saveButton.addActionListener(new SaveButtonListener(this.editorPanel));
		
	}
	
	protected void setButtonToolTips() {
		
		// TODO: set tool tips
		
		addInstance.setToolTipText("Add a new " + this.getCategoryName());
		
		String currentFunction = ""; // = getCurrentFunction();
		
		if (currentFunction != null) {
			upFunc.setToolTipText("Move " + currentFunction + " up");
			downFunc.setToolTipText("Move " + currentFunction + " down");
			removeFunction.setToolTipText("Remove " + currentFunction);
			renameFunction.setToolTipText("Rename " + currentFunction);
			//editFunction.setToolTipText("Edit " + currentFunction + "?");
			//copyFunction.setToolTipText("Copy " + currentFunction + "?");
		}
		
		String currentItem = ""; // = getCurrentItem();
		
		if (currentItem != null) {
			upFG.setToolTipText("Move " + currentItem + " up?");
			downFG.setToolTipText("Move " + currentItem + " down?");
			
			removeInstance.setToolTipText("Remove " + currentItem + "?");
			renameInstance.setToolTipText("Rename " + currentItem + "?");
			copyInstance.setToolTipText("Copy " + currentItem + "?");
			addFunction.setToolTipText("Add a new function to " + currentItem);
		}
		
		compileButton.setToolTipText("Compile the current model");
		checkButton.setToolTipText("Check the current source file");
		saveButton.setToolTipText("Save the current source file");
		
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
		//editFunction.setEnabled(false);
		//copyFunction.setEnabled(false);
	}

	private void update_functions(Catagory c) {
		fillFunctionTree();
		DefaultTreeModel t = (DefaultTreeModel) this.tree.getModel();
		t.setRoot(rootNode);
		this.tree.validate();
		// Set this to be focused
		this.variablePanel.update_selected_category(c);
		//this.variablePanel.clear();
		this.editorPanel.clear();
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
	
	public void addCategory(String name) {
		controller.addCategory(this,name);	
	}

	public void addFunction(String name) {
		controller.addFunction(this,name);
	}
	
}
