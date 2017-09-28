# plagiarismCheck
This is a command-line program that performs plagiarism detection using a N-tuple comparison algorithm allowing for synonyms in the text.
The program should take in 3 required arguments, and one optional.  
In other cases such as no arguments, the program should print out usage instructions.
1.	file name for a list of synonyms
2.	input file1.txt
3.	input file2.txt
4.	(optional) the number N, the tuple size.  If not supplied, the default should be N=3.
----------------------------------------------------------------------
NTuple check Algorithm:
1.For every tuple of synonyms file, take every synonym word from this tuple and put it as a key into the HashMap and set the whole tuple as its corresponding value.
2.Search through all the words in file1 and file2. If the word matches with the key in synonym HashMap, replace the word with the first synonym word in the corresponding list of synonyms.
3.Compare the replaced file1 and file2 and count for duplicates.
4.Calculate the percentage of plagiarism.
----------------------------------------------------------------------
checkPlagiarism.java:
The main function to take in inputs from command-line.
Call functions from other files to process the contents in these files.
Print out the result of plagiarism check.

NTuple.java:
Abstract type for extended use of this NTuple algorithm.

FileProcessor.java:
Process the files and clean punctuations.
Handle file I/O specific.
Save the contents into Ntuple type.

checkSimilarity.java:
Take the passing parameters from main function and save them in local variables.
Process to compare the duplicates in file1 and file2 using the synonym file.
Return the percentage as an integer.
-----------------------------------------------------------------------
Assumption:
1.Past tense and future tense of a word are assumed as different words.
2.There are only{. , : ! ? “” ‘} as punctuations in the files. 
3.The order of the words does not influence the plagiarism check. That is to say:
	"go for a run" and "run go a for" has plagiarism 100%
4.If tuple size is greater than the number of words in file for any files, return 0%
	file1:"go for a run" and file2:"run go a for"and syns:"run sprint jog" and tuple size=4 return 100%
	file1:"go for a run" and file2:"run go a for"and syns:"run sprint jog" and tuple size=5 return 0%
5.If the tuble size is missing from command line, use the default tuple size.
