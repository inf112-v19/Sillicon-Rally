package inf112.skeleton.app.card;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.util.MyStack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Martin on 01/02/2019.
 */
public class StackOfCards {


    public static MyStack<MoveCard> stack = new MyStack<>();

    public StackOfCards() {
        ArrayList<MoveCard> cards = new ArrayList<>();
        Texture textureMove1 = new Texture("cardLayouts/Move1.png");
        Texture textureMove2 = new Texture("cardLayouts/Move2.png");
        Texture textureMove3 = new Texture("cardLayouts/Move3.png");
        Texture textureUTurn = new Texture("cardLayouts/UTurn.png");
        Texture textureRightTurn = new Texture("cardLayouts/RightTurn.png");
        Texture textureLeftTurn = new Texture("cardLayouts/LeftTurn.png");
        Texture textureReversing = new Texture("cardLayouts/Reverse1.png");


        for (int i = 0; i < 18; i++) {
            //create and add turnleft, turnright, move1 cards to list
            MoveCard card1 = new MoveCard(MoveCard.Type.move1, textureMove1);
            MoveCard card2 = new MoveCard(MoveCard.Type.turnleft, textureLeftTurn);
            MoveCard card3 = new MoveCard(MoveCard.Type.turnright, textureRightTurn);
            cards.add(card1);
            cards.add(card2);
            cards.add(card3);
        }

        for (int i = 0; i < 6; i++) {
            //create and add move3, uturn, reverse cards to list
            MoveCard card1 = new MoveCard(MoveCard.Type.move3, textureMove3);
            MoveCard card2 = new MoveCard(MoveCard.Type.uturn, textureUTurn);
            MoveCard card3 = new MoveCard(MoveCard.Type.reverse, textureReversing);
            cards.add(card1);
            cards.add(card2);
            cards.add(card3);
        }

        for (int i = 0; i < 12; i++) {
            //create and add move2 cards to list
            MoveCard card1 = new MoveCard(MoveCard.Type.move2, textureMove2);
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

    public MoveCard drawCard() {
        return stack.pop();
    }


}
