package inf112.skeleton.app.demo;

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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import group1.team2.src.main.java.inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class Game extends ApplicationAdapter implements InputProcessor {
    final int TILE_SIZE_IN_PX = 128;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    Texture texture;
    Player player;
    Direction startDirection;
    public TileGrid grid;


    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        grid = new TileGrid((int)h, (int)w, TILE_SIZE_IN_PX);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMap = new TmxMapLoader().load("RoboRally.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);

        tiledMap.getLayers().get("walls");

        sb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("car.jpg"));

        startDirection = Direction.West;
        player = new Player(texture, startDirection);
        player.setPosition(5,40);
        grid.getTile(0,0).addSprite(player);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        drawSprites();
        sb.end();
    }
    
    public void drawSprites() {
        for (Sprite sprite : grid.getAllSpritesOnMap()) {
            sprite.draw(sb);
        }
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
            player.moveForward(1, moveDistance, this, currentTile);
        }

        if (keycode == Input.Keys.valueOf("2")) {
            player.moveForward(2, moveDistance, this, currentTile);
        }

        if (keycode == Input.Keys.valueOf("3")) {
            player.moveForward(3, moveDistance, this, currentTile);
        }

        if (keycode == Input.Keys.U) {
            player.uTurn();
        }


        return false;
    }

    public void updatePlayerPositionInGrid(Tile currentTile) {
        float x = player.getX();
        float y = player.getY();

        currentTile.getSprites().remove(player);
        grid.getTileFromCoordinates(y, x).addSprite(player);

        System.out.println(grid.getTileFromCoordinates(y,x));

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
