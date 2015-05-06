package Pacman;
import org.newdawn.slick.Sound;
 
 
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
}