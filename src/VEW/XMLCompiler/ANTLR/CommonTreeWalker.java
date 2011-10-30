package VEW.XMLCompiler.ANTLR;

import java.util.Collection;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import org.antlr.runtime.Token;

//Or CommonEnt... At least according to Mike
public class CommonTreeWalker {

	private CommonTree antlrTree;
	private CommonTree currentNode;
	
	public CommonTreeWalker(CommonTree antlrTree) {
		this.antlrTree = antlrTree;
		this.currentNode = antlrTree;
	}
	
	public ASTree constructASTree() throws TreeWalkerException{
		RuleSequenceNode constructedTree = null;
		Token t = antlrTree.getToken();
		if (t != null) {
			throw new TreeWalkerException("The tree root token is incorrect");
		}
		List<?> childRules = antlrTree.getChildren();
		RuleSequenceNode currentSeq = null;
		for (Object c : childRules) {
			CommonTree child = (CommonTree) c;
			Token childToken = child.getToken();
			if (childToken.getType() != SymbolSet.RULE) {
				throw new TreeWalkerException("Expected a rule token");
			}
			RuleSequenceNode ruleSeq = constructRuleSeqNode(child);
			if (currentSeq != null) {
				currentSeq.setRuleSequence(ruleSeq);
			}
			else {
				currentSeq = ruleSeq;
				constructedTree = ruleSeq;
			}
		}
		return constructedTree;
	}

	private RuleSequenceNode constructRuleSeqNode(CommonTree tree) throws TreeWalkerException {
		CommonTree ruleNameNode = (CommonTree) tree.getChild(0);
		String ruleName = ruleNameNode.getToken().getText();
		RuleNode rule = constructRuleNode((CommonTree) tree.getChild(1));
		return new RuleSequenceNode(ruleName, rule);
	}
	
	private RuleNode constructRuleNode(CommonTree tree) throws TreeWalkerException {
		Token token = tree.getToken();
		RuleNode rule = null;
		int tokenType = token.getType();
		switch (tokenType) {
			case(SymbolSet.IF) : {
				BExprNode bexpr = constructBExprNode((CommonTree)tree.getChild(0));
				RuleNode thenRule = constructRuleNode((CommonTree)tree.getChild(1));
				rule = new IfRuleNode(bexpr, thenRule);
				break;
			}
			case(SymbolSet.ASSIGN) : {
				IdNode id = constructIdNode((CommonTree)tree.getChild(0));
				ExprNode expr = constructExprNode((CommonTree)tree.getChild(1));
				rule = new AssignNode(id, expr);
				break;
			}
			case(SymbolSet.DIVIDE) : {
				ExprNode expr = constructExprNode((CommonTree)tree.getChild(0));
		        rule = new UnaryFunctionExprNode(UnaryExprFunction.DIVIDE, expr);
				break;
			}
			case(SymbolSet.CHANGE) : {
				IdNode id = constructIdNode((CommonTree)tree.getChild(0));
				rule = new UnaryFunctionRuleNode(UnaryRuleFunction.CHANGE, id);
				break;
			}
			case(SymbolSet.UPTAKE) : {
				rule = constructBinFuncNode(BinaryFunction.UPTAKE, tree);
				break;
			}
			case(SymbolSet.RELEASE) : {
				rule = constructBinFuncNode(BinaryFunction.RELEASE, tree);
				break;
			}
			case(SymbolSet.PCHANGE) : {
				rule = constructBinFuncNode(BinaryFunction.PCHANGE, tree);
				break;
			}
			
									  
		}
		return null;
	}

	private BExprNode constructBExprNode(CommonTree child) {
		// TODO Auto-generated method stub
		return null;
	}

	private ExprNode constructExprNode(CommonTree child) {
		// TODO Auto-generated method stub
		return null;
	}

	private IdNode constructIdNode(CommonTree firstItem) {
		return new IdNode(firstItem.getToken().getText());
	}
	
	private BinaryFunctionNode constructBinFuncNode(BinaryFunction binFunc, CommonTree tree) {
		IdNode id = constructIdNode((CommonTree)tree.getChild(0));
		ExprNode expr = constructExprNode((CommonTree)tree.getChild(1));
		return new BinaryFunctionNode(binFunc, id, expr);
	}
}
