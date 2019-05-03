package inf112.skeleton.app.SetupVariables;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.Objects.LaserAnimation;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.collision.objects.*;
import inf112.skeleton.app.game.CustomCamera;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.logic.Round;
import inf112.skeleton.app.map.GameMap;
import org.mockito.Mockito;

import java.util.ArrayList;

public class SetupVariables {
    //Map related
    public TileGrid grid;
    public GameMap gameMap;


    //Object related
    public FlagObject flag;
    public FlagObject flag2;
    public TeleportObstacle teleport;
    public Player player;
    public Player player2;
    public ConveyorBeltObject belt;
    public LaserObject laser;
    public RepairObject repairObject;
    public LaserAnimation laserAnimation;


    //Function related
    private Application application;
    public RoboGame game;
    public ArrayList<Player> playerList;
    public ArrayList<MoveCard> cardList;
    public ArrayList<MoveCard> oneForwardCardList;
    public Round round;
    public int TILESIZE_IN_PX;








    public SetupVariables() {
        setup();
        //Map related
        gameMap = new GameMap("MapNumberOne.tmx");
        grid = makeGrid(gameMap);
        TILESIZE_IN_PX = gameMap.getTileSize();

        //Player related
        laserAnimation = new LaserAnimation();


        //Object related
        flag = new FlagObject(2,4,grid);
        flag2 = new FlagObject(3,4, grid);
        belt = new ConveyorBeltObject(4, 4, grid, 1, RoboGame.Direction.West);
        laser = new LaserObject(2,2, grid);
        repairObject = new RepairObject(2,2,grid);
        teleport = new TeleportObstacle(gameMap, grid);

        game = new RoboGame();
        setUpGame();
        playerList = getPlayers();
        player = playerList.get(0);
        player2 = playerList.get(1);

        //Function related
        oneForwardCardList = getOneForwardDeck();
        oneForwardCardList = getOneForwardDeck();
        round = new Round(new StackOfCards(), playerList);
        cardList = getDeck();
    }

    public void setUpGame() {
        game.playerList = new ArrayList<>();
        game.gameMap = this.gameMap;
        game.TILE_SIZE_IN_PX = game.getTileSize();
     //   game.tiledMapRenderer = new OrthogonalTiledMapRenderer(gameMap.getTiledMap());
        game.grid = this.grid;
        game.constructor = new GameObjectFactory(gameMap, grid);


        game.font = new BitmapFont();
        game.xLoc = -400;
        game.yLoc = -600;


        game.camera = new CustomCamera(game.gameMap.getTiledMap());
        game.camera.translate( -470, -700);

//        game.sb = new SpriteBatch();

        game.texture = new Texture("cardLayouts/mech.jpg");
        game.backboard = new Sprite(game.texture);
        game.backboard.setSize(((CustomCamera) game.camera).pixelWidth*2,((CustomCamera) game.camera).pixelHeight*2);
        game.backboard.setPosition(-480,-700);

        game.deck = new StackOfCards();
        game.listOfNine = new MoveCard[9];
        game.chosenFive = new MoveCard[5];
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
        Texture playerTexture = new Texture("RobotSprites/robot1.png");
        Player player1 = new Player(playerTexture, RoboGame.Direction.North,this.game, "Player1");

        player1.setName("Vegard");
        Player player2 = new Player(playerTexture, RoboGame.Direction.North, this.game, "Player2");
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
