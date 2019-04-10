package inf112.skeleton.app.game;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.Objects.Player;

import java.util.List;

public class DrawCards {
    List<Player> playerList;
    int playersTurn;
    RoboGame game;
    int cardsDrawByPlayer;
    private boolean playersFinishedPickingCards;

    public DrawCards(RoboGame game) {
        this.playerList = game.playerList;
        this.game = game;
        this.playersTurn = 0;
        this.playersFinishedPickingCards = false;
    }

    public void drawCards() {
        Player playerToDraw = playerList.get(playersTurn);
        Gdx.input.setInputProcessor(playerToDraw);
        this.cardsDrawByPlayer = playerList.get(playersTurn).moveCardQueue.size();

        if ((cardsDrawByPlayer != 0) && cardsDrawByPlayer % 5 == 0)
            setNextPlayersTurn();
    }

    public void setNextPlayersTurn() {
        int currentPlayer = playersTurn;
        this.playersTurn = (currentPlayer+1) % playerList.size();
        drawNewCardsFromDeck();
        cardsDrawByPlayer = 0;

        if (allPlayersDone()) {
            playersFinishedPickingCards = true;
            //executeAllMoves();
            game.putCardsBackInDeck();
            game.drawNineCardsFromDeck();
        }
    }

    private void executeAllMoves() {
        for (Player player : playerList) {
            player.executeCard();
        }
    }

    private void drawNewCardsFromDeck() {
        game.drawNineCardsFromDeck();
    }

    public boolean allPlayersDone() {

        for (Player player : playerList) {
            if (player.moveCardQueue.size() != 5)
                return false;
        }

        return true;
    }

    public boolean playersFinishedPickingCards() {
        return this.playersFinishedPickingCards;
    }

    public void startPickingCards() {
        this.playersFinishedPickingCards = false;
    }
}
