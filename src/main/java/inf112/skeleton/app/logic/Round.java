package inf112.skeleton.app.logic;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.grid.TileGrid;

import java.util.ArrayList;

/**
 * Created by Martin on 27/02/2019.
 */
public class Round {
    private MoveCard card;
    private ArrayList<MoveCard> listToChooseFrom;
    private StackOfCards deck;
    public ArrayList<Player> players;



    public Round(StackOfCards deck, ArrayList<Player> playerList) {
        this.deck = deck;
        this.players = playerList;

        for (Player p : players) {
            drawNineCards(deck);
        }

    }

    public void doTurn(ArrayList<MoveCard> cardList, ArrayList<Player> playas, TileGrid grid) {
        int tilesize = grid.tileSizeInPx;
       int numberOfPlayers = playas.size();
       int counter = 0;
       for (int i = 0; i < cardList.size(); i++) {
           int playerTurn = counter%numberOfPlayers;
           card = cardList.get(i);
           switch (playerTurn){

               case 0: playas.get(0).movePlayer(card.getType(), tilesize, grid);
               break;
               case 1: playas.get(1).movePlayer(card.getType(), tilesize, grid);
               break;
               case 2: playas.get(2).movePlayer(card.getType(), tilesize, grid);
               break;
               case 3: playas.get(3).movePlayer(card.getType(), tilesize, grid);
               break;

           }
           counter++;
       }
    }


    private ArrayList<MoveCard> drawNineCards(StackOfCards deck) {
        listToChooseFrom = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            card = deck.nextCard();
            listToChooseFrom.add(card);
        }
        return listToChooseFrom;
    }

}
