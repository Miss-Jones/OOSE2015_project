package Pacman;
 
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
 
public class Play extends BasicGameState {
       
        Player player = new Player(2*32,1*32,3,"Player1");
        Enemy ghost1 = new Enemy(448,384);
        Enemy ghost2 = new Enemy(480,384);
        Enemy ghost3 = new Enemy(512,384);
        Enemy ghost4 = new Enemy(544,384);
        Level collisionLevel = new Level();
        Level coinLevel = new Level();
        Level powerLevel = new Level();
        private boolean boolUp, boolDown, boolLeft, boolRight;
        Image coin;
        Image powerUp;
       
       
    private static final int MAX_PATH_LENGTH = 100;
    Path path;
    AStarPathFinder pathFinder;
    SimpleMap map;
    int mover;
    Shape[] test = new Rectangle[MAX_PATH_LENGTH];
   
   
   
        /**
         *
         * @param state the integer: The state
         */
        public Play(int state){
        }
       
        
        @Override
        public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
                coin = new Image("data/map/coin.png");
                powerUp = new Image("data/map/Powerup.png");
                collisionLevel.SetMap("data/map/map.tmx");
                collisionLevel.SetHitBox("collider");
                coinLevel.SetMap("data/map/map.tmx");
                coinLevel.SetHitBox("coinPath");
                powerLevel.SetMap("data/map/map.tmx");
                powerLevel.SetHitBox("powerup");
                player.SetAniUp("data/pacman/PacmanUp1.png","data/pacman/PacmanUp2.png","data/pacman/PacmanBallUpDown.png");
                player.SetAniDown("data/pacman/PacmanDown1.png", "data/pacman/PacmanDown2.png", "data/pacman/PacmanBallUpDown.png");
                player.SetAniLeft("data/pacman/PacmanLeft1.png","data/pacman/PacmanLeft2.png","data/pacman/PacmanBallLeftRight.png");
                player.SetAniRight("data/pacman/PacmanRight1.png", "data/pacman/PacmanRight2.png", "data/pacman/PacmanBallLeftRight.png");
                player.SetSprite(player.GetAniUp());
                boolRight = true;
               
                ghost1.SetAniUp("data/ghost/red/GhostUp1.png","data/ghost/red/GhostUp2.png","data/ghost/red/GhostUp1.png");
                ghost1.SetAniDown("data/ghost/red/GhostDown1.png","data/ghost/red/GhostDown2.png","data/ghost/red/GhostDown1.png");
                ghost1.SetAniLeft("data/ghost/red/GhostLeft1.png","data/ghost/red/GhostLeft2.png","data/ghost/red/GhostLeft1.png");
                ghost1.SetAniRight("data/ghost/red/GhostRight1.png","data/ghost/red/GhostRight2.png","data/ghost/red/GhostRight1.png");
                ghost1.SetSprite(ghost1.GetAniUp());
                ghost1.SetPath(collisionLevel);
               
                ghost2.SetAniUp("data/ghost/cyan/GhostUp1.png","data/ghost/cyan/GhostUp2.png","data/ghost/cyan/GhostUp1.png");
                ghost2.SetAniDown("data/ghost/cyan/GhostDown1.png","data/ghost/cyan/GhostDown2.png","data/ghost/cyan/GhostDown1.png");
                ghost2.SetAniLeft("data/ghost/cyan/GhostLeft1.png","data/ghost/cyan/GhostLeft2.png","data/ghost/cyan/GhostLeft1.png");
                ghost2.SetAniRight("data/ghost/cyan/GhostRight1.png","data/ghost/cyan/GhostRight2.png","data/ghost/cyan/GhostRight1.png");
                ghost2.SetSprite(ghost2.GetAniUp());
                ghost2.SetPath(collisionLevel);
               
                ghost3.SetAniUp("data/ghost/orange/GhostUp1.png","data/ghost/orange/GhostUp2.png","data/ghost/orange/GhostUp1.png");
                ghost3.SetAniDown("data/ghost/orange/GhostDown1.png","data/ghost/orange/GhostDown2.png","data/ghost/orange/GhostDown1.png");
                ghost3.SetAniLeft("data/ghost/orange/GhostLeft1.png","data/ghost/orange/GhostLeft2.png","data/ghost/orange/GhostLeft1.png");
                ghost3.SetAniRight("data/ghost/orange/GhostRight1.png","data/ghost/orange/GhostRight2.png","data/ghost/orange/GhostRight1.png");
                ghost3.SetSprite(ghost3.GetAniUp());
                ghost3.SetPath(collisionLevel);
               
