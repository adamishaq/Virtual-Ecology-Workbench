package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionListener;

import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;
import VEW.Planktonica2.ControllerStructure.VEWController;

public class FGButtonListener extends TreeButtonListener implements ActionListener {


	public FGButtonListener(VEWController controller) {
		super(controller);
		
	}

	protected void markTop(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void moveUp(String s) {
		System.out.println("lololol");
		((FunctionalGroupController) controller).moveFunctionIndex(s, -1);
	}
	@Override
	protected void moveDown(String s) {
		System.out.println("roflmao");
		((FunctionalGroupController) controller).moveFunctionIndex(s, 1);
	}
	@Override
	protected void rename(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void add(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void remove(String s) {
		// TODO Auto-generated method stub
		
	}
	
	

}
