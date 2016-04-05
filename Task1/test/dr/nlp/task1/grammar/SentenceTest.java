package dr.nlp.task1.grammar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class SentenceTest
{
	@Test
	public void testGetXML() throws Exception
	{
		String test = "This is a test.";
		Sentence sentence = new Sentence( test );
		sentence.parse();
		assertEquals( "\t<Sentence>\n" +
							  "\t\t<TokenWord>This</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord>is</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord>a</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord>test</TokenWord>\n" +
							  "\t\t<TokenPunctuation>.</TokenPunctuation>\n" +
							  "\t</Sentence>\n", sentence.getXML( true ) );
	}

	@Test
	public void testIsNumeric() throws Exception
	{
		assertTrue( Sentence.isNumeric( "33" ) );
		assertTrue( Sentence.isNumeric( "33.44343" ) );
		assertTrue( Sentence.isNumeric( "-33" ) );
		assertTrue( Sentence.isNumeric( "0" ) );
		assertTrue( Sentence.isNumeric( "000" ) );
		assertTrue( Sentence.isNumeric( "0343" ) );
		assertTrue( Sentence.isNumeric( "-33.5543543543543" ) );

		assertFalse( Sentence.isNumeric( "33A" ) );
		assertFalse( Sentence.isNumeric( "33.443.43" ) );
		assertFalse( Sentence.isNumeric( "-c33" ) );
		assertFalse( Sentence.isNumeric( "01a55" ) );
		assertFalse( Sentence.isNumeric( "test" ) );
		assertFalse( Sentence.isNumeric( "10,000.32" ) );
	}

	@Test
	public void testIsWhiteSpace() throws Exception
	{
		assertTrue( Sentence.isWhiteSpace( " " ) );
		assertTrue( Sentence.isWhiteSpace( "   " ) );
		assertTrue( Sentence.isWhiteSpace( "\t" ) );
		assertTrue( Sentence.isWhiteSpace( "\n" ) );
		assertTrue( Sentence.isWhiteSpace( " \t  \n  \t" ) );

		assertFalse( Sentence.isWhiteSpace( "33A" ) );
		assertFalse( Sentence.isWhiteSpace( "?" ) );
		assertFalse( Sentence.isWhiteSpace( "test" ) );
		assertFalse( Sentence.isWhiteSpace( "'" ) );
		assertFalse( Sentence.isWhiteSpace( ":" ) );
		assertFalse( Sentence.isWhiteSpace( " ?" ) );
	}

	@Test
	public void testGetSentenceString() throws Exception
	{
		String test = "This is a test.";
		Sentence sentence = new Sentence( test );
		assertEquals( test, sentence.getSentenceString() );
	}
}