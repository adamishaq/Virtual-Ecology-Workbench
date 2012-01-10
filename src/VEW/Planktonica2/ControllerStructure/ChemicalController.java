package VEW.Planktonica2.ControllerStructure;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import VEW.Planktonica2.ChemicalDisplay;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Model;
import VEW.Planktonica2.Model.NullSpectrum;
import VEW.Planktonica2.Model.Spectrum;

public class ChemicalController extends VEWController {

	Collection<Chemical> chemicals;
	
	Chemical selectedChemical;

	private FunctionalGroupController funcController;
	
	public ChemicalController(Model m,FunctionalGroupController f) {
		super(m);
		this.funcController = f;
		chemicals = model.getChemicals();
		
	}

	@Override
	public Catagory getSelectedCatagory() {
		return selectedChemical;
	}

	@Override
	protected boolean setInternalSelectedCatagory(Catagory i) {
		if (i instanceof Chemical) {
			selectedChemical = (Chemical) i;
			return true;
		}
		return false;
	}
	


	@Override
	public Collection<Catagory> getCatagories() {
		Collection<Catagory> c = new ArrayList<Catagory> (chemicals.size());
		for (Chemical chem : chemicals) {
			c.add(chem);
		}
		return c;
	}

	public Chemical getSelectedChemical() {
		return this.selectedChemical;
	}

	public void copy_chemical(ChemicalDisplay d) {
		if (selectedChemical == null)
			return;
		String name = JOptionPane.showInputDialog(d,
	        	"Choose a name for the new Chemical",
	            "Name Copy of Chemical", 1);
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
		Chemical c = new Chemical(name,model.getFilePath());
		model.copy_chemical(c,this.selectedChemical);
		this.setChanged();
		this.notifyObservers(new NewCategoryEvent(c));
		this.funcController.updateVariableDisplay();
		
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
		// The variable display may need to be updated to reflect new chemical pools
		this.funcController.updateVariableDisplay();
	}
	
	@Override
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
		this.funcController.updateVariableDisplay();
	}
	
	@Override
	public void chemical_delete() {
		this.funcController.updateVariableDisplay();
	}
	
}
