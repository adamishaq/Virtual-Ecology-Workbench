package VEW.Planktonica2.ControllerStructure;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Function;

public class DeleteFunctionEvent {

	private Catagory cat;
	private Function function;
	
	public DeleteFunctionEvent(Catagory c,Function f) {
		this.cat = c;
		this.function = f;
	}
	
	public Catagory getCategory() {
		return cat;
	}
	
	public Function getFunction() {
		return function;
	}
	
}
