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
	private Round round;
	private Player player;
	private MoveCard card;
	private RoboGame game;
	private TileGrid grid;
	private StackOfCards deck;




	//Player default position (4 ,4)
	@Test
	public void doTurnTestAndMoveTest(){
		SetupVariables setup = new SetupVariables();
		this.round = setup.round;
		this.player = setup.player;
		this.card = setup.card;
		this.grid = setup.grid;
		this.deck = setup.deck;


		ArrayList<MoveCard> testDeck = new ArrayList<>();



		Player p1 = new Player();
		//player.setX(3*grid.tileSizeInPx);
		//player.setY(3*grid.tileSizeInPx);
		round.players.add(p1);

		setup.playerDefaultPosition(p1);

		round.doTurn();
		assertEquals(grid.getTile(1*grid.tileSizeInPx,4*grid.tileSizeInPx),     player.getY());


	}

}
