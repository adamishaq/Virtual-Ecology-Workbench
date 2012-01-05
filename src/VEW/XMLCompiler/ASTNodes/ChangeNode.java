package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;
import VEW.Common.Pair;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Stage;
import VEW.Planktonica2.Model.VarietyType;

public class ChangeNode extends RuleNode {

	private ExprNode proportionExpr;
	private ArrayList<Pair<BExprNode, IdNode>> changeStatements;
	
	public ChangeNode (ExprNode proportionExpr, ArrayList<Pair<BExprNode, IdNode>> changeStatements) {
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
		ArrayList<BExprNode> previousConds = new ArrayList<BExprNode>();
		String generatedStr = "";
		for (int counter = 0; counter < changeStatements.size(); counter++) {
			Pair<BExprNode, IdNode> changeStat = changeStatements.get(counter);
			BExprNode bexpr = changeStat.getFirst();
			IdNode stage = changeStat.getSecond();
			if (bexpr == null) {
				if (previousConds.isEmpty()) {
					generatedStr += "changegen" + counter + ":\\pchange{\\stage{" 
									+ stage.getName() + "}," + proportionExpr.generateXML() + "};";
				}
				else {
					generatedStr += "changegen" + counter + ":\\ifthen{" + generateConditional(previousConds) +
							",\\pchange{\\stage{" + stage.getName() + "}," + proportionExpr.generateXML() + "}};";
				}
				generatedStr = generatedStr.substring(generatedStr.indexOf(':')+1, generatedStr.length()-1);
				return generatedStr;
			}
			generatedStr += "changegen" + counter + ":\\ifthen{" + generateConditional(previousConds, bexpr) +
							",\\pchange{\\stage{" + stage.getName() + "}," + proportionExpr.generateXML() + "}};";
			previousConds.add(bexpr);
		}
		generatedStr = generatedStr.substring(generatedStr.indexOf(':')+1, generatedStr.length()-1);
		/*System.out.println(generatedStr);
		VEW.Planktonica2.Model.EquationStringParser p;
		String[] parts = generatedStr.split(";");
		for (String s : parts) {
			String[] ts = s.split(":");
			int index = 1;
			if (ts.length == 1) {
				index = 0;
			}
			p = new VEW.Planktonica2.Model.EquationStringParser(s.split(":")[index]);
			System.out.println(p.parseEquationString());
		}*/
		return generatedStr;
	}
	
	private String generateConditional(ArrayList<BExprNode> previousConds) {
		if (previousConds.size() == 1) {
			return "\\not{" + previousConds.get(0).generateXML() + "}";
		}
		String conditionalString = "\\and{";
		for (int n = 0; n < previousConds.size() - 1; n++) {
			BExprNode prevCond = previousConds.get(n);
			conditionalString += "\\not{" + prevCond.generateXML() + "},";
		}
		BExprNode lastCond = previousConds.get(previousConds.size()-1);
		return conditionalString + lastCond.generateXML() + "}";
		
	}
	
	private String generateConditional(ArrayList<BExprNode> previousConds, BExprNode currentCond) {
		if (previousConds.size() == 0) {
			return currentCond.generateXML();
		}
		String conditionalString = "\\and{";
		for (BExprNode prevCond : previousConds) {
			conditionalString += "\\not{" + prevCond.generateXML() + "},";
		}
		conditionalString += currentCond.generateXML() + "}";
		return conditionalString;
	}

	@Override
	public String generateLatex() {
		String func = "change(";
		func += proportionExpr.generateLatex();
		func += ")\\;";
		func += "\\begin{cases}";
		for (Pair<BExprNode,IdNode> p : this.changeStatements) {
			func += p.getSecond().generateLatex();
			func += " \\; \\; \\; if \\; \\;";
			func += p.getFirst().generateLatex();
			func += " \\\\ ";
		}
		func += "\\end{cases}";
		return func;
	}

	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		super.acceptDependencyCheckVisitor(visitor);
		
		proportionExpr.acceptDependencyCheckVisitor(visitor);
		for (Pair<BExprNode, IdNode> pair : changeStatements) {
			pair.getFirst().acceptDependencyCheckVisitor(visitor);
			pair.getSecond().acceptDependencyCheckVisitor(visitor);
		}
		visitor.visit(this);
		
	}
	
}
