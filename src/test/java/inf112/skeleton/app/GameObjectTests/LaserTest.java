package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.collision.objects.LaserObject;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class LaserTest {
    private TileGrid grid;
    private Player player;
    private LaserObject laserObject;
    private LaserObject laser;
    private int TILE_SIZE_IN_PX;


    public void setup(){
        SetupVariables variables = new SetupVariables();
        grid = variables.grid;
        player = variables.player;

        //player = new Player(grid);
        player.setPosition(2 * TILE_SIZE_IN_PX, 2 * TILE_SIZE_IN_PX, grid);
        laserObject = variables.laser;
        laser = new LaserObject(2,2,grid);
        //laserObject = new LaserObject(2 * TILE_SIZE_IN_PX, 2 * TILE_SIZE_IN_PX, grid);
        TILE_SIZE_IN_PX = grid.tileSizeInPx;
                //variables.gameMap.getTileSize();
    }

    @Test
    void laserPresentOnMapTest(){
        setup();
        Tile laserTile = grid.getTileFromCoordinates(laser.yLocation, laser.xLocation);
        assertTrue(laserTile.getGameObjects().contains(laser));
    }

    @Test
    void playerTakesDamage(){
        setup();
        laserObject.handleCollision(player, grid);
        assertEquals(player.playerHP, player.MAX_HP - laserObject.LASER_DAMAGE);
    }


    @Test
    void playerTakesDamageMovingThroughLaser(){
        setup();
        player.setDirection(RoboGame.Direction.North);
        player.setPosition(1, 2, grid);
        player.moveStraight(2,1,grid);
        assertTrue(player.playerHP < player.MAX_HP);
    }

}
