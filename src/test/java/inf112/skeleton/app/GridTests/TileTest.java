package inf112.skeleton.app.GridTests;

import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.grid.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileTest {

    @Test
    public void equalsTest() {
        SetupVariables setup = new SetupVariables();
        Tile testTile = new Tile(0,0, setup.TILESIZE_IN_PX);
        assertEquals(true, testTile.equals(setup.grid.getTile(0,0)));
    }


}
