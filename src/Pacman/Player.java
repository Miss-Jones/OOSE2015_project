package Pacman;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
 
 
public class Player extends Entity {
       
        protected String name;
        protected int score;
        protected Sound sound; //Put in sound plz
 
 
        Player(int x, int y, int l, String n){
                this.posX = x;
                this.posY = y;
                this.name = n;         
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
                this.score = s;
        }
       
        public int GetScore(){
                return this.score;
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