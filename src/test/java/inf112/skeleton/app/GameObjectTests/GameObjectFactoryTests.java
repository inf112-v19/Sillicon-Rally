package inf112.skeleton.app.GameObjectTests;

import com.badlogic.gdx.Game;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.collision.objects.GameObjectFactory;
import inf112.skeleton.app.collision.objects.LaserObject;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class GameObjectFactoryTests {
    private Player player;
    private GameMap map;
    private RoboGame game;
    private TileGrid grid;
    private GameObjectFactory factory;



    private void setup(){
        SetupVariables variables = new SetupVariables();
        player = variables.player;
        map = variables.gameMap;
        grid = variables.grid;
        factory = new GameObjectFactory(map, grid);
        game = variables.game;
        game.playerList = new ArrayList<>();
    }


    @Test
    void playerOneRightTexturTest(){
        setup();
        factory.createPlayers(1,game);
        String expectedString = ("RobotSprites/robot1.png");
        assertEquals(expectedString, GameObjectFactory.playerTexture.toString());
    }

    @Test
    void canCreateOnePlayerTest(){
        setup();
        factory.createPlayers(1,game);
        assertEquals(1, game.playerList.size());
    }


    @Test
    void createTwoPlayersTest(){
        setup();
        factory.createPlayers(2,game);
        assertEquals(2, game.playerList.size());
    }

    @Test
    void createThreePlayersTest(){
        setup();
        factory.createPlayers(3,game);
        assertEquals(3, game.playerList.size());
    }

    @Test
    void createFourPlayersTest(){
        setup();
        factory.createPlayers(4, game);
        assertEquals(4, game.playerList.size());
    }



    @Test
    void playersGetRightTextureTest(){
        setup();
        factory.createPlayers(4,game);
        String playerOne = "RobotSprites/robot1.png";
        String playerTwo = "RobotSprites/robot2.png";
        String playerThree = "RobotSprites/robot3.png";
        String playerFour = "RobotSprites/robot4.png";
        assertEquals(game.playerList.get(0).texture.toString(), playerOne);
        assertEquals(game.playerList.get(1).texture.toString(), playerTwo);
        assertEquals(game.playerList.get(2).texture.toString(), playerThree);
        assertEquals(game.playerList.get(3).texture.toString(), playerFour);
    }
}
