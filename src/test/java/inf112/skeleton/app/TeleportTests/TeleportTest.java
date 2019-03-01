package inf112.skeleton.app.TeleportTests;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.collision.objects.TeleportObstacle;
import inf112.skeleton.app.game.Game;
import org.junit.Test;
import org.mockito.Mockito;

public class TeleportTest {

    public void setup(Game game) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Silion_Rally";
        cfg.width = 1080;
        cfg.height = 720;

        new LwjglApplication(game, cfg);
    }

    @Test
    public void teleportTest() {
        Game game = new Game();
        game.makeGrid();

    }
}
