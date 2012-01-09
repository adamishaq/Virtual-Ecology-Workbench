package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyType;



public class BooleanComparitorNode extends BExprNode {
	
	private ComparisonOperator comparitor;
	private ExprNode rExpr;
	private ExprNode lExpr;
	
	public BooleanComparitorNode (ComparisonOperator comparitor, ExprNode lExpr, ExprNode rExpr, int line) {
		this.comparitor = comparitor;
		this.rExpr = rExpr;
		this.lExpr = lExpr;
		this.line_number = line;
	}

	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		rExpr.check(enclosingCategory, enclosingTree);
		lExpr.check(enclosingCategory, enclosingTree);
		Type rExprType = rExpr.getExprType();
		Type lExprType = lExpr.getExprType();
		try {
			setBExprType(checkTypeCompatible(lExprType, rExprType));
		} catch (BACONCompilerException e) {
			enclosingTree.addSemanticException(e);
			setBExprType(lExprType);
		}

	}

	private Type checkTypeCompatible(Type rExprType, Type lExprType) throws BACONCompilerException {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type boolType = (Type) tables.checkTypeTable("$boolean");
		if (lExprType instanceof VarietyType) {
			VarietyType vlType = (VarietyType) lExprType;
			if (rExprType instanceof VarietyType) {
				VarietyType vrType = (VarietyType) rExprType;
				if (!vlType.checkLinkCompatible(vrType)) {
					throw new SemanticCheckException("Boolean expression has inconsistent variety links", line_number);
				}
			}
			return new VarietyType("boolean", boolType, vlType.getLink());
		}
		if (rExprType instanceof VarietyType) {
			VarietyType vrType = (VarietyType) rExprType;
			return new VarietyType("boolean", boolType, vrType.getLink());
		}
		return boolType;
	}

	@Override
	public String generateXML() {
		String op = "";
		switch (comparitor) {
		case EQUALS        : op = "equal"; break; 
		case NEQUALS       : op = "neq"; break; 
		case GREATERTHAN   : op = "greater"; break; 
		case LESSTHAN      : op = "less"; break; 
		case GREATEREQUALS : op = "greaterequal"; break;
		case LESSEQUALS    : op = "lessequal"; break; 
		}
		return "\\" + op + "{" + lExpr.generateXML() + "," + rExpr.generateXML() + "}";
	}
	
	public String generateLatex() {
		String op = "???";
		String left = "???";
		if (lExpr != null)
			left = lExpr.generateLatex();
		String right = "???";
		if (rExpr != null)
			right = rExpr.generateLatex();
		switch (comparitor) {
		case EQUALS        : op = " = "; break; 
		case NEQUALS       : op = " \\neq " ; break; 
		case GREATERTHAN   : op = " > "; break; 
		case LESSTHAN      : op = " < "; break; 
		case GREATEREQUALS : op = " \\geq "; break;
		case LESSEQUALS    : op = " \\leq "; break; 
		}
		return left + op + right;
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		rExpr.acceptDependencyCheckVisitor(visitor);
		lExpr.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

}
