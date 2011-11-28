package VEW.XMLCompiler.ANTLR;

import java.util.List;

import VEW.Planktonica2.Model.Function;

public class CompilerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1519293717311538420L;
	private List<Exception> containedExceptions;
	private Function errorFunction;
	
	public CompilerException(Function errorFunction, List<Exception> containedExceptions) {
		this.errorFunction = errorFunction;
		this.containedExceptions = containedExceptions;
	}

	public List<Exception> getContainedExceptions() {
		return containedExceptions;
	}
	
	public Function getFilePath() {
		return errorFunction;
	}
}
