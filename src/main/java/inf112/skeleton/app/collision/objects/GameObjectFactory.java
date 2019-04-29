package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.AIPlayer;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class GameObjectFactory {
    public GameMap map;
    public TileGrid grid;
    //public Player player;
    //public Player player2;
    public ConveyorBeltObject conveyorBeltObject;
    public List<IGameObject> flags;
    public List<IGameObject> lasers;
    public List<IGameObject> pitfalls;
    public List<IGameObject> repairs;
    public TeleportObstacle teleportObstacle;

    public List<ConveyorBeltObject> ForwardBelts;
    public List<TurnGearObject> turnGears;

    private RoboGame.Direction startDirection = RoboGame.Direction.North;
    public Texture player2Texture;

    public GameObjectFactory(GameMap map, TileGrid grid, RoboGame game) {
        this.map = map;
        this.grid = grid;
    }

    public void createObjects(RoboGame game, List<Player> playerList, int numberOfPlayers) {
        //createPlayers(playerList, game);
        createFlags();
        createTeleporter();
        createConveyorBelts();

        createLasers();
        createRepairs();
        createTurnGears();
        createPits();
    }


    public ArrayList<Player> createPlayers(int numberOfPlayers, RoboGame game) {
        ArrayList<Player> list = new ArrayList<>();
        if (numberOfPlayers == 1)
            createPlayer1(list, game);
        if (numberOfPlayers == 2) {
            createPlayer1(list, game);
            createPlayer2(list, game);
        }

        return list;
    }

    private void createAi(List<Player> playerList, RoboGame game) {
        player2Texture = new Texture("robot2.png");
        AIPlayer player = new AIPlayer((player2Texture), startDirection, game, "AI");
        grid.getTile(0, 0).addGameObject(player);
        playerList.add(player);
    }

    public void createPlayer1(List<Player> playerList, RoboGame game){
        //game.player = new Player(new Texture("robot1.png"), startDirection, game, "Player 1");
        Player player = new Player(new Texture("robot1.png"), startDirection, game, "Player 1");
        grid.getTile(0, 0).addGameObject(player);
        playerList.add(player);
    }


    public void createPlayer2(List<Player> playerList, RoboGame game){
        player2Texture = new Texture("robot2.png");
        Player player = new Player((player2Texture), startDirection, game, "Player 2");
        grid.getTile(0, 0).addGameObject(player);
        playerList.add(player);
    }

    public void removePlayerSprite(RoboGame game, Player thisPlayer){
        grid.getTile(0,0).getGameObjects().remove(thisPlayer);
    }

    private void createFlags() {
        flags = new ArrayList<>();

        MapLayer layer = map.getMapLayerByName("Flags");

        for (MapObject flag : layer.getObjects()) {
            RectangleMapObject flagRectangleObject = (RectangleMapObject) flag;
            flags.add(new FlagObject(flagRectangleObject, grid));
        }

    }

    private void createLasers(){
        lasers = new ArrayList<>();

        MapLayer laserLayer = map.getMapLayerByName("Lasers");
        if (laserLayer == null)
            return;

        for (MapObject laser : laserLayer.getObjects() ) {
            RectangleMapObject laserRectangleObject = (RectangleMapObject) laser;
            lasers.add(new LaserObject(laserRectangleObject, grid));
        }

    }
    private void createRepairs(){
        repairs = new ArrayList<>();

        MapLayer repairLayer = map.getMapLayerByName("Repairs");
        if (repairLayer == null)
            return;

        for (MapObject repair : repairLayer.getObjects() ) {
            RectangleMapObject repairRectangleObject = (RectangleMapObject) repair;
            repairs.add(new RepairObject(repairRectangleObject, grid));
        }

    }

    private void createPits(){
        pitfalls = new ArrayList<>();

        MapLayer pitLayer = map.getMapLayerByName("Pits");
        if (pitLayer == null)
            return;

        for (MapObject pit : pitLayer.getObjects() ) {
            RectangleMapObject pitRectangleObject = (RectangleMapObject) pit;
            pitfalls.add(new PitObject(pitRectangleObject, grid));
        }

    }


    private void createConveyorBelts() {
        this.ForwardBelts = new ArrayList<>();
        MapLayer belts = map.getMapLayerByName("Belts");
        if (belts == null)
            return;


        for (MapObject belt: belts.getObjects()) {
            RectangleMapObject beltRectangleObject = (RectangleMapObject) belt;
            ForwardBelts.add(new ConveyorBeltObject(beltRectangleObject, grid));
        }
    }


    private void createTurnGears(){
        this.turnGears = new ArrayList<>();

        MapLayer mapLayer = map.getMapLayerByName("turnGears");
        if (mapLayer == null)
            return;

        for (MapObject turnGear: mapLayer.getObjects()) {
            RectangleMapObject turnGearObject = (RectangleMapObject) turnGear;
            turnGears.add(new TurnGearObject(turnGearObject, grid));
        }
    }



    private void createTeleporter() {
        this.teleportObstacle = new TeleportObstacle(map, grid);
    }
}