                ghost4.SetAniUp("data/ghost/pink/GhostUp1.png","data/ghost/pink/GhostUp2.png","data/ghost/pink/GhostUp1.png");
                ghost4.SetAniDown("data/ghost/pink/GhostDown1.png","data/ghost/pink/GhostDown2.png","data/ghost/pink/GhostDown1.png");
                ghost4.SetAniLeft("data/ghost/pink/GhostLeft1.png","data/ghost/pink/GhostLeft2.png","data/ghost/pink/GhostLeft1.png");
                ghost4.SetAniRight("data/ghost/pink/GhostRight1.png","data/ghost/pink/GhostRight2.png","data/ghost/pink/GhostRight1.png");
                ghost4.SetSprite(ghost4.GetAniUp());
                ghost4.SetPath(collisionLevel);
    }  
       
        /**
         *
         */
        @Override
        public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException{
                collisionLevel.GetMap().render(0,0);
                for(int x = 0; x<coinLevel.GetWidth();x++){
                        for(int y = 0; y<coinLevel.GetHeight();y++){
                                if(coinLevel.GetBlocked()[x][y]){
                                                coin.draw(x*32,y*32);
                                               
                                        }
                                }
                        }
               
                for(int x = 0; x<powerLevel.GetWidth();x++){
                        for(int y = 0; y<powerLevel.GetHeight();y++){
                                if(powerLevel.GetBlocked()[x][y]){
                                                powerUp.draw(x*32,y*32);
                                               
                                        }
                                }
                        }
                ghost1.GetSprite().draw(ghost1.GetPosX(),ghost1.GetPosY());
                ghost2.GetSprite().draw(ghost2.GetPosX(),ghost2.GetPosY());
                ghost3.GetSprite().draw(ghost3.GetPosX(),ghost3.GetPosY());
                ghost4.GetSprite().draw(ghost4.GetPosX(),ghost4.GetPosY());
                player.GetSprite().draw(player.GetPosX(),player.GetPosY());
               
                for(int i = 0; i < ghost1.getPathLenght(); i++) {
                        g.setColor(Color.red);
                        g.draw(ghost1.getPathHitbox()[i]);
        }
               
                for(int i = 0; i<ghost2.getPathLenght();i++){
                        g.setColor(Color.cyan);
                        g.draw(ghost2.getPathHitbox()[i]);
                }
                for(int i = 0; i<ghost3.getPathLenght();i++){
                        g.setColor(Color.orange);
                        g.draw(ghost3.getPathHitbox()[i]);
                }
                for(int i = 0; i<ghost4.getPathLenght();i++){
                        g.setColor(Color.pink);
                        g.draw(ghost4.getPathHitbox()[i]);
                }
               
               
        }
       
       
        /**
         *
         */
        @Override
        public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException{
                ghost1.movePath(collisionLevel);
                ghost1.Move(delta, gc.getHeight(), gc.getWidth());
                ghost2.movePath(collisionLevel);
                ghost2.Move(delta, gc.getHeight(), gc.getWidth());
                ghost3.movePath(collisionLevel);
                ghost3.Move(delta, gc.getHeight(), gc.getWidth());
                ghost4.movePath(collisionLevel);
                ghost4.Move(delta, gc.getHeight(), gc.getWidth());
                player.GetSprite().update(delta);
                player.Move(delta,gc.getHeight(),gc.getWidth());
               
                Input input = gc.getInput();
                if(input.isKeyPressed(Input.KEY_UP)){
                        boolUp = true;
                        boolDown = false;
                        boolLeft = false;
                        boolRight = false;
                }else if(input.isKeyPressed(Input.KEY_DOWN)){
                        boolUp = false;
                        boolDown = true;
                        boolLeft = false;
                        boolRight = false;
                }else if(input.isKeyPressed(Input.KEY_LEFT)){
                        boolUp = false;
                        boolDown = false;
                        boolLeft = true;
                        boolRight = false;
                }else if(input.isKeyPressed(Input.KEY_RIGHT)){
                        boolUp = false;
                        boolDown = false;
                        boolLeft = false;
                        boolRight = true;
                }
               
 
                if(boolUp){
                        player.SetSprite(player.GetAniUp());
                        player.MoveUp();
                        if(collisionLevel.collision(player)){
                                boolUp = false;
                                player.StopUpDown();
                        }
                }else if(boolDown){
                        player.SetSprite(player.GetAniDown());
                        player.MoveDown();
                        if(collisionLevel.collision(player)){
                                boolDown = false;
                                player.StopUpDown();
                        }
                }else if(boolLeft){
                        player.SetSprite(player.GetAniLeft());
                        player.MoveLeft();
                        if(collisionLevel.collision(player)){
                                boolLeft = false;
                                player.StopLeftRight();
                        }                      
                }else if(boolRight){
                        player.SetSprite(player.GetAniRight());
                        player.MoveRight();
                        if(collisionLevel.collision(player)){
                                boolRight = false;
                                player.StopLeftRight();
                        }
                       
                }
        }
       
       
        @Override
        public int getID(){
        	return 1;
        }
}