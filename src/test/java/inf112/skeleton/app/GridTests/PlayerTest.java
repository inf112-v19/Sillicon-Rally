package inf112.skeleton.app.GridTests;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.game.Game;

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
        cfg.title = "Silicon_Rally";
        cfg.width = 1080;
        cfg.height = 720;

        new LwjglApplication(new Game(), cfg);

        Texture texture = new Texture(512, 512, Pixmap.Format.RGB888);
        player = new Player(texture, startDirection);
        startDirection = Game.Direction.East;
        player.setPosition(0,0, new Game());
        game.grid.getTile(0,0).addGameObject(player);
    }


    @Test
    public void setPositionTest() {
        Game game = new Game();
        game.player.setPosition(game.TILE_SIZE_IN_PX, game.TILE_SIZE_IN_PX, game);
        System.out.println(game.grid.getTile(1,1).toString());

    }

}
