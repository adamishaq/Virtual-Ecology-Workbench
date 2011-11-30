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
		
		if (getSelectedCatagory() == null) {
			return null;
		}
		SelectableItem i = getSelectedCatagory();
		if (i instanceof Catagory) {
			Catagory c = (Catagory) i;
			return c.checkAllVariableTables(selectedVariable);
		}
		return null;
		
	}

	public void populateFunctionTree(DefaultMutableTreeNode rootNode) {
		
		rootNode.removeAllChildren();
		
		Collection<Catagory> selectables = this.getCatagories();
		
		for (Catagory s : selectables) {
			DefaultMutableTreeNode select = new DefaultMutableTreeNode(s);
			rootNode.add(select);
			for (int i = 0; i < s.getNoFunctions(); i++) {
				select.add(new DefaultMutableTreeNode(s.getFunctionAtIndex(i)));
			}
		}
		
	}
	

	public Function getFunctionAtIndex(int functionNo) {
		return getSelectedCatagory().getFunctionAtIndex(functionNo);
	}

	public int getNoFunctions() {
		return (getSelectedCatagory() == null) ? 0 : getSelectedCatagory().getNoFunctions();
	}
	
	public abstract Catagory getSelectedCatagory();
	
	/**
	 * Sets the currently selected item (FunctionalGroup/Chemical) and
	 * fires an event telling listeners that the selection has changed.
	 * @param i
	 */
	public void setSelectedCatagory (Catagory i) {
		
		if (setInternalSelectedCatagory(i)) {
			this.setChanged();
			this.notifyObservers(i);
		}
		
	}
	
	/**
	 * Called by setSelectedItem to set the internal reference to the appropriate SelectableItem
	 * @param i the item that has just been selected
	 * @return whether the selection was a success or not
	 */
	protected abstract boolean setInternalSelectedCatagory(Catagory i);
	 
	
	
	public Function getCurrentlySelectedFunction() {
		return currentlySelectedFunction;
	}

	public void setSelectedFunction(Function currentlySelectedFunction) {
		this.currentlySelectedFunction = currentlySelectedFunction;
	}


	public abstract Collection<Catagory> getCatagories();

	
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

	public void updateVariableDisplay() {
		this.setChanged();
		this.notifyObservers(new NewVariableEvent());
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


	public void addCategory(Display d) {
		String categorytype = this instanceof FunctionalGroupController ? "Functional Group" : "Chemical";
		String name = JOptionPane.showInputDialog(d,
	        	"Choose a name for the new " + categorytype,
	            "Name Functional Group", 1);
	        if (name == null) {
	        	return;
	        }

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
		if (this.getSelectedCatagory() == null)
			return;
		// Check name uniqueness
		for (Function f : this.getSelectedCatagory().getFunctions()) {
			if (f.getName().equals(name)) {
				JOptionPane.showMessageDialog(display, "A function with that name already exists");
				return;
			}
		}
		Catagory c = (Catagory) this.getSelectedCatagory();
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
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rename_chemical(Chemical c, String name) {
		Chemical newchem = new Chemical(name,c.getFilePath());
		newchem.setFunctions(c.getFunctions());
		newchem.setSpectrum(c.getSpectrum());
		newchem.setPigment(c.hasPigment());
		newchem.setValue(c.getValue());
		model.removeChemical(c);
		model.addChemical(newchem);
		this.setChanged();
		this.notifyObservers(new NewCategoryEvent(c));
	}

	public void deleteCategory(Display d) {
		// Check the user really wants to delete the category
		String category = this instanceof ChemicalController ? "Chemical" : "Funtional Group";
		int choice = JOptionPane.showOptionDialog(d, "Are you sure you want to delete this " 
				+ category + "?",
				"Confirm delete", JOptionPane.YES_NO_OPTION, 1, null, null, 1);
			if (choice == 1)
				return;
		SelectableItem i = this.getSelectedCatagory();
		if (i == null)
			return;
		String filepath = ((Catagory)i).getFilePath();
		filepath = filepath.substring(0, filepath.lastIndexOf('\\'));
		filepath += "\\" + i.getName();
		try {
			File f = new File(filepath);
			String[] children = f.list();
			for (int j = 0; j < children.length; j++) {
				File del = new File(filepath,children[j]);
				del.delete();
	        }
			f.delete();
		} catch (Exception e) {
		} finally {
			if (i instanceof Chemical) {
				model.removeChemical((Chemical)i);
			} else {
				model.removeFunctionalGroup((FunctionalGroup)i);
			}
			this.setChanged();
			this.notifyObservers(new NewCategoryEvent(null));
		}
	}

	public void deleteFunction(Display d) {
		// Check the user really wants to delete this function
		int choice = JOptionPane.showOptionDialog(d, "Are you sure you want to delete this function?",
			"Confirm delete", JOptionPane.YES_NO_OPTION, 1, null, null, 1);
		if (choice == 1)
			return;
		Function f = this.getCurrentlySelectedFunction();
		if (f == null)
			return;
		try {
			File del = new File(f.getSource_code() + "\\" + f.getParent().getName() 
					+ "\\" + f.getName() + ".bacon");
			del.delete();
		} catch (Exception e) {
		} finally {
			this.getSelectedCatagory().removeFunction(f);
			this.setChanged();
			this.notifyObservers(new NewCategoryEvent(null));
		}
	}

	
	/**
	 * Moves the currently Selected thing up.
	 * If the function is selected, it moves the function up.
	 * If no function is selected, it moves the currently selected instance up.
	 */
	public void moveSelectedUp() {
		Function f = getCurrentlySelectedFunction(); 
		if (f != null) {
			this.getSelectedCatagory().moveFunctionIndex(f, -1);
		} else {
			this.model.moveCatagoryUp(getSelectedCatagory(), -1);
		}
		
	}

	
	/**
	 * Moves the currently Selected thing down.
	 * If the function is selected, it moves the function down.
	 * If no function is selected, it moves the currently selected instance down.
	 */
	public void moveSelectedDown() {
		
		Function f = getCurrentlySelectedFunction(); 
		if (f != null) {
			this.getSelectedCatagory().moveFunctionIndex(f, 1);
		} else {
			this.model.moveCatagoryUp(getSelectedCatagory(), 1);
		}
		
	}


}
