package Pacman;
 
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.util.ResourceLoader;
 
public class Play extends BasicGameState {
       
        Player player;//create player
        Enemy ghost[] = new Enemy[4];//create all enemies
        Level collisionLevel;//create the collision level
        Level coinLevel;//create the coin level
        Image coin;//image of coin
        Image powerUp;//image of power-up
        TrueTypeFont font2;//New font
        Score score = new Score();//make an instance of the Score class
        Image pacmanpic;//pacman image
        Image livespic;//lives image
        Image scorepic;//score image
       
        long startupTime;//time for startup
    	int startupDelay;//the delay for startup
    	long runningTime;//the time for death animation
    	int aniTime;//the delay for death animation
    	boolean dieani;//if death animation has been run
         
        /**
         * play state 
         * @param state the integer: The state
         */
        public Play(int state){
        }
       
        /**
         * Set images for the power up and for the coin <br>
         * Set the maps with the matching level <br>
         * Set the hitbox with the matching level <br>
         * Set the animations to the player when moving up, down, left, right <br>
         * Set the path for the player <br>
         * Set the animations for each ghost when moving up, down, left, right <br>
         * Set the path for the ghosts <br>
         * Loads a custom font and sets its size
         */
        @Override
        public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
        		pacmanpic = new Image("data/fonts/pacman3.png");
        		livespic = new Image("data/fonts/lives.png");
        		scorepic = new Image("data/fonts/score.png");
        		dieani = false;
        		runningTime = 0;
    			startupTime = 0;
    			aniTime = 2400;
    			startupDelay = 4388;
            	collisionLevel = new Level();
            	coinLevel = new Level();
        		player = new Player(15*32,14*32,3,"Player1");
        		ghost[0] = new Enemy(448,384); 
        		ghost[1] = new Enemy(480,384);
        		ghost[2] = new Enemy(512,384);
        		ghost[3] = new Enemy(544,384);
                collisionLevel.SetMap("data/map/map.tmx");
                collisionLevel.SetHitBox("collider");
               
                coin = new Image("data/map/coin.png");
                coinLevel.SetMap("data/map/map.tmx","data/sounds/pacmanEat.ogg");
                coinLevel.SetHitBox("coinPath");
               
                player.SetAniUp("data/pacman/PacmanUp1.png","data/pacman/PacmanUp2.png","data/pacman/PacmanBallUpDown.png");
                player.SetAniDown("data/pacman/PacmanDown1.png", "data/pacman/PacmanDown2.png", "data/pacman/PacmanBallUpDown.png");
                player.SetAniLeft("data/pacman/PacmanLeft1.png","data/pacman/PacmanLeft2.png","data/pacman/PacmanBallLeftRight.png");
                player.SetAniRight("data/pacman/PacmanRight1.png", "data/pacman/PacmanRight2.png", "data/pacman/PacmanBallLeftRight.png");
                player.SetDieAni("data/pacman/die/PacmanDie1.png", "data/pacman/die/PacmanDie2.png", "data/pacman/die/PacmanDie3.png", "data/pacman/die/PacmanDie4.png", "data/pacman/die/PacmanDie5.png", "data/pacman/die/PacmanDie6.png", "data/pacman/die/PacmanDie7.png", "data/pacman/die/PacmanDie8.png", "data/pacman/die/PacmanDie9.png", "data/pacman/die/PacmanDie10.png", "data/pacman/die/PacmanDie11.png", "data/pacman/die/PacmanDie12.png", 200);
                player.SetSprite(player.GetAniRight());
                player.SetPath(collisionLevel);
               
                ghost[0].SetAniUp("data/ghost/red/GhostUp1.png","data/ghost/red/GhostUp2.png","data/ghost/red/GhostUp1.png");
                ghost[0].SetAniDown("data/ghost/red/GhostDown1.png","data/ghost/red/GhostDown2.png","data/ghost/red/GhostDown1.png");
                ghost[0].SetAniLeft("data/ghost/red/GhostLeft1.png","data/ghost/red/GhostLeft2.png","data/ghost/red/GhostLeft1.png");
                ghost[0].SetAniRight("data/ghost/red/GhostRight1.png","data/ghost/red/GhostRight2.png","data/ghost/red/GhostRight1.png");
                ghost[0].SetSprite(ghost[0].GetAniUp());
                ghost[0].SetPath(collisionLevel);
               
                ghost[1].SetAniUp("data/ghost/cyan/GhostUp1.png","data/ghost/cyan/GhostUp2.png","data/ghost/cyan/GhostUp1.png");
                ghost[1].SetAniDown("data/ghost/cyan/GhostDown1.png","data/ghost/cyan/GhostDown2.png","data/ghost/cyan/GhostDown1.png");
                ghost[1].SetAniLeft("data/ghost/cyan/GhostLeft1.png","data/ghost/cyan/GhostLeft2.png","data/ghost/cyan/GhostLeft1.png");
                ghost[1].SetAniRight("data/ghost/cyan/GhostRight1.png","data/ghost/cyan/GhostRight2.png","data/ghost/cyan/GhostRight1.png");
                ghost[1].SetSprite(ghost[1].GetAniUp());
                ghost[1].SetPath(collisionLevel);
               
