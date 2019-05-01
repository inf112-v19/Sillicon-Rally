package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.RepairObject;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class RepairTests {
    private TileGrid grid;
    private Player player;
    private int TILE_SIZE_IN_PIX;
    private RepairObject repairObject;



    public void setUp(){
        SetupVariables setupVariable = new SetupVariables();
        grid = setupVariable.grid;
        player = setupVariable.player;
        TILE_SIZE_IN_PIX = setupVariable.gameMap.getTileSize();
        player = new Player(grid);
        player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
        repairObject = new RepairObject(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
    }

    @Test
    void repairObjectsArePresentTest(){
        setUp();
        Tile repairTile = grid.getTileFromCoordinates(repairObject.yLocation, repairObject.xLocation);
        assertTrue(repairTile.getGameObjects().contains(repairObject));
    }

    @Test
    void repairObjectRepairsHP(){
        setUp();
        player.playerHP = 0;
        repairObject.handleCollision(player, grid);

        assertEquals( 0 + repairObject.REPAIR_REGENERATE, player.playerHP);
    }
}
