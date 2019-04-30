package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.collision.objects.GameObjectFactory;
import inf112.skeleton.app.game.RoboGame;

import java.awt.*;


public class MainMenuScreen extends RoboGame implements Screen {
    private  RoboGame game;
    private GameObjectFactory factory;

    Texture startActive;
    Texture startInactive;
    Texture Player1Active;
    Texture Player1InActive;
    Texture Player2Active;
    Texture Player2InActive;

    Player player;



    public static final int WidthButton = 120;
    public static final int HeightButton = 35;
    private static final int centralizedX = (RoboGame.ROBO_GAME_WIDTH/2) - (WidthButton / 2);
    private static final int centralizedY = (RoboGame.ROBO_GAME_HEIGHT / 2) - (HeightButton /2);
    private static final int ButtonGap = 30;



    @Override
    public void show() {
        startActive = new Texture("Buttons/startActive.png");
        startInactive = new Texture("Buttons/startInactive.png");

        Player1Active = new Texture("Buttons/Player1Active.png");
        Player1InActive = new Texture("Buttons/Player1InActive.png");

        Player2Active = new Texture("Buttons/Player2Active.png");
        Player2InActive = new Texture("Buttons/Player2InActive.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) ;
        game.sb.begin();

        //checks if mouse is hovering over the button, opens gamescreen if then mouse is clicked
        player1ButtonStatus();
        player2ButtonStatus();
        game.sb.end();

    }

    private void player1ButtonStatus(){
        if (Gdx.input.getX() < centralizedX - ButtonGap + WidthButton && Gdx.input.getX() > centralizedX  &&
                RoboGame.ROBO_GAME_HEIGHT - Gdx.input.getY() < centralizedY - ButtonGap+ HeightButton && RoboGame.ROBO_GAME_HEIGHT - Gdx.input.getY() > centralizedY - ButtonGap){
            game.sb.draw(Player1Active, centralizedX, centralizedY - ButtonGap, WidthButton, HeightButton);
            if (Gdx.input.isTouched()){
                removePlayer(game.player2);
                setScreen();
            }
        }
        else{
            game.sb.draw(Player1InActive, centralizedX , centralizedY - ButtonGap, WidthButton, HeightButton);
        }
    }

    private void player2ButtonStatus(){
        if (Gdx.input.getX() < centralizedX + ButtonGap + WidthButton && Gdx.input.getX() > centralizedX + ButtonGap&&
                RoboGame.ROBO_GAME_HEIGHT - Gdx.input.getY() < centralizedY + ButtonGap + HeightButton && RoboGame.ROBO_GAME_HEIGHT - Gdx.input.getY() > centralizedY + ButtonGap){
            game.sb.draw(Player2Active, centralizedX , centralizedY + ButtonGap, WidthButton, HeightButton);
            if (Gdx.input.isTouched()){
               setScreen();
            }
        }
        else{
            game.sb.draw(Player2InActive, centralizedX, centralizedY + ButtonGap, WidthButton, HeightButton);
        }
    }


    private void setScreen(){
        GameScreen gameScreen = new GameScreen(game);
        game.setGameScreen(gameScreen);
        game.setScreen(gameScreen);
    }

    private void removePlayer(Player thisPlayer){
        float y = thisPlayer.getY();
        float x = thisPlayer.getX();
        factory.removePlayerSprite(game, thisPlayer);
        game.playerList.remove(thisPlayer);

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


