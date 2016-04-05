package dr.nlp.task1.grammar;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class Token implements NlpGrammar
{
	private final String token;

	public Token( String token )
	{
		this.token = token;
	}

	@Override
	public void parse()
	{
	}

	@Override
	public String getXML( boolean includeChildren )
	{
		return "\t\t" + getOpeningXML() + token + getClosingXML() +"\n";
	}
}
