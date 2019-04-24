package inf112.skeleton.app.Objects;

import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.game.RoboGame;

import java.util.LinkedList;

public class AI {
    Player player;
    RoboGame game;
    boolean[] selected;

    public AI(Player player, RoboGame game) {
        this.player = player;
        this.game = game;
        this.selected = new boolean[9];
    }

    public void pickCard() {
        MoveCard[] listOfNine = game.listOfNine;
        initiate();
        Position position = new Position(player.getY(), player.getX(), player.getDirection());
        Dfs dfs = new Dfs(game, player.maxCardsAllowedForPlayer, position);
        dfs.dfs();
        LinkedList<Integer> selectedCards = dfs.getSelectedCards();


        while (!selectedCards.isEmpty()) {
            int index = selectedCards.poll();
            System.out.println("AI's selection: " + game.listOfNine[index]);
            player.pickCard(index);
        }
    }

    private void initiate() {
        for (int i = 0; i < selected.length; i++) {
            selected[i] = false;
        }
    }


}
