package inf112.skeleton.app.logic;

import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.game.RoboGame;

/**
 * Created by Martin on 22/03/2019.
 */
public class KeyHandling extends RoboGame implements InputProcessor {
    RoboGame game;

    public KeyHandling(){
        game.create();

    }
}
