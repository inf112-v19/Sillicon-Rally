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
    public final int REPAIR_REGENERATE = 2;
    private Sprite sprite;
    private Tile repairTile;

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
            healDamage(player);
        }
    }


    public void healDamage(Player player){
        player.playerHP += REPAIR_REGENERATE;
        System.out.println(player.playerHP);
        if(player.playerHP>player.MAX_HP){
            player.playerHP=player.MAX_HP;
        }
    }

    @Override
    public Sprite getSprite() {
        sprite.setX(xLocation);
        sprite.setY(yLocation);

        return sprite;
    }




    /*
    For Testing
     */

    public RepairObject(int y, int x, TileGrid grid){
        this.yLocation = y;
        this.xLocation = x;
        repairTile = grid.getTileFromCoordinates(yLocation, xLocation);
        repairTile.addGameObject(this);

    }
}
