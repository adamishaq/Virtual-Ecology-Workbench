package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VarietyType;



public class BinOpNode extends ExprNode {
	
	private MathematicalOperator operator;
	private ExprNode lExpr;
	private ExprNode rExpr;
	
	public BinOpNode (MathematicalOperator operator, ExprNode lExpr, ExprNode rExpr, int line) {
		this.operator = operator;
		this.lExpr = lExpr;
		this.rExpr = rExpr;
		this.line_number = line;
	}

	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		lExpr.check(enclosingCategory, enclosingTree);
		rExpr.check(enclosingCategory, enclosingTree);
		Type lType = lExpr.getExprType();
		Type rType = rExpr.getExprType();
		try {
			setExprType(checkCompatibility(lType, rType));
		} catch (BACONCompilerException e) {
			enclosingTree.addSemanticException(e);
			setExprType(lType);
		}
		switch (operator) {
		case PLUS     : 
			if (!UnitChecker.getUnitChecker().CheckUnitCompatability(rExpr.getUnits(),
				lExpr.getUnits())) {
				enclosingTree.addWarning("Addition of two different unit types on line " + line_number);
				units = UnitChecker.null_collection;
			} else {
				this.units = UnitChecker.getUnitChecker().add_units(rExpr.getUnits(), lExpr.getUnits());
			}
			break;
		case MINUS    :
			if (!UnitChecker.getUnitChecker().CheckUnitCompatability(rExpr.getUnits(),
				lExpr.getUnits())) {
				enclosingTree.addWarning("Subtraction of two different unit types on line " + line_number);
				this.units = UnitChecker.null_collection;
			} else {
				this.units = UnitChecker.getUnitChecker().add_units(rExpr.getUnits(), lExpr.getUnits());
			}
			break; 
		case MULTIPLY :
			this.units = UnitChecker.getUnitChecker().multiply_units(rExpr.getUnits(), lExpr.getUnits());
			break; 
		case DIVIDE   :
			this.units = UnitChecker.getUnitChecker().divide_units(lExpr.getUnits(), rExpr.getUnits());
			break; 
		case POWER    :
			if (!UnitChecker.getUnitChecker().isDimensionless(rExpr.getUnits()))
				enclosingTree.addWarning("Expression raised to power of expression with units on line " +
						line_number);
			if (rExpr instanceof NumNode) {
				NumNode num = (NumNode) rExpr;
				units = UnitChecker.getUnitChecker().power_units(lExpr.getUnits(),num.getValue());
			} else {
				units = UnitChecker.null_collection;
			}
			break; 
		}
	}

	private Type checkCompatibility(Type lType, Type rType) throws BACONCompilerException {
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		if (lType instanceof VarietyType) {
			VarietyType vlType = (VarietyType) lType;
			if (rType instanceof VarietyType) {
				VarietyType vrType = (VarietyType) rType;
				if (!vlType.checkLinkCompatible(vrType)) {
					throw new SemanticCheckException("Expressions evaluate to varieties with different link sets", line_number);
				}
				return vrType;
			}
			return lType;
		}
		if (rType instanceof VarietyType) {
			return rType;
		}
		return floatType;
		
	}

	@Override
	public String generateXML() {
		String op = "";
		switch (operator) {
		case PLUS     : op = "add"; break; 
		case MINUS    : op = "sub"; break; 
		case MULTIPLY : op = "mul"; break; 
		case DIVIDE   : op = "div"; break; 
		case POWER    : op = "pow"; break; 
		}
		return "\\" + op + "{" + lExpr.generateXML() + "," + rExpr.generateXML() + "}";
	}

	public String generateLatex() {
		String func = "???";
		String left = "???";
		if (lExpr != null)
			left = lExpr.generateLatex();
		String right = "???";
		if (rExpr != null)
			right = rExpr.generateLatex();
		switch (operator) {
		case PLUS     : func  = "+"; break;
		case MINUS    : func  = "-"; break;
		case MULTIPLY : func  = "*"; break;
		case DIVIDE   : return "\\frac {" + left + "} {" + right + "}";
		case POWER    : return left + "^ {" + right + "}";
		}
		return left + func + right;
	}

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		lExpr.acceptDependencyCheckVisitor(visitor);
		rExpr.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}
	
}
