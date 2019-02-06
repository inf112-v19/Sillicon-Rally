package inf112.skeleton.app.util;

/**
 * Created by Martin on 06/02/2019.
 */
public class MyGrid {

    private Cell[][] grid;
    private int pixelsPerTile;


    public MyGrid(int width, int length, int pPT) {
        this.grid = new Cell[width][length];
        this.pixelsPerTile = pPT;

    }



}
