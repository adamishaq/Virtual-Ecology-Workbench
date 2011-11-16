package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.ControllerStructure.*;

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
	public void check() {
		Catagory cata = getCatagory();
		if (cata instanceof Chemical) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException("Special functions cannot be called within chemical equations",
							line_number));
		}
		VarietyConcentration foodSet = cata.checkVarietyConcTable(identifier.getName());
		if (foodSet == null) {
			CommonTreeWalker.add_exception(
					new SemanticCheckException(identifier.getName() + " is not a known food set",line_number));
		}
		threshold.check();
		rate.check();
		//TODO: Check that if things are varieties that they link back to appropriate food set

	}

	@Override
	public String generateXML() {
		return "\\ingest{" + identifier.generateXML() + "," + threshold.generateXML() + ","
		  	+ rate.generateXML() + "}";
	}

	@Override
	public String generateLatex() {
		String id = "???";
		if (identifier != null)
			id = identifier.generateLatex();
		String thresh = "???";
		if (threshold != null)
			thresh = threshold.generateLatex();
		String rp = "???";
		if (rate != null)
			rp = rate.generateLatex();
		return " ingest(" + id + "," + thresh + " , " + rp + ")";
	}
	
}
