package inf112.skeleton.app.GridTests;

import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.LaserObject;
import inf112.skeleton.app.collision.objects.PitObject;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileTest {
    private TileGrid grid;
    private int TILESIZE_IN_PIX;
    private LaserObject laser;
    private Tile testTile;

    void setup(){
      SetupVariables variables = new SetupVariables();
      grid = variables.grid;
      TILESIZE_IN_PIX = grid.tileSizeInPx;
      laser = variables.laser;
      testTile = new Tile(0,0,grid.tileSizeInPx);

    }

    @Test
    public void equalsTest() {
        setup();
        assertEquals(true, testTile.equals(grid.getTile(0,0)));
    }

    @Test
    void addGameObjectsMethodTest(){
        setup();
        laser.xLocation = 0;
        laser.yLocation = 0;
        testTile.addGameObject(laser);
    }

    @Test
    void getGameObjectsMethodTest(){
        setup();
        laser.xLocation = 0;
        laser.yLocation = 0;
        testTile.addGameObject(laser);
        assertEquals(1, testTile.getGameObjects().size());
    }

    @Test
    void rightObjectIsAddedToTileTest(){
        setup();
        laser.xLocation = 0;
        laser.yLocation = 0;
        testTile.addGameObject(laser);
        assertTrue(testTile.getGameObjects().contains(laser));
    }

}
