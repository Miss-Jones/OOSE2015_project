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
       
        /**
    	 * Constructor where we initialise all the values
    	 */
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
       
        /**
    	 * returns the Shape getHitBox, which has the position x,y and a box of 32*32 pixels
    	 * @return new Rectangle
    	 */
        public Shape getHitBox(){
                return new Rectangle((int)this.GetPosX(),(int)this.GetPosY(),32,32);
        }
       
        /**
    	 * Move is an update that moves the characters (and controls how much?)-------
    	 * @param del the integer:
    	 * @param height the integer:
    	 * @param width the integer:
    	 */
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
       
        /**
    	 * Controls the direction up
    	 */
        public void MoveUp(){
                this.speedY = -0.15;
                this.speedX = 0;
        }
        
        /**
    	 * Controls the direction down
    	 */
        public void MoveDown(){
                this.speedY = 0.15;
                this.speedX = 0;
        }
        
        /**
    	 * Controls the direction left
    	 */
        public void MoveLeft(){
                this.speedX = -0.15;
                this.speedY = 0;
        }
        
        /**
    	 * Controls the direction right
    	 */
        public void MoveRight(){
                this.speedX = 0.15;
                this.speedY = 0;
        }
        
        /**
    	 * When the character has moved and stops, stops
    	 */
        public void StopUpDown(){
                this.speedY=0;
        }
        
        /**
    	 * When the character has moved and stops, stops
    	 */
        public void StopLeftRight(){
                this.speedX=0;
        }
       
        /**
    	 * Returns the float GetPosX
    	 * @return this.posX the float
    	 */
        public float GetPosX(){
                return this.posX;
        }
       
        /**
    	 * Returns the float GetPosY
    	 * @return this.posY the float
    	 */
        public float GetPosY(){
                return this.posY;
        }
       
        /**
    	 * Returns the float GetSpeedX
    	 * @return this.speedX the float
    	 */
        public double getSpeedX(){
                return this.speedX;
        }
       
        /**
    	 * Returns the float GetSpeedY
    	 * @return this.speedY the float
    	 */
        public double getSpeedY(){
                return this.speedY;
        }
       
        /**
    	 * Checks if the character is dead by the amount of lives there is left
    	 * @return true if this.lives<0, else false
    	 */
        public boolean DEAD(){
                if(this.lives<1){
                        return true;
                }else{
                        return false;
                }
        }
       
        /**
    	 * Used for setting the animation of the character when moving upwards
    	 * @param first the String: location for the first picture
    	 * @param second the String: location for the second picture
    	 * @param third the String: location for the third picture
    	 */
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
       
        /**
    	 * Used for getting the upwards animation
    	 * @return this.up the Animation
    	 */
        public Animation GetAniUp(){
                return this.up;
        }
       
        /**
    	 * Used for setting the animation of the character when moving downwards
    	 * @param first the String: location for the first picture
    	 * @param second the String: location for the second picture
    	 * @param third the String: location for the third picture
    	 */
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
       
        /**
    	 * Used for getting the downwards animation
    	 * @return this.down the Animation
    	 */
        public Animation GetAniDown(){
                return this.down;
        }
       
        /**
    	 * Used for setting the animation of the character when moving left
    	 * @param first the String: location for the first picture
    	 * @param second the String: location for the second picture
    	 * @param third the String: location for the third picture
    	 */
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
       
        /**
    	 * Used for getting the left moving animation
    	 * @return this.down the Animation
    	 */
        public Animation GetAniLeft(){
                return this.left;
        }
       
        /**
    	 * Used for setting the animation of the character when moving right
    	 * @param first the String: location for the first picture
    	 * @param second the String: location for the second picture
    	 * @param third the String: location for the third picture
    	 */
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
       
        /**
    	 * Used for getting the right moving animation
    	 * @return this.down the Animation
    	 */
        public Animation GetAniRight(){
                return this.right;
        }
       
        /**
    	 * Used to set the animation of the sprite
    	 * @param s the Animation: this.sprite is set to s
    	 */
        public void SetSprite(Animation s){
                this.sprite = s;
        }
       
        /**
    	 * Used to get the animation of the sprite
    	 * @return this.sprite the Animation
    	 */
        public Animation GetSprite(){
                return this.sprite;
        }
       
        /**
    	 * Using an array to retrieve the duration of the animation
    	 * @return this.anidur the integer array
    	 */
        public int[] GetAnimationDuration(){
                return this.anidur;
        }
       
        /**
    	 * Set the duration of the animation in the integer array
    	 * @param a the integer array: The animation duration should only hold 3 values
    	 */
        public void SetAnimationDuration(int[] a){
                if(a.length>3||a.length<3){
                        System.out.println("ERROR: Animation Duration has to have 3 values only!");
                }else{
                        this.anidur = a;
                }
        }
       
        /**
    	 * Setting the collisionLayerId with the TiledMap so that the collision matches the walls
    	 * @param map the TiledMap: based on the TiledMap
    	 * @param collisionLayerId the integer: based on the collisionLayerId
    	 */
        public void SetTargetPos(TiledMap map, int collisionLayerId){
                this.TargetposY = (int)this.rand.nextInt(map.getHeight());
                this.TargetposX = (int)this.rand.nextInt(map.getWidth());
                while(map.getTileId(this.TargetposX, this.TargetposY, collisionLayerId)!=0||(this.posX/32==this.TargetposX&&this.posY/32==this.TargetposY)){
                        this.TargetposY = (int)this.rand.nextInt(map.getHeight());
                        this.TargetposX = (int)this.rand.nextInt(map.getWidth());
        }
       
}
        /**
    	 * Creating a path based on the free space between the walls of the TiledMap and collisionLayerId 
    	 * @param map the Level: based on the level
    	 */
        public void SetPath(Level map){
                this.SetTargetPos(map.GetMap(),map.GetIndexLayer());
        this.map = new SimpleMap(map.GetMap(), map.GetIndexLayer());
        this.pathFinder = new AStarPathFinder(this.map, this.maxPathLength, false);
        this.path = this.pathFinder.findPath(null, (int)this.GetPosX()/32, (int)this.GetPosY()/32, this.TargetposX, this.TargetposY);
        this.mover = 1;
        }
       
        /**
    	 * Making it possible to move on the path created from SetPath in the 4 directions(up,down,left,right)
    	 * @param map the Level: based on the level
    	 */
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
       
        /**
    	 * Creating a hitbox for the path for moving around and for setting the coins
    	 * @return temp the Shape[]: not temperary just used for naming
    	 */
        public Shape[] getPathHitbox(){
                Shape temp[] = new Rectangle[this.path.getLength()];
                for(int i = 0; i < temp.length; i++) { 
                        temp[i] = new Rectangle(this.path.getX(i)*32,this.path.getY(i)*32,32,32);
        }
        return temp;
        }
        
        /**
    	 * Getting the length of the path
    	 * @return this.path.getLength the integer
    	 */
        public int getPathLenght(){
                return this.path.getLength();
        }
}