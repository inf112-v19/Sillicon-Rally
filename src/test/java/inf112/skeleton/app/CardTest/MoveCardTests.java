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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoveCardTests {
	private ArrayList<Player> playerList;
	private ArrayList<MoveCard> cardList;
	private Round round;
	private Player player;
	private MoveCard card;
	private RoboGame game;
	private TileGrid grid;
	private StackOfCards deck;
	int TILE_SIZE_IN_PIX;
	Texture textureCard;		//texture does not matter in testing, it is used to avoid exceptions for classes demanding sprite for card

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
	void moveCardOneForwardTest(){
		setup();
		this.player = new Player(grid);
		player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
		player.setDirection(RoboGame.Direction.North);

		card = new MoveCard(MoveCard.Type.move1, textureCard);
		player.movePlayer(card.getType(), TILE_SIZE_IN_PIX, grid);

		assertEquals( 3 * TILE_SIZE_IN_PIX, player.getY());
	}

	@Test
	void moveCardOneBackwardTest(){
		setup();
		this.player = new Player(grid);
		player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
		player.setDirection(RoboGame.Direction.North);

		card = new MoveCard(MoveCard.Type.reverse, textureCard);
		player.movePlayer(card.getType(), TILE_SIZE_IN_PIX, grid);

		assertEquals( 1 * TILE_SIZE_IN_PIX, player.getY());
		assertEquals(2  * TILE_SIZE_IN_PIX, player.getX());

	}

	@Test
	void moveCardTwoForwardTest(){
		setup();
		this.player = new Player(grid);
		player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
		player.setDirection(RoboGame.Direction.North);

		card = new MoveCard(MoveCard.Type.move2, textureCard);
		player.movePlayer(card.getType(), TILE_SIZE_IN_PIX, grid);

		assertEquals( 4 * TILE_SIZE_IN_PIX, player.getY());
	}

	@Test
	void moveCardThreeForwardTest(){
		setup();
		this.player = new Player(grid);
		player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
		player.setDirection(RoboGame.Direction.North);

		card = new MoveCard(MoveCard.Type.move3, textureCard);
		player.movePlayer(card.getType(), TILE_SIZE_IN_PIX, grid);

		assertEquals( 5 * TILE_SIZE_IN_PIX, player.getY());
	}




	@Test
	void turnCardClockwiseTest(){
		setup();
		this.player = new Player(grid);
		player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
		player.setDirection(RoboGame.Direction.North);

		card = new MoveCard(MoveCard.Type.turnright, textureCard);
		player.movePlayer(card.getType(), TILE_SIZE_IN_PIX, grid);

		assertEquals(RoboGame.Direction.East, player.getDirection());
	}

	@Test
	void turnCardCounterClockwiseTest(){
		setup();
		card = new MoveCard(MoveCard.Type.turnleft, textureCard);
		player.setDirection(RoboGame.Direction.North);
		player.movePlayer(card.getType(), TILE_SIZE_IN_PIX, grid);

		assertEquals(RoboGame.Direction.West, player.getDirection());
	}


	@Test
	void uturnCardTest(){
		setup();
		this.player = new Player(grid);
		player.setPosition(2 * TILE_SIZE_IN_PIX, 2 * TILE_SIZE_IN_PIX, grid);
		card = new MoveCard(MoveCard.Type.uturn, textureCard);
		player.setDirection(RoboGame.Direction.North);
		player.movePlayer(card.getType(), TILE_SIZE_IN_PIX, grid);

		assertEquals(RoboGame.Direction.South, player.getDirection());
	}



}
