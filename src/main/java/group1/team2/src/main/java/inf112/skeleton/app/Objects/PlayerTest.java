/**package group1.team2.src.main.java.inf112.skeleton.app.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Tile;
import inf112.skeleton.app.demo.Game;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {
    final int TILE_SIZE_IN_PX = 128;
    Game game;

    Game.Direction startDirection = Game.Direction.West;
    Player player;

    public void setup() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Silion_Rally";
        cfg.width = 1080;
        cfg.height = 720;

        //new LwjglApplication(new HelloWorld(), cfg);
        //new LwjglApplication(new TiledTest(), cfg);

        new LwjglApplication(new Game(), cfg);

        Texture texture = new Texture(Gdx.files.internal("car.jpg"));
        player = new Player(texture, startDirection);
        startDirection = Game.Direction.East;
        player.setPosition(0,0);
        game.grid.getTile(0,0).addSprite(player);
    }

    @Test
    public void playerTest() {


    }

    @Test
    public void updatePlayerPosInGridTest() {
        setup();
        Tile currentTile = game.grid.getTileFromCoordinates(player.getY(), player.getX());
        player.moveForward(2, TILE_SIZE_IN_PX, game, currentTile);

        List list = game.grid.getTile(0,0).getSprites();

        assertEquals(0, list.size());
    }

}
*/