package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;

public abstract class DependencyCheck <D extends HasDependency> {

	/**
	 * The next check in the chain.
	 */
	private DependencyCheck<D> next;
	
	protected Collection<Collection<Dependency<D>>> result;

	protected Collection<Dependency<D>> arcs; 
	
	public DependencyCheck() {
		result = new ArrayList<Collection<Dependency<D>>> ();
	}
	
	public DependencyCheck(DependencyCheck<D> next) {
		result = new ArrayList<Collection<Dependency<D>>> ();
		this.next = next;
	}

	/**
	 * Populates the failedDependencies Collection
	 * with the dependencies that fail 
	 */
	public void performChecks() {
		this.internalCheckDependencies();
		next.performChecks();
	}
	
	public void setDependencies(Collection<Dependency<D>> dependencies) {
		this.arcs = dependencies;
	}
	
	/**
	 * The internal computation of the dependency check
	 * 
	 * This is the vital method that performs the actual checks.
	 */
	protected abstract void internalCheckDependencies();
	
	public void setNextCheck(GraphDependencyCheck<D> nextCheck) {
		this.next = nextCheck;
	}
	
	public DependencyCheck<D> getNextCheck() {
		return this.next;
	}
	
	public Collection<Collection<Dependency<D>>> getResults() {
		Collection<Collection<Dependency<D>>> all = new ArrayList<Collection<Dependency<D>>> ();
		all.addAll(this.result);
		
		if (next != null) {
			all.addAll(next.getResults());
		}
		return all;
	}
}
