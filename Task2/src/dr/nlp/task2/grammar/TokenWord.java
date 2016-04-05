package dr.nlp.task2.grammar;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 */
public class TokenWord extends TokenAlphaNum
{
	public TokenWord( String token )
	{
		super( token );
	}

	public TokenWord( String token, NamedEntityMatch match )
	{
		super( token, match );
	}
}
