package Pacman;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
 
 
public class Player extends Entity {
       
        protected String name;//name of player
        protected int score;
        protected int coinsEaten;
        protected int MAXcoins;//the coins in the level
        protected int[] diedur = new int[12];//death animation duration
        protected Image[] imageDie = new Image[12];//death animation images
        protected Animation die;//the death animation
        Sound diesound;//the death sound
 
        /**
         * 
         * @param x the int: x coordinate of player
         * @param y the int: y coordinate of player
         * @param l the int: lives of the player
         * @param n the String: the name of the player
         */
        Player(int x, int y, int l, String n){
                this.posX = x;
                this.posY = y;
                this.name = n;
                this.MAXcoins = 328;//set max coins to 328. NEEDS TO BE CHANGED ON LEVEL CHANGE
                try {
                        this.diesound = new Sound("data/sounds/pacmanDie.ogg");//initiates the die sound
                } catch (SlickException e) {
                        System.out.println("Could not initiate Die sound!");//if the sound can't be initiated, print
                }
                if(l<0){//if the lives set for the player is below 0.
                        this.lives = 3;//set the lives to 3
                }else{
                        this.lives = l;//else set it to l
                }              
        }
            
        /**
         * gets the name of the player using a string
         * @return this.name the String
         */
        public String GetName(){
                return this.name;
        }
       
        /**
         * Sets the score
         * @param s the integer:
         */
        public void SetScore(int s){
                this.score += s;
        }
       
        /**
         * gets the score
         * @return this.score the integer
         */
        public int GetScore(){
                return this.score;
        }
       /**
        * set the coins eaten
        * @param x the integer: coins eaten
        */
        public void SetCoinsEaten(int x){
                this.coinsEaten+=x;
        }
       /**
        * gets the coins eaten
        * @return this.coinseaten the int
        */
        public int GetCoinsEaten(){
                return this.coinsEaten;
        }
       /**
        * Gets the max coins of the level
        * @return this.MAX.coins the int
        */
        public int getMAXcoins(){
                return this.MAXcoins;
        }
       /**
        * get the lives
        * @return this.lives the integer
        */
        public int getLives(){
                return this.lives;
        }
        /**
         * sets the lives
         * @param l the integer: lives
         */
        public void setLives(int l){
        	this.lives = l;
        }
       /**
        * Checks if coins eaten is equal or above the max coins of the level
        * @return true if coins eaten is more than max coins, else false 
        */
        public boolean WIN(){
                if(this.coinsEaten >= this.MAXcoins){
                        return true;
                }else{
                return false;
                }
        }
       /**
        * sets the die animation, based in the location of the images
        * @param img1 the String: First image
        * @param img2 the String: Second image
        * @param img3 the String: Third image
        * @param img4 the String: Fourth image
        * @param img5 the String: Fifth image
        * @param img6 the String: Sixth image
        * @param img7 the String: Seventh image
        * @param img8 the String: Eighth image
        * @param img9 the String: Ninth image
        * @param img10 the String: Tenth image
        * @param img11 the String: Eleventh image
        * @param img12 the String: Twelfth image
        * @param dur the integer: Duration in miliseconds
        */
        public void SetDieAni(String img1, String img2, String img3,String img4, String img5, String img6,String img7, String img8, String img9,String img10, String img11, String img12, int dur){
                try {
                        for(int i = 0; i<this.diedur.length; i++){//sets the die animation duration to dur in all 12
                                diedur[i] = dur;
                        }
                        //sets all the images
                        this.imageDie[0] = new Image(img1);
                        this.imageDie[1] = new Image(img2);
                        this.imageDie[2] = new Image(img3);
                        this.imageDie[3] = new Image(img4);
                        this.imageDie[4] = new Image(img5);
                        this.imageDie[5] = new Image(img6);
                        this.imageDie[6] = new Image(img7);
                        this.imageDie[7] = new Image(img8);
                        this.imageDie[8] = new Image(img9);
                        this.imageDie[9] = new Image(img10);
                        this.imageDie[10] = new Image(img11);
                        this.imageDie[11] = new Image(img12);
                        this.die = new Animation(this.imageDie,this.diedur,false);//create the animation
                } catch (SlickException e) {
                        System.out.println("Could not initialize DIE Animation images");//if it can't, print
                }
        }
       /**
        * gets the die animation
        * @return this.die the Animation
        */
        public Animation getDieAni(){
                return this.die;
        }
       /**
        * If the Player dies, remove a live and play the die sound
        */
        public void die(){
                this.lives--;
                this.diesound.play();
        }
       
