package dr.nlp.task2;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitry Kozinets on 4/3/2016.
 */
public class FileUtils
{
	public static String read( Path filePath )
	{
		StringBuilder result = new StringBuilder();
		char[] buffer = new char[2048];
		int length = 0;
		try( BufferedReader reader = new BufferedReader( new FileReader( filePath.toString() ) ) )
		{
			while( ( length = reader.read( buffer, 0, buffer.length ) ) != -1 )
			{
				result.append( buffer, 0, length );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		return result.toString();
	}

	public static Set<String> readListOfNamedEntities( Path filePath )
	{
		Set<String> set = new HashSet<>();
		try( BufferedReader reader = new BufferedReader( new FileReader( filePath.toString() ) ) )
		{
			String line;
			while( ( line = reader.readLine() ) != null )
			{
				set.add( line.trim() );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		return set;
	}

	public static void write( Path filePath, String data )
	{
		try( BufferedWriter writer = new BufferedWriter( new FileWriter( filePath.toString() ) ) )
		{
			writer.write( data );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
}
