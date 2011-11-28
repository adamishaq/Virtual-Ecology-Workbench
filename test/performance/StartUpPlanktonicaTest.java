package performance;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLFile;
import VEW.Planktonica2.Planktonica;

public class StartUpPlanktonicaTest {

	private final String pathSep = System.getProperty("file.separator");
	private final String pathName = "ModelTree" + pathSep + "LERM-ES" + pathSep + "3" + pathSep;
	private final String fileName = "Model.xml";
	
	XMLFile f;
	
	ArrayList<Long> times;
	
	int noRuns = 50;
	
	@Before
	public void setUp() throws Exception {
		f = new XMLFile(pathName, fileName);
		times = new ArrayList<Long> ();
	}

	@After
	public void tearDown() throws Exception {
		
		long sum = 0;
		for (Long l : times) {
			sum += l;
		}
		
		System.out.println("Mean: " + ((float) sum / (float) times.size())/ 1000.0 + "s");
		
	}

	@Test
	public void test() {
		
		
		for (int i = 0; i < noRuns; i++) {
			long sTime = System.currentTimeMillis();
			new Planktonica(f);
			long time = System.currentTimeMillis() - sTime; 
			times.add(time);
		}
		
	}

}
