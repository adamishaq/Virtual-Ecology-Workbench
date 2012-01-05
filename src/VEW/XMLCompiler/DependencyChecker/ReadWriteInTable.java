package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import VEW.Planktonica2.Model.VariableType;

public class ReadWriteInTable <D extends HasDependency> extends HashMap<VariableType, Collection<DependantMetaData <D>>> {

	private static final long serialVersionUID = 2797160589026295457L;

	public Collection<DependantMetaData <D>> put(VariableType type, DependantMetaData<D> r) {
		
		Collection<DependantMetaData<D>> node = this.get(type);
		
		if (node == null) {
			node = new ArrayList<DependantMetaData<D>> ();
		}
		
		node.add(r);
		
		return super.put(type, node);
	}
	
	
	
}
