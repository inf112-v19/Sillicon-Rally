package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IDamageDealer;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

/**
 * Created by Jørgen
 */

public class LaserObject implements IGameObject, IDamageDealer {

    public int xLocation;
    public int yLocation;
    public final int LASER_DAMAGE = 1;
    Sprite sprite;
    Tile laserTile;

    public LaserObject(RectangleMapObject laserFromTiled, TileGrid grid){
        xLocation = (int) laserFromTiled.getRectangle().getX();
        yLocation = (int) laserFromTiled.getRectangle().getY();

        Texture texture = new Texture("TileSprites/tile-laser-1-start.png");
        sprite = new Sprite(texture);
        sprite.setSize(0, 0); //hiding sprite, not having a sprite throws error.

        laserTile = grid.getTileFromCoordinates(yLocation, xLocation);
        laserTile.addGameObject(this);
    }

    public void handleCollision(Player player, TileGrid grid) {
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());

        if (laserTile.equals(playerTile)) {
            dealDamage(player, grid);
        }
    }

    @Override
    public Sprite getSprite() {
        sprite.setX(xLocation);
        sprite.setY(yLocation);

        return sprite;
    }

    @Override
    public void dealDamage(Player player, TileGrid grid) {
        player.damagePlayer(LASER_DAMAGE, grid);
    }

    //for testing
    public LaserObject(int y, int x, TileGrid grid){
        this.yLocation = y;
        this.xLocation = x;
        laserTile = grid.getTileFromCoordinates(yLocation, xLocation);
        laserTile.addGameObject(this);
    }
}