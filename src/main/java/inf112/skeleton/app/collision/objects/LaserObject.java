package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class LaserObject implements IGameObject {
    public String Orientation;
    public int xLocation;
    public int yLocation;
    Sprite sprite;
    Tile laserTile;

    public LaserObject( RectangleMapObject laserFromTiled, TileGrid grid){
        yLocation = (int) laserFromTiled.getRectangle().getY();
        xLocation = (int) laserFromTiled.getRectangle().getX();
        Texture texture = new Texture(TODO);
        sprite = new Sprite(texture);

        laserTile = grid.getTileFromCoordinates(xLocation, yLocation);
        laserTile.addGameObject(this);
    }
}
