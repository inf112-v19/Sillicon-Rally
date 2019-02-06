package inf112.skeleton.app;

import java.awt.*;

public class Tile implements ITile, Comparable<Tile> {
    public int x;
    public int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    @Override
    public int compareTo(Tile o) {
        return 0;
    }
}
