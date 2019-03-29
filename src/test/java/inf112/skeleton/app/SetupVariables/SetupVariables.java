package inf112.skeleton.app.SetupVariables;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.collision.objects.*;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.logic.Round;
import inf112.skeleton.app.map.GameMap;
import org.mockito.Mockito;

import java.util.ArrayList;

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
    public Round round;
    public StackOfCards deck;
    public MoveCard card;
    public Texture texture;
    public ArrayList<Player> playerList;
    public ArrayList<MoveCard> cardList;

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
        this.cardList = getDeck();
        this.playerList = getPlayers();
        this.round = new Round(new StackOfCards(), playerList);

        this.cardList = getDeck();
        playerList = getPlayers();


    }

    public void playerDefaultPosition(Player player){
        int y = 0;
        int x = 4;
        int tileSize = grid.tileSizeInPx;
        player.setDirection(RoboGame.Direction.North);
        Tile playerTile = grid.getTile(y,x);
        playerTile.addGameObject(player);
        player.setY(y * grid.tileSizeInPx);
        player.setX(x * grid.tileSizeInPx);
    }

    public ArrayList<Player> getPlayers(){
        ArrayList<Player> playerList = new ArrayList<>();

        Player player1 = new Player();
        player1.setName("Vegard");
        Player player2 = new Player();
        player2.setName("Martin");


        playerList.add(player1);
        playerList.add(player2);

        for (Player p : playerList)
            playerDefaultPosition(p);

        return playerList;
    }

    public ArrayList<MoveCard> getDeck(){

        ArrayList<MoveCard> cards = new ArrayList<>();
        Texture textureMove1 = new Texture("cardLayouts/Move1.png");
        Texture textureMove2 = new Texture("cardLayouts/Move2.png");
        Texture textureRightTurn = new Texture("cardLayouts/RightTurn.png");

        MoveCard card1 = new MoveCard(MoveCard.Type.move1, textureMove1);
        MoveCard card2 = new MoveCard(MoveCard.Type.move2, textureMove2);
        MoveCard card3 = new MoveCard(MoveCard.Type.turnright, textureRightTurn);

        cards.add(card1);
        cards.add(card2);
        cards.add(card2);
        cards.add(card3);
        cards.add(card1);

        return cards;
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
