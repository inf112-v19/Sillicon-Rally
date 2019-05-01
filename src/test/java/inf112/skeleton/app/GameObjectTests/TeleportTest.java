package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.Objects.iPlayer;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.TeleportObstacle;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeleportTest {
    SetupVariables variables;
    private Player player;
    private TileGrid grid;
    private TeleportObstacle teleport;

    private void setup(){
        variables = new SetupVariables();
        grid = variables.grid;
        teleport = variables.teleport;
        player = variables.player;
    }


    @Test
    void teleportPresentOnMapTest(){
        setup();
        teleport = new TeleportObstacle(0,0,2,2,grid);
        Tile teleportTile = grid.getTileFromCoordinates(teleport.yTeleportFromInPx,teleport.xTeleportFromInPx);
        assertTrue(teleportTile.getGameObjects().contains(teleport));
    }

    @Test
    void removesPlayerAtRightLocationTest(){
        setup();
        teleport = new TeleportObstacle(0,0,2,2,grid);
        Tile teleportFromTile = grid.getTileFromCoordinates(teleport.yTeleportFromInPx, teleport.xTeleportFromInPx);
        player.setPosition(teleport.yTeleportFromInPx, teleport.xTeleportFromInPx,grid);
        teleport.handleCollision(player,grid);
        assertFalse(teleportFromTile.getX() == (int) (player.getX()));
        assertFalse(teleportFromTile.getY() == (int) (player.getY()));
    }

    @Test
    void addsPlayerAtRightLocationTest(){
        setup();
        teleport = new TeleportObstacle(0,0,2,2,grid);
        Tile teleportToTile = grid.getTileFromCoordinates(teleport.yTeleportToInPx, teleport.xTeleportToInPx);
        player.setPosition(teleport.yTeleportFromInPx, teleport.xTeleportFromInPx, grid);
        teleport.handleCollision(player,grid);
        assertTrue(teleportToTile.getGameObjects().contains(player));
    }



    @Test
    public void teleportTest() {
        setup();
        //Put player at the teleportFrom position and make the teleporter do it's magid
        player.setPosition(teleport.xTeleportFromInPx, teleport.yTeleportFromInPx, grid);
        teleport.handleCollision(player, grid);


        //Verify that the player has been moved to the teleportTo location.
        Tile teleportTile = grid.getTileFromCoordinates(teleport.yTeleportToInPx, teleport.xTeleportToInPx);
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());

        assertEquals(true, teleportTile.equals(playerTile));
        variables.stop();
    }

}
