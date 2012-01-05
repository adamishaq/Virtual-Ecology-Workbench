package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.prefs.BackingStoreException;

import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public class Model implements BuildFromXML, BuildToXML {

	private Collection<Chemical> chemicals;
	private Collection<FunctionalGroup> functionalGroups;
	
	private XMLFile oldFile;
	private XMLFile newFile;
	private XMLTag[] oldTags;

	public Model (XMLFile f) {
		AmbientVariableTables.destroyAmbientVariableTable();
		this.functionalGroups = new ArrayList<FunctionalGroup>();
		this.chemicals = new ArrayList<Chemical>();
		oldFile = f;
		newFile = (XMLFile) f.clone();
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
	 * Checks the validity of the current data in the controller.
	 */
	public void checkModel () {
		// TODO: check model
	}
	
	public Collection<Chemical> getChemicals() {
		return chemicals;
	}	
	public void addChemical(Chemical c) {
		chemicals.add(c);
	}
	public boolean removeChemical(Chemical c) {
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
			}
			catch (XMLWriteBackException ex) {
				collectedExceptions.addCompilerException(ex.getCompilerExceptions());
			}
		}
		for (Chemical chem : chemicals) {
			try {
				newFile.addTag(chem.buildToXML());
			}
			catch (XMLWriteBackException ex) {
				collectedExceptions.addCompilerException(ex.getCompilerExceptions());
			}
		}
		if (collectedExceptions.hasExceptions()) {
			throw collectedExceptions;
		}
		return newFile;
	}

	
	
}
