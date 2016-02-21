/*
 * File: Hangman.java
 * ------------------
 * This program plays the game of Hangman.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {

	/*
	 * This brings all the pieces of hangman together into one method 
	 */
    public void run() {
		hangmanConsole();   	
    }
    
    /*
     * This allows the graphics display to appear alongside the interactive portion of hangman
     */
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    
    /*
     * This method controls the interactive component of hangman
     */
    private void hangmanConsole() {
    	canvas.reset();
    	println("Welcome to Hangman!");
    	
    	getWord();    	
    	makeDashes();
    	canvas.displayWord(hangmanWord);
    	    	
    	while(NGUESSES != 0) {
    		if (hangmanWord.indexOf("-") == -1) {
    			println("You guessed the word: " + wordToGuess);
    			println("You have won.");
    			break;
    		} else {
    			hangmanPrompt();
    			if (guessInitial.length() == 1 && (guess >= 'a' && guess <= 'z')) {
					if (wordToGuess.indexOf(guess) != -1) {
						replaceDashes();
						canvas.displayWord(hangmanWord);
						println("That guess is correct.");
					} else {
						println("There are no " + guess + "'s in the word.");
						NGUESSES--;
						canvas.noteIncorrectGuess(guess);
					}
				} else {
					println("Please enter only one letter.");
				}
    		}
    	}
    	
    	if (NGUESSES == 0) {
    		loserMessage();
    	}
	}
    
    /*
     * This gets the word from the HangmanLexicon
     */
    private void getWord() {
    	wordList = new HangmanLexicon();
    	int randomWord = rgen.nextInt(0, wordList.getWordCount());
    	wordToGuess = wordList.getWord(randomWord);
    	wordToGuess = wordToGuess.toLowerCase();
    }
    
    /*
     * This method creates a dash for each letter that is in the word
     */
    private void makeDashes() {
    	hangmanWord = "";
    	for(int i=0; i < wordToGuess.length(); i++) {
    		hangmanWord += "-";
    	}
    }
    
    /*
     * This method tells the user what the word looks like
     * and how many guesses are left
     * it also prompts the user to enter a letter
     */
    private void hangmanPrompt() {
    	print("Your word looks like this: ");
		println(hangmanWord);
		println("You have " + NGUESSES + " guesses left.");
		requestGuess();
    }
    
    /*
     * This method accepts a string from the user, converts it
     * to lowercase and then converts it into a string.
     */
    private void requestGuess() {
    	guessInitial = readLine("Your guess: ");
		guessInitial = guessInitial.toLowerCase();
		guess = guessInitial.charAt(0);
    }
    
    /*
     * This method replaces the dashes with the correctly guessed letter
     */
    private void replaceDashes() {
    	for(int i = 0; i < wordToGuess.length(); i++) {
    		if(wordToGuess.charAt(i) == guess) {
				String beginning = hangmanWord.substring(0, i);
				String end = hangmanWord.substring(i+1);
				hangmanWord = beginning + guess + end;
			}
		}
    }
    
    /*
     * This message is displayed if the user loses
     */
    private void loserMessage() {
    	println("You are completely hung.");
    	println("The word was: " + wordToGuess);
    	println("You have lost.");
    }
    
    /*
     * These are the instance variables that are called in multiple methods
     */
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private String wordToGuess, hangmanWord, guessInitial;
    private int NGUESSES = 8; 
    private char guess;
    private HangmanLexicon wordList;
    private HangmanCanvas canvas;
    
}
    
