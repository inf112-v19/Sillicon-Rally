package inf112.skeleton.app.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import group1.team2.src.main.java.inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.Tile;

import java.awt.*;

public class TeleportObstacle extends Sprite {
    Game game;
    int xTeleportFrom, yTeleportFrom;
    int xTeleportTo, yTeleportTo;


    public TeleportObstacle(Game game) {
        super(new Texture(Gdx.files.internal("sprites/clockface.png")));
        this.game = game;

        TiledMap map = game.tiledMap;
        MapLayer layer = map.getLayers().get("Teleports");
        RectangleMapObject tpFrom = (RectangleMapObject) layer.getObjects().get(0);
        RectangleMapObject tpTo = (RectangleMapObject) layer.getObjects().get(1);

        setTeleportFromLocation((int) tpFrom.getRectangle().getX(), (int) tpFrom.getRectangle().getY());
        setTeleportToLocation((int) tpTo.getRectangle().getX(), (int) tpTo.getRectangle().getY());
    }

    public void setTeleportToLocation(int xLocation, int yLocation) {
        xTeleportTo = xLocation;
        yTeleportTo = yLocation;



        game.grid.getTileFromCoordinates(yLocation,xLocation).getSprites().add(this);
    }

    public void setTeleportFromLocation(int xLocation, int yLocation) {
        xTeleportFrom = xLocation;
        yTeleportFrom = yLocation;

        game.grid.getTileFromCoordinates(yLocation,xLocation).getSprites().add(this);
    }

    public void handleCollision(Player player) {
        Tile teleportFromTile = game.grid.getTileFromCoordinates(yTeleportFrom, yTeleportFrom);
        Tile playerTile = game.grid.getTileFromCoordinates(player.getY(), player.getX());

        if (teleportFromTile.equals(playerTile)) {
            player.setX(xTeleportTo);
            player.setY(yTeleportTo);
            game.updatePlayerPositionInGrid(playerTile);
        }
    }


}
