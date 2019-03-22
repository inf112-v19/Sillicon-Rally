package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class RepairObject implements IGameObject {

    public int xLocation;
    public int yLocation;
    Sprite sprite;
    Tile repairTile;

    public RepairObject(RectangleMapObject repairFromTiled, TileGrid grid){
        xLocation = (int) repairFromTiled.getRectangle().getX();
        yLocation = (int) repairFromTiled.getRectangle().getY();

        Texture texture = new Texture("TileSprites/tile-laser-1-start.png");
        sprite = new Sprite(texture);
        sprite.setSize(0, 0); //hiding sprite, not having a sprite throws error.

        repairTile = grid.getTileFromCoordinates(yLocation, xLocation);
        repairTile.addGameObject(this);
    }

    public void handleCollision(Player player, TileGrid grid) {
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());

        if (repairTile.equals(playerTile)) {
            player.healDmg(2);
        }
    }

    @Override
    public Sprite getSprite() {
        sprite.setX(xLocation);
        sprite.setY(yLocation);

        return sprite;
    }
}
