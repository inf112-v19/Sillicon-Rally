package inf112.skeleton.app.GameObjectTests;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


//import jdk.internal.util.xml.impl.Input;


public class PlayerTests {

    Player player;
    TileGrid grid;
    RoboGame roboGame;
    int TILE_SIZE_IN_PX;
    final int MAX_HP = 3;


    public void setup() {
        SetupVariables variables = new SetupVariables();
        roboGame = new RoboGame();
        grid = variables.grid;
        player = new Player(grid);
        TILE_SIZE_IN_PX = variables.gameMap.getTileSize();
    }

    @Test
    public void setPositionTest() {
        setup();
        player.setPosition(TILE_SIZE_IN_PX *2, TILE_SIZE_IN_PX*1, grid);

        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        Tile checkTile = grid.getTile(2, 1);
        System.out.println(player.getX());

        assertEquals(true, playerTile.equals(checkTile));
    }

    @Test
    public void moveOneWestForwardTest() {
        setup();
        player = new Player(grid);
        player.setPosition(2 * TILE_SIZE_IN_PX,2* TILE_SIZE_IN_PX,grid);
        player.moveStraight(1, grid.tileSizeInPx, grid);
        assertEquals(1 * TILE_SIZE_IN_PX, player.getX());
    }

    @Test
    void moveOneNorthForwardTest(){
        setup();
        player.setPosition(0 * TILE_SIZE_IN_PX,0 * TILE_SIZE_IN_PX,grid);
        player.setDirection(RoboGame.Direction.North);
        player.moveStraight(1,grid.tileSizeInPx, grid);
        assertEquals(1 * grid.tileSizeInPx,player.getY());
    }

    @Test
    void moveOneEastForwardTest(){
        setup();
        player.setPosition(2 * TILE_SIZE_IN_PX, 2 * TILE_SIZE_IN_PX, grid);
        player.setDirection(RoboGame.Direction.East);
        player.moveStraight(1, grid.tileSizeInPx, grid);
        assertEquals(3 * TILE_SIZE_IN_PX, player.getX());
    }

    @Test
    void moveOneSouthForwardTest(){
        setup();
        player.setPosition(2 * TILE_SIZE_IN_PX, 2 *TILE_SIZE_IN_PX, grid);
        player.setDirection(RoboGame.Direction.South);
        player.moveStraight(1, grid.tileSizeInPx, grid);
        assertEquals(1 *grid.tileSizeInPx, player.getY());
    }





    @Test
    void moveTwoForwardStraightTest(){
        setup();
        player = new Player(grid);
        player.setPosition(2 * TILE_SIZE_IN_PX,2* TILE_SIZE_IN_PX,grid);
        player.moveStraight(2, grid.tileSizeInPx, grid);
        assertEquals(0, player.getX());
    }
    @Test
    void MoveBackWardsOneTest(){
        setup();
        player.setPosition(2 * TILE_SIZE_IN_PX,2 * TILE_SIZE_IN_PX,grid);
        player.moveStraight(1,grid.tileSizeInPx * (-1), grid);
        assertEquals(3* TILE_SIZE_IN_PX, player.getX());
    }

    @Test
    void createPlayerTest(){
        setup();
        player = new Player(grid);
        player.setPosition(2* TILE_SIZE_IN_PX,2 * TILE_SIZE_IN_PX, grid);
        Tile testTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        assertEquals(RoboGame.Direction.West, player.getDirection());
        assertEquals(true, testTile.getGameObjects().contains(player));
    }



    @Test
    public void updateXPixelPositionTest() {
        setup();
        player.setX(135);
        assertEquals(135, player.getX());
    }

    @Test
    public void updateYPixelPositionTest() {
        setup();
        player.setY(135);
        assertEquals(135, player.getY());
    }

    @Test
    public void checkIfMoveIsOutOfBoundsTest() {
        int max = Integer.MAX_VALUE;
        setup();
        assertEquals(true, player.checkIfMoveIsOutOfBounds(-1, -1, grid));
        assertEquals(true, player.checkIfMoveIsOutOfBounds(max, max, grid));
    }

    @Test
    void playerHasDamageTokensTest() {
        setup();
        assertEquals(player.MAX_DAMAGE_TOKENS, player.playerTokens);
    }

    @Test
    void updatedTokensAfterLostlifeTest(){
        setup();
        player.handleDeath(grid);
        assertEquals(player.playerTokens, player.MAX_DAMAGE_TOKENS-1);
    }

    @Test
    void fewerTokensAfterLostLifeTest(){
        setup();
        player.handleDeath(grid);
        player.handleDeath(grid);
        assertEquals(player.playerTokens, player.MAX_DAMAGE_TOKENS-2);
    }

    @Test
    void PlayerTokensAreSetToZero(){
        setup();
        player.handleDeath(grid);
        player.handleDeath(grid);
        System.out.println(player.playerHP + ", " + player.playerTokens);
        player.handleDeath(grid);
        System.out.println(player.playerHP);
        assertEquals(player.playerTokens, 0);
    }

    @Test
    void shootTest() {
        SetupVariables setup = new SetupVariables();

        Player player = setup.player;
        player.setPosition(0,0, setup.grid);
        player.setDirection(RoboGame.Direction.North);

        Player otherPlayer = new Player(setup.grid);
        otherPlayer.setPosition(5 * setup.grid.tileSizeInPx, 0, setup.grid);
        otherPlayer.setDirection(RoboGame.Direction.South);

        assertEquals(MAX_HP, otherPlayer.playerHP);

        player.shootLaser(setup.grid);
        assertEquals(MAX_HP-1, otherPlayer.playerHP);

        otherPlayer.shootLaser(setup.grid);
        assertEquals(MAX_HP-1, player.playerHP);
    }



    @Test
    void FewerCardsAfterDamageTest(){
        setup();
        int initialMaxCards = player.maxCardsAllowedForPlayer;
        player.handleDeath(grid);
        System.out.println(player.maxCardsAllowedForPlayer);
        assertTrue(initialMaxCards > player.maxCardsAllowedForPlayer);
    }

}
