package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import VEW.Planktonica2.ControllerStructure.DeleteCategoryEvent;
import VEW.Planktonica2.ControllerStructure.DeleteFunctionEvent;
import VEW.Planktonica2.ControllerStructure.DeleteVariableEvent;
import VEW.Planktonica2.ControllerStructure.NewCategoryEvent;
import VEW.Planktonica2.ControllerStructure.NewFunctionEvent;
import VEW.Planktonica2.ControllerStructure.NewVariableEvent;
import VEW.Planktonica2.ControllerStructure.SelectableItem;
import VEW.Planktonica2.ControllerStructure.SourcePath;
import VEW.Planktonica2.ControllerStructure.UpdateCategoryEvent;
import VEW.Planktonica2.ControllerStructure.UpdateVariableEvent;
import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.DisplayEventHandlers.AddCategoryButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.AddFunctionButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.CheckButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.CompileButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.ExportTexButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.ImportSourceButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.LeftPanelTreeSelectionListener;
import VEW.Planktonica2.DisplayEventHandlers.MoveDownButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.MoveUpButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.DeleteButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.OptionsButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.RenameButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.SaveButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.SwitchLayoutButtonListener;
import VEW.Planktonica2.DisplayEventHandlers.VariableSelectionEventHandler;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.Local;
import VEW.Planktonica2.Model.Parameter;
import VEW.Planktonica2.Model.StateVariable;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.Model.VarietyConcentration;
import VEW.Planktonica2.Model.VarietyLocal;
import VEW.Planktonica2.Model.VarietyParameter;
import VEW.Planktonica2.Model.VarietyVariable;
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
	
	public static final String IconRoot = "Data"+File.separator+"Graphics"+File.separator+"icons"+File.separator;
	public final static Dimension STANDARD_BUTTON_SIZE = new Dimension(20, 20);
	protected final Dimension ALTERNATE_BUTTON_SIZE = new Dimension(new Dimension(150, 24));
	protected final Dimension STANDARD_GROUP_SIZE = new Dimension(250, 200);
	
	protected JButton upFG;
	protected JButton downFG;
	protected JButton addInstance;
	protected JButton removeInstance;
	protected JButton renameInstance;
	protected JButton copyInstance;
	protected JButton addFunction;
	
	// Compiler buttons
	protected JButton compileButton;
	protected JButton checkButton;
	protected JButton previewButton;
	protected JButton saveButton;
	protected JButton importSourceButton;
	protected JButton exportTexButton;
	protected JButton optionsButton;
	protected JButton switchLayoutButton;

	
	public DefaultMutableTreeNode rootNode;
	private DefaultMutableTreeNode varRootNode;
	private JTabbedPane ancilaryFuncPane;

	protected JPanel buttonPane;
	protected JPanel treeButtonPanel = new JPanel(new FlowLayout());

	public JTree tree;
	protected JTree var_tree;
	
	protected Display(VEWController controller, Dimension initialSize) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		this.controller = controller;
		this.controller.addObserver(this);
		initialiseGUI(initialSize);
		
		fillGUI();
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof SelectableItem) {
			this.update_vars((SelectableItem)arg);
		}
		if (arg instanceof Catagory) {
			Catagory f = (Catagory) controller.getSelectedCatagory();
			this.variablePanel.update_selected_category(f);
			this.editorPanel.clear();
			this.saveButton.setEnabled(false);
			this.checkButton.setEnabled(false);
		} else if (arg instanceof SourcePath) {
			this.ancilaryFuncPane.setSelectedIndex(0);
			this.saveButton.setEnabled(true);
			this.checkButton.setEnabled(true);
		} else if (arg instanceof VariableType) {
			this.variablePanel.display((VariableType)arg);
			this.ancilaryFuncPane.setSelectedIndex(1);
		} else if (arg instanceof NewCategoryEvent) {
			add_category(((NewCategoryEvent)arg).getNew_category());
		} else if (arg instanceof NewVariableEvent) {
			add_variable(((NewVariableEvent)arg).getVar());
		} else if (arg instanceof NewFunctionEvent) {
			add_function((NewFunctionEvent)arg);
			this.saveButton.setEnabled(true);
			this.checkButton.setEnabled(true);
		} else if (arg instanceof UpdateCategoryEvent) {
			update_functions(((UpdateCategoryEvent)arg).getCategory());
		} else if (arg instanceof UpdateVariableEvent) {
			SelectableItem s = controller.getSelectedCatagory();
			this.update_vars(s);
		} else if (arg instanceof DeleteCategoryEvent) {
			remove_category(((DeleteCategoryEvent)arg).getCategory());
		} else if (arg instanceof DeleteFunctionEvent) {
			remove_function((DeleteFunctionEvent)arg);
		} else if (arg instanceof DeleteVariableEvent) {
			remove_variable((DeleteVariableEvent) arg);
		}
		
	}

	protected abstract String getCategoryName();
	
	protected abstract void populateAncilaryFuncPane ();
	
	protected abstract void populateButtonPane ();
	
	protected void defaultPopulateButtonPane () {
		
		initialiseButtons();
		
		setButtonToolTips();
		
		addItemButtons(treeButtonPanel);
		
		addCompilerButtons(buttonPane);
	}
	

	
	private void initialiseGUI(Dimension initialSize) {
		
		this.setPreferredSize(initialSize);
		
		JPanel leftPanel = new JPanel (new BorderLayout ());
		leftPanel.setMinimumSize(new Dimension (170, this.getHeight()));
		
		constructLeftPanel(leftPanel);
		
		
		
		
		JPanel rightPanel = new JPanel (new BorderLayout ());
		rightPanel.setMinimumSize(new Dimension (300, this.getHeight()));
		
		constructRightPanel(rightPanel);
		
		
		this.setDividerLocation(DisplayOptions.getOptions().SIDE_PANEL_SIZE);
		this.addPropertyChangeListener(new DisplayChangeListener(this));
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		
		this.populateAncilaryFuncPane();
		
		this.populateButtonPane();
	}
	
	


	private void constructLeftPanel(JPanel leftPanel) {
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.1);
		
		
		// sets up the functional group/chemical tree
		this.rootNode 
			= new DefaultMutableTreeNode (
					this instanceof ChemicalDisplay ? "Chemicals" : "Functional Groups"); 
		
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
		
		
		JPanel layout = new JPanel (new GridBagLayout());
		
		
		GridBagConstraints g = new GridBagConstraints ();
		g.anchor = GridBagConstraints.NORTH;
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.NONE;
		g.weightx = 1;
		g.weighty = 0;
		
		buttonPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
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
		itemPanel.add(addFunction);
		itemPanel.add(renameInstance);
		itemPanel.add(removeInstance);
	}
	
	private void addCompilerButtons(JPanel compilerButtonPanel) {
		compilerButtonPanel.add(compileButton);
		compilerButtonPanel.add(checkButton);
		compilerButtonPanel.add(saveButton);
		compilerButtonPanel.add(importSourceButton);
		compilerButtonPanel.add(exportTexButton);
		compilerButtonPanel.add(optionsButton);
		//compilerButtonPanel.add(switchLayoutButton);
	}
	
	private void initialiseButtons() {
		
		
		upFG = new JButton(new ImageIcon(IconRoot+ "up.png"));
		upFG.setPreferredSize(STANDARD_BUTTON_SIZE);
		upFG.addActionListener(new MoveUpButtonListener(this.controller));
		
		downFG = new JButton(new ImageIcon(IconRoot+ "down.png"));
		downFG.setPreferredSize(STANDARD_BUTTON_SIZE);
		downFG.addActionListener(new MoveDownButtonListener(this.controller));
		
		addInstance = new JButton(new ImageIcon(IconRoot+ "add_category.png"));
		addInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		addInstance.addActionListener(new AddCategoryButtonListener(this));
		
		removeInstance = new JButton(new ImageIcon(IconRoot + "delete.png"));
		removeInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		removeInstance.addActionListener(new DeleteButtonListener(this));
		
		renameInstance = new JButton(new ImageIcon(IconRoot + "rename.png"));
		renameInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		renameInstance.addActionListener(new RenameButtonListener(this));
		
		copyInstance = new JButton(new ImageIcon(IconRoot + "copy.gif"));
		copyInstance.setPreferredSize(STANDARD_BUTTON_SIZE);
		
		addFunction = new JButton(new ImageIcon(IconRoot+ "add_function.png"));
		addFunction.setPreferredSize(STANDARD_BUTTON_SIZE);
		addFunction.addActionListener(new AddFunctionButtonListener(this));
		
		compileButton = new JButton(new ImageIcon(IconRoot + "compile.png"));		
		compileButton.setPreferredSize(STANDARD_BUTTON_SIZE);
		compileButton.addActionListener(new CompileButtonListener(this.editorPanel));
		
		checkButton = new JButton(new ImageIcon(IconRoot + "check.png"));		
		checkButton.setPreferredSize(new Dimension(16,16));
		checkButton.addActionListener(new CheckButtonListener(this.editorPanel));
		checkButton.setEnabled(false);
		
		saveButton = new JButton(new ImageIcon(IconRoot + "save.png"));
		saveButton.setPreferredSize(new Dimension(16,16));
		saveButton.addActionListener(new SaveButtonListener(this.editorPanel));
		saveButton.setEnabled(false);
		
		importSourceButton = new JButton(new ImageIcon(IconRoot + "import_function.png"));
		importSourceButton.setPreferredSize(STANDARD_BUTTON_SIZE);
		importSourceButton.addActionListener(new ImportSourceButtonListener(this.editorPanel));
		//importSourceButton.setEnabled(false);
		
		exportTexButton = new JButton(new ImageIcon(IconRoot + "export_latex.png"));
		exportTexButton.setPreferredSize(STANDARD_BUTTON_SIZE);
		exportTexButton.addActionListener(new ExportTexButtonListener(this.editorPanel));
		
		optionsButton = new JButton(new ImageIcon(IconRoot + "options.png"));
		optionsButton.setPreferredSize(STANDARD_BUTTON_SIZE);
		optionsButton.addActionListener(new OptionsButtonListener(this.editorPanel));
		
		switchLayoutButton = new JButton(new ImageIcon(IconRoot + "switch_layout.png"));
		switchLayoutButton.setPreferredSize(STANDARD_BUTTON_SIZE);
		// this is not particularly pleasant but avoids making the button public
		switchLayoutButton.addActionListener(new SwitchLayoutButtonListener(this.editorPanel, this.switchLayoutButton));
	}
	
	protected void setButtonToolTips() {

		addInstance.setToolTipText("Add a new " + this.getCategoryName());
		upFG.setToolTipText("Move current selection up");
		downFG.setToolTipText("Move current selection down");

		removeInstance.setToolTipText("Remove current selection");
		renameInstance.setToolTipText("Rename current selection");
		copyInstance.setToolTipText("Copy selection");
		addFunction.setToolTipText("Add a new function to the selected " + this.getCategoryName());

		compileButton.setToolTipText("Compile the current model");
		checkButton.setToolTipText("Check the current source file");
		saveButton.setToolTipText("Save the current source file");
		importSourceButton.setToolTipText("Import an existing source file");
		exportTexButton.setToolTipText("Export this function to LaTeX");
		optionsButton.setToolTipText("Alter the editor settings");
		switchLayoutButton.setToolTipText("Change the orientation of the editor and preview panels");
		
	}
	
	
	

	private void fillGUI() {
		fillFunctionTree();
	}
	
	protected void fillFunctionTree() {
		
		controller.populateFunctionTree(rootNode);
		
		this.tree.expandRow(0);
		this.tree.setRootVisible(false);
		
	}


	
	protected void resetButtons() {
		addInstance.setEnabled(false);
		upFG.setEnabled(false);
		downFG.setEnabled(false);
		removeInstance.setEnabled(false);
		renameInstance.setEnabled(false);
		copyInstance.setEnabled(false);
		addFunction.setEnabled(false);
	}
	
	private void update_functions(Catagory c) {
		ArrayList<Integer> paths = new ArrayList<Integer>();
		for (int i = 0; i < tree.getRowCount(); i++) {
			if (tree.isExpanded(tree.getPathForRow(i))) {
				paths.add(i);
			}
		}
		fillFunctionTree();
		DefaultTreeModel t = (DefaultTreeModel) this.tree.getModel();
		t.setRoot(rootNode);
		for (Integer i : paths)
			tree.expandRow(i);
		this.tree.validate();
		// Set this to be focused
		if (c != null)
			this.variablePanel.update_selected_category(c);
		this.editorPanel.clear();
		this.saveButton.setEnabled(false);
		this.checkButton.setEnabled(false);
	}

	public void update_vars(SelectableItem i) {
		ArrayList<Integer> paths = new ArrayList<Integer>();
		for (int j = 0; j < var_tree.getRowCount(); j++) {
			if (var_tree.isExpanded(var_tree.getPathForRow(j))) {
				paths.add(j);
			}
		}
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
		for (Integer j : paths)
			var_tree.expandRow(j);
		this.var_tree.validate();
	}

	private void add_category(Catagory newCategory) {
		if (newCategory == null) {
			update_functions(null);
			return;
		}
		DefaultTreeModel t = (DefaultTreeModel) this.tree.getModel();
		DefaultMutableTreeNode new_cat = new DefaultMutableTreeNode(newCategory);
		for (Function f : newCategory.getFunctions()) {
			DefaultMutableTreeNode func = new DefaultMutableTreeNode(f);
			new_cat.add(func);
		}
		t.insertNodeInto(new_cat, this.rootNode, rootNode.getChildCount());
		this.tree.setRootVisible(true);
		this.tree.expandRow(0);
		this.tree.setRootVisible(false);
		this.variablePanel.update_selected_category(newCategory);
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
	
	public void addCategory() {
		controller.addCategory(this);	
	}

	public void addFunction() {
		String name = JOptionPane.showInputDialog(this,
	        	"Choose a name for the new function",
	            "Name Function", 1);
	    if (name != null || !controller.validate_name(this, name)) 
	    	controller.addFunction(this,name);
	}

	public void rename() {
		if (tree == null || tree.getSelectionPath() == null) {
			return;
		} else if (this.tree.getSelectionPath().getPathCount() == 2) {
			rename_category();
		} else if (this.tree.getSelectionPath().getPathCount() == 3) {
			rename_function();
		}
	}
	
	public void rename_function() {
		Function f = controller.getCurrentlySelectedFunction();
		String filepath = f.getSource_code();
		filepath += "\\";
		filepath += f.getParent().getName();
		filepath += "\\";
		try {
			String name = JOptionPane.showInputDialog(this,
		        	"Choose a new name for the function",
		            "Rename Function", 1);
		    if (name == null || !controller.validate_name(this, name))
		    	return;
		    // Check name is unique
		    for (Function fun : controller.getSelectedCatagory().getFunctions()) {
		    	if (fun.getName().equals(name)) {
		    		JOptionPane.showMessageDialog(this, "A Function with that name already exists");
					return;
		    	}
		    }
		    File fi = new File(filepath + f.getName() + ".bacon");
			fi.renameTo(new File(filepath + name + ".bacon"));
			f.setName(name);
			this.update_functions((Catagory) controller.getSelectedCatagory());
		} catch (Exception e) {
			
		}
	}
	
	public void rename_category() {
		SelectableItem i = controller.getSelectedCatagory();
		if (i == null)
			return;
		String filepath = ((Catagory)i).getFilePath();
		filepath = filepath.substring(0, filepath.lastIndexOf('\\'));
		filepath += "\\";
		try {
			String category = this instanceof ChemicalDisplay ? "Chemical" : "Functional Group";
			String name = JOptionPane.showInputDialog(this,
		        	"Choose a new name for the " + category,
		            "Rename " + category, 1);
		    if (name == null || !controller.validate_name(this, name))
		    	return;
		    // Check name is unique
		    for (SelectableItem si : controller.getCatagories()) {
		    	if (si.getName().equals(name)) {
		    		JOptionPane.showMessageDialog(this, "Something with that name already exists");

					return;
				}
			}
			File fi = new File(filepath + i.getName());
			fi.renameTo(new File(filepath + name));
			if (i instanceof Chemical)
				controller.rename_chemical((Chemical)i,name);
			else
				i.setName(name);
			this.update_functions((Catagory) controller.getSelectedCatagory());
		} catch (Exception e) {

		}
	}

	public void delete_category() {
		controller.delete_category(this);
	}
	
	private void add_function(NewFunctionEvent n) {
		Enumeration<?> children = rootNode.children();
		DefaultTreeModel t = (DefaultTreeModel) tree.getModel();
		while (children.hasMoreElements()) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
			if (child.toString().equals(n.getCategory().toString())) {
				DefaultMutableTreeNode nt = 
					new DefaultMutableTreeNode(n.getFunction());
				t.insertNodeInto(nt, child, child.getChildCount());
				// Automatically select the new function
				tree.setSelectionPath(new TreePath(new Object[]{rootNode,child,nt}));
				return;
			}
		}
	}
	
	private void remove_category(Catagory c) {
		Enumeration<?> children = rootNode.children();
		DefaultTreeModel t = (DefaultTreeModel) tree.getModel();
		while (children.hasMoreElements()) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
			if (child.toString().equals(c.toString())) {
				t.removeNodeFromParent(child);
				return;
			}
		}
	}
	
	private void remove_function(DeleteFunctionEvent d) {
		Enumeration<?> children = rootNode.children();
		DefaultTreeModel t = (DefaultTreeModel) tree.getModel();
		while (children.hasMoreElements()) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
			if (child.toString().equals(d.getCategory().toString())) {
				Enumeration<?> functions = child.children();
				while (functions.hasMoreElements()) {
					DefaultMutableTreeNode function = (DefaultMutableTreeNode) functions.nextElement();
					if (function.toString().equals(d.getFunction().getName())) {
						t.removeNodeFromParent(function);
						this.editorPanel.clear();
						this.saveButton.setEnabled(false);
						this.checkButton.setEnabled(false);
						return;
					}
				}
			}
		}
	}
	
	private void add_variable(VariableType v) {
		Enumeration<?> children = varRootNode.children();
		if (v instanceof StateVariable) {
			add_to_heading(children,"State Variables",v);
		} else if (v instanceof Parameter) {
			add_to_heading(children,"Parameters",v);
		} else if (v instanceof Local) {
			add_to_heading(children,"Local Variables",v);
		} else if (v instanceof VarietyConcentration) {
			add_to_heading(children,"Food Sets/Concentrations",v);
		} else if (v instanceof VarietyParameter) {
			add_to_heading(children,"Variety Parameters",v);
		} else if (v instanceof VarietyVariable) {
			add_to_heading(children,"Variety State Variables",v);
		} else if (v instanceof VarietyLocal) {
			add_to_heading(children,"Variety Local Variables",v);
		}
	}
	
	private void add_to_heading(Enumeration<?> children, String search,VariableType v) {
		DefaultTreeModel t = (DefaultTreeModel) var_tree.getModel();
		while (children.hasMoreElements()) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
			if (child.toString().equals(search)) {
				t.insertNodeInto(new DefaultMutableTreeNode(v.getName()), child, child.getChildCount());
				return;
			}
		}
		DefaultMutableTreeNode heading = new DefaultMutableTreeNode(search);
		t.insertNodeInto(heading, varRootNode, varRootNode.getChildCount());
		t.insertNodeInto(new DefaultMutableTreeNode(v.getName()), heading, 0);
	}
	
	private void remove_variable(DeleteVariableEvent d) {
		Enumeration<?> children = varRootNode.children();
		DefaultTreeModel t = (DefaultTreeModel) var_tree.getModel();
		while (children.hasMoreElements()) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
			Enumeration<?> headings = child.children();
			while (headings.hasMoreElements()) {
				DefaultMutableTreeNode variable = (DefaultMutableTreeNode) headings.nextElement();
				if (variable.toString().equals(d.getVarName())) {
					t.removeNodeFromParent(variable);
					return;
				}
			}
		}
	}
	
	public void update_panel_size() {
		DisplayOptions.getOptions().SIDE_PANEL_SIZE = this.getDividerLocation();
		DisplayOptions.getOptions().write_config();
	}

class DisplayChangeListener implements PropertyChangeListener {
	
	Display parent;
	
	public DisplayChangeListener(Display parent) {
		this.parent = parent;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		parent.update_panel_size();
	}
	
}
}
