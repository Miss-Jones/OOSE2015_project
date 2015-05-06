package Pacman;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

public class Play extends BasicGameState {
	private TiledMap map;
	private Image coin;
	private boolean[][] coinPlace= new boolean[1000][1000];
	private Music endSound;
	Image score1;
	Image score2;
	Image lives1;
	Image lives2;

	/**
	 * 
	 * @param state the int: The state
	 */
	public Play(int state){	
	}
	
	/**
	 * 
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{	
		map = new TiledMap("data/map/map.tmx");
		score1 = new Image("data/fonts/pacmanScore1.png");
		score2 = new Image("data/fonts/pacmanScore2.png");
		lives1 = new Image("data/fonts/pacmanLives1.png");
		lives2 = new Image("data/fonts/pacmanLives2.png");
		int coinLayer = map.getLayerIndex("coinPath");
		for(int i = 0; i < map.getHeight(); i++)
		{
			for(int j = 0; j < map.getWidth(); j++)
			{
				if(map.getTileId(j, i, coinLayer)!=0)
				{
					coinPlace[i][j] = true;	
				}else{
					coinPlace[i][j] = false;
				}
			}
		}
	}
	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException{
		map.render(0, 0);
		score1.draw(810,400);
		score2.draw(825,350);
		lives1.draw(100,400);
		lives2.draw(70,350);
		coin = new Image("data/map/coin.png");
		
		for(int i = 0; i < coinPlace[0].length; i++)
		{
			for(int j = 0; j < coinPlace[1].length ; j++)
			{
				if(coinPlace[i][j]){
					coin.draw(j*32,i*32);
				}
			}
		}	
	}
	
	/**
	 * 
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException{
		if(Keyboard.isKeyDown(Input.KEY_ESCAPE)){
			sbg.enterState(0);
			endSound = new Music("data/sounds/pacmanDie.ogg");
			endSound.setVolume(0.5f);
			endSound.play();
		}
		
	}
	
	/**
	 * 
	 */
	@Override
	public int getID(){
		return 1;
	}
}
