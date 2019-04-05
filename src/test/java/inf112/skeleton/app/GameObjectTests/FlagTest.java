
package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.FlagObject;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;


public class FlagTest {
    TileGrid grid;
    FlagObject flag;
    int TILE_SIZE_IN_PX;

    public void setup() {
        SetupVariables variables = new SetupVariables();
        grid = variables.grid;
        flag = variables.flag;
        TILE_SIZE_IN_PX = variables.gameMap.getTileSize();
    }


    @Test
   public void flagIsPickedUpTest(){
        SetupVariables variables = new SetupVariables();
        TileGrid grid = variables.grid;
        FlagObject flag = variables.flag;
        Player player = variables.player;
       player.setPosition(flag.yLocation, flag.xLocation, grid);
        Tile flagtile = grid.getTileFromCoordinates(flag.yLocation, flag.xLocation);
       flag.handleCollision(player, grid);

        assertEquals(true, flagtile.getGameObjects().contains(flag));


        variables.stop();
    }

    @Test
    void flagPresentOnMapTest(){
        SetupVariables variables = new SetupVariables();
        TileGrid grid = variables.grid;
        FlagObject flag = variables.flag;
        Player player = variables.player;
        Tile flagtile = grid.getTileFromCoordinates(flag.yLocation, flag.xLocation);
        assertEquals(true, flagtile.getGameObjects().contains(flag));
    }
}

