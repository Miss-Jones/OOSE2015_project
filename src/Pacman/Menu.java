package Pacman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {

	Image pacman;
	Image playB;
	Image highscoreB;
	Image exitB;
	private Music startSound;
	/**
	 * 
	 * @param state the int: The state
	 */
	public Menu(int state){	
	}
	
	/**
	 * 
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{	
		pacman = new Image("data/fonts/pacman.png");
		playB = new Image("data/fonts/playB.png");
		highscoreB = new Image("data/fonts/highscoreB.png");
		exitB = new Image("data/fonts/exitgameB.png");
		sbg.enterState(0);
	}
	
	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException{
		pacman.draw(262,125); 
		playB.draw(361, 450); //Image size is 298x36
		highscoreB.draw(370, 550); //Image size is 282x36
		exitB.draw(369,650); //Image size is 288x36
	}
	
	/**
	 * 
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException{
		int posMouseX = Mouse.getX();
		int posMouseY = Mouse.getY();
		
		if((posMouseX>361 && posMouseX<659) && (posMouseY>314 && posMouseY<360)){
			playB = new Image("data/fonts/playW.png");
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
				startSound = new Music("data/sounds/pacmanStart.ogg");
				startSound.setVolume(0.5f);
				startSound.play();
			}	
		}
		else{
			playB = new Image("data/fonts/playB.png");
		}
		if((posMouseX>370 && posMouseX<652) && (posMouseY>214 && posMouseY<250)){
			highscoreB = new Image("data/fonts/highscoreW.png");
			if(Mouse.isButtonDown(0)){
				sbg.enterState(2);
			}
		}
		else{
			highscoreB = new Image("data/fonts/highscoreB.png");
		}
		
		if((posMouseX>369 && posMouseX<657) && (posMouseY>114 && posMouseY<150)){
			exitB = new Image("data/fonts/exitgameW.png");
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}
		else{
			exitB = new Image("data/fonts/exitgameB.png");
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
