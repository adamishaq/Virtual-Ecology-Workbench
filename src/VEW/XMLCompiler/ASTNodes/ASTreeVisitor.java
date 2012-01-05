package VEW.XMLCompiler.ASTNodes;


public interface ASTreeVisitor {

	public abstract void visit(AssignNode assignNode);

	public abstract void visit(IdNode idNode);

	public abstract void visit(AssignListNode assignListNode);

	public abstract void visit(BinaryFunctionNode binaryFunctionNode);

	public abstract void visit(BinaryPrimitiveNode binaryPrimitiveNode);

	public abstract void visit(BinOpNode binOpNode);

	public abstract void visit(BooleanBinOpNode booleanBinOpNode);

	public abstract void visit(BooleanComparitorNode booleanComparitorNode);

	public abstract void visit(BooleanNotOpNode booleanNotOpNode);

	public abstract void visit(CreateNode createNode);

	public abstract void visit(IfExprNode ifExprNode);

	public abstract void visit(IfRuleNode ifRuleNode);

	public abstract void visit(IngestNode ingestNode);

	public abstract void visit(NegNode negNode);

	public abstract void visit(NumNode numNode);

	public abstract void visit(RuleSequenceNode ruleSequenceNode);

	public abstract void visit(UnaryFunctionExprNode unaryFunctionExprNode);

	public abstract void visit(UnaryFunctionRuleNode unaryFunctionRuleNode);

	public abstract void visit(UnaryPrimNode unaryPrimNode);

	public abstract void visit(VarHistNode varHistNode);

	public abstract void visit(VBOpNode vbOpNode);

	public abstract void visit(VOpNode vOpNode);

	public abstract void visit(RuleNode ruleNode);

}