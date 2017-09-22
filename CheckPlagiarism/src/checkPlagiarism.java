import java.io.IOException;
import java.util.ArrayList;

public class checkPlagiarism {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file1;
		String file2;
		String synonymFile;
		int defaultTuples = 3;
		// Get input files from users in order: file1.txt file2.txt synonymsFile.txt, defaultTupleNumber
		// Return user message if the input format is incorrect
		if (args.length > 0) {
			if (args.length < 3 || args.length > 4) {
				System.err.println("Usage Message: file1Name file2Name synonymFile tuplesNumber");
				System.exit(-1);
			}
			file1 = args[0];
			file2 = args[1];
			synonymFile = args[2];
			if (args.length == 4) {
				String num = args[3];
				defaultTuples = Integer.valueOf(num);
			}
			if (defaultTuples <= 0) {
				System.err.println("Please input positive tuple size.");
				System.exit(-1);
			}
			// Process the files use methods in FileProcess.java
			ArrayList<NTuple> f1 = FileProcessor.readInArray(file1,
					defaultTuples);
			ArrayList<NTuple> f2 = FileProcessor.readInArray(file2,
					defaultTuples);
			if (f1 == null || f2 == null) {
				System.out.println("Run Test");
				System.out.println("Plagiarism Percentage: 0%");
			}
			ArrayList<String> sF = FileProcessor.readInArrayList(synonymFile);

			// Calculate the percentage by calling class SimilarityCheck
			int percentage = checkSimilarity.getInstance().initialSetting(f1,
					f2, sF);
			System.out.println("Run Test");
			System.out.println("Plagiarism Percentage: " + percentage + "%");
		} else {
			// Use test case in JUnit
			System.out.println("Run Test");
			synonymFile = "syns.txt";
			file1 = "file1.txt";
			file2 = "file2.txt";

			// Process the files use methods in FileProcess.java
			ArrayList<NTuple> f1 = FileProcessor.readInArray(file1,
					defaultTuples);
			ArrayList<NTuple> f2 = FileProcessor.readInArray(file2,
					defaultTuples);
			if (defaultTuples <= 0) {
				System.err.println("Please input positive tuple size.");
				System.exit(-1);
			}
			if (f1 == null || f2 == null) {
				System.out.println("Run Test");
				System.out.println("Plagiarism Percentage: 0%");
			}
			ArrayList<String> sF = FileProcessor.readInArrayList(synonymFile);

			// Calculate the percentage by calling class SimilarityCheck
			int percentage = checkSimilarity.getInstance().initialSetting(f1,
					f2, sF);
			System.out.println("Plagiarism Percentage: " + percentage + "%");

		}

	}

}
