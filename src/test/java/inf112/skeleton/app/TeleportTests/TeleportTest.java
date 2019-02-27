package inf112.skeleton.app.TeleportTests;

import inf112.skeleton.app.collision.objects.TeleportObstacle;
import inf112.skeleton.app.game.Game;
import org.junit.Test;

public class TeleportTest {

    @Test
    public void teleportTest() {
        Game game = new Game();
        TeleportObstacle teleport = new TeleportObstacle(game);
    }
}
