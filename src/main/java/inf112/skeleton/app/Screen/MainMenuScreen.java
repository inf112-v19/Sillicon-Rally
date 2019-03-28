package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.RoboGame;


public class MainMenuScreen extends RoboGame implements Screen {
    private  RoboGame game;
    Texture startActive;
    Texture startInactive;
    Player player;


    public static final int WidthButton = 120;
    public static final int HeightButton = 35;
    private static final int centralizedX = (RoboGame.ROBO_GAME_WIDTH/2) - (WidthButton / 2);
    private static final int centralizedY = (RoboGame.ROBO_GAME_HEIGHT / 2) - (HeightButton /2);



    public MainMenuScreen(RoboGame game, Player player){
        this.game = game;
        this.player = player;
    }



    @Override
    public void show() {
        startActive = new Texture("Buttons/startActive.png");
        startInactive = new Texture("Buttons/startInactive.png");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) ;
        game.sb.begin();

        //checks if mouse is hovering over the button, opens gamescreen if then mouse is clicked
        if (Gdx.input.getX() < centralizedX + WidthButton && Gdx.input.getX() > centralizedX &&
                RoboGame.ROBO_GAME_HEIGHT - Gdx.input.getY() < centralizedY + HeightButton && RoboGame.ROBO_GAME_HEIGHT - Gdx.input.getY() > centralizedY){
            game.sb.draw(startActive, centralizedX, centralizedY, WidthButton, HeightButton);
            if (Gdx.input.isTouched()){
                game.setScreen(new GameScreen(game, player));
            }
        }
        else{
            game.sb.draw(startInactive, centralizedX, centralizedY, WidthButton, HeightButton);
        }
        game.sb.end();

    }

    @Override
    public void create() {
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


