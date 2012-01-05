package VEW.XMLCompiler.DependencyChecker;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.VariableType;
import VEW.XMLCompiler.ASTNodes.ASTreeVisitor;
import VEW.XMLCompiler.ASTNodes.AssignListNode;
import VEW.XMLCompiler.ASTNodes.AssignNode;
import VEW.XMLCompiler.ASTNodes.BinOpNode;
import VEW.XMLCompiler.ASTNodes.BinaryFunctionNode;
import VEW.XMLCompiler.ASTNodes.BinaryPrimitiveNode;
import VEW.XMLCompiler.ASTNodes.BooleanBinOpNode;
import VEW.XMLCompiler.ASTNodes.BooleanComparitorNode;
import VEW.XMLCompiler.ASTNodes.BooleanNotOpNode;
import VEW.XMLCompiler.ASTNodes.CreateNode;
import VEW.XMLCompiler.ASTNodes.IdNode;
import VEW.XMLCompiler.ASTNodes.IfExprNode;
import VEW.XMLCompiler.ASTNodes.IfRuleNode;
import VEW.XMLCompiler.ASTNodes.IngestNode;
import VEW.XMLCompiler.ASTNodes.NegNode;
import VEW.XMLCompiler.ASTNodes.NumNode;
import VEW.XMLCompiler.ASTNodes.RuleNode;
import VEW.XMLCompiler.ASTNodes.RuleSequenceNode;
import VEW.XMLCompiler.ASTNodes.UnaryFunctionExprNode;
import VEW.XMLCompiler.ASTNodes.UnaryFunctionRuleNode;
import VEW.XMLCompiler.ASTNodes.UnaryPrimNode;
import VEW.XMLCompiler.ASTNodes.VBOpNode;
import VEW.XMLCompiler.ASTNodes.VOpNode;
import VEW.XMLCompiler.ASTNodes.VarHistNode;

public class ASTreeDependencyVisitor implements ASTreeVisitor {
	
	private ReadWriteInTable<RuleNode> writtenVariables;
	private ReadWriteInTable<RuleNode> readVariables;
	private Collection<Dependency<DependantMetaData <RuleNode>>> inTreeDependencies;
	private RuleNode currentRule;
	private Function parentFunction;
	private Collection<MultipleWriteException> multipleWrite;
	
