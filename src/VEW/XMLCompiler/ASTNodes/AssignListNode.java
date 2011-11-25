package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Planktonica2.model.Catagory;


public class AssignListNode extends ASTree {

	private Collection<AssignNode> assignList;

	
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
			genString += "\\set{" + a.generateXML() + "},";
		}
		return genString;
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
	


}
