package inf112.skeleton.app.Objects;

import inf112.skeleton.app.game.RoboGame;

public class Position {
    float x;
    float y;
    RoboGame.Direction dir;

    public Position(float y, float x, RoboGame.Direction dir) {
        this.y = y;
        this.x = x;
        this.dir = dir;
    }
}
