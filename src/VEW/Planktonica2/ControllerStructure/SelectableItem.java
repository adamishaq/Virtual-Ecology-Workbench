package VEW.Planktonica2.ControllerStructure;

import VEW.Planktonica2.Model.Function;

public interface SelectableItem {

	public String getName();
	
	public Function getFunctionAtIndex(int index);
	public int getNoFunctions();
	
	public String toString();
	
}
