package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author Chris Bates
 *
 * @param <D> the object which has dependency over
 * 
 * follows the chain design pattern: so each DependencyCheck can contain a 
 * child dependency check which it performs after itself.
 * 
 * The user must create the DependencyCheck and then call performChecks.
 * Then use getFailedDependencies to get the dependencies back out.
 * 
 */
public abstract class GraphDependencyCheck <D extends HasDependency> extends DependencyCheck<D> {

	/**
	 * The set of dependencies that failed the checks
	 */
	protected Collection<Collection<Representative<D>>> failedDependencies;
	
	/**
	 * The set of representatives over which the dependency
	 * checks are to be applied
	 */
	private Collection<Representative<D>> representatives;
	
	
	
	public GraphDependencyCheck(Collection<Representative<D>> representatives) {
		super();
		this.failedDependencies = new ArrayList<Collection<Representative<D>>> ();
		this.representatives = representatives;
	}
	
	public GraphDependencyCheck() {
		super();
		this.failedDependencies = new ArrayList<Collection<Representative<D>>> ();
		this.representatives = null;
	}
	
	public GraphDependencyCheck(GraphDependencyCheck<D> next) {
		super(next);
		this.failedDependencies = new ArrayList<Collection<Representative<D>>> ();
		this.representatives = next.getRepresentatives();
	}
	
	
	@Override
	public Collection<Collection<Dependency<D>>> getResults() {
		
		this.result.addAll(convertRepresentatives(failedDependencies));
		
		return super.getResults();
	}

	@Override
	public void setDependencies(Collection<Dependency<D>> dependencies) {
		super.setDependencies(dependencies);

		DependencyGraphGenerator<D> graphGenerator = new DependencyGraphGenerator<D> ();
		ArrayList<D> dependants = graphGenerator.fillDependents(dependencies);
		if(dependants.isEmpty()){
			this.representatives = new ArrayList<Representative<D>> ();
			return;
		}
		
		this.representatives = graphGenerator.createRepresentatives(dependants, dependencies);
	}
	
	
	
	
	public void setRepresentatives(Collection<Representative<D>> reps) {
		this.representatives = reps;
	}
	
	public Collection<Representative<D>> getRepresentatives() {
		return this.representatives;
	}
	
	
	private Collection<Collection<Dependency<D>>> convertRepresentatives(
			Collection<Collection<Representative<D>>> failedDependencies) {
		
		Collection<Collection<Dependency<D>>> result = new ArrayList<Collection<Dependency<D>>> ();
		
		for (Collection<Representative<D>> chain : failedDependencies) {
			result.add(getDependencyFromChain(chain));
			
		}
		
		return null;
	}

	private Collection<Dependency<D>> getDependencyFromChain(Collection<Representative<D>> chain) {
		
		Collection<Dependency<D>> result = new ArrayList<Dependency<D>>();
		if (chain.isEmpty()) {
			return result;
		}
		
		Iterator<Representative<D>> chainIt = chain.iterator();
		Representative<D> previous = chainIt.next();
		while(chainIt.hasNext()) {
			Representative<D> current = chainIt.next();
			
			result.add(new Dependency<D>(previous.getRepresentedObject(), current.getRepresentedObject()));
			
			previous = current;
		}
		
		
		return null;
	}
	
	
}
