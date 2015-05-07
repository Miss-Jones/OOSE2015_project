package Pacman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Endlose extends BasicGameState {

	Image endlose;
	Image playagainB;
	Image menuB;
	private Music startSound;
	Endlose(int state){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		// TODO Auto-generated method stub
		endlose = new Image("data/fonts/endgameLoser.png");
		playagainB = new Image("data/fonts/playagainB.png");//256x30
		menuB = new Image("data/fonts/menuB.png");//113x30
		//playagainW = new Image("data/fonts/playagainW.png");//113x30
		//menuW = new Image("data/fonts/menuW.png");//113x30
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		// TODO Auto-generated method stub
		endlose.draw(0,0);
		playagainB.draw(384,570);
		menuB.draw(456,625);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		// TODO Auto-generated method stub
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

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
