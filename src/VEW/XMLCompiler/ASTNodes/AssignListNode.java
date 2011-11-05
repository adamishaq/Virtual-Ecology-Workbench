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
		String genString = "";
		for (AssignNode a : assignList) {
			genString += a.generateLatex() + ",";
		}
		// Trim off the last comma
		genString = genString.substring(0, genString.length() - 1);
		return genString;
	}
	
}
