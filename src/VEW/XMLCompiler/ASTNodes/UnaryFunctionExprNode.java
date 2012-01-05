package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;


public class UnaryFunctionExprNode extends RuleNode {

	private UnaryExprFunction function;
	private ExprNode expArg;

	public UnaryFunctionExprNode(UnaryExprFunction function, ExprNode expArg) {
		this.function = function;
		this.expArg = expArg;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		//This may need to change if any more unaryFunctions with expr args are added
		//Im considering changing this into a Divide node
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Divide cannot be called within chemical equations",
							line_number));
			return;
		}
		expArg.check(enclosingCategory, enclosingTree);
		Type expType = expArg.getExprType();
		if (expType instanceof VarietyType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The expression for the number to divide to must be scalar",
							line_number));
		}

	}

	@Override
	public String generateXML() {
		String func = "";
		switch (function) {
		case DIVIDE : func = "divide"; break;
		}
		return "\\" + func + "{" + expArg.generateXML() + "}";
	}
	
	public String generateLatex() {
		String func = "???";
		switch (function) {
		case DIVIDE : func = " divide "; break;
		}
		if (expArg != null)
			return func + "(" + expArg.generateLatex() + ")";
		return func + "(???)";
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		super.acceptDependencyCheckVisitor(visitor);		
		
		expArg.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

}
