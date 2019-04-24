package inf112.skeleton.app.game;

import inf112.skeleton.app.Objects.Player;

import java.util.List;

public class RoundExecutor {
    private RoboGame game;
    List<Player> playerList;
    int playersTurn;
    public boolean isCurrentlyExecutingRound;
    public boolean shootLaserNow;

    public int localRoundCounter=1;

    public RoundExecutor(List<Player> playerList, RoboGame g) {
        this.game = g;
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
        sleep(300);

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
        Player player = playerList.get(playersTurn);

        if(player.powerDownOn==localRoundCounter) {
            player.moveCardQueue.clear();
            player.powerDown();
            setNextPlayersTurn();
            player.executeCard();
        }
        else {
            player.executeNextCard();
        checkCollisions();
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
        for (Player player : playerList){
            if (!player.moveCardQueue.isEmpty())
                return false;

            player.roundNr=localRoundCounter;
            //System.out.println("(player) current round: " + player.roundNr);
        }
        localRoundCounter++;
        //System.out.println("(local) current round: " + localRoundCounter);

        return true;
    }

    private void setNextPlayersTurn(){
        int currentPlayer = playersTurn;
        this.playersTurn = (currentPlayer+1) % playerList.size();

    }


}
