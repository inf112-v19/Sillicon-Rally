package inf112.skeleton.app.collision.objects;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IDamageDealer;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class PitObject implements IGameObject, IDamageDealer {
    public int xLocation;
    public int yLocation;
    private Tile pitTile;


    public PitObject(RectangleMapObject pitFromTiled, TileGrid grid){
        xLocation = (int) pitFromTiled.getRectangle().getX();
        yLocation = (int) pitFromTiled.getRectangle().getY();

        pitTile = grid.getTileFromCoordinates(yLocation, xLocation);
        pitTile.addGameObject(this);
    }


    public void handleCollision(Player player, TileGrid grid) {
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        if (pitTile.equals(playerTile)) {
            dealDamage(player, grid);
        }
    }


    public void dealDamage(Player player, TileGrid grid) {
        // Falling in pit is instant death
        player.handleDeath(grid);
    }


    @Override
    public Sprite getSprite() {
        return null;
    }
}
