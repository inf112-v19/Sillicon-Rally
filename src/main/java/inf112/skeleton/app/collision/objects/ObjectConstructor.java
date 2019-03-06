package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class ObjectConstructor {
    public GameMap map;
    public TileGrid grid;
    public Player player;
    public List<IGameObject> flags;
    public TeleportObstacle teleportObstacle;

    public ObjectConstructor(GameMap map, TileGrid grid) {
        this.map = map;
        this.grid = grid;

        createPlayer();
        createFlags();
        createTeleporter();
    }

    private void createPlayer() {
        Game.Direction startDirection = Game.Direction.North;
        player = new Player(new Texture("robot1.png"), startDirection);
    }

    private void createFlags() {
        flags = new ArrayList<>();

        MapLayer layer = map.getMapLayerByName("Flags");

        for (MapObject flag : layer.getObjects()) {
            RectangleMapObject flagRectangleObject = (RectangleMapObject) flag;
            flags.add(new FlagObject(flagRectangleObject, grid));
        }

    }

    private void createTeleporter() {
        this.teleportObstacle = new TeleportObstacle(map, grid);
    }
}
