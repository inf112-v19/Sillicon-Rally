package inf112.skeleton.app.grid;

import inf112.skeleton.app.ITile;
import inf112.skeleton.app.Objects.IGameObject;

import java.util.ArrayList;

public class Tile implements ITile, Comparable<Tile> {
    public int x;
    public int y;
    public int xCoordFrom;
    public int xCoordTo;
    public int yCoordFrom;
    public int yCoordTo;
    ArrayList<IGameObject> list;

    public Tile(int x, int y, int tileSizeInPx) {
        this.x = x;
        this.y = y;
        xCoordFrom = x * tileSizeInPx;
        xCoordTo = (x+1) * tileSizeInPx;
        yCoordFrom = y * tileSizeInPx;
        yCoordTo = (y+1) * tileSizeInPx;
        list = new ArrayList<>();
    }

    public int getX() {
        return this.x;
    }

    @Override
    public int compareTo(Tile o) {
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Tile))
            return false;
        if (o == this)
            return true;

        Tile tile = (Tile) o;

        if (tile.x == this.x && tile.y == this.y)
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "[x: " + this.x + " y: " +this.y + " ]";
    }

    public ArrayList<IGameObject> getGameObjects() {
        return list;
    }

    public void addGameObject(IGameObject mapObject) {
        list.add(mapObject);
    }

}

