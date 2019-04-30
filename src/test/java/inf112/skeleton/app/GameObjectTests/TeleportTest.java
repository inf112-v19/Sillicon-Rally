package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.TeleportObstacle;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Tile teleportTile = grid.getTileFromCoordinates(teleport.xTeleportToInPx, teleport.yTeleportFromInPx);
        assertTrue(teleportTile.getGameObjects().contains(teleport));
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
