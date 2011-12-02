package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VarietyType;


public class IfExprNode extends ExprNode {

	private BExprNode conditionExpr;
	private ExprNode thenExpr;
	private ExprNode elseExpr;
	
	public IfExprNode(BExprNode conditionExpr, ExprNode thenExpr, ExprNode elseExpr, int line) {
		this.conditionExpr = conditionExpr;
		this.thenExpr = thenExpr;
		this.elseExpr = elseExpr;
		this.line_number = line;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		conditionExpr.check(enclosingCategory, enclosingTree);
		Type condType = conditionExpr.getBExprType();
		if (condType instanceof VarietyType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The condition must evaluate to a boolean",line_number));
		}
		thenExpr.check(enclosingCategory, enclosingTree);
		elseExpr.check(enclosingCategory, enclosingTree);
		try {
			setExprType(checkCompatibility(thenExpr.getExprType(), elseExpr.getExprType()));
		} catch (SemanticCheckException e) {
			enclosingTree.addSemanticException(e);
		} finally {
			if (!UnitChecker.getUnitChecker().CheckUnitCompatability(thenExpr.getUnits(),
					elseExpr.getUnits())) {
				enclosingTree.addWarning("Conditional returning two different unit types on line "
					+ line_number);
				this.units = UnitChecker.null_collection;
			} else {
				this.units = UnitChecker.getUnitChecker().add_units(thenExpr.getUnits(), elseExpr.getUnits());
			}
		}
	}
	
	private Type checkCompatibility(Type lType, Type rType) throws SemanticCheckException{
		if (lType.equals(rType)) {
			return lType;
		}
		if (lType instanceof VarietyType && rType instanceof VarietyType) {
			VarietyType lVarType = (VarietyType) lType;
			VarietyType rVarType = (VarietyType) rType;
			if (!lVarType.getElementType().equals(rVarType.getElementType())) {
				throw new SemanticCheckException("The two variety expressions have different underlying types"
														, line_number);
			}
			else if (!lVarType.checkLinkCompatible(rVarType)) {
				throw new SemanticCheckException("The two expressions within the if statement evaluate have different variety links"
													, line_number);
			}
			return lVarType;
		}
		throw new SemanticCheckException("The expressions in the if statement must both evaluate to the same type"
														, line_number);
		
	}

	@Override
	public String generateXML() {
		return "\\conditional{" + conditionExpr.generateXML() + "," + thenExpr.generateXML()
		 + "," + elseExpr.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String cond = "???";
		if (conditionExpr != null)
			cond = conditionExpr.generateLatex();
		String then = "???";
		if (thenExpr != null)
			then = thenExpr.generateLatex();
		String elseexp = "???";
		if (elseExpr != null)
			elseexp = elseExpr.generateLatex();
		return "if\\;(" + cond + ")\\;then\\;(" + then
		 + ")\\;else\\;(" + elseexp + ")";
	}

}
