package Pacman;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class Level {
	
	protected TiledMap map;//The map of the level
	protected String mapName;//name of that map
	protected Shape[][] hitbox = new Rectangle[1024][800];//make a shape array with the size of the screen.
	protected int hitboxLayer;//The layer to create the hitbox on
	protected boolean[][] blocked = new boolean[100][100];//is there a hitbox on this layer?
	protected Sound sound;//Level sound
	
	/**
	 * Level constructor 
	 */
	Level(){
	}

	/**
     * Takes this.map and sets it to a new TiledMap with this.mapName
     * @param m the String: changes this.mapName
     */
	public void SetMap(String m){
		this.mapName = m;
		try {
			this.map = new TiledMap(this.mapName);//load the map
			
		} catch (SlickException e) {
			System.out.println("Could not initiate Tiled Map");//If it can't load the map, print an error.
		}
	}
	
	/**
	 * Used for the sound on the map
	 * @param m the String: changes this.mapName
	 * @param soundlocation the String: takes a string as argument for the sound location
	 */
	public void SetMap(String m, String soundlocation){
		this.mapName = m;//load the map
		try {
			this.sound = new Sound(soundlocation);//load the sound
		} catch (SlickException e1) {
			System.out.println("Could not initialize level sound!");//If it can't load the sound, print an error.
		}
		try {
			this.map = new TiledMap(this.mapName);//load the map
			
		} catch (SlickException e) {
			System.out.println("Could not initiate Tiled Map");//If it can't load the map, print an error.
		}
	}
	
	/**
     * Returns the TiledMap this.map
     * @return this.map the TiledMap
     */
	public TiledMap GetMap(){
		return this.map;
	}
	
	/**
     * Returns the String GetMapName
     * @return this.mapName the String
     */
	public String GetMapName(){
		return this.mapName;
	}
	
	/**
	 * Used for playing the sound
	 */
	public void playSound(){
		if(!this.sound.playing()){//if the sound is not playing
		this.sound.play();//play the sound
		}
	}
	
	/**
	 * Used for stopping the sound
	 */
	public void stopSound(){
		this.sound.stop();
	}
	
	/**
     * Sets the hitbox on the layer of that map
     * @param h the String:
     */
	public void SetHitBox(String h){
		this.hitboxLayer = this.map.getLayerIndex(h);//get the layer from h
		for(int x = 0; x<this.map.getWidth();x++){//loop through the map
			for(int y = 0; y<this.map.getHeight();y++){
				if(this.map.getTileId(x, y, this.hitboxLayer)!=0){//if there is something on that tile
					this.hitbox[x][y] = new Rectangle(x*32,y*32,32,32);//set the hitbox
					this.blocked[x][y]= true;//set the blocked to true
				}else{
					this.hitbox[x][y] = new Rectangle(0,0,0,0);
				}
			}
		}
	}
	
	/**
     * Returns the int GetIndexLayer
     * @return this.hitboxLayer the integer
     */
	public int GetIndexLayer(){
		return this.hitboxLayer; 
	}
	
	/**
     * Returns the Shape array GetHitbox
     * @return this.hitbox the Shape[]
     */
	public Shape[][] GetHitbox(){
		return this.hitbox;
	}
	
	/**
     * Returns the 2d boolean array GetBlocked 
     * @return this.blcoked the boolean
     */
	public boolean[][] GetBlocked(){
		return this.blocked;
	}
	
	/**
	 * Sets up the block based on x-y coordinates
	 * @param x the integer: the x position
	 * @param y the integer: the y position
	 * @param b the boolean: true or false
	 */
	public void SetBlocked(int x, int y, boolean b){
		this.blocked[x][y] = b;
	}
	
	 /**
     * Returns the integer GetHeight
     * @return this.map.getHeight the int
     */
	public int GetHeight(){
		return this.map.getHeight();
	}	
	
	 /**
     * Returns the integer GetWidth
     * @return this.map.getWidth the int
     */
	public int GetWidth(){
		return this.map.getWidth();
	}
	
}
