package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class ConveyerObject implements IGameObject {
    private int speed;
    Sprite sprite;
    public int yLocation;
    public int xLocation;
    Tile conveyerTile;
    boolean hasJustMovedPlayer;

    public ConveyerObject(RectangleMapObject conveyerFromTile, TileGrid grid, int speed){
        this.speed = speed;
        yLocation = (int) conveyerFromTile.getRectangle().getY();
        xLocation = (int) conveyerFromTile.getRectangle().getX();

        conveyerTile = grid.getTileFromCoordinates(yLocation, xLocation);
        conveyerTile.addGameObject(this);
    }

    public void handleCollision(Player player, TileGrid grid){
            Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());
            int moveDistance = grid.tileSizeInPx;

            player.moveStraight(speed, moveDistance, grid);

    }

    @Override
    public Sprite getSprite() {
        return null;
    }


}
