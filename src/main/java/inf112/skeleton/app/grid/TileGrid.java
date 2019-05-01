package inf112.skeleton.app.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.game.RoboGame;

import java.util.ArrayList;

/*
* En klasse som tar imot to pikselbaserte koordinater og returnerer
* riktig Tile. På denne måten kan frontenden operere med pixler, mens
* backenden kan bruke Tile-objekter til å bestemme logikk og kollisjonshåndtering.
*
* Klassen holder alle Tile objektene som brukes i spillkartet.
 */

public class TileGrid {
    public int tileSizeInPx;
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
    * Get a tile based on the Tiles xPosition and yPosition coordinates, 0,0 being bottom left
     */
    public Tile getTile(int y, int x) {
        if (x >= columns || y >= rows) {
            throw new IllegalArgumentException("Attempt to move out of map");
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

    public ArrayList<Tile> getDirectPath(Tile tile, RoboGame.Direction dir) {
        ArrayList<Tile> list = new ArrayList<>();

        switch (dir) {
            case North:
                return getAllNorthTiles(tile);
            case South:
                return getAllSouthTiles(tile);
            case West:
                return getAllWestTiles(tile);
            case East:
                return getAllEastTiles(tile);
        }

        return list;
    }

    private ArrayList<Tile> getAllEastTiles(Tile tile) {
        ArrayList<Tile> list = new ArrayList<>();
        if (tile.x == columns-1)
            return list;

        for (int i = tile.x+1; i < columns; i++) {
            list.add(getTile(tile.y, i));
        }

        return list;

    }

    private ArrayList<Tile> getAllWestTiles(Tile tile) {
        ArrayList<Tile> list = new ArrayList<>();
        if (tile.x == 0)
            return list;

        for (int i = tile.x-1; i >= 0; i--) {
            list.add(getTile(tile.y, i));
        }

        return list;
    }

    private ArrayList<Tile> getAllSouthTiles(Tile tile) {
        ArrayList<Tile> list = new ArrayList<>();
        if (tile.y == 0)
            return list;

        for (int i = tile.y-1; i >= 0; i--) {
            list.add(getTile(i, tile.x));
        }

        return list;
    }

    private ArrayList<Tile> getAllNorthTiles(Tile tile) {
        ArrayList<Tile> list = new ArrayList<>();
        if (tile.y == rows-1)
            return list;

        for (int i = tile.y+1; i < rows; i++) {
            list.add(getTile(i, tile.x));
        }
        return list;
    }


}

