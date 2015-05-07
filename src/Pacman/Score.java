package Pacman;

public class Score {
	
	private static int theScore = 0;
	
	/**
	 * Score constructor
	 */
	public Score(){
	}

	/**
	 * Returns the getScore
	 * @return theScore the integer
	 */
	public static int getScore(){
		return theScore;
	}
	
	/**
	 * Sets the score 
	 * @param s the integer: takes an integer as input
	 */
	public static void setScore(int s){
		theScore = s;
	}
}
