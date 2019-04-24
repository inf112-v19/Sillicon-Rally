package inf112.skeleton.app.Objects;

import inf112.skeleton.app.game.RoboGame;

public class AI {
    Player player;
    RoboGame game;

    public AI(Player player, RoboGame game) {
        this.player = player;
        this.game = game;
    }

    public void pickCard() {
        player.pickCard(1);
        player.pickCard(2);
        player.pickCard(3);
        player.pickCard(4);
        player.pickCard(5);
    }
}
