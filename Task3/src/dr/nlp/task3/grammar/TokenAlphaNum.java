package dr.nlp.task3.grammar;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class TokenAlphaNum extends Token
{
	public TokenAlphaNum( String token )
	{
		super(token);
	}

	public TokenAlphaNum( String token, NamedEntityMatch match )
	{
		super(token, match);
	}
}

