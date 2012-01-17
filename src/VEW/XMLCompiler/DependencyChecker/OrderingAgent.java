package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import VEW.Planktonica2.Model.Function;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;
import VEW.XMLCompiler.ASTNodes.RuleNode;

/**
 * Finds a rearangement of functions in all the catagories of a model.
 * 
 * Use DependencyCheckerWrapper to access this functionality.
 * 
 * @author Chris Bates
 *
 */
public class OrderingAgent {

	private Collection<DependantMetaData<ConstructedASTree>> trees;
	private Collection<MultipleWriteException> multipleWrite;
	private HashMap<ConstructedASTree, ArrayList<RuleNode>> functionOrder;
	private ArrayList<Function> ordering;

	/**
	 * 
	 * @param m the model to be checked for consistency.
	 * @param quitOnMultipleWrite whether or not the code should try to give a reordering of the trees if there has been a multiple write.
	 */
	public OrderingAgent(Collection<DependantMetaData<ConstructedASTree>> trees) {
		this.trees = trees;
		this.multipleWrite = new ArrayList<MultipleWriteException>();
		this.functionOrder = new HashMap<ConstructedASTree, ArrayList<RuleNode>> ();
		this.ordering = new ArrayList<Function> (); 
	}

	public boolean reorder () {
		
		// reorder each Function
		for (DependantMetaData<ConstructedASTree> data : trees) {
			if (!fillFunctionOrder(data.getNode(), calculateDependencies(data))) {
				return false;
			}
		}
		
		// reorder Functions
		
		
		return true;
	}

	

	private Collection<Dependency<DependantMetaData<RuleNode>>> calculateDependencies(DependantMetaData<ConstructedASTree> tree) {
		
		ASTreeDependencyVisitor visitor = new ASTreeDependencyVisitor(tree.getParent());
		tree.getNode().checkASTree(visitor);
		
		
		// check each tree for multiple writes and add gather all the related dependancies 
		multipleWrite.addAll(visitor.getMultipleWrite());
			
		return visitor.getInTreeDependencies();
		
	}
	
	private boolean fillFunctionOrder(ConstructedASTree parent, Collection<Dependency<DependantMetaData<RuleNode>>> tree) {
		
		// generate graph
		DependencyGraphGenerator<DependantMetaData<RuleNode>> gen = new DependencyGraphGenerator<DependantMetaData<RuleNode>> ();
		Collection<Representative<DependantMetaData<RuleNode>>> graph = gen.createRepresentatives(tree);

		// traverse graph from every other point
		ArrayList<Representative<DependantMetaData<RuleNode>>> order = new ArrayList<Representative<DependantMetaData<RuleNode>>> ();
		// add all to order
		for (Representative<DependantMetaData<RuleNode>> node : graph) {
			order.add(node);
		}

		boolean changed = true;
		int retries = order.size()^2;
		while (changed) {
			changed = false;
			for (int nodeIndex = 0; nodeIndex < order.size(); nodeIndex++) {
				int currentIndex = nodeIndex;
				Representative<DependantMetaData<RuleNode>> curNode = order.get(nodeIndex);
				for (Representative<DependantMetaData<RuleNode>> child : curNode.getChildren()) {
					int childIndex = order.indexOf(child);
					if (childIndex < currentIndex) {
						// move parent behind child and set changed to true
						// removes node from current index
						order.remove(currentIndex);
						// adds curNode in at child (which moves child along by one)
						order.add(childIndex, curNode);
						// updates position of currentNode!
						currentIndex = childIndex;
						changed = true;
					}
				}
			}

			if (retries <= 0) {
				return false;
			}
			retries--;
		}

		// remove representatives and metaData
		ArrayList<RuleNode> output = new ArrayList<RuleNode> (order.size());
		for (Representative<DependantMetaData<RuleNode>> node : order) {
			output.add(node.getRepresentedObject().getNode());
		}
		
		this.functionOrder.put(parent, output);

		return true;

	}

	
	/*
	 * Getters and setters
	 */
	public Collection<DependantMetaData<ConstructedASTree>> getTrees() {
		return trees;
	}

	public void setTrees(Collection<DependantMetaData<ConstructedASTree>> trees) {
		this.trees = trees;
	}

	public Collection<MultipleWriteException> getMultipleWrite() {
		return multipleWrite;
	}

	public HashMap<ConstructedASTree, ArrayList<RuleNode>> getFunctionOrder() {
		return functionOrder;
	}

	public ArrayList<Function> getOrdering() {
		return ordering;
	}
	
	
}
