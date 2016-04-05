package dr.nlp.task1.grammar;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dr.nlp.task1.FileUtils;

/**
 * Created by Dmitry Kozinets on 4/3/2016.
 */
public class NlpDocument implements NlpGrammar
{
	private static final String REGEX_SENTENCE = "(?<=[.?!;\\n])\\s+(?=\\p{Lu})";

	private Path filePath;
	private ArrayList<Sentence> sentences = new ArrayList<>();

	public NlpDocument( Path filePath )
	{
		this.filePath = filePath;
	}

	@Override
	public void parse()
	{
		// read the contents of the file into String and then parse String
		parse( FileUtils.read( this.filePath ) );
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
		}
	}

	@Override
	public String getXML( boolean includeChildren )
	{
		StringBuilder result = new StringBuilder();
		result.append( this.generateHeader() );
		result.append( this.getOpeningXML( this.generateAttributesMap() ) + "\n" );
		if( includeChildren )
		{
			for( Sentence sentence : sentences )
			{
				//				result.append( sentence.getSentenceString() );
				result.append( sentence.getXML( includeChildren ) );
			}
		}
		result.append( this.getClosingXML() + "\n" );
		return result.toString();
	}

	private Map<String, String> generateAttributesMap()
	{
		Map<String, String> attributes = new HashMap<>();
		attributes.put( "SourceFileName", this.filePath.toString() );
		return attributes;
	}

	private String generateHeader()
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
}
