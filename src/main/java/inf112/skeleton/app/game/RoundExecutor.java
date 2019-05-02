package inf112.skeleton.app.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.StackOfCards;

import java.util.ConcurrentModificationException;
import java.util.List;

public class RoundExecutor {
    private RoboGame game;
    List<Player> playerList;
    public static int playersTurn;
    public boolean isCurrentlyExecutingRound;
    public boolean shootLaserNow;
    private BitmapFont font = new BitmapFont();


    public int localRoundCounter=1;

    public RoundExecutor(List<Player> playerList, RoboGame g) {
        this.game = g;
        this.playerList = playerList;
        playersTurn = g.currentPlayer;
        this.isCurrentlyExecutingRound = false;
        this.shootLaserNow = false;
    }

    public void playPlayerNextCard() {
        if (playerList.size() > 0) {
            if (roundIsDone() && isCurrentlyExecutingRound == true) {
                isCurrentlyExecutingRound = false;
                checkCollisions();
                playerShootLaser();
                game.deck = new StackOfCards();
                return;
            }
            sleep(300);

            if (playerList.size() > 0) {
                Player player = playerList.get(playersTurn);
                player.executeNextCard();
                setNextPlayersTurn();
            }
        }

    }

    private void playerShootLaser() {
        this.shootLaserNow = true;
    }

    private void sleep(int i) {
        try {Thread.sleep(i);}
        catch (Exception e) {}
    }

    private void checkCollisions() {
        try {
            for (Player player : playerList) {
                player.checkCollision();
                player.checkForDamageTaken();
            }
        } catch (ConcurrentModificationException e) {

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
        if (playerList.size() > 0) {
            int currentPlayer = playersTurn;
            this.playersTurn = (currentPlayer + 1) % playerList.size();
            drawCurrentPlayer();
        }
    }



    private void drawCurrentPlayer() {
        game.sb.begin();
            font.getData().setScale(3);
            font.setColor(new Color(Color.GREEN));
            String str = "player executing move: " + playerList.get(playersTurn);

            font.draw(game.sb, str, 360,-10);
            game.sb.end();
    }


}
