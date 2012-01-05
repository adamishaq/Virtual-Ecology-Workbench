package VEW.Planktonica2.ControllerStructure;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;


import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.Display;
import VEW.Planktonica2.DisplayOptions;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Model;
import VEW.Planktonica2.Model.VariableType;
import VEW.Planktonica2.Model.XMLWriteBackException;
import VEW.XMLCompiler.ANTLR.ANTLRParser;
import VEW.XMLCompiler.ANTLR.CompilerException;
import VEW.XMLCompiler.ASTNodes.BACONCompilerException;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;


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
		restore_user_preferences(m.getFilePath());
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
			this.currentlySelectedFunction = null;
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

	
	public Collection<String> writeBackToXMLFile() {
		XMLTag tag = null;
		ArrayList<String> exceptions = new ArrayList<String>();
		try {
			tag = model.buildToXML();
			//TODO make sure multiple compiler errors are collated
		}
		catch (XMLWriteBackException ex) {
			for (CompilerException cex : ex.getCompilerExceptions()) {
				for (BACONCompilerException fex : cex.getContainedExceptions()) {
					exceptions.add(cex.getErrorFunctionName() + " (" + cex.getErrorCategoryName() + ")"
							+ ": " + fex.getError());
				}
			}
			return exceptions;
		}
		if (!(tag instanceof XMLFile)) {
			exceptions.add("The method was not constructed with a proper XMLFile.");
			return exceptions;
		}
		XMLFile xmlFile = (XMLFile) tag;
		//String fileName = xmlFile.getFileName();
		//String newName = fileName.substring(0, fileName.lastIndexOf('\\') + 1) + "testFile.xml";
		//xmlFile.setFileName(newName);
		xmlFile.write();
		return exceptions;
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
		this.notifyObservers(new UpdateVariableEvent());
	}

	public void addVariable(VariableType v) {
		this.setChanged();
		this.notifyObservers(new NewVariableEvent(v));
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
	    if (name == null || !validate_name(d,name))
	    	return;
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

	public void addFunction(Component display, String name) {
		if (this.getSelectedCatagory() == null || !validate_name(display,name))
			return;
		// Check name uniqueness
		for (Function f : this.getSelectedCatagory().getFunctions()) {
			if (f.getName().equals(name)) {
				JOptionPane.showMessageDialog(display, "A function with that name already exists");
				return;
			}
		}
		Catagory c = (Catagory) this.getSelectedCatagory();
		Function f = c.addFunction(model.getFilePath(), name);
		addSourceFile(model.getFilePath() + c.getName() + "\\", name);
		this.setChanged();
		this.notifyObservers(new NewFunctionEvent(c,f));
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
			System.err.println("Failed to write sourcecode to file.");
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
	
	public Collection<String> get_chemical_names() {
		ArrayList<String> names = new ArrayList<String>();
		for (Chemical c : this.model.getChemicals()) {
			names.add(c.getName());
		}
		return names;
	}

	public void delete_category(Display d) {
		if (this.getCurrentlySelectedFunction() != null) {
			// User has a function selected
			// Check the user really wants to delete this function
			int choice = JOptionPane.showOptionDialog(d, "Are you sure you want to delete the function "
					+ this.getCurrentlySelectedFunction().getName() + "?",
				"Confirm delete", JOptionPane.YES_NO_OPTION, 1, null, null, 1);
			if (choice == 1)
				return;

			Function f = this.getCurrentlySelectedFunction();
			if (f == null)
				return;

			File del = new File(f.getSource_code() + "\\" + f.getParent().getName() 
					+ "\\" + f.getName() + ".bacon");
			del.delete();

			this.getSelectedCatagory().removeFunction(f);
			this.setChanged();
			this.notifyObservers(new UpdateCategoryEvent(this.getSelectedCatagory()));
			this.setSelectedFunction(null);

		} else if (this.getSelectedCatagory() != null) {
			// User has a functional group/chemical selected
			// Check the user really wants to delete the category
			String category = this instanceof ChemicalController ? "Chemical" : "Funtional Group";
			int choice = JOptionPane.showOptionDialog(d, "Are you sure you want to delete the " 
					+ category + " " + this.getSelectedCatagory().getName() + "?",
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
				this.notifyObservers(new DeleteCategoryEvent((Catagory)i));
				this.setSelectedCatagory(null);
			}
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
			this.setChanged();
			this.notifyObservers(new UpdateCategoryEvent(getSelectedCatagory()));
		} else {
			this.model.moveCatagory(getSelectedCatagory(), -1);
			this.setChanged();
			this.notifyObservers(new NewCategoryEvent());
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
			this.setChanged();
			this.notifyObservers(new UpdateCategoryEvent(getSelectedCatagory()));
		} else {
			this.model.moveCatagory(getSelectedCatagory(), 1);
			this.setChanged();
			this.notifyObservers(new NewCategoryEvent());
		}
		
	}
	
	public String get_xml_source() {
		String path = this.model.getFilePath();
		return path;
	}

	public String get_model_name() {
		String name = model.getFilePath();
		name = name.substring(0,name.lastIndexOf('\\'));
		name = name.substring(0,name.lastIndexOf('\\'));
		name = name.substring(name.lastIndexOf('\\') + 1, name.length());
		return name;
	}

	public String generate_model_latex() {
		String latex = "Functional Groups: \\\\ \n";
		for(FunctionalGroup fg : model.getFunctionalGroups()) {
			latex += "{\\bf " + fg.getName() + "} \\\\ \\\\ \n";
			if (fg.get_params().length > 0) {
				latex += "Parameters \\\\ \n";
				latex += "\\begin{center}\n";
				latex += "\t\\begin{tabular}{ | c | c | c | c | }\n";
				latex += "\t\t\\hline\n";
				latex += "\t\t{\\bf Parameter} & {\\bf Description} & " +
						"{\\bf Default Value} & {\\bf Unit} \\\\ \\hline\n";
				for (String s : fg.get_params()) {
					VariableType v = fg.checkParameterTable(s);
					if (v != null) {
						latex += "\t\t" + v.getName() + " & " + v.getDesc() + " & $" 
							+ v.getValue() + "$ & $" + v.get_formatted_units() + "$ \\\\ \\hline \n";
					}
				}
				latex += "\t\\end{tabular}\n";
				latex += "\\end{center}\n";
			}
			for (Function f : fg.getFunctions()) {
				latex += source_latex(f) + "\\\\ \n";
			}
			//latex += "\\\\ \n";
		}
		return latex;
	}

	private String source_latex(Function f) {
		String latex = f.getName() + " \\\\ \n";
		String fpath = f.getSource_code() + "\\" + f.getParent().getName() 
			+ "\\" + f.getName() + ".bacon";
		try {
			FileInputStream fstream = new FileInputStream(fpath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			String file_text = "";
			while ((line = br.readLine()) != null)   {
				file_text += (line + "\n"); 
			}
			file_text = file_text.trim();
			in.close();
			if (DisplayOptions.getOptions().SOURCE_IN_LATEX) {
				latex += "\\begin{verbatim}\n";
				latex += file_text.trim() + "\n";
				latex += "\\end{verbatim}\n";
			}
			// TODO - separate get_report_latex() function?
			ANTLRParser p = new ANTLRParser (file_text);
			ConstructedASTree ct = p.getAST();
			if (ct.getTree() != null) 
				latex += "$" + ct.getTree().generateLatex() + "$ \n";
		} catch (Exception e) {
			latex += "{\\if Source not found} \\\\ \n";
		}
		return latex;
	}
	
	public boolean validate_name(Component display, String name) {
		// Check it contains no disallowed characters
		char[] chars = name.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!(Character.isLetterOrDigit(chars[i]) || chars[i] == '_' || chars[i] == ' ')) {
				JOptionPane.showMessageDialog(display, "Name cannot contain '" 
						+ chars[i] + "'");
				return false;
			}
		}
		return true;
	}

	public void delete_variable(VariableType v) {
		VariableType delete = this.getSelectedCatagory().removeFromTables(v.getName());
		if (delete != null) {
			this.setChanged();
			this.notifyObservers(new DeleteVariableEvent(v.getName()));
		}		
	}
	
	private void restore_user_preferences(String filepath) {
		  String path = filepath.substring(0, filepath.lastIndexOf("\\"));
		  path = path.substring(0, path.lastIndexOf("\\"));
		  path = path.substring(0, path.lastIndexOf("\\"));
		  path += "\\vew.config";
		  DisplayOptions.getOptions().config_path = path;
		  File config_file = new File(path);
		  if (config_file.exists()) {
			  FileInputStream fstream;
			  try {
				  fstream = new FileInputStream(path);
				  DataInputStream in = new DataInputStream(fstream);
				  BufferedReader br = new BufferedReader(new InputStreamReader(in));
				  String line = "";
				  while ((line = br.readLine()) != null)   {
					  apply_config(line); 
				  }
				  in.close();
			  } catch (Exception e) {
				  // Someone has müllered the config file...
			  }
		  } else {
			  // Create a new config file and fill it with the default values
			  DisplayOptions.getOptions().write_config();
		  }
	  }

		private void apply_config(String line) {
			if (line.startsWith("Maximised -")) {
				if (line.endsWith("1")) {
					// The form should be maximised
					DisplayOptions.getOptions().MAXIMISED = true;
				} else {
					DisplayOptions.getOptions().MAXIMISED = false;
				}
			} else if (line.startsWith("Frame Width - ")) {
				try {
					line = line.substring("Frame Width - ".length(),line.length());
					DisplayOptions.getOptions().FRAME_WIDTH = Integer.parseInt(line);
				} catch (Exception e) {
					// File has been altered...
					return;
				}
			} else if (line.startsWith("Frame Height - ")) {
				try {
					line = line.substring("Frame Height - ".length(),line.length());
					DisplayOptions.getOptions().FRAME_HEIGHT = Integer.parseInt(line);
				} catch (Exception e) {
					// File has been altered...
					return;
				}
			} else if (line.startsWith("Frame X - ")) {
				try {
					line = line.substring("Frame X - ".length(),line.length());
					DisplayOptions.getOptions().FRAME_X = Integer.parseInt(line);
				} catch (Exception e) {
					// File has been altered...
					return;
				}
			} else if (line.startsWith("Frame Y - ")) {
				try {
					line = line.substring("Frame Y - ".length(),line.length());
					DisplayOptions.getOptions().FRAME_Y = Integer.parseInt(line);
				} catch (Exception e) {
					// File has been altered...
					return;
				}
			} else if (line.startsWith("Layout Vertical -")) {
				if (line.endsWith("1")) {
					// Layout is vertical
					DisplayOptions.getOptions().LAYOUT_VERTICAL = true;
				} else {
					DisplayOptions.getOptions().LAYOUT_VERTICAL = false;
				}
			} else if (line.startsWith("LaTeX Units -")) {
				if (line.endsWith("1")) {
					// Display units in LaTeX previews
					DisplayOptions.getOptions().PREVIEW_UNITS = true;
				} else {
					DisplayOptions.getOptions().PREVIEW_UNITS = false;
				}
			} else if (line.startsWith("LaTeX Names -")) {
				if (line.endsWith("1")) {
					// Display rule names in LaTeX previews
					DisplayOptions.getOptions().PREVIEW_RULE_NAMES = true;
				} else {
					DisplayOptions.getOptions().PREVIEW_RULE_NAMES = false;
				}
			} else if (line.startsWith("LaTeX Names -")) {
				if (line.endsWith("1")) {
					// Display rule names in LaTeX previews
					DisplayOptions.getOptions().PREVIEW_RULE_NAMES = true;
				} else {
					DisplayOptions.getOptions().PREVIEW_RULE_NAMES = false;
				}
			} else if (line.startsWith("Source in LaTeX -")) {
				if (line.endsWith("1")) {
					// Display the source for functions in the LaTeX documentation
					DisplayOptions.getOptions().SOURCE_IN_LATEX = true;
				} else {
					DisplayOptions.getOptions().SOURCE_IN_LATEX = false;
				}
			} else if (line.startsWith("Editor Pane - ")) {
				try {
					line = line.substring("Editor Pane - ".length(),line.length());
					DisplayOptions.getOptions().EDITOR_PANEL_SIZE = Integer.parseInt(line);
				} catch (Exception e) {
					// File has been altered...
					return;
				}
			} else if (line.startsWith("Error Pane - ")) {
				try {
					line = line.substring("Error Pane - ".length(),line.length());
					DisplayOptions.getOptions().ERROR_PANEL_SIZE = Integer.parseInt(line);
				} catch (Exception e) {
					// File has been altered...
					return;
				}
			}
		}

}
