package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.DisplayOptions;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.UnitChecker;
import VEW.Planktonica2.Model.VarietyType;

/**
 * An AST node representing an if-then-else expression
 * @author David Coulden
 *
 */
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
			//Check whether then and else types are compatible and find their resultant type
			setExprType(checkCompatibility(thenExpr.getExprType(), elseExpr.getExprType(),
												conditionExpr.getBExprType()));
		} catch (BACONCompilerException e) {
			enclosingTree.addSemanticException(e);
			setExprType(thenExpr.getExprType());
		} finally {
			if (UnitChecker.getUnitChecker().CheckUnitCompatability(thenExpr.getUnits(),
					elseExpr.getUnits()) == 0) {
				enclosingTree.addWarning("Conditional returning two different unit types on line "
					+ line_number);
				this.units = UnitChecker.null_collection;
			} else if (UnitChecker.getUnitChecker().CheckUnitCompatability(thenExpr.getUnits(),
					elseExpr.getUnits()) == 1) { 
				this.units = UnitChecker.getUnitChecker().add_units(thenExpr.getUnits(), elseExpr.getUnits());
			} else {
				// Use units of then expr
				this.units = thenExpr.getUnits();
			}
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
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING) {
			float scale = UnitChecker.getUnitChecker().CheckUnitCompatability(thenExpr.getUnits(),
					elseExpr.getUnits());
			if (scale != 0 && scale != 1) {
				return "\\conditional{" + conditionExpr.generateXML() + "," + thenExpr.generateXML()
				 + ",\\mul{" + scale + "," + elseExpr.generateXML() + "}}";
			}
		}
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
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_SCALING) {
			float scale = UnitChecker.getUnitChecker().CheckUnitCompatability(thenExpr.getUnits(),
					elseExpr.getUnits());
			if (scale != 0 && scale != 1) {
				return "if\\;(" + cond + ")\\;then\\;(" + then
				 + ")\\;else\\;(" + scale + "*" + elseexp + ")";
			}
		}
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
