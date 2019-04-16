package inf112.skeleton.app.Objects;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.game.RoboGame;

public class AIPlayer extends Player {
    AI ai;

    public AIPlayer(Texture texture, RoboGame.Direction startDirection, RoboGame game, String name) {
        super(texture,startDirection, game, name);
        this.ai = new AI(this, game);
    }

    public void pickCards() {
        ai.pickCard();
    }
}