                ghost[2].SetAniUp("data/ghost/orange/GhostUp1.png","data/ghost/orange/GhostUp2.png","data/ghost/orange/GhostUp1.png");
                ghost[2].SetAniDown("data/ghost/orange/GhostDown1.png","data/ghost/orange/GhostDown2.png","data/ghost/orange/GhostDown1.png");
                ghost[2].SetAniLeft("data/ghost/orange/GhostLeft1.png","data/ghost/orange/GhostLeft2.png","data/ghost/orange/GhostLeft1.png");
                ghost[2].SetAniRight("data/ghost/orange/GhostRight1.png","data/ghost/orange/GhostRight2.png","data/ghost/orange/GhostRight1.png");
                ghost[2].SetSprite(ghost[2].GetAniUp());
                ghost[2].SetPath(collisionLevel);
               
                ghost[3].SetAniUp("data/ghost/pink/GhostUp1.png","data/ghost/pink/GhostUp2.png","data/ghost/pink/GhostUp1.png");
                ghost[3].SetAniDown("data/ghost/pink/GhostDown1.png","data/ghost/pink/GhostDown2.png","data/ghost/pink/GhostDown1.png");
                ghost[3].SetAniLeft("data/ghost/pink/GhostLeft1.png","data/ghost/pink/GhostLeft2.png","data/ghost/pink/GhostLeft1.png");
                ghost[3].SetAniRight("data/ghost/pink/GhostRight1.png","data/ghost/pink/GhostRight2.png","data/ghost/pink/GhostRight1.png");
                ghost[3].SetSprite(ghost[3].GetAniUp());
                ghost[3].SetPath(collisionLevel);
                
