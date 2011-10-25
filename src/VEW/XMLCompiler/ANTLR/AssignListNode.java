package VEW.XMLCompiler.ANTLR;

public class AssignListNode extends ASTree {

	private AssignNode assign;
	private AssignListNode nextAssign;

	public AssignListNode (AssignNode assign) {
		this.assign = assign;
		this.nextAssign = null;
	}
	
	public AssignListNode (AssignNode assign, AssignListNode nextAssign) {
		this.assign = assign;
		this.nextAssign = nextAssign;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
