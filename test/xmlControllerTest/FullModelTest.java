package xmlControllerTest;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLFile;
import VEW.Planktonica2.Model.Model;

public class FullModelTest {

	private final String pathSep = System.getProperty("file.separator");
	private final String fileName = "Model.xml";
	
	int noRuns = 1000;

	private XMLFile f1;
	private XMLFile f2;
	private XMLFile f3;
	private XMLFile f4;
	private XMLFile f5;
	
	ArrayList<Long>times;
	long totalTime;
	
	@Before
	public void setUp() throws Exception {
		
		f1 = new XMLFile("ModelTree" + pathSep + "LERM-ES" + pathSep + "1" + pathSep, fileName);
		f2 = new XMLFile("ModelTree" + pathSep + "LERM-ES" + pathSep + "2" + pathSep, fileName);
		f3 = new XMLFile("ModelTree" + pathSep + "LERM-ES" + pathSep + "3" + pathSep, fileName);
		
		f4 = new XMLFile("ModelTree" + pathSep + "LERM-PS" + pathSep + "1" + pathSep, fileName);
		f5 = new XMLFile("ModelTree" + pathSep + "LERM-PS" + pathSep + "2" + pathSep, fileName);
		
		
	}

	@After
	public void tearDown() throws Exception {
		
		
		
		System.out.println("Total time: " + totalTime);
		
		long sum = 0;
		for (long t : times) {
			sum += t;
		}
		
		double mean = (float) sum / (float) times.size();
		
		System.out.println("Mean: "  + mean);				
		
	}

	@Test
	public void test() throws BackingStoreException {
		
		long startTime = System.currentTimeMillis();
		
		times = new ArrayList<Long> (); 
		
		for (int i = 0; i < noRuns; i++) {
			
			long sTime = System.currentTimeMillis();
			
			Model m = new Model(f1);
			m.buildFromFile();

			m = new Model(f2);
			m.buildFromFile();

			m = new Model(f3);
			m.buildFromFile();

			m = new Model(f4);
			m.buildFromFile();

			m = new Model(f5);
			m.buildFromFile();
			
			long time = System.currentTimeMillis() - sTime;
			times.add(time);
		}
		
		totalTime = System.currentTimeMillis() - startTime;
		
	}

}
