package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;

public class Representative <D extends HasDependency> implements HasDependency, Visitable {
	
	private D origional;
	/**
	 * All the things that have to be performed after.
	 */
	private Collection<Representative<D>> children;
	
	private boolean visited;
	
	public Representative(D objectToRepresent) {
		this.origional = objectToRepresent;
		this.children = new ArrayList<Representative<D>> ();
		this.visited = false;
	}
	
	public Representative(D objectToRepresent, Representative<D> next) {
		this.origional = objectToRepresent;
		this.children = new ArrayList<Representative<D>> ();
		this.children.add(next);
		this.visited = false;
	}
	
	public Representative(D objectToRepresent, Collection<Representative<D>> children) {
		this.origional = objectToRepresent;
		this.children = new ArrayList<Representative<D>> (children);
		this.visited = false;
	}
	
	public D getRepresentedObject() {
		return this.origional;
	}

	public Collection<Representative<D>> getChildren() {
		return this.children;
	}
	
	public void addChild(Representative<D> rep) {
		if (!children.contains(rep)) {
			children.add(rep);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof Representative) {
			o = ((Representative<?>) o).getRepresentedObject();
		}
		
		return o == this.getRepresentedObject();
	}

	
	@Override
	public boolean hasDependency(HasDependency d) {
		return this.getChildren().contains(d);
	}

	@Override
	public void setVisited(boolean visited) {
		
		this.visited = visited;
		
	}

	@Override
	public boolean hasBeenVisited() {
		return this.visited;
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
}
