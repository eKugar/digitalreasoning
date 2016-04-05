
#Digital Reasoning Exercise

##Using
- Java 8 (default methods in interface)
- JUnit 4 for testing
- IntelliJ


##General Assumptions
- Assuming input files are relatively small.
- We will not run into memory issues.


## Known Issues 
- Need to add more tests 
- Need to add more comments
- Better error handling is required
- Known matching issue: Broyden-Fletcher-Goldfarb-Shanno named entity was not working.  Not able to fix because of time constraints.
- Search for named entities needs to be improved to handle multiple word named entities better.   Right now, if the whitespace or some other character between words does not match the string entirely it is not considered a full match.



##Part One

Write a program that identifies sentence boundaries and tokenizes the text in the file
“ nlp_data.txt ” into words. It should correctly process all symbols, including punctuation
and whitespace. Every word must fall into a sentence. Create data structures that
efficiently express the data you have processed. When your program runs it should
output an XML representation of your Java object model.


###Project

Task1


###to run:

Task1/src/dr/nlp/task1/NlpProcessor.java


###output file:

Task1/nlp_data.xml


###test suite:

Task1/test/dr/nlp/task1/TestSuite.java


##Part Two

Modify your program from #1 to add rudimentary recognition of proper nouns (“named
entities”) in the input, and print a list of recognized named entities when it runs. The list
of named entities is in the file “ NER.txt ”. Enhance your data structures and output
schema to store information about which portions of the text represent named entities.


###Project

Task2


###to run:

Task2/src/dr/nlp/task2/NlpProcessor.java


###output file:

Task2/nlp_data2.xml


###test suite:

Task2/test/dr/nlp/task2/TestSuite.java


##Part Three

Modify your program from #2 to use “ nlp_data.zip ” as its input. Use a thread pool to
parallelize the processing of the text files contained in the zip. Aggregate the results
and modify the output schema accordingly.


###Project

Task3


###to run:

Task3/src/dr/nlp/task3/NlpProcessor.java


###output file:

Task3/nlp_data3.xml


###test suite:

Task3/test/dr/nlp/task3/TestSuite.java



##Author:
#####Dmitry Kozinets
