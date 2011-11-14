package VEW.XMLCompiler.ASTNodes;

public class BinaryFunctionNode extends RuleNode {

	private BinaryFunction binFunc;
	private IdNode idArg;
	private ExprNode expArg;
	
	public BinaryFunctionNode(BinaryFunction function, IdNode idArg, ExprNode expArg) {
		this.binFunc = function;
		this.idArg = idArg;
		this.expArg = expArg;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		//Considering splitting this into three nodes
		expArg.check();
		Type expArgType = expArg.getExprType();
		switch (binFunc) {
			case UPTAKE : 
			case RELEASE : {
				//TODO Check id is a chemical
				break;
			}
			case PCHANGE : {
				//TODO Check id is a stage
			}
		}
		if (expArgType instanceof Variety) {
			throw new SemanticCheckException("The expression must evaluate to a scalar value");
		}
		
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
		String func = "???";
		String id = "???";
		if (idArg != null)
			id = idArg.generateLatex();
		String exp = "???";
		if (expArg != null)
			exp = expArg.generateLatex();
		switch (binFunc) {
		case UPTAKE  : func = "uptake"; break;
		case RELEASE : func = "release"; break;
		case PCHANGE : return "pchange(" + id + "," + exp + ")";
		}
		return func + "(" + exp + "," + id + ")";
	}
	
}
