package inf112.skeleton.app.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.Objects.IGameObject;

import java.util.ArrayList;

/*
* En klasse som tar imot to pikselbaserte koordinater og returnerer
* riktig Tile. På denne måten kan frontenden operere med pixler, mens
* backenden kan bruke Tile-objekter til å bestemme logikk og kollisjonshåndtering.
*
* Klassen holder alle Tile objektene som brukes i spillkartet.
 */

public class TileGrid {
    int tileSizeInPx;
    Tile[][] grid;
    public int rows;
    public int columns;
    int size;

    public TileGrid(int rows, int columns, int tileSizeInPx) {
        this.tileSizeInPx = tileSizeInPx;
        this.rows = rows;
        this.columns = columns;
        grid = new Tile[rows][columns];
        this.size = rows * columns;

        grid = createGrid(rows, columns);
    }

    public Tile[][] createGrid(int rows, int columns) {
        grid = new Tile[rows][columns];

        int y = 0;

        for (int i = rows-1; i >= 0 ; i--) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Tile(j, y, tileSizeInPx);
            }
            y++;

        }
        return grid;
    }

    public Tile getTileFromCoordinates(float yCoor, float xCoor) {

        int yTile = (int) yCoor/tileSizeInPx;
        int xTile = (int) xCoor/tileSizeInPx;


        if ((xCoor % tileSizeInPx == 0) && (yCoor % tileSizeInPx == 0)) {
            int x = ((int) xCoor / tileSizeInPx);
            int y = ((int) yCoor / tileSizeInPx);

            return getTile(y, x);
        }
        return getTile(yTile, xTile);
    }

    /*
    * Get a tile based on the Tiles x and y coordinates, 0,0 being bottom left
     */
    public Tile getTile(int y, int x) {
        if (x > columns || y > rows) {
            throw new IllegalArgumentException();
        }

        Tile tile = grid[(rows-1) - y][x];

        return tile;
    }

    public ArrayList<IGameObject> getAllSpritesOnMap() {
        ArrayList<IGameObject> list = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                for (IGameObject gameObject : grid[i][j].getGameObjects()) {
                    list.add(gameObject);
                }
            }
        }

        return list;
    }
}
