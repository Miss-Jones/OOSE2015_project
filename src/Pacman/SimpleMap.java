package Pacman;
 
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
 
public class SimpleMap implements TileBasedMap {
       
    private TiledMap map;
    private int blockingLayerId;
   
    /**
     * Takes this.map and set it to the map <br>
     * Takes this.blockingLayerId and set it to the blockingLayerId <br>
     * @param map the TiledMap: makes this.map to the map
     * @param blockingLayerId the integer: makes this.blockingLayerId into the blockingLayerId
     */
    public SimpleMap(TiledMap map, int blockingLayerId) {
        this.map = map;
        this.blockingLayerId = blockingLayerId;
    }
    
    /**
     * Used for checking if there is a wall or not on the path.
     * @return map.getTileId the boolean
     */
       @Override
    public boolean blocked(PathFindingContext ctx, int x, int y) {
        return map.getTileId(x, y, blockingLayerId) != 0;
    }
 
    /**
     * Get cost of every tile
     */
    @Override
    public float getCost(PathFindingContext ctx, int x, int y) {
        return 1.0f;
    }
 
    /**
     * gets the height of the tiles
     * @return map.getHeight the integer
     */
    @Override
    public int getHeightInTiles() {
        return map.getHeight();
    }
 
    /**
     * gets the width of the tiles
     * @return map.getWidth the integer
     */
    @Override
    public int getWidthInTiles() {
        return map.getWidth();
    }
 
    /**
     * checks if the path have been visited using arguments 0 and 1
     */
    @Override
    public void pathFinderVisited(int arg0, int arg1) {}
 
}