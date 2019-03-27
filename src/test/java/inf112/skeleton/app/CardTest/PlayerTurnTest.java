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
	private Tile tile;



	//Player default position (3 ,3)
	@Test
	public void doTurnTest(){
		SetupVariables setup = new SetupVariables();
		Texture textureMove1 = new Texture("cardLayouts/Move1.png");
		Texture textureMove2 = new Texture("cardLayouts/Move2.png");

		MoveCard card1 = new MoveCard(MoveCard.Type.move1, textureMove1);
		MoveCard card2 = new MoveCard(MoveCard.Type.move2, textureMove2);
		ArrayList<MoveCard> testDeck = new ArrayList<>();

		testDeck.add(card1);
		testDeck.add(card2);

		Player p1 = new Player();
		//player.setX(3*grid.tileSizeInPx);
		//player.setY(3*grid.tileSizeInPx);
		round.players.add(p1);

		setup.playerDefaultPosition(p1);


		round.doTurn();
		assertEquals(grid.getTile(6,3),player.getY());


	}

}
