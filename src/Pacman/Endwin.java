package Pacman;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class Endwin extends BasicGameState {

	Image endwin;
	Image playagainB;
	Image menuB;
	private Music startSound;
	TrueTypeFont font2;
	
	/**
	 * State when the game is over by collecting all the coins
	 * @param state the integer: the state
	 */
	Endwin(int state){	
	}
	
	/**
	 * Loads a custom font and sets its size <br>
	 * Opens the location for the pictures used
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		// TODO Auto-generated method stub
		try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("data/fonts/heavy_data.ttf");
             
            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(70f); // set font size
            font2 = new TrueTypeFont(awtFont2, false);
                 
        } catch (Exception e) {
            System.out.println("Could not create font!");
        }
		endwin = new Image("data/fonts/endgameWinner.png");
		playagainB = new Image("data/fonts/playagainB.png");//253x30
		menuB = new Image("data/fonts/menuB.png");//113x30
		
	}

	/**
	 * Used for drawing the size of the fonts <br>
	 * The score is also displayed 
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		// TODO Auto-generated method stub
		endwin.draw(0,0);
		playagainB.draw(386,570);
		menuB.draw(456,625);
		font2.drawString(435, 435, ""+Score.getScore(), Color.white);
		
	}

	/**
	 * Update is used for manoeuvring the mouse on the screen and clicking on the menu or play again, working like a button
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		int posMouseX = Mouse.getX();
		int posMouseY = Mouse.getY();
		if((posMouseX>384 && posMouseX<649) && (posMouseY>200 && posMouseY<230)){
			playagainB = new Image("data/fonts/playagainW.png");
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
				startSound = new Music("data/sounds/pacmanStart.ogg");
				startSound.setVolume(0.5f);
				startSound.play();
			}	
		}
		else{
			playagainB = new Image("data/fonts/playagainB.png");
	}
		if((posMouseX>456 && posMouseX<569) && (posMouseY>145 && posMouseY<175)){
			menuB = new Image("data/fonts/menuW.png");
			if(Mouse.isButtonDown(0)){
				sbg.enterState(0);
			}	
		}
		else{
			menuB = new Image("data/fonts/menuB.png");
		}
		
	}

	 /**
     * returns the integer getID <br>
     * This is an override function 
     */
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
