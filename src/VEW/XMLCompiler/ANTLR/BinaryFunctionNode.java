package VEW.XMLCompiler.ANTLR;

public class BinaryFunctionNode extends ASTree implements RuleNode {

	private BinaryFunction binFunc;
	private IdNode idArg;
	private ExprNode expArg;
	
	public BinaryFunctionNode(String funcName, IdNode idArg, ExprNode expArg) {
		this.binFunc = BinaryFunction.valueOf(funcName);
		this.idArg = idArg;
		this.expArg = expArg;
	}
	
	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateXML() {
		String func = "";
		switch (binFunc) {
		case UPTAKE  : func = "uptake"; break;
		case RELEASE : func = "release"; break;
		case PCHANGE : return "\\pchange{" + idArg.generateXML() + "," + expArg.generateXML() + "}";
		}
		return "\\" + func + "{" + expArg.generateXML() + "," + idArg.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String func = "";
		switch (binFunc) {
		case UPTAKE  : func = "uptake"; break;
		case RELEASE : func = "release"; break;
		case PCHANGE : return "pchange(" + idArg.generateLatex() + "," + expArg.generateLatex() + ")";
		}
		return func + "(" + expArg.generateXML() + "," + idArg.generateXML() + ")";
	}

}
