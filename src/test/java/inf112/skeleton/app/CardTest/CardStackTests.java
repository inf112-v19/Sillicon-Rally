package inf112.skeleton.app.CardTest;

import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CardStackTests {
    private ArrayList<MoveCard> deck;
    StackOfCards stackOfCards;



    void setup() {
        SetupVariables variables = new SetupVariables();
        this.deck = variables.cardList;

    }


    @Test
    void rightAmountOfUniqueCardsTest(){
        setup();
        int i = 0;
        assertEquals(5,deck.size());
        for (MoveCard.Type card: MoveCard.Type.values()) {
            i++;
        }
        assertEquals(7, i);
    }

    @Test
    void rightAmountOfCardsTest(){
        setup();
        stackOfCards = new StackOfCards();
        assertEquals(84, stackOfCards.getSize());
    }

    @Test
    void stackIsShuffledTest(){
        setup();
        stackOfCards = new StackOfCards();
        boolean isShuffled = true;
        MoveCard card1 = stackOfCards.stack.pop();
        MoveCard card2 = stackOfCards.stack.pop();
        MoveCard card3 = stackOfCards.stack.pop();
        MoveCard card4 = stackOfCards.stack.pop();
        if ((card1 == card2) && (card2 == card3) && (card3 == card4)){
            isShuffled = false;
        }
        assertTrue(isShuffled);
    }
}
