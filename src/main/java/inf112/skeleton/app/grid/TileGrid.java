package inf112.skeleton.app.grid;

import inf112.skeleton.app.Tile;

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
    int rows;
    int columns;
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

        if ((xCoor % tileSizeInPx == 0) && (yCoor % tileSizeInPx == 0)) {
            int x = ((int) xCoor / tileSizeInPx);
            int y = ((int) yCoor / tileSizeInPx);

            return getTile(y, x);
        }
        return null;
    }

    /*
    * Get a tile based on the Tiles x and y coordinates, 0,0 being bottom left
     */
    public Tile getTile(int y, int x) {
        if (y > columns || x > rows)
            throw new IllegalArgumentException();

        Tile tile = grid[(rows-1) - y][x];

        return tile;
    }


}
