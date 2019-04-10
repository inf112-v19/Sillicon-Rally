package inf112.skeleton.app.game;

import inf112.skeleton.app.Objects.Player;

import java.util.List;

public class RoundExecutor {
    List<Player> playerList;
    int playersTurn;
    public boolean isCurrentlyExecutingRound;
    public int roundNr=1;

    public RoundExecutor(List<Player> playerList) {
        this.playerList = playerList;
        this.playersTurn = 0;
        this.isCurrentlyExecutingRound = false;
    }

    public void playPlayerNextCard() {
        if (roundIsDone() && isCurrentlyExecutingRound == true) {
            isCurrentlyExecutingRound = false;
            checkCollisions();
            return;
        }

        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
        Player player = playerList.get(playersTurn);

        if(player.powerDownOn==roundNr) {
            player.moveCardList.clear();
            player.powerDown();
            setNextPlayersTurn();
            player.powerDownOn=0;

        }
        else {
            player.executeNextCard();
            setNextPlayersTurn();
        }
    }

    private void checkCollisions() {
        for (Player player : playerList) {
            player.checkCollision();
            player.checkForDamageTaken();
        }
    }

    private boolean roundIsDone() {
        roundNr++;
        for (Player player : playerList){
            if (!player.moveCardList.isEmpty())
                return false;
        }
        return true;
    }

    private void setNextPlayersTurn(){
        int currentPlayer = playersTurn;
        this.playersTurn = (currentPlayer+1) % playerList.size();

    }


}