       /**
        * Moves the path which is calculated <br>
        * this is an override method
        */
        @Override
        public void movePath(Level map){
                if(this.path.getLength()>this.mover){//get every step of the path
                        if(this.path.getX(this.mover)>Math.round(this.GetPosX()/32*8)/8f){//if the next step is right
                                this.MoveRight();//move right
                                
                        }else if(this.path.getX(this.mover)<Math.round(this.GetPosX()/32*8)/8f){//if the next step is left
                                this.MoveLeft();//move left
                             
                        }else if(this.path.getY(this.mover)>Math.round(this.GetPosY()/32*8)/8f){//if the next step is down
                                this.MoveDown();//move down
                               
                        }else if(this.path.getY(this.mover)<Math.round(this.GetPosY()/32*8)/8f){//if the next step is up
                                this.MoveUp();//move up
                                
                        }
                        //if we are at this step
                        if(this.path.getX(this.mover)==Math.round(this.GetPosX()/32*8)/8f&&this.path.getY(this.mover)==Math.round(this.GetPosY()/32*8)/8f){
 
                                this.mover++;//goto next step
                                this.StopLeftRight();//stop left and right movement
                                this.StopUpDown();//stop up and down movement
                        }
                }else{//if we are at the end of the path
                        this.posX=this.path.getX(this.mover-1)*32;//reset position
                        this.posY=this.path.getY(this.mover-1)*32;//reset position
                }
        }
       /**
        * Sets the target position<br>
        * this is an override method
        */
        @Override
        public void SetTargetPos(TiledMap map, int collisionLayerId){
                this.TargetposY = 14;
                this.TargetposX = 16;
       
}
        /**
         * Sets the path<br>
         * this is an override method
         */
        @Override
        public void SetPath(Level map){
                this.SetTargetPos(map.GetMap(),map.GetIndexLayer());
        this.map = new SimpleMap(map.GetMap(), map.GetIndexLayer());
        this.pathFinder = new AStarPathFinder(this.map, this.maxPathLength, false);
        this.path = this.pathFinder.findPath(null, (int)this.GetPosX()/32, (int)this.GetPosY()/32, this.TargetposX, this.TargetposY);
        this.mover = 1;
        }
       /**
        * Put one step to the right of the end of the path
        * @param map the TiledMap: input the current map
        * @param moveableLayerId the integer: what layer to move on
        */
        public void GoStepRight(TiledMap map, int moveableLayerId){
                if(map.getTileId((int)(this.posX/32)+1, (int)this.posY/32, moveableLayerId)!=0){//if the tile is a tile that can be moved to
                this.path.appendStep((int)(this.posX/32)+1, (int)this.posY/32);//append a step onto the path
                }
        }
        /**
         * Put one step to the left of the end of the path
         * @param map the TiledMap: input the current map
         * @param moveableLayerId the integer: what layer to move on
         */
        public void GoStepLeft(TiledMap map, int moveableLayerId){
                if(map.getTileId((int)(this.posX/32)-1, (int)this.posY/32, moveableLayerId)!=0){//if the tile is a tile that can be moved to
                this.path.appendStep((int)(this.posX/32)-1, (int)this.posY/32);//append a step onto the path
                }
        }
        /**
         * Put one step up at the end of the path
         * @param map the TiledMap: input the current map
         * @param moveableLayerId the integer: what layer to move on
         */
        public void GoStepUp(TiledMap map, int moveableLayerId){
                if(map.getTileId((int)this.posX/32, (int)(this.posY/32)-1, moveableLayerId)!=0){//if the tile is a tile that can be moved to
                this.path.appendStep((int)(this.posX/32), (int)(this.posY/32)-1);//append a step onto the path
                }
        }
        /**
         * Put one step down at the end of the path
         * @param map the TiledMap: input the current map
         * @param moveableLayerId the integer: what layer to move on
         */
        public void GoStepDown(TiledMap map, int moveableLayerId){
                if(map.getTileId((int)this.posX/32, (int)(this.posY/32)+1, moveableLayerId)!=0){//if the tile is a tile that can be moved to
                this.path.appendStep((int)(this.posX/32), (int)(this.posY/32)+1);//append a step onto the path
                }
        }
}