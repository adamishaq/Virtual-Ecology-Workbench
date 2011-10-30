package VEW.XMLCompiler.ANTLR.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AssignTest.class, SimpleParserLexer.class, CreateTest.class, ComprehensiveParserTest.class, FailedAssignTest.class })
public class AllTests {

}
