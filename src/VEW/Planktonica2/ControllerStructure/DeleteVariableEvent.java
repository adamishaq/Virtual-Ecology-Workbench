package VEW.Planktonica2.ControllerStructure;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Function;

public class DeleteVariableEvent {

	private String var_name;
	
	public DeleteVariableEvent(String varname) {
		var_name = varname;
	}
	
	public String getVarName() {
		return var_name;
	}
	
}
