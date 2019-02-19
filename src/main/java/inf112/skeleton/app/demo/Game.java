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

public class Game extends ApplicationAdapter implements InputProcessor {
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    Texture texture;
    Sprite player;
    Direction dir; //La til en public enum metode i bunn av denne appen, men Direction burde kanskje være en egen klasse?


    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMap = new TmxMapLoader().load("RoboRally.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);

        sb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("car.jpg"));
        player = new Sprite(texture);
        player.setPosition(10,40); //plasserer bilen ca midt i hver tile.
        dir = Direction.West; //startretning
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

    private void drawSprites() {
        player.draw(sb);
    }


    @Override
    public boolean keyDown(int keycode) {
        float x = player.getX();
        float y = player.getY();
        int moveDistance = 128;

        /**La til litt kode på hver input for å rotere
         * spiller ved første tastetrykk om spilleren ikke allerede vender riktig retning
         */
        if (keycode == Input.Keys.RIGHT ) {
            if (dir == Direction.East) {
            player.setX(x + moveDistance);
            } else {
                switch (dir) {
                    case North:
                        player.rotate(180); //vet ikke hvorfor, men må rotere spiller en ulogisk mengde
                    case South:                     // i dette tilfellet for at det skal fungere riktig.
                        player.rotate(-90);
                    case West:
                        player.rotate(180);
                }
                dir = Direction.East;
            }

           // System.out.println(player.getX());

        }
        
        if (keycode == Input.Keys.LEFT) {
            if (dir == Direction.West) {
            player.setX(x - moveDistance);
            } else {
                switch (dir) {
                    case South:
                        player.rotate(180);
                    case North:
                        player.rotate(-90);
                    case East:
                        player.rotate(180);
                }
                dir = Direction.West;
            }
        }
        if (keycode == Input.Keys.UP) {
            if (dir == Direction.North) {
            player.setY(y + moveDistance);
            } else {
                switch (dir) {
                    case West: player.rotate(180);
                    case East: player.rotate(-90);
                    case South: player.rotate(180);
                }
                dir = Direction.North;
            }
        }
        if (keycode == Input.Keys.DOWN) {
            if (dir == Direction.South) {
            player.setY(y - moveDistance);
            } else {
                switch (dir) {
                    case East: player.rotate(180);
                    case West: player.rotate(-90);
                    case North: player.rotate(180);
                }
                dir = Direction.South;
            }
        }

        return false;
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
