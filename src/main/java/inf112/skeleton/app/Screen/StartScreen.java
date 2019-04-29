package inf112.skeleton.app.Screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import javafx.application.Application;

import java.awt.*;
import java.util.ArrayList;

public class StartScreen extends ApplicationAdapter implements Screen {
    Stage stage;
    TextButton button;
    private Skin skin;
    TextButton onePlayer;
    TextButton twoPlayers;
    TextButton threePlayers;
    TextButton fourPlayers;
    TextButton fivePlayers;
    TextButton sixPlayers;
    final int BUTTON_SIZE = 200;



    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("Buttons/glassy-ui.json"));
        onePlayer = createButton("1P", mySkin);
        twoPlayers = createButton("2P", mySkin);
        threePlayers = createButton("3P", mySkin);
        fourPlayers = createButton("4P", mySkin);

        onePlayer.setPosition(stage.getWidth()/4 - 50, stage.getHeight() - onePlayer.getHeight()*2);
        twoPlayers.setPosition(onePlayer.getX() + onePlayer.getWidth() + 50,stage.getHeight() - onePlayer.getHeight()*2);
        threePlayers.setPosition(stage.getWidth()/4 - 50, stage.getHeight() - onePlayer.getHeight()*4);
        fourPlayers.setPosition(threePlayers.getX() + threePlayers.getWidth() + 50, stage.getHeight() - onePlayer.getHeight()*4);

        // add a listener to your buttons so it does something when clicked
        onePlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("One Player!");

            }
        });

        twoPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("Two Players!");
            }
        });
    }

    private TextButton createButton(String label, Skin skin) {
        TextButton button = new TextButton(label, skin);
        stage.addActor(button);
        return button;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    public boolean hasSelectedPlayers() {
        return false;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("Buttons/glassy-ui.json"));
        onePlayer = createButton("1P", mySkin);
        twoPlayers = createButton("2P", mySkin);
        threePlayers = createButton("3P", mySkin);
        fourPlayers = createButton("4P", mySkin);

        onePlayer.setPosition(stage.getWidth()/4 - 50, stage.getHeight() - onePlayer.getHeight()*2);
        twoPlayers.setPosition(onePlayer.getX() + onePlayer.getWidth() + 50,stage.getHeight() - onePlayer.getHeight()*2);
        threePlayers.setPosition(stage.getWidth()/4 - 50, stage.getHeight() - onePlayer.getHeight()*4);
        fourPlayers.setPosition(threePlayers.getX() + threePlayers.getWidth() + 50, stage.getHeight() - onePlayer.getHeight()*4);

        // add a listener to your buttons so it does something when clicked
        onePlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("One Player!");

            }
        });

        twoPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("Two Players!");
            }
        });
    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {

    }
}