	public ASTreeDependencyVisitor(Function enclosingCatagory) {
		this.inTreeDependencies = new ArrayList<Dependency<DependantMetaData<RuleNode>>> ();
		this.writtenVariables = new ReadWriteInTable<RuleNode> ();
		this.readVariables = new ReadWriteInTable<RuleNode> ();
		this.currentRule = null;
		this.parentFunction = enclosingCatagory;
		this.multipleWrite = new ArrayList<MultipleWriteException> ();
	}
	
	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.AssignNode)
	 */
	@Override
	public void visit(AssignNode assignNode) {
		
		VariableType t = assignNode.getAssignVar();
		DependantMetaData<RuleNode> curDependant = getCurrentMetaData();
		
		// Checks for read before write
		Collection<DependantMetaData<RuleNode>> nodesReadIn = readVariables.get(t);
		if (nodesReadIn != null) {
			for (DependantMetaData<RuleNode> r : nodesReadIn) {
				inTreeDependencies.add(new Dependency<DependantMetaData<RuleNode>> (curDependant, r));
			}
		}
		
		// Checks for double write
		Collection<DependantMetaData <RuleNode>> nodesWrittenTo = writtenVariables.get(t);
		if (nodesWrittenTo != null) {
			for (DependantMetaData<RuleNode> r : nodesWrittenTo) {
				multipleWrite.add(new MultipleWriteException(r, curDependant, t, "Standard double write issue."));
			}
			return;
		}
		
		writtenVariables.put(t, getCurrentMetaData());
		
		
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.IdNode)
	 */
	@Override
	public void visit(IdNode idNode) {
		
		// checks for read before write
		Collection<DependantMetaData <RuleNode>> nodesWrittenTo = writtenVariables.get(idNode.getVariableType());
		DependantMetaData<RuleNode> curDependant = getCurrentMetaData();
		if (nodesWrittenTo != null) {
			for (DependantMetaData<RuleNode> r : nodesWrittenTo) {
				inTreeDependencies.add(new Dependency<DependantMetaData<RuleNode>> (r, curDependant));
			}
		}
		
		readVariables.put(idNode.getVariableType(), getCurrentMetaData());
		
		
		
	}
	
	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.AssignListNode)
	 */
	@Override
	public void visit(AssignListNode assignListNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.BinaryFunctionNode)
	 */
	@Override
	public void visit(BinaryFunctionNode binaryFunctionNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.BinaryPrimitiveNode)
	 */
	@Override
	public void visit(BinaryPrimitiveNode binaryPrimitiveNode) {
		return;		
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.BinOpNode)
	 */
	@Override
	public void visit(BinOpNode binOpNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.BooleanBinOpNode)
	 */
	@Override
	public void visit(BooleanBinOpNode booleanBinOpNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.BooleanComparitorNode)
	 */
	@Override
	public void visit(BooleanComparitorNode booleanComparitorNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.BooleanNotOpNode)
	 */
	@Override
	public void visit(BooleanNotOpNode booleanNotOpNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.CreateNode)
	 */
	@Override
	public void visit(CreateNode createNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.IfExprNode)
	 */
	@Override
	public void visit(IfExprNode ifExprNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.IfRuleNode)
	 */
	@Override
	public void visit(IfRuleNode ifRuleNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.IngestNode)
	 */
	@Override
	public void visit(IngestNode ingestNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.NegNode)
	 */
	@Override
	public void visit(NegNode negNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.NumNode)
	 */
	@Override
	public void visit(NumNode numNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.RuleSequenceNode)
	 */
	@Override
	public void visit(RuleSequenceNode ruleSequenceNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.UnaryFunctionExprNode)
	 */
	@Override
	public void visit(UnaryFunctionExprNode unaryFunctionExprNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.UnaryFunctionRuleNode)
	 */
	@Override
	public void visit(UnaryFunctionRuleNode unaryFunctionRuleNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.UnaryPrimNode)
	 */
	@Override
	public void visit(UnaryPrimNode unaryPrimNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.VarHistNode)
	 */
	@Override
	public void visit(VarHistNode varHistNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.VBOpNode)
	 */
	@Override
	public void visit(VBOpNode vbOpNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.VOpNode)
	 */
	@Override
	public void visit(VOpNode vOpNode) {
		return;
	}

	/* (non-Javadoc)
	 * @see VEW.XMLCompiler.DependencyChecker.ASTreeVisitor#visit(VEW.XMLCompiler.ASTNodes.RuleNode)
	 */
	@Override
	public void visit(RuleNode ruleNode) {

		this.currentRule = ruleNode;
		
	}
	
	/**
	 * @return a new DependantMetaData using the current RuleNode and paretn Catagory
	 */
	private DependantMetaData<RuleNode> getCurrentMetaData() {
		return new DependantMetaData<RuleNode> (currentRule, parentFunction);
	}
	
	
	/*
	 * Getters and Setters
	 */
	
	public ReadWriteInTable<RuleNode> getWrittenVariables() {
		return writtenVariables;
	}

	public void setWrittenVariables(ReadWriteInTable<RuleNode> writtenVariables) {
		this.writtenVariables = writtenVariables;
	}

	public ReadWriteInTable<RuleNode> getReadVariables() {
		return readVariables;
	}

	public void setReadVariables(ReadWriteInTable<RuleNode> readVariables) {
		this.readVariables = readVariables;
	}

	public Collection<Dependency<DependantMetaData<RuleNode>>> getInTreeDependencies() {
		return inTreeDependencies;
	}

	public void setInTreeDependencies(
			Collection<Dependency<DependantMetaData<RuleNode>>> inTreeDependencies) {
		this.inTreeDependencies = inTreeDependencies;
	}

	public Function getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}

	public Collection<MultipleWriteException> getMultipleWrite() {
		return multipleWrite;
	}
	
}
