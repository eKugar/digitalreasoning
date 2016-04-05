package dr.nlp.task3.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dr.nlp.task3.NamedEntities;

/**
 * Created by Dmitry Kozinets on 4/3/2016.
 */
public class Sentence implements NlpGrammar
{
	private static final String REGEX_NON_WORD = "\\W";

	private final String sentence;
	private ArrayList<Token> tokens = new ArrayList<>();

	public Sentence( String sentence )
	{
		this.sentence = sentence;
	}

	@Override
	public void parse()
	{
		if( !this.sentence.isEmpty() )
		{
			int start = 0;
			Pattern pattern = Pattern.compile( REGEX_NON_WORD );
			Matcher matcher = pattern.matcher( this.sentence );
			while( matcher.find() )
			{
				if( start != matcher.start() )
				{
					tokens.add( createNewWord( this.sentence.substring( start, matcher.start() ) ) );
					start = matcher.end();
				}
				else
				{
					start++;
				}

				tokens.add( createNewNonWord( matcher.group() ) );
			}
			if( start < this.sentence.length() )
			{
				tokens.add( createNewWord( this.sentence.substring( start ) ) );
			}
		}
	}

	@Override
	public String getXML( boolean includeChildren )
	{
		StringBuilder result = new StringBuilder();
		result.append( "\t" + this.getOpeningXML() + "\n" );
		if( includeChildren )
		{
			for( Token token : tokens )
			{
				result.append( token.getXML( includeChildren ) );
			}
		}
		result.append( "\t" + this.getClosingXML() + "\n" );
		return result.toString();
	}

	private Token createNewNonWord( String nonWord )
	{
		if( isWhiteSpace( nonWord ) )
		{
			return new TokenWhiteSpace( nonWord );
		}
		return new TokenPunctuation( nonWord );
	}

	private Token createNewWord( String word )
	{
		boolean partial = NamedEntities.INSTANCE.isPartialNamedEntity( word );

		NamedEntityMatch match = ( !partial ) ?	NamedEntityMatch.NO : NamedEntities.INSTANCE.isNamedEntity( word ) ? NamedEntityMatch.FULL : NamedEntityMatch.PARTIAL;
		if( isNumeric( word ) )
		{
			return new TokenNumeric( word, match );
		}
		return new TokenWord( word, match );
	}

	public static boolean isNumeric( String str )
	{
		try
		{
			double d = Double.parseDouble( str );
		}
		catch( NumberFormatException nfe )
		{
			return false;
		}
		return true;
	}

	public static boolean isWhiteSpace( String str )
	{
		if( str == null )
		{
			return false;
		}
		int length = str.length();
		for( int i = 0; i < length; i++ )
		{
			if( ( !Character.isWhitespace( str.charAt( i ) ) ) )
			{
				return false;
			}
		}
		return true;
	}

	public ArrayList<Token> getTokens()
	{
		return this.tokens;
	}

	public String getSentenceString()
	{
		return sentence;
	}
}
