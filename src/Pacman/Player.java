package Pacman;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
 
 
public class Player extends Entity {
       
        protected String name;
        protected int score;
        protected int coinsEaten;
        protected int MAXcoins;
        protected int[] diedur = new int[12];
        protected Image[] imageDie = new Image[12];
        protected Animation die;
        Sound diesound;
 
 
        Player(int x, int y, int l, String n){
                this.posX = x;
                this.posY = y;
                this.name = n;
                this.MAXcoins = 328;
                try {
                        this.diesound = new Sound("data/sounds/pacmanDie.ogg");
                } catch (SlickException e) {
                        // TODO Auto-generated catch block
                        System.out.println("Could not initiate Die sound!");
                }
                if(l<0){
                        this.lives = 3;
                }else{
                        this.lives = l;
                }              
        }
               
        public String GetName(){
                return this.name;
        }
       
        public void SetScore(int s){
                this.score += s;
        }
       
        public int GetScore(){
                return this.score;
        }
       
        public void SetCoinsEaten(int x){
                this.coinsEaten+=x;
        }
       
        public int GetCoinsEaten(){
                return this.coinsEaten;
        }
       
        public int getMAXcoins(){
                return this.MAXcoins;
        }
       
        public int getLives(){
                return this.lives;
        }
        
        public void setLives(int l){
        	this.lives = l;
        }
       
        public boolean WIN(){
                if(this.coinsEaten >= this.MAXcoins){
                        return true;
                }else{
                return false;
                }
        }
       
        public void SetDieAni(String img1, String img2, String img3,String img4, String img5, String img6,String img7, String img8, String img9,String img10, String img11, String img12, int dur){
                try {
                        for(int i = 0; i<this.diedur.length; i++){
                                diedur[i] = dur;
                        }
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
                        this.die = new Animation(this.imageDie,this.diedur,false);
                } catch (SlickException e) {
                        System.out.println("Could not initialize DIE Animation images");
                }
        }
       
        public Animation getDieAni(){
                return this.die;
        }
       
        public void die(){
                this.lives--;
                this.diesound.play();
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
                }
        }
       
        public void SetTargetPos(TiledMap map, int collisionLayerId){
                this.TargetposY = 14;
                this.TargetposX = 16;
       
}
        public void SetPath(Level map){
                this.SetTargetPos(map.GetMap(),map.GetIndexLayer());
        this.map = new SimpleMap(map.GetMap(), map.GetIndexLayer());
        this.pathFinder = new AStarPathFinder(this.map, this.maxPathLength, false);
        this.path = this.pathFinder.findPath(null, (int)this.GetPosX()/32, (int)this.GetPosY()/32, this.TargetposX, this.TargetposY);
        this.mover = 1;
        }
       
        public void GoStepRight(TiledMap map, int moveableLayerId){
                if(map.getTileId((int)(this.posX/32)+1, (int)this.posY/32, moveableLayerId)!=0){
                this.path.appendStep((int)(this.posX/32)+1, (int)this.posY/32);
                }
        }
        public void GoStepLeft(TiledMap map, int moveableLayerId){
                if(map.getTileId((int)(this.posX/32)-1, (int)this.posY/32, moveableLayerId)!=0){
                this.path.appendStep((int)(this.posX/32)-1, (int)this.posY/32);
                }
        }
        public void GoStepUp(TiledMap map, int moveableLayerId){
                if(map.getTileId((int)this.posX/32, (int)(this.posY/32)-1, moveableLayerId)!=0){
                this.path.appendStep((int)(this.posX/32), (int)(this.posY/32)-1);
                }
        }
        public void GoStepDown(TiledMap map, int moveableLayerId){
                if(map.getTileId((int)this.posX/32, (int)(this.posY/32)+1, moveableLayerId)!=0){
                this.path.appendStep((int)(this.posX/32), (int)(this.posY/32)+1);
                }
        }
}