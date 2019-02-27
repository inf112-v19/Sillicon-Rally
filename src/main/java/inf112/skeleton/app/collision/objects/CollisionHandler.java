package inf112.skeleton.app.collision.objects;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.demo.Savestate;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.game.Game;

import java.util.List;

public class CollisionHandler {
    Game game;
    Player player;

    public CollisionHandler(Game game) {
        this.game = game;
        player = game.player;
    }

    public void checkCollision() {
        Tile playerTile = game.grid.getTileFromCoordinates(player.getY(), player.getX());
        List spritesOnTile = playerTile.getGameObjects();
        System.out.println(spritesOnTile);
        for (int i = 0; i < spritesOnTile.size(); i++) {
            if (spritesOnTile.get(i).equals(player))
                continue;
            if (spritesOnTile.get(i) instanceof TeleportObstacle) {
                ((TeleportObstacle) spritesOnTile.get(i)).handleTeleportCollision(player);
            }
            if(spritesOnTile.get(i) instanceof Savestate){
                ((Savestate) spritesOnTile.get(i)).handleCollision(player);
            }
        }

    }
}
