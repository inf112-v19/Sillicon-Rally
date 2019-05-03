package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class RepairObject implements IGameObject {
    private Tile repairTile;
    public int xLocation;
    public int yLocation;
    public final int REPAIR_REGENERATE = 2;


    public RepairObject(RectangleMapObject repairFromTiled, TileGrid grid){
        xLocation = (int) repairFromTiled.getRectangle().getX();
        yLocation = (int) repairFromTiled.getRectangle().getY();

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
        return null;
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
