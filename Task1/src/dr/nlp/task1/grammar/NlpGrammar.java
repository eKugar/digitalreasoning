package dr.nlp.task1.grammar;

import java.util.Map;

/**
 * Created by Dmitry Kozinets on 4/3/2016.
 */
public interface NlpGrammar
{
	void parse();

	String getXML( boolean includeChildren );

	default String getClassName()
	{
		return this.getClass().getSimpleName();
	}

	default String getOpeningXML()
	{
		return "<" + getClassName() + ">";
	}

	// Will create opening xml tag with all the attributes from the Map
	default String getOpeningXML( Map<String, String> attributes )
	{
		StringBuilder result = new StringBuilder();
		result.append( "<" + getClassName() + " " );
		if( attributes != null && !attributes.isEmpty() )
		{
			for( Map.Entry<String, String> entry : attributes.entrySet() )
			{
				result.append( entry.getKey() );
				result.append( "=\"" + entry.getValue() + "\"" );
			}
		}
		result.append( ">" );
		return result.toString();
	}

	default String getClosingXML()
	{
		return "</" + getClassName() + ">";
	}
}
