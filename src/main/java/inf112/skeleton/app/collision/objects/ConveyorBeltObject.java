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
    private Tile conveyorTile;
    public RoboGame.Direction direction;


    public ConveyorBeltObject(RectangleMapObject conveyorFromTile, TileGrid grid){
        speed = getConveyorSpeed(conveyorFromTile);
        yLocation = (int) conveyorFromTile.getRectangle().getY();
        xLocation = (int) conveyorFromTile.getRectangle().getX();
        direction = getDirection(conveyorFromTile);

        conveyorTile = grid.getTileFromCoordinates(yLocation, xLocation);
        conveyorTile.addGameObject(this);
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
        int beltspeed = 0;
        switch (speedProperty){
            case "1" : return beltspeed = 1;
            case "2" : return beltspeed= 2;
        }
        return beltspeed;
    }



    public void handleCollision(Player player, TileGrid grid){
        int moveDistance = grid.tileSizeInPx;
        boolean notAnotherConveyor=false;

        RoboGame.Direction originalDirection = player.getDirection();
        player.setDirection(direction);

        player.moveStraight(speed, moveDistance, grid);
        player.setDirection(originalDirection);

        if(player.getDirection() == RoboGame.Direction.North){
            if(grid.getTileFromCoordinates(player.getY()+1, player.getX()).equals(conveyorTile)){
                notAnotherConveyor=false;
            }
        }
        else if(player.getDirection() == RoboGame.Direction.South){
            if(grid.getTileFromCoordinates(player.getY()-1, player.getX()).equals(conveyorTile)){
                notAnotherConveyor=false;
            }
        }
        else if(player.getDirection() == RoboGame.Direction.East){
            if(grid.getTileFromCoordinates(player.getY(), player.getX()+1).equals(conveyorTile)){
                notAnotherConveyor=false;
            }
        }
        else if(player.getDirection() == RoboGame.Direction.West){
            if(grid.getTileFromCoordinates(player.getY(), player.getX()-1).equals(conveyorTile)){
                notAnotherConveyor=false;
            }
        }

        if(notAnotherConveyor){
            player.checkCollision(grid);
        }
    }



    @Override
    public Sprite getSprite() {
        return null;
    }




   /*
        For testing
    */
    public ConveyorBeltObject(int y, int x, TileGrid grid, int speed, RoboGame.Direction dir){
        this.yLocation = y;
        this.xLocation = x;
        conveyorTile = grid.getTileFromCoordinates(yLocation, xLocation);
        conveyorTile.addGameObject(this);
        this.direction = dir;
        this.speed = speed;
    }
}
