# plagiarismDetector

This is a command-line program that performs plagiarism detection using a N- tuple comparison algorithm 
allowing for synonyms in the text.
The program takes in 3 required arguments, and one optional. In other cases such as no
arguments, the program prints out usage instructions.
1. file name for a list of synonyms
2. input file 1
3. input file 2
4. (optional) the number N, the tuple size.  If not supplied, the default should be N=3.

The synonym file has lines each containing one group of synonyms.  For example a line saying &quot;run
sprint jog&quot; means these words should be treated as equal.

The input files are declared plagiarized based on the number of N-tuples in file1 that appear in
file2, where the tuples are compared by accounting for synonyms as described above.  
For example,
the text &quot;go for a run&quot; has two 3-tuples, [&quot;go for a&quot;, &quot;for a run&quot;] both of which appear in the text &quot;go for
a jog&quot;.

The output of the program is the percent of tuples in file1 which appear in file2.  So for the
above example, the output would be one line saying &quot;100%&quot;.  In another example, for texts &quot;go for a
run&quot; and &quot;went for a jog&quot; and N=3 we would output &quot;50%&quot; because only one 3-tuple in the first text
appears in the second one.
