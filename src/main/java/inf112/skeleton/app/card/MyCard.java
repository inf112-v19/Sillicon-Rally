package inf112.skeleton.app.card;


import com.badlogic.gdx.graphics.g2d.Sprite;

public class MyCard {
    private Sprite cardSprite;
    private Suit cardSuit;        //"type" of card. in classic card game the 4 suits are diamonds, hearts, clubs and spades
    private int cardValue;        //

/*
    Constructor for MyCard
    @param suit: type of card ex. "Spades, "Hearts", "Diamonds" in this game could be Move, Attack..
    @param faceName
    @param value: could be the amount of tiles player could move
 */
    public MyCard (Sprite cardSprite, Suit cardSuit, int cardValue){
        this.cardSprite = cardSprite;
        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
    }

    public enum Suit{
       MOVE, ROTATION
        }








    public Sprite getCardSprite(){ return cardSprite;   }
    public Suit getCardSuit(){ return cardSuit; }
    public int getCardValue(){  return cardValue;   }
}
