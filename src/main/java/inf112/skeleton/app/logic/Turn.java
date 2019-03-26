package inf112.skeleton.app.logic;

import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Martin and Vegard
 */
public class Turn {

	private ArrayList<Player> players;
	private int getNumberOfPlayer;
	RoboGame game;
	Tile tile;

	public Turn(RoboGame game){ //TODO fjern hardkodingen
		this.game = game;
		Player p1 = new Player();
		Player p2 = new Player();

		players.add(p1);
		players.add(p2);

		for (Player p: players) {
			doTurn(p);

		}
	}

	private void doTurn(Player player) {
		Scanner in = new Scanner(System.in);
		int choose = in.nextInt()-1;
		game.movePlayer(choose, game.TILE_SIZE_IN_PX, game.grid.getTileFromCoordinates(player.getY(), player.getX()));

	}


}