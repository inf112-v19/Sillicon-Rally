package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class Tile implements ITile, Comparable<Tile> {
    public int x;
    public int y;
    public int xCoordFrom;
    public int xCoordTo;
    public int yCoordFrom;
    public int yCoordTo;
    ArrayList<Sprite> list;

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

    public ArrayList<Sprite> getSprites() {
        if (list.isEmpty())
            return null;

        return list;
    }

    public void addSprite(Sprite sprite) {
        list.add(sprite);
    }

}

