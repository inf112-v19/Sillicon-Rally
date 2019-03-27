package inf112.skeleton.app.GameObjectTests;


import com.badlogic.gdx.Game;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.TurnGearObject;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.game.RoboGame;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class
TurnGearTest {
    private TileGrid grid;
    private Player player;
    private int TILE_SIZE_IN_PIX;
    private TurnGearObject turnGear;


    public void setup() {
        SetupVariables setupVariable = new SetupVariables();
        grid = setupVariable.grid;
        player = setupVariable.player;
        TILE_SIZE_IN_PIX = setupVariable.gameMap.getTileSize();
        player = new Player();
        player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
        player.currentDirection = RoboGame.Direction.West;
    }

    @Test
    void turnGearClockwiseTest() {
        setup();
        turnGear = new TurnGearObject(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid, TurnGearObject.RotateDirection.CLOCKWISE);

        turnGear.handleCollision(player, grid);
        assertEquals(RoboGame.Direction.North, player.currentDirection);
    }

    @Test
    void turnGearCounterClockwiseTest(){
        setup();
        player = new Player();
        turnGear = new TurnGearObject(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid, TurnGearObject.RotateDirection.COUNTER_CLOCKWISE);
        turnGear.handleCollision(player, grid);
        assertEquals(RoboGame.Direction.South, player.currentDirection);
    }
}
