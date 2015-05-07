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
 
        protected float posX;//The x position
        protected float posY;//The y position
        protected int lives;// number of lives
        protected Random randomGen;// a Random number generator
        protected double speedX, speedY;//the speed on the x and y axis
        protected int[] anidur = new int[3];//The animation duration
        protected Image[] imageUp = new Image[3];//The images for animation
        protected Image[] imageDown = new Image[3];//The images for animation
        protected Image[] imageLeft = new Image[3];//The images for animation
        protected Image[] imageRight = new Image[3];//The images for animation
        protected Animation sprite,up,down,left,right;//the different animations
        protected int maxPathLength = 100;//The maximum length of the path
        Path path;//The actual path
        AStarPathFinder pathFinder;//The way to find the path
        SimpleMap map;//A Simplemap, to convert to from TiledMap
        int mover;//Interger to keep track of steps taken
        protected int TargetposX;//The x position to go to
        protected int TargetposY;//The y position to go to
        protected Random rand;//Another random generator
       
        /**
    	 * Constructor where we initialize all the values
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
    	 * Move is an update that starts the movement of the entities
    	 * @param del the integer: The Delta time
    	 * @param height the integer: Height of the screen
    	 * @param width the integer: Width of the screen
    	 */
        public void Move(int del, int height, int width){
                this.posX += del*this.speedX; //move depending on the speed and delta time
                this.posY += del*this.speedY; //move depending on the speed and delta time
                //Check if the Entity is going off-screen
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
    	 * Controls the direction up, by setting the speed
    	 */
        public void MoveUp(){
                this.speedY = -0.15;
                this.speedX = 0;
        }
        
        /**
    	 * Controls the direction down, by setting the speed
    	 */
        public void MoveDown(){
                this.speedY = 0.15;
                this.speedX = 0;
        }
        
        /**
    	 * Controls the direction left, by setting the speed
    	 */
        public void MoveLeft(){
                this.speedX = -0.15;
                this.speedY = 0;
        }
        
        /**
    	 * Controls the direction right, by setting the speed
    	 */
        public void MoveRight(){
                this.speedX = 0.15;
                this.speedY = 0;
        }
        
        /**
    	 * Stops up and down movement, by setting the speed
    	 */
        public void StopUpDown(){
                this.speedY=0;
        }
        
        /**
    	 * Stops the left and right move, by setting the speed
    	 */
        public void StopLeftRight(){
                this.speedX=0;
        }
       
        /**
    	 * Returns GetPosX the float
    	 * @return this.posX the float
    	 */
        public float GetPosX(){
                return this.posX;
        }
       
        /**
    	 * Returns GetPosY the float
    	 * @return this.posY the float
    	 */
        public float GetPosY(){
                return this.posY;
        }
       
        /**
    	 * Returns GetSpeedX the double
    	 * @return this.speedX the double
    	 */
        public double getSpeedX(){
                return this.speedX;
        }
       
        /**
    	 * Returns GetSpeedY the double
    	 * @return this.speedY the double
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
                        this.up = new Animation(this.imageUp,this.anidur,false);//Set the animation depending on the images
                        this.up.setPingPong(true);//Go back and fourth between animations
                } catch (SlickException e) {
                        System.out.println("Could not initialize UP Animation images");//If it can't, print error messages
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
                        this.down = new Animation(this.imageDown,this.anidur,false);//Set the animation depending on the images
                        this.down.setPingPong(true);//Go back and fourth between animations
                } catch (SlickException e) {
                        System.out.println("Could not initialize DOWN Animation images");//If it can't, print error messages
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
                        this.left = new Animation(this.imageLeft,this.anidur,false);//Set the animation depending on the images
                        this.left.setPingPong(true);//Go back and fourth between animations
                } catch (SlickException e) {
                        // TODO Auto-generated catch block
                        System.out.println("Could not initialize LEFT Animation images");//If it can't, print error messages
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
                        this.right = new Animation(this.imageRight,this.anidur,false);//Set the animation depending on the images
                        this.right.setPingPong(true);//Go back and fourth between animations
                } catch (SlickException e) {
                        System.out.println("Could not initialize RIGHT Animation images");//If it can't, print error messages
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
    	 * Setting the collisionLayerId with the TiledMap so that the collision matches the Layer ID of the TiledMap
    	 * @param map the TiledMap: based on the TiledMap
    	 * @param collisionLayerId the integer: based on the collisionLayerId
    	 */
        public void SetTargetPos(TiledMap map, int collisionLayerId){
                this.TargetposY = (int)this.rand.nextInt(map.getHeight());//set target position y to a random interger within the height of the map
                this.TargetposX = (int)this.rand.nextInt(map.getWidth());//set target position x to a random interger within the width of the map
                //While these target positions are not on the path without walls, set new target positions
                while(map.getTileId(this.TargetposX, this.TargetposY, collisionLayerId)!=0||(this.posX/32==this.TargetposX&&this.posY/32==this.TargetposY)){
                        this.TargetposY = (int)this.rand.nextInt(map.getHeight());
                        this.TargetposX = (int)this.rand.nextInt(map.getWidth());
        }
       
}
        /**
    	 * Creating a path based on the free space between the walls of the TiledMap, based on the collisionLayerId 
    	 * @param map the Level: based on the level
    	 */
        public void SetPath(Level map){
                this.SetTargetPos(map.GetMap(),map.GetIndexLayer());//Sets the target position
        this.map = new SimpleMap(map.GetMap(), map.GetIndexLayer());//Convert the TiledMap to a SimpleMap
        this.pathFinder = new AStarPathFinder(this.map, this.maxPathLength, false);//Create a new pathfinder, based on the A* pathfinding model
        this.path = this.pathFinder.findPath(null, (int)this.GetPosX()/32, (int)this.GetPosY()/32, this.TargetposX, this.TargetposY);//Create a new path from the know position to the target position
        this.mover = 1;//Start at the first step in the path
        }
       
        /**
    	 * Making it possible to move on the path created from SetPath in the 4 directions(up,down,left,right)
    	 * @param map the Level: based on the level
    	 */
        public void movePath(Level map){
                if(this.path.getLength()>this.mover){//Go through every step of the path
                        if(this.path.getX(this.mover)>Math.round(this.GetPosX()/32*8)/8f){//If the path is to the right
                                this.MoveRight();//go right
                                this.SetSprite(this.GetAniRight());//set right animation
                        }else if(this.path.getX(this.mover)<Math.round(this.GetPosX()/32*8)/8f){//If the path is to the left
                                this.MoveLeft();//go left
                                this.SetSprite(this.GetAniLeft());//set left animation
                        }else if(this.path.getY(this.mover)>Math.round(this.GetPosY()/32*8)/8f){//If the path is down
                                this.MoveDown();//go down
                                this.SetSprite(this.GetAniDown());//set down animation
                        }else if(this.path.getY(this.mover)<Math.round(this.GetPosY()/32*8)/8f){//If the path is up
                                this.MoveUp();//go up
                                this.SetSprite(this.GetAniUp());//set up animation
                        }
                        //if we are at the end of the step
                        if(this.path.getX(this.mover)==Math.round(this.GetPosX()/32*8)/8f&&this.path.getY(this.mover)==Math.round(this.GetPosY()/32*8)/8f){
 
                                this.mover++; //go to the next step, and stop movement
                                this.StopLeftRight(); 
                                this.StopUpDown();
                        }
                }else{//if we are at the end of the path.
                        this.posX=this.path.getX(this.mover-1)*32;//Set the position to the target position
                        this.posY=this.path.getY(this.mover-1)*32;//this is to make sure we calculate from the right position again
                        this.SetPath(map);//set a new path
                }
        }
       
        /**
    	 * Creating a hitbox for the path, for moving around
    	 * @return temp the Shape[]: getting the hitbox
    	 */
        public Shape[] getPathHitbox(){
                Shape temp[] = new Rectangle[this.path.getLength()];//A rectangle array that is as long as the path 
                for(int i = 0; i < temp.length; i++) { //going through the path
                        temp[i] = new Rectangle(this.path.getX(i)*32,this.path.getY(i)*32,32,32);//making a new rectangle on every step of the path
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