package inf112.skeleton.app.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.LaserAnimation;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.Screen.GameScreen;
import inf112.skeleton.app.Screen.StartMenuScreen;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.collision.objects.GameObjectFactory;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoboGame extends Game {
    //Map related
    public TileGrid grid;
    public static OrthographicCamera camera;
    public TiledMap tiledMap;
    public GameMap gameMap;
    public static TiledMapRenderer tiledMapRenderer;
    public static int TILE_SIZE_IN_PX;
    public static final int ROBO_GAME_WIDTH = 1500;
    public static final int ROBO_GAME_HEIGHT = 900;


    //Sprite related
    public SpriteBatch sb;
    private Sprite backboard;
    private Texture texture;
    private BitmapFont font;
    private float xLoc;
    private float yLoc;

    //Player related
    public Player player;
    public Player player2;
    public ArrayList<Player> playerList;
    public int currentPlayer;

    //Card related
    public StackOfCards deck;
    public MoveCard cardPickedByPlayer;
    public MoveCard[] listOfNine;
    public MoveCard[] chosenFive;

    public GameScreen gameScreen;
    public GameObjectFactory constructor;




    @Override
    public void create() {

        playerList = new ArrayList<>();
        gameMap = new GameMap("MapNumberOne.tmx");
        TILE_SIZE_IN_PX = getTileSize();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(gameMap.getTiledMap());
        grid = makeGrid();

        font = new BitmapFont();
        xLoc = -400;
        yLoc = -600;

        constructor = new GameObjectFactory(gameMap, grid);
        constructor.createObjects();

        camera = new CustomCamera(gameMap.getTiledMap());
        camera.translate( -470, -700);

        sb = new SpriteBatch();

        texture = new Texture("cardLayouts/mech.jpg");
        backboard = new Sprite(texture);
        backboard.setSize(((CustomCamera) camera).pixelWidth*2,((CustomCamera) camera).pixelHeight*2);
        backboard.setPosition(-480,-700);

        deck = new StackOfCards();
        listOfNine = new MoveCard[9];
        chosenFive = new MoveCard[5];

        this.setScreen(new StartMenuScreen(this));
    }

    public void createPlayers(int numberOfPlayers) {
        constructor.createPlayers(numberOfPlayers, this);
        drawNineCardsFromDeck();
    }

    public void createAIs(int numberofAIs){
        constructor.createAis(numberofAIs, this);
    }



    public MoveCard chooseCard(int index, Player currentPlayer) {
        if(!canChooseMoreCard(currentPlayer))
            return null;

        cardPickedByPlayer = listOfNine[index];
        for (int i = 0; i < chosenFive.length; i++) {
            if (chosenFive[i] == null) {
                chosenFive[i] = alignCard(cardPickedByPlayer, i);

                listOfNine[index] = null;
                break;
            }
        }
        currentPlayer.increaseDeckCount();
        return cardPickedByPlayer;
    }


    private boolean canChooseMoreCard(Player currentPlayer) {
        if (currentPlayer.chosenCards == currentPlayer.MaxMoveCardLength)
            return false;

        return true;
    }


    private MoveCard alignCard(MoveCard temp, int index) {
        int x = 0 + 170*(index);
        int y = - 370;
        temp.setPosition(x,y);
        temp.setSize(300,400);
        return temp;
    }

    public void drawNineCardsFromDeck() {
        int cardYPos = -770;
        int cardXPos = -550;
        MoveCard card;
        for (int i = 0; i < listOfNine.length; i++) {
            card = deck.nextCard();
            if (card != null) {
                card.setSize(400, 520);
                card.setPosition(cardXPos, cardYPos);
            }
            listOfNine[i] = card;
            cardXPos += 205;
        }
        for (int i = 0; i < chosenFive.length; i++) {
            chosenFive[i] = null;
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
        for (int i = 0; i < listOfNine.length; i++) {
            if (listOfNine[i] != null) {
                listOfNine[i].draw(sb);
            }
        }
        for (int i = 0; i < chosenFive.length; i++) {
            if (chosenFive[i] != null) {
                chosenFive[i].draw(sb);
            }
        }
        drawNumbers(sb);
        sb.end();
    }


    private void drawNumbers(SpriteBatch sb) {
        float n = xLoc;
        float m = yLoc;
        font.getData().setScale(4);
        font.setColor(new Color(Color.GREEN));

        for (int i = 1; i < 10; i++) {
            String num = i + "";
            font.draw(sb, num,n, m);
            n += 204;
        }
    }

    public void drawSpritesFromGrid() {
        sb.begin();
        for (IGameObject gameObject : grid.getAllSpritesOnMap()) {
            if (gameObject instanceof LaserAnimation)
                System.out.println("Found laserAnimation");

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
        gameMap.getTiledMap().dispose();
    }


    public void putCardsBackInDeck() {
        for (int i = 0; i < listOfNine.length; i++) {
            if(listOfNine[i] != null) {
                addToDeck(listOfNine[i]);
            }
        }
    }


    public void addToDeck(MoveCard cd) {
        deck.putCardInDeck(cd);
    }


    public enum Direction{
        North, East, South, West
    }

}
