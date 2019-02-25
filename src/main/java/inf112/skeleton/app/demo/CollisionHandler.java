package inf112.skeleton.app.demo;

import com.badlogic.gdx.graphics.g2d.Sprite;
import group1.team2.src.main.java.inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.Tile;

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
        List spritesOnTile = playerTile.getSprites();
        System.out.println(spritesOnTile);
        for (int i = 0; i < spritesOnTile.size(); i++) {
            if (spritesOnTile.get(i).equals(player))
                continue;
            if (spritesOnTile.get(i) instanceof TeleportObstacle) {
                ((TeleportObstacle) spritesOnTile.get(i)).handleCollision(player);
            }
        }

    }
}
