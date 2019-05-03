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
    private RoboGame.Direction startDirection = RoboGame.Direction.South;
    private int temp;
    public List<IGameObject> flags;
    public List<IGameObject> lasers;
    public List<IGameObject> pitfalls;
    public List<IGameObject> repairs;
    public List<ConveyorBeltObject> ForwardBelts;
    public List<TurnGearObject> turnGears;

    public TeleportObstacle teleportObstacle;

    public static Texture playerTexture;

    public int spawnCounter=0;

    public GameObjectFactory(GameMap map, TileGrid grid) {
        this.map = map;
        this.grid = grid;
        temp = 0;
    }

    public void createObjects() {
        createFlags();
        createTeleporter();
        createConveyorBelts();
        createLasers();
        createRepairs();
        createTurnGears();
        createPits();
    }


    public ArrayList<Player> createPlayers(int numberOfPlayers, RoboGame game) {
        for (int i = 0; i < numberOfPlayers; i++) {
            createPlayer(game.playerList, game, (i+1));
            temp++;
        }
        return game.playerList;
    }

    public ArrayList<Player> createAis(int numberOfPlayers, RoboGame game) {
        for (int j = 0; j < numberOfPlayers; j++) {
            createAi(game.playerList, game, (temp+1));
            temp++;
        }
        return game.playerList;
    }



    public void createAi(List<Player> playerList, RoboGame game, int playernum) {
        playerTexture = new Texture(("RobotSprites/robot" +playernum+".png"));
        Player player = new AIPlayer(playerTexture, startDirection, game, ("AI"+playernum));
        player.getSprite().rotate(180);
        grid.getTile(0, 0).addGameObject(player);
        playerList.add(player);
        int[] array=getSpawn();
        player.setPosition(array[1], array[0], grid);
    }


    public void createPlayer(List<Player> playerList, RoboGame game, int playernum){
        playerTexture = new Texture(("RobotSprites/robot" +playernum+".png"));
        Player player = new Player(playerTexture, startDirection, game, ("Player"+ playernum));
        player.getSprite().rotate(180);
        grid.getTile(0, 0).addGameObject(player);
        playerList.add(player);
        int[] array=getSpawn();
        player.setPosition(array[1], array[0], grid);
    }



    public int[] getSpawn(){
        int[] returnArray = {0, 0};
        //spawnpoints in top right corner of the map
        int[] SpawnX={(96*9),(96*10), (96*11), (96*10), (96*11),(96*11)};
        int[] SpawnY={(96*9), (96*9), (96*9), (96*8), (96*8),(96*7) };

        returnArray[0] = SpawnX[spawnCounter];
        returnArray[1] = SpawnY[spawnCounter];

        SpawnX[spawnCounter]=0;
        SpawnY[spawnCounter]=0;

        spawnCounter++;

        return returnArray;
    }


    public void removePlayerSprite(Player thisPlayer){
        grid.getTileFromCoordinates(thisPlayer.getY() , thisPlayer.getX()).getGameObjects().remove(thisPlayer);
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
