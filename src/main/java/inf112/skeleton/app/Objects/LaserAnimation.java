package inf112.skeleton.app.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class LaserAnimation implements IGameObject {
    RoboGame game;
    Sprite laserBeam;

    public LaserAnimation(RoboGame game) {
        this.game = game;
        Texture texture = new Texture("car.jpg");
        this.laserBeam = new Sprite(texture);
        this.laserBeam.setX(0);
        this.laserBeam.setY(0);
    }

    public void animateLaser(Player player) {
        float playerY = player.getY();
        float playerX = player.getX();

        laserBeam.setY(playerY);
        laserBeam.setX(playerX);

        Tile playerTile = game.grid.getTileFromCoordinates(playerY, playerX);
        playerTile.addGameObject(this);
    }

    public void removeLaser() {
        game.grid.getTileFromCoordinates(laserBeam.getY(),laserBeam.getX()).getGameObjects().remove(this);
    }

    @Override
    public Sprite getSprite() {
        return laserBeam;
    }

    @Override
    public void handleCollision(Player player, TileGrid grid) {

    }
}
