package Pacman;

public class Score {
	
	private static int theScore = 0;//sets the score at the start of creation
	
	/**
	 * Score constructor, nothing is needed
	 */
	public Score(){
	}

	/**
	 * Returns the Score
	 * @return theScore the integer
	 */
	public static int getScore(){
		return theScore;
	}
	
	/**
	 * Sets the Score 
	 * @param s the integer: takes an integer as input and save in score
	 */
	public static void setScore(int s){
		theScore = s;
	}
}
