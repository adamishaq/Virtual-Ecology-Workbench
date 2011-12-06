package VEW.XMLCompiler.ASTNodes;

import java.util.Collection;

import VEW.Common.Pair;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Stage;
import VEW.Planktonica2.Model.VarietyType;

public class ChangeNode extends RuleNode {

	private ExprNode proportionExpr;
	private Collection<Pair<BExprNode, IdNode>> changeStatements;
	
	public ChangeNode (ExprNode proportionExpr, Collection<Pair<BExprNode, IdNode>> changeStatements) {
		this.changeStatements = changeStatements;
		this.proportionExpr = proportionExpr;
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Special functions cannot be called within chemical equations",
							line_number));
			return;
		}
		if (isWithinConditional()) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Change statements cannot be put within conditionals", 
							line_number));
		}
		proportionExpr.check(enclosingCategory, enclosingTree);
		if (proportionExpr.getExprType() instanceof VarietyType) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("The change proportion must evaluate to a scalar value", 
							line_number));
		}
		FunctionalGroup group = (FunctionalGroup) enclosingCategory;
		checkChangeStatements(group, enclosingTree);

	}
	
	private void checkChangeStatements(FunctionalGroup enclosingGroup, ConstructedASTree enclosingTree) {
		boolean foundOtherwise = false;
		for(Pair<BExprNode, IdNode> changeStat: changeStatements) {
			BExprNode changeBExpr = changeStat.getFirst();
			if (foundOtherwise) {
				enclosingTree.addSemanticException(
						new SemanticCheckException("Cannot have further clauses after an otherwise " +
								"statement", line_number));
			}
			if (changeBExpr == null) {
				foundOtherwise = true;
			}
			else {
				changeBExpr.check(enclosingGroup, enclosingTree);
				if (changeBExpr.getBExprType() instanceof VarietyType) {
					enclosingTree.addSemanticException(
							new SemanticCheckException("The change condition must evaluate to boolean value",
									line_number));
				}
			}
			IdNode stage = changeStat.getSecond();
			Stage stg = enclosingGroup.checkStageTable(stage.getName());
			if (stg == null) {
				enclosingTree.addSemanticException(
						new SemanticCheckException(stage.getName() + " is not a known stage",line_number));
			}
		}
	}

	@Override
	public String generateXML() {
//		String func = "";
//		switch (funcName) {
//		case CHANGE : func = "change"; break;
//		}
//		return "\\" + func + "{" + idArg.generateXML() + "}";
		return "???";
	}
	
	@Override
	public String generateLatex() {
		String func = "???";
//		switch (funcName) {
//		case CHANGE : func = " change "; break;
//		}
//		if (idArg != null)
//			return "change (" + idArg.generateLatex() + ")";
		return func + "(???)";
	}

}
