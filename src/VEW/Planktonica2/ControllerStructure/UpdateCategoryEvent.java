package VEW.Planktonica2.ControllerStructure;

import VEW.Planktonica2.Model.Catagory;

public class UpdateCategoryEvent {
	
	private Catagory current_category;
	
	public UpdateCategoryEvent(SelectableItem i) {
		if (i instanceof Catagory) {
			this.current_category = (Catagory) i;
		}
	}
	
	public Catagory getCategory() {
		return this.current_category;
	}

}
