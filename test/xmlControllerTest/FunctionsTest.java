package xmlControllerTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import VEW.Common.XML.XMLTag;
import VEW.Planktonica2.ControllerStructure.Equation;
import VEW.Planktonica2.ControllerStructure.Function;
import VEW.Planktonica2.ControllerStructure.Stage;
import VEW.Planktonica2.ControllerStructure.XMLTagEnum;

public class FunctionsTest {

	private String stageName = "stage_name";

	private XMLTag function;
	private XMLTag stage;
	
	@Before
	public void setUp() throws Exception {
		XMLTag functionTag = new XMLTag (XMLTagEnum.FUNCTION.xmlTag());
		XMLTag name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "functionName");
		XMLTag calledIn = new XMLTag(XMLTagEnum.CALLED_IN.xmlTag(), this.stageName);

		XMLTag equation1 = new XMLTag(XMLTagEnum.EQUATION.xmlTag());
		XMLTag equation1Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "e1");
		XMLTag equation1Eq = new XMLTag(XMLTagEnum.EQ.xmlTag(), "\\assign{\\var{cb}, \\var{mh}}");

		XMLTag equation2 = new XMLTag(XMLTagEnum.EQUATION.xmlTag());
		XMLTag equation2Name = new XMLTag(XMLTagEnum.NAME.xmlTag(), "e2");
		XMLTag equation2Eq = new XMLTag(XMLTagEnum.EQ.xmlTag(), "\\assign{\\var{cb}, \\plus{\\var{mh}, \\sival{1.0, 0}}}");


		XMLTag archiveName = new XMLTag(XMLTagEnum.ARCHIVE_NAME.xmlTag(), "herrow");


		XMLTag stage = new XMLTag(XMLTagEnum.STAGE.xmlTag());
		stage.setAttribute("log", "false");
		XMLTag stageName = new XMLTag(XMLTagEnum.NAME.xmlTag(), this.stageName);
		XMLTag stageComment = new XMLTag(XMLTagEnum.COMMENT.xmlTag(), "Herrow again");

		stage.addTag(stageName);
		stage.addTag(stageComment);

		equation1.addTag(equation1Name);
		equation1.addTag(equation1Eq);

		equation2.addTag(equation2Name);
		equation2.addTag(equation2Eq);

		functionTag.addTag(name);
		functionTag.addTag(calledIn);
		functionTag.addTag(archiveName);
		functionTag.addTag(equation1);
		functionTag.addTag(equation2);

		this.function = functionTag;
		this.stage = stage;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Stage s = new Stage();

		s.build(this.stage);

		Collection<Stage> stages = new ArrayList<Stage> (1);

		stages.add(s);

		Function f = new Function (stages);

		f.build(function);

		assertNotNull(f.getName());
		assertEquals(f.getName(), "functionName");

		assertNotNull(f.getCalledIn());
		assertEquals(f.getCalledIn().size(), 1);

		for (Stage input : f.getCalledIn()) {
			assertEquals(input.getName(), stageName);
		}

		assertNotNull(f.getArchiveName());
		assertEquals(f.getArchiveName(), "herrow");


		assertNotNull(f.getEquations());

		Equation [] equations = new Equation [2];

		equations = f.getEquations().toArray(equations);

		assertEquals(equations.length, 2);

		assertNotNull(equations[0]);
		assertNotNull(equations[1]);

		if (!equations[0].getName().equals("e1")) {
			assertEquals(equations[0].getName(), "e2");
		}

		if (!equations[1].getName().equals("e2")) {
			assertEquals(equations[1].getName(), "e1");
		}


	}

}
