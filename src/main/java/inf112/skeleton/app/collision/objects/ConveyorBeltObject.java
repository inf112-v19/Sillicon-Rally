package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;


public class ConveyorBeltObject implements IGameObject {
    public int speed;
    public int yLocation;
    public int xLocation;
    Tile conveyorTile;
    public RoboGame.Direction direction;

    public ConveyorBeltObject(RectangleMapObject conveyorFromTile, TileGrid grid){
        speed = getConveyorSpeed(conveyorFromTile);
        yLocation = (int) conveyorFromTile.getRectangle().getY();
        xLocation = (int) conveyorFromTile.getRectangle().getX();
        //System.out.println(conveyorFromTile.getProperties().get("east"));

        //getDirection(conveyorFromTile);
        direction = getDirection(conveyorFromTile);

        conveyorTile = grid.getTileFromCoordinates(yLocation, xLocation);
        conveyorTile.addGameObject(this);
    }

    //For testing only
    public ConveyorBeltObject(int y, int x, TileGrid grid, int speed, RoboGame.Direction dir){
        this.yLocation = y;
        this.xLocation = x;
        conveyorTile = grid.getTileFromCoordinates(yLocation, xLocation);
        conveyorTile.addGameObject(this);
        this.direction = dir;
        this.speed = speed;
    }



    public RoboGame.Direction getDirection(RectangleMapObject conveyorFromTile) {

        String directionProperty = (String) conveyorFromTile.getProperties().get("direction");

        switch (directionProperty) {
            case "north": return RoboGame.Direction.North;
            case "east": return RoboGame.Direction.East;
            case "south": return RoboGame.Direction.South;
            case "west": return RoboGame.Direction.West;
        }

        return null;
    }



    public int getConveyorSpeed(RectangleMapObject conveyorFromTile){
        String speedProperty = (String) conveyorFromTile.getProperties().get("speed");
        int integer = 0;
        switch (speedProperty){
            case "1" : return integer = 1;
            case "2" : return integer= 2;
        }
        return integer;
    }



    public void handleCollision(Player player, TileGrid grid){
        int moveDistance = grid.tileSizeInPx;

        RoboGame.Direction originalDirection = player.currentDirection;
        player.currentDirection = direction;

        player.moveStraight(speed, moveDistance, grid);
        player.currentDirection = originalDirection;
    }



    @Override
    public Sprite getSprite() {
        return null;
    }
}
