package VEW.XMLCompiler.ANTLR;

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
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		String genString = "";
		for (AssignNode a : assignList) {
			genString += "\\set{" + a.generateXML() + "},";
		}
		return genString;
	}

}
