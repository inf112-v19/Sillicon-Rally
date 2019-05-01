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
    private final TextButton totalPlayers;
    private final TextButton humanPlayers;
    private final Button startGame;


    public RoboGame game;
    public int numberOfRobots;
    public int numberOfPlayers;
    private boolean gameToBeStarted;

    public StartMenuScreen(RoboGame game) {
        this.game = game;
        numberOfRobots = 0;
        numberOfPlayers = 0;
        gameToBeStarted = false;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("Buttons/glassy-ui.json"));
        totalPlayers = createButton("Total Robots: "+ numberOfRobots, mySkin);
        humanPlayers = createButton("Human Players: " + numberOfPlayers, mySkin);
        startGame = createButton("Lets GO!", mySkin);


        totalPlayers.setPosition(stage.getWidth()/2 - (stage.getWidth()/4) - 120, stage.getHeight() - totalPlayers.getHeight()*2 -50);
        humanPlayers.setPosition(humanPlayers.getX() + humanPlayers.getWidth() + 200,stage.getHeight() - humanPlayers.getHeight()*2 -50);


        // add a listener to your buttons so it does something when clicked
        totalPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                //game.createPlayers(1);
                if (numberOfRobots == 6) {
                    numberOfRobots = 0;
                }
                numberOfRobots++;
                totalPlayers.setText("Total Player: " + numberOfRobots);
            }
        });

        humanPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                //System.out.println("Two Players!");
                if (numberOfPlayers >= numberOfRobots) {
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

       /*onePlayer = createButton("1P", mySkin);
        twoPlayers = createButton("2P", mySkin);
        threePlayers = createButton("3P", mySkin);
        fourPlayers = createButton("4P", mySkin);
        fivePlayers = createButton("5P", mySkin);
        sixPlayers = createButton("6P", mySkin);

        onePlayer.setPosition(stage.getWidth()/2 - (stage.getWidth()/4), stage.getHeight() - onePlayer.getHeight()*2);
        twoPlayers.setPosition(onePlayer.getX() + onePlayer.getWidth() + 50,stage.getHeight() - onePlayer.getHeight()*2);

        threePlayers.setPosition(stage.getWidth()/2 - (stage.getWidth()/4), stage.getHeight() - onePlayer.getHeight()*4);
        fourPlayers.setPosition(threePlayers.getX() + threePlayers.getWidth() + 50, stage.getHeight() - onePlayer.getHeight()*4);

        fivePlayers.setPosition(stage.getWidth()/2 - (stage.getWidth()/4), stage.getHeight() - onePlayer.getHeight()*6);
        sixPlayers.setPosition(fivePlayers.getX() + fivePlayers.getWidth() + 50, stage.getHeight() -onePlayer.getHeight()*6);

        // add a listener to your buttons so it does something when clicked
        onePlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("One players!");
                game.createPlayers(1);
                numberOfRobots = 1;
            }
        });

        twoPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("Two Players!");
                numberOfRobots = 2;
            }
        });

        threePlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                numberOfRobots = 3;
            }
        });

        fourPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                numberOfRobots = 4;
            }
        });

       fivePlayers.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
               numberOfRobots = 5;
           }
       });

       sixPlayers.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
             numberOfRobots = 6;
           }
       });*/
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
            game.createPlayers(numberOfRobots);
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
