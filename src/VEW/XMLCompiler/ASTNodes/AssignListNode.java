package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Planktonica2.Model.Catagory;

/**
 * AST Node that represents the assign list construct used within create statements
 * @author David Coulden
 *
 */
public class AssignListNode extends ASTree {

	private Collection<AssignNode> assignList; //The list of assigns comprising this node

	
	public AssignListNode() {
		this.assignList = new ArrayList<AssignNode>();
	}
	
	public AssignListNode(Collection<AssignNode> assignList) {
		this.assignList = new ArrayList<AssignNode>(assignList);
	}
	
	public void addAssign(AssignNode assign) {
		assignList.add(assign);
	}	

	@Override
	public void check(Catagory enclosingCategory,
			ConstructedASTree enclosingTree) {
		for (AssignNode assign : assignList) {
			assign.check(enclosingCategory, enclosingTree);
		}
		
	}

	@Override
	public String generateXML() {
		String genString = "";
		for (AssignNode a : assignList) {
			String assignString = a.generateXML().replace("\\assign{", "");
			assignString = assignString.substring(0, assignString.length() - 1);
			genString += "\\set{" + assignString + "},";
		}
		// Trim off the last comma
		return genString.substring(0, genString.length() - 1);
	}
	
	@Override
	public String generateLatex() {
		String genString = "";
		for (AssignNode a : assignList) {
			if (a != null)
				genString += a.generateLatex() + ",";
			else 
				genString += "???,";
		}
		// Trim off the last comma
		genString = genString.substring(0, genString.length() - 1);
		return genString;
	}

	/**
	 * Doesn't check the AssignNodes in the list.
	 */
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		
		visitor.visit(this);
		
	}
	
	

}
