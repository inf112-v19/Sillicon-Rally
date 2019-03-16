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

public class GameObjectFactory {
    public GameMap map;
    public TileGrid grid;
    public Player player;
    public List<IGameObject> flags;
    public TeleportObstacle teleportObstacle;
    public List<ConveyorObjectWest> oneForwardBeltsWest;
    public List<ConveyorObjectEast> oneForwardBeltsEast;
    public List<ConveyorObjectNorth> oneForwardBeltsNorth;
    public List<ConveyorObjectSouth> oneForwardBeltsSouth;

    public GameObjectFactory(GameMap map, TileGrid grid) {
        this.map = map;
        this.grid = grid;

        createPlayer();
        createFlags();
        createTeleporter();
        createConveyorBelts();
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

    private void createConveyorBelts() {
        createNormalBelts();
    }

    private void createNormalBelts() {
        int speed = 1;
        oneForwardBeltsWest = new ArrayList<>();
        oneForwardBeltsEast = new ArrayList<>();
        oneForwardBeltsNorth = new ArrayList<>();
        oneForwardBeltsSouth = new ArrayList<>();

        MapLayer beltWest = map.getMapLayerByName("OneForwardBeltsWest");
        MapLayer beltEast = map.getMapLayerByName("OneForwardBeltsEast");
        MapLayer beltNorth = map.getMapLayerByName("OneForwardBeltsNorth");
        MapLayer beltSouth = map.getMapLayerByName("OneForwardBeltsSouth");


        for (MapObject belt : beltWest.getObjects()) {
            RectangleMapObject beltRectangleObject = (RectangleMapObject) belt;
            oneForwardBeltsWest.add(new ConveyorObjectWest(beltRectangleObject, grid, speed));
        }
       for (MapObject belt: beltEast.getObjects()) {
            RectangleMapObject beltRectangleObject = (RectangleMapObject) belt;
            oneForwardBeltsEast.add(new ConveyorObjectEast(beltRectangleObject, grid, speed));
        }
       for (MapObject belt: beltNorth.getObjects()) {
            RectangleMapObject beltRectangleObject = (RectangleMapObject) belt;
            oneForwardBeltsNorth.add(new ConveyorObjectNorth(beltRectangleObject, grid, speed));
        }
        for (MapObject belt: beltSouth.getObjects()) {
            RectangleMapObject beltRectangleObject = (RectangleMapObject) belt;
            oneForwardBeltsSouth.add(new ConveyorObjectSouth(beltRectangleObject, grid, speed));
        }
    }



    private void createTeleporter() {
        this.teleportObstacle = new TeleportObstacle(map, grid);
    }
}
