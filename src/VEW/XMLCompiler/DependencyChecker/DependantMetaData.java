package VEW.XMLCompiler.DependencyChecker;

import VEW.Planktonica2.Model.Function;

public class DependantMetaData <D extends HasDependency> implements HasDependency {

	private D node;
	private Function parent;

	public DependantMetaData(D node, Function parent) {
		this.node = node;
		this.parent = parent;
	}

	public D getNode() {
		return node;
	}

	public void setNode(D node) {
		this.node = node;
	}

	public Function getParent() {
		return parent;
	}

	public void setParent(Function parent) {
		this.parent = parent;
	}
	
	
	
}
