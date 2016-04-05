package dr.nlp.task3.grammar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class TokenWordTest
{
	@Test
	public void testGetXML() throws Exception
	{
		Token token = new TokenWord("Test");
		token.getXML( true );
		assertEquals( "\t\t<TokenWord NamedEntityMatch=\"NO\">Test</TokenWord>\n", token.getXML( true ) );
	}
}