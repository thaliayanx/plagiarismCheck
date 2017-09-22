import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Save all the synonyms from the synonyms file as keys and their corresponding tuple as value
 * Replace all the synonyms in both file1 and file2 using synMap;
 * Save file2 content into HashSet; 
 * Compare tuples in file1 and file2 to find percentage of similarity;
 */
public class checkSimilarity {
	// instance
	private static checkSimilarity instance = null;

	// variables to pass from PlagiarismDetector
	private ArrayList<String> synonym;
	private HashMap<String, ArrayList<String>> synMap = new HashMap<String, ArrayList<String>>();
	private HashSet<String> tSet = new HashSet<String>();
	private ArrayList<NTuple> word1;
	private ArrayList<NTuple> word2;

	// constructor
	protected checkSimilarity() {
	}

	public static checkSimilarity getInstance() {
		if (instance == null) {
			instance = new checkSimilarity();
		}
		return instance;
	}

	/**
	 * Take params from main and process to check plagiarism;
	 * 
	 * @param file1 content in string, file2 content in string, synonyms in arraylist;
	 * @return int percentage of plagiarism;
	 */
	public int initialSetting(ArrayList<NTuple> file1, ArrayList<NTuple> file2,
			ArrayList<String> synonyms) {
		synonym = synonyms;
		putSynMap();
		word1 = file1;
		word1 = synReplace(word1);
		word2 = file2;
		word2 = synReplace(word2);
		if (word1 == null || word2 == null) {
			return 0;
		}
		putTSet();

		return duplicateCheck();
	}

	/**
	 * Replace all synonyms in file with the first word in corresponding synonym list at synMap;
	 *  All characters are switched to their lowerCase;
	 * 
	 * @param file content in string;
	 * @return a string of replaced file content;
	 */
	private ArrayList<NTuple> synReplace(ArrayList<NTuple> readInFile) {
		for (NTuple word : readInFile) {
			for (int i = 0; i < word.getSize(); i++) {
				String check = word.getList().get(i).toLowerCase();
				if (synMap.containsKey(check)) {
					word.getList().remove(i);
					word.getList().add(i, synMap.get(check).get(0));
				}
			}

		}
		return readInFile;
	}

	/**
	 * Take tuples from replaced file1 content and compare them with the words
	 * from replaced file2 content saved in a HashMap;
	 * 
	 * @return int percentage of plagiarism;
	 */
	private int duplicateCheck() {
		int count = 0;
		int base = 0;
		for (NTuple word : word1) {
			String check = word.toString();
			if (tSet.contains(check)) {
				count++;
			} else {
				base++;
			}

		}
		int percentage = (int) (((double) count / (double) (count + base)) * 100);
		return percentage;
	}

	/**
	 * Save all the synonyms in syns.file as keys; Save every tuple into an arraylist as values; 
	 * Put all the synonyms as keys and their corresponding arraylist as values; 
	 * Setup the synMap as a HashMap;
	 */
	private void putSynMap() {
		for (String words : synonym) {
			String tmp[] = words.split(" ");
			ArrayList<String> val = new ArrayList<String>();
			for (int i = 0; i < tmp.length; i++) {
				val.add(tmp[i].toLowerCase());
			}
			for (int i = 0; i < tmp.length; i++) {
				String check = tmp[i].toLowerCase();
				if (!synMap.containsKey(check)) {
					synMap.put(tmp[i].toLowerCase(), val);
				}
			}
		}

	}

	/**
	 * Save the tuples from replaced file2 content into a HashSet; 
	 */
	private void putTSet() {
		for (NTuple word : word2) {
			StringBuilder res = new StringBuilder();
			for (int i = 0; i < word.getSize(); i++) {
				res.append(word.getList().get(i).toString());
				res.append(" ");
			}
			tSet.add(res.toString());
		}

	}

}
