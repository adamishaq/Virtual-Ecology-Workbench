package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Model;

public class ChemicalController extends VEWController {

	Collection<Chemical> chemicals;
	
	Chemical selectedChemical;
	
	public ChemicalController(Model m) {
		super(m);
		
		chemicals = model.getChemicals();
		
	}

	@Override
	public SelectableItem getSelectedItem() {
		return selectedChemical;
	}

	@Override
	protected boolean setInternalSelectedItem(SelectableItem i) {
		if (i instanceof Chemical) {
			selectedChemical = (Chemical) i;
			return true;
		}
		return false;
	}
	


	@Override
	public Collection<SelectableItem> getSelectables() {
		Collection<SelectableItem> c = new ArrayList<SelectableItem> (chemicals.size());
		for (Chemical chem : chemicals) {
			c.add(chem);
		}
		return c;
	}

	public Chemical getSelectedChemical() {
		return this.selectedChemical;
	}

	public Collection<Chemical> getChemicals() {
		return chemicals;
	}
	
	
}
