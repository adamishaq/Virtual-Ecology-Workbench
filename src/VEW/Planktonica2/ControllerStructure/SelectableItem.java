package VEW.Planktonica2.ControllerStructure;


import java.util.Collection;
import VEW.Planktonica2.Model.Function;

public interface SelectableItem {

	public String getName();
	public void setName(String name);
	
	public void removeFunction(Function f);
	public Function getFunctionAtIndex(int index);
	public Collection<Function> getFunctions();
	public int getNoFunctions();
	
	public String toString();
	
}
