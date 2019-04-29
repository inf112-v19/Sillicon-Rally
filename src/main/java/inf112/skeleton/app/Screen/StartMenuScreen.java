package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.RoboGame;

public class StartMenuScreen implements Screen {

    private final Stage stage;
    private final Button onePlayer;
    private final Button twoPlayers;
    private final Button threePlayers;
    private final Button fourPlayers;
    public RoboGame game;
    public int numberofPlayers;

    public StartMenuScreen(RoboGame game) {
        this.game = game;
        this.numberofPlayers = 0;


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
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("One players!");
                game.createPlayers(1);
                numberofPlayers = 1;
            }
        });

        twoPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("Two Players!");
                numberofPlayers = 2;
            }
        });

        threePlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                numberofPlayers = 3;
            }
        });

        fourPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                numberofPlayers = 4;
            }
        });

    }

    private TextButton createButton(String label, Skin skin) {
        TextButton button = new TextButton(label, skin);
        stage.addActor(button);
        return button;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (this.numberofPlayers > 0) {
            game.createPlayers(numberofPlayers);
        }
    }

    @Override
    public void resize(int i, int i1) {

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
