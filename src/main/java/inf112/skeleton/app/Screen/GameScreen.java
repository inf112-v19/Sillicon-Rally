package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private RoboGame game;
    public GameMap gameMap;
    public MainMenuScreen mainMenu;
    public int TILE_SIZE_IN_PX;
    public TiledMap tiledMap;
    public SpriteBatch sb;
    public Player player;
    RoboGame.Direction startDirection;
    public TileGrid grid;
    public StackOfCards deck;
    private ArrayList<MoveCard> cardsOnBoard;

    public GameScreen (RoboGame game){
        this.game = game;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.sb.begin();
        game.drawHUD();
        RoboGame.camera.update();
        RoboGame.tiledMapRenderer.setView(RoboGame.camera);
        RoboGame.tiledMapRenderer.render();

        game.sb.setProjectionMatrix(RoboGame.camera.combined);

        game.drawSpritesFromGrid();
        //game.sb.end();
    }


    @Override
    public void show() {
        gameMap = new GameMap("map.v.01.tmx");
        player = new Player(new Texture("car.jpg"), startDirection);

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}