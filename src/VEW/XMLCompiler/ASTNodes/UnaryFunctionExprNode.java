package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.Type;
import VEW.Planktonica2.ControllerStructure.VarietyType;

public class UnaryFunctionExprNode extends RuleNode {

	private UnaryExprFunction function;
	private ExprNode expArg;

	public UnaryFunctionExprNode(UnaryExprFunction function, ExprNode expArg) {
		this.function = function;
		this.expArg = expArg;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		//This may need to change if any more unaryFunctions with expr args are added
		//Im considering changing this into a Divide node
		expArg.check();
		Type expType = expArg.getExprType();
		if (expType instanceof VarietyType) {
			throw new SemanticCheckException("The expression for the number to divide to must be scalar");
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

}
