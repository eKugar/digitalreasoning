package dr.nlp.task1;

import java.io.*;
import java.nio.file.Path;

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
