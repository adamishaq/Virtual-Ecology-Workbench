package VEW.XMLCompiler.ASTNodes;

import java.util.ArrayList;
import java.util.List;

import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.Model.Catagory;

public class ConstructedASTree {
	
	private ASTree tree;
	private ArrayList<Exception> exceptions;
	private List<XMLTag> tagList;
	public ConstructedASTree(ASTree _tree, ArrayList<Exception> _excep) {
		tree = _tree;
		exceptions = new ArrayList<Exception>(_excep);
	}
	
	public ConstructedASTree(ASTree _tree) {
		tree = _tree;
		exceptions = new ArrayList<Exception>();
	}
	
	public ASTree getTree() {
		return tree;
	}
	
	public ArrayList<Exception> getExceptions() {
		return exceptions;
	}
	
	public void addSemanticException(SemanticCheckException ex) {
		exceptions.add(ex);
	}
	
	public boolean hasExceptions() {
		return !exceptions.isEmpty();
	}
	
	public void checkTree(Catagory parent) {
		tree.check(parent, this);
	}
	
	public List<XMLTag> compileTree() {
		String xml = tree.generateXML();
		tagList = constructXMLTag(xml);
		return tagList;
	}
	
	private List<XMLTag> constructXMLTag(String xmlString) {
		List<XMLTag> tagList = new ArrayList<XMLTag>();
		String[] eqStrings = xmlString.split(";");
		for (String eqString:eqStrings) {
			XMLTag eqTag = new XMLTag("equation");
			String[] eqParts = eqString.split(":");
			eqTag.addTag(new XMLTag("name", eqParts[0]));
			eqTag.addTag(new XMLTag("eq", eqParts[1]));
			tagList.add(eqTag);
		}
		return tagList;
	}

	public List<XMLTag> getTagList() {
		return tagList;
	}
	
	

}
