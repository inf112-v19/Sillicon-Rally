package inf112.skeleton.app.collision.objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class PitObject implements IGameObject {

    public int xLocation;
    public int yLocation;
    public final int PIT_DAMAGE = 2;
    Sprite sprite;
    Tile pitTile;

    public PitObject(RectangleMapObject pitFromTiled, TileGrid grid){
        xLocation = (int) pitFromTiled.getRectangle().getX();
        yLocation = (int) pitFromTiled.getRectangle().getY();

        Texture texture = new Texture("TileSprites/tile-hole.png");
        sprite = new Sprite(texture);
        sprite.setSize(0, 0); //hiding sprite, not having a sprite throws error.

        pitTile = grid.getTileFromCoordinates(yLocation, xLocation);
        pitTile.addGameObject(this);
    }

    public void handleCollision(Player player, TileGrid grid) {
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());

        if (pitTile.equals(playerTile)) {
            effectOfPit(player);
            if (player.playerHP <= 0)
                player.handleDeath(grid);
        }
    }

    public void effectOfPit(Player player){
        player.playerHP -= PIT_DAMAGE;
    }

    @Override
    public Sprite getSprite() {
        sprite.setX(xLocation);
        sprite.setY(yLocation);

        return sprite;
    }
}
