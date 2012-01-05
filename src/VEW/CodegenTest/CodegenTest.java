package VEW.CodegenTest;

import VEW.XMLCompiler.ANTLR.*;
import VEW.XMLCompiler.ASTNodes.*;

public class CodegenTest {

	public static void main(String[] args) {
		// Construct an AST
		NumNode num1 = new NumNode(20,0);
		IdNode var1 = new IdNode("A_lipid",0);
		BooleanComparitorNode bool1 = new BooleanComparitorNode(ComparisonOperator.GREATEREQUALS,num1,var1,0);
		NumNode num2 = new NumNode(1,0);
		NumNode num3 = new NumNode(2,0);
		IfExprNode if1 = new IfExprNode(bool1,num2,num3,0);
		IdNode var2 = new IdNode("Body_vol",0);
		AssignNode assi1 = new AssignNode(var2,if1,0);
		RuleSequenceNode assign2 = new RuleSequenceNode(assi1,null);
		NumNode num4 = new NumNode(10,0);
		IdNode var3 = new IdNode("C_d",0);
		AssignNode assi2 = new AssignNode(var3,num4,0);
		RuleSequenceNode assign1 = new RuleSequenceNode(assi2,assign2);
		String latex = "";
		latex = "\\begin{array}{lr}";
		latex += assign1.generateLatex();
		latex += "\\end{array}";
		System.out.println(latex);
	}
	
}
