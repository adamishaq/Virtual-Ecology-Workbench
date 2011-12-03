package VEW.XMLCompiler.DependencyChecker;

public class Dependency<D extends HasDependency> {

	private D dependent1;
	private D dependent2;
	
	/**
	 * Shows a relation from dependent1 -> dependent2, so dependent1 must be performed before dependant2
	 * 
	 * @param dependent1 the dependant that goes first
	 * @param dependent2 the dependant that goes second
	 */
	public Dependency(D dependent1, D dependent2) {
		
		this.dependent1 = dependent1;
		this.dependent2 = dependent2;
		
	}
	
	
	public D getDependent1() {
		return this.dependent1;
	}
	
	public D getDependent2() {
		return this.dependent2;
	}

	
}
