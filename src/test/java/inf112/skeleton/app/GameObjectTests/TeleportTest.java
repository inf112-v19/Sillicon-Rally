package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.TeleportObstacle;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeleportTest {
    @Test
    public void teleportTest() {
        SetupVariables variables = new SetupVariables();
        TileGrid grid = variables.grid;
        TeleportObstacle teleport = variables.teleport;
        Player player = variables.player;

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
