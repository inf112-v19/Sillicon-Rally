package inf112.skeleton.app.Objects;

public class AI {
    Player player;

    public AI(Player player) {
        this.player = player;
    }

    public void pickCard() {
        player.pickCard(1);
        player.pickCard(2);
        player.pickCard(3);
        player.pickCard(4);
        player.pickCard(5);

    }
}
