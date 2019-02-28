package inf112.skeleton.app.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.collision.objects.CollisionHandler;
import inf112.skeleton.app.collision.objects.TeleportObstacle;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

import java.util.ArrayList;

public class Game extends ApplicationAdapter implements InputProcessor {
    public int TILE_SIZE_IN_PX;
    public TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    Texture texture;
    public Player player;
    private Direction startDirection;
    public TileGrid grid;
    private StackOfCards deck;
    private ArrayList<MoveCard> cardsOnBoard;
    private MoveCard temp;
    private int cardXPos, lifeXPos;
    private Sprite numbers;
    private Sprite backboard;
    private Sprite lives;


    @Override
    public void create() {
        tiledMap = new TmxMapLoader().load("map.v.01.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new CustomCamera(tiledMap);
        this. TILE_SIZE_IN_PX = getTileSize();
        camera.translate( -470, -700);

        this.grid = makeGrid();
        Gdx.input.setInputProcessor(this);
        sb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("sprites/car.jpg"));

        startDirection = Direction.West;
        player = new Player(texture, startDirection);
        player.setSize(100,50);
        player.setOriginCenter();
        player.setPosition(0,20);
        grid.getTile(0,0).addSprite(player);

        texture = new Texture("cardLayouts/mech.jpg");
        backboard = new Sprite(texture);
        backboard.setSize(1250,680);
        backboard.setPosition(-140,-700);

        texture = new Texture("sprites/exmplLife.png");
        lives = new Sprite(texture);
        lives.setSize(300,150);
        lives.setPosition(750, -230);

        texture = new Texture("sprites/number1to5.png");
        numbers = new Sprite(texture);
        numbers.setSize(1050, 600);
        numbers.setPosition(-30,-730);

        deck = new StackOfCards();
        drawFiveCards();

        TeleportObstacle tele = new TeleportObstacle(this);
        CollisionHandler coll = new CollisionHandler(this);

    }

    //"draw", as in drawing cards from a deck of cards.
    //not "draw", as in drawing the picture of a card in the application.
    //#tricky #difference #notTheSame ##
    private void drawFiveCards() {
        int cardYPos = -800;
        cardXPos = -235;
        cardsOnBoard = new ArrayList<>();
        MoveCard card;
        for (int i = 0; i < 5; i++) {
            card = deck.nextCard();
            card.setSize(470,670);
            card.setPosition(cardXPos, cardYPos);
            cardsOnBoard.add(card);
            cardXPos += 240;
        }
    }


    public TileGrid makeGrid() {
        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);

        int heightNumberOfTiles = layer.getHeight();
        int widthNumberOfTiles = layer.getWidth();

        return new TileGrid(heightNumberOfTiles, widthNumberOfTiles, TILE_SIZE_IN_PX);
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        sb.setProjectionMatrix(camera.combined);

        drawSprites();

    }

    //draws sprites in spritebatch
    public void drawSprites() {
        sb.begin();
        backboard.draw(sb);
        for (Sprite sprite : grid.getAllSpritesOnMap()) {
            sprite.draw(sb);
        }
        for (MoveCard card : cardsOnBoard) {
            card.draw(sb);
        }
        lives.draw(sb);
        numbers.draw(sb);
        sb.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        float x = player.getX();
        float y = player.getY();

        //Viktig å fjerne spilleren fra sin nåværende tile
        Tile currentTile = grid.getTileFromCoordinates(y, x);

        int moveDistance = TILE_SIZE_IN_PX;

        if (keycode == Input.Keys.RIGHT) {
            player.turnRight();
        }

        if (keycode == Input.Keys.LEFT) {
            player.turnLeft();
        }

        if (keycode == Input.Keys.valueOf("1")) {
            int index = 0;
            if (cardsOnBoard.get(index) != null) {
                movePlayer(index, moveDistance, currentTile);
                cardsOnBoard.remove(index);
            }
        }

        if (keycode == Input.Keys.valueOf("2")) {
            int index = 1;
            if (cardsOnBoard.get(index) != null) {
                movePlayer(index, moveDistance, currentTile);
                cardsOnBoard.remove(index);
            }
        }

        if (keycode == Input.Keys.valueOf("3")) {
            int index = 2;
            if (cardsOnBoard.get(index) != null) {
                movePlayer(index, moveDistance, currentTile);
                cardsOnBoard.remove(index);
            }
        }

        if (keycode == Input.Keys.valueOf("4")) {
            int index = 3;
            if (cardsOnBoard.get(index) != null) {
                movePlayer(index, moveDistance, currentTile);
                cardsOnBoard.remove(index);
            }
        }

        if (keycode == Input.Keys.valueOf("5")) {
            int index = 4;
            if (cardsOnBoard.get(index) != null) {
                movePlayer(index, moveDistance, currentTile);
                cardsOnBoard.remove(index);
            }
        }

        if (keycode == Input.Keys.U) {
            player.uTurn();
        }

        if (keycode == Input.Keys.UP) {
            player.moveForward(1, moveDistance, this, currentTile);
        }

        if (keycode == Input.Keys.DOWN) {
            player.moveForward(-1,moveDistance,this, currentTile);
        }

        return false;
    }

    private void movePlayer(int index, int moveDistance, Tile currentTile) {
        temp = cardsOnBoard.get(index);
        switch (temp.getType()) {
            case move1:
                player.moveForward(1, moveDistance, this, currentTile);
            case move2:
                player.moveForward(2, moveDistance, this, currentTile);
            case move3:
                player.moveForward(3, moveDistance, this, currentTile);
            case uturn:
                player.uTurn();
            case turnleft:
                player.turnLeft();
            case turnright:
                player.turnRight();
            case reverse:
                player.moveForward(-1, moveDistance, this, currentTile);
        }

    }

    public void checkCollision() {
        CollisionHandler collisionHandler = new CollisionHandler(this);
        collisionHandler.checkCollision();
    }

    public void updatePlayerPositionInGrid(Tile currentTile) {
        float x = player.getX();
        float y = player.getY();

        currentTile.getSprites().remove(player);
        grid.getTileFromCoordinates(y, x).addSprite(player);
    }

    //Only works if each tile is a square with equal sides
    public int getTileSize() {
        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        return (int) layer.getTileWidth();
    }

    @Override
    public void dispose() {
        sb.dispose();
        texture.dispose();
        tiledMap.dispose();
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    public enum Direction{
        North, East, South, West
    }

}
