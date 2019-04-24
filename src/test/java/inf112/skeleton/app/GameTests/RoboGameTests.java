package inf112.skeleton.app.GameTests;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.logic.Round;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class RoboGameTests {
    private ArrayList<Player> playerList;
    private ArrayList<MoveCard> cardList;
    private Round round;
    private Player player;
    private MoveCard card;
    private RoboGame game;
    private TileGrid grid;
    private StackOfCards deck;
    int TILE_SIZE_IN_PIX;
    Texture textureCard;		//texture does not matter in testing, it is used to avoid exceptions for classes demading sprite for card

    public void setup() {
        SetupVariables variables = new SetupVariables();
        grid = variables.grid;
        player = variables.player;
        playerList = new ArrayList<>();
        player.setPosition(2 * TILE_SIZE_IN_PIX,2 * TILE_SIZE_IN_PIX, grid);
        playerList.add(player);
        System.out.println(player);
        cardList = new ArrayList<>();
        round = new Round(new StackOfCards(), playerList);
        TILE_SIZE_IN_PIX = variables.TILESIZE_IN_PX;
        textureCard = new Texture("cardLayouts/Move1.png");
    }


    @Test
    void getCurrentPlayerTest(){
        game.currentPlayer =0;
        assertEquals(player, game.getCurrentPlayer());
    }

}
