package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class ConveyorObjectNorth implements IGameObject {
    private int speed;
    public int yLocation;
    public int xLocation;
    Tile conveyorTile;
    Game.Direction originDirection;
    Game.Direction temporaryDirection;

    public ConveyorObjectNorth(RectangleMapObject conveyorFromTile, TileGrid grid, int speed){
        this.speed = speed;
        yLocation = (int) conveyorFromTile.getRectangle().getY();
        xLocation = (int) conveyorFromTile.getRectangle().getX();

        conveyorTile = grid.getTileFromCoordinates(yLocation, xLocation);
        conveyorTile.addGameObject(this);
    }

    public void handleCollision(Player player, TileGrid grid){
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        int moveDistance = grid.tileSizeInPx;

        //turns player, still keeps track of original direction to reset player direction after action
        Game.Direction originDirection = player.currentDirection;
        temporaryDirection = Game.Direction.North;
        player.currentDirection = temporaryDirection;
        player.moveStraight(speed, moveDistance, grid);
        player.currentDirection = originDirection;
    }




    @Override
    public Sprite getSprite() {
        return null;
    }
}
