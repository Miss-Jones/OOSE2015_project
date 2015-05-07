package Pacman;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class Level {
	
	protected TiledMap map;
	protected String mapName;
	protected Shape[][] hitbox = new Rectangle[1024][800];
	protected int count;
	protected int hitboxLayer;
	protected boolean[][] blocked = new boolean[100][100];
	protected Sound sound;
	
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
			this.map = new TiledMap(this.mapName);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not initiate Tiled Map");
		}
	}
	
	/**
	 * Used for the sound on the map
	 * @param m the String: 
	 * @param soundlocation the String: takes a string as argument for the sound location
	 */
	public void SetMap(String m, String soundlocation){
		this.mapName = m;
		try {
			this.sound = new Sound(soundlocation);
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			System.out.println("Could not initialize level sound!");
		}
		try {
			this.map = new TiledMap(this.mapName);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not initiate Tiled Map");
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
	 * @return this.sound.play
	 */
	public void playSound(){
		if(!this.sound.playing()){
		this.sound.play();
		}
	}
	
	/**
	 * Used for stopping the sound
	 * @return this.sound.stop
	 */
	public void stopSound(){
		this.sound.stop();
	}
	
	/**
     * Sets the hitbox and checks if the hitbox is hitting the walls. If so it should not be able to move through
     * @param h the String:
     */
	public void SetHitBox(String h){
		this.count = 0;
		this.hitboxLayer = this.map.getLayerIndex(h);
		for(int x = 0; x<this.map.getWidth();x++){
			for(int y = 0; y<this.map.getHeight();y++){
				if(this.map.getTileId(x, y, this.hitboxLayer)!=0){
					this.hitbox[x][y] = new Rectangle(x*32,y*32,32,32);
					this.count++;
					this.blocked[x][y]= true;
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
	 * @param x the integer:
	 * @param y the integer:
	 * @param b the boolean:
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
