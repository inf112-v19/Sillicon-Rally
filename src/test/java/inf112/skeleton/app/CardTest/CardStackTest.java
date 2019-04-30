package inf112.skeleton.app.CardTest;

import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CardStackTest {
    private ArrayList<MoveCard> deck;
    StackOfCards stackOfCards;



    void setup() {
        SetupVariables variables = new SetupVariables();
        this.deck = variables.cardList;

    }



    @Test
    void DesiredAmountOfUniqueCards(){
        setup();
        int i = 0;
        assertEquals(5,deck.size());
        for (MoveCard.Type card: MoveCard.Type.values()) {
            i++;
        }
        assertEquals(7, i);
    }

    @Test
    void desiredAmountOfCards(){
        setup();
        stackOfCards = new StackOfCards();
        assertEquals(84, stackOfCards.getSize());
    }
}
