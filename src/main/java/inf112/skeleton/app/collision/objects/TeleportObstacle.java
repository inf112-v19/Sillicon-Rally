package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

public class TeleportObstacle implements IGameObject {
   // Game game;
    int xTeleportFrom, yTeleportFrom;
    int xTeleportTo, yTeleportTo;
    GameMap map;


    public TeleportObstacle(GameMap map,TileGrid grid) {
        this.map = map;

        //TiledMap map = game.tiledMap;
        //GameMap map = game.gameMap;
        MapLayer layer = map.getMapLayerByName("Teleports");
        RectangleMapObject tpFrom = (RectangleMapObject) layer.getObjects().get(0);
        RectangleMapObject tpTo = (RectangleMapObject) layer.getObjects().get(1);

        setTeleportFromLocation((int) tpFrom.getRectangle().getX(), (int) tpFrom.getRectangle().getY(), grid);
        setTeleportToLocation((int) tpTo.getRectangle().getX(), (int) tpTo.getRectangle().getY(), grid);
    }

    public void setTeleportToLocation(int xLocation, int yLocation, TileGrid grid) {
        xTeleportTo = xLocation;
        yTeleportTo = yLocation;

        grid.getTileFromCoordinates(yLocation,xLocation).getGameObjects().add(this);
    }

    public void setTeleportFromLocation(int xLocation, int yLocation, TileGrid grid) {
        xTeleportFrom = xLocation;
        yTeleportFrom = yLocation;

        grid.getTileFromCoordinates(yLocation,xLocation).getGameObjects().add(this);
    }

    public void handleTeleportCollision(Player player, TileGrid grid) {
        Tile teleportFromTile = grid.getTileFromCoordinates(yTeleportFrom, yTeleportFrom);
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());

        if (teleportFromTile.equals(playerTile)) {
            int deltaX = (int) (xTeleportTo - player.getX());
            int dealtY = (int) (yTeleportTo - player.getY());
            int newX = (int) (deltaX + player.getX());
            int newY = (int) (dealtY + player.getY());

            player.setPosition(newX, newY, grid);
            //game.updatePlayerPositionInGrid(playerTile);
        }
    }


    @Override
    public Sprite getSprite() {
        return null;
    }
}
