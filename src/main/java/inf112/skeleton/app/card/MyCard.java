package inf112.skeleton.app.card;



public class MyCard {
    private String faceName;
    private Suit suit;        //"type" of card. in classic card game the 4 suits are diamonds, hearts, clubs and spades
    private int value;          //


/*
    Constructor for MyCard
    @param suit: type of card ex. "Spades, "Hearts", "Diamonds" in this game could be Move, Attack..
    @param faceName
    @param value: could be the amount of tiles player could move
 */
    public MyCard (Suit suit, String faceName, int value){
        setSuit(suit);
        this.faceName = faceName;
        setValue(value);
    }



public enum Suit{
        ATTACK, MOVE;
    }


    public String getFaceName(){
       return faceName;
    }



//setters & getters for Suit
    public Suit getSuit(){  return suit; }
    public void setSuit(Suit suit){
        if (suit == null)
            throw new RuntimeException("Suit cannot be null");
        this.suit = suit;
    }


//setters & getters for Rank
    public int getValue(){  return value;    }
    public void setValue(int value){
        this.value = value;
    }


}
