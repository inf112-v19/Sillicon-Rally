package inf112.skeleton.app.collision.objects;

import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.demo.Savestate;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

import java.util.List;

public class CollisionHandler {
    Player player;
    TileGrid grid;

    public CollisionHandler(TileGrid grid, Player player) {
        this.player = player;
        this.grid = grid;
    }

    public void checkCollision() {
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        List<IGameObject> spritesOnTile = playerTile.getGameObjects();
        System.out.println(spritesOnTile);

        for (IGameObject object : spritesOnTile) {
            object.handleCollision(player, grid);
        }

    }
}