                try {
                    InputStream inputStream = ResourceLoader.getResourceAsStream("data/fonts/heavy_data.ttf");
                     
                    Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
                    awtFont2 = awtFont2.deriveFont(36f); // set font size
                    font2 = new TrueTypeFont(awtFont2, false);
                         
                } catch (Exception e) {
                    System.out.println("Could not create font!");//if the font could not be created, print.
                }
    }  
       
        /**
         * Used for drawing the coins around the map <br>
         * Sets up the size and placement of the GUI
         * The commented part makes the path of the ghost visible with the matching color to the ghost (e.g. red ghost, red path, etc.)
         */
        @Override
        public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException{
                collisionLevel.GetMap().render(0,0);//set the map on screen
                //draw the coins
                for(int x = 0; x<coinLevel.GetWidth();x++){
                        for(int y = 0; y<coinLevel.GetHeight();y++){
                                if(coinLevel.GetBlocked()[x][y]){
                                        coin.draw(x*32,y*32);
                                }
                        }
                }
                //draw all the sprites
                ghost[0].GetSprite().draw(ghost[0].GetPosX(),ghost[0].GetPosY());
                ghost[1].GetSprite().draw(ghost[1].GetPosX(),ghost[1].GetPosY());
                ghost[2].GetSprite().draw(ghost[2].GetPosX(),ghost[2].GetPosY());
                ghost[3].GetSprite().draw(ghost[3].GetPosX(),ghost[3].GetPosY());
                player.GetSprite().draw(player.GetPosX(),player.GetPosY());
                
                //draw the gui
                font2.drawString(880, 365, ""+player.getLives(), Color.white);
                font2.drawString(855, 435, ""+player.GetScore(), Color.white);
                pacmanpic.draw(40, 365);
                livespic.draw(840, 335);
                scorepic.draw(830, 410);
               
                //Uncomment this to see the path of all the ghosts, and pacman
               /* for(int i = 0; i < ghost[0].getPathLenght(); i++) {
                        g.setColor(Color.red);
                        g.draw(ghost[0].getPathHitbox()[i]);
        }
                for(int i = 0; i<ghost[1].getPathLenght();i++){
                        g.setColor(Color.cyan);
                        g.draw(ghost[1].getPathHitbox()[i]);
                }
                for(int i = 0; i<ghost[2].getPathLenght();i++){
                        g.setColor(Color.orange);
                        g.draw(ghost[2].getPathHitbox()[i]);
                }
                for(int i = 0; i<ghost[3].getPathLenght();i++){
                        g.setColor(Color.pink);
                        g.draw(ghost[4].getPathHitbox()[i]);
                }
                for(int i = 0; i<player.getPathLenght();i++){
                        g.setColor(Color.yellow);
                        g.draw(player.getPathHitbox()[i]);
                }*/
               
        }
       
       
        /**
         * Update: updates different values for e.g. movement and sprites depending on the delta time
         */
        @Override
        public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException{
               
        	 //wait for the startup to be finished
        	 if(startupTime>startupDelay){
                 ghost[0].movePath(collisionLevel);//Update the ghost path
                 ghost[0].Move(delta, gc.getHeight(), gc.getWidth());//move the ghost on the path
                 ghost[0].GetSprite().update(delta);//update the sprite to animate depending on delta time
                 }
        	 //wait for the startup to be finished
                 if(startupTime>startupDelay){
                 ghost[1].movePath(collisionLevel);//Update the ghost path
                 ghost[1].Move(delta, gc.getHeight(), gc.getWidth());//move the ghost on the path
                 ghost[1].GetSprite().update(delta);//update the sprite to animate depending on delta time
                 }
               //wait for the startup to be finished
                 if(startupTime>startupDelay){
                 ghost[2].movePath(collisionLevel);//Update the ghost path
                 ghost[2].Move(delta, gc.getHeight(), gc.getWidth());//move the ghost on the path
                 ghost[2].GetSprite().update(delta);//update the sprite to animate depending on delta time
                 }
               //wait for the startup to be finished
                 if(startupTime>startupDelay){
                 ghost[3].movePath(collisionLevel);//Update the ghost path
                 ghost[3].Move(delta, gc.getHeight(), gc.getWidth());//move the ghost on the path
                 ghost[3].GetSprite().update(delta);//update the sprite to animate depending on delta time
                 }
                
                 if(runningTime>aniTime&&startupTime>startupDelay){//wait for startup to be finished and pacman to have finished dying
                 player.movePath(collisionLevel);//update the player move path
                 player.Move(delta,gc.getHeight(),gc.getWidth());//move the player on the path
                 }
                 player.GetSprite().update(delta);//update the player sprite to animate depending on delta time
                
                 Input input = gc.getInput();//get user-input
                 if(input.isKeyPressed(Input.KEY_UP)&&runningTime>aniTime&&startupTime>startupDelay){//if up key pressed and wait for start up to be finished and pacman to have finished dying
                         player.GoStepUp(coinLevel.GetMap(),coinLevel.GetIndexLayer());//make the player path 1 step up
                         player.SetSprite(player.GetAniUp());//set the animation of pacman to up
                 }else if(input.isKeyPressed(Input.KEY_DOWN)&&runningTime>aniTime&&startupTime>startupDelay){//if down key pressed and wait for start up to be finished and pacman to have finished dying
                         player.GoStepDown(coinLevel.GetMap(),coinLevel.GetIndexLayer());//make the player path 1 step down
                         player.SetSprite(player.GetAniDown());//set the animation of pacman to down
                 }else if(input.isKeyPressed(Input.KEY_LEFT)&&runningTime>aniTime&&startupTime>startupDelay){//if left key pressed and wait for start up to be finished and pacman to have finished dying
                         player.GoStepLeft(coinLevel.GetMap(),coinLevel.GetIndexLayer());//make the player path 1 step left
                         player.SetSprite(player.GetAniLeft());//set the animation of pacman to left
                 }else if(input.isKeyPressed(Input.KEY_RIGHT)&&runningTime>aniTime&&startupTime>startupDelay){//if right key pressed and wait for start up to be finished and pacman to have finished dying
                         player.GoStepRight(coinLevel.GetMap(),coinLevel.GetIndexLayer());//make the player path 1 step right
                         player.SetSprite(player.GetAniRight());//set the animation of pacman to right
                 }
                //go through the level
                 for(int x = 0; x<coinLevel.GetWidth();x++){
                         for(int y = 0; y<coinLevel.GetHeight();y++){
                        	 //if player hitbox hits any coin hitbox
                                 if(player.getHitBox().contains(coinLevel.GetHitbox()[x][y].getCenterX(), coinLevel.GetHitbox()[x][y].getCenterY())){
                                         if(coinLevel.GetBlocked()[x][y]){
                                                 coinLevel.playSound();//play the coin sound
                                                 player.SetScore(10);//plus the scores with 10
                                                 player.SetCoinsEaten(1);//plus the coins eaten with 1
                                         }
                                         coinLevel.SetBlocked(x, y, false);//if a coin has been taken, delete it
                                 }
                         }
                 }
                //go through all ghosts
                 for(int i = 0; i<ghost.length;i++){
                	 //if player hits any ghost
                         if(player.getHitBox().contains(ghost[i].getHitBox().getCenterX(),ghost[i].getHitBox().getCenterY())&&runningTime>aniTime){
                                 player.die();//-1 live
                                 player.SetScore(-250);//minus the score with 250
                                 player.SetSprite(player.getDieAni());//sets the player animation to the die animation
                                 runningTime = 0;//start over the timer
                                 dieani = true;//and set the die animation to true
                                
                         }
                 }
                 runningTime +=delta; //increment the timer
                 startupTime +=delta; //increment the timer
                 if(dieani&&runningTime>aniTime){ //if the player is not in a die state
                         player.SetSprite(player.GetAniRight());//set the player sprite to right
                         dieani = false;
                 }        
                if(player.DEAD()){//if there is no more lives
                	Score.setScore(player.GetScore());//Set the global score to the score you have
                	init(gc,sbg); //reset the play state
                        sbg.enterState(3); //go into the lose state
                }
               
                if(player.WIN()){//if there is no more coins
                	Score.setScore(player.GetScore());//Set the global score to the score you have
                	init(gc,sbg);//reset the play state
                        sbg.enterState(2);//go into the win state
                }
        }
       
        /**
         * returns the integer getID <br>
         * This is an override function 
         */
        @Override
        public int getID(){
                return 1;
        }
}