package inf112.skeleton.app.logic;

import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;

import java.util.ArrayList;

/**
 * Created by Martin on 27/02/2019.
 */
public class Round {

    private int turnNumber;
    private MoveCard card;
    private ArrayList<MoveCard> listToChooseFrom;
    private StackOfCards deck;
    private Boolean[] booList;

    public Round(StackOfCards deck123) {
        this.deck = deck123;
        drawNineCards(deck);
        chooseFiveCards();


    }

    private void chooseFiveCards() {
        booList = new Boolean[9];
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


}
