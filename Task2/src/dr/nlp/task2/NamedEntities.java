package dr.nlp.task2;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitry Kozinets on 4/4/2016.
 * <p>
 * Singleton class to hold Named Entities
 */
public enum NamedEntities
{
	INSTANCE;

	private Set<String> namedEntities;
	private Set<String> partialNamedEntities;

	NamedEntities()
	{
		this.namedEntities = FileUtils.readListOfNamedEntities( Paths.get( ".\\NER.txt" ) );
		this.createPartialNamedEntitiesList();
	}

	public boolean isNamedEntity( String s )
	{
		return this.namedEntities.contains( s );
	}

	public boolean isPartialNamedEntity( String s )
	{
		return this.partialNamedEntities.contains( s );
	}

	public void createPartialNamedEntitiesList()
	{
		this.partialNamedEntities = new HashSet<>();
		for( String namedEntity : this.namedEntities )
		{
			String[] tokens = namedEntity.split( "\\W" );
			for( String token : tokens )
			{
				this.partialNamedEntities.add( token );
			}
		}
	}
}