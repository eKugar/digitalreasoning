package dr.nlp.task3.grammar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class TokenNumericTest
{
	@Test
	public void testGetXML() throws Exception
	{
		Token token = new TokenNumeric("34.55");
		token.getXML( true );
		assertEquals( "\t\t<TokenNumeric NamedEntityMatch=\"NO\">34.55</TokenNumeric>\n", token.getXML( true ) );
	}
}