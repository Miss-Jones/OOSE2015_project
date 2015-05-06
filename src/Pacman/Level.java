package Pacman;
 
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;
 
public class Level {
       
        protected TiledMap map;
        protected String mapName;
        protected Shape[] hitbox = new Rectangle[1000];
        protected int count;
        protected int hitboxLayer;
        protected boolean[][] blocked = new boolean[100][100];
       
        Level(){
        }
 
        public void SetMap(String m){
                this.mapName = m;
                try {
                        this.map = new TiledMap(this.mapName);
                       
                } catch (SlickException e) {
                        // TODO Auto-generated catch block
                        System.out.println("Could not initiate Tiled Map");
                }
        }
        public TiledMap GetMap(){
                return this.map;
        }
       
        public String GetMapName(){
                return this.mapName;
        }
       
        public void SetHitBox(String h){
                this.count = 0;
                this.hitboxLayer = this.map.getLayerIndex(h);
                for(int x = 0; x<this.map.getWidth();x++){
                        for(int y = 0; y<this.map.getHeight();y++){
                                if(this.map.getTileId(x, y, this.hitboxLayer)!=0){
                                        this.hitbox[this.count] = new Rectangle(x*32,y*32,32,32);
                                        this.count++;
                                        this.blocked[x][y]= true;
                                }
                        }
                }
        }
       
        public int GetIndexLayer(){
                return this.hitboxLayer;
        }
       
        public Shape[] GetHitbox(){
                return this.hitbox;
        }
       
        public boolean[][] GetBlocked(){
                                return this.blocked;
 
        }
       
        public boolean collision(Player p){
                Shape rect[] = this.GetHitbox();
                for(int i = 0; i<this.count;i++){
                        if(p.getHitBox().intersects(rect[i])){
                                return true;
                        }
        }
                return false;
        }
       
        /*
       
        public boolean TopCollision(Player p){
                if(p.speedX!=0||p.speedY>0){
                        return false;
                }
                Shape rect[] = this.GetHitbox();
                Point upperRight = new Point (p.getHitBox().getX()+p.getHitBox().getWidth(),p.getHitBox().getY());
                for(int i = 0; i<this.count;i++){
                        if(rect[i].contains(upperRight)){
                                if(p.speedX*p.speedX<p.speedY*p.speedY){
                                        return true;
                                }
                        }
                }
                return false;
                }
       
        public boolean BottomCollision(Player p){
                if(p.speedX!=0||p.speedY<0){
                        return false;
                }
        Shape rect[] = this.GetHitbox();
        Point lowerLeft = new Point (p.getHitBox().getX(),p.getHitBox().getY()+p.getHitBox().getHeight());
        for(int i = 0; i<this.count;i++){
                if(rect[i].contains(lowerLeft)){
                if(p.speedX*p.speedX<p.speedY*p.speedY){
                                return true;
                        }
                }
        }
        return false;
}
       
        public boolean LeftCollision(Player p){
                if(p.speedY!=0||p.speedX>0){
                        return false;
                }
                Shape rect[] = this.GetHitbox();
                Point lowerLeft = new Point (p.getHitBox().getX(),p.getHitBox().getY()+p.getHitBox().getHeight());
                for(int i = 0; i<this.count;i++){
                        if(rect[i].contains(lowerLeft)){
                                if(p.speedX*p.speedX>p.speedY*p.speedY){
                                        return true;
                        }
                }
        }
                return false;
}
       
        public boolean RightCollision(Player p){
                if(p.speedY!=0||p.speedX<0){
                        return false;
                }
                Shape rect[] = this.GetHitbox();
                Point upperRight = new Point (p.getHitBox().getX()+p.getHitBox().getWidth(),p.getHitBox().getY());
                for(int i = 0; i<this.count;i++){
                        if(rect[i].contains(upperRight)){
                                if(p.speedX*p.speedX>p.speedY*p.speedY){
                                        return true;
                                }
                        }
                }
                return false;
        }*/
       
        public int GetHeight(){
                return this.map.getHeight();
        }      
        public int GetWidth(){
                return this.map.getWidth();
        }
       
}