package Pacman;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;


public class Entity {

	protected float posX;
	protected float posY;
	protected int lives;
	protected Random randomGen;
	protected double speedX, speedY;
	protected int[] anidur = new int[3];
	protected Image[] imageUp = new Image[3];
	protected Image[] imageDown = new Image[3];
	protected Image[] imageLeft = new Image[3];
	protected Image[] imageRight = new Image[3];
	protected Animation sprite,up,down,left,right;
	protected int maxPathLength = 100;
    Path path;
    AStarPathFinder pathFinder;
    SimpleMap map;
    int mover;
	protected int TargetposX;
	protected int TargetposY;
	protected Random rand;
	
	Entity(){
		this.posX = 0;
		this.posY = 0;
		this.lives = 0;
		this.randomGen = new Random();
		this.speedX = 0;
		this.speedY = 0;
		this.rand = new Random();
		for(int i = 0; i<3; i++){
			this.anidur[i] = 200;
		}
	}
	
	public Shape getHitBox(){
		return new Rectangle((int)this.GetPosX(),(int)this.GetPosY(),32,32);
	}
	
	public void Move(int del, int height, int width){
		this.posX += del*this.speedX;
		this.posY += del*this.speedY;
		if(this.posX>width+31){
			this.posX = -30;
		}
		if(this.posX<-31){
			this.posX=width+30;
		}
		if(this.posY>height+31){
			this.posY=-30;
		}
		if(this.posY<-31){
			this.posY=height+30;
		}
	}
	
	public void MoveUp(){
		this.speedY = -0.15;
		this.speedX = 0;
	}
	public void MoveDown(){
		this.speedY = 0.15;
		this.speedX = 0;
	}
	public void MoveLeft(){
		this.speedX = -0.15;
		this.speedY = 0;
	}
	public void MoveRight(){
		this.speedX = 0.15;
		this.speedY = 0;
	}
	public void StopUpDown(){
		this.speedY=0;
	}
	public void StopLeftRight(){
		this.speedX=0;
	}
	
	public float GetPosX(){
		return this.posX;
	}
	
	public float GetPosY(){
		return this.posY;
	}
	
	public double getSpeedX(){
		return this.speedX;
	}
	
	public double getSpeedY(){
		return this.speedY;
	}
	
	public boolean Dead(){
		if(this.lives<0){
			return true;
		}else{
			return false;
		}
	}
	
	public void SetAniUp(String first, String second, String third){
		try {
			this.imageUp[0] = new Image(first);
			this.imageUp[1] = new Image(second);
			this.imageUp[2] = new Image(third);
			this.up = new Animation(this.imageUp,this.anidur,false);
			this.up.setPingPong(true);
		} catch (SlickException e) {
			System.out.println("Could not initialize UP Animation images");
		}
	}
	
	public Animation GetAniUp(){
		return this.up;
	}
	
	public void SetAniDown(String first, String second, String third){
		try {
			this.imageDown[0] = new Image(first);
			this.imageDown[1] = new Image(second);
			this.imageDown[2] = new Image(third);
			this.down = new Animation(this.imageDown,this.anidur,false);
			this.down.setPingPong(true);
		} catch (SlickException e) {
			System.out.println("Could not initialize DOWN Animation images");
		}
	}
	
	public Animation GetAniDown(){
		return this.down;
	}
	
	public void SetAniLeft(String first, String second, String third){
		try {
			this.imageLeft[0] = new Image(first);
			this.imageLeft[1] = new Image(second);
			this.imageLeft[2] = new Image(third);
			this.left = new Animation(this.imageLeft,this.anidur,false);
			this.left.setPingPong(true);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not initialize LEFT Animation images");
		}
	}
	
	public Animation GetAniLeft(){
		return this.left;
	}
	
	public void SetAniRight(String first, String second, String third){
		try {
			this.imageRight[0] = new Image(first);
			this.imageRight[1] = new Image(second);
			this.imageRight[2] = new Image(third);
			this.right = new Animation(this.imageRight,this.anidur,false);
			this.right.setPingPong(true);
		} catch (SlickException e) {
			System.out.println("Could not initialize RIGHT Animation images");
		}
	}
	
	public Animation GetAniRight(){
		return this.right;
	}
	
	public void SetSprite(Animation s){
		this.sprite = s;
	}
	
	public Animation GetSprite(){
		return this.sprite;
	}
	
	public int[] GetAnimationDuration(){
		return this.anidur;
	}
	
	public void SetAnimationDuration(int[] a){
		if(a.length>3||a.length<3){
			System.out.println("ERROR: Animation Duration has to have 3 values only!");
		}else{
			this.anidur = a;
		}
	}
	
	public void SetTargetPos(TiledMap map, int collisionLayerId){
		this.TargetposY = (int)this.rand.nextInt(map.getHeight());
		this.TargetposX = (int)this.rand.nextInt(map.getWidth());
		while(map.getTileId(this.TargetposX, this.TargetposY, collisionLayerId)!=0||(this.posX/32==this.TargetposX&&this.posY/32==this.TargetposY)){
			this.TargetposY = (int)this.rand.nextInt(map.getHeight());
			this.TargetposX = (int)this.rand.nextInt(map.getWidth());
	}
	
}
	public void SetPath(Level map){
		this.SetTargetPos(map.GetMap(),map.GetIndexLayer());
        this.map = new SimpleMap(map.GetMap(), map.GetIndexLayer());
        this.pathFinder = new AStarPathFinder(this.map, this.maxPathLength, false);
        this.path = this.pathFinder.findPath(null, (int)this.GetPosX()/32, (int)this.GetPosY()/32, this.TargetposX, this.TargetposY);
        this.mover = 1;
	}
	
	public void movePath(Level map){
		if(this.path.getLength()>this.mover){
			if(this.path.getX(this.mover)>Math.round(this.GetPosX()/32*8)/8f){
				this.MoveRight();
				this.SetSprite(this.GetAniRight());
			}else if(this.path.getX(this.mover)<Math.round(this.GetPosX()/32*8)/8f){
				this.MoveLeft();
				this.SetSprite(this.GetAniLeft());
			}else if(this.path.getY(this.mover)>Math.round(this.GetPosY()/32*8)/8f){
				this.MoveDown();
				this.SetSprite(this.GetAniDown());
			}else if(this.path.getY(this.mover)<Math.round(this.GetPosY()/32*8)/8f){
				this.MoveUp();
				this.SetSprite(this.GetAniUp());
			}
			if(this.path.getX(this.mover)==Math.round(this.GetPosX()/32*8)/8f&&this.path.getY(this.mover)==Math.round(this.GetPosY()/32*8)/8f){

				this.mover++;
				this.StopLeftRight();
				this.StopUpDown();
			}
		}else{
			this.posX=this.path.getX(this.mover-1)*32;
			this.posY=this.path.getY(this.mover-1)*32;
			this.SetPath(map);
		}
	}
	
	public Shape[] getPathHitbox(){
		Shape temp[] = new Rectangle[this.path.getLength()];
for(int i = 0; i < temp.length; i++) {	
			temp[i] = new Rectangle(this.path.getX(i)*32,this.path.getY(i)*32,32,32);
        }
	return temp;
	}
	
	public int getPathLenght(){
		return this.path.getLength();
	}
}
