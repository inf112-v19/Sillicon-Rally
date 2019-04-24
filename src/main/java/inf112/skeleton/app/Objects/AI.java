package inf112.skeleton.app.Objects;

import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.game.RoboGame;

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
        player.pickCard(1);
        player.pickCard(2);
        player.pickCard(3);
        player.pickCard(4);
        player.pickCard(5);

        MoveCard[] listOfNine = game.listOfNine;
        initiate();
        Position position = new Position(player.getY(), player.getX(), player.getDirection());
        Dfs dfs = new Dfs(game, player.maxCardsAllowedForPlayer, position);
    }

    private void initiate() {
        for (int i = 0; i < selected.length; i++) {
            selected[i] = false;
        }
    }


}
