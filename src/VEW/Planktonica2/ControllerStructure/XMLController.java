package VEW.Planktonica2.ControllerStructure;

import java.util.Collection;
import java.util.prefs.BackingStoreException;

import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class XMLController extends VEWController {

	public XMLFile xmlFile;
	public Collection <FunctionalGroup> functionalGroups;
	public Collection <Chemical> chemicals;
	
	public XMLController(XMLFile xmlFile) throws BackingStoreException {
		this.xmlFile = xmlFile;
		readInData();
		
	}
	
	@Override
	protected void readInData() throws BackingStoreException {
		
		try {
			XMLTag [] tags = xmlFile.getTags(XMLTagEnum.FUNCTIONAL_GROUP.xmlTag());

			for(XMLTag t : tags) {
				FunctionalGroup f = new FunctionalGroup ();
				f.build(t);
				functionalGroups.add(f);
			}

			tags = xmlFile.getTags(XMLTagEnum.CHEMICAL.xmlTag());

			for(XMLTag t : tags) {
				Chemical c = new Chemical ();
				c.build(t);
				chemicals.add(c);
			}
		} catch (NullPointerException n) {
			throw new BackingStoreException (n.getMessage()); 
		}
		
	}

}
