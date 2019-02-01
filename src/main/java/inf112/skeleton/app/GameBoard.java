package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameBoard implements ApplicationListener {
    private int xSize;
    private int ySize;
    public Tile[][] board;

    public SpriteBatch batch;
    public OrthographicCamera camera;

    public GameBoard(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;


    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
