package VEW.Planktonica2.ControllerStructure;

import VEW.Planktonica2.Model.Catagory;

public class DeleteCategoryEvent {

	private Catagory category;

	public Catagory getCategory() {
		return category;
	}
	
	public DeleteCategoryEvent(Catagory cat) {
		this.category = cat;
	}
	
}
