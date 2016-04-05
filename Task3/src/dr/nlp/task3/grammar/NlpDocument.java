package dr.nlp.task3.grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dr.nlp.task3.FileUtils;
import dr.nlp.task3.NamedEntities;

/**
 * Created by Dmitry Kozinets on 4/3/2016.
 */
public class NlpDocument implements NlpGrammar, Runnable
{
	private static final String REGEX_SENTENCE = "(?<=[.?!;\\n])\\s+(?=\\p{Lu})";

	private Path filePath;
	private InputStream inputStream;
	private Writer writer;

	private ArrayList<Sentence> sentences = new ArrayList<>();
	private Set<String> matchedEntities = new HashSet<>();
	private Set<String> partiallyMatchedEntities = new HashSet<>();

	public NlpDocument( Path filePath )
	{
		this.filePath = filePath;
		this.inputStream = null;
	}

	public NlpDocument( InputStream inputStream, String zipFileName, Writer writer )
	{
		this.filePath = Paths.get( zipFileName );
		this.inputStream = inputStream;
		this.writer = writer;
	}

	@Override
	public void parse()
	{
		// read the contents of the file into String and then parse String
		if( this.inputStream != null )
		{
			parse( FileUtils.read( this.inputStream ) );
		}
		else
		{
			parse( FileUtils.read( this.filePath ) );
		}
	}

	// Exposing this method for testing purposes
	public void parse( String fileContents )
	{
		if( !fileContents.isEmpty() )
		{
			int start = 0;
			Pattern pattern = Pattern.compile( REGEX_SENTENCE );
			Matcher matcher = pattern.matcher( fileContents );
			while( matcher.find() )
			{
				sentences.add( createNewSentence( fileContents.substring( start, matcher.end() - 1 ) ) );
				start = matcher.end() - 1;
			}
			sentences.add( createNewSentence( fileContents.substring( start ) ) );

			updateTokens();
		}
	}

	private void updateTokens()
	{
		Deque<Token> queue = new ArrayDeque<>();
		String str = "";

		for( Sentence sentence : sentences )
		{
			for( Token token : sentence.getTokens() )
			{
				if( token instanceof TokenAlphaNum )
				{
					switch( token.getNamedEntityMatch() )
					{
						case FULL:
							matchedEntities.add( token.getToken() );
							// now do the same as partial

						case PARTIAL:
							partiallyMatchedEntities.add( token.getToken() );
							queue.addLast( token );
							str = str + token.getToken();
							checkMatches( queue, str );
							break;

						case NO:
							emptyQueueAndLookForMatches( queue );
							str = "";
							break;
					}
				}
				else
				{
					// Punctuation or white space
					if( !queue.isEmpty() )
					{
						queue.addLast( token );
						str = str + token.getToken();
					}
				}
			}
			emptyQueueAndLookForMatches( queue );
		}
		partiallyMatchedEntities.removeAll( matchedEntities );
	}

	private void emptyQueueAndLookForMatches( Deque<Token> queue )
	{
		String str;
		while( !queue.isEmpty() )
		{
			Token t = queue.removeFirst();
			//remove punctuation or white space from the first
			if( !queue.isEmpty() )
			{
				Token punc = queue.peekFirst();
				if( !( punc instanceof TokenAlphaNum ) )
				{
					queue.removeFirst();
				}
			}
			//remove punctuation or white space from the last
			if( !queue.isEmpty() )
			{
				Token punc = queue.peekLast();
				if( !( punc instanceof TokenAlphaNum ) )
				{
					queue.removeLast();
				}
			}
			if( !queue.isEmpty() )
			{
				str = getRebuildStringFromQueue( queue );
				checkMatches( queue, str );
			}
		}
	}

	private String getRebuildStringFromQueue( Deque<Token> queue )
	{
		StringBuilder tmp = new StringBuilder();
		for( Token token : queue )
		{
			tmp.append( token.getToken() );
		}
		return tmp.toString();
	}

	private void checkMatches( Deque<Token> queue, String str )
	{
		if( NamedEntities.INSTANCE.isNamedEntity( str ) )
		{
			//Found full match - mark all tokens in Queue FULL
			for( Token matchedToken : queue )
			{
				matchedToken.setNamedEntityMatch( NamedEntityMatch.FULL );
			}
			matchedEntities.add( str );
		}
	}

	@Override
	public String getXML( boolean includeChildren )
	{
		StringBuilder result = new StringBuilder();
		result.append( this.getOpeningXML( this.generateAttributesMap() ) + "\n" );
		if( includeChildren )
		{
			for( Sentence sentence : sentences )
			{
				result.append( sentence.getXML( includeChildren ) );
			}
		}
		// Add matches
		result.append( "\t<matches>\n" );
		for( String match : matchedEntities )
		{
			result.append( "\t\t<match>" + match + "</match>\n" );
		}
		result.append( "\t</matches>\n" );

		// Add partial matches
		result.append( "\t<partialMatches>\n" );
		for( String match : partiallyMatchedEntities )
		{
			result.append( "\t\t<partialMatch>" + match + "</partialMatch>\n" );
		}
		result.append( "\t</partialMatches>\n" );

		result.append( this.getClosingXML() + "\n" );
		return result.toString();
	}

	private Map<String, String> generateAttributesMap()
	{
		Map<String, String> attributes = new HashMap<>();
		attributes.put( "SourceFileName", this.filePath.toString() );
		return attributes;
	}

	public static String generateHeader()
	{
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
	}

	private Sentence createNewSentence( String s )
	{
		Sentence sentence = new Sentence( s );
		sentence.parse();
		return sentence;
	}

	public void generateXmlFile( Path filePathXmlDestination )
	{
		String xml = this.getXML( true );
		FileUtils.write( filePathXmlDestination, xml );

		// For debugging purposes
		System.out.println( "File created: [" + filePathXmlDestination.toString() + "] with the following contents:" );
		System.out.println( xml );
	}

	public void writeResultsToFile( Writer writer )
	{
		String xml = this.getXML( true );
		try
		{
			synchronized( NlpDocument.class )
			{
				writer.write( xml );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		// For debugging purposes
		System.out.println( "Writing to shared output file the following contents:" );
		System.out.println( xml );
	}

	public void printMatches()
	{
		System.out.println( "Full matches:" );
		for( String match : matchedEntities )
		{
			System.out.println( match );
		}
	}

	public void printPartialMatches()
	{
		System.out.println( "Partial matches:" );
		for( String partialMatch : partiallyMatchedEntities )
		{
			System.out.println( partialMatch );
		}
	}

	public Set<String> getMatchedEntities()
	{
		return matchedEntities;
	}

	public Set<String> getPartiallyMatchedEntities()
	{
		return partiallyMatchedEntities;
	}

	@Override
	public void run()
	{
		parse();
		writeResultsToFile( this.writer );
	}
}
