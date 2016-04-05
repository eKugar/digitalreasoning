package dr.nlp.task3.grammar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class TokenWhiteSpaceTest
{
	@Test
	public void testGetXML() throws Exception
	{
		Token token = new TokenWhiteSpace(" ");
		token.getXML( true );
		assertEquals( "\t\t<TokenWhiteSpace NamedEntityMatch=\"NO\"> </TokenWhiteSpace>\n", token.getXML( true ) );
	}
}