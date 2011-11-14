package VEW.XMLCompiler.ASTNodes;

public class IngestNode extends RuleNode {
	
	private IdNode identifier;
	private ExprNode threshold;
	private ExprNode rate;
	
	public IngestNode(IdNode _identifier, ExprNode _threshold, ExprNode _rate) {
		this.identifier = _identifier;
		this.threshold = _threshold;
		this.rate = _rate;
	}
	
	@Override
	public void check() throws SemanticCheckException {
		//TODO: Check identifier links to a food set
		threshold.check();
		rate.check();
		//TODO: Check that if things are varieties that they link back to appropriate food set

	}

	@Override
	public String generateXML() {
		return "\\ingest{" + identifier.generateXML() + "," + threshold.generateXML() + ","
		  	+ rate.generateXML() + "}";
	}

}
