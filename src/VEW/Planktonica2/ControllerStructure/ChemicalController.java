package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Model;
import VEW.Planktonica2.Model.NullSpectrum;
import VEW.Planktonica2.Model.Spectrum;
import VEW.Planktonica2.Model.StateVariable;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

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

	public Spectrum getSelectedChemicalSpetrum (String spectrumName) {
		if (selectedChemical == null) {
			return new NullSpectrum();
		}
		Collection<Spectrum> allSpecs = selectedChemical.getSpectrum();
		for (Spectrum s : allSpecs) {
			String name = s.getName();
			if (name != null && name.equals(spectrumName)) {
				return s;
			}
		}
		
		return new NullSpectrum();
	}
	
	public Collection<Spectrum> getAllSelectedSpectrum() {
		return selectedChemical.getSpectrum();
	}
	
	
	public Collection<Chemical> getChemicals() {
		return chemicals;
	}

	
	public void chemicalHasPigment(boolean b) {
		
		Chemical c = getSelectedChemical();
		if (c != null) {
			c.setPigment(b);
		}
		
		
	}
	
	public void addCategoryToModel(String name) {
		Chemical c = new Chemical(name,model.getFilePath());
		model.addChemical(c);
		this.setChanged();
		this.notifyObservers(new NewCategoryEvent(c));
	}
	
}
