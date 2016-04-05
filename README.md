
#Digital Reasoning Exercise

##Using
- Java 8 (default methods in interface)
- JUnit 4 for testing
- IntelliJ


##General Assumptions
- Assuming input files are relatively small.
- We will not run into memory issues.


## Known Issues 
- Known matching issues: Broyden-Fletcher-Goldfarb-Shanno was not able to fix because of time constraints
- Not enough testing was done



##Part One

Write a program that identifies sentence boundaries and tokenizes the text in the file
“ nlp_data.txt ” into words. It should correctly process all symbols, including punctuation
and whitespace. Every word must fall into a sentence. Create data structures that
efficiently express the data you have processed. When your program runs it should
output an XML representation of your Java object model.


###Project

task1


###to run:

dr.nlp.task1.NlpProcessor.java


###output file:

nlp_data.xml



##Part Two

Modify your program from #1 to add rudimentary recognition of proper nouns (“named
entities”) in the input, and print a list of recognized named entities when it runs. The list
of named entities is in the file “ NER.txt ”. Enhance your data structures and output
schema to store information about which portions of the text represent named entities.


###Project

task2


###to run:

dr.nlp.task2.NlpProcessor.java


###output file:

nlp_data2.xml


##Part Three

Modify your program from #2 to use “ nlp_data.zip ” as its input. Use a thread pool to
parallelize the processing of the text files contained in the zip. Aggregate the results
and modify the output schema accordingly.


###Project

task3


###to run:

dr.nlp.task3.NlpProcessor.java


###output file:

nlp_data3.xml


##Author:
#####Dmitry Kozinets
