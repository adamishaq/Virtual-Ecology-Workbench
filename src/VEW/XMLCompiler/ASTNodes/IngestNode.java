package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Chemical;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyConcentration;
import VEW.Planktonica2.Model.VarietyType;


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
		Type threshType = threshold.getExprType();
		Type rateType = rate.getExprType();
		if (threshType instanceof VarietyType) {
			VarietyType vThreshType = (VarietyType) threshType;
			if (rateType instanceof VarietyType) {
				VarietyType vRateType = (VarietyType) rateType;
				if (!vRateType.checkLinkCompatible(vThreshType)) {
					enclosingTree.addSemanticException(new SemanticCheckException("Threshold and rate arguments for ingest do" +
							" not evaluate to the same variety link", line_number));
				}
			}
			if (!vThreshType.checkLinkCompatible((VarietyType) foodSet.getVarType())) {
				enclosingTree.addSemanticException(new SemanticCheckException("Threshold argument" +
						" incompatible with given food set", line_number));
			}
		}
		if (rateType instanceof VarietyType) {
			VarietyType vRateType = (VarietyType) rateType;
			if (!vRateType.checkLinkCompatible((VarietyType) foodSet.getVarType())) {
				enclosingTree.addSemanticException(new SemanticCheckException("Rate argument" +
						" incompatible with given food set", line_number));
			}
		}

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
