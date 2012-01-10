
package VEW.Planktonica2.Model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.prefs.BackingStoreException;

import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public class Model implements BuildFromXML, BuildToXML {

	private ArrayList<Chemical> chemicals;
	private ArrayList<FunctionalGroup> functionalGroups;
	
	private XMLFile oldFile;
	private XMLFile newFile;
	private XMLTag[] oldTags;
	
	private List<String> warnings;

	public Model (XMLFile f) {
		AmbientVariableTables.destroyAmbientVariableTable();
		this.functionalGroups = new ArrayList<FunctionalGroup>();
		this.chemicals = new ArrayList<Chemical>();
		oldFile = f;
		newFile = (XMLFile) f.clone();
		warnings = new ArrayList<String>();
	}
	
	public String getFilePath() {
		String filepath = oldFile.getFileName();
		filepath = filepath.substring(0, filepath.lastIndexOf('\\'));
		filepath += "\\";
		return filepath;
	}
	
	public void buildFromFile() throws BackingStoreException {
		BuildFromXML b = null;
		
		try {
			b = build(newFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BackingStoreException (e);
		}
		
		if (b == null) {
			throw new BackingStoreException("Null model returned from XML");
		}
	}
	
	@Override
	public BuildFromXML build(XMLTag tag) {
		XMLTag [] tags = tag.getTags(XMLTagEnum.CHEMICAL.xmlTag());

		for(XMLTag t : tags) {
			Chemical c = new Chemical (newFile.getFileName());
			c.build(t);
			chemicals.add(c);
			t.removeFromParent();
		}
		
		tags = tag.getTags(XMLTagEnum.FUNCTIONAL_GROUP.xmlTag());

		for(XMLTag t : tags) {
			FunctionalGroup f = new FunctionalGroup (newFile.getFileName());
			f.build(t);
			functionalGroups.add(f);
			t.removeFromParent();
		}
		oldTags = newFile.getTags();
		return this;
	}

	/**
	 * Moves a given catagory by the offset in the respective list
	 * @param catagory the catagory to move
	 * @param offset (+ = down/ - = up (the list))
	 */
	public void moveCatagory(Catagory catagory, int offset) {
		
		if (catagory == null)
			return;
		if (functionalGroups.contains(catagory)) {
			
			int oldIndex = functionalGroups.indexOf(catagory) + offset;
			if (oldIndex >= 0 && oldIndex < functionalGroups.size()) {
				functionalGroups.remove(catagory);
				functionalGroups.add(oldIndex, (FunctionalGroup) catagory);
			}
			
			
		} else if (chemicals.contains(catagory)) {
			
			int oldIndex = chemicals.indexOf(catagory) + offset;
			if (oldIndex >= 0 && oldIndex < chemicals.size()) {
				chemicals.remove(catagory);
				chemicals.add(oldIndex, (Chemical) catagory);
			}
			
		} else {
			throw new NoSuchElementException("There is no catagory in the model with name: " + catagory.getName() + ".");
		}
		

	}
	
	/**
	 * Checks the validity of the current data in the controller.
	 */
	public void checkModel () {
		// TODO: check model
	}
	
	public Collection<Chemical> getChemicals() {
		return chemicals;
	}	
	public void addChemical(Chemical c) {
		String name = c.getName();
		chemicals.add(c);
		AmbientVariableTables.getTables().addChemical(name);
		Type floatType = (Type) AmbientVariableTables.getTables().checkTypeTable("$float");
		ArrayList<Unit> units = new ArrayList<Unit>();
		units.add(new Unit(0, "mol", 1));
		StateVariable pool = new StateVariable(name + "$Pool", name + " internal pool", floatType,
				units, new Float(0), 0, false);
		pool.setIncludeInXML(false);
		StateVariable ingested = new StateVariable(name + "$Ingested", name + " incoming pool",
				floatType, units, new Float(0), 0, false);
		ingested.setIncludeInXML(false);
		for (FunctionalGroup f : this.getFunctionalGroups()) {
			f.addToStateVarTable(name + "_Pool", pool);
			f.addToStateVarTable(name + "_Ingested", ingested);
		}
	}
	
	public boolean removeChemical(Chemical c) {
		AmbientVariableTables.getTables().removeChemical(c.getName());
		for (FunctionalGroup f : this.getFunctionalGroups()) {
			f.removeFromTables(c.getName() + "_Ingested");
			f.removeFromTables(c.getName() + "_Pool");
		}
		return chemicals.remove(c);
	}
	
	public void removeAllChemicals() {
		chemicals = new ArrayList<Chemical> ();
	}
	

	public Collection<FunctionalGroup> getFunctionalGroups() {
		return functionalGroups;
	}
	public void addFunctionalGroup(FunctionalGroup f) {
		functionalGroups.add(f);
	}
	public boolean removeFunctionalGroup(FunctionalGroup f) {
		return functionalGroups.remove(f);
	}
	public void removeAllFunctionalGroups() {
		functionalGroups = new ArrayList<FunctionalGroup> ();
	}

	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		for (XMLTag child :  newFile.getTags()) {
			child.removeFromParent();
		}
		for (XMLTag child : oldTags) {
			child.removeFromParent();
		}
		newFile.addTags(oldTags);
		XMLWriteBackException collectedExceptions = new XMLWriteBackException();
		for (FunctionalGroup fGroup : functionalGroups) {
			try {
				newFile.addTag(fGroup.buildToXML());
				warnings.addAll(fGroup.getWarnings());
				fGroup.clearWarnings();
			}
			catch (XMLWriteBackException ex) {
				collectedExceptions.addCompilerException(ex.getCompilerExceptions(),fGroup.getName());
			}
		}
		for (Chemical chem : chemicals) {
			try {
				newFile.addTag(chem.buildToXML());
				warnings.addAll(chem.getWarnings());
				chem.clearWarnings();
			}
			catch (XMLWriteBackException ex) {
				collectedExceptions.addCompilerException(ex.getCompilerExceptions(),chem.getName());
			}
		}
		if (collectedExceptions.hasExceptions()) {
			throw collectedExceptions;
		}
		return newFile;
	}

	public void copy_chemical(Chemical new_chem, Chemical selected) {
		// Create copies of all variables and add them to the appropriate tables
		String[] vars = selected.get_local_vars();
		for (int i = 0; i < vars.length; i++) {
			Local l = selected.checkLocalVarTable(vars[i]);
			if (l != null)
				new_chem.addToLocalTable(new Local(l));
		}
		vars = selected.get_params();
		for (int i = 0; i < vars.length; i++) {
			Parameter p = selected.checkParameterTable(vars[i]);
			if (p != null)
				new_chem.addToParamTable(new Parameter(p));
		}
		vars = selected.get_state_vars();
		for (int i = 0; i < vars.length; i++) {
			StateVariable s = selected.checkStateVariableTable(vars[i]);
			if (s != null)
				new_chem.addToStateVarTable(new StateVariable(s));
		}
		vars = selected.get_variety_concs();
		for (int i = 0; i < vars.length; i++) {
			VarietyConcentration v = selected.checkVarietyConcTable(vars[i]);
			if (v != null)
				new_chem.addToVarietyConcTable(new VarietyConcentration(v));
		}
		vars = selected.get_variety_locals();
		for (int i = 0; i < vars.length; i++) {
			VarietyLocal v = selected.checkVarietyLocalTable(vars[i]);
			if (v != null)
				new_chem.addToVarietyLocalTable(new VarietyLocal(v));
		}
		vars = selected.get_variety_params();
		for (int i = 0; i < vars.length; i++) {
			VarietyParameter v = selected.checkVarietyParamTable(vars[i]);
			if (v != null)
				new_chem.addToVarietyParamTable(new VarietyParameter(v));
		}
		vars = selected.get_variety_states();
		for (int i = 0; i < vars.length; i++) {
			VarietyVariable v = selected.checkVarietyStateTable(vars[i]);
			if (v != null)
				new_chem.addToVarietyStateTable(new VarietyVariable(v));
		}
		
		Collection<Spectrum> specs = new ArrayList<Spectrum>();
		for (Spectrum s : selected.getSpectrum()) {
			specs.add(new Spectrum(s));
		}
		new_chem.setSpectrum(specs);
		new_chem.baseTag = selected.baseTag;
		new_chem.setValue(selected.getValue());
		new_chem.setPigment(selected.hasPigment());
		for (Function f : selected.getFunctions()) {
			Function new_func = new_chem.addFunction(this.getFilePath(),f.getName());
			String file_path = f.getSource_code();
			file_path += f.getParent().getName() + "\\" + f.getName() + ".bacon";
			FileInputStream fstream;
			try {
				fstream = new FileInputStream(file_path);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String source = "", line = "";
				while ((line = br.readLine()) != null)   {
					source += (line + "\n"); 
				}
				in.close();
				file_path = f.getSource_code() + new_func.getParent().getName();
				File parentDirectory = new File(file_path);
				if (!parentDirectory.exists()) {
					parentDirectory.mkdir();
				}
				file_path += "\\" + new_func.getName() + ".bacon";
				FileOutputStream fostream = new FileOutputStream(file_path);
				PrintStream out = new PrintStream(fostream);
				out.print(source);
				out.close();
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
		
		this.addChemical(new_chem);
	}
	
	public List<String> getWarnings() {
		return warnings;
	}
	
	public void clearWarnings() {
		warnings = new ArrayList<String>();
	}

	
	
}

