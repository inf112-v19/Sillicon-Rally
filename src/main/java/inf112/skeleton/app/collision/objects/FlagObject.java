package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class FlagObject implements IGameObject {
    Sprite sprite;
    int yLocation;
    int xLocation;

    public FlagObject(RectangleMapObject flagFromTiled, TileGrid grid) {
        yLocation = (int) flagFromTiled.getRectangle().getY();
        xLocation = (int) flagFromTiled.getRectangle().getX();
        Texture texture = new Texture("sprites/flag2.png");
        sprite = new Sprite(texture);
        sprite.setSize(100,100);

        grid.getTileFromCoordinates(yLocation, xLocation).addGameObject(this);
    }

    public void handleCollision(Player player, TileGrid grid) {
        Tile flagTile = grid.getTileFromCoordinates(yLocation, xLocation);
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());

        if (flagTile.equals(playerTile)) {
            player.setBackupLocation(flagTile);
            removeFlagFromMap(grid);
        }
    }


    @Override
    public Sprite getSprite() {
        sprite.setY(yLocation);
        sprite.setX(xLocation);

        return sprite;
    }

    public void removeFlagFromMap(TileGrid grid) {
        this.sprite = null;

        Tile myTile = grid.getTileFromCoordinates(this.yLocation, this.xLocation);
        myTile.getGameObjects().remove(this);
    }
}
