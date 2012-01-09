package VEW.XMLCompiler.DependencyChecker;

public interface Visitable {

	public void setVisited(boolean visited);
	
	public boolean hasBeenVisited();
}
