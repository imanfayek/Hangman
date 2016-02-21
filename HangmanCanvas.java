/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		
		removeAll();
		drawScaffold();
		
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		
		
		double x = (getWidth() / 2) - BEAM_LENGTH;
		double y = (getHeight() / 4) + BODY_LENGTH + LEG_LENGTH + 50;
		GObject g = getElementAt(x, y);
		if(g != null) {
			remove(g);
		}
		wordToGuess = word;
		guessWord = new GLabel(wordToGuess, x, y);
		guessWord.setFont("SansSerif-35");
		add(guessWord);
		
		
								
	}
				
/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		
		double x = (getWidth() / 2) - BEAM_LENGTH;
		double y = (getHeight() / 4) + BODY_LENGTH + LEG_LENGTH + 75;
		wrong = wrong + letter; 
		GLabel incorrectGuesses = new GLabel(wrong, x, y);
		incorrectGuesses.setFont("SansSerif-15");
		add(incorrectGuesses);
		
		switch (wrong.length()) {
			case 1: drawHead(); break;
			case 2: drawBody(); break;
			case 3: drawLeftArm(); break;
			case 4: drawRightArm(); break;
			case 5: drawLeftLeg(); break;
			case 6: drawRightLeg(); break;
			case 7: drawLeftFoot(); break;
			case 8: drawRightFoot(); break;
			default: throw new ErrorException("");
		}
		
	}
	
	/*
	 * This draws the scaffold
	 */
	private void drawScaffold() {
		
		double rx = (getWidth() / 2);
		double ry = (getHeight() / 4) - (HEAD_RADIUS * 2) - ROPE_LENGTH;
		double ry2 = (getHeight() / 4) - (HEAD_RADIUS * 2);
		GLine rope = new GLine(rx, ry, rx, ry2);
		add(rope);
		
		double bx = (getWidth() / 2) - BEAM_LENGTH;
		double bx2 = rx;
		double by = ry;
		GLine beam = new GLine(bx, by, bx2, by);
		add(beam);
		
		double sx = bx;
		double sy = by;
		double sy2 = sy + SCAFFOLD_HEIGHT;
		GLine scaffold = new GLine(sx, sy, sx, sy2);
		add(scaffold);
		
	}
		
	/*
	 * This draws the head of the hangman
	 */
	private void drawHead() {
		
		double x = (getWidth() / 2) - HEAD_RADIUS;
		double y = (getHeight() / 4) - (HEAD_RADIUS * 2);
		GOval head = new GOval(x, y, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(head);
		
	}
	
	/*
	 * This draws the body of the hangman
	 */
	private void drawBody() {
		
		double x = getWidth() / 2;
		double y1 = getHeight() / 4;
		double y2 = y1 + BODY_LENGTH;
		GLine body = new GLine(x, y1, x, y2);
		add(body);
		
	}
	
	/*
	 * This draws the left arm of the hangman
	 */
	private void drawLeftArm() {
		
		double ux = (getWidth() / 2);
		double ux2 = ux - UPPER_ARM_LENGTH;
		double uy = (getHeight() / 4) + ARM_OFFSET_FROM_HEAD;
		GLine upper = new GLine(ux2, uy, ux, uy);
		add(upper);
		
		double lx = ux2;
		double ly = uy;
		double ly2 = uy + LOWER_ARM_LENGTH;
		GLine lower = new GLine(lx, ly, lx, ly2);
		add(lower);
	}
	
	/*
	 * This draws the right arm of the hangman
	 */
	private void drawRightArm() {
		
		double ux = (getWidth() / 2);
		double ux2 = ux + UPPER_ARM_LENGTH;
		double uy = (getHeight() / 4) + ARM_OFFSET_FROM_HEAD;
		GLine upper = new GLine( ux, uy, ux2, uy);
		add(upper);
		
		double lx = ux2;
		double ly = uy;
		double ly2 = uy + LOWER_ARM_LENGTH;
		GLine lower = new GLine(lx, ly, lx, ly2);
		add(lower);
		
	}
	
	/*
	 * This draws the left leg of the hangman
	 */
	private void drawLeftLeg() {
		
		double hx = (getWidth() / 2);
		double hx2 = hx - HIP_WIDTH;
		double hy = (getHeight() / 4) + BODY_LENGTH;
		GLine hip = new GLine(hx, hy, hx2, hy);
		add(hip);
		
		double lx = hx2;
		double ly = hy;
		double ly2 = ly + LEG_LENGTH;
		GLine leg = new GLine(lx, ly, lx, ly2);
		add(leg);
		
	}
	
	/*
	 * This draws the right leg of the hangman
	 */
	private void drawRightLeg() {
		
		double hx = (getWidth() / 2);
		double hx2 = hx + HIP_WIDTH;
		double hy = (getHeight() / 4) + BODY_LENGTH;
		GLine hip = new GLine(hx, hy, hx2, hy);
		add(hip);
		
		double lx = hx2;
		double ly = hy;
		double ly2 = ly + LEG_LENGTH;
		GLine leg = new GLine(lx, ly, lx, ly2);
		add(leg);
		
	}
	
	/*
	 * This draws the left foot of the hangman
	 */
	private void drawLeftFoot() {
		
		double fx = (getWidth() / 2) - HIP_WIDTH;
		double fx2 =  fx - FOOT_LENGTH;
		double fy = (getHeight() / 4) + BODY_LENGTH + LEG_LENGTH;
		GLine foot = new GLine(fx, fy, fx2, fy);
		add(foot);
		
	}
	
	/*
	 * This draws the right foot of the hangman
	 */
	private void drawRightFoot() {
		
		double fx = (getWidth() / 2) + HIP_WIDTH;
		double fx2 = fx + FOOT_LENGTH;
		double fy = (getHeight() / 4) + BODY_LENGTH + LEG_LENGTH;
		GLine foot = new GLine(fx, fy, fx2, fy);
		add(foot);
		
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private String wrong = "";
	private String wordToGuess = "";
	private GLabel guessWord;
}
