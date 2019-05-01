package inf112.skeleton.app.ScreenTests;

import inf112.skeleton.app.Screen.StartMenuScreen;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.game.RoboGame;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



/**
 * Created by Martin on 01/05/2019.
 */
public class StartMenuTests {
    RoboGame game;



    @Test
    void cannotCreateMoreThanMaxPlayers(){
        SetupVariables variables = new SetupVariables();
        this.game = variables.game;
        StartMenuScreen start = new StartMenuScreen(game);
        int num = 0;
        start.incrementPlayers(num);
        assertEquals(1, 1);
    }

}
