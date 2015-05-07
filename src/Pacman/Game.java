package Pacman;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {
	//
	/**
	 * gamename is the name of the game <br>
	 * menu is an int that saves the number of that state  <br>
	 * play is an int that saves the number of that state  <br>
	 * endwin is an int that saves the number of that state  <br>
	 * endlose is an int that saves the number of that state 
	 */
	public static final String gamename = "Pacman";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int endwin = 2;
	public static final int endlose = 3;
	
	/**
	 * Takes the gamename as input, and set the superclass gamename <br>
	 * also add states 0,1,2 and 3. <br>
	 * @param gamename the String: The name of the game
	 */
	public Game(String gamename){
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Endwin(endwin));
		this.addState(new Endlose(endlose));
	}
	
	/**
	 * Initiates the states and enters the 1st state.
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc,this);
		this.getState(play).init(gc,this);
		this.getState(endwin).init(gc,this);
		this.getState(endlose).init(gc,this);
		this.enterState(menu);
		
	}
	
	/**
	 * The main, makes a new appcontainer, with the gamename <br>
	 * Sets the display mode and starts the appclication <br>
	 * @param args
	 */
	public static void main(String[] args) {
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(1024,800,false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}

	}



}
