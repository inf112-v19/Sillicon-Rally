package inf112.skeleton.app.SetupVariables;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.collision.objects.*;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;
import org.mockito.Mockito;

public class SetupVariables {
    private Application application;
    public TileGrid grid;
    public GameMap gameMap;
    public TeleportObstacle teleport;
    public Player player;
    public int TILESIZE_IN_PX;
    public FlagObject flag;
    public ConveyorBeltObject belt;
    public LaserObject laser;
    public RepairObject repairObject;


    public SetupVariables() {
        setup();
        this.gameMap = new GameMap("map.v.01.tmx");
        this.grid = makeGrid(gameMap);
        this.teleport = new TeleportObstacle(gameMap, grid);
        this.player = new Player();
        this.TILESIZE_IN_PX = gameMap.getTileSize();
        this.flag = new FlagObject(2,4,grid);
        this.belt = new ConveyorBeltObject(4, 4, grid, 1, RoboGame.Direction.West);
        this.laser = new LaserObject(2,2, grid);
        this.repairObject = new RepairObject(2,2,grid);
    }


    public SetupVariables renew() {
        return new SetupVariables();
    }

    public void setup() {
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    public TileGrid makeGrid(GameMap gameMap) {
        TiledMapTileLayer layer = (TiledMapTileLayer)gameMap.getMapLayerByIndex(0);
        int TILE_SIZE_IN_PX = (int) layer.getTileWidth();

        int heightNumberOfTiles = layer.getHeight();
        int widthNumberOfTiles = layer.getWidth();

        return new TileGrid(heightNumberOfTiles, widthNumberOfTiles, TILE_SIZE_IN_PX);
    }

    public void stop() {
        application.exit();
        application = null;
    }
}
