package Pacman;

public class Score {
	
	private static int theScore = 0;
	
	public Score(){
	}

	public static int getScore(){
		return theScore;
	}
	
	public static void setScore(int s){
		theScore = s;
	}
}
