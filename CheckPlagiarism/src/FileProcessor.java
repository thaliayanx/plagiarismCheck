import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileProcessor {
	/**
	 * Read file and save the contents into arralist of string;
	 * 
	 * @param fileName;
	 * @return String of file content using " " as separator;
	 * @throws FileNotFoundException and IOException
	 */
	public static ArrayList<String> readInArrayList(String fileName) {
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader read = new BufferedReader(file);
			ArrayList<String> res = new ArrayList<String>();
			String line = read.readLine();
			while (line != null) {
				res.add(line);
				line = read.readLine();
			}
			file.close();
			return res;
		} catch (FileNotFoundException e) {
			System.err.println("Caught FileNotFoundException: "
					+ e.getMessage());
			return new ArrayList<String>();

		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
			return new ArrayList<String>();
		}
	}

	/**
	 * Read file and save the contents into an arrayList of strings;
	 * 
	 * @param fileName;
	 * @return ArrayList of strings of every line in the file;
	 * @throws FileNotFoundException and IOException
	 */
	public static ArrayList<NTuple> readInArray(String fileName, int N) {
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader read = new BufferedReader(file);
			ArrayList<NTuple> res = new ArrayList<NTuple>();
			StringBuilder tmp = new StringBuilder();
			String lines = read.readLine();
			while (lines != null) {
				tmp.append(lines);
				tmp.append(" ");
				lines = read.readLine();
			}
			String deal = tmp.toString();
			res.addAll(lineToTuple(deal, N));
			file.close();
			return res;
		} catch (FileNotFoundException e) {
			System.err.println("Caught FileNotFoundException: "
					+ e.getMessage());
			System.exit(-1);
			return new ArrayList<NTuple>();

		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
			System.exit(-1);
			return new ArrayList<NTuple>();
		}
	}

	/**
	 * Convert line from file contents to NTuple; Split words by space and other punctuations {. , ! ? : " '}
	 * @param every line of the file content;
	 * @param tuple size;
	 * @return arraylist of NTuples;
	 */
	public static ArrayList<NTuple> lineToTuple(String line, int N) {
		ArrayList<NTuple> res = new ArrayList<NTuple>();
		String[] words = line.split("[\\!\\?\\:\\.\\,\\s\"\']+");
		for (int i = 0; i <= words.length - N; i++) {
			NTuple tmp = new NTuple(N);
			for (int j = i; j < i + N; j++) {
				tmp.addNew(words[j]);
			}
			res.add(tmp);
		}

		return res;
	}

}
