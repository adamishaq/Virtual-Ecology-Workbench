package BACONCompiler.CycleChecker;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLFile;
import VEW.Planktonica2.Model.Catagory;
import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.Model;
import VEW.XMLCompiler.ANTLR.ANTLRParser;
import VEW.XMLCompiler.ANTLR.BACONCompiler;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;
import VEW.XMLCompiler.ASTNodes.RuleNode;
import VEW.XMLCompiler.DependencyChecker.DependantMetaData;
import VEW.XMLCompiler.DependencyChecker.OrderingAgent;

public class SimpleCycleCheck {

	
	private static final String SEPARATOR = File.separator;
	private static final String MODELRESOURCE = "ModelTree"+SEPARATOR+"LERM-ES"+SEPARATOR+"4"+SEPARATOR;
	private OrderingAgent o;
	
	@Before
	public void setUp() throws Exception {
		
		XMLFile f = XMLFile.LoadFile(MODELRESOURCE + "Model.xml");
		
		
		
		Model m = new Model(f);
		m.buildFromFile();
		
		BACONCompiler parser;
		
		
		Collection<DependantMetaData<ConstructedASTree>> all = new ArrayList<DependantMetaData<ConstructedASTree>> ();
		for (Catagory c : m.getFunctionalGroups()) {
			for (Function fun : c.getFunctions()) {
				String source = readSourceFile(fun.getSource_code() + fun.getParent().getName() + "\\" + fun.getName() + ".bacon");
				parser = new BACONCompiler(fun, source);
				parser.compile();
				ConstructedASTree t = parser.getTree();
				all.add(new DependantMetaData<ConstructedASTree> (t, fun));
			}
		}
		
		
		o = new OrderingAgent(all);		
		
	}
	
	private String readSourceFile(String parentPath) throws FileNotFoundException, IOException {
		FileInputStream fStream = new FileInputStream(parentPath);
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fStream));
		String sourceCode = "";
		String line = new String();
		while ((line = buffReader.readLine()) != null) 
			sourceCode += line + '\n';
		return sourceCode;
	}

	@Test
	public void test() {
		
		assertTrue(o.reorder());
		
		assertTrue(o.getFunctionOrder().isEmpty());
		
	}

}
