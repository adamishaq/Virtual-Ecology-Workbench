package VEW.XMLCompiler.ANTLR;

import java.util.List;

import org.antlr.runtime.RecognitionException;

import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.Model.Function;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;

public class BACONCompiler {
	
	private Function function;
	private String code;
	private ConstructedASTree tree;
	
	public BACONCompiler(Function function, String code) {
		this.function = function;
		this.code = code;
		tree = null;
	}
	
	public List<XMLTag> compile() throws CompilerException{
		ANTLRParser parser = new ANTLRParser(code);
		ConstructedASTree tree = null;
		try {
			 tree = parser.getAST();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
		if (tree.hasExceptions()) {
			throw new CompilerException(function, tree.getExceptions());
		}
		tree.checkTree(function.getParent());
		if (tree.hasExceptions()) {
			throw new CompilerException(function, tree.getExceptions());
		}
		return tree.compileTree();
	}

	public ConstructedASTree getTree() {
		return tree;
	}

	
}
