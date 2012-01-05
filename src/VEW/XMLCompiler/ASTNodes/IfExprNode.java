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
		thenExpr.check(enclosingCategory, enclosingTree);
		elseExpr.check(enclosingCategory, enclosingTree);
		try {
			setExprType(checkCompatibility(thenExpr.getExprType(), elseExpr.getExprType(),
												conditionExpr.getBExprType()));
		} catch (BACONCompilerException e) {
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
			setExprType(thenExpr.getExprType());
		}
	}
	
	private Type checkCompatibility(Type lType, Type rType, Type boolType) throws BACONCompilerException{
		if (lType instanceof VarietyType && rType instanceof VarietyType) {
			VarietyType lVarType = (VarietyType) lType;
			VarietyType rVarType = (VarietyType) rType;
			if (!lVarType.checkLinkCompatible(rVarType)) {
				throw new SemanticCheckException("The two expressions within the if statement evaluate have different variety links"
													, line_number);
			}
			if (boolType instanceof VarietyType) {
				VarietyType vBoolType = (VarietyType) boolType;
				if (!lVarType.checkLinkCompatible(vBoolType)) {
					throw new SemanticCheckException("The boolean expression and returned expressions have different variety links"
							, line_number);
				}
			}
			return lVarType;
		}
		if (rType instanceof VarietyType && boolType instanceof VarietyType) {
			VarietyType rVarType = (VarietyType) rType;
			VarietyType vBoolType = (VarietyType) boolType;
			if (!rVarType.checkLinkCompatible(vBoolType)) {
				throw new SemanticCheckException("The boolean expression and returned expressions have different variety links"
						, line_number);
			}
			return rType;
		}
		if (lType instanceof VarietyType && boolType instanceof VarietyType) {
			VarietyType lVarType = (VarietyType) lType;
			VarietyType vBoolType = (VarietyType) boolType;
			if (!lVarType.checkLinkCompatible(vBoolType)) {
				throw new SemanticCheckException("The boolean expression and returned expressions have different variety links"
						, line_number);
			}
			return lType;
		}
		if (rType instanceof VarietyType) {
			return rType;
		}
		return lType;
		
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

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		conditionExpr.acceptDependencyCheckVisitor(visitor);
		thenExpr.acceptDependencyCheckVisitor(visitor);
		elseExpr.acceptDependencyCheckVisitor(visitor);
		visitor.visit(this);
		
	}

}
