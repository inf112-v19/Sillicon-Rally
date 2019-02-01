package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.demo.Demo;
import inf112.skeleton.app.demo.TiledTest;


public class Main {


    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "hello-world";
        cfg.width = 2160;
        cfg.height = 1440;

       // new LwjglApplication(new HelloWorld(), cfg);
        new LwjglApplication(new TiledTest(), cfg);



    }
}