package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;

public class DependencyGraphGenerator <D extends HasDependency> {
	
	/**
	 * Calls create representatives with the dependants filled. 
	 * 
	 * @param dependencies the list of dependencies
	 * @return
	 */
	public Collection<Representative<D>> createRepresentatives(Collection<Dependency<D>> dependencies) {
		return createRepresentatives(this.fillDependents(dependencies), dependencies);
	}
	
	/**
	 * Traverses the list of dependencies and creates representatives
	 * of each dependant (using them as an abstract representative of
	 * the dependancies).
	 * 
	 * It sets up the links between the representatives so that:
	 * if (Dependency(dep1 -> dep2)) {
	 * 		Representative(dep1).children.contains(Representative(dep2))
	 * }
	 * i.e. it a representative of a dependant will contain a representative of
	 * dep2 in its children iff there is a dependency from dep1 -> dep2
	 * 
	 * @param dependants the list of all dependents (nodes) in the current system
	 * @param dependencies the list of all dependencies in the system.
	 * @return the Collection of Representatives
	 */
	public Collection<Representative<D>> createRepresentatives(Collection<D> dependants, Collection<Dependency<D>> dependencies) {
		
		Collection<Representative<D>> representatives = new ArrayList<Representative<D>> ();		
		
		for (Representative<D> rep : representatives) {
			for (Dependency<D> dep : dependencies) {
				if (dep.getDependent1() == rep.getRepresentedObject()) {
					rep.addChild(findRepresentative(representatives, dep.getDependent2()));
				}
			}
		}
		
		return representatives;
	}
	
	
	/**
	 * Finds the representative associated with dependent.
	 * 
	 * If said representative does not exist in the given list,
	 * it creates a new one and adds that to the array.
	 * 
	 * @param representatives the list of current Representatives
	 * @param dependent the object to find the associated Representative of.
	 * @return the related Representative. If it not in representatives, it returns a new one.
	 */
	private Representative<D> findRepresentative(
			Collection<Representative<D>> representatives, D dependent) {

		for (Representative<D> rep : representatives) {
			if (rep.equals(dependent)) {
				return rep;
			}
		}
		
		Representative<D> newRep = new Representative<D> (dependent);
		
		representatives.add(newRep);
		
		return newRep;
	}
	
	/**
	 * Traverses this.dependencies finding all the objects with dependencies associated.
	 * @param dependencies the list of all dependencies in the system
	 * 
	 * @return an arraylist containing all the dependants
	 */
	public ArrayList<D> fillDependents(Collection<Dependency<D>> dependencies) {
		
		ArrayList<D> dependants = new ArrayList<D> ();
		
		for (Dependency<D> dependency : dependencies) {
			
			if (!dependants.contains(dependency.getDependent1())) {
				
				dependants.add(dependency.getDependent1());
				
			}
			
			if (!dependants.contains(dependency.getDependent2())) {
				
				dependants.add(dependency.getDependent2());
				
			}
			
		}
		
		return dependants;
	}
	
}
