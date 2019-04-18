package inf112.skeleton.app.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.Screen.MainMenuScreen;
import inf112.skeleton.app.Screen.StartScreen;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Silicon_Rally";
        cfg.width = RoboGame.ROBO_GAME_WIDTH;
        cfg.height = RoboGame.ROBO_GAME_HEIGHT;
        cfg.resizable = true;


        new LwjglApplication(new StartScreen(), cfg);
       // new LwjglApplication(new RoboGame(), cfg);
    }
}