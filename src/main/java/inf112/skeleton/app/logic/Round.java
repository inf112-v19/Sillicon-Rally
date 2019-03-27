package inf112.skeleton.app.logic;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;

import java.util.ArrayList;

/**
 * Created by Martin on 27/02/2019.
 */
public class Round {

    //roundNumber (int) ?
    private MoveCard card;
    private ArrayList<MoveCard> listToChooseFrom;
    private ArrayList<Player> players;
    private StackOfCards deck;
    private boolean[] booList;


    public Round(StackOfCards deck, ArrayList<Player> playerList) {
        this.deck = deck;
        players = playerList;

        drawNineCards(deck);

        for (Player p : players) {
            chooseFiveCards();
        }


        doTurn();


    }

    private void chooseFiveCards() {
        //booList = new Boolean[9];
        for (int i = 0; i < 5; i++) {
            
        }
    }

    private ArrayList<MoveCard> drawNineCards(StackOfCards deck) {
        listToChooseFrom = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            card = deck.nextCard();
            //card.setPosition, setSize;
            listToChooseFrom.add(card);
        }
        return listToChooseFrom;
    }

    public void doTurn() {

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
