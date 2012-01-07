package VEW.Planktonica2.ControllerStructure;

import VEW.Planktonica2.Model.VariableType;

public class NewVariableEvent {

	private VariableType var;
	
	public NewVariableEvent(VariableType v) {
		var = v;
	}
	
	public VariableType getVar() {
		return var;
	}

}
