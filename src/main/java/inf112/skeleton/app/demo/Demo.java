package inf112.skeleton.app.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Demo extends Game {
    private Stage stage;


    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Texture texture = new Texture(Gdx.files.internal("grayTile.jpg"));

        Image image1 = new Image(texture);
        image1.setPosition(Gdx.graphics.getWidth()/3-image1.getWidth()/2,Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
        stage.addActor(image1);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {

    }


}
