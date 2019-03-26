package inf112.skeleton.app.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.Screen.MainMenuScreen;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.collision.objects.GameObjectFactory;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

public class RoboGame extends Game {
    public static OrthographicCamera camera;
    public int TILE_SIZE_IN_PX;
    public TiledMap tiledMap;
    public static TiledMapRenderer tiledMapRenderer;
    public SpriteBatch sb;
    public Player player;
    public TileGrid grid;
    public GameMap gameMap;
    private StackOfCards deck;
    private MoveCard temp;
    public MoveCard[] list;
    public Boolean[] booList;
    private Sprite backboard;
    private Sprite lives;
    private Texture texture;


    public static final int ROBO_GAME_WIDTH = 1080;
    public static final int ROBO_GAME_HEIGHT = 720;

    @Override
    public void create() {

        this.setScreen(new MainMenuScreen(this));
        gameMap = new GameMap("map.v.01.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(gameMap.getTiledMap());
        this.TILE_SIZE_IN_PX = getTileSize();

        tiledMap = new TmxMapLoader().load("map.v.01.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new CustomCamera(tiledMap);
        camera.translate( -470, -700);

        this.grid = makeGrid();
        sb = new SpriteBatch();

        texture = new Texture("cardLayouts/mech.jpg");
        backboard = new Sprite(texture);
        backboard.setSize(1950,1600);
        backboard.setPosition(-480,-700);

        texture = new Texture("sprites/exmplLife.png");
        lives = new Sprite(texture);
        lives.setSize(300,150);
        lives.setPosition(1050, 600);

        deck = new StackOfCards();
        list = new MoveCard[9];
        booList = new Boolean[9];

        GameObjectFactory constructor = new GameObjectFactory(gameMap, grid, this);
        player = constructor.player;
        grid.getTile(0,0).addGameObject(player);
        Gdx.input.setInputProcessor(player);

        drawNineCardsFromDeck();

    }

    //"draw", as in drawing cards from a deck of cards.
    //not "draw", as in drawing the picture of a card in the application.
    //#tricky #difference #notTheSame ##
    public void drawNineCardsFromDeck() {
        int cardYPos = -770;
        int cardXPos = -550;
        MoveCard card;
        for (int i = 0; i < list.length; i++) {
            card = deck.nextCard();
            if (card != null) {
                card.setSize(400, 520);
                card.setPosition(cardXPos, cardYPos);
                booList[i] = false;
            }
            list[i] = card;
            cardXPos += 205;

        }
    }



    public TileGrid makeGrid() {
        TiledMapTileLayer layer = (TiledMapTileLayer)gameMap.getMapLayerByIndex(0);

        int heightNumberOfTiles = layer.getHeight();
        int widthNumberOfTiles = layer.getWidth();

        return new TileGrid(heightNumberOfTiles, widthNumberOfTiles, TILE_SIZE_IN_PX);
    }

    //Only works if each tile is a square with equal sides
    public int getTileSize() {
        TiledMapTileLayer layer = (TiledMapTileLayer)gameMap.getMapLayerByIndex(0);
        return (int) layer.getTileWidth();

    }

    @Override
    public void render() {
         super.render();
    }


    public void drawHUD() {
        sb.end();
        sb.begin();
        backboard.draw(sb);
        for (int i = 0; i < list.length; i++) {
            //if (booList[i] != true) {
                list[i].draw(sb);
            //}
        }
        lives.draw(sb);

        sb.end();
    }

    public void drawSpritesFromGrid() {
        sb.begin();
        for (IGameObject gameObject : grid.getAllSpritesOnMap()) {
            //Not all GameObjects have sprites
            if (gameObject.getSprite() != null)
                gameObject.getSprite().draw(sb);
        }
        sb.end();
    }


    @Override
    public void dispose() {
        sb.dispose();
        texture.dispose();
        tiledMap.dispose();
    }




    public enum Direction{
        North, East, South, West
    }


}
