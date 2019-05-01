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
import inf112.skeleton.app.game.RoboGame;

public class StartMenuScreen implements Screen {

    private final Stage stage;
    private final TextButton totalRobots;
    private final TextButton humanPlayers;
    private final Button startGame;


    public RoboGame game;
    public final int maxNumberOfRobots = 6;
    public int numberOfPlayers;
    int numberOfAIs;
    private boolean gameToBeStarted;

    public StartMenuScreen(RoboGame game) {
        this.game = game;
        numberOfPlayers = 0;
        numberOfAIs = 0;

        gameToBeStarted = false;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("Buttons/glassy-ui.json"));
        totalRobots = createButton("AIs "+ numberOfAIs, mySkin);
        humanPlayers = createButton("Human Players: " + numberOfPlayers, mySkin);
        startGame = createButton("Start Game", mySkin);


        totalRobots.setPosition(stage.getWidth()/2 - (stage.getWidth()/4) - 200, stage.getHeight() - totalRobots.getHeight()*2 -50);
        humanPlayers.setPosition(humanPlayers.getX() + humanPlayers.getWidth() + 200,stage.getHeight() - humanPlayers.getHeight()*2 -50);
        startGame.setPosition(humanPlayers.getX() + humanPlayers.getWidth() - 725,stage.getHeight() - humanPlayers.getHeight()*2 -300);

        // add a listener to your buttons so it does something when clicked
        totalRobots.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                //game.createPlayers(1);
                if (numberOfPlayers + numberOfAIs >= maxNumberOfRobots) {
                    numberOfAIs = 0;
                }
                numberOfAIs++;
                totalRobots.setText("AIs: " + numberOfAIs);
            }
        });

        humanPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                //System.out.println("Two Players!");
                if (numberOfPlayers + numberOfPlayers >= maxNumberOfRobots) {
                    numberOfPlayers = 0;
                }
                numberOfPlayers++;
                humanPlayers.setText("Human Players: " + numberOfPlayers);


            }
        });

        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                gameToBeStarted = true;
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

        if (gameToBeStarted) {
            game.createPlayers(numberOfPlayers);
            game.createAIs(numberOfAIs);
            game.setScreen(new GameScreen(game));
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
