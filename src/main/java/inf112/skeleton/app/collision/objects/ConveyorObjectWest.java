package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class ConveyorObjectWest implements IGameObject {
    private int speed;
    public int yLocation;
    public int xLocation;
    Tile conveyorTile;
    Game.Direction temporaryDirection = Game.Direction.West;


    public ConveyorObjectWest(RectangleMapObject conveyerFromTile, TileGrid grid, int speed){
        this.speed = speed;
        yLocation = (int) conveyerFromTile.getRectangle().getY();
        xLocation = (int) conveyerFromTile.getRectangle().getX();

        conveyorTile = grid.getTileFromCoordinates(yLocation, xLocation);
        conveyorTile.addGameObject(this);
    }

    public void handleCollision(Player player, TileGrid grid){
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        int moveDistance = grid.tileSizeInPx;

        //turns player, still keeps track of original direction to reset player direction after action
        Game.Direction originDirection = player.currentDirection;
        player.currentDirection = temporaryDirection;
        player.moveStraight(speed, moveDistance, grid);
        player.currentDirection = originDirection;

    }

    @Override
    public Sprite getSprite() {
        return null;
    }



}
