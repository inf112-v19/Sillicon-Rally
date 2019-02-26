package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.demo.Game;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Silicon_Rally";
        cfg.width = 1080;
        cfg.height = 720;

        //new LwjglApplication(new HelloWorld(), cfg);
        //new LwjglApplication(new TiledTest(), cfg);

        new LwjglApplication(new Game(), cfg);


    }
}