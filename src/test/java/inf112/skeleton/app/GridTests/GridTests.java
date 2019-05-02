package inf112.skeleton.app.GridTests;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class GridTests {
    int rows = 10;
    int columns = 8;
    int tileSizeInPx = 128;


    private TileGrid newGrid() {
        return new TileGrid(rows, columns, tileSizeInPx);
    }

    @Test
    public void createGridTest() {
        TileGrid tg = newGrid();
        Tile[][] grid = tg.createGrid(rows, columns);
        int[][] arr = new int[rows][columns];

        int a = rows -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Tile tile = grid[i][j];
                assertEquals(a, tile.y);
                assertEquals(j, tile.x);
            }
            a--;
        }

    }

    @Test
    public void getTileTest() {
        TileGrid tg = newGrid();

        for (int i = 0; i < rows -1; i++) {
            for (int j = 0; j < columns -1; j++) {
                String s = tg.getTile(i, j).toString();
                assertEquals(("[xPosition: " + j + " yPosition: " + i + " ]"), s);
            }
        }
    }

    @Test
    public void getTileFromCoordinatesTest() {
        TileGrid tg = newGrid();

        Tile tile = tg.getTileFromCoordinates(128, 0);
        assertEquals(1, tile.y);
        assertEquals(0, tile.x);
    }

    @Test
    public void getAllSpritesOnMapTest() {
        TileGrid tg = newGrid();

        Sprite one = new Sprite();
        Sprite oneCopy = one;

        ArrayList<IGameObject> sprites = tg.getAllSpritesOnMap();

        for (IGameObject sp : sprites) {
            assertEquals(one, sp);
        }
    }

    @Test
    void findPlayerOnTileTest() {
        TileGrid tg = newGrid();
        SetupVariables setup = new SetupVariables();
        tg.getTile(0,0).addGameObject(setup.player);

        for (IGameObject o : tg.getTile(0,0).getGameObjects()) {
            if (o instanceof Player)
                assertEquals(true, true);
            else
                fail();
        }
    }



}
