package inf112.skeleton.app.GridTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.game.Game;

import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {

    Game.Direction startDirection = Game.Direction.West;
    Player player;
    TileGrid grid;
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

}
