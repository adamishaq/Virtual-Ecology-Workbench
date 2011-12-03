package VEW.XMLCompiler.DependencyChecker;

import java.util.Collection;

public class DependencyChecker <D extends HasDependency> {

	private Collection<Dependency<D>> dependencies;
	private DependencyCheck<D> checks;
	
	public DependencyChecker(Collection<Dependency<D>> dependencies, DependencyCheck<D> check) {
		this.dependencies = dependencies;
		this.checks = check;
	}
	
	/**
	 * Uses the given set of dependencies to give a list of dependencies
	 * that fails the given checks.
	 * @return a list of dependencies that fail.
	 */
	public Collection<Collection<Dependency<D>>> checkDependencies() { 
		
				
		
		checks.setDependencies(dependencies);
		
		checks.performChecks();
		
		return checks.getResults();
	}	
	
}
