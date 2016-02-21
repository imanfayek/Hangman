/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file retrieves a word from an array list
 * created from a .txt file.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class HangmanLexicon {
	
	/*
	 * This method creates an array list of strings,
	 * calling them from the file HangmanLexicon.txt
	 */
	public HangmanLexicon() {
		BufferedReader rd = null;
		while(rd == null) {
			try {
				rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
				wordList = new ArrayList<String>();	
				while(true) {
					String word = rd.readLine();
					if(word == null) break;
					wordList.add(word);
				}
				rd.close();		
			} catch(IOException ex) {
				throw new ErrorException(ex);
			}
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		int totalCount = wordList.size();
		return totalCount;
	}
	
/** Returns the word at the specified index. */
	public String getWord(int index) {
		String word = wordList.get(index);
		return word;
	}
	
	private ArrayList<String> wordList;
	
}
