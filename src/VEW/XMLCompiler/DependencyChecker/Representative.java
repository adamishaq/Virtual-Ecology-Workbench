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
	
	public Representative() {
		children = new ArrayList<Representative<D>> ();
		visited = false;
	}
	
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

	
	
	
}
