package dr.nlp.task3;

import dr.nlp.task3.grammar.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith( Suite.class )
@Suite.SuiteClasses( {NlpDocumentTest.class,
					  SentenceTest.class,
					  TokenTest.class,
					  TokenWordTest.class,
					  TokenNumericTest.class,
					  TokenWhiteSpaceTest.class,
					  TokenPunctuationTest.class,} )
public class TestSuite
{
}