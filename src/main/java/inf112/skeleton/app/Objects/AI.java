package inf112.skeleton.app.Objects;

import inf112.skeleton.app.game.RoboGame;

import java.util.LinkedList;

public class AI {
    private Player player;
    private RoboGame game;

    public AI(Player player, RoboGame game) {
        this.player = player;
        this.game = game;
    }

    public void pickCard() {
        Position position = new Position(player.getY(), player.getX(), player.getDirection());
        Dfs dfs = new Dfs(game.grid, player.maxCardsAllowedForPlayer, position, game.listOfNine);
        dfs.dfs();
        LinkedList<Integer> selectedCards = dfs.getSelectedCards();


        while (!selectedCards.isEmpty()) {
            int index = selectedCards.poll();
            System.out.println("AI's selection: " + game.listOfNine[index]);
            player.pickCard(index);
        }
    }

}
