package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.LaserObject;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertTrue;

public class LaserTest {
    private TileGrid grid;
    private Player player;
    private int TILE_SIZE_IN_PIX;
    private LaserObject laserObject;


    public void setup(){
        SetupVariables setupVariable = new SetupVariables();
        grid = setupVariable.grid;
        player = setupVariable.player;
        TILE_SIZE_IN_PIX = setupVariable.gameMap.getTileSize();
    }

    @Test
    void playerTakesDamage(){
        setup();
        int originPlayerHP = player.getCurrentHP();
        player = new Player();
        player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
        laserObject = new LaserObject(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);

        laserObject.handleCollision(player, grid);
        assertTrue(originPlayerHP > player.getCurrentHP());
    }

}
