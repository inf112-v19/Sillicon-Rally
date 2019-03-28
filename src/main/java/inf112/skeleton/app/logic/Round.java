package inf112.skeleton.app.logic;

import com.badlogic.gdx.backends.headless.mock.audio.MockAudio;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;

import java.util.ArrayList;

/**
 * Created by Martin on 27/02/2019.
 */
public class Round {

    //roundNumber (int) ?
    private MoveCard card;
    private ArrayList<MoveCard> listToChooseFrom;
    public ArrayList<Player> players;
    private StackOfCards deck;
    private Player player;
    private TileGrid grid;
    private RoboGame game;

    public Round(StackOfCards deck, ArrayList<Player> playerList) {
        this.deck = deck;
        this.players = playerList;


        for (Player p : players) {
            drawNineCards(deck, p);

        }

       // doTurn();
    }

    public void doTurn(ArrayList<MoveCard> cardList1, ArrayList<Player> playas, TileGrid grid_) {
       ArrayList<Player> playerList = playas;
       int numberOfPlayers = players.size();
       int counter = 0;
       ArrayList<MoveCard> cardList = new ArrayList<>();
       for (int i = 0; i < cardList.size(); i++) {
           int playerTurn = counter%numberOfPlayers;
           card = cardList.get(i);
           switch (playerTurn){
               case 0: players.get(0).movePlayer(card.getType(), game.TILE_SIZE_IN_PX, grid_);
               case 1: players.get(1).movePlayer(card.getType(), game.TILE_SIZE_IN_PX, grid_);
               case 2: players.get(2).movePlayer(card.getType(), game.TILE_SIZE_IN_PX, grid_);
               case 3: players.get(3).movePlayer(card.getType(), game.TILE_SIZE_IN_PX, grid_);
               default:
                   System.out.println("fuck you kristian");

           }
           counter++;
       }
    }

    private ArrayList<MoveCard> drawNineCards(StackOfCards deck, Player player) {
        listToChooseFrom = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            card = deck.nextCard();
            listToChooseFrom.add(card);
        }
        return listToChooseFrom;
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
