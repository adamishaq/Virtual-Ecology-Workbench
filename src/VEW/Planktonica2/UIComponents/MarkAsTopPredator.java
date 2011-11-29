package VEW.Planktonica2.UIComponents;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;

import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;

public class MarkAsTopPredator extends JCheckBox implements Observer {

	private static final long serialVersionUID = -8166749867156523870L;
	
	private final static String attachedText = "Mark as top predator";
	
	private FunctionalGroupController controller;
	
	
	public MarkAsTopPredator (FunctionalGroupController controller) {
		super(attachedText);
		
		this.setSelected(controller.isTopPredator());
		this.controller = controller;
		controller.addObserver(this);
		
		this.setSelected(controller.isTopPredator());
		
	}

	@Override
	public void update(Observable o, Object arg) {
		 
		if (o == controller) {
			
			this.setSelected(controller.isTopPredator());
			
		}
		
	}
	
	
	
	

}
