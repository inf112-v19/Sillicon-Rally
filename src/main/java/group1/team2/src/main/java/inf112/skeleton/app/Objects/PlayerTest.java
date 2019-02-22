package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import group1.team2.src.main.java.inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.demo.Game;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {
    final int TILE_SIZE_IN_PX = 128;
    Game game = new Game();
    Texture texture = new Texture(Gdx.files.internal("core/assets/car.jpg"));

    Game.Direction startDirection = Game.Direction.West;
    Player player = new Player(texture, startDirection);

    public void setup() {
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
