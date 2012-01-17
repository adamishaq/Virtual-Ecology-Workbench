package VEW.XMLCompiler.ANTLR;

import java.util.List;

import VEW.Planktonica2.Model.Function;
import VEW.XMLCompiler.ASTNodes.BACONCompilerException;

public class CompilerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1519293717311538420L;
	private List<BACONCompilerException> containedExceptions;
	private Function errorFunction;
	private String errorCategoryName;
	
	public CompilerException(Function errorFunction, List<BACONCompilerException> containedExceptions) {
		this.errorFunction = errorFunction;
		this.containedExceptions = containedExceptions;
	}
	

	public List<BACONCompilerException> getContainedExceptions() {
		return containedExceptions;
	}
	
	public void setContainedExceptions(List<BACONCompilerException> exceptions) {
		this.containedExceptions = exceptions;
	}
	
	
	public String getErrorFunctionName() {
		return errorFunction.getName();
	}
	
	public void setErrorCategoryName(String name) {
		this.errorCategoryName = name;
	}
	
	public String getErrorCategoryName() {
		return errorCategoryName;
	}
	
	public boolean hasExceptions() {
		return containedExceptions.isEmpty();
	}
}
