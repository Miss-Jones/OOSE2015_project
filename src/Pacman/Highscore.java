package Pacman;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Highscore extends BasicGameState {
	Image pacman2;
	Image highscores;
	Image first;
	Image second;
	Image third;
	Image back;
	/**
	 * 
	 * @param state the int: The state
	 */
	public Highscore(int state){	
	}
	
	/**
	 * 
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{	
		pacman2 = new Image("data/fonts/pacman2.png");
		highscores = new Image("data/fonts/highscores.png");
		first = new Image("data/fonts/first.png");
		second = new Image("data/fonts/second.png");
		third = new Image("data/fonts/third.png");
		back = new Image("data/fonts/backB.png");
	}
	
	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException{
		pacman2.draw(346,25); //Image size is 350x159 
		highscores.draw(317, 225); //Image size is 404x48
		first.draw(100, 350); //Image size is 272x36
		second.draw(100,475); //Image size is 315x36
		third.draw(100,600); //Image size is 267x36
		back.draw(455,720); //Image size is 132x36
	}
	
	/**
	 * 
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException{
		int posMouseX = Mouse.getX();
		int posMouseY = Mouse.getY();

		
		if((posMouseX>455 && posMouseX<587) && (posMouseY>44 && posMouseY<80)){
			back = new Image("data/fonts/backW.png");
			if(Mouse.isButtonDown(0)){
				sbg.enterState(0);
			}
		}
		else{
			back = new Image("data/fonts/backB.png");
		}
	}
	
	/**
	 * 
	 */
	@Override
	public int getID(){
		return 2;
	}
}
