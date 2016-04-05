package dr.nlp.task1.grammar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class TokenTest
{
	@Test
	public void testGetXML() throws Exception
	{
		Token token = new Token("Test");
		token.getXML( true );
		assertEquals( "\t\t<Token>Test</Token>\n", token.getXML( true ) );
	}
}