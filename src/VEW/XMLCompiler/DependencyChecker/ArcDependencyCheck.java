package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ArcDependencyCheck<D extends HasDependency> extends DependencyCheck<D> {
	
	public ArcDependencyCheck() {
		super();
		this.arcs = new ArrayList<Dependency<D>> ();
	}
	
	public ArcDependencyCheck(DependencyCheck<D> next) {
		super(next);
		this.arcs = new ArrayList<Dependency<D>> ();
	}
	
	public Collection<Dependency<D>> getDependencies() {
		return arcs;
	}
	
	

}
