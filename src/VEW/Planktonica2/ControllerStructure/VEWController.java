package VEW.Planktonica2.ControllerStructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Observable;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;


import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.Display;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Model;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.Model.XMLWriteBackException;


public abstract class VEWController extends Observable {
	
	protected Model model;
	protected String currentSourcePath;
	protected Function currentlySelectedFunction;
	
	/**
	 * 
	 * @param m the class based model of the current VEW project
	 * @throws BackingStoreException when the XMLFile does not have the required tags in it.
	 */
	public VEWController (Model m) {
		super();
		model = m;
	}

	/**
	 * Get a variable by name.
	 * 
	 * This takes into account the currently selected Item and Function when choosing the Variable data to return.
	 * 
	 * @param selectedVariable
	 * @return a Variable with name == selected Variable
	 */
	public VariableType getVariable(String selectedVariable) {
		
		if (getSelectedItem() == null) {
			return null;
		}
		SelectableItem i = getSelectedItem();
		if (i instanceof Catagory) {
			Catagory c = (Catagory) i;
			return c.checkAllVariableTables(selectedVariable);
		}
		return null;
		
	}

	public void populateFunctionTree(DefaultMutableTreeNode rootNode) {
		
		rootNode.removeAllChildren();
		
		Collection<SelectableItem> selectables = this.getSelectables();
		
		for (SelectableItem s : selectables) {
			DefaultMutableTreeNode select = new DefaultMutableTreeNode(s);
			rootNode.add(select);
			for (int i = 0; i < s.getNoFunctions(); i++) {
				select.add(new DefaultMutableTreeNode(s.getFunctionAtIndex(i)));
			}
		}
		
	}
	
	private Function getSelectedFunction() {
		return currentlySelectedFunction;
	}
	
	public Function getFunctionAtIndex(int functionNo) {
		return getSelectedItem().getFunctionAtIndex(functionNo);
	}

	public int getNoFunctions() {
		return (getSelectedItem() == null) ? 0 : getSelectedItem().getNoFunctions();
	}
	
	public abstract SelectableItem getSelectedItem();
	
	/**
	 * Sets the currently selected item (FunctionalGroup/Chemical) and
	 * fires an event telling listeners that the selection has changed.
	 * @param i
	 */
	public void setSelectedItem (SelectableItem i) {
		
		if (setInternalSelectedItem(i)) {
			this.setChanged();
			this.notifyObservers(i);
		}
		
	}
	
	/**
	 * Called by setSelectedItem to set the internal reference to the appropriate SelectableItem
	 * @param i the item that has just been selected
	 * @return whether the selection was a success or not
	 */
	protected abstract boolean setInternalSelectedItem(SelectableItem i);
	 
	
	
	public Function getCurrentlySelectedFunction() {
		return currentlySelectedFunction;
	}

	public void setCurrentlySelectedFunction(Function currentlySelectedFunction) {
		this.currentlySelectedFunction = currentlySelectedFunction;
	}


	public abstract Collection<SelectableItem> getSelectables();

	
	public void writeBackToXMLFile() {
		XMLTag tag = null;
		try {
			tag = model.buildToXML();
			//TODO make sure multiple compiler errors are collated
		}
		catch (XMLWriteBackException ex) {
			ex.printStackTrace();
		}
		if (!(tag instanceof XMLFile)) {
			//TODO something has gone really wrong
		}
		XMLFile xmlFile = (XMLFile) tag;
		String fileName = xmlFile.getFileName();
		String newName = fileName.substring(0, fileName.lastIndexOf('\\')) + "testFile.xml";
		xmlFile.setFileName(newName);
		xmlFile.write();
	}


	public void load_source(String filePath) {
		this.currentSourcePath = filePath;
		this.setChanged();
		this.notifyObservers(new SourcePath(filePath));
	}

	
	public void updateVariablePanel(VariableType variable) {
		this.setChanged();
		this.notifyObservers(variable);
		
	}


	public void update_category(Catagory cat) {
		this.setChanged();
		this.notifyObservers(cat);
	}

	public Object getFunctionalNameAtIndex(int x) {
		Function f = this.getFunctionAtIndex(x);
		if (f == null) {
			return "";
		} else {
			return f.getName();
		}
	}

	public void addCategory(Display d,String name) {
		// Check name uniqueness
		for (FunctionalGroup f : this.model.getFunctionalGroups()) {
			if (f.getName().equals(name)) {
				JOptionPane.showMessageDialog(d, "A Functional Group with that name already exists");
				return;
			}
		}
		for (Chemical c : this.model.getChemicals()) {
			if (c.getName().equals(name)) {
				JOptionPane.showMessageDialog(d, "A Chemical with that name already exists");
				return;
			}
		}
		// Add the new FG/Chemical
		addCategoryToModel(name);
	}
	
	public abstract void addCategoryToModel(String name);

	public void addFunction(Display display, String name) {
		if (this.getSelectedItem() == null)
			return;
		// Check name uniqueness
		for (Function f : this.getSelectedItem().getFunctions()) {
			if (f.getName().equals(name)) {
				JOptionPane.showMessageDialog(display, "A function with that name already exists");
				return;
			}
		}
		Catagory c = (Catagory) this.getSelectedItem();
		c.addFunction(model.getFilePath(), name);
		addSourceFile(model.getFilePath() + c.getName() + "\\", name);
		this.setChanged();
		this.notifyObservers(new NewCategoryEvent(c));
	}

	private void addSourceFile(String parentPath, String name) {
		File parentDirectory = new File(parentPath);
		if (!parentDirectory.exists()) {
			parentDirectory.mkdir();
		}
		File sourceFile = new File(parentPath + name + ".bacon");
		if (sourceFile.exists()) {
			return;
		}
		try {
			FileWriter writer = new FileWriter(sourceFile);
			writer.write("");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
