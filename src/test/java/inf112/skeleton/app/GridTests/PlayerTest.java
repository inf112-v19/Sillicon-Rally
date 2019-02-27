package inf112.skeleton.app.GridTests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.collision.objects.TeleportObstacle;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.game.Game;

import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {

    Game.Direction startDirection = Game.Direction.West;
    Player player;
    Game game;
    TileGrid grid;
    Tile tile;
    int target = 128;

    public void setup() {
        game = new Game();
        grid = new TileGrid(8, 10, target);
        player = new Player();
        tile = new Tile(1, 1, target);
        player.setPosition(target, target, grid);

    }

    @Test
    public void origoPositionTest() {
        setup();
        player.setPosition(0, 0, grid);
        tile = new Tile(0, 0, 128);
        assertEquals(true, tile.equals(grid.getTileFromCoordinates(player.getY(), player.getX())));
    }

    @Test
    public void updatesTilePositionTest() {
        setup();
        assertEquals(true, tile.equals(grid.getTileFromCoordinates(player.getY(), player.getX())));
    }


    @Test
    public void updateXPixelPositionTest() {
        setup();
        assertEquals(target, player.getX());
    }

    @Test
    public void updateYPixelPositionTest() {
        setup();
        assertEquals(128, player.getY());
    }

}
