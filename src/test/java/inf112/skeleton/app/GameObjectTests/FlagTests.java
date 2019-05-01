
package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.collision.objects.FlagObject;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;


public class FlagTests {
    TileGrid grid;
    FlagObject flag;
    Player player;
    int TILE_SIZE_IN_PX;
    SetupVariables variables;
    int initialFlagsTouched;

    public void setup() {
        variables = new SetupVariables();
        grid = variables.grid;
        flag = variables.flag;
        player = variables.player;
        initialFlagsTouched = player.flagNr;
        TILE_SIZE_IN_PX = variables.gameMap.getTileSize();
    }

    @Test
    public void playerCanTouchFlag(){
        setup();
        flag.flagNumber = 1;
        Player player = variables.player;

        //int initialFlagsTouched = player.flagNr;
        player.setPosition(flag.yLocation, flag.xLocation, grid);
        flag.handleCollision(player,grid);
        assertEquals(initialFlagsTouched+1, player.flagNr);
    }

    @Test
   public void NotRemovedWhenTouchedTest(){
        setup();
        player.setPosition(flag.yLocation, flag.xLocation, grid);
        Tile flagtile = grid.getTileFromCoordinates(flag.yLocation, flag.xLocation);
        flag.handleCollision(player, grid);

        assertEquals(true, flagtile.getGameObjects().contains(flag));
        variables.stop();
    }


    @Test
    public void mustPickUpFlagTwoBeforeFlagOneTest(){
        setup();
        Player player = variables.player;
        FlagObject flag = variables.flag;
        flag.flagNumber = 2;
        player.setPosition(flag.yLocation, flag.xLocation, grid);
        assertEquals(initialFlagsTouched, player.flagNr);
        variables.stop();
    }

    @Test
    public void canPickUpFlagTwoAfterFlagOneTest(){
        setup();
        Player player = variables.player;
        FlagObject flag1 = variables.flag;
        FlagObject flag2 = variables.flag2;
        flag1.flagNumber = 1;
        flag2.flagNumber = 2;

        player.setPosition(flag1.yLocation, flag2.xLocation,grid);
        flag.handleCollision(player,grid);
        player.setPosition(flag2.yLocation, flag2.xLocation, grid);
        flag2.handleCollision(player,grid);
        assertEquals(initialFlagsTouched + 2, player.flagNr);
        variables.stop();
    }

    @Test
    public void presentsFirstFlagToPickUp(){
        setup();
        flag.handleCollision(player,grid);
        String string = flag.presentNextFlag(player);
        assertEquals("player needs flagNumber nr: 1", string);
    }

    @Test
    public void presentsNextFlagToPickUp(){
        setup();
        player.setPosition(flag.yLocation, flag.xLocation,grid);
        flag.flagNumber = 1;
        flag.handleCollision(player,grid);
        String string = flag.presentNextFlag(player);
        assertEquals("player needs flagNumber nr: 2", string);
    }

    @Test
    public void displayAllFlagsPickedUp(){
        setup();
        flag.flagNumber = flag.maxFlags;
        player.flagNr = 4;
        player.setPosition(flag.yLocation, flag.xLocation,grid);
        flag.handleCollision(player,grid);
        String string = flag.presentNextFlag(player);
        assertEquals("you win", string);
    }



    @Test
    void flagPresentOnMapTest(){
        SetupVariables variables = new SetupVariables();
        TileGrid grid = variables.grid;
        FlagObject flag = variables.flag;
        Player player = variables.player;
        Tile flagtile = grid.getTileFromCoordinates(flag.yLocation, flag.xLocation);
        assertEquals(true, flagtile.getGameObjects().contains(flag));
        variables.stop();
    }
}

