package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.model.Catagory;
import VEW.Planktonica2.model.Chemical;
import VEW.Planktonica2.model.VarietyConcentration;


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
		Catagory cata = getCatagory();
		if (cata instanceof Chemical) {
			throw new SemanticCheckException("Special functions cannot be called within chemical equations");
		}
		VarietyConcentration foodSet = cata.checkVarietyConcTable(identifier.getName());
		if (foodSet == null) {
			throw new SemanticCheckException(identifier.getName() + " is not a known food set");
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

}
