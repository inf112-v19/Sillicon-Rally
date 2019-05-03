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
    private Tile laserTile;

    public int xLocation;
    public int yLocation;
    public final int LASER_DAMAGE = 1;



    public LaserObject(RectangleMapObject laserFromTiled, TileGrid grid){
        xLocation = (int) laserFromTiled.getRectangle().getX();
        yLocation = (int) laserFromTiled.getRectangle().getY();

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
        return null;
    }

    @Override
    public void dealDamage(Player player, TileGrid grid) {
        player.damagePlayer(LASER_DAMAGE, grid);
    }





    /*
        For testing
     */
    public LaserObject(int y, int x, TileGrid grid){
        this.yLocation = y;
        this.xLocation = x;
        laserTile = grid.getTileFromCoordinates(yLocation, xLocation);
        laserTile.addGameObject(this);
    }
}