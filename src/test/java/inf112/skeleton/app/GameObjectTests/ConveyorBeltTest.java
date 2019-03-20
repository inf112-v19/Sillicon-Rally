package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.ConveyorBeltObject;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ConveyorBeltTest {
private TileGrid grid;
private Player player;
private int TILE_SIZE_IN_PIX;
private ConveyorBeltObject belt;

   public void setup(){
       SetupVariables setupVariable = new SetupVariables();
       grid = setupVariable.grid;
       player = setupVariable.player;
       TILE_SIZE_IN_PIX = setupVariable.gameMap.getTileSize();
   }


   @Test
    void conveyorMoveWestTest(){
       setup();
       player = new Player();
       player.setPosition(2 * TILE_SIZE_IN_PIX, 2 *TILE_SIZE_IN_PIX, grid);
       belt = new ConveyorBeltObject(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid, 1, Game.Direction.West);
       belt.handleCollision(player,grid);
       assertEquals(1 * TILE_SIZE_IN_PIX, (int) (player.getX()));
   }
    @Test
    void conveyorMoveEastTest(){
        setup();
        player = new Player();
        player.setPosition(2 * TILE_SIZE_IN_PIX, 2 *TILE_SIZE_IN_PIX, grid);
        belt = new ConveyorBeltObject(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid, 1, Game.Direction.East);
        belt.handleCollision(player,grid);
        assertEquals(3 * TILE_SIZE_IN_PIX, (int) (player.getX()));
    }


    @Test
    void conveyorMoveNorthTest(){
        setup();
        player = new Player();
        player.setPosition(2 * TILE_SIZE_IN_PIX, 2 *TILE_SIZE_IN_PIX, grid);
        belt = new ConveyorBeltObject(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid, 1, Game.Direction.North);
        belt.handleCollision(player,grid);
        assertEquals(3 * TILE_SIZE_IN_PIX, (int) (player.getY()));
    }

    @Test
    void conveyorMoveSouthTest(){
        setup();
        player = new Player();
        player.setPosition(2 * TILE_SIZE_IN_PIX, 2 *TILE_SIZE_IN_PIX, grid);
        belt = new ConveyorBeltObject(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid, 1, Game.Direction.South);
        belt.handleCollision(player,grid);
        assertEquals(1 * TILE_SIZE_IN_PIX, (int) (player.getY()));
    }


    @Test
    void conveyorDoesNotMovePlayerOutOfMap(){
       setup();
       player = new Player();
       player.setPosition(0,0,grid);
       belt = new ConveyorBeltObject(0,0, grid, 1, Game.Direction.West);
       belt.handleCollision(player,grid);
       assertEquals(0, (int) (player.getX()));
    }

}