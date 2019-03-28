package inf112.skeleton.app.CardTest;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.logic.Round;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;


public class PlayerTurnTest {

	private Player player;
	private ArrayList<Player> playerlist;
	private ArrayList<MoveCard> testDeck;
	private MoveCard card;
	private RoboGame game;
	private TileGrid grid;
	private StackOfCards deck;


	//Player default position (0 ,4)
	@Test
	public void doTurnTestAndMoveTest(){
		SetupVariables setup = new SetupVariables();

		this.playerlist=setup.playerList;
		this.player = setup.player;
		this.grid = setup.grid;
		this.testDeck = setup.cardList;

		for(Player p: playerlist){
			setup.playerDefaultPosition(p);
		}
		Player player1 = playerlist.get(0);
		Player player2 = playerlist.get(1);



		Round.doTurn(testDeck, playerlist, grid);
		assertEquals(4*grid.tileSizeInPx, player1.getY());
		assertEquals(4*grid.tileSizeInPx, player1.getX());
		assertEquals(2*grid.tileSizeInPx, player2.getY());
		assertEquals(4*grid.tileSizeInPx, player2.getX());



		assertEquals(RoboGame.Direction.North, player1.currentDirection);
		assertEquals(RoboGame.Direction.East, player2.currentDirection);


	}

}
