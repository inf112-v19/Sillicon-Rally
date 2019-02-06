package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.demo.Demo;
import inf112.skeleton.app.demo.Game;
import inf112.skeleton.app.demo.Scene2D;
import inf112.skeleton.app.demo.TiledTest;


public class Main {


    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "hello-world";
        cfg.width = 1080;
        cfg.height = 720;

        //new LwjglApplication(new HelloWorld(), cfg);
      //  new LwjglApplication(new TiledTest(), cfg);
        new LwjglApplication(new Game(), cfg);


        /**
        StackOfCards stack = new StackOfCards();
        for (int i = 0; i < 84; i++) {
            System.out.println(stack.drawOne());
        }
        */



    }
}