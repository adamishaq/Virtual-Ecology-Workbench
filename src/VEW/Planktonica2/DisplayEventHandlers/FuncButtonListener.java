package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonCommandNamesEnum enumName = 
				ButtonCommandNamesEnum.toButtonCommandNamesEnum(e.getActionCommand());
		
		switch (enumName) {
		case UPFUNC:
			
			break;
		case DOWNFUNC:
			
			break;
		case ADD_FUNC:
			
			break;
		case RENAME_FUNC:
			
			break;
		case REMOVE_FUNC:
			
			break;
		case COPY_FUNC:
			
			break;	
		case EDIT_FUNC:
			
			break;	
		default:
			System.err.println("Whoops, for some reason that button doesn't have an event");
			break;
		
		}

	}

}
