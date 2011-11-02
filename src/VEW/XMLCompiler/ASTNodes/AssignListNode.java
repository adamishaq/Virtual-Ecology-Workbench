package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;
import java.util.Collection;


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
	public void check() throws SemanticCheckException {
		for (AssignNode assign : assignList) {
			assign.check();
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
		if (nextAssign != null)
		  return assign.generateXML() + "," + nextAssign.generateXML();
		else
		  return assign.generateXML();
	}
	
}
