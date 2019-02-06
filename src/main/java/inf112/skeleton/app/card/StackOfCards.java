package inf112.skeleton.app.card;

import inf112.skeleton.app.util.MyStack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Martin on 01/02/2019.
 */
public class StackOfCards {

    public MyStack<MoveCard> stack = new MyStack<>(90);

    public StackOfCards() {
        ArrayList<MoveCard> cards = new ArrayList<>();

        for (int i = 0; i < 18; i++) {
            //create and add turnleft, turnright, move1 cards to list
            MoveCard card1 = new MoveCard(MoveCard.Type.move1);
            MoveCard card2 = new MoveCard(MoveCard.Type.turnleft);
            MoveCard card3 = new MoveCard(MoveCard.Type.turnright);
            cards.add(card1);
            cards.add(card2);
            cards.add(card3);
        }

        for (int i = 0; i < 6; i++) {
            //create and add move3, uturn, backup cards to list
            MoveCard card1 = new MoveCard(MoveCard.Type.move3);
            MoveCard card2 = new MoveCard(MoveCard.Type.uturn);
            MoveCard card3 = new MoveCard(MoveCard.Type.backup);
            cards.add(card1);
            cards.add(card2);
            cards.add(card3);
        }

        for (int i = 0; i < 12; i++) {
            //create and add move2 cards to list
            MoveCard card1 = new MoveCard(MoveCard.Type.move2);
            cards.add(card1);
        }

        //shuffle list
        Collections.shuffle(cards);

        //add all 84 card to Stack.
        for (int i = 0; i < 84; i++) {
            MoveCard temp = cards.get(i);
            stack.push(temp);
        }
    }

    public MoveCard drawOne() {
        return stack.pop();
    }

}