package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;

public class LoopDependencyCheck<D extends HasDependency> extends GraphDependencyCheck<D> {

	@Override
	protected void internalCheckDependencies() {
		
		for (Representative<D> r : this.getRepresentatives()) {
			r.setVisited(false);
		}
		
		for (Representative<D> r : this.getRepresentatives()) {
			if (!r.hasBeenVisited()) {
				Collection<Collection<Representative<D>>> failed = new LoopVisitor<D>(r).startLoop();
				if (failed != null) {
					this.failedDependencies.addAll(failed);
				}
			}
		}
		
	}
	
	
 	private class LoopVisitor<E extends HasDependency> {
	 
		private Representative<E> start;

		public LoopVisitor(Representative<E> start) {
			this.start = start;
		}
		
		public Collection<Collection<Representative<E>>> startLoop() {
			
			Collection<Representative<E>> currentChain = new ArrayList<Representative<E>> ();
			
			return depthSearch(start, currentChain);
			
		}
		
		
		private Collection<Collection<Representative<E>>> depthSearch(Representative<E> current,
																	  Collection<Representative<E>> currentChain) {
			
			currentChain.add(current);
			current.setVisited(true);
			
			Collection<Representative<E>> children = current.getChildren();
			
			if (children.isEmpty()) {
				return null;
			}
			
			while (children.size() == 1) {
				Representative<E> next = children.iterator().next();
				
				if (next.hasBeenVisited()) {
					return null;
				}
				
				if (currentChain.contains(next)) {
					ArrayList<Collection<Representative<E>>> result = new ArrayList<Collection<Representative<E>>> ();
					result.add(currentChain);
					return result;
				}
				
				current = next;
				currentChain.add(current);
				
			}
			
			Collection<Collection<Representative<E>>> result = new ArrayList<Collection<Representative<E>>> ();
			for (Representative<E> child : current.getChildren()) {
				if (!child.hasBeenVisited() && !currentChain.contains(child)) {
					Collection<Collection<Representative<E>>> failed = depthSearch(child, currentChain);
					if (failed != null) {
						result.addAll(failed);
					}
					
				}
			}
			
			return (result.isEmpty() ? null : result);
		}
		
		
	}
}
