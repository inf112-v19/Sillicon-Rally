package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import jdk.internal.util.xml.impl.Input;


public class PlayerTest {

    Player player;
    TileGrid grid;
    RoboGame roboGame;
    int TILE_SIZE_IN_PX;


    public void setup() {
        SetupVariables variables = new SetupVariables();
        grid = variables.grid;
        player = variables.player;
        TILE_SIZE_IN_PX = variables.gameMap.getTileSize();
    }

    @Test
    public void setPositionTest() {
        setup();
        player.setPosition(TILE_SIZE_IN_PX *2, TILE_SIZE_IN_PX*1, grid);

        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        Tile checkTile = grid.getTile(2, 1);

        assertEquals(true, playerTile.equals(checkTile));
    }

    @Test
    public void moveStraightTest() {
        setup();
        player = new Player();
        player.setPosition(2 * TILE_SIZE_IN_PX,2* TILE_SIZE_IN_PX,grid);
        player.moveStraight(1, grid.tileSizeInPx, grid);
        assertEquals(1 * TILE_SIZE_IN_PX, player.getX());
    }

    @Test
    void createPlayerTest(){
        setup();
        player = new Player();
        player.setPosition(2* TILE_SIZE_IN_PX,2 * TILE_SIZE_IN_PX, grid);
        Tile testTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        assertEquals(RoboGame.Direction.West, player.currentDirection);
        assertEquals(true, testTile.getGameObjects().contains(player));
    }


    @Test
    public void updateXPixelPositionTest() {
        setup();
        player.setX(135);
        assertEquals(135, player.getX());
    }

    @Test
    public void updateYPixelPositionTest() {
        setup();
        player.setY(135);
        assertEquals(135, player.getY());
    }

    @Test
    public void checkIfMoveIsOutOfBoundsTest() {
        int max = Integer.MAX_VALUE;
        setup();
        assertEquals(true, player.checkIfMoveIsOutOfBounds(-1, -1, grid));
        assertEquals(true, player.checkIfMoveIsOutOfBounds(max, max, grid));
    }


}
