
/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: PA12Main.java
 * ASSIGNMENT: Programming assignment 12
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: this program accepts three inputs from the command line: the .txt dictionary file,
 * 			a phrase with no spaces to find anagrams of, and a limit on the number of words in 
 * 			the found anagrams (or 0 to indicate no limit). Then, it will print all the words found
 * 			in that phrase as well as its anagrams. 
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PA12Main {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = args[0];
		String phrase = args[1];
		LetterInventory word = new LetterInventory(phrase);
		int limit = Integer.parseInt(args[2]);

		// open dict file and store its contents into an array;
		try {
			Scanner file = new Scanner(new File(fileName));
			List<String> words = new ArrayList<>();

			System.out.println("Phrase to scramble: " + phrase);
			while (file.hasNext()) {
				words.add(file.nextLine().strip());
			}
			List<String> foundWords = findAllWordsIn(words, word);

			System.out.println("\nAll words found in " + phrase + ":\n" + foundWords);
			System.out.println("\nAnagrams for " + phrase + ":");
			findAnagrams(words, word, new ArrayList<String>(), limit);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/*
	 * a function that finds all the words found in the given phrase, using a
	 * dictionary. it uses a LetterInventory class to check if a word is contained
	 * in a phrase.
	 * 
	 * @param dictionary: an array of words from the dictionary file.
	 * 
	 * @param phrase: a phrase from LetterInventory class
	 */
	public static List<String> findAllWordsIn(List<String> dictionary, LetterInventory phrase) {
		List<String> foundWords = new ArrayList<>();
		for (String word : dictionary) {
			if (phrase.contains(word))
				foundWords.add(word);
		}
		return foundWords;

	}

	/*
	 * a function that generates anagram phrases using recursive backtracking. The
	 * process involves choosing a word for part of the phrase and then recursively
	 * choosing words for the rest of the phrase. If it finds a collection of words
	 * that use up all of the letters in the phrase, it prints them.
	 * 
	 * @param words: an array of words from the dictionary file.
	 * 
	 * @param letters: a phrase from LetterInventory class
	 * 
	 * @param found: an array containing the generated anagram phrases.
	 * 
	 * @param limit: an integer,limit on the number of words in the found anagrams
	 * (or 0 to indicate no limit)
	 * 
	 */
	public static void findAnagrams(List<String> words, LetterInventory letters, List<String> found, int limit) {

		// prints the generated anagrams when there are no more letters left
		if (letters.isEmpty()) {
			if (limit > 0) {
				if (found.size() <= limit)
					System.out.println(found);
			} else {
				System.out.println(found);

			}

			return;
		}

		for (String word : words) {
			if (letters.contains(word)) {
				found.add(word);
				letters.subtract(word);
				findAnagrams(words, letters, found, limit);
				letters.add(word);
				found.remove(word);
			}
		}

	}
}
