package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.VarietyConcentration;


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
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		if (enclosingCategory instanceof Chemical) {
			enclosingTree.addSemanticException(
					new SemanticCheckException("Ingest cannot be called within chemical equations",
							line_number));
			return;
		}
		VarietyConcentration foodSet = enclosingCategory.checkVarietyConcTable(identifier.getName());
		if (foodSet == null) {
			enclosingTree.addSemanticException(
					new SemanticCheckException(identifier.getName() + " is not a known food set",line_number));
		}
		threshold.check(enclosingCategory, enclosingTree);
		rate.check(enclosingCategory, enclosingTree);
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

	
	@Override
	public void acceptDependencyCheckVisitor(ASTreeVisitor visitor) {
		super.acceptDependencyCheckVisitor(visitor);		
		
		identifier.acceptDependencyCheckVisitor(visitor);
		threshold.acceptDependencyCheckVisitor(visitor);
		rate.acceptDependencyCheckVisitor(visitor);
		
		visitor.visit(this);
		
	}
	
}
