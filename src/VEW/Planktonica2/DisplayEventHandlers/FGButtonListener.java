package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import VEW.Planktonica2.DisplayEventHandlers.ButtonCommandNamesEnum;

public class FGButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonCommandNamesEnum enumName = 
				ButtonCommandNamesEnum.toButtonCommandNamesEnum(e.getActionCommand());
		
		switch (enumName) {
		case UPFG:
			
			break;
		case DOWNFG:
			
			break;
		case ADD_INSTANCE:
			
			break;
		case RENAME_INSTANCE:
			
			break;
		case REMOVE_INSTANCE:
			
			break;
		case COPY_INSTANCE:
				
			break;	
		default:
			System.err.println("Whoops, for some reason that button doesn't have an event");
			break;
		
		}

	}
	
	

}
