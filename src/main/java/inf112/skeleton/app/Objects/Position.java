package inf112.skeleton.app.Objects;

import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.game.PlayerMovements;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;

public class Position {
    float x;
    float y;
    RoboGame.Direction dir;

    public Position(float y, float x, RoboGame.Direction dir) {
        this.y = y;
        this.x = x;
        this.dir = dir;
    }

    public void movePosition(MoveCard.Type type, PlayerMovements movements, TileGrid grid) {
        switch (type) {
            case move1:
                movements.moveStraight(1, grid.tileSizeInPx, grid);
                break;
            case move2:
                movements.moveStraight(2, grid.tileSizeInPx, grid);
                break;
            case move3:
                movements.moveStraight(3, grid.tileSizeInPx, grid);
                break;
            case reverse:
                movements.moveStraight(1, grid.tileSizeInPx*-1, grid);
                break;
            case uturn:
                movements.uTurn(grid);
                break;
            case turnleft:
                movements.rotateCounterClockwise(grid);
                break;
            case turnright:
                movements.rotateClockwise(grid);
                break;
        }
        this.y = movements.getY();
        this.x = movements.getX();
        this.dir = movements.getDirection();
    }
}
