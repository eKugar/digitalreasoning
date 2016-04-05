package dr.nlp.task3.grammar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class TokenPunctuationTest
{
	@Test
	public void testGetXML() throws Exception
	{
		Token token = new TokenPunctuation("?");
		token.getXML( true );
		assertEquals( "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">?</TokenPunctuation>\n", token.getXML( true ) );
	}
}