package Pacman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {

	Image pacman;//pacman image
	Image playB;//play image
	Image exitB;//exit image
	private Music startSound;//the start sound
	
	/**
	 * Menu state
	 * @param state the int: The state
	 */
	public Menu(int state){	
	}
	
	/**
	 * Creates the path to the images for each instance of the GUI images
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//sets all the images
		pacman = new Image("data/fonts/pacman.png");
		playB = new Image("data/fonts/playB.png");
		exitB = new Image("data/fonts/exitgameB.png");
	}
	
	/**
	 * Used for drawing the GUI of the menu
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException{
		pacman.draw(262,125); 
		playB.draw(361, 450); //Image size is 298x36
		exitB.draw(370, 550); //Image size is 282x36
	}
	
	/**
	 * Takes the mouse position and enters either of the instances in the main menu when clicked. <br>
	 * Makes sounds in playmode and when exiting.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException{
		//get mouse position
		int posMouseX = Mouse.getX();
		int posMouseY = Mouse.getY();
		
		if((posMouseX>361 && posMouseX<659) && (posMouseY>314 && posMouseY<360)){//if mouse is within play image
			playB = new Image("data/fonts/playW.png");//change the image to the white
			if(Mouse.isButtonDown(0)){//if the button is clicked
				sbg.enterState(1);//goto play
				//and play all the musics
				startSound = new Music("data/sounds/pacmanStart.ogg");
				startSound.setVolume(0.5f);
				startSound.play();
			}	
		}
		else{//if mouse is moved away
			playB = new Image("data/fonts/playB.png");//change image back
		}
		if((posMouseX>370 && posMouseX<652) && (posMouseY>214 && posMouseY<250)){//if mouse is within exit image
			exitB = new Image("data/fonts/exitgameW.png");//change the image to the white
			if(Mouse.isButtonDown(0)){//if the button is clicked
				System.exit(0);//terminate
			}
		}
		else{//if mouse is moved away
			exitB = new Image("data/fonts/exitgameB.png");//change image back
		}
	}
	
	/**
	 * returns the integer getID <br>
     * This is an override function 
	 */
	@Override
	public int getID(){
		return 0;
	}
}
