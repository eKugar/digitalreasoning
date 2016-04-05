package dr.nlp.task1.grammar;

import java.nio.file.Paths;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class NlpDocumentTest
{
	@Test
	public void testDocument1() throws Exception
	{
		String test = "This is a test.";
		NlpDocument doc = new NlpDocument( Paths.get( "test.abc" ) );
		doc.parse( test );
		assertEquals( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
							  "<NlpDocument SourceFileName=\"test.abc\">\n" +
							  "\t<Sentence>\n" +
							  "\t\t<TokenWord>This</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord>is</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord>a</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord>test</TokenWord>\n" +
							  "\t\t<TokenPunctuation>.</TokenPunctuation>\n" +
							  "\t</Sentence>\n" +
							  "</NlpDocument>\n", doc.getXML( true ) );
	}

	@Test
	public void testDocument2() throws Exception
	{
		String test = "Testing!!!  Testing 1,2,3!?!?";
		NlpDocument doc = new NlpDocument( Paths.get( "test.abc" ) );
		doc.parse( test );
		assertEquals( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
							  "<NlpDocument SourceFileName=\"test.abc\">\n" +
							  "\t<Sentence>\n" +
							  "\t\t<TokenWord>Testing</TokenWord>\n" +
							  "\t\t<TokenPunctuation>!</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation>!</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation>!</TokenPunctuation>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t</Sentence>\n" +
							  "\t<Sentence>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord>Testing</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t\t<TokenNumeric>1</TokenNumeric>\n" +
							  "\t\t<TokenPunctuation>,</TokenPunctuation>\n" +
							  "\t\t<TokenNumeric>2</TokenNumeric>\n" +
							  "\t\t<TokenPunctuation>,</TokenPunctuation>\n" +
							  "\t\t<TokenNumeric>3</TokenNumeric>\n" +
							  "\t\t<TokenPunctuation>!</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation>?</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation>!</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation>?</TokenPunctuation>\n" +
							  "\t</Sentence>\n" +
							  "</NlpDocument>\n", doc.getXML( true ) );
	}
}