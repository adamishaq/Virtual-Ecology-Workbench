package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ANTLR.CompilerException;

public interface BuildToXML {

	public XMLTag buildToXML () throws CompilerException;
	
}
