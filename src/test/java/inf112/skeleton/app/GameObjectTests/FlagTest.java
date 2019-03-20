
package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.FlagObject;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;


public class FlagTest {

    @Test
   public void flagTest(){
       SetupVariables variables = new SetupVariables();
       TileGrid grid = variables.grid;
       FlagObject flag = variables.flag;
       Player player = variables.player;
       Tile flagtile = grid.getTileFromCoordinates(flag.yLocation, flag.xLocation);
       assertEquals(true, flagtile.getGameObjects().contains(flag));
       player.setPosition(flag.yLocation, flag.xLocation, grid);
       flag.handleCollision(player, grid);
        assertEquals(false, flagtile.getGameObjects().contains(flag));





        variables.stop();
    }
}

