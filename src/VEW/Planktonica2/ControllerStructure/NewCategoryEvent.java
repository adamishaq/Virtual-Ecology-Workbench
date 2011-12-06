package VEW.Planktonica2.ControllerStructure;

import VEW.Planktonica2.Model.Catagory;

public class NewCategoryEvent {

	private Catagory new_category;

	public Catagory getNew_category() {
		return new_category;
	}
	
	public NewCategoryEvent(Catagory cat) {
		this.new_category = cat;
	}

	
	public NewCategoryEvent() {
		this.new_category = null;
	}
	
}
