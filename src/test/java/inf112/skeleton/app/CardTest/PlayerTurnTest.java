package inf112.skeleton.app.CardTest;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.logic.Round;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;


public class PlayerTurnTest {

	private Round round;
	private Player player;
	private ArrayList<Player> playerlist;
	private ArrayList<MoveCard> testDeck;
	private MoveCard card;
	private TileGrid grid;
	private StackOfCards deck;
	private RoboGame game;

	//Player default position (0 ,4)
	@Test
	public void doTurnTestAndMoveTest(){ //perfectTitle
		SetupVariables setup = new SetupVariables();

		this.playerlist= setup.playerList;
		this.player = setup.player;
		this.grid = setup.grid;
		this.testDeck = setup.cardList;
		this.round = setup.round;

		for(Player p: playerlist){
			setup.playerDefaultPosition(p);
		}
		Player player1 = playerlist.get(0);
		Player player2 = playerlist.get(1);

		player1.setDirection(RoboGame.Direction.North);
		player2.setDirection(RoboGame.Direction.North);



		round.doTurn(testDeck, playerlist, grid);

		assertEquals((float) 4*grid.tileSizeInPx, player1.getY());
		assertEquals((float) 4*grid.tileSizeInPx, player1.getX());
		assertEquals((float) 2*grid.tileSizeInPx, player2.getY());
		assertEquals((float) 4*grid.tileSizeInPx, player2.getX());



		assertEquals(RoboGame.Direction.North, player1.getDirection());
		assertEquals(RoboGame.Direction.East, player2.getDirection());

		/*					2play	3play	4play	5play
		1. move1			p1		p1		p1		p1
		2. move2			p2		p2		p2		p2
		3. move2			p1		p3		p3		p3
		4. turn right		p2		p1		p4		p4
		5. move1			p1		p2		p1		"fuck you kristian!"
		 */

	}

}
