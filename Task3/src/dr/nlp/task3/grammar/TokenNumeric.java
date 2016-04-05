package dr.nlp.task3.grammar;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class TokenNumeric extends TokenAlphaNum
{
	public TokenNumeric( String token )
	{
		super( token );
	}

	public TokenNumeric( String token, NamedEntityMatch match )
	{
		super( token, match );
	}
}
