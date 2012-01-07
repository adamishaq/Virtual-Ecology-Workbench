package VEW.XMLCompiler.ASTNodes;

import VEW.Planktonica2.Model.Catagory;

public class VarHistNode extends ExprNode {

	private IdNode identifier;
	private ExprNode expression;

	public VarHistNode (IdNode identifier, ExprNode expression, int line) {
		this.identifier = identifier;
		this.expression = expression;
		this.line_number = line;
		
	}
	
	@Override
	public void check(Catagory enclosingCategory, ConstructedASTree enclosingTree) {
		identifier.check(enclosingCategory, enclosingTree);
		expression.check(enclosingCategory, enclosingTree);
		if (expression instanceof NumNode) {
			//TODO some checking for variables out of history ranges
		}
		//TODO find out if expressions in varhist is valid
		setExprType(expression.getExprType());
		identifier.set_units(enclosingCategory);
		units = identifier.getUnits();
	}

	@Override
	public String generateXML() {
		return "\\varhist{" + identifier.generateXML() + "," + expression.generateXML() + "}";
	}
	
	@Override
	public String generateLatex() {
		String id = "???";
		if (identifier != null)
			id = identifier.generateLatex();
		String exp = "???";
		if (expression != null)
			exp = expression.generateLatex();
		return "varhist( " + id + " , " + exp + " )";
	}

}
