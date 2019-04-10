package inf112.skeleton.app.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class LaserAnimation implements IGameObject {
    Sprite laserBeam;
    int laserTimer;

    public LaserAnimation() {
        Texture texture = new Texture("texture_laser_cutout.png");
        this.laserBeam = new Sprite(texture);
        this.laserBeam.setX(0);
        this.laserBeam.setY(0);

        laserBeam.setSize(100,500);
    }

    public void animateLaser(Player player) {
        if (laserTimer > 1) {
            laserTimer++;
            return;
        }

        float playerY = player.getY();
        float playerX = player.getX();

        laserBeam.setY(playerY);
        laserBeam.setX(playerX);
        laserBeam.setOrigin(50,50);

        setLaserDirection(player);

        player.grid.getTile(0,0).addGameObject(this);
        laserTimer++;

    }

    private void setLaserDirection(Player player) {
        laserBeam.setRotation(player.getSprite().getRotation());
    }

    /*
    public void removeLaser(Player player) {
        if (laserTimer == 100) {
            player.grid.getTileFromCoordinates(0, 0).getGameObjects().remove(this);
            laserTimer = 0;
        }
    }
    */

    public boolean removeLaser(Player player) {
        player.grid.getTileFromCoordinates(0, 0).getGameObjects().remove(this);
        laserTimer = 0;
        return true;

    }

    @Override
    public Sprite getSprite() {
        return laserBeam;
    }

    @Override
    public void handleCollision(Player player, TileGrid grid) {

    }
}
