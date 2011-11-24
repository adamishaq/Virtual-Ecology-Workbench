package VEW.Planktonica2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.prefs.BackingStoreException;

import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class Model implements BuildFromXML, Observer {

	private Collection<Chemical> chemicals;
	private Collection<FunctionalGroup> functionalGroups;
	private Catagory current_category;
	
	private XMLFile file;

	public Model (XMLFile f) {
		this.functionalGroups = new ArrayList<FunctionalGroup>();
		this.chemicals = new ArrayList<Chemical>();
		
		file = f;
	}
	
	public void buildFromFile() throws BackingStoreException {
		BuildFromXML b = null;
		
		try {
			b = build(file);
		} catch (Exception e) {
			throw new BackingStoreException (e);
		}
		
		if (b == null) {
			throw new BackingStoreException("Null model returned from XML");
		}
	}
	
	@Override
	public BuildFromXML build(XMLTag tag) {

		XMLTag [] tags = tag.getTags(XMLTagEnum.FUNCTIONAL_GROUP.xmlTag());

		for(XMLTag t : tags) {
			FunctionalGroup f = new FunctionalGroup (file.getFileName());
			f.build(t);
			functionalGroups.add(f);
		}

		tags = tag.getTags(XMLTagEnum.CHEMICAL.xmlTag());

		for(XMLTag t : tags) {
			Chemical c = new Chemical (file.getFileName());
			c.build(t);
			chemicals.add(c);
		}

		if (!functionalGroups.isEmpty()) {
			Iterator<FunctionalGroup> i = functionalGroups.iterator();
			current_category = i.next();
		} else if (!chemicals.isEmpty()) {
			Iterator<Chemical> i = chemicals.iterator();
			current_category = i.next();
		}

		return this;
	}

	/**
	 * A function that activates the checkModel method, causing the model to
	 * automatically compile. 
	 */
	@Override
	public void update(Observable o, Object arg) {
		checkModel();

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

	
	
}
