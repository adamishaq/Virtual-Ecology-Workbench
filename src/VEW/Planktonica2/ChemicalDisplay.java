package VEW.Planktonica2;

import java.awt.Dimension;

import VEW.Planktonica2.ControllerStructure.VEWController;

public class ChemicalDisplay extends Display {

	private static final long serialVersionUID = 7723416016731656817L;


	protected ChemicalDisplay(VEWController controller, Dimension initialSize) {
		super(controller, initialSize);
	}

	@Override
	protected String getCategoryName() {
		// TODO Auto-generated method stub
		return "chemical";
	}

	

}
