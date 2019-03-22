package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class TurnGearObject implements IGameObject {
    public int yLocation;
    public int xLocation;
    Tile turnGearTile;
    RotateDirection rotateDirection;


    public TurnGearObject(RectangleMapObject turnGearObjectFromTiled, TileGrid grid){
        yLocation = (int) turnGearObjectFromTiled.getRectangle().getY();
        xLocation = (int) turnGearObjectFromTiled.getRectangle().getX();

        rotateDirection = getRotateDirection(turnGearObjectFromTiled);
        turnGearTile = grid.getTileFromCoordinates(yLocation, xLocation);
        turnGearTile.addGameObject(this);
    }
    //for testing
    public TurnGearObject(int y, int x, TileGrid grid, RotateDirection direction){
        this.yLocation = y;
        this.xLocation = x;
        turnGearTile = grid.getTileFromCoordinates(yLocation, xLocation);
        turnGearTile.addGameObject(this);
        this.rotateDirection = direction;
    }

    public RotateDirection getRotateDirection(RectangleMapObject turnGearFromTiled) {
        String turnDirectionProperty = (String) turnGearFromTiled.getProperties().get("rotationDirection");

        switch (turnDirectionProperty){
            case "counterClockwise": return RotateDirection.COUNTER_CLOCKWISE;
            case "clockwise": return RotateDirection.CLOCKWISE;
        }
        return rotateDirection;
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public void handleCollision(Player player, TileGrid grid) {
        if (rotateDirection.equals(RotateDirection.CLOCKWISE))
            player.rotateClockwise();
        if (rotateDirection.equals(RotateDirection.COUNTER_CLOCKWISE))
            player.rotateCounterClockwise();
    }


    public enum RotateDirection{
        CLOCKWISE, COUNTER_CLOCKWISE
    }
}
