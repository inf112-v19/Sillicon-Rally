package inf112.skeleton.app.ScreenTests;

import inf112.skeleton.app.Screen.StartMenuScreen;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StartMenuTests {

    @Test
    public void cannotCreateMoreThanMaxPlayers(){
        StartMenuScreen start = new StartMenuScreen(true,6,0);
        start.numberOfPlayers = start.incrementPlayers(start.numberOfPlayers);
        assertEquals(1, start.numberOfPlayers);
    }


    @Test
    public void canIncrementNumberOfAIsTest(){
        StartMenuScreen start = new StartMenuScreen(true,0,1);
        start.numberOfAIs = start.incrementPlayers(start.numberOfAIs);
        assertEquals(2, start.numberOfAIs);
    }


    @Test
    public void nPlayersLimitnAIsTest(){
        StartMenuScreen start = new StartMenuScreen(true,4,2);
        start.numberOfAIs = start.incrementPlayers(start.numberOfAIs);
        assertEquals(1, start.numberOfAIs);
    }


    @Test
    public void nAIsLimitnPlayersTest(){
        StartMenuScreen start = new StartMenuScreen(true, 2,4);
        start.numberOfPlayers = start.incrementPlayers(start.numberOfPlayers);
        assertEquals(1, start.numberOfPlayers);
    }

}
