package dr.nlp.task3.grammar;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class Token implements NlpGrammar
{
	private String token;
	private NamedEntityMatch namedEntityMatch;

	public Token( String token )
	{
		this( token, NamedEntityMatch.NO );
	}

	public Token( String token, NamedEntityMatch match )
	{
		this.token = token;
		this.namedEntityMatch = match;
	}

	@Override
	public void parse()
	{
	}

	@Override
	public String getXML( boolean includeChildren )
	{
		return "\t\t" + getOpeningXML( generateAttributesMap() ) + token + getClosingXML() + "\n";
	}

	private Map<String, String> generateAttributesMap()
	{
		Map<String, String> attributes = new HashMap<>();
		attributes.put( "NamedEntityMatch", this.namedEntityMatch.toString() );
		return attributes;
	}

	public String getToken()
	{
		return this.token;
	}

	public NamedEntityMatch getNamedEntityMatch()
	{
		return this.namedEntityMatch;
	}

	public void setNamedEntityMatch( NamedEntityMatch namedEntityMatch )
	{
		this.namedEntityMatch = namedEntityMatch;
	}

}

