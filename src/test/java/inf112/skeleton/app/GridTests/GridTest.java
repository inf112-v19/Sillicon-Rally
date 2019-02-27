import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class GridTest {
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
                assertEquals(("[x: " + j + " y: " + i + " ]"), s);
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

        ArrayList<Sprite> sprites = tg.getAllSpritesOnMap();

        for (Sprite sp : sprites) {
            assertEquals(one, sp);
        }


    }



}
