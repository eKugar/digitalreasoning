package dr.nlp.task3;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import dr.nlp.task3.grammar.NlpDocument;

/**
 * Created by Dmitry Kozinets on 4/3/2016.
 *

 Remote programming exercise

 This project should be well tested and code should be checked into GitHub along with any
 output produced from running your program. Write your solution in the Java programming
 language, using only the standard libraries. Do not use any third-­party libraries for natural
 language processing. Describe any assumptions you make in your implementation. What are
 the limitations of your approach? What other approaches might be possible? At the end of the
 assignment there should be a commit/push for each of the following features.

 1. Write a program that identifies sentence boundaries and tokenizes the text in the file
 “ nlp_data.txt ” into words. It should correctly process all symbols, including punctuation
 and whitespace. Every word must fall into a sentence. Create data structures that
 efficiently express the data you have processed. When your program runs it should
 output an XML representation of your Java object model.

 2. Modify your program from #1 to add rudimentary recognition of proper nouns (“named
 entities”) in the input, and print a list of recognized named entities when it runs. The list
 of named entities is in the file “ NER.txt ”. Enhance your data structures and output
 schema to store information about which portions of the text represent named entities.

 3. Modify your program from #2 to use “ nlp_data.zip ” as its input. Use a thread pool to
 parallelize the processing of the text files contained in the zip. Aggregate the results
 and modify the output schema accordingly.


 This is part 3

 *
 */
public class NlpProcessor
{
	private Path filePathSource;
	private Path filePathXmlDestination;

	public NlpProcessor( Path filePathSource, Path filePathXmlDestination )
	{
		this.filePathSource = filePathSource;
		this.filePathXmlDestination = filePathXmlDestination;
	}

	public void parse()
	{
		parseAndLoadZip( this.filePathSource );
	}

	void shutdownAndAwaitTermination( ExecutorService pool )
	{
		pool.shutdown(); // Disable new tasks from being submitted
		try
		{
			// Wait a while for existing tasks to terminate
			if( !pool.awaitTermination( 1, TimeUnit.DAYS ) )
			{
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if( !pool.awaitTermination( 1, TimeUnit.DAYS ) )
				{
					System.err.println( "Pool did not terminate" );
				}
			}
		}
		catch( InterruptedException ie )
		{
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

	public int parseAndLoadZip( Path filePath )
	{
		try( ZipFile zipFile = new ZipFile( filePath.toString() );
			 BufferedWriter sharedWriter = FileUtils.createWriter( filePathXmlDestination ); )
		{
			// print header only once to xml file
			sharedWriter.write(NlpDocument.generateHeader());
			sharedWriter.write("<NlpDocuments>\n");

			int cores = Runtime.getRuntime().availableProcessors();
			System.out.println( "Available cores: " + cores );
			System.out.println( "Creating thread pool with " + cores + " threads" );
			ExecutorService pool = Executors.newFixedThreadPool( cores );

			System.out.println( "Zip File Name = [" + filePath.toString() + "]" );

			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while( entries.hasMoreElements() )
			{
				ZipEntry entry = entries.nextElement();
				if( entry.getName().startsWith( "nlp_data" ) && entry.getName().endsWith( ".txt" ) )
				{
					System.out.println( "Processing Zip entry name = [" + entry.getName() + "]" );

					pool.execute( new NlpDocument( zipFile.getInputStream( entry ), entry.getName(), sharedWriter ) );

				}
			}
			shutdownAndAwaitTermination( pool );
			sharedWriter.write("</NlpDocuments>\n");
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
			return -1;
		}
		catch( IOException e )
		{
			e.printStackTrace();
			return -1;
		}

		return 1;
	}


	public static void main( String[] args )
	{
		NlpProcessor nlpProcessor = new NlpProcessor( Paths.get( ".\\nlp_data.zip" ), Paths.get( ".\\nlp_data3.xml" ) );
		nlpProcessor.parse();
	}
}
