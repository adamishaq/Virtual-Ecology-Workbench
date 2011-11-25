package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import VEW.Planktonica2.ControllerStructure.VEWController;

public abstract class TreeButtonListener implements ActionListener {

	protected VEWController controller;

	public TreeButtonListener(VEWController controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonCommandNamesEnum enumName = 
				ButtonCommandNamesEnum.toButtonCommandNamesEnum(e.getActionCommand());
		String func = controller.getSelectedFunction();
		switch (enumName) {
		case MOVE_UP:
			/* Move up fg/chem/function button */
			this.moveUp(func);
			break;
		case MOVE_DOWN:
			/* Move down fg/chem/function button */
			this.moveDown(func);
			break;
		case ADD_INSTANCE:
			/* Add new fg/chem/function button */
			this.add(func);
			break;
		case RENAME_INSTANCE:
			/* Rename fg/chem/function button */
			this.rename(func);
			break;
		case REMOVE_INSTANCE:
			/* Delete fg/chem/function button */
			this.remove(func);
			break;
		case TOP_GUN:
			/* Mark as top gun button
			 * TODO: case MAVERICK and case GOOSE  */
			this.markTop(func);
			break;
		default:
			System.err.println("\"Better to do something imperfectly than to do nothing flawlessly.\"");
			System.err.println(" => Whoops, for some reason that button doesn't have an event");
			break;
		}
	}
	

	protected abstract void moveUp(String s);
	protected abstract void moveDown(String s);
	protected abstract void rename(String s);
	protected abstract void add(String s);
	protected abstract void remove(String s);
	protected abstract void markTop(String s);

}
