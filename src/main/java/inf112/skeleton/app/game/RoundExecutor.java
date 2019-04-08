package inf112.skeleton.app.game;

import inf112.skeleton.app.Objects.LaserAnimation;
import inf112.skeleton.app.Objects.Player;

import java.util.List;

public class RoundExecutor {
    List<Player> playerList;
    int playersTurn;
    public boolean isCurrentlyExecutingRound;
    public boolean shootLaserNow;

    public RoundExecutor(List<Player> playerList) {
        this.playerList = playerList;
        this.playersTurn = 0;
        this.isCurrentlyExecutingRound = false;
        this.shootLaserNow = false;
    }

    public void playPlayerNextCard() {
        if (roundIsDone() && isCurrentlyExecutingRound == true) {
            isCurrentlyExecutingRound = false;
            checkCollisions();
            playerShootLaser();
            return;
        }
        sleep(0);

        Player player = playerList.get(playersTurn);
        player.executeNextCard();
        setNextPlayersTurn();

    }

    private void playerShootLaser() {
        this.shootLaserNow = true;
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {

        }
    }

    private void checkCollisions() {
        for (Player player : playerList) {
            player.checkCollision();
            player.checkForDamageTaken();
        }
    }

    private boolean roundIsDone() {
        for (Player player : playerList){
            if (!player.moveCardList.isEmpty())
                return false;
        }
        return true;
    }

    private void setNextPlayersTurn() {
        int currentPlayer = playersTurn;
        this.playersTurn = (currentPlayer+1) % playerList.size();

    }


}
