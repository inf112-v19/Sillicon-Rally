package inf112.skeleton.app.game;

import inf112.skeleton.app.Objects.Player;

import java.util.LinkedList;
import java.util.List;

public class RoundExecutor {
    List<Player> playerList;
    int playersTurn;

    public RoundExecutor(List<Player> playerList) {
        this.playerList = playerList;
        this.playersTurn = 0;
    }

    public void playPlayerNextCard() {
        Player player = playerList.get(playersTurn);

    }
}
