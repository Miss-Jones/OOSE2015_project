package Pacman;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {
	
	/**
	 * gamename is the name of the game
	 * menu is an int that saves the number of that state
	 * play is an int that saves the number of that state
	 */
	public static final String gamename = "Pacman";
	public static final int menu = 0;
	public static final int play = 1;
	
	/**
	 * Takes the gamename as input, and set the superclass gamename
	 * also add two states, 0 and 1.
	 * @param gamename the String: The name of the game
	 */
	public Game(String gamename){
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
	}
	
	/**
	 * Initiates the two states and enters the 1st state.
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc,this);
		this.getState(play).init(gc,this);
		this.enterState(menu);
		
	}
	
	/**
	 * The main, makes a new appcontainer, with the gamename
	 * Sets the display mode and starts the appclication
	 * @param args
	 */
	public static void main(String[] args) {
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(640,320,false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}

	}



}
