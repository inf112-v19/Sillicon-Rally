package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

public class TeleportObstacle implements IGameObject {
    public int xTeleportFromInPx, yTeleportFromInPx;
    public int xTeleportToInPx, yTeleportToInPx;
    GameMap map;


    public TeleportObstacle(GameMap map,TileGrid grid) {
        this.map = map;

        MapLayer layer = map.getMapLayerByName("Teleports");
        if (layer == null)
            return;

        RectangleMapObject tpFrom = (RectangleMapObject) layer.getObjects().get(0);
        RectangleMapObject tpTo = (RectangleMapObject) layer.getObjects().get(1);

        setTeleportFromLocation((int) tpFrom.getRectangle().getX(), (int) tpFrom.getRectangle().getY(), grid);
        setTeleportToLocation((int) tpTo.getRectangle().getX(), (int) tpTo.getRectangle().getY(), grid);
    }

    public void setTeleportToLocation(int xLocation, int yLocation, TileGrid grid) {
        xTeleportToInPx = xLocation;
        yTeleportToInPx = yLocation;

        System.out.println(yLocation + " " +  xLocation);

        grid.getTileFromCoordinates(yLocation,xLocation).getGameObjects().add(this);
    }

    public void setTeleportFromLocation(int xLocation, int yLocation, TileGrid grid) {
        xTeleportFromInPx = xLocation;
        yTeleportFromInPx = yLocation;

        grid.getTileFromCoordinates(yLocation,xLocation).getGameObjects().add(this);
    }

    @Override
    public void handleCollision(Player player, TileGrid grid) {
        Tile teleportFromTile = grid.getTileFromCoordinates(yTeleportFromInPx, yTeleportFromInPx);
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());

        if (teleportFromTile.equals(playerTile)) {
            int deltaX = (int) (xTeleportToInPx - player.getX());
            int dealtY = (int) (yTeleportToInPx - player.getY());
            int newX = (int) (deltaX + player.getX());
            int newY = (int) (dealtY + player.getY());

            player.setPosition(newX, newY, grid);
        }
    }


    @Override
    public Sprite getSprite() {
        return null;
    }


}
