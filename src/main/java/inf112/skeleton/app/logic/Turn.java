package inf112.skeleton.app.logic;

import com.badlogic.gdx.Game;
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
	Player player;


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
		if(checkList(booList)){
			return;
		}
	}
	private void chooseFive(Player player){

	}

	public boolean checkList(boolean[] bool){
		int counter = 0;
		boolean temp = false;
		for (int i = 0; i < bool.length; i++) {
			if (bool[i]){
				counter++;
			}
		}
		if (counter==5){
			temp=true;
		}
		return temp;
	}


}