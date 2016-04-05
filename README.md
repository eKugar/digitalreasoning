# digitalreasoning
Digital Reasoning Exercise

Part One

Write a program that identifies sentence boundaries and tokenizes the text in the file "nlp_data.txt" into words. It should correctly process all symbols, including punctuation and whitespace. Every word must fall into a sentence. Create data structures that efficiently express the data you have processed. When your program runs it should output an XML representation of your Java object model.

Assumtions:

-Assuming input files are relatively small.

-We will not run into memory issues.

-Using Java 8 and JUnit for testing


to run:

dr.nlp.task1.NlpProcessor.java


output file:

nlp_data.xml



Part Two

Modify your program from #1 to add rudimentary recognition of proper nouns (“named
entities”) in the input, and print a list of recognized named entities when it runs. The list
of named entities is in the file “ NER.txt ”. Enhance your data structures and output
schema to store information about which portions of the text represent named entities.


Assumtions:

-Assuming input files are relatively small.

-We will not run into memory issues.

-Better matching is possible 

-Using Java 8 and JUnit for testing


to run:

dr.nlp.task2.NlpProcessor.java


output file:

nlp_data2.xml


