package inf112.skeleton.app.SetupVariables;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.Objects.LaserAnimation;
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
    public FlagObject flag2;
    public ConveyorBeltObject belt;
    public LaserObject laser;
    public RepairObject repairObject;
    public Round round;
    //public StackOfCards deck;
    public MoveCard card;
    public LaserAnimation laserAnimation;
    public Texture texture;
    public ArrayList<Player> playerList;
    public ArrayList<MoveCard> cardList;
    public ArrayList<MoveCard> oneForwardCardList;

    public SetupVariables() {
        setup();
        //map related
        gameMap = new GameMap("map.v.01.tmx");
        grid = makeGrid(gameMap);
        TILESIZE_IN_PX = gameMap.getTileSize();


        //player related
        player = new Player(grid);
        laserAnimation = new LaserAnimation();


        //Object related
        flag = new FlagObject(2,4,grid);
        flag2 = new FlagObject(3,4, grid);
        belt = new ConveyorBeltObject(4, 4, grid, 1, RoboGame.Direction.West);
        laser = new LaserObject(2,2, grid);
        repairObject = new RepairObject(2,2,grid);
        teleport = new TeleportObstacle(gameMap, grid);


        //Function related
        oneForwardCardList = getOneForwardDeck();
        oneForwardCardList = getOneForwardDeck();
        playerList = getPlayers();
        round = new Round(new StackOfCards(), playerList);
        cardList = getDeck();
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

        Player player1 = new Player(grid);
        player1.setName("Vegard");
        Player player2 = new Player(grid);
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

        MoveCard move1Card = new MoveCard(MoveCard.Type.move1, textureMove1);
        MoveCard move2Card = new MoveCard(MoveCard.Type.move2, textureMove2);
        MoveCard turnRightCard = new MoveCard(MoveCard.Type.turnright, textureRightTurn);

        cards.add(move1Card);
        cards.add(move2Card);
        cards.add(move2Card);
        cards.add(turnRightCard);
        cards.add(move1Card);

        return cards;
    }

    private ArrayList<MoveCard> getOneForwardDeck() {
        ArrayList<MoveCard> cards = new ArrayList<>();
        Texture textureMove1 = new Texture("cardLayouts/Move1.png");
        MoveCard moveOneCard = new MoveCard(MoveCard.Type.move1, textureMove1);

        cards.add(moveOneCard);
        cards.add(moveOneCard);

        return cards;
    }

    public SetupVariables renew() {
        return new SetupVariables();
    }

    private void setup() {
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
