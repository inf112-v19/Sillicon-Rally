package inf112.skeleton.app.CardTest;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.logic.Round;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoveCardTest {
	private Round round;
	private Player player;
	private MoveCard card;
	private RoboGame game;
	private TileGrid grid;
	private StackOfCards deck;
	private int TILE_SIZE_IN_PIX;

	@Test
	void MoveCardTest(){
		SetupVariables setup = new SetupVariables();
		this.grid = setup.grid;

		this.player = new Player();
		player.setPosition(2* TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
		Texture textureMove1 = new Texture("cardLayouts/Move1.png");
		card = new MoveCard(MoveCard.Type.move1, textureMove1);

		assertEquals(3 * TILE_SIZE_IN_PIX, player.getY());
	}
}
