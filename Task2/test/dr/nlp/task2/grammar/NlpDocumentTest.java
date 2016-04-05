package dr.nlp.task2.grammar;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

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
							  "\t\t<TokenWord NamedEntityMatch=\"NO\">This</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace NamedEntityMatch=\"NO\"> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord NamedEntityMatch=\"NO\">is</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace NamedEntityMatch=\"NO\"> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord NamedEntityMatch=\"NO\">a</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace NamedEntityMatch=\"NO\"> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord NamedEntityMatch=\"NO\">test</TokenWord>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">.</TokenPunctuation>\n" +
							  "\t</Sentence>\n" +
							  "\t<matches>\n" +
							  "\t</matches>\n" +
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
							  "\t\t<TokenWord NamedEntityMatch=\"NO\">Testing</TokenWord>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">!</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">!</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">!</TokenPunctuation>\n" +
							  "\t\t<TokenWhiteSpace NamedEntityMatch=\"NO\"> </TokenWhiteSpace>\n" +
							  "\t</Sentence>\n" +
							  "\t<Sentence>\n" +
							  "\t\t<TokenWhiteSpace NamedEntityMatch=\"NO\"> </TokenWhiteSpace>\n" +
							  "\t\t<TokenWord NamedEntityMatch=\"NO\">Testing</TokenWord>\n" +
							  "\t\t<TokenWhiteSpace NamedEntityMatch=\"NO\"> </TokenWhiteSpace>\n" +
							  "\t\t<TokenNumeric NamedEntityMatch=\"NO\">1</TokenNumeric>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">,</TokenPunctuation>\n" +
							  "\t\t<TokenNumeric NamedEntityMatch=\"NO\">2</TokenNumeric>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">,</TokenPunctuation>\n" +
							  "\t\t<TokenNumeric NamedEntityMatch=\"NO\">3</TokenNumeric>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">!</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">?</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">!</TokenPunctuation>\n" +
							  "\t\t<TokenPunctuation NamedEntityMatch=\"NO\">?</TokenPunctuation>\n" +
							  "\t</Sentence>\n" +
							  "\t<matches>\n" +
							  "\t</matches>\n" +
							  "</NlpDocument>\n", doc.getXML( true ) );
	}


	@Test
	public void testMatches1() throws Exception
	{
		Set<String> set = new HashSet<>();
		String test = "Testing!!!  Testing 1,2,3!?!?";

		NlpDocument doc = new NlpDocument( Paths.get( "test.abc" ) );
		doc.parse( test );
		assertEquals( set, doc.getMatchedEntities() );
	}


	@Test
	public void testMatches2() throws Exception
	{
		Set<String> set = new HashSet<>();
		set.add( "Sarajevo" );

		String test = "Sarajevo";

		NlpDocument doc = new NlpDocument( Paths.get( "test.abc" ) );
		doc.parse( test );
		assertEquals( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
							  "<NlpDocument SourceFileName=\"test.abc\">\n" +
							  "\t<Sentence>\n" +
							  "\t\t<TokenWord NamedEntityMatch=\"FULL\">Sarajevo</TokenWord>\n" +
							  "\t</Sentence>\n" +
							  "\t<matches>\n" +
							  "\t\t<match>Sarajevo</match>\n" +
							  "\t</matches>\n" +
							  "</NlpDocument>\n", doc.getXML( true ) );
		assertEquals( set, doc.getMatchedEntities() );
	}

	@Test
	public void testMatches3() throws Exception
	{
		Set<String> set = new HashSet<>();
		set.add( "Gavrilo Princip" );
		set.add( "Sarajevo" );

		String test = "Yugoslav nationalist Gavrilo Princip (19 years old at the time) in Sarajevo.";

		NlpDocument doc = new NlpDocument( Paths.get( "test.abc" ) );
		doc.parse( test );
		assertEquals( set, doc.getMatchedEntities() );
	}


	@Test
	public void testMatches4() throws Exception
	{
		Set<String> set = new HashSet<>();
		set.add( "Olympic Games" );
		set.add( "Japan" );
		set.add( "North America" );

		String test = "Olympic Games in Japan and North America";

		NlpDocument doc = new NlpDocument( Paths.get( "test.abc" ) );
		doc.parse( test );
		assertEquals( set, doc.getMatchedEntities() );
	}

	@Test
	public void testMatches5() throws Exception
	{
		Set<String> set = new HashSet<>();
		set.add( "Olympic Games" );
		set.add( "North America" );

		String test = "Olympic Games are off the coast of North America";

		NlpDocument doc = new NlpDocument( Paths.get( "test.abc" ) );
		doc.parse( test );
		assertEquals( set, doc.getMatchedEntities() );
	}
}